package com.xindaibao.cashloan.core.common.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 性能监测，超过指定值的允许都将被记录。 如果把TimeProfiler 的log
 * level定为info，则输出简要信息，如果设为debug，则输出详细信息
 * @author
 */

public final class TimeProfiler {

	private static final Log logger = LogFactory.getLog(TimeProfiler.class);

	private static final int Default_Threshold = 10;// default value

	public final static String NewLine = "\r\n";

	public final static String SummaryHeader = "Entire running time(millis)=[";

	public final static String SummaryHeaderTimeEnd = "]";

	public final static String In = "in";

	private String id;

	private int threshold;

	private long begin = 0;

	private long end = 0;

	private List<ProfilerTask> tasks = new ArrayList<ProfilerTask>();

	private ProfilerTask currentTask;

	private static final ThreadLocal<TimeProfiler> local = new ThreadLocal<TimeProfiler>();

	/**
	 * 
	 * @param id
	 * @param threshold
	 *            超过这个值的运行将被记录
	 */
	private TimeProfiler(String id, int threshold) {
		this.id = id;
		this.threshold = threshold;
	}

	/**
	 * 当前设置是否允许profile
	 * 
	 * @return
	 */
	public static boolean isProfileEnable() {
		return logger.isInfoEnabled() || logger.isDebugEnabled();
	}

	/**
	 * 开始一个profiler任务
	 * @param name 任务名称
	 * @param threshold 超过此数值的将被记录
	 * @return
	 */
	public static TimeProfiler start(String name, int threshold) {
		TimeProfiler profiler = new TimeProfiler(name, threshold);
		local.set(profiler);
		profiler.begin = System.currentTimeMillis();
		return profiler;
	}

	/**
	 * 使用缺省值开始一个profiler任务
	 * 
	 * @param name
	 * @return
	 */
	public static TimeProfiler start(String name) {
		return start(name, Default_Threshold);
	}

	/**
	 * 结束此任务
	 * 
	 */
	public void release() {
		release(null);
	}

	/**
	 * 结束此任务，并且在此确定任务的名称
	 * 
	 * @param name
	 */
	public void release(String name) {
		local.remove();
		this.end = System.currentTimeMillis();
		logProfiler(name);
	}

	private void logProfiler(String name) {
		if (getTotalTimeMillis() < this.threshold) {
			return;
		}
		
		logger.debug(name == null ? prettyPrint() : prettyPrint(name));
		/*if (logger.isDebugEnabled()) {
			logger.debug(name == null ? prettyPrint() : prettyPrint(name));
		} else {
			if (logger.isInfoEnabled()) {
				logger.info(name == null ? shortSummary() : shortSummary(name));
			}

		}*/
	}

	private long getTotalTimeMillis() {
		return end - begin;
	}

	private String shortSummary(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("TimeProfiler output:").append(NewLine);
		sb.append(SummaryHeader).append(getTotalTimeMillis()).append(SummaryHeaderTimeEnd);
		sb.append(' ').append(In).append(' ').append(name);
		return sb.toString();
	}

	private String shortSummary() {
		return this.shortSummary(this.id);
	}

	private String prettyPrint(String name) {
		StringBuilder sb = new StringBuilder(shortSummary(name));
		for (ProfilerTask task : this.tasks) {
			sb.append(NewLine).append(task.prettyPrint(this.begin));
		}
		return sb.toString();
	}

	private String prettyPrint() {
		StringBuilder sb = new StringBuilder(shortSummary());
		for (ProfilerTask task : this.tasks) {
			sb.append(NewLine).append(task.prettyPrint(this.begin));
		}
		return sb.toString();
	}

	/**
	 * 开始一个子任务，如果没有启动profile，则此方法将启动profile运行
	 * @param string 子任务名称
	 */
	public static void beginTask(String string) {
		TimeProfiler profiler = local.get();
		boolean iFireIt = false;
		if (profiler == null) {
			profiler = start(string);
			iFireIt = true;
		}
		ProfilerTask task = profiler.new ProfilerTask(string);
		task.fire = iFireIt;
		profiler.addTask(task);
		task.start();
	}

	private void addTask(ProfilerTask task) {
		taskJoin(task);
		this.currentTask = task;
	}
	
	private void taskJoin(ProfilerTask task){
		if (this.currentTask == null) {// 没有包裹在一个父任务中运行，也就是一个顶级任务
			this.tasks.add(task);
		} else {
			this.currentTask.addChildTask(task);
		}
	}

	/**
	 * 结束当前子任务
	 */
	public static void endTask() {
		TimeProfiler profiler = local.get();
		if (profiler == null) {
			throw new IllegalStateException(
					"Can't end ProfilerTask: TimeProfiler is not running");
		}
		if (profiler.currentTask == null) {
			throw new IllegalStateException(
					"Can't end ProfilerTask: currentTask is null");
		}
		ProfilerTask current = profiler.currentTask;
		current.end();
		boolean iFireIt = current.fire;
		profiler.currentTask = profiler.currentTask.getParentTask();
		if (iFireIt) {
			profiler.release();
		}
	}
	
	/**
	 * 增加一个跟踪信息，比如一个sql
	 * 注意：目前，顶级任务无法trace
	 * @param string
	 */
	public static void addTrace(String string){
		if(string == null){
			return;
		}
		TimeProfiler profiler = local.get();
		if(profiler == null){
			return;
		}
		profiler.taskJoin(profiler.new TraceTask(string));
	}

	private class ProfilerTask {

		protected ProfilerTask parent;

		protected List<ProfilerTask> children;

		protected String name;

		protected long start;

		protected long end;

		protected int deep = 1;

		protected boolean fire = false;;// 是否是由task启动的time Profiler

		public ProfilerTask(String n) {
			this.name = n;
		}

		public StringBuilder prettyPrint(long profileStart) {
			StringBuilder sb = new StringBuilder();
			sb.append("Task:").append(this.name);
			sb.append(" running time(millis)");
			sb.append('[').append(this.start - profileStart).append("->").append(this.end - profileStart).append(']');
			sb.append('=').append(getTotalTimeMillis());
			double rate = (double) this.getTotalTimeMillis()
					/ TimeProfiler.this.getTotalTimeMillis();
			rate = rate * 100;
			sb.append(" ").append((int) rate).append('%');
			if (this.children != null) {
				for (ProfilerTask child : this.children) {
					sb.append(NewLine);
					for (int i = 0; i < this.deep; i++) {
						sb.append("--");
					}
					sb.append(child.prettyPrint(profileStart));
				}
			}
			return sb;
		}

		public void start() {
			start = System.currentTimeMillis();
		}

		public void addChildTask(ProfilerTask task) {
			if (children == null) {
				children = new ArrayList<ProfilerTask>();
			}
			children.add(task);
			task.parent = this;
			task.deep = this.deep + 1;
		}

		public void end() {
			end = System.currentTimeMillis();
		}

		public ProfilerTask getParentTask() {
			return parent;
		}

		@SuppressWarnings("unused")
		public String getName() {
			return this.name;
		}

		public long getTotalTimeMillis() {
			return end - start;
		}
	}
	
	private class TraceTask extends ProfilerTask{
		public TraceTask(String n) {
			super(n);
		}

		@Override
		public StringBuilder prettyPrint(long profileStart) {
			StringBuilder sb = new StringBuilder();
			sb.append("Trace:").append(this.name);
			if (this.children != null) {
				for (ProfilerTask child : this.children) {
					sb.append(NewLine);
					for (int i = 0; i < this.deep; i++) {
						sb.append("--");
					}
					sb.append(child.prettyPrint(profileStart));
				}
			}
			return sb;
		}
	}
}

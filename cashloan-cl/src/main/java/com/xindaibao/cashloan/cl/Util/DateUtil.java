/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this res except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xindaibao.cashloan.cl.Util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String Y_M_D = "yyyy-MM-dd";
	public static final String H_M_S = "HH:mm:ss";
	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	public static final String YMD = "yyyyMMdd";
	public static final String HMS = "HHmmss";
	public static final String YMDHMS = "yyyyMMddHHmmss";
	
	public static DateFormat dateFormat = new SimpleDateFormat(Y_M_D);
	public static DateFormat timeFormat = new SimpleDateFormat(H_M_S);
	public static DateFormat dataTimeFormat = new SimpleDateFormat(Y_M_D_H_M_S);
	public static DateFormat dateFormatDB = new SimpleDateFormat(YMD);
	public static DateFormat timeFormatDB = new SimpleDateFormat(HMS);
	public static DateFormat dataTimeFormatDB = new SimpleDateFormat(YMDHMS);
	

	/**
	 * 根据formatStr 获取DateFormat
	 * @param formatStr
	 * @return
	 */
	public static DateFormat getDateFormat(String formatStr){
		return new SimpleDateFormat(formatStr);
	}

	/**
	 * 当前日期 yyyyMMdd
	 * @return
	 */
	public static String getCurrentDateTime(String formatStr){
		Date date = new Date();
		return getDateFormat(formatStr).format(date);
	}

	/**
	 * 当前日期 yyyyMMdd
	 * @return
	 */
	public static String getCurrentDate(){
		Date date = new Date();
		return dateFormatDB.format(date);
	}

	/**
	 * 当前时间 HHmmss
	 * @return
	 */
	public static String getCurrentTime(){
		Date date = new Date();
		return timeFormatDB.format(date);
	}

	/**
	 * 当前日期时间 yyyyMMddHHmmss
	 * @return
	 */
	public static String getCurrentDateTime(){
		Date date = new Date();
		return dataTimeFormatDB.format(date);
	}
	
	public static String formatDateTime(Date date, String formatStr){
		return getDateFormat(formatStr).format(date);
	}
	
	/**
	 * 下一个工作日
	 * @Title: nextWorkDate
	 * @Description: 下一个工作日
	 * @param date 
	 * @return String
	 * @throws
	 */
	public static Date nextWorkDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekday == 5) {
			c.add(Calendar.DAY_OF_YEAR, 3);//日期加1天
		} else if (weekday == 6) {
			c.add(Calendar.DAY_OF_YEAR, 2);//日期加1天
		} else {
			c.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
		}
		
		return c.getTime();
	}

	/**
	 * 下一个工作日
	 * @Title: nextWorkDate
	 * @Description: 下一个工作日
	 * @param date yyyyMMdd
	 * @return String
	 * @throws ParseException 
	 */
	public static String nextWorkDate(String date) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(dateFormatDB.parse(date));
		
		int weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekday == 5) {
			c.add(Calendar.DAY_OF_YEAR, 3);//日期加3天
		} else if (weekday == 6) {
			c.add(Calendar.DAY_OF_YEAR, 2);//日期加2天
		} else {
			c.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
		}
		
		return dateFormatDB.format(c.getTime());
	}
	
	/**
	 * 指定日期加上指定天数
	 * @param date 日期
	 * @param day 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		if(day == 0)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);//日期加day天
//		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 指定日期减去指定天数
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date diffDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, -1*day);//日期减day天
//		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 指定日期减去指定小时数
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date diffHour(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) hour) * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 指定时间减去指定秒数
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date diffSecond(Date date, long second) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - second * 1000);
		return c.getTime();
	}

	/**
	 * 返回毫秒
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 指定时间加上指定秒数
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, long second) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + second * 1000);
		return c.getTime();
	}
	

    /**
     * 获取YYYY格式
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false)
     *
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }
	//计算时间差
	public static int daysBetween(Date date1, Date date2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		try {
			Date e = sdf.parse(dateStr7(date1));
			Date d2 = sdf.parse(dateStr7(date2));
			cal.setTime(e);
			long time1 = cal.getTimeInMillis();
			cal.setTime(d2);
			long time2 = cal.getTimeInMillis();
			return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
		} catch (ParseException var10) {
			throw new Exception("计算时间差时出现ParseException，date1:" + date1 + "，date2:" + date2, var10);
		}
	}

	public static String dateStr7(Date date) {
		return dateStr(date, "yyyyMMdd");
	}

	public static String dateStr(Date date, String f) {
		if(date == null) {
			return "";
		} else {
			SimpleDateFormat format = new SimpleDateFormat(f);
			String str = format.format(date);
			return str;
		}
	}


	/**
     * 格式化日期
     */
    public static Date parseDate(String date) {
        return parse(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     */
    public static Date parseTimeMinutes(String date) {
        return parse(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 格式化日期  用这个！！！！
     */
    public static Date parseTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     */
    public static Date parse(String date, String pattern) {
        try {
            return DateUtils.parseDate(date, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把日期转换为Timestamp
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

}

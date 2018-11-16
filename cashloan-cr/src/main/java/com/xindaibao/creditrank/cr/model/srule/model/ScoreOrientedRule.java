package com.xindaibao.creditrank.cr.model.srule.model;

/**
 * 分值导向规则信息，在结果导向规则基础上增加评分处理
 * @author
 * @version 1.0.0
 * @date 2017年1月3日 上午11:48:48


 * 

 */
public class ScoreOrientedRule extends SimpleRule {
	
	/**
	 * 结果分值
	 */
	public String resultScore;
	
	/**
	 * 条件匹配时的分值
	 */
	public String score;

	/** 
	 * 获取结果分值
	 * @return resultScore
	 */
	public String getResultScore() {
		return resultScore;
	}

	/** 
	 * 设置结果分值
	 * @param resultScore
	 */
	public void setResultScore(String resultScore) {
		this.resultScore = resultScore;
	}

	/** 
	 * 获取条件匹配时的分值
	 * @return score
	 */
	public String getScore() {
		return score;
	}

	/** 
	 * 设置条件匹配时的分值
	 * @param score
	 */
	public void setScore(String score) {
		this.score = score;
	}

	
}

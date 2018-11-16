package com.xindaibao.cashloan.rule.model;

import java.util.List;

import com.xindaibao.cashloan.rule.domain.BorrowRuleConfig;
 
public class BorrowRuleConfigModel   {
	private BorrowRuleConfig rule;
	private List<BorrowRuleConfig> configList;

	public BorrowRuleConfig getRule() {
		return rule;
	}

	public void setRule(BorrowRuleConfig rule) {
		this.rule = rule;
	}

	public List<BorrowRuleConfig> getConfigList() {
		return configList;
	}

	public void setConfigList(List<BorrowRuleConfig> configList) {
		this.configList = configList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((configList == null) ? 0 : configList.hashCode());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BorrowRuleConfigModel other = (BorrowRuleConfigModel) obj;
		if (configList == null) {
			if (other.configList != null)
				return false;
		} else if (!configList.equals(other.configList))
			return false;
		if (rule == null) {
			if (other.rule != null)
				return false;
		} else if (!rule.equals(other.rule))
			return false;
		return true;
	}
 
 
	 
  
}

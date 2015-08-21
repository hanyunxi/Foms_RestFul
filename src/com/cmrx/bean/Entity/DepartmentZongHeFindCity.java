package com.cmrx.bean.Entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepartmentZongHeFindCity {

	private String DICT_LEVEL;
	private String DICT_KEY;
	private String DICT_VALUE;
	private BigDecimal DICT_SORT;
	public String getDICT_LEVEL() {
		return DICT_LEVEL;
	}
	public void setDICT_LEVEL(String dICT_LEVEL) {
		DICT_LEVEL = dICT_LEVEL;
	}
	public String getDICT_KEY() {
		return DICT_KEY;
	}
	public void setDICT_KEY(String dICT_KEY) {
		DICT_KEY = dICT_KEY;
	}
	public String getDICT_VALUE() {
		return DICT_VALUE;
	}
	public void setDICT_VALUE(String dICT_VALUE) {
		DICT_VALUE = dICT_VALUE;
	}
	public BigDecimal getDICT_SORT() {
		return DICT_SORT;
	}
	public void setDICT_SORT(BigDecimal dICT_SORT) {
		DICT_SORT = dICT_SORT;
	}
	@Override
	public String toString() {
		return "DepartmentZongHeFindCity [DICT_LEVEL=" + DICT_LEVEL
				+ ", DICT_KEY=" + DICT_KEY + ", DICT_VALUE=" + DICT_VALUE
				+ ", DICT_SORT=" + DICT_SORT + "]";
	}
	
}

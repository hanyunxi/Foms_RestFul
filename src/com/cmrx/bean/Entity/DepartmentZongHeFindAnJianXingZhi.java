package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepartmentZongHeFindAnJianXingZhi {
	private String DICT_KEY;
	private String PARENT_KEY;
	private String ROOT_KEY;
	private String DICT_VALUE;
	private Object DICT_VALUE1;
	public String getDICT_KEY() {
		return DICT_KEY;
	}
	public void setDICT_KEY(String dICT_KEY) {
		DICT_KEY = dICT_KEY;
	}
	public String getPARENT_KEY() {
		return PARENT_KEY;
	}
	public void setPARENT_KEY(String pARENT_KEY) {
		PARENT_KEY = pARENT_KEY;
	}
	public String getROOT_KEY() {
		return ROOT_KEY;
	}
	public void setROOT_KEY(String rOOT_KEY) {
		ROOT_KEY = rOOT_KEY;
	}
	public String getDICT_VALUE() {
		return DICT_VALUE;
	}
	public void setDICT_VALUE(String dICT_VALUE) {
		DICT_VALUE = dICT_VALUE;
	}
	public Object getDICT_VALUE1() {
		return DICT_VALUE1;
	}
	public void setDICT_VALUE1(Object dICT_VALUE1) {
		DICT_VALUE1 = dICT_VALUE1;
	}
	@Override
	public String toString() {
		return "DepartmentZongHeFindAnJianXingZhi [DICT_KEY=" + DICT_KEY
				+ ", PARENT_KEY=" + PARENT_KEY + ", ROOT_KEY=" + ROOT_KEY
				+ ", DICT_VALUE=" + DICT_VALUE + ", DICT_VALUE1=" + DICT_VALUE1
				+ "]";
	}
	



	
}

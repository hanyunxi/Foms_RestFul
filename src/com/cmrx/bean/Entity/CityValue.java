package com.cmrx.bean.Entity;

 public  class CityValue {

	 private String  DICT_KEY;
	 private String  DICT_VALUE;
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
	@Override
	public String toString() {
		return "'"+DICT_VALUE.substring(3, DICT_VALUE.length())+"'";
	}
	 
	
}

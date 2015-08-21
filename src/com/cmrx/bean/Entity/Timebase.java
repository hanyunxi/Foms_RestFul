package com.cmrx.bean.Entity;


public class Timebase {

	private String name1;
	private String value1;
	
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	@Override
	public String toString() {
		return "{name1:" + "\'"+name1 + "\'"+ ",value1:" + "\'"+value1 + "\'"+ "}";
	}

}

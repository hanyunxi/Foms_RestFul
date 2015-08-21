package com.cmrx.bean.Entity;

public class SearchGetSyntheticalCaseQishuMap {

	private String name;
	private Object scount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getScount() {
		return scount;
	}

	public void setScount(Object scount) {
		this.scount = scount;
	}

	@Override
	public String toString() {
		return "{\"name\":" + "\"" + name + "\"" + ",\"scount\":" + "\""
				+ scount + "\"" + "}";
	}

}

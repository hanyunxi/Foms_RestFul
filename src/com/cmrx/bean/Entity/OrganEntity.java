package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrganEntity {

	private String shortName;
	private Object nook;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Object getNook() {
		return nook;
	}

	public void setNook(Object nook) {
		this.nook = nook;
	}

	

}

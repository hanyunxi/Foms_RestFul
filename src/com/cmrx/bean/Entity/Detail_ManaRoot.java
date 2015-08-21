package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Detail_ManaRoot {
	private String ROOTKEY;
	private String PARENTKEY;
	public String getROOTKEY() {
		return ROOTKEY;
	}
	public void setROOTKEY(String rOOTKEY) {
		ROOTKEY = rOOTKEY;
	}
	public String getPARENTKEY() {
		return PARENTKEY;
	}
	public void setPARENTKEY(String pARENTKEY) {
		PARENTKEY = pARENTKEY;
	}
}

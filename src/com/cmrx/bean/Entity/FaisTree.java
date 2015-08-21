package com.cmrx.bean.Entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FaisTree {
	private BigDecimal ID;
	private BigDecimal PARENT_KEY;
	private String TEXT;
	private BigDecimal ISPARENT;
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	public BigDecimal getPARENT_KEY() {
		return PARENT_KEY;
	}
	public void setPARENT_KEY(BigDecimal pARENT_KEY) {
		PARENT_KEY = pARENT_KEY;
	}
	public String getTEXT() {
		return TEXT;
	}
	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	public BigDecimal getISPARENT() {
		return ISPARENT;
	}
	public void setISPARENT(BigDecimal iSPARENT) {
		ISPARENT = iSPARENT;
	}
	
}

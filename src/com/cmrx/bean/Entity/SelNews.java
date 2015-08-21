package com.cmrx.bean.Entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SelNews {
	private BigDecimal NEWSID;
	private BigDecimal RN;
	private String NEWSTITLE;
	private String NEWSTYPE;
	public BigDecimal getNEWSID() {
		return NEWSID;
	}
	public void setNEWSID(BigDecimal nEWSID) {
		NEWSID = nEWSID;
	}
	
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	public String getNEWSTITLE() {
		return NEWSTITLE;
	}
	public void setNEWSTITLE(String nEWSTITLE) {
		NEWSTITLE = nEWSTITLE;
	}
	public String getNEWSTYPE() {
		return NEWSTYPE;
	}
	public void setNEWSTYPE(String nEWSTYPE) {
		NEWSTYPE = nEWSTYPE;
	}
	
}

package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewsListByType {
	private BigDecimal NEWSID;
	private String NEWSTITLE;
	private String NEWSCONTENT;
	private String CREATEDATE;
	private String NEWSTYPE;
	private String NEWSIMG;
	private BigDecimal RN;
	public BigDecimal getNEWSID() {
		return NEWSID;
	}
	public void setNEWSID(BigDecimal nEWSID) {
		NEWSID = nEWSID;
	}
	public String getNEWSTITLE() {
		return NEWSTITLE;
	}
	public void setNEWSTITLE(String nEWSTITLE) {
		NEWSTITLE = nEWSTITLE;
	}
	public String getNEWSCONTENT() {
		return NEWSCONTENT;
	}
	public void setNEWSCONTENT(String nEWSCONTENT) {
		NEWSCONTENT = nEWSCONTENT;
	}
	public String getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(String cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	public String getNEWSTYPE() {
		return NEWSTYPE;
	}
	public void setNEWSTYPE(String nEWSTYPE) {
		NEWSTYPE = nEWSTYPE;
	}
	public String getNEWSIMG() {
		return NEWSIMG;
	}
	public void setNEWSIMG(String nEWSIMG) {
		NEWSIMG = nEWSIMG;
	}
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	
}

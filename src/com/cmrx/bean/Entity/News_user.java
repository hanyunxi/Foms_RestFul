package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class News_user {
	//Newsinformation查询属性
	private BigDecimal NEWSID;
	private String NEWSTITLE;
	private String NEWSCONTENT;
	private String NEWSTYPE;
	private String TRUENAME;
	private String CREATEDATE;
	private BigDecimal RN;
	
	private String BEFORETITLE;
	//newsbeforenext用到
	private BigDecimal P;
	
	public BigDecimal getP() {
		return P;
	}
	public void setP(BigDecimal p) {
		P = p;
	}
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
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
	public String getNEWSTYPE() {
		return NEWSTYPE;
	}
	public void setNEWSTYPE(String nEWSTYPE) {
		NEWSTYPE = nEWSTYPE;
	}
	public String getTRUENAME() {
		return TRUENAME;
	}
	public void setTRUENAME(String tRUENAME) {
		TRUENAME = tRUENAME;
	}
	public String getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(String cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	public String getBEFORETITLE() {
		return BEFORETITLE;
	}
	public void setBEFORETITLE(String bEFORETITLE) {
		BEFORETITLE = bEFORETITLE;
	}
	
}

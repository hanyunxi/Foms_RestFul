package com.cmrx.bean.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;

@XmlRootElement
public class Analyzeresult {
	private BigDecimal ID;
	private String TASKNUM;
	private Integer LEVELNUM;
	private String SYSNUM;
	private String SYSTYPE;
	private Timestamp CREATETIME;
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	public String getTASKNUM() {
		return TASKNUM;
	}
	public void setTASKNUM(String tASKNUM) {
		TASKNUM = tASKNUM;
	}
	public Integer getLEVELNUM() {
		return LEVELNUM;
	}
	public void setLEVELNUM(Integer lEVELNUM) {
		LEVELNUM = lEVELNUM;
	}
	public String getSYSNUM() {
		return SYSNUM;
	}
	public void setSYSNUM(String sYSNUM) {
		SYSNUM = sYSNUM;
	}
	public String getSYSTYPE() {
		return SYSTYPE;
	}
	public void setSYSTYPE(String sYSTYPE) {
		SYSTYPE = sYSTYPE;
	}
	public Timestamp getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(Timestamp cREATETIME) {
		CREATETIME = cREATETIME;
	}
}	

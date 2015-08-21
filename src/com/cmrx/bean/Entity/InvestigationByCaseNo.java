package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InvestigationByCaseNo {
	private String INVESTIGATION_NO;
	private String CASE_NO;//案件编号
	private String CASE_NAME;//案件类别名称
	private String OCCURRENCE_DATE_FROM;//发案时间(起)
	private String OCCURRENCE_DATE_TO;//发案时间（止)
	public String getINVESTIGATION_NO() {
		return INVESTIGATION_NO;
	}
	public void setINVESTIGATION_NO(String iNVESTIGATION_NO) {
		INVESTIGATION_NO = iNVESTIGATION_NO;
	}
	public String getCASE_NO() {
		return CASE_NO;
	}
	public void setCASE_NO(String cASE_NO) {
		CASE_NO = cASE_NO;
	}
	public String getCASE_NAME() {
		return CASE_NAME;
	}
	public void setCASE_NAME(String cASE_NAME) {
		CASE_NAME = cASE_NAME;
	}
	public String getOCCURRENCE_DATE_FROM() {
		return OCCURRENCE_DATE_FROM;
	}
	public void setOCCURRENCE_DATE_FROM(String oCCURRENCE_DATE_FROM) {
		OCCURRENCE_DATE_FROM = oCCURRENCE_DATE_FROM;
	}
	public String getOCCURRENCE_DATE_TO() {
		return OCCURRENCE_DATE_TO;
	}
	public void setOCCURRENCE_DATE_TO(String oCCURRENCE_DATE_TO) {
		OCCURRENCE_DATE_TO = oCCURRENCE_DATE_TO;
	}
	
}

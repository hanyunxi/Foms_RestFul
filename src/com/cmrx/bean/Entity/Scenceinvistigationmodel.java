package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Scenceinvistigationmodel {
	private String INVESTIGATION_NO;
	private Object INVESTIGATION_DATE_FROM;
	private Object OCCURRENCE_DATE_FROM;
	private String PROTECTOR;
	private String INVESTIGATION_PLACE;
	private BigDecimal RN;
	
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}

	public String getINVESTIGATION_NO() {
		return INVESTIGATION_NO;
	}
	public void setINVESTIGATION_NO(String iNVESTIGATION_NO) {
		INVESTIGATION_NO = iNVESTIGATION_NO;
	}

	public Object getINVESTIGATION_DATE_FROM() {
		return INVESTIGATION_DATE_FROM;
	}
	public void setINVESTIGATION_DATE_FROM(Object iNVESTIGATION_DATE_FROM) {
		INVESTIGATION_DATE_FROM = iNVESTIGATION_DATE_FROM;
	}
	public Object getOCCURRENCE_DATE_FROM() {
		return OCCURRENCE_DATE_FROM;
	}
	public void setOCCURRENCE_DATE_FROM(Object oCCURRENCE_DATE_FROM) {
		OCCURRENCE_DATE_FROM = oCCURRENCE_DATE_FROM;
	}
	public void setOCCURRENCE_DATE_FROM(Date oCCURRENCE_DATE_FROM) {
		OCCURRENCE_DATE_FROM = oCCURRENCE_DATE_FROM;
	}
	public String getPROTECTOR() {
		return PROTECTOR;
	}
	public void setPROTECTOR(String pROTECTOR) {
		PROTECTOR = pROTECTOR;
	}
	public String getINVESTIGATION_PLACE() {
		return INVESTIGATION_PLACE;
	}
	public void setINVESTIGATION_PLACE(String iNVESTIGATION_PLACE) {
		INVESTIGATION_PLACE = iNVESTIGATION_PLACE;
	}
	@Override
	public String toString() {
		return "Scenceinvistigationmodel [INVESTIGATION_NO=" + INVESTIGATION_NO
				+ ", INVESTIGATION_DATE_FROM=" + INVESTIGATION_DATE_FROM
				+ ", OCCURRENCE_DATE_FROM=" + OCCURRENCE_DATE_FROM
				+ ", PROTECTOR=" + PROTECTOR + ", INVESTIGATION_PLACE="
				+ INVESTIGATION_PLACE + ", RN=" + RN + "]";
	}

	

}

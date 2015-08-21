package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Studylist {
	private BigDecimal ID;
	private String FORMULANAME;
	private String CREATEUSER;
	private String CREATETIME;
	private String FORMULAREMARK;
	private BigDecimal RN;
	
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	
	public String getFORMULAREMARK() {
		return FORMULAREMARK;
	}
	public void setFORMULAREMARK(String fORMULAREMARK) {
		FORMULAREMARK = fORMULAREMARK;
	}
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	public String getFORMULANAME() {
		return FORMULANAME;
	}
	public void setFORMULANAME(String fORMULANAME) {
		FORMULANAME = fORMULANAME;
	}
	
	public String getCREATEUSER() {
		return CREATEUSER;
	}
	public void setCREATEUSER(String cREATEUSER) {
		CREATEUSER = cREATEUSER;
	}
	public String getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}
}

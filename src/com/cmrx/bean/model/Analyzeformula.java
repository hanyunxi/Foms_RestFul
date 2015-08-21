package com.cmrx.bean.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Analyzeformula {
	private BigDecimal ID;
	private String JSONTEXT;
	private String FORMULANAME;
	private String FORMULAREMARK;
	private String FSTATEMENTS;
	private Integer FORMULASTATUS;
	private Timestamp CREATETIME;
	private String CREATEUSER;
	private Timestamp UPDATETIME;
	private BigDecimal RN;
	private String UPDATEUSER;
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	public String getJSONTEXT() {
		return JSONTEXT;
	}
	public void setJSONTEXT(String jSONTEXT) {
		JSONTEXT = jSONTEXT;
	}
	public String getFSTATEMENTS() {
		return FSTATEMENTS;
	}
	public void setFSTATEMENTS(String fSTATEMENTS) {
		FSTATEMENTS = fSTATEMENTS;
	}
	public String getFORMULANAME() {
		return FORMULANAME;
	}
	public void setFORMULANAME(String fORMULANAME) {
		FORMULANAME = fORMULANAME;
	}
	public String getFORMULAREMARK() {
		return FORMULAREMARK;
	}
	public void setFORMULAREMARK(String fORMULAREMARK) {
		FORMULAREMARK = fORMULAREMARK;
	}

	public Integer getFORMULASTATUS() {
		return FORMULASTATUS;
	}
	public void setFORMULASTATUS(Integer fORMULASTATUS) {
		FORMULASTATUS = fORMULASTATUS;
	}
	public Timestamp getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(Timestamp cREATETIME) {
		CREATETIME = cREATETIME;
	}
	public String getCREATEUSER() {
		return CREATEUSER;
	}
	public void setCREATEUSER(String cREATEUSER) {
		CREATEUSER = cREATEUSER;
	}
	public Timestamp getUPDATETIME() {
		return UPDATETIME;
	}
	public void setUPDATETIME(Timestamp uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	public String getUPDATEUSER() {
		return UPDATEUSER;
	}
	public void setUPDATEUSER(String uPDATEUSER) {
		UPDATEUSER = uPDATEUSER;
	}
	
}

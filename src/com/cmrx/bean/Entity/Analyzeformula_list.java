package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Analyzeformula_list {
	private BigDecimal ID;
	private Timestamp UPDATETIME;
	private BigDecimal RN;
	private String UPDATEUSER;
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
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

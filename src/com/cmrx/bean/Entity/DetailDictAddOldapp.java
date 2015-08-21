package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetailDictAddOldapp {
	//这个是通过sql查询，没有通过hibernate映射类型对应，所以这里是BigInteger
	//否则就是Long类型的，看StudyAnalysisBean
	private BigDecimal ID;
	private String CREATEUSER;
	private String CREATEUNIT;
	private String DICTVAL;
	private String DICTPY;
	private BigDecimal ADDSTATUS;
	private BigDecimal RN;
	private String CREATEDATETIME;
	
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	public String getCREATEUSER() {
		return CREATEUSER;
	}
	public void setCREATEUSER(String cREATEUSER) {
		CREATEUSER = cREATEUSER;
	}
	public String getCREATEUNIT() {
		return CREATEUNIT;
	}
	public void setCREATEUNIT(String cREATEUNIT) {
		CREATEUNIT = cREATEUNIT;
	}
	public String getDICTVAL() {
		return DICTVAL;
	}
	public void setDICTVAL(String dICTVAL) {
		DICTVAL = dICTVAL;
	}
	public String getDICTPY() {
		return DICTPY;
	}
	public void setDICTPY(String dICTPY) {
		DICTPY = dICTPY;
	}
	public BigDecimal getADDSTATUS() {
		return ADDSTATUS;
	}
	public void setADDSTATUS(BigDecimal aDDSTATUS) {
		ADDSTATUS = aDDSTATUS;
	}
	public String getCREATEDATETIME() {
		return CREATEDATETIME;
	}
	public void setCREATEDATETIME(String cREATEDATETIME) {
		CREATEDATETIME = cREATEDATETIME;
	}
}

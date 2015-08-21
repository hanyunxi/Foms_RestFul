package com.cmrx.bean.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;

@XmlRootElement
public class DnaSercabase {
	private BigDecimal ID;
	private String DNA_SERIAL_NO;
	private String SCASEID;
	private String SRC_LAB_REGIONALISM;
	private String SRC_LAB_NAME;
	private String CREATE_DATETIME;
	private BigDecimal RN;
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	public String getDNA_SERIAL_N0() {
		return DNA_SERIAL_NO;
	}
	public void setDNA_SERIAL_N0(String dNA_SERIAL_NO) {
		DNA_SERIAL_NO = dNA_SERIAL_NO;
	}
	public String getSCASEID() {
		return SCASEID;
	}
	public void setSCASEID(String sCASEID) {
		SCASEID = sCASEID;
	}
	public String getSRC_LAB_REGIONALISM() {
		return SRC_LAB_REGIONALISM;
	}
	public void setSRC_LAB_REGIONALISM(String sRC_LAB_REGIONALISM) {
		SRC_LAB_REGIONALISM = sRC_LAB_REGIONALISM;
	}
	public String getSRC_LAB_NAME() {
		return SRC_LAB_NAME;
	}
	public void setSRC_LAB_NAME(String sRC_LAB_NAME) {
		SRC_LAB_NAME = sRC_LAB_NAME;
	}
	public String getCREATE_DATETIME() {
		return CREATE_DATETIME;
	}
	public void setCREATE_DATETIME(String cREATE_DATETIME) {
		CREATE_DATETIME = cREATE_DATETIME;
	}
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	
}

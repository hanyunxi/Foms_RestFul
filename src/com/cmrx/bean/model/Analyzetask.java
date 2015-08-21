package com.cmrx.bean.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;

@XmlRootElement
public class Analyzetask {
	private BigDecimal ID;
	private String TASKNAME;
	private String TASKREMARK;
	private String TASKNUM;
	private int FORMULAID;
	private int TSTATUS;
	private String STATUS;//study方法getAnalyzetask用到
	private Timestamp CREATETIME;
	private String CREATETIME_S;//String类型的
	private String CREATEUSER;//ID
	private String CREATEUSERNAME;//名字
	private Timestamp ENDTIME;
	private String JSONTEXT;
	private String RESULTJSON;
	private BigDecimal RN;
	
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	
	public String getCREATEUSERNAME() {
		return CREATEUSERNAME;
	}
	public void setCREATEUSERNAME(String cREATEUSERNAME) {
		CREATEUSERNAME = cREATEUSERNAME;
	}
	public String getCREATETIME_S() {
		return CREATETIME_S;
	}
	public void setCREATETIME_S(String cREATETIME_S) {
		CREATETIME_S = cREATETIME_S;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	public String getTASKNAME() {
		return TASKNAME;
	}
	public void setTASKNAME(String tASKNAME) {
		TASKNAME = tASKNAME;
	}
	public String getTASKREMARK() {
		return TASKREMARK;
	}
	public void setTASKREMARK(String tASKREMARK) {
		TASKREMARK = tASKREMARK;
	}
	public String getTASKNUM() {
		return TASKNUM;
	}
	public void setTASKNUM(String tASKNUM) {
		TASKNUM = tASKNUM;
	}
	public int getFORMULAID() {
		return FORMULAID;
	}
	public void setFORMULAID(int fORMULAID) {
		FORMULAID = fORMULAID;
	}
	public int getTSTATUS() {
		return TSTATUS;
	}
	public void setTSTATUS(int tSTATUS) {
		TSTATUS = tSTATUS;
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
	public Timestamp getENDTIME() {
		return ENDTIME;
	}
	public void setENDTIME(Timestamp eNDTIME) {
		ENDTIME = eNDTIME;
	}
	public String getJSONTEXT() {
		return JSONTEXT;
	}
	public void setJSONTEXT(String jSONTEXT) {
		JSONTEXT = jSONTEXT;
	}
	public String getRESULTJSON() {
		return RESULTJSON;
	}
	public void setRESULTJSON(String rESULTJSON) {
		RESULTJSON = rESULTJSON;
	}
	
}

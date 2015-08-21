package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScenceinvistigationmodelDetails {

	private BigDecimal ID;// ID
	private String INVESTIGATION_NO;// 勘查号
	private String CASE_NO;// 案件编号
	private String RECEPTION_NO;// 案事件受理号
	private Date INVESTIGATION_DATE_FROM;// 勘验开始时间
	private Date INVESTIGATION_DATE_TO;// 勘验结束时间
	private String INVESTIGATION_PLACE;// 案件现场地点
	private String INVESTIGATOR;// 勘验人员
	private String PROTECTION_MEASURE;// 现场保护措施
	private String PROTECTOR;// 现场保护人
	private String CASE_TYPE;// 案件类别编号
	private String CASE_NAME;// 案件类别名称
	private String SCENE_DETAIL;// 发案地点
	private String EXPOSURE_PROCESS;// 案件发现过程
	private Date PROTECTION_DATE;// 现场保护时间
	private Date NOTE_MADE_DATE;// 制作笔录时间
	private Date TRANSFER_DATE;// 信息上报时间
	private Date CREATE_DATETIME;// 勘验信息创建时间
	private Date CRACKED_DATE;// 破案时间
	private Date OCCURRENCE_DATE_FROM;// 发案时间(起)
	private Date OCCURRENCE_DATE_TO;// 发案时间（止)
	private Date RECEIVED_DATE;// 接警时间
	private Date DISPATCH_DATE;// 出警时间
	private BigDecimal RN;// oracle的行

	private String INIT_SERVER_NO;

	public String getINIT_SERVER_NO() {
		return INIT_SERVER_NO;
	}

	public void setINIT_SERVER_NO(String iNIT_SERVER_NO) {
		INIT_SERVER_NO = iNIT_SERVER_NO;
	}

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

	public String getRECEPTION_NO() {
		return RECEPTION_NO;
	}

	public void setRECEPTION_NO(String rECEPTION_NO) {
		RECEPTION_NO = rECEPTION_NO;
	}

	public Date getINVESTIGATION_DATE_FROM() {
		return INVESTIGATION_DATE_FROM;
	}

	public void setINVESTIGATION_DATE_FROM(Date iNVESTIGATION_DATE_FROM) {
		INVESTIGATION_DATE_FROM = iNVESTIGATION_DATE_FROM;
	}

	public Date getINVESTIGATION_DATE_TO() {
		return INVESTIGATION_DATE_TO;
	}

	public void setINVESTIGATION_DATE_TO(Date iNVESTIGATION_DATE_TO) {
		INVESTIGATION_DATE_TO = iNVESTIGATION_DATE_TO;
	}

	public String getINVESTIGATION_PLACE() {
		return INVESTIGATION_PLACE;
	}

	public void setINVESTIGATION_PLACE(String iNVESTIGATION_PLACE) {
		INVESTIGATION_PLACE = iNVESTIGATION_PLACE;
	}

	public String getINVESTIGATOR() {
		return INVESTIGATOR;
	}

	public void setINVESTIGATOR(String iNVESTIGATOR) {
		INVESTIGATOR = iNVESTIGATOR;
	}

	public String getPROTECTION_MEASURE() {
		return PROTECTION_MEASURE;
	}

	public void setPROTECTION_MEASURE(String pROTECTION_MEASURE) {
		PROTECTION_MEASURE = pROTECTION_MEASURE;
	}

	public String getPROTECTOR() {
		return PROTECTOR;
	}

	public void setPROTECTOR(String pROTECTOR) {
		PROTECTOR = pROTECTOR;
	}

	public String getCASE_TYPE() {
		return CASE_TYPE;
	}

	public void setCASE_TYPE(String cASE_TYPE) {
		CASE_TYPE = cASE_TYPE;
	}

	public String getCASE_NAME() {
		return CASE_NAME;
	}

	public void setCASE_NAME(String cASE_NAME) {
		CASE_NAME = cASE_NAME;
	}

	public String getSCENE_DETAIL() {
		return SCENE_DETAIL;
	}

	public void setSCENE_DETAIL(String sCENE_DETAIL) {
		SCENE_DETAIL = sCENE_DETAIL;
	}

	public String getEXPOSURE_PROCESS() {
		return EXPOSURE_PROCESS;
	}

	public void setEXPOSURE_PROCESS(String eXPOSURE_PROCESS) {
		EXPOSURE_PROCESS = eXPOSURE_PROCESS;
	}

	public Date getPROTECTION_DATE() {
		return PROTECTION_DATE;
	}

	public void setPROTECTION_DATE(Date pROTECTION_DATE) {
		PROTECTION_DATE = pROTECTION_DATE;
	}

	public Date getNOTE_MADE_DATE() {
		return NOTE_MADE_DATE;
	}

	public void setNOTE_MADE_DATE(Date nOTE_MADE_DATE) {
		NOTE_MADE_DATE = nOTE_MADE_DATE;
	}

	public Date getTRANSFER_DATE() {
		return TRANSFER_DATE;
	}

	public void setTRANSFER_DATE(Date tRANSFER_DATE) {
		TRANSFER_DATE = tRANSFER_DATE;
	}

	public Date getCREATE_DATETIME() {
		return CREATE_DATETIME;
	}

	public void setCREATE_DATETIME(Date cREATE_DATETIME) {
		CREATE_DATETIME = cREATE_DATETIME;
	}

	public Date getCRACKED_DATE() {
		return CRACKED_DATE;
	}

	public void setCRACKED_DATE(Date cRACKED_DATE) {
		CRACKED_DATE = cRACKED_DATE;
	}

	public Date getOCCURRENCE_DATE_FROM() {
		return OCCURRENCE_DATE_FROM;
	}

	public void setOCCURRENCE_DATE_FROM(Date oCCURRENCE_DATE_FROM) {
		OCCURRENCE_DATE_FROM = oCCURRENCE_DATE_FROM;
	}

	public Date getOCCURRENCE_DATE_TO() {
		return OCCURRENCE_DATE_TO;
	}

	public void setOCCURRENCE_DATE_TO(Date oCCURRENCE_DATE_TO) {
		OCCURRENCE_DATE_TO = oCCURRENCE_DATE_TO;
	}

	public Date getRECEIVED_DATE() {
		return RECEIVED_DATE;
	}

	public void setRECEIVED_DATE(Date rECEIVED_DATE) {
		RECEIVED_DATE = rECEIVED_DATE;
	}

	public Date getDISPATCH_DATE() {
		return DISPATCH_DATE;
	}

	public void setDISPATCH_DATE(Date dISPATCH_DATE) {
		DISPATCH_DATE = dISPATCH_DATE;
	}

	public BigDecimal getRN() {
		return RN;
	}

	public void setRN(BigDecimal rN) {
		RN = rN;
	}

	public BigDecimal getID() {
		return ID;
	}

	public void setID(BigDecimal iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "ScenceinvistigationmodelDetails [ID=" + ID
				+ ", INVESTIGATION_NO=" + INVESTIGATION_NO + ", CASE_NO="
				+ CASE_NO + ", RECEPTION_NO=" + RECEPTION_NO
				+ ", INVESTIGATION_DATE_FROM=" + INVESTIGATION_DATE_FROM
				+ ", INVESTIGATION_DATE_TO=" + INVESTIGATION_DATE_TO
				+ ", INVESTIGATION_PLACE=" + INVESTIGATION_PLACE
				+ ", INVESTIGATOR=" + INVESTIGATOR + ", PROTECTION_MEASURE="
				+ PROTECTION_MEASURE + ", PROTECTOR=" + PROTECTOR
				+ ", CASE_TYPE=" + CASE_TYPE + ", CASE_NAME=" + CASE_NAME
				+ ", SCENE_DETAIL=" + SCENE_DETAIL + ", EXPOSURE_PROCESS="
				+ EXPOSURE_PROCESS + ", PROTECTION_DATE=" + PROTECTION_DATE
				+ ", NOTE_MADE_DATE=" + NOTE_MADE_DATE + ", TRANSFER_DATE="
				+ TRANSFER_DATE + ", CREATE_DATETIME=" + CREATE_DATETIME
				+ ", CRACKED_DATE=" + CRACKED_DATE + ", OCCURRENCE_DATE_FROM="
				+ OCCURRENCE_DATE_FROM + ", OCCURRENCE_DATE_TO="
				+ OCCURRENCE_DATE_TO + ", RECEIVED_DATE=" + RECEIVED_DATE
				+ ", DISPATCH_DATE=" + DISPATCH_DATE + ", RN=" + RN + "]";
	}

}

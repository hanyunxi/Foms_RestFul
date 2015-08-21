package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScenceinvistigationmodelDetailsInfo {
	private BigDecimal ID;
	private String INVESTIGATION_NO;
	private String CASE_NO;
	private String RECEPTION_NO;
	private Date INVESTIGATION_DATE_FROM;
	private Date INVESTIGATION_DATE_TO;
	private String INVESTIGATION_PLACE;
	private String INVESTIGATOR;
	private Date PROTECTION_DATE;
	private String PROTECTION_MEASURE;
	private String PROTECTOR;
	private Date NOTE_MADE_DATE;
	private Date TRANSFER_DATE;
	private Date CREATE_DATETIME;
	private Date CRACKED_DATE;
	private String CASE_TYPE;
	private String CASE_NAME;
	private String SCENE_DETAIL;
	private Date OCCURRENCE_DATE_FROM;
	private Date OCCURRENCE_DATE_TO;
	private String EXPOSURE_PROCESS;
	private Date RECEIVED_DATE;
	private Date DISPATCH_DATE;
	private String INIT_SERVER_NO;
	private String INTENTION;
	private String INTENTION_CN;
	private String LOCATION;
	private String LOCATION_CN;
	private String INVOLVED_ORG_TYPE;
	private String INVOLVED_ORG_CN;
	private String COMMISSION_PERIOD;
	private String COMMISSION_PERIOD_CN;
	private String ENTRANCE_EXIT;
	private String ENTRANCE_EXIT_CN;
	private String INTRUDING_WAY;
	private String INTRUDING_WAY_CN;
	private String COMMISSION_MEANS;
	private String COMMISSION_MEANS_CN;
	private String COMMISSION_POINTS;
	private String COMMISSION_POINTS_CN;
	private String MOTIVATION;
	private String MOTIVATION_CN;
	private String CASE_PROPERTY;
	private String CASE_PROPERTY_CN;

	private String CBYJ;// 串并依据
	
	private String INVESTIGATION_DATE_FROM_S;// 串并依据
	
	public String getINVESTIGATION_DATE_FROM_S() {
		return INVESTIGATION_DATE_FROM_S;
	}
	public void setINVESTIGATION_DATE_FROM_S(String iNVESTIGATION_DATE_FROM_S) {
		INVESTIGATION_DATE_FROM_S = iNVESTIGATION_DATE_FROM_S;
	}
	public String getCBYJ() {
		return CBYJ;
	}
	public void setCBYJ(String cBYJ) {
		CBYJ = cBYJ;
	}

	public BigDecimal getID() {
		return ID;
	}

	public void setID(BigDecimal iD) {
		ID = iD;
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

	public Date getPROTECTION_DATE() {
		return PROTECTION_DATE;
	}

	public void setPROTECTION_DATE(Date pROTECTION_DATE) {
		PROTECTION_DATE = pROTECTION_DATE;
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

	public String getEXPOSURE_PROCESS() {
		return EXPOSURE_PROCESS;
	}

	public void setEXPOSURE_PROCESS(String eXPOSURE_PROCESS) {
		EXPOSURE_PROCESS = eXPOSURE_PROCESS;
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

	public String getINIT_SERVER_NO() {
		return INIT_SERVER_NO;
	}

	public void setINIT_SERVER_NO(String iNIT_SERVER_NO) {
		INIT_SERVER_NO = iNIT_SERVER_NO;
	}

	public String getINTENTION() {
		return INTENTION;
	}

	public void setINTENTION(String iNTENTION) {
		INTENTION = iNTENTION;
	}

	public String getINTENTION_CN() {
		return INTENTION_CN;
	}

	public void setINTENTION_CN(String iNTENTION_CN) {
		INTENTION_CN = iNTENTION_CN;
	}

	public String getLOCATION() {
		return LOCATION;
	}

	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}

	public String getLOCATION_CN() {
		return LOCATION_CN;
	}

	public void setLOCATION_CN(String lOCATION_CN) {
		LOCATION_CN = lOCATION_CN;
	}

	public String getINVOLVED_ORG_TYPE() {
		return INVOLVED_ORG_TYPE;
	}

	public void setINVOLVED_ORG_TYPE(String iNVOLVED_ORG_TYPE) {
		INVOLVED_ORG_TYPE = iNVOLVED_ORG_TYPE;
	}

	public String getINVOLVED_ORG_CN() {
		return INVOLVED_ORG_CN;
	}

	public void setINVOLVED_ORG_CN(String iNVOLVED_ORG_CN) {
		INVOLVED_ORG_CN = iNVOLVED_ORG_CN;
	}

	public String getCOMMISSION_PERIOD() {
		return COMMISSION_PERIOD;
	}

	public void setCOMMISSION_PERIOD(String cOMMISSION_PERIOD) {
		COMMISSION_PERIOD = cOMMISSION_PERIOD;
	}

	public String getCOMMISSION_PERIOD_CN() {
		return COMMISSION_PERIOD_CN;
	}

	public void setCOMMISSION_PERIOD_CN(String cOMMISSION_PERIOD_CN) {
		COMMISSION_PERIOD_CN = cOMMISSION_PERIOD_CN;
	}

	public String getENTRANCE_EXIT() {
		return ENTRANCE_EXIT;
	}

	public void setENTRANCE_EXIT(String eNTRANCE_EXIT) {
		ENTRANCE_EXIT = eNTRANCE_EXIT;
	}

	public String getENTRANCE_EXIT_CN() {
		return ENTRANCE_EXIT_CN;
	}

	public void setENTRANCE_EXIT_CN(String eNTRANCE_EXIT_CN) {
		ENTRANCE_EXIT_CN = eNTRANCE_EXIT_CN;
	}

	public String getINTRUDING_WAY() {
		return INTRUDING_WAY;
	}

	public void setINTRUDING_WAY(String iNTRUDING_WAY) {
		INTRUDING_WAY = iNTRUDING_WAY;
	}

	public String getINTRUDING_WAY_CN() {
		return INTRUDING_WAY_CN;
	}

	public void setINTRUDING_WAY_CN(String iNTRUDING_WAY_CN) {
		INTRUDING_WAY_CN = iNTRUDING_WAY_CN;
	}

	public String getCOMMISSION_MEANS() {
		return COMMISSION_MEANS;
	}

	public void setCOMMISSION_MEANS(String cOMMISSION_MEANS) {
		COMMISSION_MEANS = cOMMISSION_MEANS;
	}

	public String getCOMMISSION_MEANS_CN() {
		return COMMISSION_MEANS_CN;
	}

	public void setCOMMISSION_MEANS_CN(String cOMMISSION_MEANS_CN) {
		COMMISSION_MEANS_CN = cOMMISSION_MEANS_CN;
	}

	public String getCOMMISSION_POINTS() {
		return COMMISSION_POINTS;
	}

	public void setCOMMISSION_POINTS(String cOMMISSION_POINTS) {
		COMMISSION_POINTS = cOMMISSION_POINTS;
	}

	public String getCOMMISSION_POINTS_CN() {
		return COMMISSION_POINTS_CN;
	}

	public void setCOMMISSION_POINTS_CN(String cOMMISSION_POINTS_CN) {
		COMMISSION_POINTS_CN = cOMMISSION_POINTS_CN;
	}

	public String getMOTIVATION() {
		return MOTIVATION;
	}

	public void setMOTIVATION(String mOTIVATION) {
		MOTIVATION = mOTIVATION;
	}

	public String getMOTIVATION_CN() {
		return MOTIVATION_CN;
	}

	public void setMOTIVATION_CN(String mOTIVATION_CN) {
		MOTIVATION_CN = mOTIVATION_CN;
	}

	public String getCASE_PROPERTY() {
		return CASE_PROPERTY;
	}

	public void setCASE_PROPERTY(String cASE_PROPERTY) {
		CASE_PROPERTY = cASE_PROPERTY;
	}

	public String getCASE_PROPERTY_CN() {
		return CASE_PROPERTY_CN;
	}

	public void setCASE_PROPERTY_CN(String cASE_PROPERTY_CN) {
		CASE_PROPERTY_CN = cASE_PROPERTY_CN;
	}

}

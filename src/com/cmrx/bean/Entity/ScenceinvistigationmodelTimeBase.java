package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScenceinvistigationmodelTimeBase {
	private String RECEIVED_DATE;//接警时间
	private String DISPATCH_DATE;//出警时间
	private String OCCURRENCE_DATE_FROM;//发案时间起
	private String OCCURRENCE_DATE_TO;//发案时间止
	private String NOTE_MADE_DATE;//制作笔录时间
	private String PROTECTION_DATE;//现场保护时间
	private String TRANSFER_DATE;//信息上报时间
	private String INVESTIGATION_DATE_FROM;//勘验开始时间
	private String INVESTIGATION_DATE_TO;//勘验结束时间
	public String getRECEIVED_DATE() {
		return RECEIVED_DATE;
	}
	public void setRECEIVED_DATE(String rECEIVED_DATE) {
		RECEIVED_DATE = rECEIVED_DATE;
	}
	public String getDISPATCH_DATE() {
		return DISPATCH_DATE;
	}
	public void setDISPATCH_DATE(String dISPATCH_DATE) {
		DISPATCH_DATE = dISPATCH_DATE;
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
	public String getNOTE_MADE_DATE() {
		return NOTE_MADE_DATE;
	}
	public void setNOTE_MADE_DATE(String nOTE_MADE_DATE) {
		NOTE_MADE_DATE = nOTE_MADE_DATE;
	}
	public String getPROTECTION_DATE() {
		return PROTECTION_DATE;
	}
	public void setPROTECTION_DATE(String pROTECTION_DATE) {
		PROTECTION_DATE = pROTECTION_DATE;
	}
	public String getTRANSFER_DATE() {
		return TRANSFER_DATE;
	}
	public void setTRANSFER_DATE(String tRANSFER_DATE) {
		TRANSFER_DATE = tRANSFER_DATE;
	}
	public String getINVESTIGATION_DATE_FROM() {
		return INVESTIGATION_DATE_FROM;
	}
	public void setINVESTIGATION_DATE_FROM(String iNVESTIGATION_DATE_FROM) {
		INVESTIGATION_DATE_FROM = iNVESTIGATION_DATE_FROM;
	}
	public String getINVESTIGATION_DATE_TO() {
		return INVESTIGATION_DATE_TO;
	}
	public void setINVESTIGATION_DATE_TO(String iNVESTIGATION_DATE_TO) {
		INVESTIGATION_DATE_TO = iNVESTIGATION_DATE_TO;
	}
	@Override
	public String toString() {
		return "ScenceinvistigationmodelTimeBase [RECEIVED_DATE="
				+ RECEIVED_DATE + ", DISPATCH_DATE=" + DISPATCH_DATE
				+ ", OCCURRENCE_DATE_FROM=" + OCCURRENCE_DATE_FROM
				+ ", OCCURRENCE_DATE_TO=" + OCCURRENCE_DATE_TO
				+ ", NOTE_MADE_DATE=" + NOTE_MADE_DATE + ", PROTECTION_DATE="
				+ PROTECTION_DATE + ", TRANSFER_DATE=" + TRANSFER_DATE
				+ ", INVESTIGATION_DATE_FROM=" + INVESTIGATION_DATE_FROM
				+ ", INVESTIGATION_DATE_TO=" + INVESTIGATION_DATE_TO + "]";
	}

	

}

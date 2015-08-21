package com.cmrx.bean.Entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Sercapic {
	private String SERCAID;
	private String SCENEID;
	private String DEPTID;
	private String DEPTNAME;
	private String INPUTTIME;
	private String INPUTUSERNAME;
	public String getSERCAID() {
		return SERCAID;
	}
	public void setSERCAID(String sERCAID) {
		SERCAID = sERCAID;
	}
	public String getSCENEID() {
		return SCENEID;
	}
	public void setSCENEID(String sCENEID) {
		SCENEID = sCENEID;
	}
	public String getDEPTID() {
		return DEPTID;
	}
	public void setDEPTID(String dEPTID) {
		DEPTID = dEPTID;
	}
	public String getDEPTNAME() {
		return DEPTNAME;
	}
	public void setDEPTNAME(String dEPTNAME) {
		DEPTNAME = dEPTNAME;
	}
	public String getINPUTTIME() {
		return INPUTTIME;
	}
	public void setINPUTTIME(String iNPUTTIME) {
		INPUTTIME = iNPUTTIME;
	}
	public String getINPUTUSERNAME() {
		return INPUTUSERNAME;
	}
	public void setINPUTUSERNAME(String iNPUTUSERNAME) {
		INPUTUSERNAME = iNPUTUSERNAME;
	}
	
}

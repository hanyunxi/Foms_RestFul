package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sercabase {
	private String SERCAID;
	private String SERCANAME;
	private String SERCAGIST;
	private String INPUTUSER;
	private String INPUTTIME;
	private BigDecimal RN;
	
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	public String getSERCAID() {
		return SERCAID;
	}
	public void setSERCAID(String sERCAID) {
		SERCAID = sERCAID;
	}
	public String getSERCANAME() {
		return SERCANAME;
	}
	public void setSERCANAME(String sERCANAME) {
		SERCANAME = sERCANAME;
	}
	public String getSERCAGIST() {
		return SERCAGIST;
	}
	public void setSERCAGIST(String sERCAGIST) {
		SERCAGIST = sERCAGIST;
	}
	public String getINPUTUSER() {
		return INPUTUSER;
	}
	public void setINPUTUSER(String iNPUTUSER) {
		INPUTUSER = iNPUTUSER;
	}
	public String getINPUTTIME() {
		return INPUTTIME;
	}
	public void setINPUTTIME(String iNPUTTIME) {
		INPUTTIME = iNPUTTIME;
	}
	
}

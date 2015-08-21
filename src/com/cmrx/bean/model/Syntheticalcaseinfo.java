package com.cmrx.bean.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Syntheticalcaseinfo {
	private BigDecimal ID;
	//private String ID;
	private String SYNTHETICALNUM;
	private String FOOTNUM;
	private String DNANUM;
	private String FINGERNUM;
	private String UPDATEDATE;
	
	private String CBYJ;//别名  串并依据
	private String CBH;//别名  串并号
	private BigDecimal RN;
	
	
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	public String getSYNTHETICALNUM() {
		return SYNTHETICALNUM;
	}
	public void setSYNTHETICALNUM(String sYNTHETICALNUM) {
		SYNTHETICALNUM = sYNTHETICALNUM;
	}
	
	public String getUPDATEDATE() {
		return UPDATEDATE;
	}
	public void setUPDATEDATE(String uPDATEDATE) {
		UPDATEDATE = uPDATEDATE;
	}
	public String getCBYJ() {
		return CBYJ;
	}
	public void setCBYJ(String cBYJ) {
		CBYJ = cBYJ;
	}
	public String getCBH() {
		return CBH;
	}
	public void setCBH(String cBH) {
		CBH = cBH;
	}
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}
	public String getFOOTNUM() {
		return FOOTNUM;
	}
	public void setFOOTNUM(String fOOTNUM) {
		FOOTNUM = fOOTNUM;
	}
	public String getDNANUM() {
		return DNANUM;
	}
	public void setDNANUM(String dNANUM) {
		DNANUM = dNANUM;
	}
	public String getFINGERNUM() {
		return FINGERNUM;
	}
	public void setFINGERNUM(String fINGERNUM) {
		FINGERNUM = fINGERNUM;
	}
	
}

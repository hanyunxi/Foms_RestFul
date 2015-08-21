package com.cmrx.bean.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchGetSyntheticalCaseQishu {
	private String SYNTHETICALNUM;
	private String FOOTNUM;
	private String DNANUM;
	private String FINGERNUM;
	private Date CREATEDATE;
	private BigDecimal SCOUNT;
	public String getSYNTHETICALNUM() {
		return SYNTHETICALNUM;
	}
	public void setSYNTHETICALNUM(String sYNTHETICALNUM) {
		SYNTHETICALNUM = sYNTHETICALNUM;
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
	public Date getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(Date cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}

	public BigDecimal getSCOUNT() {
		return SCOUNT;
	}
	public void setSCOUNT(BigDecimal sCOUNT) {
		SCOUNT = sCOUNT;
	}
	@Override
	public String toString() {
		return "SearchGetSyntheticalCaseQishu [SYNTHETICALNUM="
				+ SYNTHETICALNUM + ", FOOTNUM=" + FOOTNUM + ", DNANUM="
				+ DNANUM + ", FINGERNUM=" + FINGERNUM + ", CREATEDATE="
				+ CREATEDATE + ", SCOUNT=" + SCOUNT + "]";
	}
	
	
	
	
	
}

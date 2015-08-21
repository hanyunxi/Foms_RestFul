package com.cmrx.bean.Entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchGetSyntheticalCaseChuanShu {
	private String FOOTNUM;
	private String DNANUM;
	private String FINGERNUM;
	private BigDecimal SCOUNT;
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
	public BigDecimal getSCOUNT() {
		return SCOUNT;
	}
	public void setSCOUNT(BigDecimal sCOUNT) {
		SCOUNT = sCOUNT;
	}
	@Override
	public String toString() {
		return "SearchGetSyntheticalCaseChuanShu [FOOTNUM=" + FOOTNUM
				+ ", DNANUM=" + DNANUM + ", FINGERNUM=" + FINGERNUM
				+ ", SCOUNT=" + SCOUNT + "]";
	}

}

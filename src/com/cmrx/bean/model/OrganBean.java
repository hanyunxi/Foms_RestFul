package com.cmrx.bean.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrganBean {
	private BigDecimal ORGANID;
	private int parentId;
	private String organName;
	private String shortName;
	private String organCode;
	private int order;

	public BigDecimal getORGANID() {
		return ORGANID;
	}
	public void setORGANID(BigDecimal oRGANID) {
		ORGANID = oRGANID;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setORGANNAME(String organName) {
		this.organName = organName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}

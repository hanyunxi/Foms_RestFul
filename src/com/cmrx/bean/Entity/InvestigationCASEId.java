package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InvestigationCASEId {
	private String  CASE_ID;
    private String EVIDENCE_NAME;
	public String getCASE_ID() {
		return CASE_ID;
	}

	public void setCASE_ID(String cASE_ID) {
		CASE_ID = cASE_ID;
	}

	public String getEVIDENCE_NAME() {
		return EVIDENCE_NAME;
	}

	public void setEVIDENCE_NAME(String eVIDENCE_NAME) {
		EVIDENCE_NAME = eVIDENCE_NAME;
	}
	

	

	
}

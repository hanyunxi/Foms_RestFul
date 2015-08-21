package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepartSuspectDetails {
	private String xcky;
	private String caseNum;
	private String suspectNum;
	private String suspectAge;
	private String suspectBirthday;
	private String suspectSex;
	private String suspectName;
	private String suspectCardId;
	private String suspectAdd;
	public String getXcky() {
		return xcky;
	}
	public void setXcky(String xcky) {
		this.xcky = xcky;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getSuspectNum() {
		return suspectNum;
	}
	public void setSuspectNum(String suspectNum) {
		this.suspectNum = suspectNum;
	}
	public String getSuspectAge() {
		return suspectAge;
	}
	public void setSuspectAge(String suspectAge) {
		this.suspectAge = suspectAge;
	}
	public String getSuspectBirthday() {
		return suspectBirthday;
	}
	public void setSuspectBirthday(String suspectBirthday) {
		this.suspectBirthday = suspectBirthday;
	}
	public String getSuspectSex() {
		return suspectSex;
	}
	public void setSuspectSex(String suspectSex) {
		this.suspectSex = suspectSex;
	}
	public String getSuspectName() {
		return suspectName;
	}
	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}
	public String getSuspectCardId() {
		return suspectCardId;
	}
	public void setSuspectCardId(String suspectCardId) {
		this.suspectCardId = suspectCardId;
	}
	public String getSuspectAdd() {
		return suspectAdd;
	}
	public void setSuspectAdd(String suspectAdd) {
		this.suspectAdd = suspectAdd;
	}
	
}

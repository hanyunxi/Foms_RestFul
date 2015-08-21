package com.cmrx.bean.Entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Depart_synAll {

	private String caseNum;
	private String xcky;
	private String accord;
	private String uploadUser;
	private String uploadUnit;
	private Date createTime;
	private String caseType;
	private String caseAdd;
	private Date caseTime;
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getXcky() {
		return xcky;
	}
	public void setXcky(String xcky) {
		this.xcky = xcky;
	}
	public String getAccord() {
		return accord;
	}
	public void setAccord(String accord) {
		this.accord = accord;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getUploadUnit() {
		return uploadUnit;
	}
	public void setUploadUnit(String uploadUnit) {
		this.uploadUnit = uploadUnit;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseAdd() {
		return caseAdd;
	}
	public void setCaseAdd(String caseAdd) {
		this.caseAdd = caseAdd;
	}
	public Date getCaseTime() {
		return caseTime;
	}
	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}
	
	
}

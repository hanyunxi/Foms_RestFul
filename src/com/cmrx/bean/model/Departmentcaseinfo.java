package com.cmrx.bean.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Departmentcaseinfo {
	private Long id;//数据库中bigint对应java中的类型大写的Long
	private String xcky;
	private String caseNum;
	private String departmentNum;
	private String footNum;
	private int dataType;
	private String caseType;
	private String caseAdd;
	private String caseVal;
	private Date caseTime;
	private String uploadUnit;
	private String uploadUser;
	private String accord;
	private String suspectName;
	private String suspectAge;
	private String suspectSex;
	private String suspectNum;
	private String suspectNativePlace;
	private String suspectAdd;
	private String suspectCardId;
	private String suspectBirthday;
	private Date createTime;
	private Date updateTime;
	private int dataStatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getDepartmentNum() {
		return departmentNum;
	}
	public void setDepartmentNum(String departmentNum) {
		this.departmentNum = departmentNum;
	}
	public String getFootNum() {
		return footNum;
	}
	public void setFootNum(String footNum) {
		this.footNum = footNum;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
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
	public String getCaseVal() {
		return caseVal;
	}
	public void setCaseVal(String caseVal) {
		this.caseVal = caseVal;
	}
	public Date getCaseTime() {
		return caseTime;
	}
	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}
	public String getUploadUnit() {
		return uploadUnit;
	}
	public void setUploadUnit(String uploadUnit) {
		this.uploadUnit = uploadUnit;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getAccord() {
		return accord;
	}
	public void setAccord(String accord) {
		this.accord = accord;
	}
	public String getSuspectName() {
		return suspectName;
	}
	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}
	public String getSuspectAge() {
		return suspectAge;
	}
	public void setSuspectAge(String suspectAge) {
		this.suspectAge = suspectAge;
	}
	public String getSuspectSex() {
		return suspectSex;
	}
	public void setSuspectSex(String suspectSex) {
		this.suspectSex = suspectSex;
	}
	public String getSuspectNum() {
		return suspectNum;
	}
	public void setSuspectNum(String suspectNum) {
		this.suspectNum = suspectNum;
	}
	public String getSuspectNativePlace() {
		return suspectNativePlace;
	}
	public void setSuspectNativePlace(String suspectNativePlace) {
		this.suspectNativePlace = suspectNativePlace;
	}
	public String getSuspectAdd() {
		return suspectAdd;
	}
	public void setSuspectAdd(String suspectAdd) {
		this.suspectAdd = suspectAdd;
	}
	public String getSuspectCardId() {
		return suspectCardId;
	}
	public void setSuspectCardId(String suspectCardId) {
		this.suspectCardId = suspectCardId;
	}
	public String getSuspectBirthday() {
		return suspectBirthday;
	}
	public void setSuspectBirthday(String suspectBirthday) {
		this.suspectBirthday = suspectBirthday;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}
	
}

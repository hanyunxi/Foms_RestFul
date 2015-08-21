package com.cmrx.bean.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudyAnalysisBean {
	private Long studyId;//数据库中bigint对应java中的类型大写的Long
	private String studyName;
	private int userId;
	private Date createDate;
	private String hideItem;
	private String hideLine;
	private String hideAllData;
	private int hideItemCount;
	private Date updateDate;
	
	public Long getStudyId() {
		return studyId;
	}
	public void setStudyId(Long studyId) {
		this.studyId = studyId;
	}
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getHideItem() {
		return hideItem;
	}
	public void setHideItem(String hideItem) {
		this.hideItem = hideItem;
	}
	public String getHideLine() {
		return hideLine;
	}
	public void setHideLine(String hideLine) {
		this.hideLine = hideLine;
	}
	public String getHideAllData() {
		return hideAllData;
	}
	public void setHideAllData(String hideAllData) {
		this.hideAllData = hideAllData;
	}
	public int getHideItemCount() {
		return hideItemCount;
	}
	public void setHideItemCount(int hideItemCount) {
		this.hideItemCount = hideItemCount;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}

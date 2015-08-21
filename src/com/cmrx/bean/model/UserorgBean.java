package com.cmrx.bean.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserorgBean {
	private String username;
	private String pwd;
	private String trueName;
	private String organName;
	private String organCode;
	private int userid;
	private int organId;
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getOrganId() {
		return organId;
	}
	public void setOrganId(int organId) {
		this.organId = organId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "UserorgBean [username=" + username + ", pwd=" + pwd
				+ ", trueName=" + trueName + ", organName=" + organName
				+ ", organCode=" + organCode + ", userid=" + userid
				+ ", organId=" + organId + "]";
	}
	
	
}

package com.cmrx.bean.Entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRoleOrgan {
	private BigDecimal USERID;
	private String pwd;
	private String userCard;
	private String userName;
	private String trueName;
	private String roleName;
	private String organName;
	private BigDecimal RN;
	
	public BigDecimal getRN() {
		return RN;
	}
	public void setRN(BigDecimal rN) {
		RN = rN;
	}

	
	public BigDecimal getUSERID() {
		return USERID;
	}
	public void setUSERID(BigDecimal uSERID) {
		USERID = uSERID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPWD(String pwd) {
		this.pwd = pwd;
	}
	public String getUserCard() {
		return userCard;
	}
	public void setUSERCARD(String userCard) {
		this.userCard = userCard;
	}
	public String getUserName() {
		return userName;
	}
	public void setUSERNAME(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTRUENAME(String trueName) {
		this.trueName = trueName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setROLENAME(String roleName) {
		this.roleName = roleName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setORGANNAME(String organName) {
		this.organName = organName;
	}
}

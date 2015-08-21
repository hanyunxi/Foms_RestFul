package com.cmrx.bean.model;

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetailDictAddBean {
	private Long id;
	private String dictkey;
	private String rootKey;
	private String parentKey;
	private String dictVal;
	private String descript;
	private String dictPY;
	private String createUser;
	private String createUnit;
	private Date createDatetime;
	private String manager1;
	private String managerUnit1;
	private Date manager1Datetime;
	private String manager2;
	private String managerUnit2;
	private Date manager2Datetime;
	private int addStatus;
	private String resultContext;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDictkey() {
		return dictkey;
	}
	public void setDictkey(String dictkey) {
		this.dictkey = dictkey;
	}
	public String getRootKey() {
		return rootKey;
	}
	public void setRootKey(String rootKey) {
		this.rootKey = rootKey;
	}
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getDictVal() {
		return dictVal;
	}
	public void setDictVal(String dictVal) {
		this.dictVal = dictVal;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getDictPY() {
		return dictPY;
	}
	public void setDictPY(String dictPY) {
		this.dictPY = dictPY;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateUnit() {
		return createUnit;
	}
	public void setCreateUnit(String createUnit) {
		this.createUnit = createUnit;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getManager1() {
		return manager1;
	}
	public void setManager1(String manager1) {
		this.manager1 = manager1;
	}
	public String getManagerUnit1() {
		return managerUnit1;
	}
	public void setManagerUnit1(String managerUnit1) {
		this.managerUnit1 = managerUnit1;
	}
	public Date getManager1Datetime() {
		return manager1Datetime;
	}
	public void setManager1Datetime(Date manager1Datetime) {
		this.manager1Datetime = manager1Datetime;
	}
	public String getManager2() {
		return manager2;
	}
	public void setManager2(String manager2) {
		this.manager2 = manager2;
	}
	public String getManagerUnit2() {
		return managerUnit2;
	}
	public void setManagerUnit2(String managerUnit2) {
		this.managerUnit2 = managerUnit2;
	}
	public Date getManager2Datetime() {
		return manager2Datetime;
	}
	public void setManager2Datetime(Date manager2Datetime) {
		this.manager2Datetime = manager2Datetime;
	}
	
	public int getAddStatus() {
		return addStatus;
	}
	public void setAddStatus(int addStatus) {
		this.addStatus = addStatus;
	}
	public String getResultContext() {
		return resultContext;
	}
	public void setResultContext(String resultContext) {
		this.resultContext = resultContext;
	}
	
}

package com.cmrx.bean.Entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.cmrx.dao.DateUtil;
@XmlRootElement
public class Depart_syn {
	private String syntheticalNum;
	private String footNum;
	private String dnaNum;
	private String fingerNum;
	private String createDate;
	private String departmentNum;
	private String accord;
	private Object scount;
	
	public Object getScount() {
		return scount;
	}
	public void setScount(Object scount) {
		this.scount = scount;
	}
	public String getSyntheticalNum() {
		return syntheticalNum;
	}
	public void setSyntheticalNum(String syntheticalNum) {
		this.syntheticalNum = syntheticalNum;
	}
	public String getFootNum() {
		return footNum;
	}
	public void setFootNum(String footNum) {
		this.footNum = footNum;
	}
	public String getDnaNum() {
		return dnaNum;
	}
	public void setDnaNum(String dnaNum) {
		this.dnaNum = dnaNum;
	}
	public String getFingerNum() {
		return fingerNum;
	}
	public void setFingerNum(String fingerNum) {
		this.fingerNum = fingerNum;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = DateUtil.format(createDate);
	}
	public String getDepartmentNum() {
		return departmentNum;
	}
	public void setDepartmentNum(String departmentNum) {
		this.departmentNum = departmentNum;
	}
	public String getAccord() {
		return accord;
	}
	public void setAccord(String accord) {
		this.accord = accord;
	}
	
}

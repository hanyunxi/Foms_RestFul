package com.cmrx.bean.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetailDictBean {
	private String id;//mysql是char类型
	private String dict_level;
	private String dict_key;
	private String parent_key;
	private String root_key;
	private String dict_value1;
	private String dict_value2;
	private String dict_value3;
	private String leaf_flag;
	private String download_flag;
	private String readonly_flag;
	private BigDecimal dict_sort;//mysql中是decimal
	private String dict_py;
	private String open_flag;
	private String remark;//mysql中text对应java的String
	private String create_user;
	private Date create_datetime;
	private String update_user;
	private Date update_datetime;
	private Byte isdetail;//mysql中是tinyint
	private String descript;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDict_level() {
		return dict_level;
	}
	public void setDict_level(String dict_level) {
		this.dict_level = dict_level;
	}
	public String getDict_key() {
		return dict_key;
	}
	public void setDict_key(String dict_key) {
		this.dict_key = dict_key;
	}
	public String getParent_key() {
		return parent_key;
	}
	public void setParent_key(String parent_key) {
		this.parent_key = parent_key;
	}
	public String getRoot_key() {
		return root_key;
	}
	public void setRoot_key(String root_key) {
		this.root_key = root_key;
	}
	public String getDict_value1() {
		return dict_value1;
	}
	public void setDict_value1(String dict_value1) {
		this.dict_value1 = dict_value1;
	}
	public String getDict_value2() {
		return dict_value2;
	}
	public void setDict_value2(String dict_value2) {
		this.dict_value2 = dict_value2;
	}
	public String getDict_value3() {
		return dict_value3;
	}
	public void setDict_value3(String dict_value3) {
		this.dict_value3 = dict_value3;
	}
	public String getLeaf_flag() {
		return leaf_flag;
	}
	public void setLeaf_flag(String leaf_flag) {
		this.leaf_flag = leaf_flag;
	}
	public String getDownload_flag() {
		return download_flag;
	}
	public void setDownload_flag(String download_flag) {
		this.download_flag = download_flag;
	}
	public String getReadonly_flag() {
		return readonly_flag;
	}
	public void setReadonly_flag(String readonly_flag) {
		this.readonly_flag = readonly_flag;
	}
	public BigDecimal getDict_sort() {
		return dict_sort;
	}
	public void setDict_sort(BigDecimal dict_sort) {
		this.dict_sort = dict_sort;
	}
	public String getDict_py() {
		return dict_py;
	}
	public void setDict_py(String dict_py) {
		this.dict_py = dict_py;
	}
	public String getOpen_flag() {
		return open_flag;
	}
	public void setOpen_flag(String open_flag) {
		this.open_flag = open_flag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_datetime() {
		return create_datetime;
	}
	public void setCreate_datetime(Date create_datetime) {
		this.create_datetime = create_datetime;
	}
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public Date getUpdate_datetime() {
		return update_datetime;
	}
	public void setUpdate_datetime(Date update_datetime) {
		this.update_datetime = update_datetime;
	}

	public Byte getIsdetail() {
		return isdetail;
	}
	public void setIsdetail(Byte isdetail) {
		this.isdetail = isdetail;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
}

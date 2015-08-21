package com.cmrx.bean.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Role_authority {

	private int roleId;
	private String roleName;
	private String authority;
	public int getRoleId() {
		return roleId;
	}
	public void setROLEID(int roleId) {//oracle中默认就是查询出来是大写的
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAUTHORITY(String authority) {
		this.authority = authority;
	}
	
}

package com.cmrx.bean.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleAuthBean {

	private int roleId;
	private String roleName;
	private String authority;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
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
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}

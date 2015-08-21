package com.cmrx.bean.Entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrganizationData {

	/*
	 * select ID,SHORTENED_NAME AS TEXT,'rootuser' as PARENT_KEY,'1' as isparent
	 * from ORGANIZATION
	 */
	private String ID;
	private String TEXT;
	private String PARENT_KEY;
	private Object ISPARENT;
	private boolean checked;
	private List<OrganizationData> children;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTEXT() {
		return TEXT;
	}

	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}

	public String getPARENT_KEY() {
		return PARENT_KEY;
	}

	public void setPARENT_KEY(String pARENT_KEY) {
		PARENT_KEY = pARENT_KEY;
	}

	public Object getISPARENT() {
		return ISPARENT;
	}

	public void setISPARENT(Object iSPARENT) {
		ISPARENT = iSPARENT;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<OrganizationData> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationData> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "OrganizationData [ID=" + ID + ", TEXT=" + TEXT
				+ ", PARENT_KEY=" + PARENT_KEY + ", ISPARENT=" + ISPARENT
				+ ", checked=" + checked + ", children=" + children + "]";
	}

	



	
	

}

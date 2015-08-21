package com.cmrx.bean.Entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetailTree {
	private String ID;
	private String PARENT_KEY;
	private String TEXT;
	private Object ISPARENT;

	//匹配DetailDict.java中的@Path("/tree")
	private String DICT_KEY;//这个字段。
	
	private String DICT_PY;//
	private BigDecimal ISDETAIL;//
	private String DESCRIPT;//
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	public BigDecimal getISDETAIL() {
		return ISDETAIL;
	}

	public void setISDETAIL(BigDecimal iSDETAIL) {
		ISDETAIL = iSDETAIL;
	}

	public String getDESCRIPT() {
		return DESCRIPT;
	}

	public void setDESCRIPT(String dESCRIPT) {
		DESCRIPT = dESCRIPT;
	}

	public String getPARENT_KEY() {
		return PARENT_KEY;
	}

	public void setPARENT_KEY(String pARENT_KEY) {
		PARENT_KEY = pARENT_KEY;
	}

	public String getTEXT() {
		return TEXT;
	}

	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}

	public Object getISPARENT() {
		return ISPARENT;
	}

	public void setISPARENT(Object iSPARENT) {
		ISPARENT = iSPARENT;
	}

	public String getDICT_KEY() {
		return DICT_KEY;
	}

	public void setDICT_KEY(String dICT_KEY) {
		DICT_KEY = dICT_KEY;
	}

	public String getDICT_PY() {
		return DICT_PY;
	}

	public void setDICT_PY(String dICT_PY) {
		DICT_PY = dICT_PY;
	}
	
	
	
}

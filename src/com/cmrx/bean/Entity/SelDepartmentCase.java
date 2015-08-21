package com.cmrx.bean.Entity;

import java.text.ParseException;
import java.util.Date;

import com.cmrx.dao.DateUtil;

public class SelDepartmentCase {
	private String DEPARTMENTNUM;
	private String ACCORD;
	private String CREATETIME;
	private String UPLOADUSER;
	private Object DCOUNT;// 必须是Object，别的会报错
	public String getDEPARTMENTNUM() {
		return DEPARTMENTNUM;
	}
	public void setDEPARTMENTNUM(String dEPARTMENTNUM) {
		DEPARTMENTNUM = dEPARTMENTNUM;
	}
	public String getACCORD() {
		return ACCORD;
	}
	public void setACCORD(String aCCORD) {
		ACCORD = aCCORD;
	}
	public String getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}
	public String getUPLOADUSER() {
		return UPLOADUSER;
	}
	public void setUPLOADUSER(String uPLOADUSER) {
		UPLOADUSER = uPLOADUSER;
	}
	public Object getDCOUNT() {
		return DCOUNT;
	}
	public void setDCOUNT(Object dCOUNT) {
		DCOUNT = dCOUNT;
	}

	
}

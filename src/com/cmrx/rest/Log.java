package com.cmrx.rest;

import java.util.Date;

import com.cmrx.bean.model.LogBean;
import com.cmrx.dao.DBSupport;

public class Log {
	private DBSupport dbSupport;
	
	public DBSupport getDbSupport() {
		return dbSupport;
	}

	public void setDbSupport(DBSupport dbSupport) {
		this.dbSupport = dbSupport;
	}

	public void AddLog(String type,String context,String username) 
	{
		//System.out.println("type="+type+"context="+context+"usernamem=="+username);
		context = context.replaceAll("'", "\"");
		LogBean logBean=new LogBean();
		logBean.setLogContext(context);
		logBean.setLogType(type);
		logBean.setLogUser(username);
		logBean.setLogTime(new Date());
		try {
			dbSupport.SaveByObject(logBean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

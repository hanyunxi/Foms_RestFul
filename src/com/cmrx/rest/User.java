package com.cmrx.rest;


import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.cmrx.bean.model.UserorgBean;
import com.cmrx.dao.DBSupport;
import com.sun.jersey.api.view.Viewable;

@Path("/User")
public class User {
	public DBSupport dbSupport;
	public Log log;

	public DBSupport getDbSupport() {
		return dbSupport;
	}

	public void setDbSupport(DBSupport dbSupport) {
		this.dbSupport = dbSupport;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	@POST
	@Produces("application/json;charset=UTF-8")
	public UserorgBean Login(@FormParam(value = "name") String name,@FormParam(value = "pwd") String pwd) {
		try {
			return dbSupport.GetUserorg(name,pwd);
		} catch (Exception e) {
			//log.AddLog("E", "Login(" + name + "," + pwd + ")" + e.getMessage(),"");
			//return null;
			throw new RuntimeException(e.getMessage());
		}
	}
	
}

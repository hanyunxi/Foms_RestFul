package com.cmrx.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cmrx.dao.DBSupport;
import com.cmrx.dao.HibernateSessionUtil;

@Path("/IntegrativeQuery")
public class IntegrativeQuery {
	public DBSupport dbSupport;
	public Log log;
	public HibernateSessionUtil hs;

	public HibernateSessionUtil getHs() {
		return hs;
	}

	public void setHs(HibernateSessionUtil hs) {
		this.hs = hs;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public DBSupport getDbSupport() {
		return dbSupport;
	}

	public void setDbSupport(DBSupport dbSupport) {
		this.dbSupport = dbSupport;
	}

	@POST
	@Path("/getXckyInfo")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetXckyInfo() {
		/**
		 * {"Xcfxyj":"","Zagj":"","Zatd":"","Kysjbeg":"","Ajbh":"bbb","Zagjpd":
		 * "1","Sadw":"","Zajckpd":"1","Pasjend":"","Sfxa":"10","Kyry":"",
		 * "HjwzqkCheck"
		 * :["9","10"],"Fadd":"","Sadwpd":"1","Zajck":"","Xzdx":"","Hjwzqkpd"
		 * :"1"
		 * ,"Faqh":"","rowscount":"-1","Kysjend":"","Tjsjend":"","Xckyh":"aaa"
		 * ,"Faddpd"
		 * :"1","Ajxz":"","Zasjpd":"1","Zatdpd":"1","Sfma":"10","Sswp":""
		 * ,"Zasd":
		 * "","Pasjbeg":"","Ajlb":"","Hjwzqk":"9、10","Xzdxpd":"1","Qrfspd"
		 * :"1","Zasj"
		 * :"","Zasdpd":"1","Tjsjbeg":"","Sswppd":"1","Zartdpd":"1","Xzcspd"
		 * :"1","Bhrbar":"","Zartd":"","Xzcs":"","Qrfs":""}
		 */
		// JSONObject.fromObject(qdata));
		System.out.println("dsada");
		// System.out.println(qdata);
		return "sadas";
	}

	@POST
	@Path("/getInfoByUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetInfoByUser() {
		return "success";
	}
}

package com.cmrx.rest;

import javax.servlet.http.HttpServletResponse;

public abstract class IResponse {
	
	public void setResponse(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", "origin,x-requested-with,content-type");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

}

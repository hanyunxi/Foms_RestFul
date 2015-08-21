package com.cmrx.bean.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Watcher {
	private int watchId;
	private String watchType;
	private Date selectTime;
	private Date lastTime;
	public int getWatchId() {
		return watchId;
	}
	public void setWatchId(int watchId) {
		this.watchId = watchId;
	}
	public String getWatchType() {
		return watchType;
	}
	public void setWatchType(String watchType) {
		this.watchType = watchType;
	}
	public Date getSelectTime() {
		return selectTime;
	}
	public void setSelectTime(Date selectTime) {
		this.selectTime = selectTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
		
}

package com.cmrx.bean.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetDataCityValue {

	private String text;
	private String textvalue;
	private boolean checked;
  /*  private String textid;*/
    
/*	public String getTextid() {
		return textid;
	}
	public void setTextid(String textid) {
		this.textid = textid;
	}*/
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextvalue() {
		return textvalue;
	}
	public void setTextvalue(String textvalue) {
		this.textvalue = textvalue;
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	/*@Override
	public String toString() {
		return "{\"text\":" + "\""+text+"\""+"}";
	}
	*/
	@Override
	public String toString() {
		return "{\"checked\":"+checked+",\"text\":"+ "\""+text +"\""+ ",\"textvalue\":"+"\""+textvalue+"\""+"}";
	}
	/*@Override
	public String toString() {
		return "{\"text\":" +"\""+ text +"\""+ ",\"textvalue\":" +"\""+ textvalue+"\""
				+ ",\"textid\":" + "\""+textid +"\""+ "}";
	}*/
	
	
}

package com.cmrx.bean.Entity;

public class DepartmentZongHeFindGetCheck {
	private String text;
	private String textvalue;
	private String state;
	private boolean checked;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "DepartmentZongHeFindGetCheck [text=" + text + ", textvalue="
				+ textvalue + ", state=" + state + ", checked=" + checked + "]";
	}

	
	

}

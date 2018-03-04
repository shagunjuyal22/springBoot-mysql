package com.mysql.mysqlproject.model;

import java.io.Serializable;
import java.util.List;

public class Parameter implements Serializable{
	String title;
	String value;
	List<Parameter> children;
	
	
	
	public Parameter(String title, String value, List<Parameter> children) {
		super();
		this.title = title;
		this.value = value;
		this.children = children;
	}
	public Parameter() {
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<Parameter> getChildren() {
		return children;
	}
	public void setChildren(List<Parameter> children) {
		this.children = children;
	}
	
	

}

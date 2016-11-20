package com.netease.course.dao;

public class User {

	String username;
	Integer usertype;
	public User(String username, Integer usertype) {
		this.username = username;
		this.usertype = usertype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	
}

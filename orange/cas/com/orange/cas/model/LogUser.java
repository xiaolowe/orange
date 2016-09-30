package com.orange.cas.model;

import java.io.Serializable;

public class LogUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7604069249059167372L;
	private String userName;
	private String userMobile;
	
	public LogUser(){
		
	}
	
	public LogUser(String userName, String userMobile){
		this.userName = userName;
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

}

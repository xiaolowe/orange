package com.orange.web.action.common.entity;

import com.google.gson.Gson;

public class ResultJson{

	
	private String code;
	
	private String msg;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public  static void main(String[] args){  
		ResultJson u = new ResultJson();
		u.setCode("123e2");
		u.setMsg("1234555");
        Gson gson = new Gson();  
        System.err.println(gson.toJson(u));  
  
    } 
	
	
}

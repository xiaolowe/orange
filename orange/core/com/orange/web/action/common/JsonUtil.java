package com.orange.web.action.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonUtil {
	
	public static void returnJson(HttpServletResponse response, Object obj){
		String json = new Gson().toJson(obj);
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void returnJsonStr(HttpServletResponse response,String json){
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String returnJsonStr(Object obj){
		return  new Gson().toJson(obj);
	}

}

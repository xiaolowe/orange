package com.orange.util;
 
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP请求
 * @author YangFan
 *
 */
public class HTTPSend {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args){
		
	}
	
	/**
	 * json转换
	 * @param str
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String jsonResolve(String str){
		JSONObject json = new JSONObject().fromObject(str);
		return json.getString("retContent");
	}
	
	/**
	 * 发送GET请求
	 * @param url
	 * @return
	 */
	public String sendGet(String url){
    	try{
    		URL httpUrl = new URL(url);
    		System.out.println("HTTP URL:" + url);
    		HttpURLConnection  connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("connection", "keep-alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
            connection.connect();
          //接受连接返回参数
	        InputStream in = connection.getInputStream();     
            BufferedReader reader = new BufferedReader(new InputStreamReader(in)); 
	        String lines;
            StringBuffer sbf = new StringBuffer();
             while ((lines = reader.readLine()) != null) {
//                    lines = new String(lines.getBytes(), "utf-8");
                    sbf.append(lines);
                }
                System.out.println(sbf);
                reader.close();
                // 断开连接
                connection.disconnect();
	        return sbf.toString();
    	}catch(Exception e){
    		logger.info("向服务端发送GET请求失败："+e.toString());
    		return "";
    	}
    }
	
	/**
	 * 发送POST请求
	 * @param url
	 * @return
	 */
	public String sendPost(String url){
    	try{
    		//创建URL
	        URL httpUrl = new URL(url);
	        System.out.println("HTTP URL:" + url);
	        //建立连接
	        HttpURLConnection  connection = (HttpURLConnection ) httpUrl.openConnection();
	        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        connection.setRequestProperty("connection", "close");
	        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
	        connection.setRequestMethod("POST");
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.connect();
	        //接受连接返回参数
	        InputStream in = connection.getInputStream();     
            BufferedReader reader = new BufferedReader(new InputStreamReader(in)); 
	        String lines;
            StringBuffer sbf = new StringBuffer();
             while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sbf.append(lines);
                }
                System.out.println(sbf);
                reader.close();
                // 断开连接
                connection.disconnect();
	        return sbf.toString();
	    }catch(Exception e){
			logger.info("向服务端发送POST请求失败："+e.toString());
			return "";
		}
    }
}
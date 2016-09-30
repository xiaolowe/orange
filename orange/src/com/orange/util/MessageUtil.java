package com.orange.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 发送短信 使用短信通提供的服务接口： 短信通地址：http://dx.pcf.cn/jiekou/
 * 
 * @author Administrator
 * 
 */
public final class MessageUtil {

	
	public static final String SMS_URL = "http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit";
	
	public static final String SMS_ACCOUNT = "dlwswlkj";
	
	public static final String SMS_PASSWORD = "9PQMH1tf";
	
	public static final String SMS_SPRID = "1012818";
	
	
	
	public static void sendSMS(String toMobile, String content) {
		System.out.println("Message content:" + content);
		String postData = null;
		try {
			postData = "sname=" + SMS_ACCOUNT +"&spwd=" + SMS_PASSWORD + "&scorpid=" + "&sprdid=" + SMS_SPRID + "&sdst=" + toMobile + "&smsg="+java.net.URLEncoder.encode(content,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Message PostData:" + postData);
		String result = SMS(postData, SMS_URL);
        //请自己反序列化返回的字符串并实现自己的逻辑
		System.out.println("Message result:" + result);
	}
	
	public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }

}
package com.orange.utils.component.push;

import com.orange.util.HTTPSend;

public class SendPushMessage {
	public static final String PUSH_URL = "http://192.168.0.139:8080/PushService/opush.do";
	
	
	public static String sendSingleMessage(String appKey, String secretKey, String mobile,
			String title, String description, String content){
		String singleUrl = PUSH_URL + "?method=sendSingleNotify&appKey="+appKey + "&secretKey=" + secretKey 
				+ "&mobile=" + mobile + "&title=" + title + "&description=" + description + "&content=" + content;
//		try {
//			singleUrl = URLEncoder.encode(singleUrl, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		HTTPSend send = new HTTPSend();
		String result = send.sendPost(singleUrl);
		return result;
	}
	
	public static String sendTagMessage(String appKey, String secretKey, String tag,
			String title, String description, String content){
		String singleUrl = PUSH_URL + "?method=sendTagNotify&appKey="+appKey + "&secretKey=" + secretKey 
				+ "&tag=" + tag + "&title=" + title + "&description=" + description + "&content=" + content;
//		try {
//			singleUrl = URLEncoder.encode(singleUrl, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		HTTPSend send = new HTTPSend();
		String result = send.sendPost(singleUrl);
		return result;
	}
	
	public static String sendAllMessage(String appKey, String secretKey,
			String title, String description, String content){
		String singleUrl = PUSH_URL + "?method=sendTagNotify&appKey="+appKey + "&secretKey=" + secretKey 
				+ "&title=" + title + "&description=" + description + "&content=" + content;
//		try {
//			singleUrl = URLEncoder.encode(singleUrl, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		HTTPSend send = new HTTPSend();
		String result = send.sendPost(singleUrl);
		return result;
	}

}

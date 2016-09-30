package com.orange.front.utils;
/**
 * Created by bingone on 15/12/16.
 */

    import org.apache.http.HttpEntity;
    import org.apache.http.NameValuePair;
    import org.apache.http.client.entity.UrlEncodedFormEntity;
    import org.apache.http.client.methods.CloseableHttpResponse;
    import org.apache.http.client.methods.HttpPost;
    import org.apache.http.impl.client.CloseableHttpClient;
    import org.apache.http.impl.client.HttpClients;
    import org.apache.http.message.BasicNameValuePair;
    import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

    import java.io.IOException;
import java.io.Serializable;
    import java.net.URISyntaxException;
    import java.net.URLEncoder;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
import java.util.Map;

/**
 * 短信http接口的java代码调用示例
 * 基于Apache HttpClient 4.3
 *
 * @author songchao
 * @since 2015-04-03
 */

public class JavaSmsApi {

    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模版发送接口的http地址
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    
    private static String apiKey = "3207487cfe62bb9f7090b65cbaee06fd";

    public static void main(String[] args) throws IOException, URISyntaxException {

        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取
        //修改为您要发送的手机号
        String mobile = URLEncoder.encode("15056992212",ENCODING);

        /**************** 查账户信息调用示例 *****************/
        System.out.println(JavaSmsApi.getUserInfo(apiKey));

        /**************** 使用智能匹配模版接口发短信(推荐) *****************/
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        String text = "【云片网】您的验证码是1234";
        //发短信调用示例
        System.out.println(JavaSmsApi.sendSms(apiKey, text, mobile));

//        /**************** 使用指定模板接口发短信(不推荐，建议使用智能匹配模版接口) *****************/
//        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
//        long tpl_id = 1;
//        //设置对应的模板变量值
//
//        String tpl_value = URLEncoder.encode("#code#",ENCODING) +"="
//            + URLEncoder.encode("1234", ENCODING) + "&"
//            + URLEncoder.encode("#company#",ENCODING) + "="
//            + URLEncoder.encode("云片网",ENCODING);
//        //模板发送的调用示例
//        System.out.println(tpl_value);
//        System.out.println(JavaSmsApi.tplSendSms(apiKey, tpl_id, tpl_value, mobile));
//
//        /**************** 使用接口发语音验证码 *****************/
//        String code = "1234";
//        //System.out.println(JavaSmsApi.sendVoice(apikey, mobile ,code));
    }
    
    public static Boolean sendMsg(String _mobile, String txt) throws IOException, URISyntaxException {
	    String mobile = URLEncoder.encode(_mobile,ENCODING);
//	    long tpl_id = 1323211;
//	      //设置对应的模板变量值
//	    String tpl_value = URLEncoder.encode("#code#",ENCODING) +"="
//	           + URLEncoder.encode(txt, ENCODING) + "&"
//	           + URLEncoder.encode("#company#",ENCODING) + "="
//	           + URLEncoder.encode("一亩茶山",ENCODING);
//	       //模板发送的调用示例
//	    System.out.println(tpl_value);
//	    String result = JavaSmsApi.tplSendSms(apiKey, tpl_id, tpl_value, mobile);
//	    System.out.println(result);
	    String result = JavaSmsApi.sendSms(apiKey, txt, _mobile);
	    System.out.println(result);
	    Msg msg = new Gson().fromJson(result, Msg.class);
	    Boolean isSend = false;
	    if(msg.getCode().equals("0")){
	    	isSend = true;
	    }
	    return isSend;
    }

    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws java.io.IOException
     */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 智能匹配模版接口发短信
     *
     * @param apikey apikey
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
     * 通过接口发送语音验证码
     * @param apikey apikey
     * @param mobile 接收的手机号
     * @param code   验证码
     * @return
     */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
    
    public class Msg implements Serializable{
    	
    	/**
		 * 
		 */
		private static final long serialVersionUID = 8890228969761143928L;

		private String code;
    	
    	private String msg;
    	
    	private String count;
    	
    	private String fee;
    	
    	private String unit;
    	
    	private String mobile;
    	
    	private String sid;

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

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getFee() {
			return fee;
		}

		public void setFee(String fee) {
			this.fee = fee;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}
    	
    	
    	
    }
}

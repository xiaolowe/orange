package com.orange.pay.utils.wx;

public class WXPayConfigUtil {
	
	public static String API_KEY = "iphiphiphiphiphiphiphiphiphiphip";

	//微信分配的公众号ID（开通公众号之后可以获取到）
	public static String APP_ID = "wx53621d46441928de";
	
	public static String APP_SECRET = "0df3b2ba0af1a673dbd4ad6e029d0ba3";

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static String MCH_ID = "1334022701";

	//受理模式下给子商户分配的子商户号
	public static String SUB_MCH_ID = "";

	//HTTPS证书的本地路径
	public static String certLocalPath = "";

	//HTTPS证书密码，默认密码等于商户号MCHID
	public static String certPassword = "";

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	public static boolean useThreadToDoReport = true;

	//机器IP
	public static String CREATE_IP = "192.168.0.66";
	//回调接口
	public static String NOTIFY_URL = "http://www.iphsport.com/pay/wx/notify";
	//统一下单接口地址
	public static String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	public static String QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	public static String QUERY_URL_TEST = "http://192.168.0.66:8080/orange/pay/wx/notify";
	
	//获取openId
	 public static String oauth_url = "https://api.weixin.qq.com/sns/oauth/access_token?appid="+ APP_ID +"&secret="+APP_SECRET+"&code=CODE&grant_type=authorization_code";


	//以下是几个API的路径：
	//1）被扫支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	//2）被扫支付查询API
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	//3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	//4）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	//5）撤销API
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	//6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	//7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";
	
	//系统支付返回码
	public static String PAY_WX_CODE_SUCCESS = "SUCCESS";
	
	public static String PAY_WX_CODE_FAIL = "FAIL";
	//错误
	public static String PAY_ORDER_STATE_ERROR = "pay_error";
	//完成
	public static String PAY_ORDER_STATE_DONE = "pay_done";
	//不给操作
	public static String PAY_ORDER_STATE_UNDO = "pay_undo";
	//重新下单
	public static String PAY_ORDER_STATE_RENEW = "pay_renew";
	//继续支付
	public static String PAY_ORDER_STATE_GO_ON = "pay_go_on";
	
	

}

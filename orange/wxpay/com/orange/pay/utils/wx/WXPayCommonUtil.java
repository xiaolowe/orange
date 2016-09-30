package com.orange.pay.utils.wx;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.ibm.icu.text.SimpleDateFormat;

public class WXPayCommonUtil {
	/**
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isTenpaySign(String characterEncoding,
			SortedMap<Object, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		sb.append("key=" + API_KEY);

		// 算出摘要
		String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
				.toLowerCase();
		String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();

		// System.out.println(tenpaySign + "    " + mysign);
		return tenpaySign.equals(mysign);
	}

	/**
	 * @author
	 * @date 2016-4-22
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding,
			SortedMap<Object, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
				.toUpperCase();
		return sign;
	}
	
	
	public static String getURLParameter(SortedMap<Object, Object> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
//		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
//				.toUpperCase();
		return sb.toString();
	}

	/**
	 * @author
	 * @date 2016-4-22
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
					|| "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	public static String buildTradeNo(){
		String currTime = WXPayCommonUtil.getCurrTime();
		String strRandom = WXPayCommonUtil.buildRandom(4) + "";
		return currTime + strRandom;
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 获取本机Ip
	 * 
	 * 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。 获得符合
	 * <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
	 * 
	 * @return
	 */
	public static String localIp() {
		String ip = null;
		@SuppressWarnings("rawtypes")
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
						.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface
						.getInterfaceAddresses();
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("获取本机Ip失败:异常信息:" + e.getMessage());
		}
		return ip;
	}

	/**
	 * 获取支付状态判断
	 * @param trade_state
	 * @return
	 */
	public static String getTradeState(String trade_state) {
		// TODO Auto-generated method stub
		String result = WXPayConfigUtil.PAY_ORDER_STATE_ERROR;
		if(trade_state.equals("SUCCESS")){
			//支付完成
			result = WXPayConfigUtil.PAY_ORDER_STATE_DONE;
		}else if(trade_state.equals("FAIL")){
			//支付完成
			result = WXPayConfigUtil.PAY_ORDER_STATE_ERROR;
		}else if(trade_state.equals("REFUND")){
			//转入退款
			result = WXPayConfigUtil.PAY_ORDER_STATE_UNDO;
		}else if(trade_state.equals("NOTPAY")){
			//未支付
			result = WXPayConfigUtil.PAY_ORDER_STATE_GO_ON;
		}else if(trade_state.equals("CLOSED")){
			//已关闭
			result = WXPayConfigUtil.PAY_ORDER_STATE_RENEW;
		}else if(trade_state.equals("REVOKED")){
			//已撤销（刷卡支付）
			result = WXPayConfigUtil.PAY_ORDER_STATE_UNDO;
		}else if(trade_state.equals("USERPAYING")){
			//用户支付中
			result = WXPayConfigUtil.PAY_ORDER_STATE_UNDO;
		}else if(trade_state.equals("PAYERROR")){
			//支付失败(其他原因，如银行返回失败)
			result = WXPayConfigUtil.PAY_ORDER_STATE_RENEW;
		}else if(trade_state.equals("ORDERNOTEXIST")){
			//订单不存在
			result = WXPayConfigUtil.PAY_ORDER_STATE_GO_ON;
		}else{
			result =  WXPayConfigUtil.PAY_ORDER_STATE_ERROR;
		}
		return result;
	}
	
}

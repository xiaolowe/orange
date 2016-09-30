package com.orange.pay.utils.wx;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.orange.race.entity.RaceAddonEntity;
import com.orange.util.StringUtil;
import com.orange.web.action.common.entity.ResultJson;

public class WXPayUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(WXPayUtil.class);

	/**
	 * 统一下单
	 * 
	 * @param pro_id
	 * @param pro_name
	 * @param pro_money
	 * @param pro_descs
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static ResultJson wx_pay_unified_page(String pro_id,
			String pro_name, String pro_money, String pro_descs)
			throws Exception {
		// 账号信息
		String appid = WXPayConfigUtil.APP_ID; // appid
		// String appsecret = PayConfigUtil.APP_SECRET; // appsecret
		String mch_id = WXPayConfigUtil.MCH_ID; // 商业号
		String key = WXPayConfigUtil.API_KEY; // key

		String currTime = WXPayCommonUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = WXPayCommonUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;
		// 支付金额，价格 注意：价格的单位是分
		String order_price = pro_money;
		// 商品名称
		String body = pro_name;
		// 订单号
		String out_trade_no = pro_id;
		// 订单详细
		String detail = pro_descs;

		// 获取发起电脑 ip
		String spbill_create_ip = WXPayCommonUtil.localIp();
		// 回调接口
		String notify_url = WXPayConfigUtil.NOTIFY_URL;
		String trade_type = "NATIVE";

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("detail", detail);
		packageParams.put("attach", detail);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", order_price);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);

		String sign = WXPayCommonUtil.createSign("UTF-8", packageParams, key);
		packageParams.put("sign", sign);

		logger.debug("微信支付统一下单订单信息:单号=" + pro_id + ";商户ID=" + mch_id + ";随机码="
				+ nonce_str + ";订单号=" + out_trade_no + ";订单名称=" + body
				+ ";订单金额=" + order_price + ";订单详细=" + detail + ";下单ip="
				+ spbill_create_ip);
		String requestXML = WXPayCommonUtil.getRequestXml(packageParams);
		logger.debug("unified request:" + requestXML);

		String resXml = HttpUtil.postData(WXPayConfigUtil.UNIFIEDORDER_URL,
				requestXML);
		logger.debug("unified response:" + resXml);
		Map map = XMLUtil.doXMLParse(resXml);
		String return_code = (String) map.get("return_code");
		String return_msg = (String) map.get("return_msg");
		String result_code = (String) map.get("result_code");
		String err_code = (String) map.get("err_code");
		String err_code_des = (String) map.get("err_code_des");
		ResultJson result = new ResultJson();
		if (return_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
			if (result_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
				String prepay_id = (String) map.get("prepay_id");
				String code_url = (String) map.get("code_url");
				logger.debug("微信支付统一下单:单号=" + pro_id + ";返回二维码支付地址:" + code_url
						+ ";返回码：" + return_code + ";处理结果码：" + result_code
						+ ";预支付ID:" + prepay_id);
				result.setCode(WXPayConfigUtil.PAY_WX_CODE_SUCCESS);
				result.setMsg(code_url);
			} else {
				result.setCode(err_code);
				result.setMsg(err_code_des);
				logger.error("微信支付统一下单:单号=" + pro_id + ";错误码=" + err_code
						+ ";错误描述=" + err_code_des);
			}
		} else {
			result.setCode(return_code);
			result.setMsg(return_msg);
			logger.error("微信支付统一下单系统错误:单号=" + pro_id + "返回码:return_code="
					+ return_code + ";返回错误描述:return_msg=" + return_msg);
		}
		return result;
	}

	// public static String getOpenid(String code) throws Exception {
	// String requestUrl =
	// "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
	// + WXPayConfigUtil.APP_ID
	// + "&secret="
	// + WXPayConfigUtil.APP_SECRET
	// + "&code=" + code + "&grant_type=authorization_code";
	// String res = HttpService.doGet(requestUrl);
	// System.out.println(res);
	// Map<String, Object> resultMap = JsonUtil.fromJson(res, HashMap.class);
	// if (resultMap.get("openid") == null) {
	// return null;
	// }
	//
	// return resultMap.get("openid").toString();
	// }

	public static String getOpenid(String appid, String appSecret, String code)
			throws Exception {
		SortedMap<Object, Object> map = new TreeMap<Object, Object>();
		map.put("appid",appid);
		map.put("secret", appSecret);
		map.put("code", "code");
		map.put("grant_type", "authorization_code");
       	String open_id_url = "https://api.weixin.qq.com/sns/oauth2/access_token?"+ WXPayCommonUtil.getURLParameter(map);
       	logger.debug("请求获取open_id请求数据:request=" + open_id_url);
		String res = HttpUtil.postData(open_id_url, "");
		logger.debug("请求获取open_id返回信息:res=" + res);
		Map<String, Object> resultMap = JsonUtil.fromJson(res, HashMap.class);
		if (resultMap.get("openid") == null) {
			return null;
		}

		return resultMap.get("openid").toString();
	}

	/**
	 * 统一下单，返回预支付id
	 * 
	 * @param pro_id
	 * @param pro_name
	 * @param pro_money
	 * @param pro_descs
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static ResultJson wx_pay_unified_h5(String pro_id, String pro_name,
			String pro_money, String pro_descs, String code) throws Exception {

		// 账号信息
		String appid = WXPayConfigUtil.APP_ID; // appid
		// String appsecret = PayConfigUtil.APP_SECRET; // appsecret
		String mch_id = WXPayConfigUtil.MCH_ID; // 商业号
		String key = WXPayConfigUtil.API_KEY; // key

		String currTime = WXPayCommonUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = WXPayCommonUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;
		// 支付金额，价格 注意：价格的单位是分
		String order_price = pro_money;
		// 商品名称
		String body = pro_name;
		// 订单号
		String out_trade_no = pro_id;
		// 订单详细
		String detail = pro_descs;

		// 获取发起电脑 ip
		String spbill_create_ip = WXPayCommonUtil.localIp();
		// 回调接口
		String notify_url = WXPayConfigUtil.NOTIFY_URL;
		String trade_type = "JSAPI";

		String open_id = getOpenid(WXPayConfigUtil.APP_ID, WXPayConfigUtil.APP_SECRET, code);

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("detail", detail);
		packageParams.put("attach", detail);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", order_price);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", open_id);

		String sign = WXPayCommonUtil.createSign("UTF-8", packageParams, key);
		packageParams.put("sign", sign);

		logger.debug("微信支付统一下单订单信息:单号=" + pro_id + ";商户ID=" + mch_id + ";随机码="
				+ nonce_str + ";订单号=" + out_trade_no + ";订单名称=" + body
				+ ";订单金额=" + order_price + ";订单详细=" + detail + ";下单ip="
				+ spbill_create_ip);
		String requestXML = WXPayCommonUtil.getRequestXml(packageParams);
		logger.debug("unified request:" + requestXML);

		String resXml = HttpUtil.postData(WXPayConfigUtil.UNIFIEDORDER_URL,
				requestXML);
		logger.debug("unified response:" + resXml);
		Map map = XMLUtil.doXMLParse(resXml);
		String return_code = (String) map.get("return_code");
		String return_msg = (String) map.get("return_msg");
		String result_code = (String) map.get("result_code");
		String err_code = (String) map.get("err_code");
		String err_code_des = (String) map.get("err_code_des");
		ResultJson result = new ResultJson();
		if (return_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
			if (result_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
				String prepay_id = (String) map.get("prepay_id");
				// String code_url = (String) map.get("code_url");
				logger.debug("微信支付统一下单:单号=" + pro_id + ";返回码：" + return_code
						+ ";处理结果码：" + result_code + ";预支付ID:" + prepay_id);
				result.setCode(WXPayConfigUtil.PAY_WX_CODE_SUCCESS);

				SortedMap<Object, Object> maps = new TreeMap<Object, Object>();
				maps.put("appId", appid);
				Long timeStamp = new Date().getTime() / 1000;
				maps.put("timeStamp", "" + timeStamp);
				maps.put("nonceStr", nonce_str);
				maps.put("signType", "MD5");
				maps.put("package", "prepay_id=" + prepay_id);
				String signs = WXPayCommonUtil.createSign("UTF-8", maps, key);
				maps.put("paySign", signs);
				/**
				 * { "appId" ： "wx2421b1c4370ec43b", //公众号名称，由商户传入
				 * "timeStamp"：" 1395712654", //时间戳，自1970年以来的秒数 "nonceStr" ：
				 * "e61463f8efa94090b1f366cccfbbb444", //随机串 "package" ：
				 * "prepay_id=u802345jgfjsdfgsdg888", "signType" ： "MD5",
				 * //微信签名方式： "paySign" ：
				 * "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名 }
				 */
				String payJson = "{" + "\"appId\":\"" + appid + "\","
						+ "\"timeStamp\":\"" + timeStamp.toString() + "\","
						+ "\"nonceStr\":\"" + nonce_str + "\","
						+ "\"signType\":\"" + "MD5" + "\"," + "\"package\":\""
						+ "prepay_id=" + prepay_id + "\"," + "\"paySign\":\""
						+ signs + "\"" + "}";
				logger.debug("微信支付JSAPI 获取支付json参数:json=" + payJson);
				result.setMsg(payJson);
			} else {
				result.setCode(err_code);
				result.setMsg(err_code_des);
				logger.error("微信支付统一下单:单号=" + pro_id + ";错误码=" + err_code
						+ ";错误描述=" + err_code_des);
			}
		} else {
			result.setCode(return_code);
			result.setMsg(return_msg);
			logger.error("微信支付统一下单系统错误:单号=" + pro_id + "返回码:return_code="
					+ return_code + ";返回错误描述:return_msg=" + return_msg);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static ResultJson wx_pay_query(String pro_id) throws Exception {
		// 账号信息
		String appid = WXPayConfigUtil.APP_ID; // appid
		String mch_id = WXPayConfigUtil.MCH_ID; // 商业号
		String key = WXPayConfigUtil.API_KEY; // key

		String currTime = WXPayCommonUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = WXPayCommonUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;

		String out_trade_no = pro_id; // 订单号

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);

		String sign = WXPayCommonUtil.createSign("UTF-8", packageParams, key);
		packageParams.put("sign", sign);

		String requestXML = WXPayCommonUtil.getRequestXml(packageParams);
		logger.debug("query request:" + requestXML);

		String resXml = HttpUtil
				.postData(WXPayConfigUtil.QUERY_URL, requestXML);
		logger.debug("query response:" + resXml);
		Map map = XMLUtil.doXMLParse(resXml);
		String return_code = (String) map.get("return_code");
		String return_msg = (String) map.get("return_msg");
		String result_code = (String) map.get("result_code");
		String err_code = (String) map.get("err_code");
		String err_code_des = (String) map.get("err_code_des");
		ResultJson result = new ResultJson();
		if (return_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
			if (result_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
				String trade_state = (String) map.get("trade_state");
				String trade_state_desc = (String) map.get("trade_state_desc");
				String mch_id_q = (String) packageParams.get("mch_id");
				String nonce_str_q = (String) packageParams.get("nonce_str");
				String openid_q = (String) packageParams.get("openid");
				String is_subscribe_q = (String) packageParams
						.get("is_subscribe");
				String out_trade_no_q = (String) packageParams
						.get("out_trade_no");
				String transaction_id_q = (String) packageParams
						.get("transaction_id");
				String total_fee_q = (String) packageParams.get("total_fee");
				String time_end_q = (String) packageParams.get("time_end");
				String attach_q = (String) packageParams.get("attach");
				logger.debug("微信支付查询操作成功:单号=" + pro_id + ";交易状态码="
						+ trade_state + ";状态描述=" + trade_state_desc);
				logger.debug("订单信息：" + "商户ID=" + mch_id_q + ";随机码="
						+ nonce_str_q + ";是否关注公众号=" + is_subscribe_q + ";用户标识="
						+ openid_q + ";支付时间=" + time_end_q + ";订单号="
						+ out_trade_no_q + ";订单金额=" + total_fee_q + ";订单详细="
						+ attach_q + ";交易流水号=" + transaction_id_q);
				// 获取交易状态
				result.setCode(trade_state);
				result.setMsg(time_end_q);
			} else {
				logger.error("微信支付查询操作错误:单号=" + pro_id + ";错误码=" + err_code
						+ ";错误描述=" + err_code_des);
				result.setCode(err_code);
				result.setMsg(err_code_des);
			}
		} else {
			// return_code=FAIL
			result.setCode(return_code);
			result.setMsg(return_msg);
			logger.error("微信支付查询操作系统错误:单号=" + pro_id + ";返回码:return_code="
					+ return_code + ";返回描述:return_msg=" + return_msg);
		}
		return result;
	}

	/**
	 * 微信支付通知回调处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static ResultJson wx_pay_notify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultJson result = new ResultJson();
		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		logger.debug("微信支付回调返回报文:" + sb.toString());
		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());
		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);
			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}
		// 账号信息
		String key = WXPayConfigUtil.API_KEY; // key
		String resXml = "";
		// 判断签名是否正确
		if (WXPayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			String return_code = (String) packageParams.get("return_code");
			String result_code = (String) packageParams.get("result_code");
			String error_code = (String) packageParams.get("err_code");
			String err_code_des = (String) packageParams.get("err_code_des");
			if (return_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
				if (result_code.equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
					// 这里是支付成功
					String mch_id = (String) packageParams.get("mch_id");
					String sign = (String) packageParams.get("sign");
					String nonce_str = (String) packageParams.get("nonce_str");
					String openid = (String) packageParams.get("openid");
					String is_subscribe = (String) packageParams
							.get("is_subscribe");
					String out_trade_no = (String) packageParams
							.get("out_trade_no");
					String transaction_id = (String) packageParams
							.get("transaction_id");
					String total_fee = (String) packageParams.get("total_fee");
					String time_end = (String) packageParams.get("time_end");
					String attach = (String) packageParams.get("attach");

					logger.debug("微信支付回调：支付成功;" + "返回码=" + return_code
							+ ";结果码=" + result_code);
					logger.debug("订单信息：" + ";商户ID=" + mch_id + ";随机码="
							+ nonce_str + "是否关注公众号=" + is_subscribe + ";用户标识="
							+ openid + ";支付时间=" + time_end + ";订单号="
							+ out_trade_no + ";订单金额=" + total_fee + ";订单详细="
							+ attach + ";交易流水号=" + transaction_id);
					RaceAddonEntity e = new RaceAddonEntity();
					e.setTradeNo(out_trade_no);
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sdf1 = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					e.setCashTime(sdf.format(sdf1.parse(time_end)));
					e.setStatus(RaceAddonEntity.S_Y);
					logger.debug("微信支付回调：支付成功");
					// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
					resXml = "<xml>"
							+ "<return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg>"
							+ "</xml> ";
					result.setCode(WXPayConfigUtil.PAY_WX_CODE_SUCCESS);
					result.setMsg(new Gson().toJson(e));

				} else {
					logger.info("[result_code=FAIL]支付失败,错误信息：错误码=" + error_code
							+ ";错误信息=" + err_code_des);
					resXml = "<xml>"
							+ "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[报文为空]]></return_msg>"
							+ "</xml> ";
					result.setCode(WXPayConfigUtil.PAY_WX_CODE_FAIL);
					result.setMsg(error_code + "=" + err_code_des);
				}
			} else {
				logger.info("微信支付回调[return_code=FAIL]支付失败,错误信息：错误码="
						+ return_code + ";错误信息=" + err_code_des);
				resXml = "<xml>"
						+ "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>"
						+ "</xml> ";
				result.setCode(WXPayConfigUtil.PAY_WX_CODE_FAIL);
				result.setMsg(error_code + "=" + err_code_des);
			}
		} else {
			logger.error("微信支付回调：签名验证失败");
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[验证失败]]></return_msg>" + "</xml> ";
			result.setCode(WXPayConfigUtil.PAY_WX_CODE_FAIL);
			result.setMsg("验证失败,通知签名验证失败");
		}
		BufferedOutputStream out = new BufferedOutputStream(
				response.getOutputStream());
		out.write(resXml.getBytes());
		out.flush();
		out.close();

		return result;
	}

	/**
	 * 获取微信内部支付订单信息
	 * 
	 * @param pro_id
	 * @param pro_name
	 * @param pro_money
	 * @param pro_descs
	 * @return
	 * @throws Exception
	 */
	// @SuppressWarnings("rawtypes")
	// public static String wx_pay_h5_json(String pro_id, String pro_name,
	// String pro_money, String pro_descs) throws Exception {
	// // 账号信息
	// String appid = WXPayConfigUtil.APP_ID; // appid
	// // String appsecret = PayConfigUtil.APP_SECRET; // appsecret
	// String mch_id = WXPayConfigUtil.MCH_ID; // 商业号
	// String key = WXPayConfigUtil.API_KEY; // key
	//
	// String currTime = WXPayCommonUtil.getCurrTime();
	// String strTime = currTime.substring(8, currTime.length());
	// String strRandom = WXPayCommonUtil.buildRandom(4) + "";
	// String nonce_str = strTime + strRandom;
	// //支付金额，价格 注意：价格的单位是分
	// String order_price = pro_money;
	// //商品名称
	// String body = pro_name;
	// //订单号
	// String out_trade_no = pro_id;
	// //订单详细
	// String detail = pro_descs;
	//
	// SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
	// packageParams.put("appid", appid);
	// packageParams.put("timeStamp", currTime);
	// packageParams.put("nonceStr", nonce_str);
	// packageParams.put("body", body);
	// packageParams.put("detail", detail);
	// packageParams.put("attach", detail);
	// packageParams.put("out_trade_no", out_trade_no);
	// packageParams.put("total_fee", order_price);
	// packageParams.put("spbill_create_ip", spbill_create_ip);
	// packageParams.put("notify_url", notify_url);
	// packageParams.put("trade_type", trade_type);
	//
	// String sign = WXPayCommonUtil.createSign("UTF-8", packageParams, key);
	// packageParams.put("sign", sign);
	//
	// logger.debug("微信支付统一下单订单信息:单号=" + pro_id + ";商户ID=" + mch_id
	// + ";随机码=" + nonce_str
	// + ";订单号=" + out_trade_no
	// + ";订单名称=" + body
	// + ";订单金额=" + order_price
	// + ";订单详细=" + detail
	// + ";下单ip=" + spbill_create_ip
	// );
	//
	// return new Gson().toJson(packageParams);
	// }

}

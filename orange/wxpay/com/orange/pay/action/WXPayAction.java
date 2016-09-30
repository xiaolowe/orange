package com.orange.pay.action;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.orange.front.utils.JavaSmsApi;
import com.orange.pay.utils.wx.QRCodeUtil;
import com.orange.pay.utils.wx.WXPayCommonUtil;
import com.orange.pay.utils.wx.WXPayConfigUtil;
import com.orange.pay.utils.wx.WXPayUtil;
import com.orange.race.entity.RaceAddonEntity;
import com.orange.race.service.RaceAddonService;
import com.orange.util.StringUtil;
import com.orange.web.action.common.entity.ResultJson;
import com.orange.web.action.common.statics.Commons;

@Controller
@RequestMapping("/pay/")
public class WXPayAction {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(WXPayAction.class);
	private static final String wx_pay = "/pay/wx/page";
	private static final String wx_pay_h5 = "/pay/wx/pay";
	private static final String page_wx_pay = Commons.SCOPE_FRONT + "/"
			+ wx_pay;
	private static final String page_wx_pay_h5 = Commons.SCOPE_FRONT + "/"
			+ wx_pay_h5;

	@Autowired
	private RaceAddonService raceAddonService;

	@RequestMapping("page/{type}/{order_id}/{order_money}")
	public String pay_page(@PathVariable String type,
			@PathVariable String order_id, @PathVariable String order_money,
			@RequestParam(required = false) String pro_name,
			@RequestParam(required = false) String pro_num,
			@RequestParam(required = false) String pro_price,
			@RequestParam(required = false) String mobile, ModelMap model)
			throws Exception {
		// 测试
		//order_money = "1";
		String pro_des = "数量：" + pro_num + ";单价：" + pro_price;
		pro_name = new String(URLDecoder.decode(pro_name, "UTF-8").getBytes(
				"ISO8859-1"), "UTF-8");
		pro_name = StringUtil.isNotEmptyString(pro_name) ? pro_name : "报名费用";

		String error = "";
		String addonId = "0";
		String code_url = "";
		ResultJson unified_result = new ResultJson();// WXPayUtil.weixin_pay(pro_id,
														// pro_name, pro_money,
														// pro_descs);
		String page = page_wx_pay;
		if (type.equals("wx")) {
			// 下单参数验证
			if (StringUtil.isNotEmptyString(order_id)
					|| StringUtil.isNotEmptyString(order_money)) {
				RaceAddonEntity e = new RaceAddonEntity();
				e.setTradeNo(order_id);
				e = raceAddonService.selectOne(e);
				// 对比订单状态，防止重复下单
				if (e != null) {
					// 订单存在
					if (e.getStatus().equals(RaceAddonEntity.S_N)) {
						// 订单未支付
						// 查询订单状态
						ResultJson query_result = WXPayUtil
								.wx_pay_query(order_id);
						String trade_state = WXPayCommonUtil
								.getTradeState(query_result.getCode());
						if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_GO_ON)) {
							// 继续支付
							// 统一下单操作
							unified_result = WXPayUtil.wx_pay_unified_page(
									order_id, pro_name, order_money, pro_des);
							if (unified_result.getCode().equals(
									WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
								code_url = unified_result.getMsg();
								addonId = e.getId();
							} else {
								error = unified_result.getMsg();
							}
						} else if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_RENEW)) {
							// 原订无效，使用新订单
							String new_trade_no = WXPayCommonUtil
									.buildTradeNo();
							e.setTradeNo(new_trade_no);
							raceAddonService.update(e);
							unified_result = WXPayUtil.wx_pay_unified_page(
									new_trade_no, pro_name, order_money,
									pro_des);
							if (unified_result.getCode().equals(
									WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
								code_url = unified_result.getMsg();
								addonId = e.getId();
							} else {
								error = unified_result.getMsg();
							}
						} else if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_DONE)) {
							error = "已支付，请确认";
							e.setStatus(RaceAddonEntity.S_Y);
							e.setCashTime(query_result.getMsg());
							raceAddonService.update(e);
						} else {
							error = query_result.getMsg();
						}

					} else {
						error = "已支付，请确认";
					}
				} else {
					error = "无效支付，请确认";
				}
			} else {
				error = "请求参数错误";
			}
			page = page_wx_pay;
		}
		model.addAttribute("code_url", code_url);
		model.addAttribute("error", error);
		model.addAttribute("addonId", addonId);
		model.addAttribute("mobile", mobile);
		model.addAttribute("raceName", pro_name);
		DecimalFormat df = new DecimalFormat("#.##");
		model.addAttribute(
				"pro_money",
				StringUtil.changeStrToDoubleWithDecimalFormate(order_money, df) / 100);
		return page;
	}

	/**
	 * 
	 * @param request
	 * @param state
	 *            报名ID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("state/query")
	@ResponseBody
	public String pay_state(HttpServletRequest request,
			@RequestParam(required = true) String state,
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String raceName,
			HttpServletResponse response) throws Exception {
		String result = "{\"code\":\"error\",\"msg\":\"支付状态查询错误\"}";
		if (StringUtil.isNotEmptyString(state)) {
			RaceAddonEntity e = new RaceAddonEntity();
			e.setId(state);
			e = raceAddonService.selectOne(e);
			if (e != null) {
				if (e.getStatus().equals(RaceAddonEntity.S_Y)) {
					result = "{\"code\":\"ok\",\"msg\":\"0\"}";
				} else {
					ResultJson query_result = WXPayUtil.wx_pay_query(e
							.getTradeNo());
					logger.debug("微信支付页面轮询订单状态:返回码=" + query_result.getCode()
							+ ";返回描述:" + query_result.getMsg());
					String trade_state = WXPayCommonUtil
							.getTradeState(query_result.getCode());
					if (trade_state
							.equals(WXPayConfigUtil.PAY_ORDER_STATE_DONE)) {
						logger.debug("微信支付页面轮询订单状态:已支付");
						// 回调通知未发送
						e.setStatus(RaceAddonEntity.S_Y);
						e.setCashTime(query_result.getMsg());
						raceAddonService.update(e);
						try {
							JavaSmsApi.sendMsg(mobile, "【爱跑汇】您报名的" + raceName
									+ "已成功支付，祝您取得好成绩。");
							logger.info("状态查询,已支付，发送短信提示");
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						} catch (URISyntaxException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						result = "{\"code\":\"ok\",\"msg\":\"0\"}";
					} else {
						result = "{\"code\":\"error\",\"msg\":\"" + e.getId()
								+ "\"}";
					}
					logger.debug("微信支付页面轮询返回页面信息:" + result);
				}
			}
		}
		return result;
	}

	/**
	 * 微信支付回调处理
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "wx/notify")
	@ResponseBody
	public void pay_notify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ResultJson result = WXPayUtil.wx_pay_notify(request, response);
			if (result.getCode().equals(WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
				logger.debug("微信支付回调处理完成：" + result.getMsg());
				RaceAddonEntity e = new Gson().fromJson(result.getMsg(),
						RaceAddonEntity.class);
				raceAddonService.update(e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 支付二维码生成接口
	 * 
	 * @param request
	 * @param type
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("prepare/{type}")
	@ResponseBody
	public void pay_prepare(HttpServletRequest request,
			@PathVariable String type, HttpServletResponse response)
			throws Exception {
		if (type.equals("wx")) {
			String code_url = request.getParameter("code");
			if (StringUtil.isNotEmptyString(code_url)) {
				QRCodeUtil.generateQRCodeImage(response, code_url, 220, 220);
			}
		}
	}

	@RequestMapping("h5/{type}/{order_id}/{order_money}")
	public String pay_page_h5(@PathVariable String type,
			@PathVariable String order_id, @PathVariable String order_money,
			@RequestParam(required = false) String pro_name,
			@RequestParam(required = false) String pro_num,
			@RequestParam(required = false) String pro_price,
			@RequestParam(required = false) String mobile, ModelMap model)
			throws Exception {
		// 测试
		//order_money = "1";
		String pro_des = "数量：" + pro_num + ";单价：" + pro_price;
		pro_name = new String(URLDecoder.decode(pro_name, "UTF-8").getBytes(
				"ISO8859-1"), "UTF-8");
		pro_name = StringUtil.isNotEmptyString(pro_name) ? pro_name : "报名费用";

		String error = "";
		String addonId = "0";
		String code_url = "";
		ResultJson unified_result = new ResultJson();// WXPayUtil.weixin_pay(pro_id,
														// pro_name, pro_money,
														// pro_descs);
		String page = page_wx_pay;
		if (type.equals("wx")) {
			// 下单参数验证
			if (StringUtil.isNotEmptyString(order_id)
					|| StringUtil.isNotEmptyString(order_money)) {
				RaceAddonEntity e = new RaceAddonEntity();
				e.setTradeNo(order_id);
				e = raceAddonService.selectOne(e);
				// 对比订单状态，防止重复下单
				if (e != null) {
					// 订单存在
					if (e.getStatus().equals(RaceAddonEntity.S_N)) {
						// 订单未支付
						// 查询订单状态
						ResultJson query_result = WXPayUtil
								.wx_pay_query(order_id);
						String trade_state = WXPayCommonUtil
								.getTradeState(query_result.getCode());
						if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_GO_ON)) {
							// 继续支付
							// 统一下单操作
							unified_result = WXPayUtil.wx_pay_unified_page(
									order_id, pro_name, order_money, pro_des);
							if (unified_result.getCode().equals(
									WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
								code_url = unified_result.getMsg();
								addonId = e.getId();
							} else {
								error = unified_result.getMsg();
							}
						} else if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_RENEW)) {
							// 原订无效，使用新订单
							String new_trade_no = WXPayCommonUtil
									.buildTradeNo();
							e.setTradeNo(new_trade_no);
							raceAddonService.update(e);
							unified_result = WXPayUtil.wx_pay_unified_page(
									new_trade_no, pro_name, order_money,
									pro_des);
							if (unified_result.getCode().equals(
									WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
								code_url = unified_result.getMsg();
								addonId = e.getId();
							} else {
								error = unified_result.getMsg();
							}
						} else if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_DONE)) {
							error = "已支付，请确认";
							e.setStatus(RaceAddonEntity.S_Y);
							e.setCashTime(query_result.getMsg());
							raceAddonService.update(e);
						} else {
							error = query_result.getMsg();
						}

					} else {
						error = "已支付，请确认";
					}
				} else {
					error = "无效支付，请确认";
				}
			} else {
				error = "请求参数错误";
			}
			page = page_wx_pay_h5;
		}
		model.addAttribute("code_url", code_url);
		model.addAttribute("error", error);
		model.addAttribute("addonId", addonId);
		model.addAttribute("mobile", mobile);
		model.addAttribute("raceName", pro_name);
		DecimalFormat df = new DecimalFormat("#.##");
		model.addAttribute(
				"pro_money",
				StringUtil.changeStrToDoubleWithDecimalFormate(order_money, df) / 100);
		return page;
	}
	
	
	/**
	 * { "appId" ： "wx2421b1c4370ec43b", //公众号名称，由商户传入
	 * "timeStamp"：" 1395712654", //时间戳，自1970年以来的秒数 "nonceStr" ：
	 * "e61463f8efa94090b1f366cccfbbb444", //随机串 "package" ：
	 * "prepay_id=u802345jgfjsdfgsdg888", "signType" ： "MD5", //微信签名方式：
	 * "paySign" ： "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名 }
	 * 
	 * @param type
	 * @param order_id
	 * @param order_money
	 * @param pro_name
	 * @param pro_num
	 * @param pro_price
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/test/h5/{type}/{order_id}/{order_money}")
	public String payWXH5InfoJson(@PathVariable String type,
			@PathVariable String order_id,
			@PathVariable String order_money,
			@RequestParam(required = false) String code,
			@RequestParam(required = false) String pro_name,
			@RequestParam(required = false) String pro_num,
			@RequestParam(required = false) String pro_price, 
			HttpServletRequest request,
			ModelMap model)
			throws Exception {
		String page = page_wx_pay_h5;
		// 测试jine
		order_money = "1";
		String pro_des = "数量：" + pro_num + ";单价：" + pro_price;
		pro_name = new String(URLDecoder.decode(pro_name, "UTF-8").getBytes(
				"ISO8859-1"), "UTF-8");
		pro_name = StringUtil.isNotEmptyString(pro_name) ? pro_name : "报名费用";

		ResultJson prepare_result = new ResultJson();// WXPayUtil.weixin_pay(pro_id,
														// pro_descs);
		String error = "";
		String json = "";
		String addonId = "0";
		if (type.equals("wx")) {
			// 下单参数验证
			if (StringUtil.isNotEmptyString(order_id)
					|| StringUtil.isNotEmptyString(order_money)) {
				RaceAddonEntity e = new RaceAddonEntity();
				e.setTradeNo(order_id);
				e = raceAddonService.selectOne(e);
				// 对比订单状态，防止重复下单
				if (e != null) {
					// 订单存在
					if (e.getStatus().equals(RaceAddonEntity.S_N)) {
						// 订单未支付
						// 查询订单状态
						ResultJson query_result = WXPayUtil
								.wx_pay_query(order_id);
						String trade_state = WXPayCommonUtil
								.getTradeState(query_result.getCode());
						if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_GO_ON)) {
							// 继续支付
							// 统一下单操作,获取prepare_id
							if(StringUtil.isNotEmptyString(code)){
								prepare_result = WXPayUtil.wx_pay_unified_h5(
										order_id, pro_name, order_money, pro_des,
										code);
								if (prepare_result.getCode().equals(
										WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
									json = prepare_result.getMsg();
									addonId = e.getId();
								} else {
									error = prepare_result.getMsg();
								}
								
							}else{
								String url = request.getContextPath() + "/pay/wx/h5/"+order_id+"/"+order_money+".html?pro_name="+pro_name+"&pro_num="
			      					+pro_num+"&pro_price=" + pro_price;
								String r_url = URLEncoder.encode(url);
								SortedMap<Object, Object> map = new TreeMap<Object, Object>();
								 map.put("appid", WXPayConfigUtil.APP_ID);
								 map.put("redirect_uri", r_url);
								 map.put("response_type", "code");
								 map.put("scope", "snsapi_base");
								 map.put("state", "STATE" + "#wechat_redirect");
							     String redirect = "https://open.weixin.qq.com/connect/oauth2/authorize?"+ WXPayCommonUtil.getURLParameter(map);
							     return "redirect:" + redirect;
							}
							
						} else if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_RENEW)) {
							if(StringUtil.isNotEmptyString(code)){
								// 原订无效，使用新订单
								String new_trade_no = WXPayCommonUtil
										.buildTradeNo();
								e.setTradeNo(new_trade_no);
								raceAddonService.update(e);
								prepare_result = WXPayUtil.wx_pay_unified_h5(
										new_trade_no, pro_name, order_money,
										pro_des, code);
								if (prepare_result.getCode().equals(
										WXPayConfigUtil.PAY_WX_CODE_SUCCESS)) {
									json = prepare_result.getMsg();
									addonId = e.getId();
								} else {
									error = prepare_result.getMsg();
								}
							}else{
								String url = request.getContextPath() + "/pay/wx/h5/"+order_id+"/"+order_money+".html?pro_name="+pro_name+"&pro_num="
			      					+pro_num+"&pro_price=" + pro_price;
								String r_url = URLEncoder.encode(url);
								SortedMap<Object, Object> map = new TreeMap<Object, Object>();
								 map.put("appid", WXPayConfigUtil.APP_ID);
								 map.put("redirect_uri", r_url);
								 map.put("response_type", "code");
								 map.put("scope", "snsapi_base");
								 map.put("state", "STATE" + "#wechat_redirect");
							     String redirect = "https://open.weixin.qq.com/connect/oauth2/authorize?"+ WXPayCommonUtil.getURLParameter(map);
							     return "redirect:" + redirect;
							}
						} else if (trade_state
								.equals(WXPayConfigUtil.PAY_ORDER_STATE_DONE)) {
							error = "已支付，请确认";
							e.setStatus(RaceAddonEntity.S_Y);
							e.setCashTime(query_result.getMsg());
							raceAddonService.update(e);
						} else {
							error = query_result.getMsg();
						}

					} else {
						error = "已支付，请确认";
					}
				} else {
					error = "无效支付，请确认";
				}
			} else {
				error = "请求参数错误";
			}

			page = page_wx_pay_h5;
		}
		model.addAttribute("json", json);
		model.addAttribute("error", error);
		model.addAttribute("addonId", addonId);
		DecimalFormat df = new DecimalFormat("#.##");
		model.addAttribute(
				"pro_money",
				StringUtil.changeStrToDoubleWithDecimalFormate(order_money, df) / 100);
		return page;
	}
	
}

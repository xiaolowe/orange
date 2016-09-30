package com.orange.front.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orange.cas.entity.MemberEntity;
import com.orange.cas.service.MemberService;
import com.orange.common.ManageContainer;
import com.orange.core.util.MD5;
import com.orange.front.utils.JavaSmsApi;
import com.orange.pay.utils.wx.WXPayCommonUtil;
import com.orange.race.entity.RaceAddonEntity;
import com.orange.race.entity.RaceAddonManEntity;
import com.orange.race.entity.RaceAddonTeamEntity;
import com.orange.race.entity.RaceInfoEntity;
import com.orange.race.entity.RaceScoreEntity;
import com.orange.race.service.RaceAddonManService;
import com.orange.race.service.RaceAddonService;
import com.orange.race.service.RaceAddonTeamService;
import com.orange.race.service.RaceInfoService;
import com.orange.race.service.RaceScoreService;
import com.orange.util.StringUtil;
import com.orange.web.action.holder.RequestHolder;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/rest/")
public class RestAction {
	private static final Logger logger = LoggerFactory
			.getLogger(RestAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Autowired
	private RaceInfoService raceInfoService;

	@Autowired
	private RaceAddonService raceAddonService;

	@Autowired
	private RaceAddonManService raceAddonManService;

	@Autowired
	private RaceAddonTeamService raceAddonTeamService;

	@Autowired
	private RaceScoreService raceScoreService;

	@Autowired
	private MemberService casMemberService;

	/**
	 * 报名操作
	 * 
	 * @param postData
	 * @param team
	 * @param raceId
	 * @param mId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="r/purchase", method = RequestMethod.POST)
	@ResponseBody
	public String race_purchase(@RequestParam(required = true) String postData,
			@RequestParam(required = true) String team,
			@RequestParam(required = true) String raceId,
			@RequestParam(required = true) String groupId,
			@RequestParam(required = true) String mId,
			@RequestParam(required = true) String money,
			@RequestParam(required = true) String raceNum,
			@RequestParam(required = true) String raceName, ModelMap model,
			HttpSession session) {
		// TODO Auto-generated method stub
		String result = getAddonCode(raceId, raceNum);
		if (result.equals("0000")) {
			RaceAddonEntity addon = new RaceAddonEntity();
			addon.setmId(mId);
			addon.setRaceId(raceId);
			addon.setGroupId(groupId);
			RaceAddonEntity addone = raceAddonService.selectOne(addon);
			if (addone != null && StringUtil.isNotEmptyString(addone.getId())) {
				result = "{\"code\":\"error\",\"msg\":\"您已经报名过该项赛事!\"}";
			} else {

				RaceAddonTeamEntity teame = new RaceAddonTeamEntity();
				if (StringUtils.isNotEmpty(team)) {
					teame = new Gson()
							.fromJson(team, RaceAddonTeamEntity.class);// new
																		// TypeToken<List<RaceAddonTeamEntity>>()
																		// {}.getType());
					int teamId = raceAddonTeamService.insert(teame);
					addon.setTeamId(String.valueOf(teamId));
				}
				List<RaceAddonManEntity> addons = new ArrayList<RaceAddonManEntity>();
				if (StringUtils.isNotEmpty(postData)) {
					addons = new Gson().fromJson(postData,
							new TypeToken<List<RaceAddonManEntity>>() {
							}.getType());
					if (StringUtil.isNotEmptyList(addons)) {
						for (RaceAddonManEntity e : addons) {
							e.setTeamId(teame.getId());
							raceAddonManService.insert(e);
						}
					}
				}
				if (StringUtil.isNotEmptyString(money)
						&& Float.valueOf(money) == 0) {
					addon.setStatus(RaceAddonEntity.S_Y);
					try {
						JavaSmsApi.sendMsg(teame.getMobile(), "【爱跑汇】您报名的"
								+ raceName + "并已成功支付，祝您取得好成绩。");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					addon.setStatus(RaceAddonEntity.S_N);
					try {
						JavaSmsApi.sendMsg(teame.getMobile(), "【爱跑汇】您正在报名"
								+ raceName + "，报名以最终支付成功为准。如果未能及时支付请登录官方网站（www.iphsport.com）完成报名。");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				addon.setTradeNo(WXPayCommonUtil.buildTradeNo());
				raceAddonService.insert(addon);
				result = "{\"code\":\"ok\",\"msg\":\"" + addon.getTradeNo()
						+ "\"}";
			}

		} else {
			result = getAddonResultByCode(result);
		}
		return result;
	}

	/*
	 * 查分
	 */
	@RequestMapping(value = "r/score", method = RequestMethod.POST)
	@ResponseBody
	public String r_score(@RequestParam(required = true) String raceId,
			@RequestParam(required = true) String mobile,
			@RequestParam(required = true) String cardId) throws Exception {

		String result = "{\"code\":\"error\",\"msg\":\"系统错误!\"}";

		RaceScoreEntity score = raceScoreService.selectScoreByMan(raceId,
				mobile, cardId);
		if (score == null) {
			result = "{\"code\":\"error\",\"msg\":\"暂无比赛成绩!\"}";
		} else {
			result = "{\"code\":\"ok\",\"msg\":\"获取比赛成绩\",\"score\":\""
					+ score.getScore() + "\",\"rank\":\"" + score.getRank()
					+ "\"}";
		}
		return result;
	}

	/**
	 * 赛事内页报名检查
	 * 
	 * @param raceId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="r/addon/check", method = RequestMethod.POST)
	@ResponseBody
	public String checkRace(@RequestParam(required = true) String raceId,
			@RequestParam(required = false) String raceNum,
			HttpSession session) {
		return getAddonResultByCode(getAddonCode(raceId, raceNum));
	}

	/**
	 * 更新会员信息
	 * 
	 * @param name
	 * @param password
	 * @param mobile
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "m/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestParam(required = false) String name,
			@RequestParam(required = false) String password,
			@RequestParam(required = false) String mobile, HttpSession session)
			throws Exception {
		MemberEntity m = new MemberEntity();
		m.setMobile(mobile);
		m = casMemberService.selectOne(m);
		if (m == null) {
			return  "{\"code\":\"error\",\"msg\":\"用户不存在!\"}";
		} else {
			if (m.getStatus().equals(MemberEntity.S_NO)) {
				logger.error("帐号已被禁用，请联系管理员,{}", m.getName());
				return  "{\"code\":\"error\",\"msg\":\"帐号已被禁用，请联系管理员!\"}";
			}
		}
		
		if (StringUtil.isNotEmptyString(name)) {
			m.setName(name);
		}
		if (StringUtil.isNotEmptyString(password)) {
			m.setPassword(MD5.md5(password));
		}

		casMemberService.update(m);
		session.setAttribute(ManageContainer.session_member_info, m);
		return "{\"code\":\"ok\",\"msg\":\"密码重置程国平!\"}";
	}

	@RequestMapping(value = "m/logout", method = RequestMethod.POST)
	@ResponseBody
	public String logout() throws Exception {
		HttpSession session = RequestHolder.getSession();
		if (session != null) {
			session.setAttribute(ManageContainer.session_member_info, null);
		}
		return "ok";
	}

	/**
	 * 用户注册验证码发送
	 * 
	 * @param request
	 * @param mobile
	 * @param flushAttrs
	 * @param response
	 * @return
	 */
	@RequestMapping(value="common/sms/code", method = RequestMethod.POST)
	@ResponseBody
	public String sms(HttpServletRequest request, @RequestParam(required=true) String mobile) {
		logger.error("checkCode.." + mobile);
		String result = "0";
		String code = getRandNum(4);
		try {
			if (JavaSmsApi.sendMsg(mobile, "【爱跑汇】您的验证码是" + code)) {
				result = code;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "0";
		}
		return result;
	}

	public String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	public int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	@SuppressWarnings("rawtypes")
	public String getAddonCode(String raceId,@RequestParam(required = false)String raceNum) {
		String result = "1000";// 未知错误
		if (StringUtils.isBlank(raceId)) {
			// 参数
			result = "0001";
		} else {
			HashMap map = (HashMap) raceInfoService.selectFrontRaceById(raceId)
					.getList().get(0);
			if (map == null) {
				result = "0002";
			} else {
				String status = map.get("status").toString();
				String isStop = map.get("isStop").toString();
				String isStart = map.get("isStart").toString();
				String isGoing = map.get("isGoing").toString();
				String isEnd = map.get("isEnd").toString();
				Long num = (Long) map.get("num");
				String isLimit = map.get("isLimit").toString();
//				String limitNum = map.get("limitNum").toString();
				
				if(StringUtil.isNotEmptyString(raceNum)){
					num = num + Integer.parseInt(raceNum);
				}

				if (StringUtils.isNotEmpty(status)
						&& status.equals(RaceInfoEntity.S_NO)) {
					// 禁用赛事
					result = "0003";
				} else if (StringUtils.isNotEmpty(isStop)
						&& isStop.equals(RaceInfoEntity.S_YES)) {
					// 人工停止
					result = "0004";
				} else if (StringUtils.isNotEmpty(isGoing)
						&& isGoing.equals(RaceInfoEntity.S_YES)) {
					// 进行中
				    // 根据组别组别
				    
				    
				    
//					if (StringUtils.isNotBlank(isLimit)
//							&& isLimit.equals(RaceInfoEntity.S_YES)) {
					    // TODO
						// 人数限制
//						if (StringUtils.isNotBlank(limitNum)
//								&& Integer.valueOf(limitNum) != 0
//								&& Integer.valueOf(limitNum) < num) {
//							result = "0005";
//						} else {
//							return "0000";
//						}
//					} else {
						result = "0000";
//					}
				} else if (StringUtils.isNotEmpty(isStart)
						&& isStart.equals(RaceInfoEntity.S_NO)) {
					// 未开始
					result = "0006";
				} else if (StringUtils.isNotEmpty(isEnd)
						&& isEnd.equals(RaceInfoEntity.S_YES)) {
					// 已结束
					result = "0007";
				} else {
					result = "0000";
				}
			}
		}
		return result;
	}

	private String getAddonResultByCode(String code) {
		String result = "{\"code\":\"error\",\"msg\":\"未知错误!\"}";
		if (code.equals("1000")) {
			result = "{\"code\":\"error\",\"msg\":\"未知错误!\"}";
		} else if (code.equals("0000")) {
			result = "{\"code\":\"ok\",\"msg\":\"赛事进行中!\"}";
		} else if (code.equals("0001")) {
			result = "{\"code\":\"error\",\"msg\":\"参数错误!\"}";
		} else if (code.equals("0002")) {
			result = "{\"code\":\"error\",\"msg\":\"参数错误!\"}";
		} else if (code.equals("0003")) {
			result = "{\"code\":\"error\",\"msg\":\"赛事已停止!\"}";
		} else if (code.equals("0004")) {
			result = "{\"code\":\"error\",\"msg\":\"赛事已停止报名!\"}";
		} else if (code.equals("0005")) {
			result = "{\"code\":\"error\",\"msg\":\"赛事报名人数限制!\"}";
		} else if (code.equals("0006")) {
			result = "{\"code\":\"error\",\"msg\":\"赛事未开始!\"}";
		} else if (code.equals("0007")) {
			result = "{\"code\":\"error\",\"msg\":\"赛事已结束!\"}";
		} else {
			result = "{\"code\":\"error\",\"msg\":\"未知错误!\"}";
		}
		return result;
	}

}

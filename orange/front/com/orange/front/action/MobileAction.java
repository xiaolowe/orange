package com.orange.front.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orange.cas.entity.MemberEntity;
import com.orange.cas.service.MemberService;
import com.orange.common.ManageContainer;
import com.orange.core.util.MD5;
import com.orange.race.entity.RaceAddonEntity;
import com.orange.race.entity.RaceAddonManEntity;
import com.orange.race.entity.RaceInfoEntity;
import com.orange.race.service.RaceAddonManService;
import com.orange.race.service.RaceAddonService;
import com.orange.race.service.RaceInfoService;
import com.orange.util.StringUtil;
import com.orange.web.action.common.statics.Commons;
import com.orange.web.action.holder.LoginUserHolder;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/mobile/")
public class MobileAction {
	private static final Logger logger = LoggerFactory
			.getLogger(MobileAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;


	@Autowired
	private RaceInfoService raceInfoService;

	@Autowired
	private RaceAddonService raceAddonService;

	@Autowired
	private RaceAddonManService raceAddonManService;

	@Autowired
	private MemberService casMemberService;

	private static final String m_race_addon_mobile = "/mobile/addon";
	private static final String page_race_addon_mobile = Commons.SCOPE_FRONT
			+ "/" + m_race_addon_mobile;

	private static final String m_race_addon_info_mobile = "/mobile/addon_info";
	private static final String page_race_addon_info_mobile = Commons.SCOPE_FRONT
			+ "/" + m_race_addon_info_mobile;

	private static final String m_cas_login_mobile = "/mobile/login";
	private static final String page_cas_login_mobile = Commons.SCOPE_FRONT
			+ m_cas_login_mobile;

	private static final String m_cas_regist_mobile = "/mobile/regist";
	private static final String page_cas_regist_mobile = Commons.SCOPE_FRONT
			+ m_cas_regist_mobile;
	
	private static final String m_cas_forget_mobile = "/mobile/forget";
	private static final String page_cas_forget_mobile = Commons.SCOPE_FRONT
			+ m_cas_forget_mobile;

	private static final String m_cas_index_mobile = "/mobile/index";
	private static final String page_cas_index_mobile = Commons.SCOPE_FRONT
			+ m_cas_index_mobile;


	/**
	 * 赛事报名页
	 * 
	 * @param rId
	 * @param model
	 * @return
	 */
	@RequestMapping("race/addon/{rId}")
	public String addon(@PathVariable String rId, ModelMap model) {
		// TODO Auto-generated method stub
		String page = page_race_addon_mobile;
		RaceInfoEntity e = new RaceInfoEntity();
		e.setId(rId);
		model.addAttribute("race", raceInfoService.selectFrontRaceById(rId)
				.getList().get(0));
		return page;
	}

	/**
	 * 报名后详细
	 * 
	 * @param rId
	 * @param model
	 * @return
	 */
	@RequestMapping("race/addon/info/{aId}/{teamId}")
	public String addonInfo(@PathVariable String aId,
			@PathVariable String teamId,ModelMap model) {
		// TODO Auto-generated method stub
		String page = page_race_addon_info_mobile;
		RaceAddonEntity e = new RaceAddonEntity();
		e.setId(aId);
		model.addAttribute("race", raceAddonService.selectPageList(e).getList()
				.get(0));
		model.addAttribute("mans",
				raceAddonManService.selectList(new RaceAddonManEntity(teamId)));
		return page;
	}

	/**
	 * 登录页
	 * 
	 * @param session
	 * @param from
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "m/toLogin")
	public String toLogin(HttpSession session,
			@ModelAttribute("e") MemberEntity e,
			@RequestParam(required = false) String from, ModelMap model) {
		if(StringUtil.isNotEmptyString(from)){
			model.addAttribute("from", from);
		}
		String page = page_cas_login_mobile;
		model.addAttribute("e", e);
		if (session.getAttribute(ManageContainer.session_member_info) != null) {
			return "redirect:/mobile/home.html";
		}
		return page;
	}

	/**
	 * 登录页
	 * 
	 * @param session
	 * @param from
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "m/toRegist")
	public String toRegist(HttpSession session,
			@ModelAttribute("e") MemberEntity e, 
			@RequestParam(required = false) String from,
			ModelMap model) {
		if(StringUtil.isNotEmptyString(from)){
			model.addAttribute("from", from);
		}
		model.addAttribute("e", e);
		if (session.getAttribute(ManageContainer.session_member_info) != null) {
			return "redirect:/mobile/home.html";
		}
		return page_cas_regist_mobile;
	}
	
	@RequestMapping(value = "m/toForget")
	public String toForget(HttpSession session,
			@ModelAttribute("e") MemberEntity e,
			@RequestParam(required = false) String from, ModelMap model) {
		if(StringUtil.isNotEmptyString(from)){
			model.addAttribute("from", from);
		}
		String page = page_cas_forget_mobile;
		model.addAttribute("e", e);
		if (session.getAttribute(ManageContainer.session_member_info) != null) {
			return "redirect:/mobile/home.html";
		}
		return page;
	}

	/**
	 * 个人中心
	 * 
	 * @param model
	 * @param from
	 * @return
	 */
	@RequestMapping(value = "m/home")
	public String home(ModelMap model,
			@RequestParam(required = false) String from,
			@ModelAttribute("e") MemberEntity e) {
		model.addAttribute("e", e);
		String page = page_cas_index_mobile;
		if (LoginUserHolder.getLoginMember() == null) {
			return page_cas_login_mobile;
		}
		if (StringUtil.isNotEmptyString(from) && !from.equals("null")
				&& !from.equals("login")) {
			return "redirect:" + from;
		}
		MemberEntity m = LoginUserHolder.getLoginMember();
		model.addAttribute("races",
				raceAddonService.selectPageList(new RaceAddonEntity(m.getId())));
		return page;
	}

	/**
	 * 登录操作
	 * 
	 * @param request
	 * @param e
	 * @param from
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "m/login")
	public String login(HttpServletRequest request,
			@ModelAttribute("e") MemberEntity e,
			@RequestParam(required = false) String from,
			HttpServletResponse response, HttpSession session, ModelMap model) {
		System.out.println("from=" + from);
		if(StringUtil.isNotEmptyString(from)){
			model.addAttribute("from", from);
		}
		if (StringUtils.isBlank(e.getMobile())
				|| StringUtils.isBlank(e.getPassword())) {
			logger.error("帐号和密码不能为空!");
			model.addAttribute("errorMsg", "账户和密码不能为空!");
			e.clear();
			return page_cas_login_mobile;
		} else {
			MemberEntity u = casMemberService.selectOne(e);
			if (u == null) {
				logger.error("该帐号不存在,{}", e.getAccount());
				model.addAttribute("errorMsg", "该账户不存在，请确认");
				e.clear();
				return page_cas_login_mobile;
			} else {
				if (u.getStatus().equals(MemberEntity.S_NO)) {
					logger.error("帐号已被禁用，请联系管理员,{}", u.getAccount());
					model.addAttribute("errorMsg", "帐号已被禁用，请联系管理员");
					e.clear();
					return page_cas_login_mobile;
				} else if (!u.getPassword().equals(MD5.md5(e.getPassword()))) {
					logger.error("登陆失败，账户或密码错误,{}", u.getAccount());
					model.addAttribute("errorMsg", "登陆失败，账户或密码错误");
					e.setPassword(null);
					return page_cas_login_mobile;
				} else {
					session.setAttribute(ManageContainer.session_member_info, u);
					e.setPassword(null);
					return "redirect:/mobile/home.html";
				}
			}
		}
	}

	/**
	 * 注册操作
	 * 
	 * @param request
	 * @param e
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "m/regist")
	public String regist(HttpServletRequest request,
			@ModelAttribute("e") MemberEntity e,
			@RequestParam(required = false) String from,
			HttpServletResponse response, HttpSession session, ModelMap model)
			throws Exception {
		if(StringUtil.isNotEmptyString(from)){
			model.addAttribute("from", from);
		}
		if (StringUtils.isBlank(e.getMobile())
				|| StringUtils.isBlank(e.getPassword())) {
			logger.error("帐号和密码不能为空!");
			model.addAttribute("errorMsg", "帐号和密码不能为空!");
			return page_cas_regist_mobile;
		} else {
			MemberEntity eu = new MemberEntity();
			eu.setMobile(e.getMobile());
			MemberEntity u = casMemberService.selectOne(eu);
			if (u == null) {
				e.setPassword(MD5.md5(e.getPassword()));
				e.setStatus(MemberEntity.S_YES);
				casMemberService.insert(e);
				e.setPassword(null);
				session.setAttribute(ManageContainer.session_member_info, e);
				return "redirect:/mobile/home.html";
			} else {
				if (u.getStatus().equals(MemberEntity.S_NO)) {
					logger.error("帐号已被禁用，请联系管理员,{}", u.getAccount());
					model.addAttribute("errorMsg", "帐号已被禁用，请联系管理员");
					e.setPassword(null);
					return page_cas_login_mobile;
				} else {
					logger.error("帐号已注册，请勿重复注册,{}", u.getAccount());
					model.addAttribute("errorMsg", "帐号已注册，请勿重复注册");
					e.clear();
					return page_cas_login_mobile;
				}
			}
		}
	}

}

package com.orange.site.action;

import javax.servlet.http.HttpServletRequest;

import com.orange.core.base.service.Services;
import com.orange.oscache.FrontCache;
import com.orange.site.entity.SettingEntity;
import com.orange.site.service.SettingService;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/site/setting")
public class SettingAction extends BaseAction<SettingEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(SettingAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private SettingService siteSettingService;
	
	@Autowired
	private FrontCache frontCache;

	private static final String page_toList = null;
	private static final String page_toAdd = null;
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/site/setting/edit";

	public SettingAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<SettingEntity> getService() {
		return siteSettingService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") SettingEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") SettingEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") SettingEntity e, ModelMap model)
			throws Exception {
		e = siteSettingService.selectOne(e);
		model.addAttribute("e", e);
		return page_toEdit;
	}
	
	@RequestMapping("statics")
	@ResponseBody
    public String statics() {
		if(frontCache.getInstance().loadSetting()!=1){
			return "0";
		}
		return "1";// "redirect:back";
	}

	
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") SettingEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") SettingEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")SettingEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			siteSettingService.insert(e);
		} else {// 修改
			siteSettingService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:toEdit";
	}

}

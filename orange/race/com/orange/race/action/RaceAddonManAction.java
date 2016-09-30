package com.orange.race.action;

import javax.servlet.http.HttpServletRequest;

import com.orange.race.entity.RaceAddonManEntity;
import com.orange.race.service.RaceAddonManService;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;
import com.orange.core.base.service.Services;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/race/addon/man/man")
public class RaceAddonManAction extends BaseAction<RaceAddonManEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(RaceAddonManAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private RaceAddonManService raceAddonManService;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/race/addon/man/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/race/addon/man/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/race/addon/man/edit";

	public RaceAddonManAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<RaceAddonManEntity> getService() {
		return raceAddonManService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") RaceAddonManEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") RaceAddonManEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") RaceAddonManEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", raceAddonManService.selectById(e.getId()));
		return page_toEdit;
	}

	
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") RaceAddonManEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") RaceAddonManEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")RaceAddonManEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			raceAddonManService.insert(e);
		} else {// 修改
			raceAddonManService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}

}

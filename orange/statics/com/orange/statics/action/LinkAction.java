package com.orange.statics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.oscache.FrontCache;
import com.orange.statics.entity.LinkEntity;
import com.orange.statics.service.LinkService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/statics/link")
public class LinkAction extends BaseAction<LinkEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(LinkAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private LinkService staticsLinkService;
	
	@Autowired
	private FrontCache frontCache;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/statics/link/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/statics/link/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/statics/link/edit";

	public LinkAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<LinkEntity> getService() {
		return staticsLinkService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") LinkEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") LinkEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") LinkEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", staticsLinkService.selectById(e.getId()));
		return page_toEdit;
	}

	
	
	@RequestMapping("work")
	@ResponseBody
    public String use(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		LinkEntity e = new LinkEntity();
		e.setId(ids);
		e = staticsLinkService.selectOne(e);
		if (e.getStatus().equals(LinkEntity.S_YES)) {
			e.setStatus(LinkEntity.S_NO);
		} else {
			e.setStatus(LinkEntity.S_YES);
		}
		staticsLinkService.update(e);
		return "1";// "redirect:back";
	}
	
	@RequestMapping("statics")
	@ResponseBody
    public String statics() {
		if(frontCache.getInstance().loadLink()!=1){
			return "0";
		}
		return "1";// "redirect:back";
	}
	
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") LinkEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") LinkEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")LinkEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			staticsLinkService.insert(e);
		} else {// 修改
			staticsLinkService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}

}

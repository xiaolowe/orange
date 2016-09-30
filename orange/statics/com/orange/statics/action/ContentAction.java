package com.orange.statics.action;

import javax.servlet.http.HttpServletRequest;

import com.orange.statics.entity.CatelogEntity;
import com.orange.statics.entity.ContentEntity;
import com.orange.statics.service.CatelogService;
import com.orange.statics.service.ContentService;
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

/**
 * 会员管理
 */
@Controller
@RequestMapping("/statics/content")
public class ContentAction extends BaseAction<ContentEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(ContentAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private ContentService staticsContentService;
	
	@Autowired
	private CatelogService staticsCatelogService;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/statics/content/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/statics/content/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/statics/content/edit";

	public ContentAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<ContentEntity> getService() {
		return staticsContentService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") ContentEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") ContentEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("catelogs",
				staticsCatelogService.selectList(new CatelogEntity()));
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") ContentEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("catelogs",
				staticsCatelogService.selectList(new CatelogEntity()));
		model.addAttribute("e", staticsContentService.selectById(e.getId()));
		return page_toEdit;
	}

	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") ContentEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") ContentEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")ContentEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			staticsContentService.insert(e);
		} else {// 修改
			staticsContentService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}

}

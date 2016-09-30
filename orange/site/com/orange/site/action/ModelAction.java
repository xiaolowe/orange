package com.orange.site.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.site.entity.ModelEntity;
import com.orange.site.service.ModelService;
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
@RequestMapping("/site/model")
public class ModelAction extends BaseAction<ModelEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(ModelAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private ModelService siteModelService;
	
	private static final String page_toList = Commons.SCOPE_MANAGE + "/site/model/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/site/model/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/site/model/edit";

	public ModelAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<ModelEntity> getService() {
		return siteModelService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") ModelEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") ModelEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") ModelEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", siteModelService.selectById(e.getId()));
		return page_toEdit;
	}

	
	
	@RequestMapping("work")
	@ResponseBody
    public String use(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		ModelEntity e = new ModelEntity();
		e.setId(ids);
		e = siteModelService.selectOne(e);
		if (e.getStatus().equals(ModelEntity.S_YES)) {
			e.setStatus(ModelEntity.S_NO);
		} else {
			e.setStatus(ModelEntity.S_YES);
		}
		siteModelService.update(e);
		return "1";// "redirect:back";
	}
	
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") ModelEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") ModelEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")ModelEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			siteModelService.insert(e);
		} else {// 修改
			siteModelService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}

}

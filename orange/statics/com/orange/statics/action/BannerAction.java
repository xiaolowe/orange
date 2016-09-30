package com.orange.statics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.oscache.FrontCache;
import com.orange.statics.entity.BannerEntity;
import com.orange.statics.service.BannerService;
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
@RequestMapping("/statics/banner")
public class BannerAction extends BaseAction<BannerEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(BannerAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private BannerService staticsBannerService;
	
	@Autowired
	private FrontCache frontCache;
	
	private static final String page_toList = Commons.SCOPE_MANAGE + "/statics/banner/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/statics/banner/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/statics/banner/edit";

	public BannerAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<BannerEntity> getService() {
		return staticsBannerService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") BannerEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") BannerEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") BannerEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", staticsBannerService.selectById(e.getId()));
		return page_toEdit;
	}

	
	
	@RequestMapping("work")
	@ResponseBody
    public String work(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		BannerEntity e = new BannerEntity();
		e.setId(ids);
		e = staticsBannerService.selectOne(e);
		if (e.getStatus().equals(BannerEntity.S_YES)) {
			e.setStatus(BannerEntity.S_NO);
		} else {
			e.setStatus(BannerEntity.S_YES);
		}
		staticsBannerService.update(e);
		return "1";// "redirect:back";
	}
	
	@RequestMapping("statics")
	@ResponseBody
    public String statics() {
		if(frontCache.getInstance().loadBanner()!=1){
			return "0";
		}
		return "1";// "redirect:back";
	}
	
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") BannerEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") BannerEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")BannerEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			staticsBannerService.insert(e);
		} else {// 修改
			staticsBannerService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}

}

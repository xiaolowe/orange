package com.orange.statics.action;

import javax.servlet.http.HttpServletRequest;

import com.orange.oscache.FrontCache;
import com.orange.statics.entity.CatelogEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/statics/catelog")
public class CatelogAction extends BaseAction<CatelogEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(CatelogAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private CatelogService staticsCatelogService;
	
	@Autowired
	private ContentService staticsContentService;
	
	@Autowired
	private FrontCache frontCache;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/statics/catelog/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/statics/catelog/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/statics/catelog/edit";

	public CatelogAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<CatelogEntity> getService() {
		return staticsCatelogService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") CatelogEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") CatelogEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") CatelogEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", staticsCatelogService.selectById(e.getId()));
		return page_toEdit;
	}

	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") CatelogEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") CatelogEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")CatelogEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			staticsCatelogService.insert(e);
		} else {// 修改
			staticsCatelogService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}
	
	@Override
	@RequestMapping(value = "deletes", method = RequestMethod.POST)
	 public String deletes(HttpServletRequest request, String[] ids, @ModelAttribute("e") CatelogEntity e, RedirectAttributes flushAttrs) throws Exception {
		Boolean result = false;
		for (int i = 0; i < ids.length; i++) {
			if(staticsContentService.selectById(ids[i]) != null){
				result = true;;
				break;
			}
		}
		if(result){
			addMessage(flushAttrs, "删除失败，所选项已被使用！");
		}else{
			staticsCatelogService.deletes(ids);
			addMessage(flushAttrs, "操作成功！");
		}
        return "redirect:selectList";
    }

	@RequestMapping("statics")
	@ResponseBody
    public String statics() {
		if(frontCache.getInstance().loadCatelog()!=1){
			return "0";
		}
		return "1";// "redirect:back";
	}
	
}

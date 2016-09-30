package com.orange.race.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.orange.race.entity.RaceScoreEntity;
import com.orange.race.entity.RaceAddonManEntity;
import com.orange.race.entity.RaceInfoEntity;
import com.orange.race.service.RaceAddonManService;
import com.orange.race.service.RaceInfoService;
import com.orange.race.service.RaceScoreService;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;
import com.orange.cas.entity.MemberEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/race/score")
public class RaceScoreAction extends BaseAction<RaceScoreEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(RaceScoreAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private RaceScoreService raceScoreService;
	

	private static final String page_toList = Commons.SCOPE_MANAGE + "/race/score/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/race/score/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/race/score/edit";

	public RaceScoreAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<RaceScoreEntity> getService() {
		return raceScoreService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") RaceScoreEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") RaceScoreEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}
	
	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") RaceScoreEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", raceScoreService.selectById(e.getId()));
		return page_toEdit;
	}

	
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") RaceScoreEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") RaceScoreEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")RaceScoreEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			raceScoreService.insert(e);
		} else {// 修改
			raceScoreService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}
	
	
	@RequestMapping(value="saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
	public String saveOrUpdate(@RequestParam(required=false) String sId, 
			@RequestParam(required=true) String addonId, 
			@RequestParam(required=true) String isTeam, 
			@RequestParam(required=true) String rank, 
			@RequestParam(required=true)String score) throws Exception{
		String result = "{\"code\":\"error\",\"msg\":\"系统错误!\"}";
		if(StringUtils.isBlank(addonId)|| StringUtils.isBlank(score) || StringUtils.isBlank(rank) || StringUtils.isBlank(isTeam)){
			return "{\"code\":\"error\",\"msg\":\"参数错误!\"}"; 
		}else{
			RaceScoreEntity e = new RaceScoreEntity();
			e.setScore(score);
			e.setAddonId(addonId);
			e.setIsTeam(isTeam);
			e.setRank(rank);
			if(StringUtils.isNotEmpty(sId)){
				e.setId(sId);
				raceScoreService.update(e);
				result = "{\"code\":\"ok\",\"msg\":\""+sId+"\"}";
			}else{
				int id = raceScoreService.insert(e);
				result = "{\"code\":\"ok\",\"msg\":\""+id+"\"}";
			}
		}
		return result;
	}

}

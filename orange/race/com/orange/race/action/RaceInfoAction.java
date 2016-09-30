package com.orange.race.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.orange.core.base.service.Services;
import com.orange.race.entity.RaceAddonEntity;
import com.orange.race.entity.RaceGroupEntity;
import com.orange.race.entity.RaceInfoEntity;
import com.orange.race.service.RaceAddonService;
import com.orange.race.service.RaceGroupService;
import com.orange.race.service.RaceInfoService;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;

@Controller
@RequestMapping("/race/info")
public class RaceInfoAction extends BaseAction<RaceInfoEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(RaceInfoAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private RaceInfoService raceInfoService;
	
	@Autowired
    private RaceGroupService raceGroupService;
	
	@Autowired
	private RaceAddonService raceAddonService;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/race/info/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/race/info/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/race/info/edit";

	public RaceInfoAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<RaceInfoEntity> getService() {
		return raceInfoService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") RaceInfoEntity e) throws Exception {
		return super.selectList(request, e);
	}
	
	@Override
    @RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") RaceInfoEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") RaceInfoEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", raceInfoService.selectById(e.getId()));
		
		setRaceGroupAttr(e, model);
		
		return page_toEdit;
	}

    /** 
     * <一句话功能简述>
     * @param e
     * @param model
     */
    private void setRaceGroupAttr(RaceInfoEntity e, ModelMap model)
    {
        // 赛事组别
		List<RaceGroupEntity> groups = raceGroupService.selectListByRaceId(e.getId());
		
		List<RaceGroupEntity> singleGroups = new ArrayList<RaceGroupEntity>();
		List<RaceGroupEntity> teamGroups = new ArrayList<RaceGroupEntity>();
		
		for (RaceGroupEntity raceGroupEntity : groups)
        {
		    // 个人组别
            if(raceGroupEntity.getType().equals("1")) {
                
                singleGroups.add(raceGroupEntity);
            } else {
                teamGroups.add(raceGroupEntity);
            }
        }
		
		model.addAttribute("singleGroups", singleGroups);
		model.addAttribute("teamGroups", teamGroups);
    }

	
	
	@RequestMapping("work")
	@ResponseBody
    public String use(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		RaceInfoEntity e = new RaceInfoEntity();
		e.setId(ids);
		e = raceInfoService.selectOne(e);
		if (e.getStatus().equals(RaceInfoEntity.S_YES)) {
			e.setStatus(RaceInfoEntity.S_NO);
		} else {
			e.setStatus(RaceInfoEntity.S_YES);
		}
		raceInfoService.update(e);
		return "1";// "redirect:back";
	}
	
	@RequestMapping("insertRace")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") RaceInfoEntity e, RedirectAttributes flushAttrs, ModelMap model)
			throws Exception {
		return save(e, flushAttrs, model);
	}

	@RequestMapping(value = "updateRace", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") RaceInfoEntity e, RedirectAttributes flushAttrs, ModelMap model)
			throws Exception {
		return save(e, flushAttrs, model);
	}

	private String save(@ModelAttribute("e")RaceInfoEntity e, RedirectAttributes flushAttrs, ModelMap model)
			throws Exception {
		if(StringUtils.isBlank(e.getLimitNum())){
			e.setLimitNum(null);
		}
		if(StringUtils.isBlank(e.getMinNum())){
			e.setMinNum(null);
		}
		if(StringUtils.isBlank(e.getMaxNum())){
			e.setMaxNum(null);
		}
		if (StringUtils.isBlank(e.getId())) {// 添加
		    
		    if(e.getSingleGroups().size() + e.getTeamGroups().size() == 0) {
		        
		        e.setId(null);
		        
		        model.addAttribute("singleGroups", new ArrayList<RaceGroupEntity>());
		        model.addAttribute("teamGroups", new ArrayList<RaceGroupEntity>());
		        
		        addError(model, "请至少输入一组赛事组别!");
		        return page_toAdd;
		    } else {
		        raceInfoService.insert(e);
		        
		        // 添加赛事组别信息
		        for (RaceGroupEntity group : e.getSingleGroups())
		        {
		            group.setRaceId(e.getId());
		            group.setType("1");
		            raceGroupService.insert(group);
		        }
		        for (RaceGroupEntity group : e.getTeamGroups())
                {
		            group.setRaceId(e.getId());
		            group.setType("2");
                    raceGroupService.insert(group);
                }
		    }
		    
		    
		} else {// 修改
		    
		    if(e.getSingleGroups().size() + e.getTeamGroups().size() == 0) {
		        
		        setRaceGroupAttr(e, model);
		        addError(model, "请至少输入一组赛事组别!");
                return page_toEdit;
            } else {
             // 更新赛事组别信息
                for (RaceGroupEntity group : e.getSingleGroups())
                {
                    
                    if(StringUtils.isBlank(group.getId())) {
                        group.setRaceId(e.getId());
                        group.setType("1");
                        raceGroupService.insert(group);
                    } else {
                        raceGroupService.update(group);
                    }
                }
                
                for (RaceGroupEntity group : e.getTeamGroups())
                {
                    
                    if(StringUtils.isBlank(group.getId())) {
                        group.setRaceId(e.getId());
                        group.setType("2");
                        raceGroupService.insert(group);
                    } else {
                        raceGroupService.update(group);
                    }
                }
                
                raceInfoService.update(e);
            }
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}
	
	/**
	 * 
	 * <删除赛事组别>
	 * @param groupId
	 * @param raceId
	 * @return
	 */
    @RequestMapping(value = "delRaceGroup", method = RequestMethod.POST)
    @ResponseBody
    public String delRaceGroup(@RequestParam("groupId") String groupId, @RequestParam("raceId") String raceId)
    {
        
        // 判断此赛事组别 是否已经有人报名
        RaceAddonEntity param = new RaceAddonEntity();
        param.setRaceId(raceId);
        param.setGroupId(groupId);
        
        int count = raceAddonService.selectRaceGroupAddonCount(param);
        
        if (count > 0)
        {
            return String.valueOf(count);
        }
        
        raceGroupService.deleteById(groupId);
        
        return "0";
        
    }

}

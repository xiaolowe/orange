package com.orange.news.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.cas.entity.UserEntity;
import com.orange.common.ManageContainer;
import com.orange.core.base.service.Services;
import com.orange.news.entity.RaceEntity;
import com.orange.news.service.RaceService;
import com.orange.util.ReadPageContent;
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

@Controller
@RequestMapping("/news/race")
public class RaceAction extends BaseAction<RaceEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(RaceAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private RaceService newsRaceService;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/news/race/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/news/race/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/news/race/edit";
	private static final String page_toShow = Commons.SCOPE_MANAGE + "/news/race/detail";

	public RaceAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<RaceEntity> getService() {
		return newsRaceService;
	}

	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") RaceEntity e) throws Exception {
		// TODO Auto-generated method stub
		UserEntity u = (UserEntity) request.getSession().getAttribute(
				ManageContainer.session_user_info);
		if (!u.getUsername().equals("admin")) {
			e.setCreateAccount(u.getUsername());
		}
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") RaceEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") RaceEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", newsRaceService.selectById(e.getId()));
		return page_toEdit;
	}

	@RequestMapping("show")
	public String toShow(@ModelAttribute("e") RaceEntity e, ModelMap model)
			throws Exception {
		e = newsRaceService.selectById(e.getId());
		Integer num = Integer.valueOf(e.getNum());
		num = num + 1;
		e.setNum(num);
		model.addAttribute("e", e);
//		if(StringUtils.isBlank(e.getKeyWord())){
//			model.addAttribute("keywords", null);
//		}else{
//			String[] keyword = newsRaceService.selectOne(e).getKeyWord()
//			.split(",");
//			List<String> keywords = new ArrayList<String>();
//			for (String str : keyword) {
//				keywords.add(str);
//			}
//			model.addAttribute("keywords", keywords);
//		}
        
//        String base = RequestHolder.getRequest().getScheme()+"://"
//			        + RequestHolder.getRequest().getServerName() 
//			        +":"+RequestHolder.getRequest().getServerPort()
//			        + "/" + RequestHolder.getRequest().getContextPath();
//        model.addAttribute("base", base);
        e.setPublishAccount("");
		newsRaceService.update(e);
		return page_toShow;
	}
	
	@RequestMapping("stick")
	@ResponseBody
    public String stick(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		RaceEntity e = new RaceEntity();
		e.setId(ids);
		e = newsRaceService.selectOne(e);
		if (e.getStick().equals("1")) {
			e.setStick("0");
		} else {
			e.setStick("1");
		}
		newsRaceService.update(e);
		return "1";// "redirect:back";
	}
	
	@RequestMapping("recommend")
	@ResponseBody
    public String recommend(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		RaceEntity e = new RaceEntity();
		e.setId(ids);
		e = newsRaceService.selectOne(e);
		if (e.getRecommend().equals("1")) {
			e.setRecommend("0");
		} else {
			e.setRecommend("1");
		}
		newsRaceService.update(e);
		return "1";// "redirect:back";
	}

	@RequestMapping("publish")
	@ResponseBody
	public String publish(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		String msg = "发布成功";
		RaceEntity e = new RaceEntity();
		e.setId(ids);
		e = newsRaceService.selectOne(e);
		//e.setPublishAccount(u.getUsername());
		e.setPublishTime("");
		if (e.getState().equals("0")) {
			e.setState("1");
		} else {
			e.setState("0");
		}
//		try {
//			createHTML(e, request, response);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			return "0";
//		}
		newsRaceService.update(e);
		addMessage(flushAttrs, msg);
		return "1";// "redirect:back";
	}

	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") RaceEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		UserEntity u = (UserEntity) request.getSession().getAttribute(
				ManageContainer.session_user_info);
		e.setCreateAccount(u.getUsername());
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") RaceEntity e, RedirectAttributes flushAttrs)
			throws Exception {
//		e = newsRaceService.selectById(e.getId());
		UserEntity u = (UserEntity) request.getSession().getAttribute(
				ManageContainer.session_user_info);
		e.setUpdateAccount(u.getUsername());
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")RaceEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			if (StringUtils.isBlank(e.getRecommend())) {
				e.setRecommend("0");
			}
			if (StringUtils.isBlank(e.getState())) {
				e.setState("0");
			}
			if (StringUtils.isBlank(e.getStick())) {
				e.setStick("0");
			}
			newsRaceService.insert(e);
		} else {// 修改

			newsRaceService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList?type=" + e.getType();
	}

	public Boolean createHTML(final RaceEntity e,
			final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		final StringBuffer basePath = new StringBuffer();
		basePath.append(request.getScheme()).append("://");
		basePath.append(request.getServerName()).append(":");
		basePath.append(request.getServerPort());
		basePath.append(request.getContextPath());
		final String FILEPATH = request.getSession().getServletContext()
				.getRealPath("");
		Boolean result = false;
		File file = new File(FILEPATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			/* 生成静态页面 */
			new ReadPageContent().readPageContent(basePath
					+ "/news/race/show?id=" + e.getId(), FILEPATH
					+ "/html/news/" + e.getId() + ".html");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			result = true;
		}
		return result;
	}

}

package com.orange.news.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.cas.entity.UserEntity;
import com.orange.common.ManageContainer;
import com.orange.core.base.service.Services;
import com.orange.news.entity.ArticleEntity;
import com.orange.news.service.ArticleService;
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

/**
 * 会员管理
 */
@Controller
@RequestMapping("/news/article")
public class ArticleAction extends BaseAction<ArticleEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(ArticleAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private ArticleService newsArticleService;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/news/article/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/news/article/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/news/article/edit";
	private static final String page_toShow = Commons.SCOPE_MANAGE + "/news/article/detail";

	public ArticleAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<ArticleEntity> getService() {
		return newsArticleService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") ArticleEntity e) throws Exception {
		// TODO Auto-generated method stub
		UserEntity u = (UserEntity) request.getSession().getAttribute(
				ManageContainer.session_user_info);
		if (!u.getUsername().equals("admin")) {
			e.setCreateAccount(u.getUsername());
		}
		return super.selectList(request, e);
	}
	
	@RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") ArticleEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}

	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") ArticleEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", newsArticleService.selectById(e.getId()));
		return page_toEdit;
	}

	@RequestMapping("show")
	public String toShow(@ModelAttribute("e") ArticleEntity e, ModelMap model)
			throws Exception {
		e = newsArticleService.selectById(e.getId());
		Integer num = Integer.valueOf(e.getNum());
		num = num + 1;
		e.setNum(num);
		model.addAttribute("e", e);
//		if(StringUtils.isBlank(e.getKeyWord())){
//			model.addAttribute("keywords", null);
//		}else{
//			String[] keyword = newsArticleService.selectOne(e).getKeyWord()
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
		newsArticleService.update(e);
		return page_toShow;
	}
	
	@RequestMapping("stick")
	@ResponseBody
    public String stick(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		ArticleEntity e = new ArticleEntity();
		e.setId(ids);
		e = newsArticleService.selectOne(e);
		if (e.getStick().equals("1")) {
			e.setStick("0");
		} else {
			e.setStick("1");
		}
		newsArticleService.update(e);
		return "1";// "redirect:back";
	}
	
	@RequestMapping("recommend")
	@ResponseBody
    public String recommend(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		ArticleEntity e = new ArticleEntity();
		e.setId(ids);
		e = newsArticleService.selectOne(e);
		if (e.getRecommend().equals("1")) {
			e.setRecommend("0");
		} else {
			e.setRecommend("1");
		}
		newsArticleService.update(e);
		return "1";// "redirect:back";
	}

	@RequestMapping("publish")
	@ResponseBody
	public String publish(HttpServletRequest request, String ids,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		String msg = "发布成功";
		ArticleEntity e = new ArticleEntity();
		e.setId(ids);
		e = newsArticleService.selectOne(e);
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
		newsArticleService.update(e);
		addMessage(flushAttrs, msg);
		return "1";// "redirect:back";
	}

	/**
	 * 添加
	 */
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") ArticleEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		UserEntity u = (UserEntity) request.getSession().getAttribute(
				ManageContainer.session_user_info);
		e.setCreateAccount(u.getUsername());
		return save(e, flushAttrs);
	}

	/**
	 * 修改用户信息
	 */
	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") ArticleEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		UserEntity u = (UserEntity) request.getSession().getAttribute(
				ManageContainer.session_user_info);
		e.setUpdateAccount(u.getUsername());
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")ArticleEntity e, RedirectAttributes flushAttrs)
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
			newsArticleService.insert(e);
		} else {// 修改

			newsArticleService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}

	public Boolean createHTML(final ArticleEntity e,
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
					+ "/news/article/show?id=" + e.getId(), FILEPATH
					+ "/html/news/" + e.getId() + ".html");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			result = true;
		}
		return result;
	}

}

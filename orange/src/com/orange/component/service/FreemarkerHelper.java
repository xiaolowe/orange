package com.orange.component.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.news.entity.ArticleEntity;
import com.orange.news.service.ArticleService;
import com.orange.web.action.common.statics.Commons;
import com.orange.web.action.holder.RequestHolder;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 生成模板的帮助类
 * 
 */
@Component
public class FreemarkerHelper {
	private static final Logger logger = LoggerFactory.getLogger(FreemarkerHelper.class);
//	@Autowired
//	private ArticleService newsArticleService;
//	
//	/**
//	 * 模板
//	 */
//	public static final String template_newsInfo = "newsInfo.ftl";//文章模板
//
//
//	public void setArticleService( ArticleService  newsArticleService) {
//		this.newsArticleService = newsArticleService;
//	}
//
//	/**
//	 * 生成静态页面主方法
//	 * 
//	 * @param context
//	 *            ServletContext
//	 * @param data
//	 *            一个Map的数据结果集
//	 * @param templatePath
//	 *            ftl模版路径
//	 * @param targetHtmlPath
//	 *            生成静态页面的路径
//	 * @throws IOException 
//	 * @throws TemplateException 
//	 */
//	public void crateHTML(ServletContext context, Map<String, Object> data,
//			String templatePath, String targetHtmlPath) throws Exception {
//		if(StringUtils.isBlank(targetHtmlPath)){
//			throw new NullPointerException("targetHtmlPath不能为空！");
//		}
//		
//		Configuration freemarkerCfg = new Configuration();
//		// 加载模版
//		freemarkerCfg.setServletContextForTemplateLoading(context, "/template");
//		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
//		freemarkerCfg.setDefaultEncoding("UTF-8");
//
//		// 指定模版路径
//		Template template = freemarkerCfg
//				.getTemplate(templatePath, "UTF-8");
//		template.setEncoding("UTF-8");
//		
//		System.out.println(targetHtmlPath);
//		File htmlFile = new File(targetHtmlPath);
//		Writer out = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream(htmlFile), "UTF-8"));
//		// 处理模版
//		template.process(data, out);
//		out.flush();
//		out.close();
//		System.out.println("生成成功");
//	}
//
//	public boolean isEmpty(String value) {
//		if (value == null || value.trim().length() == 0) {
//			return true;
//		}
//		return false;
//	}
//	
//	/**
//	 * 对文章数据静态化
//	 * @throws Exception 
//	 */
//	public void news() throws Exception {
//		ArticleEntity param = new ArticleEntity();
//		List<ArticleEntity> articles = newsArticleService.selectList(param);
//		if(articles==null || articles.size()==0){
//			logger.error("articles size = 0");
//			return;
//		}
//		logger.error("articles size = " + articles.size());
//		
//		HashMap<String, Object> data = new HashMap<String, Object>();
//		for(int i=0;i<articles.size();i++){
//			ArticleEntity article = articles.get(i);
//			if(StringUtils.isBlank(article.getContent())){
//				continue;
//			}
//			
//			data.clear();
//			data.put("e", article);
//			String templateHtml = RequestHolder.getSession().getServletContext().getRealPath("/")+"/jsp/helps/"+article.getId()+".jsp";
//			crateHTML(RequestHolder.getSession().getServletContext(), data, template_newsInfo,templateHtml);
//			logger.error("生成html页面成功！id="+article.getId());
//		}
//	}
//	
//	/**
//	 * 静态化指定的文章
//	 * @param id
//	 * @return
//	 * @throws Exception 
//	 */
//	public String staticNews(ArticleEntity news) throws Exception {
//		
//		
//		if(news==null || StringUtils.isBlank(news.getContent())){
//			throw new NullPointerException("ERROR,news == null || news content == null");
//		}
//		
//		HashMap<String, Object> data = new HashMap<String, Object>();
//		data.clear();
//		data.put("e", news);
//		String templateHtml = null;
//		
//		templateHtml = RequestHolder.getSession().getServletContext().getRealPath("/")+"/html/news/"+news.getId()+".html";
//		crateHTML(RequestHolder.getSession().getServletContext(), data, template_newsInfo,templateHtml);
//		logger.error("生成html页面成功！id="+news.getId());
//		
//		return "success";
//	}
}

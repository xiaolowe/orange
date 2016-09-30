package com.orange.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.orange.oscache.FrontCache;
import com.orange.oscache.ManageCache;


/**
 * 系统配置加载监听器
 * 
 */
public class SystemListener implements ServletContextListener {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemListener.class);
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
			FrontCache frontCache = (FrontCache) app.getBean("frontCache");
			frontCache.loadFrontCache();
			ManageCache manageCache = (ManageCache)app.getBean("manageCache");
			manageCache.loadManageCache();
			
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("System load faild!"+e.getMessage());
			try {
				throw new Exception("系统初始化失败！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	

}

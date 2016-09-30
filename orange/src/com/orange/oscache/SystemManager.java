package com.orange.oscache;

import com.orange.core.cache.CacheProvider;
import com.orange.core.cache.SimpleCacheProvider;
import com.orange.core.listener.SystemListener;
import com.orange.site.entity.SettingEntity;
import com.orange.site.model.NavItem;
import com.orange.site.utils.SiteEntity;
import com.orange.statics.entity.BannerEntity;
import com.orange.statics.entity.CatelogEntity;
import com.orange.statics.entity.LinkEntity;
import com.orange.statics.model.Catelog;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * 系统管理类. 1、负责管理system.properties 2、负责缓存管理
 */
public class SystemManager {
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(SystemManager.class);
	private static Properties p = new Properties();
	private static CacheProvider cacheProvider = new SimpleCacheProvider();
	private static SystemManager instance;
	
	public static String cache_setting = "cache_setting";
	public static String cache_links = "cache_links";
	public static String cache_banners = "cache_banners";
	public static String cache_catelogs = "cache_catelogs";
	public static String cache_navs = "cache_navs";
	
	
	@PostConstruct
	public void afterPropertiesSet() {
		instance = this;
	}

	public static SystemManager getInstance() {
		return instance;
	}

	static {
		init();
	}

	private static void init() {
		try {
			p.load(SystemListener.class
					.getResourceAsStream("/system.properties"));
			logger.info(p.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getProperty(String key) {
		return p.getProperty(key);
	}

	public CacheProvider getCacheProvider() {
		return cacheProvider;
	}

	public void setCacheProvider(CacheProvider cacheProvider) {
		SystemManager.cacheProvider = cacheProvider;
	}

	private static String buildKey(String key) {
		return "SystemManager." + StringUtils.trimToEmpty(key);
	}

	private static void putCacheObject(String key, Serializable cacheObject) {
		String key1 = buildKey(key);
		if (cacheObject == null) {
			cacheProvider.remove(key1);
		} else {
			cacheProvider.put(key1, cacheObject);
		}
	}

	@SuppressWarnings("unchecked")
	private static <E> E getCacheObject(String key) {
		return (E) cacheProvider.get(buildKey(key));
	}
	
	private static void removeCacheObject(String key) {
		if (cacheProvider.get(key) != null) {
			cacheProvider.remove(key);
		}
	}

	
	///
	public SettingEntity getSiteSetting() {
		return getCacheObject(cache_setting);
	}

	public void setSiteSetting(SettingEntity siteSetting) {
		//removeCacheObject(cache_setting);
		putCacheObject(cache_setting, siteSetting);
	}

	public List<LinkEntity> getLinks() {
		return getCacheObject(cache_links);
	}

	public void setLinks(List<LinkEntity> links) {
		//removeCacheObject(cache_links);
		putCacheObject(cache_links, (Serializable)links);
	}

	public SiteEntity getSite() {
		return getCacheObject("site");
	}

	public void setSite(SiteEntity site) {
		//removeCacheObject("site");
		putCacheObject("site", site);
	}

	public void setBanners(List<BannerEntity> banners) {
		// TODO Auto-generated method stub
		//removeCacheObject(cache_banners);
		putCacheObject(cache_banners, (Serializable)banners);
	}
	
	public List<BannerEntity> getBanners() {
		return getCacheObject(cache_banners);
	}

	public void setCatelogs(List<Catelog> catelogs) {
		// TODO Auto-generated method stub
		//removeCacheObject(cache_catelogs);
		putCacheObject(cache_catelogs, (Serializable)catelogs);
	}
	
	public List<Catelog> getCatelogs() {
		return getCacheObject(cache_catelogs);
	}
	
	public void setNavs(List<NavItem> navs) {
		// TODO Auto-generated method stub
		//removeCacheObject(cache_navs);
		putCacheObject(cache_navs, (Serializable)navs);
	}
	
	public List<NavItem> getNavs() {
		return getCacheObject(cache_navs);
	}
	
}

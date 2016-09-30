package com.orange.oscache;

import com.orange.site.entity.SettingEntity;
import com.orange.site.model.NavItem;
import com.orange.site.service.NavService;
import com.orange.site.service.SettingService;
import com.orange.statics.entity.BannerEntity;
import com.orange.statics.entity.CatelogEntity;
import com.orange.statics.entity.ContentEntity;
import com.orange.statics.entity.LinkEntity;
import com.orange.statics.model.Catelog;
import com.orange.statics.service.BannerService;
import com.orange.statics.service.CatelogService;
import com.orange.statics.service.ContentService;
import com.orange.statics.service.LinkService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * 缓存管理器。可以通过接口程序通知该类重新加载部分或全部的缓存
 * 
 */
public class FrontCache {
	private static final Logger logger = LoggerFactory.getLogger(FrontCache.class);
	
	private static FrontCache instance;
	
    @Autowired
    private SettingService siteSettingService;
    
    @Autowired
    private LinkService staticsLinkService;
    
    @Autowired
    private BannerService staticsBannerService;
    
    @Autowired
    private CatelogService staticsCatelogService;
    
    @Autowired
    private ContentService staticsContentService;
    
    @Autowired
    private NavService siteNavService;
    
    private static SystemManager systemManager;
    
    @PostConstruct
	public void afterPropertiesSet() {
		instance = this;
	}

	public FrontCache getInstance() {
		return instance;
	}
    
    
    @Autowired
    public void setSystemManager(SystemManager systemManager) {
        FrontCache.systemManager = systemManager;
    }
    

	/**
	 * 加载全部的缓存数据
	 * @throws Exception 
	 */
	public int loadFrontCache(){
		logger.info("FrontCache.loadFrontCache...");
		loadSetting();
		loadLink();
		loadBanner();
		loadCatelog();
		loadNav();
		logger.info("后台缓存加载完毕!");
		return 1;
	}
	
	
//	public void loadSites(){
//		siteSettingService.selectOne(new SettingEntity());
//		SiteEntity se = new SiteEntity();
//		SettingEntity setting = siteSettingService.selectOne(new SettingEntity());
//		List<LinkEntity> links = staticsLinkService.selectList(new LinkEntity(LinkEntity.S_YES));
//		se.setSetting(setting);
//		se.setLinks(links);
//		systemManager.setSite(se);
//	}
	
	public int loadSetting(){
		SettingEntity setting = siteSettingService.selectOne(new SettingEntity());
		systemManager.setSiteSetting(setting);
		return 1;
	}
	
	public int loadLink(){
		List<LinkEntity> links = staticsLinkService.selectList(new LinkEntity(LinkEntity.S_YES));
		systemManager.setLinks(links);
		return 1;
	}
	
	
	public int loadBanner(){
		List<BannerEntity> links = staticsBannerService.selectList(new BannerEntity(BannerEntity.S_YES));
		systemManager.setBanners(links);
		return 1;
	}
	
	public int loadCatelog(){
		List<CatelogEntity> cateloges = staticsCatelogService.selectList(new CatelogEntity(CatelogEntity.S_YES));
		List<Catelog> catelogs = new ArrayList<Catelog>();
		for(CatelogEntity cateloge : cateloges){
			Catelog catelog = new Catelog();
			catelog.setCateloge(cateloge);
			List<ContentEntity> contents = staticsContentService.selectList(new ContentEntity(cateloge.getId()));
			catelog.setContents(contents);
			catelogs.add(catelog);
		}
		systemManager.setCatelogs(catelogs);
		return 1;
	}
	
	public int loadNav(){
		List<NavItem> navs = siteNavService.loadNavs("0");
		systemManager.setNavs(navs);
		return 1;
	}
}
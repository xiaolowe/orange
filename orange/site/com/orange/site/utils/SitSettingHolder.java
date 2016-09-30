package com.orange.site.utils;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.orange.common.ManageContainer;
import com.orange.site.entity.SettingEntity;
import com.orange.statics.entity.LinkEntity;
import com.orange.web.action.holder.RequestHolder;

public class SitSettingHolder {
	
    @SuppressWarnings("unchecked")
	public static SiteEntity getSitSetting(){
        HttpSession session = RequestHolder.getSession();
        if(session != null){
        	SiteEntity site = new SiteEntity();
        	site.setSetting((SettingEntity)session.getAttribute(ManageContainer.session_setting_info));
        	site.setLinks((List<LinkEntity>)session.getAttribute(ManageContainer.session_link_info));
        	return site;
        }
        return null;
    }
    
}

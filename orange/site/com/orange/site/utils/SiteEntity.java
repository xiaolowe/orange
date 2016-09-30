package com.orange.site.utils;

import java.io.Serializable;
import java.util.List;

import com.orange.site.entity.SettingEntity;
import com.orange.statics.entity.LinkEntity;


public class SiteEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8175896356052457134L;
	
	
	private SettingEntity setting;
	
	private List<LinkEntity> links;

	public SettingEntity getSetting() {
		return setting;
	}

	public void setSetting(SettingEntity setting) {
		this.setting = setting;
	}

	public List<LinkEntity> getLinks() {
		return links;
	}

	public void setLinks(List<LinkEntity> links) {
		this.links = links;
	}
	

	

}

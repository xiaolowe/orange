package com.orange.site.entity;

import com.orange.core.base.dao.page.QueryModel;

/**
 * 资源
 */
public class NavEntity extends QueryModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6711438884188058364L;
	private String pid;
	private String url;
	private String name;
	private String model;
	private int orders;
	private String type;// module：模块 ; page：页面 ; button：功能
	private String banner;

	private String isSelf;

	
	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void clear() {
		super.clear();
		pid = null;
		url = null;
		name = null;
		orders = 0;
		type = null;
		model = null;
		banner = null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	public String getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(String isSelf) {
		this.isSelf = isSelf;
	}

	@Override
	public String toString() {
		return "[id:" + getId() + ",pid:" + pid + "]";
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	

}

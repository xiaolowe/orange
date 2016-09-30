package com.orange.cas.entity;

import com.orange.core.base.dao.page.QueryModel;

/**
 * 资源
 */
public class MenuEntity extends QueryModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6711438884188058364L;
	private String pid;
	private String url;
	private String name;
	private int orderNum;
	private String type;// module：模块 ; page：页面 ; button：功能

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
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
		orderNum = 0;
		type = null;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "[id:" + getId() + ",pid:" + pid + "]";
	}

}

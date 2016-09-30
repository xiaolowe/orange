package com.orange.statics.entity;

import com.orange.core.base.dao.page.QueryModel;


public class BannerEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	private String name;
	private String link;
	private String img;
	private String status;
	private int orders;
	
	/**
	 * 是否显示在首页的导航条上
	 */
	public static final String S_YES = "1";
	public static final String S_NO = "0";
	
	
	public BannerEntity() {
		super();
	}
	
	public BannerEntity(String status) {
		super();
		this.status = status;
	}

	public BannerEntity(String id, String name) {
		super();
		this.name = name;
	}

	public void clear() {
		super.clear();
		name = null;
		link = null;
		img = null;
		status = null;
		orders = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}
	

}

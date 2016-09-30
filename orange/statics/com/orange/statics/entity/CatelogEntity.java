package com.orange.statics.entity;

import com.orange.core.base.dao.page.QueryModel;


public class CatelogEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private String link;
	private String banner;
	private String status;
	private String orders;
	
	public static String S_YES = "1";
	public static String S_NO = "0";
	
	
	public CatelogEntity() {
		super();
	}
	
	public CatelogEntity(String status) {
		super();
		this.status = status;
	}
	public CatelogEntity(String id, String status) {
		super();
		this.status = status;
	}

	public void clear() {
		super.clear();
		name = null;
		code = null;
		link = null;
		banner = null;
		status = null;
		orders = null;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	
	

}

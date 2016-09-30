package com.orange.site.entity;

import com.orange.core.base.dao.page.QueryModel;


public class ModelEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private String status;
	private int orders;
	
	/**
	 * 是否显示在首页的导航条上
	 */
	public static final String S_YES = "1";
	public static final String S_NO = "0";
	
	
	public ModelEntity() {
		super();
	}
	
	public ModelEntity(String status) {
		super();
		this.status = status;
	}

	public ModelEntity(String id, String name) {
		super();
		this.name = name;
	}

	public void clear() {
		super.clear();
		name = null;
		code = null;
		status = null;
		orders = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	

}

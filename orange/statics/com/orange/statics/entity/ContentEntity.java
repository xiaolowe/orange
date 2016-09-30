package com.orange.statics.entity;

import com.orange.core.base.dao.page.QueryModel;

public class ContentEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	
	private String name;				//名称
	private String code;				//编码
	private String catelogId;			//所属分类ID
	private String content;				//文章内容
	private String createTime;			//创建时间
	private String updateTime;			//更新时间
	private String link;				//静态化地址
	private String orders;
	
	public ContentEntity(){
		super();
	}
	
	public ContentEntity(String catelogId){
		super();
		this.catelogId = catelogId;
	}
	
	@Override
	public void clear() {
		super.clear();
		name = null;
		code = null;
		catelogId = null;
		content = null;
		createTime = null;
		updateTime = null;
		link = null;
		orders = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCatelogId() {
		return catelogId;
	}

	public void setCatelogId(String catelogId) {
		this.catelogId = catelogId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

}

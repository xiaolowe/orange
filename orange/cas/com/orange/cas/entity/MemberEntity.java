package com.orange.cas.entity;import com.orange.core.base.dao.page.QueryModel;public class MemberEntity extends QueryModel{	private static final long serialVersionUID = 1L;	private String account;//用户昵称	private String name;//用户名	private String password;//密码	private String mobile;//手机号码	private String status;//n:禁用;y:启用	private String createTime;//注册时间	private String updateTime;//更新时间			public static final String TYPE_MOBILE = "0";	public static final String TYPE_QQ = "1";	public static final String TYPE_WX = "2";	public static final String TYPE_EMAIL = "3";		public static final String S_YES = "1";	public static final String S_NO = "0";		public static final String G_F = "0";	public static final String G_M = "1";		public void clear() {		super.clear();		account = null;		name = null;		password = null;		mobile = null;		status = null;		createTime = null;		updateTime = null;	}	public String getAccount() {		return account;	}	public void setAccount(String account) {		this.account = account;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getMobile() {		return mobile;	}	public void setMobile(String mobile) {		this.mobile = mobile;	}	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getUpdateTime() {		return updateTime;	}	public void setUpdateTime(String updateTime) {		this.updateTime = updateTime;	}	}
package com.orange.site.entity;

import com.orange.core.base.dao.page.QueryModel;


public class SettingEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	private String name;		//网站名称
	private String www;			//网站地址
	private String title;		//标题
	private String descs;		//描述
	private String keywords;	//关键字
	private String fav;			//favIcon
	private String isOpen;		//是否打开网站
	private String closeMsg;	//关闭网站的原因
	private String slogan;		//口号
	private String logo;		//网站logo
	private String tel;			//联系电话
	private String cpr;			//版权
	private String icp;			//备案
	private String address;		//公司地址
	private String email;		//email
	private String qqHelp;		//qq帮助代码
	private String totalHelp;	//站长统计代码
	private String createTime;	//创建时间
	
	/**
	 * 是否显示在首页的导航条上
	 */
	public static final String S_YES = "1";
	public static final String S_NO = "0";
	
	
	public SettingEntity() {
		super();
	}

	public SettingEntity(String id, String name) {
		super();
		this.name = name;
	}

	public void clear() {
		super.clear();
		name = null;
		www = null;
		logo = null;
		title = null;
		descs = null;
		keywords = null;
		fav = null;
		tel = null;
		cpr = null;
		address = null;
		email = null;
		icp = null;
		isOpen = null;
		closeMsg = null;
		qqHelp = null;
		totalHelp = null;
		slogan = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getFav() {
		return fav;
	}

	public void setFav(String fav) {
		this.fav = fav;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getCloseMsg() {
		return closeMsg;
	}

	public void setCloseMsg(String closeMsg) {
		this.closeMsg = closeMsg;
	}

	public String getQqHelp() {
		return qqHelp;
	}

	public void setQqHelp(String qqHelp) {
		this.qqHelp = qqHelp;
	}

	public String getTotalHelp() {
		return totalHelp;
	}

	public void setTotalHelp(String totalHelp) {
		this.totalHelp = totalHelp;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}

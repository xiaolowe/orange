package com.orange.race.entity;

import java.util.ArrayList;
import java.util.List;

import com.orange.core.base.dao.page.QueryModel;


public class RaceInfoEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	//基本信息  7
	private String name;		//赛事名称
	private String time;		//赛事举办时间
	private String address;		//举办地点
	private String slt;			//首页缩略图
	private String banner;		//详情页banner图
	private String ads;			//宣传广告图,滚动
	private String descs;		//描述
	private String isStop;		//是否停止报名
	//报名信息 7
	private String startTime;	//报名开始时间
	private String endTime;		//报名截止时间
	private String isMan;		//报名对象，成人
	private String isChild;		//报名对象：儿童
	private String price;		//报名价格
	private String isLimit;		//是否限制报名人数
	private String limitNum;	//限制人数
	private String isTeam;		//是否团体报名
	private String minNum;  //多人报名最少人数
	private String maxNum;	//多人报名最多人数
	
	private String detail;		//赛事详情
	
	private String introduce;	//赛事说明
	
	private String frees;		//费用说明
	
	private String tips;		//友情提示
	
	private String contact;		//联系我们
	
	private String createTime;	//创建时间
	private String updateTime;	//更新时间
	private String status;		//赛事发布状态：0：未发布，1：发布
	private int orders;
	
	private String type;		//查询数据类型,主要是进行判断赛事是否已经开赛，如果未开赛，对应最新赛事，如果已开赛，对应赛事案例
	
	private List<RaceGroupEntity> singleGroups = new ArrayList<RaceGroupEntity>();
	
	private List<RaceGroupEntity> teamGroups = new ArrayList<RaceGroupEntity>();
	
	/**
	 * 是否显示在首页的导航条上
	 */
	public static final String S_YES = "1";
	public static final String S_NO = "0";
	
	public static final String T_UNFINISHED = "0";
	public static final String T_FINISHED = "1";
	
	
	public RaceInfoEntity() {
		super();
	}
	
	public RaceInfoEntity(String status) {
		super();
		this.status = status;
	}
	
	public RaceInfoEntity(String status, String offset, String pageSize) {
		super();
		this.status = status;
		offset = offset;
		pageSize = pageSize;
	}

	public RaceInfoEntity(String id, String name) {
		super();
		this.name = name;
	}

	@Override
    public void clear() {
		super.clear();
		name = null;
		time = null;
		address = null;
		slt = null;
		banner = null;
		ads = null;
		descs = null;
		
		startTime = null;
		endTime = null;
		isMan = null;
		price = null;
		isChild = null;
		isLimit = null;
		limitNum = null;
		isTeam = null;
		minNum = null;
		maxNum = null;
		
		detail = null;
		introduce = null;
		frees = null;
		tips = null;
		contact = null;
		
		createTime = null;
		updateTime = null;
		status = null;
		orders = 0;
		
		isStop = null;
		type = null;
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSlt() {
		return slt;
	}

	public void setSlt(String slt) {
		this.slt = slt;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getAds() {
		return ads;
	}

	public void setAds(String ads) {
		this.ads = ads;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsMan() {
		return isMan;
	}

	public void setIsMan(String isMan) {
		this.isMan = isMan;
	}

	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(String isLimit) {
		this.isLimit = isLimit;
	}

	public String getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(String limitNum) {
		this.limitNum = limitNum;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getFrees() {
		return frees;
	}

	public void setFrees(String frees) {
		this.frees = frees;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(String isTeam) {
		this.isTeam = isTeam;
	}

	public String getMinNum() {
		return minNum;
	}

	public void setMinNum(String minNum) {
		this.minNum = minNum;
	}

	public String getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    /**
     * 获取 singleGroups
     * @return 返回 singleGroups
     */
    public List<RaceGroupEntity> getSingleGroups()
    {
        return singleGroups;
    }

    /**
     * 设置 singleGroups
     * @param 对singleGroups进行赋值
     */
    public void setSingleGroups(List<RaceGroupEntity> singleGroups)
    {
        this.singleGroups = singleGroups;
    }

    /**
     * 获取 teamGroups
     * @return 返回 teamGroups
     */
    public List<RaceGroupEntity> getTeamGroups()
    {
        return teamGroups;
    }

    /**
     * 设置 teamGroups
     * @param 对teamGroups进行赋值
     */
    public void setTeamGroups(List<RaceGroupEntity> teamGroups)
    {
        this.teamGroups = teamGroups;
    }


	
	
	
}

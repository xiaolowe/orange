package com.orange.news.entity;

import com.orange.core.base.dao.page.QueryModel;

public class ArticleEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	
	private String title;				//文章标题
	private String content;				//文章内容
	private String state;				//文章状态[0:未发布，1：已发布，-1：删除]
	private String author;				//作者
	private String createTime;			//创建时间
	private String updateTime;			//更新时间
	private String publishTime;			//发布时间
	private String createAccount;		//创建人员
	private String updateAccount;		//更新人员
	private String publishAccount;		//发布人
	private String keyWord;				//关键字
	private String digest;				//文章摘要
	private String recommend;			//是否推荐
	private String bannerImg;			//详情页banner图
	private String sltImg;				//文章缩略图
	private String stick;				//是否固顶
	private String orders;  			//排序
	private int num;					//浏览数量
	private int znum;					//点赞数量
	private int favnum;					//收藏数量
	private int cnum;					//评论数量
	private String statics;				//静态化地址
	private String type;				//新闻类型
	
	public static final String TYPE_NEWS = "0";
	public static final String TYPE_RACE = "1";
	
	
	public static final String POSITION_COMMON = "0";
	public static final String POSITION_BANNER = "1";
	public static final String POSITION_HOME = "2";
	
	public static final String S_E = "0";
	
	public static final String S_P = "1";
	
	
	public ArticleEntity(){
		super();
	}
	
	public ArticleEntity(String state){
		super();
		this.state = state;
	}
	
	@Override
	public void clear() {
		super.clear();

		title = null;
		content = null;
		state = null;
		author = null;
		createTime = null;
		updateTime = null;
		publishTime = null;
		publishAccount = null;
		keyWord = null;
		digest = null;
		recommend = null;
		bannerImg = null;
		sltImg= null;
		stick = null;
		orders = null;
		num = 0;
		author = null;
		znum = 0;
		favnum = 0;
		cnum = 0;
		createAccount = null;
		updateAccount = null;
		type = null;	
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublishAccount() {
		return publishAccount;
	}

	public void setPublishAccount(String publishAccount) {
		this.publishAccount = publishAccount;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public String getSltImg() {
		return sltImg;
	}

	public void setSltImg(String sltImg) {
		this.sltImg = sltImg;
	}

	public String getStick() {
		return stick;
	}

	public void setStick(String stick) {
		this.stick = stick;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getZnum() {
		return znum;
	}

	public void setZnum(int znum) {
		this.znum = znum;
	}

	public int getFavnum() {
		return favnum;
	}

	public void setFavnum(int favnum) {
		this.favnum = favnum;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public String getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public String getStatics() {
		return statics;
	}

	public void setStatics(String statics) {
		this.statics = statics;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	

}

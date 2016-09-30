package com.orange.race.entity;

import com.orange.core.base.dao.page.QueryModel;


public class RaceAddonEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	
	private String mId;
	
	private String raceId;
	
	private String  teamId;
	
	private String status;
	
	private String createTime;
	
	private String cashTime;
	
	private String tradeNo;
	
	private String groupId;
	
	public static String S_T="2";//超时取消
	public static String S_Y="1";//已支付
	public static String S_N="0";//未支付
	
	public RaceAddonEntity() {
		super();
	}
	
	public RaceAddonEntity(String mId) {
		super();
		this.mId = mId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getRaceId() {
		return raceId;
	}

	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCashTime() {
		return cashTime;
	}

	public void setCashTime(String cashTime) {
		this.cashTime = cashTime;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

    /**
     * 获取 groupId
     * @return 返回 groupId
     */
    public String getGroupId()
    {
        return groupId;
    }

    /**
     * 设置 groupId
     * @param 对groupId进行赋值
     */
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }
	
	
	
}

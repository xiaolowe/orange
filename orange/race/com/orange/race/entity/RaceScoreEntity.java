package com.orange.race.entity;

import com.orange.core.base.dao.page.QueryModel;

public class RaceScoreEntity extends QueryModel {
	
	private static final long serialVersionUID = 6711438884188058364L;
	
	private String rank;
	private String score;
	private String addonId;
	private String isTeam;
	private String createTime;
	private String updateTime;
	
	
	
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getAddonId() {
		return addonId;
	}
	public void setAddonId(String addonId) {
		this.addonId = addonId;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getIsTeam() {
		return isTeam;
	}
	public void setIsTeam(String isTeam) {
		this.isTeam = isTeam;
	}
	
	

}

package com.orange.race.entity;

import com.orange.core.base.dao.page.QueryModel;


public class RaceAddonManEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String mobile;
	
	private String cardId;
	
	private String gender;
	
	private String isChild;
	
	private String teamId;
	
	private static String S_YES = "1";
	private static String S_NO = "0";
	
	public RaceAddonManEntity() {
		super();
	}

	public RaceAddonManEntity(String teamId) {
		super();
		this.teamId = teamId;
	}
	
	public void clear() {
		name = null;
		mobile = null;
		cardId = null;
		gender = null;
		isChild = null;
		teamId = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	
}

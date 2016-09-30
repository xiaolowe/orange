package com.orange.race.entity;

import com.orange.core.base.dao.page.QueryModel;


public class RaceAddonTeamEntity extends QueryModel{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String contact;
	
	private String mobile;
	
	private String descs;
	
	private String isTeam;
	
	private static String S_Y = "1";
	private static String S_N = "0";
	
	public RaceAddonTeamEntity() {
		super();
	}
	
	public RaceAddonTeamEntity(String id) {
		super();
		setId(id);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(String isTeam) {
		this.isTeam = isTeam;
	}
	
}

package com.orange.statics.model;

import java.util.List;

import com.orange.core.base.dao.page.QueryModel;
import com.orange.statics.entity.CatelogEntity;
import com.orange.statics.entity.ContentEntity;


public class Catelog extends QueryModel{
	private static final long serialVersionUID = 1L;
	
	private CatelogEntity cateloge;
	
	private List<ContentEntity> contents;


	public CatelogEntity getCateloge() {
		return cateloge;
	}

	public void setCateloge(CatelogEntity cateloge) {
		this.cateloge = cateloge;
	}

	public List<ContentEntity> getContents() {
		return contents;
	}

	public void setContents(List<ContentEntity> contents) {
		this.contents = contents;
	}
	
}

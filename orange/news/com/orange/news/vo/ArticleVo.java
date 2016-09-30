package com.orange.news.vo;

import java.util.List;

import com.orange.news.entity.ArticleEntity;

public class ArticleVo extends ArticleEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1227043156841654959L;
	
	private String label;
	
	private List<String> labels;
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	
	

}

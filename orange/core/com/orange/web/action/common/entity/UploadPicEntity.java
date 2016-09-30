package com.orange.web.action.common.entity;

import java.io.Serializable;

public class UploadPicEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5336310996232996030L;

	private String realPath;
	
	private String uploadPath;

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
}

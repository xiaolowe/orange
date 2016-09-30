package com.orange.cas.entity;

import com.orange.core.base.dao.page.QueryModel;

/**
 * 权限
 *
 */
public class PrivilegeEntity extends QueryModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8739533726176677014L;
	private String rid;
	private String mid;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
		this.mid = null;
		this.rid = null;
	}

}

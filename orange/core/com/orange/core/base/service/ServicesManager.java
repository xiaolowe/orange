package com.orange.core.base.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

public abstract class ServicesManager<E extends PagerModel, DAO extends DaoManager<E>>
		implements Services<E> {

	protected DAO dao;

	public DAO getDao() {
		return dao;
	}

	public abstract void setDao(DAO dao);

	public int insert(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return dao.insert(e);
	}

	public int delete(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return dao.delete(e);
	}
	
	public int deleteById(String id){
		return dao.deleteById(id);
	}

	public int deletes(String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new NullPointerException("id不能全为空！");
		}

		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isBlank(ids[i])) {
				throw new NullPointerException("id不能为空！");
			}
			deleteById(ids[i]);
		}
		return 0;
	}

	public int update(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return dao.update(e);
	}
	
	public E selectOne(E e) {
		return dao.selectOne(e);
	}
	
	public E selectById(String id) {
		return dao.selectById(id);
	}
	
	public List<E> selectList(E e) {
		return dao.selectList(e);
	}

	public PagerModel selectPageList(E e) {
		return dao.selectPageList(e);
	}
	
	public List<E> selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList(arg1);
	}
	
}

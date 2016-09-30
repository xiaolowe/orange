package com.orange.statics.dao;

import java.util.List;
import java.util.Map;

import com.orange.statics.entity.CatelogEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("staticsCatelogDao")
public class CatelogDao implements DaoManager<CatelogEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(CatelogEntity e) {
		return dao.insert("statics.catelog.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("statics.catelog.deleteById", id);
	}
	
	public int delete(CatelogEntity e) {
		return dao.delete("statics.catelog.delete", e);
	}
	
	public int deletes(String[] ids) {
		CatelogEntity e = new CatelogEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(CatelogEntity e) {
		return dao.update("statics.catelog.update", e);
	}

	public CatelogEntity selectById(String id) {
		return (CatelogEntity) dao.selectOne("statics.catelog.selectById",id);
	}
	
	public CatelogEntity selectOne(CatelogEntity e) {
		return (CatelogEntity) dao.selectOne("statics.catelog.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(CatelogEntity e) {
		return dao.selectList("statics.catelog.selectList", e);
	}
	
	public PagerModel selectPageList(CatelogEntity e) {
		return dao.selectPageList("statics.catelog.selectPageList",
				"statics.catelog.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("statics.catelog.selectList", arg1);
	}


}

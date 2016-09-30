package com.orange.cas.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.orange.cas.entity.LogEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

@Repository("casLogDao")
public class LogDao implements DaoManager<LogEntity>{
	@Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(LogEntity e) {
		return dao.insert("cas.log.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("cas.log.deleteById", id);
	}
	
	public int delete(LogEntity e) {
		return dao.delete("cas.log.delete", e);
	}
	
	public int deletes(String[] ids) {
		LogEntity e = new LogEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(LogEntity e) {
		return dao.update("cas.log.update", e);
	}

	public LogEntity selectById(String id) {
		return (LogEntity) dao.selectOne("cas.log.selectById",id);
	}
	
	public LogEntity selectOne(LogEntity e) {
		return (LogEntity) dao.selectOne("cas.log.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(LogEntity e) {
		return dao.selectList("cas.log.selectList", e);
	}
	
	public PagerModel selectPageList(LogEntity e) {
		return dao.selectPageList("cas.log.selectPageList",
				"cas.log.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("cas.log.selectList", arg1);
	}

	public LogEntity selectFirstOne(String arg0) {
		// TODO Auto-generated method stub
		return (LogEntity) dao.selectOne("cas.log.selectFirstOne", arg0);
	}
}

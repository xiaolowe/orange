package com.orange.news.dao;

import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.news.entity.RaceEntity;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("newsRaceDao")
public class RaceDao implements DaoManager<RaceEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(RaceEntity e) {
		return dao.insert("news.race.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("news.race.deleteById", id);
	}
	
	public int delete(RaceEntity e) {
		return dao.delete("news.race.delete", e);
	}
	
	public int deletes(String[] ids) {
		RaceEntity e = new RaceEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(RaceEntity e) {
		return dao.update("news.race.update", e);
	}

	public RaceEntity selectById(String id) {
		return (RaceEntity) dao.selectOne("news.race.selectById",id);
	}
	
	public RaceEntity selectOne(RaceEntity e) {
		return (RaceEntity) dao.selectOne("news.race.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(RaceEntity e) {
		return dao.selectList("news.race.selectList", e);
	}
	
	public PagerModel selectPageList(RaceEntity e) {
		return dao.selectPageList("news.race.selectPageList",
				"news.race.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("news.race.selectList", arg1);
	}
	
	public RaceEntity selectPreBy(RaceEntity race) {
		return (RaceEntity) dao.selectOne("news.race.selectPreBy",race);
	}

	public RaceEntity selectNextBy(RaceEntity race) {
		return (RaceEntity) dao.selectOne("news.race.selectNextBy",race);
	}
}

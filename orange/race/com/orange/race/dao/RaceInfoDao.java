package com.orange.race.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.race.entity.RaceInfoEntity;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("raceInfoDao")
public class RaceInfoDao implements DaoManager<RaceInfoEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(RaceInfoEntity e) {
		return dao.insert("race.info.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("race.info.deleteById", id);
	}
	
	public int delete(RaceInfoEntity e) {
		return dao.delete("race.info.delete", e);
	}
	
	public int deletes(String[] ids) {
		RaceInfoEntity e = new RaceInfoEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(RaceInfoEntity e) {
		return dao.update("race.info.update", e);
	}

	public RaceInfoEntity selectById(String id) {
		return (RaceInfoEntity) dao.selectOne("race.info.selectById",id);
	}
	
	public RaceInfoEntity selectOne(RaceInfoEntity e) {
		return (RaceInfoEntity) dao.selectOne("race.info.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(RaceInfoEntity e) {
		return dao.selectList("race.info.selectList", e);
	}
	
	public PagerModel selectPageList(RaceInfoEntity e) {
		return dao.selectPageList("race.info.selectPageList",
				"race.info.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("race.info.selectList", arg1);
	}
	
	
	public PagerModel selectFrontRaceById(String id) {
		// TODO Auto-generated method stub
		RaceInfoEntity e = new RaceInfoEntity();
		e.setId(id);
		return dao.selectPageList("race.info.front.selectPageList",
				"race.info.front.selectPageCount", e);
	}

	@SuppressWarnings("unchecked")
	public List<RaceInfoEntity> seleListTop2(RaceInfoEntity e) {
		// TODO Auto-generated method stub
		return dao.selectList("race.info.top3.selectList", e);
	}


}

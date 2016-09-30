package com.orange.race.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.race.entity.RaceScoreEntity;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("raceScoreDao")
public class RaceScoreDao implements DaoManager<RaceScoreEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(RaceScoreEntity e) {
		return dao.insert("race.score.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("race.score.deleteById", id);
	}
	
	public int delete(RaceScoreEntity e) {
		return dao.delete("race.score.delete", e);
	}
	
	public int deletes(String[] ids) {
		RaceScoreEntity e = new RaceScoreEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(RaceScoreEntity e) {
		return dao.update("race.score.update", e);
	}

	public RaceScoreEntity selectById(String id) {
		return (RaceScoreEntity) dao.selectOne("race.score.selectById",id);
	}
	
	public RaceScoreEntity selectOne(RaceScoreEntity e) {
		return (RaceScoreEntity) dao.selectOne("race.score.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(RaceScoreEntity e) {
		return dao.selectList("race.score.selectList", e);
	}
	
	public PagerModel selectPageList(RaceScoreEntity e) {
		return dao.selectPageList("race.score.selectPageList",
				"race.score.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("race.score.selectList", arg1);
	}
	
	public int updateCash(RaceScoreEntity e) {
		return dao.update("race.score.update.cash", e);
	}
	
	public RaceScoreEntity selectScoreByMan(String raceId, String mobile, String cardId) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("raceId", raceId);
		map.put("mobile", mobile);
		map.put("cardId", cardId);
		return (RaceScoreEntity) dao.selectOne("race.score.selectScore", map);
	}


}

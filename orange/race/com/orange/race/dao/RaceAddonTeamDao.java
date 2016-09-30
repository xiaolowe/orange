package com.orange.race.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.race.entity.RaceAddonTeamEntity;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("raceAddonTeamDao")
public class RaceAddonTeamDao implements DaoManager<RaceAddonTeamEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(RaceAddonTeamEntity e) {
		return dao.insert("race.team.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("race.team.deleteById", id);
	}
	
	public int delete(RaceAddonTeamEntity e) {
		return dao.delete("race.team.delete", e);
	}
	
	public int deletes(String[] ids) {
		RaceAddonTeamEntity e = new RaceAddonTeamEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(RaceAddonTeamEntity e) {
		return dao.update("race.team.update", e);
	}

	public RaceAddonTeamEntity selectById(String id) {
		return (RaceAddonTeamEntity) dao.selectOne("race.team.selectById",id);
	}
	
	public RaceAddonTeamEntity selectOne(RaceAddonTeamEntity e) {
		return (RaceAddonTeamEntity) dao.selectOne("race.team.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(RaceAddonTeamEntity e) {
		return dao.selectList("race.team.selectList", e);
	}
	
	public PagerModel selectPageList(RaceAddonTeamEntity e) {
		return dao.selectPageList("race.team.selectPageList",
				"race.team.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("race.team.selectList", arg1);
	}

	@SuppressWarnings({"rawtypes" })
	public PagerModel selectListByRaceId(String raceId) {
		// TODO Auto-generated method stub
		RaceAddonTeamEntity e = new RaceAddonTeamEntity();
		e.setId(raceId);
		return dao.selectPageList("race.team.selectListByRaceId", "race.team.selectPageCount", e);
	}

}

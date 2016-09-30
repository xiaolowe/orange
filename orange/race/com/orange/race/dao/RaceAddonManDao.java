package com.orange.race.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.race.entity.RaceAddonManEntity;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("raceAddonManDao")
public class RaceAddonManDao implements DaoManager<RaceAddonManEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(RaceAddonManEntity e) {
		return dao.insert("race.addon.man.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("race.addon.man.deleteById", id);
	}
	
	public int delete(RaceAddonManEntity e) {
		return dao.delete("race.addon.man.delete", e);
	}
	
	public int deletes(String[] ids) {
		RaceAddonManEntity e = new RaceAddonManEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(RaceAddonManEntity e) {
		return dao.update("race.addon.man.update", e);
	}

	public RaceAddonManEntity selectById(String id) {
		return (RaceAddonManEntity) dao.selectOne("race.addon.man.selectById",id);
	}
	
	public RaceAddonManEntity selectOne(RaceAddonManEntity e) {
		return (RaceAddonManEntity) dao.selectOne("race.addon.man.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(RaceAddonManEntity e) {
		return dao.selectList("race.addon.man.selectList", e);
	}
	
	public PagerModel selectPageList(RaceAddonManEntity e) {
		return dao.selectPageList("race.addon.man.selectPageList",
				"race.addon.man.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("race.addon.man.selectList", arg1);
	}
	
	@SuppressWarnings({"rawtypes" })
	public PagerModel selectListByRaceId(String raceId) {
		// TODO Auto-generated method stub
		RaceAddonManEntity e = new RaceAddonManEntity();
		e.setId(raceId);
		return dao.selectPageList("race.addon.man.selectListByRaceId", "race.addon.man.selectPageCount", e);
	}


}

package com.orange.race.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.race.entity.RaceAddonEntity;

@Repository("raceAddonDao")
public class RaceAddonDao implements DaoManager<RaceAddonEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(RaceAddonEntity e) {
		return dao.insert("race.addon.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("race.addon.deleteById", id);
	}
	
	public int delete(RaceAddonEntity e) {
		return dao.delete("race.addon.delete", e);
	}
	
	public int deletes(String[] ids) {
		RaceAddonEntity e = new RaceAddonEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(RaceAddonEntity e) {
		return dao.update("race.addon.update", e);
	}

	public RaceAddonEntity selectById(String id) {
		return (RaceAddonEntity) dao.selectOne("race.addon.selectById",id);
	}
	
	public RaceAddonEntity selectOne(RaceAddonEntity e) {
		return (RaceAddonEntity) dao.selectOne("race.addon.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(RaceAddonEntity e) {
		return dao.selectList("race.addon.selectList", e);
	}
	
	public PagerModel selectPageList(RaceAddonEntity e) {
		return dao.selectPageList("race.addon.selectPageList",
				"race.addon.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("race.addon.selectList", arg1);
	}
	
	public int updateCash(RaceAddonEntity e) {
		return dao.update("race.addon.update.cash", e);
	}

	public int cancelAddon(RaceAddonEntity e) {
		// TODO Auto-generated method stub
		return dao.update("race.addon.update.cancel", e);
	}

    @SuppressWarnings("rawtypes")
    public List selectAddonList(RaceAddonEntity e) {
        return dao.selectList("race.addon.selectAddonList", e);
    }
    
    /**
     * 
     * <查询赛事组别已经报名的数量>
     * @param e
     * @return
     */
    public int selectRaceGroupAddonCount(RaceAddonEntity e) {
        
        return dao.getCount("selectRaceGroupAddonCount", e);
    }

}

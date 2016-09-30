package com.orange.site.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.site.entity.NavEntity;

@Repository("siteNavDao")
public class NavDao implements DaoManager<NavEntity>{

	@Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(NavEntity e) {
		return dao.insert("site.nav.insert", e);
	}

	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("site.nav.deleteById", id);
	}

	public int delete(NavEntity e) {
		return dao.delete("site.nav.delete", e);
	}

	public int deletes(String[] ids) {
		NavEntity e = new NavEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}

	public int update(NavEntity e) {
		return dao.update("site.nav.update", e);
	}

	public NavEntity selectById(String id) {
		return (NavEntity) dao.selectOne("site.nav.selectById", id);
	}

	public NavEntity selectOne(NavEntity e) {
		return (NavEntity) dao.selectOne("site.nav.selectOne", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(NavEntity e) {
		return dao.selectList("site.nav.selectList", e);
	}
	
	public PagerModel selectPageList(NavEntity e) {
		return null;
	}

	public int getCount(NavEntity e) {
		// TODO Auto-generated method stub
		return dao.getCount("site.nav.getCount", e);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public List loadMenus(Map<String, String> arg1){
		return dao.selectList("site.nav.selectMenus", arg1);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1){
		return dao.selectList("site.nav.selectList", arg1);
	}
}

package com.orange.cas.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.orange.cas.entity.MenuEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

@Repository("casMenuDao")
public class MenuDao implements DaoManager<MenuEntity>{

	@Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(MenuEntity e) {
		return dao.insert("cas.menu.insert", e);
	}

	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("cas.menu.deleteById", id);
	}

	public int delete(MenuEntity e) {
		return dao.delete("cas.menu.delete", e);
	}

	public int deletes(String[] ids) {
		MenuEntity e = new MenuEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}

	public int update(MenuEntity e) {
		return dao.update("cas.menu.update", e);
	}

	public MenuEntity selectById(String id) {
		return (MenuEntity) dao.selectOne("cas.menu.selectById", id);
	}

	public MenuEntity selectOne(MenuEntity e) {
		return (MenuEntity) dao.selectOne("cas.menu.selectOne", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(MenuEntity e) {
		return dao.selectList("cas.menu.selectList", e);
	}
	
	public PagerModel selectPageList(MenuEntity e) {
		return null;
	}

	public int getCount(MenuEntity e) {
		// TODO Auto-generated method stub
		return dao.getCount("cas.menu.getCount", e);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public List loadMenus(Map<String, String> arg1){
		return dao.selectList("cas.menu.selectMenus", arg1);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectList(Map<String, String> arg1){
		return dao.selectList("cas.menu.selectList", arg1);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectBtnList(Map<String, String> arg1){
		return dao.selectList("cas.menu.selectBtnMenus", arg1);
	}
}

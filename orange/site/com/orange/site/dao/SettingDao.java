package com.orange.site.dao;

import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.site.entity.SettingEntity;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("siteSettingDao")
public class SettingDao implements DaoManager<SettingEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(SettingEntity e) {
		return dao.insert("site.setting.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("site.setting.deleteById", id);
	}
	
	public int delete(SettingEntity e) {
		return dao.delete("site.setting.delete", e);
	}
	
	public int deletes(String[] ids) {
		SettingEntity e = new SettingEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(SettingEntity e) {
		return dao.update("site.setting.update", e);
	}

	public SettingEntity selectById(String id) {
		return (SettingEntity) dao.selectOne("site.setting.selectById",id);
	}
	
	public SettingEntity selectOne(SettingEntity e) {
		return (SettingEntity) dao.selectOne("site.setting.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(SettingEntity e) {
		return dao.selectList("site.setting.selectList", e);
	}
	
	public PagerModel selectPageList(SettingEntity e) {
		return dao.selectPageList("site.setting.selectPageList",
				"site.setting.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("site.setting.selectList", arg1);
	}


}

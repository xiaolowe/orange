package com.orange.site.dao;

import java.util.List;
import java.util.Map;

import com.orange.site.entity.ModelEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("siteModelDao")
public class ModelDao implements DaoManager<ModelEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(ModelEntity e) {
		return dao.insert("site.model.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("site.model.deleteById", id);
	}
	
	public int delete(ModelEntity e) {
		return dao.delete("site.model.delete", e);
	}
	
	public int deletes(String[] ids) {
		ModelEntity e = new ModelEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(ModelEntity e) {
		return dao.update("site.model.update", e);
	}

	public ModelEntity selectById(String id) {
		return (ModelEntity) dao.selectOne("site.model.selectById",id);
	}
	
	public ModelEntity selectOne(ModelEntity e) {
		return (ModelEntity) dao.selectOne("site.model.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(ModelEntity e) {
		return dao.selectList("site.model.selectList", e);
	}
	
	public PagerModel selectPageList(ModelEntity e) {
		return dao.selectPageList("site.model.selectPageList",
				"site.model.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("site.model.selectList", arg1);
	}


}

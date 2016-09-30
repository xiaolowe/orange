package com.orange.statics.dao;

import java.util.List;
import java.util.Map;

import com.orange.statics.entity.ContentEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("staticsContentDao")
public class ContentDao implements DaoManager<ContentEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(ContentEntity e) {
		return dao.insert("statics.content.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("statics.content.deleteById", id);
	}
	
	public int delete(ContentEntity e) {
		return dao.delete("statics.content.delete", e);
	}
	
	public int deletes(String[] ids) {
		ContentEntity e = new ContentEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(ContentEntity e) {
		return dao.update("statics.content.update", e);
	}

	public ContentEntity selectById(String id) {
		return (ContentEntity) dao.selectOne("statics.content.selectById",id);
	}
	
	public ContentEntity selectOne(ContentEntity e) {
		return (ContentEntity) dao.selectOne("statics.content.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(ContentEntity e) {
		return dao.selectList("statics.content.selectList", e);
	}
	
	public PagerModel selectPageList(ContentEntity e) {
		return dao.selectPageList("statics.content.selectPageList",
				"statics.content.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("statics.content.selectList", arg1);
	}


}

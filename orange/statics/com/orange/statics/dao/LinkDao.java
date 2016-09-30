package com.orange.statics.dao;

import java.util.List;
import java.util.Map;

import com.orange.statics.entity.LinkEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("staticsLinkDao")
public class LinkDao implements DaoManager<LinkEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(LinkEntity e) {
		return dao.insert("statics.link.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("statics.link.deleteById", id);
	}
	
	public int delete(LinkEntity e) {
		return dao.delete("statics.link.delete", e);
	}
	
	public int deletes(String[] ids) {
		LinkEntity e = new LinkEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(LinkEntity e) {
		return dao.update("statics.link.update", e);
	}

	public LinkEntity selectById(String id) {
		return (LinkEntity) dao.selectOne("statics.link.selectById",id);
	}
	
	public LinkEntity selectOne(LinkEntity e) {
		return (LinkEntity) dao.selectOne("statics.link.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(LinkEntity e) {
		return dao.selectList("statics.link.selectList", e);
	}
	
	public PagerModel selectPageList(LinkEntity e) {
		return dao.selectPageList("statics.link.selectPageList",
				"statics.link.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("statics.link.selectList", arg1);
	}


}

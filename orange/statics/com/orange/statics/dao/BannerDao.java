package com.orange.statics.dao;

import java.util.List;
import java.util.Map;

import com.orange.statics.entity.BannerEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("staticsBannerDao")
public class BannerDao implements DaoManager<BannerEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(BannerEntity e) {
		return dao.insert("statics.banner.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("statics.banner.deleteById", id);
	}
	
	public int delete(BannerEntity e) {
		return dao.delete("statics.banner.delete", e);
	}
	
	public int deletes(String[] ids) {
		BannerEntity e = new BannerEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(BannerEntity e) {
		return dao.update("statics.banner.update", e);
	}

	public BannerEntity selectById(String id) {
		return (BannerEntity) dao.selectOne("statics.banner.selectById",id);
	}
	
	public BannerEntity selectOne(BannerEntity e) {
		return (BannerEntity) dao.selectOne("statics.banner.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(BannerEntity e) {
		return dao.selectList("statics.banner.selectList", e);
	}
	
	public PagerModel selectPageList(BannerEntity e) {
		return dao.selectPageList("statics.banner.selectPageList",
				"statics.banner.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("statics.banner.selectList", arg1);
	}


}

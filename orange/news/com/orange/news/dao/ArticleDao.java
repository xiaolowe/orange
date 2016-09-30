package com.orange.news.dao;

import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.news.entity.ArticleEntity;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository("newsArticleDao")
public class ArticleDao implements DaoManager<ArticleEntity> {
	
    @Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(ArticleEntity e) {
		return dao.insert("news.article.insert", e);
	}
	
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete("news.article.deleteById", id);
	}
	
	public int delete(ArticleEntity e) {
		return dao.delete("news.article.delete", e);
	}
	
	public int deletes(String[] ids) {
		ArticleEntity e = new ArticleEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}
	
	public int update(ArticleEntity e) {
		return dao.update("news.article.update", e);
	}

	public ArticleEntity selectById(String id) {
		return (ArticleEntity) dao.selectOne("news.article.selectById",id);
	}
	
	public ArticleEntity selectOne(ArticleEntity e) {
		return (ArticleEntity) dao.selectOne("news.article.selectOne", e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(ArticleEntity e) {
		return dao.selectList("news.article.selectList", e);
	}
	
	public PagerModel selectPageList(ArticleEntity e) {
		return dao.selectPageList("news.article.selectPageList",
				"news.article.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("news.article.selectList", arg1);
	}

	public ArticleEntity selectPreBy(ArticleEntity art) {
		return (ArticleEntity) dao.selectOne("news.article.selectPreBy",art);
	}
	
	public ArticleEntity selectNextBy(ArticleEntity art) {
		return (ArticleEntity) dao.selectOne("news.article.selectNextBy",art);
	}
}

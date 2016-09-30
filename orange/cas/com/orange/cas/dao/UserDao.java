package com.orange.cas.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.orange.cas.entity.UserEntity;
import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;

@Repository("casUserDao")
public class UserDao implements DaoManager<UserEntity> {

	@Resource
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int insert(UserEntity e) {
		return dao.insert("cas.user.insert", e);
	}

	public int deleteById(String id) {
		// TODO Auto-generated method stub
		UserEntity e = new UserEntity();
		e.setId(id);
		return delete(e);
	}

	public int delete(UserEntity e) {
		return dao.delete("cas.user.delete", e);
	}

	public int deletes(String[] ids) {
		UserEntity e = new UserEntity();
		for (int i = 0; i < ids.length; i++) {
			e.setId(ids[i]);
			delete(e);
		}
		return 0;
	}

	public int update(UserEntity e) {
		return dao.update("cas.user.update", e);
	}

	public UserEntity selectById(String id) {
		UserEntity e = new UserEntity();
		e.setId(id);
		return selectOne(e);
	}

	public UserEntity selectOne(UserEntity e) {
		return (UserEntity) dao.selectOne("cas.user.selectOne", e);
	}
	
	public int selectCount(UserEntity e) {
		return (Integer) dao.selectOne("cas.user.selectCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(UserEntity e) {
		return null;
	}

	public PagerModel selectPageList(UserEntity e) {
		return dao.selectPageList("cas.user.selectPageList",
				"cas.user.selectPageCount", e);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectList(Map<String, String> arg1) {
		// TODO Auto-generated method stub
		return dao.selectList("cas.user.selectList", arg1);
	}

}

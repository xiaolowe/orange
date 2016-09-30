package com.orange.cas.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.orange.cas.dao.UserDao;
import com.orange.cas.entity.UserEntity;
import com.orange.core.annotation.ServiceLogAnnotation;
import com.orange.core.base.service.Services;
import com.orange.core.base.service.ServicesManager;

@Service("casUserService")
public class UserService extends ServicesManager<UserEntity, UserDao> implements
Services<UserEntity>{

	@Resource(name="casUserDao")
	@Override
	public void setDao(UserDao mDao) {
		// TODO Auto-generated method stub
		dao = mDao;
	}
	
	@ServiceLogAnnotation(description = "后台用户登录")
	public UserEntity login(UserEntity user) {
		return (UserEntity) dao.selectOne(user);
	}
	
	/**
	 * 根据条件查询数量
	 * @param user
	 * @return
	 */
	public int selectCount(UserEntity user) {
		if(user==null){
			throw new NullPointerException();
		}
		return (Integer) dao.selectCount(user);
	}
	
	public UserEntity selectById(String id) {
		UserEntity user = new UserEntity();
		user.setId(id);
		return dao.selectOne(user);
	}
	
}

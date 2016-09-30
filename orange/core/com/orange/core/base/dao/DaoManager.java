package com.orange.core.base.dao;

import java.util.List;
import java.util.Map;

import com.orange.core.base.dao.page.PagerModel;

/**
 * 该接口提供业务逻辑最基本的服务，所有的业逻辑类都必须实现此接口，这样该业务逻辑类对应
 * 的action就免去了写基本selectList、insert、update、toEdit、deletes麻烦s
 * 
 */
public interface DaoManager<E extends PagerModel> {
	/**
	 * 添加
	 * 
	 * @param e
	 * @return
	 */
	public int insert(E e);
	
	/**
	 * 根据ID来删除一条记录
	 * @param id
	 */
	public int deleteById(String id);

	/**
	 * 删除
	 * 
	 * @param e
	 * @return
	 */
	public int delete(E e);

	/**
	 * 修改
	 * 
	 * @param e
	 * @return
	 */
	public int update(E e);
	
	/**
	 * 根据ID查询一条记录
	 * @param id
	 * @return
	 */
	public E selectById(String id);

	/**
	 * 查询一条记录
	 * 
	 * @param e
	 * @return
	 */
	public E selectOne(E e);
	

	/**
	 * 根据条件查询所有
	 * @return
	 */
	public List<E> selectList(E e);
	
	/**
	 * 分页查询
	 * 
	 * @param e
	 * @return
	 */
	public PagerModel selectPageList(E e);
	
	public List<E> selectList(Map<String, String> param);
	
}

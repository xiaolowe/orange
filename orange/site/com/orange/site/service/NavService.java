package com.orange.site.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.orange.core.base.service.ServicesManager;
import com.orange.site.dao.NavDao;
import com.orange.site.entity.NavEntity;
import com.orange.site.model.NavItem;

@Service("siteNavService")
public class NavService extends ServicesManager<NavEntity, NavDao>{

	@Resource(name = "siteNavDao")
	@Override
	public void setDao(NavDao mDao) {
		// TODO Auto-generated method stub
		this.dao = mDao;
	}
	
	public int getCount(NavEntity e) {
		return dao.getCount(e);
	}

	@SuppressWarnings("unchecked")
	public List<NavEntity> selectList(NavEntity e) {
		return dao.selectList(e);
	}

	/**
	 * 根据分类ID，删除分类树勾选的节点。某个非叶子节点即便它下面的所有的叶子节点都被勾选也不会被本次删除操作删除掉，
	 * 这样做是为了避免只想删除某个非叶子节点下面的所有子节点
	 * 
	 * @param ids
	 * @param deleteParent
	 *            是否级联删除父分类,在父分类下的所有子分类全部勾选的情况下,1:级联删除,-1不级联
	 */
	public void deletes(String ids, String deleteParent) {

		String[] idArr = ids.split(",");
		// 按照从小到大排序
		Arrays.sort(idArr, new Comparator<String>() {
			public int compare(String o1, String o2) {
				int a1 = Integer.parseInt(o1);
				int a2 = Integer.parseInt(o2);
				if (a1 > a2) {
					return 1;
				} else if (a1 < a2) {
					return -1;
				}
				return 0;
			}
		});
		NavEntity menu = new NavEntity();

		if (deleteParent.equals("-1")) {
			// 从分类ID最小的开始删起，避免先把ID大的删除了，倒置ID小的成为了叶子节点而被删除掉
			for (int i = 0; i < idArr.length; i++) {
				/*
				 * 1、分类下没有子分类，直接删除 2、分类下有子分类，检查所有的子分类是否全部已经勾选 A)全部勾选，则可以删除。
				 * B)没有全部勾选，则不能删除。
				 */
				menu.clear();
				menu.setPid(idArr[i]);
				if (this.getCount(menu) == 0) {
					// 指定节点下没有子分类，删除指定的分类(叶子)
					menu.clear();
					menu.setId(idArr[i]);
					dao.delete(menu);
				}
			}
		} else if (deleteParent.equals("1")) {
			for (int i = idArr.length - 1; i >= 0; i--) {
				/*
				 * 1、分类下没有子分类，直接删除 2、分类下有子分类，检查所有的子分类是否全部已经勾选 A)全部勾选，则可以删除。
				 * B)没有全部勾选，则不能删除。
				 */
				menu.clear();
				menu.setPid(idArr[i]);
				if (this.getCount(menu) == 0) {
					// 指定节点下没有子分类，删除指定的分类(叶子)
					menu.clear();
					menu.setId(idArr[i]);
					dao.delete(menu);
				} else {
					menu.clear();
					menu.setPid(idArr[i]);
					// 查询指定分类下的全部子分类
					List<NavEntity> menus = this.selectList(menu);
					if (menus != null && menus.size() > 0) {
						if (checkAllContains(idArr, menus)) {
							dao.delete(menu);
						}
					}
				}
			}
		} else {
			throw new NullPointerException("deleteParent:" + deleteParent);
		}
	}

	/**
	 * 检查指定的分类列表是否全部存在于另一个列表中
	 * 
	 * @param idArr
	 *            待删除的分类列表
	 * @param list
	 *            被检查的分类列表
	 * @return 全部存在返回true，否则返回false
	 */
	private boolean checkAllContains(String[] idArr, List<NavEntity> list) {
		int n = list.size();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < idArr.length; j++) {
				if (list.get(i).getId().equals(idArr[j])) {
					n--;
					break;
				}
			}
		}
		return n == 0 ? true : false;
	}

	/**
	 * 添加子分类、修改父分类
	 * 
	 * @param updateP
	 *            -1：修改父分类，否则添加子分类并且可能修改父分类
	 * @param menu
	 *            父分类
	 * @param itemMenu
	 *            子分类
	 * @return
	 */
	public boolean addOrUpdate(String updateP, NavEntity menu, NavEntity itemMenu) {
		if (itemMenu != null) {
			// 添加子分类
			insert(itemMenu);
		}
		// 修改父分类
		update(menu);
		return true;
	}

	// 加载根节点
	@SuppressWarnings("unchecked")
	public List<NavItem> loadNavs(String pid) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pid", pid);// 分类父ID
		List<NavEntity> menus = dao.loadMenus(param);
		// 创建分类集合
		List<NavItem> root = new ArrayList<NavItem>();
		// 循环添加分类到分类集合
		for (int i = 0; i < menus.size(); i++) {
			NavEntity menu = menus.get(i);
			NavItem item = new NavItem(menu.getName(), null);
			item.setId(menu.getId());
			item.setPid(menu.getPid());
			item.setUrl(menu.getUrl());
			item.setOrders(menu.getOrders());
			item.setModel(menu.getModel());
			item.setBanner(menu.getBanner());
			root.add(item);
		}
		for (int i = 0; i < root.size(); i++) {
			NavItem ee = root.get(i);
			NavEntity mm = new NavEntity();
			mm.setPid(ee.getId());
			loadChildrenByPid(ee, mm);
		}

		return root;
	}

	/**
	 * 根据父ID加载子节点
	 * 
	 * @param item
	 * @param menu
	 * @param url
	 * @param u
	 */
	@SuppressWarnings("unchecked")
	private void loadChildrenByPid(NavItem item, NavEntity menu) {
		Map<String, String> param = new HashMap<String, String>();

		param.put("pid", menu.getPid());
		// 加载分类节点
		List<NavEntity> data = dao.loadMenus(param);
		if (data == null || data.size() == 0) {
			return;
		}
		if (item.getChildren() == null)
			item.setChildren(new ArrayList<NavItem>());
		// 创建分类节点
		for (int i = 0; i < data.size(); i++) {
			NavEntity entry = data.get(i);
			NavItem addItem = new NavItem(entry.getName(), null);
			addItem.setId(entry.getId());
			addItem.setPid(entry.getPid());
			addItem.setUrl(entry.getUrl());
			addItem.setOrders(entry.getOrders());
			addItem.setModel(entry.getModel());
			addItem.setBanner(entry.getBanner());
			item.getChildren().add(addItem);
		}
		// 根据分类节点进行递归加载
		for (int i = 0; i < item.getChildren().size(); i++) {
			NavEntity itemMenu = new NavEntity();

			itemMenu.setPid(item.getChildren().get(i).getId());
			loadChildrenByPid(item.getChildren().get(i), itemMenu);
		}
	}


}

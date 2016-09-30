package com.orange.cas.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.orange.cas.dao.MenuDao;
import com.orange.cas.entity.MenuEntity;
import com.orange.cas.entity.UserEntity;
import com.orange.cas.model.MenuItem;
import com.orange.cas.model.MenuType;
import com.orange.core.base.service.ServicesManager;

@Service("casMenuService")
public class MenuService extends ServicesManager<MenuEntity, MenuDao>{

	@Resource(name = "casMenuDao")
	@Override
	public void setDao(MenuDao mDao) {
		// TODO Auto-generated method stub
		this.dao = mDao;
	}
	
	public int getCount(MenuEntity menu) {
		return dao.getCount(menu);
	}

	@SuppressWarnings("unchecked")
	public List<MenuEntity> selectList(MenuEntity menu) {
		return dao.selectList(menu);
	}

	/**
	 * 根据菜单ID，删除菜单树勾选的节点。某个非叶子节点即便它下面的所有的叶子节点都被勾选也不会被本次删除操作删除掉，
	 * 这样做是为了避免只想删除某个非叶子节点下面的所有子节点
	 * 
	 * @param ids
	 * @param deleteParent
	 *            是否级联删除父菜单,在父菜单下的所有子菜单全部勾选的情况下,1:级联删除,-1不级联
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
		MenuEntity menu = new MenuEntity();

		if (deleteParent.equals("-1")) {
			// 从菜单ID最小的开始删起，避免先把ID大的删除了，倒置ID小的成为了叶子节点而被删除掉
			for (int i = 0; i < idArr.length; i++) {
				/*
				 * 1、菜单下没有子菜单，直接删除 2、菜单下有子菜单，检查所有的子菜单是否全部已经勾选 A)全部勾选，则可以删除。
				 * B)没有全部勾选，则不能删除。
				 */
				menu.clear();
				menu.setPid(idArr[i]);
				if (this.getCount(menu) == 0) {
					// 指定节点下没有子菜单，删除指定的菜单(叶子)
					menu.clear();
					menu.setId(idArr[i]);
					dao.delete(menu);
				}
			}
		} else if (deleteParent.equals("1")) {
			for (int i = idArr.length - 1; i >= 0; i--) {
				/*
				 * 1、菜单下没有子菜单，直接删除 2、菜单下有子菜单，检查所有的子菜单是否全部已经勾选 A)全部勾选，则可以删除。
				 * B)没有全部勾选，则不能删除。
				 */
				menu.clear();
				menu.setPid(idArr[i]);
				if (this.getCount(menu) == 0) {
					// 指定节点下没有子菜单，删除指定的菜单(叶子)
					menu.clear();
					menu.setId(idArr[i]);
					dao.delete(menu);
				} else {
					menu.clear();
					menu.setPid(idArr[i]);
					// 查询指定菜单下的全部子菜单
					List<MenuEntity> menus = this.selectList(menu);
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
	 * 检查指定的菜单列表是否全部存在于另一个列表中
	 * 
	 * @param idArr
	 *            待删除的菜单列表
	 * @param list
	 *            被检查的菜单列表
	 * @return 全部存在返回true，否则返回false
	 */
	private boolean checkAllContains(String[] idArr, List<MenuEntity> list) {
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
	 * 添加子菜单、修改父菜单
	 * 
	 * @param updateP
	 *            -1：修改父菜单，否则添加子菜单并且可能修改父菜单
	 * @param menu
	 *            父菜单
	 * @param itemMenu
	 *            子菜单
	 * @return
	 */
	public boolean addOrUpdate(String updateP, MenuEntity menu, MenuEntity itemMenu) {
		if (itemMenu != null) {
			// 添加子菜单
			insert(itemMenu);
		}
		// 修改父菜单
		update(menu);
		return true;
	}

	// 加载根节点
	@SuppressWarnings("unchecked")
	public List<MenuItem> loadMenus(UserEntity u, String pid, String url) {
		Map<String, String> param = new HashMap<String, String>();
		if (u != null && u.getRid() != null) {
			param.put("rid", u.getRid());// 角色ID
		}
		param.put("pid", pid);// 菜单父ID
		List<MenuEntity> menus = dao.loadMenus(param);
		// 创建菜单集合
		List<MenuItem> root = new ArrayList<MenuItem>();
		// 循环添加菜单到菜单集合
		for (int i = 0; i < menus.size(); i++) {
			MenuEntity menu = menus.get(i);
			MenuItem item = new MenuItem(menu.getName(), null);
			item.setId(menu.getId());
			item.setPid(menu.getPid());
			item.setMenuType(menu);
			if (url != null) {
				item.setUrl(url);
			} else {
				item.setUrl(menu.getUrl());
			}
			root.add(item);
		}
		// 加载子菜单，注意 只加载type为模块级或页面级的
		for (int i = 0; i < root.size(); i++) {
			MenuItem ee = root.get(i);
			if (ee.getType() == null || ee.getType().toString().equals("")
					|| ee.getType().equals(MenuType.button)) {
				continue;
			}

			MenuEntity mm = new MenuEntity();
			mm.setPid(ee.getId());
			loadChildrenByPid(ee, mm, url, u);
		}

		return root;
	}

	// 加载根节点
	@SuppressWarnings("unchecked")
	public List<MenuEntity> loadBtnMenus(String rId, String type) {
		Map<String, String> param = new HashMap<String, String>();
		if (StringUtils.isNotBlank(rId)) {
			param.put("rid", rId);// 角色ID
		}
		if (StringUtils.isNotBlank(type)) {
			param.put("type", type);
		}

		List<MenuEntity> menus = dao.selectBtnList(param);

		return menus;
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
	private void loadChildrenByPid(MenuItem item, MenuEntity menu, String url, UserEntity u) {
		Map<String, String> param = new HashMap<String, String>();

		if (u != null && u.getRid() != null)
			param.put("rid", u.getRid());
		param.put("pid", menu.getPid());
		// 加载菜单节点
		List<MenuEntity> data = dao.loadMenus(param);
		if (data == null || data.size() == 0) {
			return;
		}
		if (item.getChildren() == null)
			item.setChildren(new ArrayList<MenuItem>());
		// 创建菜单节点
		for (int i = 0; i < data.size(); i++) {
			MenuEntity entry = data.get(i);
			MenuItem addItem = new MenuItem(entry.getName(), null);
			addItem.setId(entry.getId());
			addItem.setPid(entry.getPid());
			addItem.setMenuType(entry);
			if (url != null) {
				addItem.setUrl(url);
			} else {
				addItem.setUrl(entry.getUrl());
			}
			item.getChildren().add(addItem);
		}
		// 根据菜单节点进行递归加载
		for (int i = 0; i < item.getChildren().size(); i++) {
			MenuEntity itemMenu = new MenuEntity();

			itemMenu.setPid(item.getChildren().get(i).getId());
			loadChildrenByPid(item.getChildren().get(i), itemMenu, url, u);
		}
	}


}

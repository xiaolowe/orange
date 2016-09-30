package com.orange.cas.action;

import com.orange.cas.entity.MenuEntity;
import com.orange.cas.entity.PrivilegeEntity;
import com.orange.cas.entity.UserEntity;
import com.orange.cas.model.MenuItem;
import com.orange.cas.model.MenuType;
import com.orange.cas.service.MenuService;
import com.orange.cas.service.PrivilegeService;
import com.orange.common.ManageContainer;
import com.orange.core.annotation.ControllerLogAnnotation;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.core.base.service.Services;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;
import com.orange.web.action.holder.LoginUserHolder;
import com.orange.web.action.holder.RequestHolder;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


/**
 * 资源管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/cas/res/")
public class MenuAction extends BaseAction<MenuEntity> {
	
    @Autowired
	private MenuService casMenuService;
    @Autowired
	private PrivilegeService privilegeService;
	private static final Logger log = LoggerFactory.getLogger(MenuAction.class);
	
    private static final String page_toList = Commons.SCOPE_MANAGE + "/cas/res/menuList";
    private static final String page_toEdit = null;
    private static final String page_addOrUpdate = Commons.SCOPE_MANAGE + "/cas/res/addOrUpdate";

    public MenuAction() {
        super.page_toList = page_toList;
        super.page_toEdit = page_toEdit;
        super.page_toAdd = page_toEdit;
    }
    
    @Override
    public Services<MenuEntity> getService() {
        return casMenuService;
    }
    
    public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

    @Override
	protected void selectListAfter(PagerModel pager) {
		pager.setPagerUrl("selectList");
	}
	/**
	 * 转到 添加/修改菜单 页面
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("toAddOrUpdate")
    @ControllerLogAnnotation(description = "权限控制项访问", client="0",  opt="0")
	public String toAddOrUpdate(@ModelAttribute("e") MenuEntity menu, ModelMap model) throws Exception{
		menu = casMenuService.selectOne(menu);
        model.addAttribute("e", menu);
		return page_addOrUpdate;
	}
	/**
	 * 添加/修改菜单
	 * 修改选中的菜单，为该菜单添加子菜单
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "addOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    @ControllerLogAnnotation(description = "权限控制项操作",  opt="7")
	public String addOrUpdate(HttpServletRequest request) throws Exception{
		//选中菜单的信息
		String updateP = request.getParameter("updateP");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String orderNum = request.getParameter("orderNum");
		String type = request.getParameter("type");
		
		//要添加的子菜单
		String url = request.getParameter("url");
		String n_name = request.getParameter("n_name");
		String n_url = request.getParameter("n_url");
		String parentOrChild = request.getParameter("parentOrChild");
		String n_orderNum = request.getParameter("n_orderNum");
		String n_type = request.getParameter("n_type");
		
		MenuEntity itemMenu = null;
		if(n_name!=null && !n_name.trim().equals("")){
			itemMenu = new MenuEntity();
			//添加子菜单
			if(parentOrChild.equals("0")){//顶级模块
				itemMenu.setPid("0");
				itemMenu.setType(MenuType.module.toString());
			} else if(parentOrChild.equals("1")){//顶级页面
				itemMenu.setPid("0");
				itemMenu.setType(MenuType.page.toString());
			} else if(parentOrChild.equals("2")){//子模块
				itemMenu.setPid(id);
				itemMenu.setType(MenuType.module.toString());
			} else if(parentOrChild.equals("3")){//子页面
				itemMenu.setPid(id);
				itemMenu.setType(MenuType.page.toString());
			} else if(parentOrChild.equals("4")){   //功能
				itemMenu.setPid(id);
				itemMenu.setType(MenuType.button.toString());
			} else {
				throw new IllegalAccessException("添加菜单异常。");
			}
			itemMenu.setName(n_name);
			itemMenu.setUrl(n_url);
			itemMenu.setOrderNum(Integer.valueOf(n_orderNum));
			itemMenu.setType(n_type);
		}
		//修改父菜单
		MenuEntity m = new MenuEntity();
		m.setId(id);
		m.setName(name);
		m.setUrl(url);
		m.setOrderNum(Integer.valueOf(orderNum));
		m.setType(type);
		
		casMenuService.addOrUpdate(updateP,m, itemMenu);
		
		return "ok";
	}
	
	//加载指定角色的全部菜单
    @RequestMapping("selectJsonMenu")
    @ResponseBody
    @ControllerLogAnnotation(description = "权限控制项查询", client="0",  opt="4")
	public String selectJsonMenu(HttpSession session) throws Exception {
		Object menusJson = session.getAttribute(ManageContainer.resource_menus);
		if(menusJson!=null){
			return menusJson.toString();
		}else{
			UserEntity u = LoginUserHolder.getLoginUser();
			List<MenuItem> root = loadMenus(u,"0",null);
			String jsonMenus = writeMenus(root);
			session.setAttribute(ManageContainer.resource_menus, jsonMenus);
            return jsonMenus;
		}
	}
	
	/**
	 * 添加用户资源功能到session，为后面权限功能检查做铺垫
	 */
	@SuppressWarnings("unchecked")
	private void addUserResourceMenusButton(String button){
		log.debug("addUserResourceMenusButton.button="+button);
        HttpSession session = RequestHolder.getSession();
		Map<String,String> buttons = (Map<String, String>) session.getAttribute(ManageContainer.user_resource_menus_button);
		if(buttons==null){
			buttons = new HashMap<String, String>();//TreeMap<String, String>();
            session.setAttribute(ManageContainer.user_resource_menus_button, buttons);
		}
		buttons.put(button, button);
	}
	
	/**
	 * 从PID=0开始加载菜单资源
	 * 获取指定节点的全部子菜单（包括当前菜单节点）
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("getMenusByPid")
    @ResponseBody
	public String getMenusByPid(HttpServletRequest request) throws Exception {
		String pid = request.getParameter("pid");
		if(pid==null || pid.trim().equals(""))
			pid = "0";
		String id = request.getParameter("id");
		List<MenuItem> menus = casMenuService.loadMenus(null, pid, "#");
		
		// 加载全部的菜单
		if(id!=null){
			// 加载指定角色的权限
			PrivilegeEntity privilege = new PrivilegeEntity();
			privilege.setRid(id);
			List<PrivilegeEntity> rolePs = privilegeService.selectList(privilege);
			
			// 拿角色拥有的菜单和全部的菜单做比对，进行勾选
			for (int i = 0; i < rolePs.size(); i++) {
				PrivilegeEntity p = rolePs.get(i);
				eeee(p, menus);
			}
		}
		return writeMenus(menus);
	}
	
	/**
	 * 角色权限和资源菜单进行对比，使checkbox选中
	 * @param p
	 * @param menus
	 */
	private void eeee(PrivilegeEntity p,List<MenuItem> menus){
		for (int j = 0; j < menus.size(); j++) {
			MenuItem menu = menus.get(j);
			if (p.getMid().equals(menu.getId())) {
				menu.setChecked(true);
				return;
			}else{
				if(menu.getChildren()!=null && menu.getChildren().size()>0)
					eeee(p, menu.getChildren());
			}
		}
	}
	
	//输出菜单到页面
	private String writeMenus(List<MenuItem> root) throws IOException{
		JSONArray json = JSONArray.fromObject(root);
		String jsonStr = json.toString();
		try {
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 根据角色ID，加载用户菜单资源
	 * @param u
	 * @param pid
	 * @param url
	 * @return
	 */
	private List<MenuItem> loadMenus(UserEntity u,String pid,String url) {
		/*
		 * 首先，加载顶级目录或页面菜单 
		 */
		Map<String,String> param = new HashMap<String, String>();
		if(u!=null && u.getRid()!=null){
			param.put("rid", u.getRid());//角色ID
		}
		param.put("pid", pid);//菜单父ID
		List<MenuEntity> menus = casMenuService.selectList(param);
		//创建菜单集合
		List<MenuItem> root = new ArrayList<MenuItem>();
		//循环添加菜单到菜单集合
		for (Iterator<MenuEntity> it = menus.iterator(); it.hasNext();) {
			MenuEntity entry = it.next();
			MenuItem item = new MenuItem(entry.getName(), null);
			item.setId(entry.getId());
			item.setPid(entry.getPid());
			item.setMenuType(entry);
			if(url!=null){
				item.setUrl(url);
			}else{
				item.setUrl(entry.getUrl());
			}
			root.add(item);
		}
		
		/*
		 * 其次，加载子菜单 或 按钮功能
		 */
		for (int i = 0; i < root.size(); i++) {
			MenuItem item = root.get(i);
			if(!item.isButton()){
				MenuEntity mm = new MenuEntity();
				mm.setPid(item.getId());
				loadChildrenByPid(root.get(i), mm,url,u);
			}else{
				//addUserResourceMenusButton(item.getUrl());
			}
		}

		return root;
	}

	// 根据父ID加载子节点
	private void loadChildrenByPid(MenuItem item, MenuEntity menu,String url,UserEntity u) {
		Map<String,String> param = new HashMap<String, String>();
		
		if(u!=null && u.getRid()!=null)
			param.put("rid", u.getRid());
		param.put("pid", menu.getPid());
		//加载菜单节点
		List<MenuEntity> data = casMenuService.selectList(param);
		if(data==null || data.size()==0){
			return;
		}
		if(item.getChildren()==null)item.setChildren(new ArrayList<MenuItem>());
		//创建菜单节点
		for (int i = 0; i < data.size(); i++) {
			MenuEntity entry = data.get(i);
			
			MenuItem addItem = new MenuItem(entry.getName(), null);
			addItem.setId(entry.getId());
			addItem.setPid(entry.getPid());
			addItem.setMenuType(entry);
			String url0 = null;
			if(url!=null){
				addItem.setUrl(url);
				url0 = url;
			}else{
				addItem.setUrl(entry.getUrl());
				url0 = entry.getUrl();
			}
//			System.out.println("entry.getType()="+entry.getType()+",MenuType.button="+MenuType.button);
			if(entry.getType().equals("button")){
				addUserResourceMenusButton(url0);
			}else{
				item.getChildren().add(addItem);
			}
		}
		//根据菜单节点进行递归加载
		for (int i = 0; i < item.getChildren().size(); i++) {
			MenuItem childItem = item.getChildren().get(i);
			if(!childItem.isButton()){
				MenuEntity itemMenu = new MenuEntity();
				itemMenu.setPid(childItem.getId());
//				itemMenu.setMenuType(entry);
				loadChildrenByPid(childItem, itemMenu,url,u);
			}
		}
	}

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ControllerLogAnnotation(description = "更新权限控制项", client="0",  opt="7")
	public String save(@ModelAttribute("e") MenuEntity menu) throws Exception {
		if (menu.getId() == null || menu.getId().equals("")) {
			if (menu.getUrl() == null) {
				menu.setUrl("");
			}
			casMenuService.insert(menu);
		} else {
			casMenuService.update(menu);
		}
		return selectList(RequestHolder.getRequest(), menu);
	}

	/**
	 * 删除菜单
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @ControllerLogAnnotation(description = "删除权限控制项", client="0",  opt="3")
	public String delete(HttpServletRequest request) throws Exception {
		
		String ids = request.getParameter("ids");
		if(ids==null || ids.trim().equals(""))
				throw new Exception("删除菜单异常！");
		
		this.casMenuService.deletes(ids,request.getParameter("deleteParent"));
		
		//删除成功返回1
		return "1";
	}

	@Override
	public void insertAfter(MenuEntity e) {
		// TODO Auto-generated method stub
		e.clear();
	}
	
}

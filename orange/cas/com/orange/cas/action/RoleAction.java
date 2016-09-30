package com.orange.cas.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.orange.cas.entity.MenuEntity;
import com.orange.cas.entity.RoleEntity;
import com.orange.cas.entity.UserEntity;
import com.orange.cas.model.MenuItem;
import com.orange.cas.service.MenuService;
import com.orange.cas.service.RoleService;
import com.orange.cas.service.UserService;
import com.orange.common.ManageContainer;
import com.orange.core.annotation.ControllerLogAnnotation;
import com.orange.core.base.service.Services;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;
import com.orange.web.action.holder.LoginUserHolder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 角色action
 *
 */
@Controller
@RequestMapping("/cas/role")
public class RoleAction extends BaseAction<RoleEntity> {
	private static final Logger logger = LoggerFactory.getLogger(RoleAction.class);
    @Autowired
	private RoleService casRoleService;
    @Autowired
    private UserService casUserService;
    
    @Autowired
	private MenuService casMenuService;
    
    private static final String page_toList = Commons.SCOPE_MANAGE + "/cas/role/roleList";
    private static final String page_toEdit = Commons.SCOPE_MANAGE + "/cas/role/editRole";
    private static final String page_toAdd = Commons.SCOPE_MANAGE + "/cas/role/editRole";

    public RoleAction() {
        super.page_toList = page_toList;
        super.page_toEdit = page_toEdit;
        super.page_toAdd = page_toAdd;
    }
    
    @Override
	public Services<RoleEntity> getService() {
		return this.casRoleService;
	}
    
	/**
	 * 添加角色
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @ControllerLogAnnotation(description = "添加角色", client="0",  opt="1")
	public String save(HttpServletRequest request, RoleEntity role, HttpSession session) throws Exception {
		role.setRole_name(request.getParameter("roleName"));
        role.setId(request.getParameter("id"));
        role.setRole_desc(request.getParameter("roleDesc"));
        role.setRole_dbPrivilege(request.getParameter("role_dbPrivilege"));
        role.setPrivileges(request.getParameter("privileges"));
        role.setStatus(request.getParameter("status"));
		if(role.getRole_name()==null || role.getRole_name().trim().equals("")){
			return "0";
		}else{
			casRoleService.editRole(role, request.getParameter("insertOrUpdate"));
		}
		//用户可访问的菜单写入session
		UserEntity u = LoginUserHolder.getLoginUser();
		Collection<MenuItem> userMenus = loadMenus(u);
		session.setAttribute("userMenus", userMenus);
		List<MenuEntity> mMenu = casMenuService.loadBtnMenus(u.getRid(), ManageContainer.Privilege_Type_Button);
		Map<String, String> btnMenus = new HashMap<String, String>();
		for(MenuEntity menu : mMenu){
			btnMenus.put(menu.getUrl(), menu.getUrl());
		}
		session.setAttribute(ManageContainer.user_resource_menus_button, btnMenus);
		return "1";
	}
    
    private Collection<MenuItem> loadMenus(UserEntity u) {
		/*
		 * 首先，加载顶级目录或页面菜单
		 */
		Map<String, String> param = new HashMap<String, String>();
		if (u != null && u.getRid() != null) {
			param.put("rid", u.getRid());//角色ID
		}
//		param.put("pid", pid);//菜单父ID
		List<MenuEntity> menus = casMenuService.selectList(param);
		//创建菜单集合
		LinkedHashMap<String, MenuItem> root = new LinkedHashMap<String, MenuItem>();
		//循环添加菜单到菜单集合
		for (MenuEntity menu : menus) {
			MenuItem item = new MenuItem(menu.getName(), null);
			item.setId(menu.getId());
			item.setPid(menu.getPid());
			item.setMenuType(menu);
			item.setUrl(StringUtils.trimToEmpty(menu.getUrl()));
			if(item.isRootMenu()) {
				root.put(item.getId(), item);
			}
		}
		for (MenuEntity menu : menus) {
			MenuItem item = new MenuItem(menu.getName(), null);
			item.setId(menu.getId());
			item.setPid(menu.getPid());
			item.setMenuType(menu);
			item.setUrl(StringUtils.trimToEmpty(menu.getUrl()));
			if(!item.isRootMenu() && !item.isButton()) {
				MenuItem parentItem = root.get(item.getPid());
				if(parentItem != null) {
					parentItem.addClild(item);
				} else {
					logger.warn("菜单项{}({})没有对应的父级菜单", item.getName(), item.getId());
				}
			}
		}
		return root.values();
	}
	
	@Override
    @RequestMapping(value = "deletes", method = RequestMethod.POST)
	@ControllerLogAnnotation(description = "删除角色", client="0",  opt="3")
	public String deletes(HttpServletRequest request, String[] ids, @ModelAttribute("e") RoleEntity e, RedirectAttributes flushAttrs) throws Exception {
		for (int i = 0; i < ids.length; i++) {
//			if(user.getRid().equals(ids[i])){
//				addError(flushAttrs, ManageContainer.RoleAction_delete_self_error);
//				return "redirect:selectList";
//			}
			UserEntity u = new UserEntity();
			u.setRid(ids[i]);
			UserEntity list = casUserService.selectOne(u);
			if(list != null){
				addError(flushAttrs, ManageContainer.RoleAction_delete_users_error);
				return "redirect:selectList";
			}
		}
		return super.deletes(request, ids, e, flushAttrs);
	}

	/**
	 * 只能是超级管理员才具有编辑其他用户权限的功能
	 */
	@Override
    @RequestMapping(value = "update", method = RequestMethod.POST)
	@ControllerLogAnnotation(description = "修改角色", client="0",  opt="2")
	public String update(HttpServletRequest request, @ModelAttribute("e") RoleEntity role, RedirectAttributes flushAttrs) throws Exception {
        UserEntity user = LoginUserHolder.getLoginUser();
		if(!user.getRid().equals("1")){
			throw new NullPointerException(ManageContainer.RoleAction_update_error);
		}
		return super.update(request, role, flushAttrs);
	}
}

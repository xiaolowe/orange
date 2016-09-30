package com.orange.cas.action;

import com.ibm.icu.text.SimpleDateFormat;
import com.orange.cas.entity.MenuEntity;
import com.orange.cas.entity.RoleEntity;
import com.orange.cas.entity.UserEntity;
import com.orange.cas.model.MenuItem;
import com.orange.cas.model.LogUser;
import com.orange.cas.service.MemberService;
import com.orange.cas.service.MenuService;
import com.orange.cas.service.RoleService;
import com.orange.cas.service.UserService;
import com.orange.common.ManageContainer;
import com.orange.core.annotation.ControllerLogAnnotation;
import com.orange.core.base.service.Services;
import com.orange.core.util.MD5;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;
import com.orange.web.action.holder.LoginUserHolder;
import com.orange.web.action.holder.RequestHolder;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * 后台用户管理
 *
 */
@Controller
@RequestMapping("/cas/user")
public class UserAction extends BaseAction<UserEntity> {
	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);


    private static final String page_input = Commons.SCOPE_MANAGE + "/cas/login";
    private static final String page_home = Commons.SCOPE_MANAGE + "/cas/home";
    private static final String page_toList = Commons.SCOPE_MANAGE + "/cas/user/userList";
    private static final String page_toAdd = Commons.SCOPE_MANAGE + "/cas/user/editUser";
    private static final String page_toEdit = Commons.SCOPE_MANAGE + "/cas/user/editUser";
    private static final String page_toChangePwd = Commons.SCOPE_MANAGE + "/cas/user/toChangePwd";
    private static final String page_changePwd_result = Commons.SCOPE_MANAGE + "/cas/user/toChangePwd";
    private static final String page_show = Commons.SCOPE_MANAGE + "/cas/user/show";
    private static final String page_initManageIndex = page_home;
   
    public UserAction() {
        super.page_toEdit = page_toEdit;
        super.page_toList = page_toList;
        super.page_toAdd = page_toAdd;
    }
    @Autowired
	private UserService casUserService;
    @Autowired
	private RoleService casRoleService;
	@Autowired
	private MenuService casMenuService;
    @Autowired
    private MemberService casMemberService;
    

    @Override
    public Services<UserEntity> getService() {
        return casUserService;
    }
   
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ControllerLogAnnotation(description = "后台用户登录访问", client="0", opt="0")
    public String login(@ModelAttribute("e") UserEntity e, HttpSession session){
		if (session.getAttribute(ManageContainer.session_user_info) != null) {
			return "redirect:/cas/user/home";
		}
        return page_input;
    }

	/**
	 * 后台登录
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ControllerLogAnnotation(description = "后台用户登录", client="0",  opt="5") 
	public String login(@ModelAttribute("e") UserEntity e, HttpSession session, ModelMap model) {
		String errorMsg;
		if (session.getAttribute(ManageContainer.session_user_info) != null) {
			return "redirect:/cas/user/home";
		}

		if (StringUtils.isBlank(e.getUsername()) || StringUtils.isBlank(e.getPassword())){
			model.addAttribute("errorMsg", "账户和密码不能为空!");
			return page_input;
		}
		logger.info("用户登录:{}", e.getUsername());
		e.setPassword(MD5.md5(e.getPassword()));
		UserEntity u = casUserService.login(e);
		if (u == null) {
			errorMsg = "登陆失败，账户或密码错误！";
			logger.error("登陆失败，账户或密码错误,{}", e.getUsername());
			model.addAttribute("errorMsg", errorMsg);
			return page_input;
		}else if(!u.getStatus().equals(UserEntity.user_status_y)){
			errorMsg = "帐号已被禁用，请联系管理员!";
			logger.error("帐号已被禁用，请联系管理员,{}", u.getUsername());
			model.addAttribute("errorMsg", errorMsg);
			return page_input;
		}
		u.setUsername(e.getUsername());
		session.setAttribute(ManageContainer.session_user_info, u);
		LogUser lu = new LogUser(u.getUsername(), u.getMobile());
		
		session.setAttribute(ManageContainer.session_log_user_info, lu);
		//解析用户的数据库权限，以后可以进行DB权限限制
		if(StringUtils.isNotBlank(u.getRole_dbPrivilege())){
			String[] dbPriArr = u.getRole_dbPrivilege().split(",");
			if(u.getDbPrivilegeMap()==null){
				u.setDbPrivilegeMap(new HashMap<String, String>());
			}else{
				u.getDbPrivilegeMap().clear();
			}
			if(dbPriArr.length!=0){
				for(int i=0;i<dbPriArr.length;i++){
					u.getDbPrivilegeMap().put(dbPriArr[i], dbPriArr[i]);
				}
			}
		}
		//用户可访问的菜单写入session
		Collection<MenuItem> userMenus = loadMenus(u);
		session.setAttribute("userMenus", userMenus);
		List<MenuEntity> mMenu = casMenuService.loadBtnMenus(u.getRid(), ManageContainer.Privilege_Type_Button);
		Map<String, String> btnMenus = new HashMap<String, String>();
		for(MenuEntity menu : mMenu){
			btnMenus.put(menu.getUrl(), menu.getUrl());
		}
		session.setAttribute(ManageContainer.user_resource_menus_button, btnMenus);
		
		return "redirect:/cas/user/home";
	}
    
    @RequestMapping("home")
    @ControllerLogAnnotation(description = "后台首页访问", client="0", opt="0") 
    public String home(ModelMap model){
        if(LoginUserHolder.getLoginUser() == null){
            return "redirect:/cas/user/login";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String time = sdf.format(new Date());
    	model.addAttribute("newUserNum", casMemberService.selectCount(time));
    	model.addAttribute("totalUserNum", casMemberService.selectTotalCount());
        return page_home;
    }

    /**
	 * 加载后台首页数据
	 * @return
	 */
    @RequestMapping("initManageIndex")
    @ControllerLogAnnotation(description = "后台首页访问", client="0",  opt="0") 
	public String initManageIndex(ModelMap model){
		//店主每次登陆后台都需要加载综合统计数据？！还是说每次都触发加载，但是到底加载不加载具体看系统的加载策略？！
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     	String time = sdf.format(new Date());
     	model.addAttribute("newUserNum", casMemberService.selectCount(time));
     	model.addAttribute("totalUserNum", casMemberService.selectTotalCount());
		return page_initManageIndex;
	}
	
    /**
     * 注销登录
     * @return
     * @throws Exception
     */
    @RequestMapping("logout")
    @ControllerLogAnnotation(description = "后台用户退出登录", client="0",  opt="6") 
	public String logout(@ModelAttribute("e") UserEntity e) throws Exception {
        HttpSession session = RequestHolder.getSession();
        if(session != null) {
            session.setAttribute(ManageContainer.session_user_info, null);
            session.setAttribute(ManageContainer.resource_menus, null);
            session.setAttribute(ManageContainer.user_resource_menus_button, null);
        }
		e.clear();
		return page_input;
	}
    
    
	@Override
    @RequestMapping("selectList")
	@ControllerLogAnnotation(description = "后台用户查询", client="0",  opt="4") 
	public String selectList(HttpServletRequest request, @ModelAttribute("e")UserEntity e)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("roleList", casRoleService.selectList(new RoleEntity()));
		return super.selectList(request, e);
	}


	/**
	 * 添加用户
	 */
    @Override
    @RequestMapping("insert")
    @ControllerLogAnnotation(description = "添加后台用户信息", client="0",  opt="1") 
	public String insert(HttpServletRequest request, @ModelAttribute("e") UserEntity user, RedirectAttributes flushAttrs) throws Exception {
		return save0(user, flushAttrs);
	}

	/**
	 * 修改用户信息
	 */
    @Override
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ControllerLogAnnotation(description = "修改后台用户信息", client="0",  opt="2") 
	public String update(HttpServletRequest request, @ModelAttribute("e") UserEntity user, RedirectAttributes flushAttrs) throws Exception {
		return save0(user, flushAttrs);
	}

	private String save0(UserEntity e, RedirectAttributes flushAttrs) throws Exception {
		logger.error("save0..."+e.getPassword()+","+e.getNewpassword2());
		
		if(StringUtils.isBlank(e.getId())){//添加
			if(StringUtils.isBlank(e.getPassword()) || StringUtils.isBlank(e.getNewpassword2())){
				flushAttrs.addFlashAttribute("errorMsg", "输入的密码不符合要求！");
				return "redirect:toEdit?id=" + e.getId();
			}
			
			if(!e.getPassword().equals(e.getNewpassword2())){
				flushAttrs.addFlashAttribute("errorMsg", "两次输入的密码不一致！");
				return "redirect:toEdit?id=" + e.getId();
			}
			
			UserEntity user = (UserEntity)RequestHolder.getSession().getAttribute(ManageContainer.session_user_info);
			e.setCreateAccount(user.getUsername());
			if(StringUtils.isBlank(e.getStatus())){
				e.setStatus(UserEntity.user_status_y);
			}
			e.setPassword(MD5.md5(e.getPassword()));
			casUserService.insert(e);
		}else{//修改
			
			//当前登录用户是admin，才能修改admin的信息，其他用户修改admin信息都属于非法操作。
			UserEntity user = (UserEntity)RequestHolder.getSession().getAttribute(ManageContainer.session_user_info);
			if(!user.getUsername().equals("admin") && e.getUsername().equals("admin")){
//				throw new RuntimeException("操作非法！");
				logger.warn("非admin用户正在尝试修改admin用户信息，{}", user.getUsername());
				flushAttrs.addFlashAttribute("errorMsg", "非法操作！");
				return "redirect:toEdit?id=" + e.getId();
			}
			
			if(StringUtils.isBlank(e.getPassword()) && StringUtils.isBlank(e.getNewpassword2())){
				//不修改密码
				e.setPassword(null);
			}else{
				//修改密码
				if(!e.getPassword().equals(e.getNewpassword2())){
					flushAttrs.addFlashAttribute("errorMsg", "两次输入的密码不一致！");
					return "redirect:toEdit?id=" + e.getId();
				}
				e.setPassword(MD5.md5(e.getPassword()));
			}
			
			e.setUpdateAccount(user.getUsername());
			casUserService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:back";
	}

	/**
	 * ajax验证输入的字符的唯一性
	 * @return
	 * @throws IOException
	 */
    @RequestMapping("unique")
    @ResponseBody
	public String unique(@ModelAttribute("e") UserEntity e, HttpServletResponse response) throws IOException{
		logger.error("验证输入的字符的唯一性"+e);
        response.setCharacterEncoding("utf-8");
		if(StringUtils.isNotBlank(e.getNickname())){//验证昵称是否被占用
			logger.error("验证昵称是否被占用");
			UserEntity user = new UserEntity();
			user.setNickname(e.getNickname());
			user = casUserService.selectOne(user);

			if(user==null){
				//数据库中部存在此编码
				return "{\"ok\":\"昵称可以使用!\"}";
			}else{
				if(StringUtils.isNotBlank(e.getId()) && e.getId().equals(user.getId())){
					//update操作，又是根据自己的编码来查询的，所以当然可以使用啦
					return "{\"ok\":\"昵称可以使用!\"}";
				}else {
					//当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
					return "{\"error\":\"昵称已经存在!\"}";
				}
			}
		}else if(StringUtils.isNotBlank(e.getUsername())){//验证用户名是否被占用
			logger.error("验证账号是否被占用");
			UserEntity user = new UserEntity();
			user.setUsername(e.getUsername());
			user = casUserService.selectOne(user);
			if(user==null){
				//数据库中部存在此编码
				return "{\"ok\":\"账号可以使用!\"}";
			}else{
				if(StringUtils.isNotBlank(e.getId()) && e.getId().equals(user.getId())){
					//update操作，又是根据自己的编码来查询的，所以当然可以使用啦
					return "{\"ok\":\"账号可以使用!\"}";
				}else {
					//当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
					return "{\"error\":\"账号已经存在!\"}";
				}
			}
		}
		return null;
	}

	
	/**
	 * 转到修改密码页面
	 * @return
	 */
    @RequestMapping("toChangePwd")
    @ControllerLogAnnotation(description = "后台用户修改密码访问", client="0",  opt="2")
	public String toChangePwd(HttpSession session, @ModelAttribute("e") UserEntity e){
		UserEntity u = (UserEntity) session.getAttribute(ManageContainer.session_user_info);
		e.setId(u.getId());
		return page_toChangePwd;
	}

	@RequestMapping("changePwd")
	public String changePwd(){
		return page_changePwd_result;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
    @RequestMapping(value = "updateChangePwd", method = RequestMethod.POST)
    @ControllerLogAnnotation(description = "后台用户修改密码", client="0", opt="2") 
	public String updateChangePwd(@ModelAttribute("e") UserEntity e, RedirectAttributes flushAttrs) {
		if(StringUtils.isBlank(e.getNewpassword()) || StringUtils.isBlank(e.getNewpassword2())){
			addMessage(flushAttrs, "密码不能为空！");
			return "redirect:toChangePwd";
		}
		
		if(!e.getNewpassword().equals(e.getNewpassword2())){
			addError(flushAttrs, "两次输入的密码不一致！");
			return "redirect:toChangePwd";
		}
		
		UserEntity u = (UserEntity) RequestHolder.getSession().getAttribute(ManageContainer.session_user_info);
		e.setPassword(MD5.md5(e.getPassword()));
		if(!e.getPassword().equals(u.getPassword())){//用户输入的旧密码和数据库中的密码一致
			addError(flushAttrs, "原密码不正确！");
			return "redirect:toChangePwd";
		}
		
		//修改密码
		e.setPassword(MD5.md5(e.getNewpassword()));
		casUserService.update(e);
		//更新session中的用户信息
		u = casUserService.selectById(u.getId());
		RequestHolder.getSession().setAttribute(ManageContainer.session_user_info, u);
		addMessage(flushAttrs, "密码修改成功!");
		return "redirect:toChangePwd";
	}
	

    @RequestMapping("toAdd")
    @ControllerLogAnnotation(description = "添加后台用户信息访问", client="0", opt="0")
	public String toAdd(@ModelAttribute("e")UserEntity user, ModelMap model) throws Exception {
        model.addAttribute("roleList", casRoleService.selectList(new RoleEntity()));
		return page_toAdd;
	}
    
	/**
	 * 编辑用户
	 */
    @RequestMapping("toEdit")
    @ControllerLogAnnotation(description = "更新后台用户信息访问", client="0", opt="0")
	public String toEdit(@ModelAttribute("e") UserEntity e, ModelMap model) throws Exception {
        model.addAttribute("roleList", casRoleService.selectList(new RoleEntity()));
        if(StringUtils.isNotEmpty(e.getId())){
        	e = casUserService.selectOne(e);
        }
        model.addAttribute("e", e);
		return page_toEdit;
	}
	
	/**
	 * 查看管理人员信息
	 * @return
	 */
    @RequestMapping("show")
    @ControllerLogAnnotation(description = "查看后台用户信息访问", client="0", opt="0")
	public String show(@ModelAttribute("e") UserEntity e, String account, ModelMap model){
		if(StringUtils.isBlank(account)){
			throw new NullPointerException("非法请求！");
		}
		e.setUsername(account);
		e = casUserService.selectOne(e);
		model.addAttribute("e", e);
		return page_show;
	}

	/**
	 * 用户修改密码--验证旧密码是否正确
	 * @return
	 */
    @RequestMapping("checkOldPassword")
    @ResponseBody
	public String checkOldPassword(@ModelAttribute("e")UserEntity e, HttpSession session) throws Exception{
		logger.error("checkOldPassword.."+e.getPassword());
		if(StringUtils.isBlank(e.getPassword())){
			return "{\"error\":\"旧密码不能为空!\"}";
		}else{
			//检查旧密码输入的是否正确
			UserEntity user = (UserEntity)session.getAttribute(ManageContainer.session_user_info);
			String oldPass = MD5.md5(e.getPassword());
			if(user.getPassword().equals(oldPass)){
				return "{\"ok\":\"旧密码输入正确!\"}";
			}else{
				return "{\"error\":\"旧密码输入错误!\"}";
			}
		}
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
    @ControllerLogAnnotation(description = "删除后台用户信息", client="0", opt="3") 
	public String deletes(HttpServletRequest request, String[] ids, @ModelAttribute("e") UserEntity e, RedirectAttributes flushAttrs) throws Exception {
		UserEntity user = LoginUserHolder.getLoginUser();
		for (int i = 0; i < ids.length; i++) {
			UserEntity u = casUserService.selectById(ids[i]);
			if(u.getUsername().equals("admin") || u.getUsername().equals(user.getUsername())){
				addError(flushAttrs, ManageContainer.UserAction_delete_self_error);
				return "redirect:selectList";
			}
		}
		return super.deletes(request, ids, e, flushAttrs);
		
	}
}

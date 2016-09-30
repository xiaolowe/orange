package com.orange.common;


/**
 * 全局变量
 */
public class ManageContainer {
	
	public static final String Privilege_Type_Module = "module";
	public static final String Privilege_Type_Page = "page";
	public static final String Privilege_Type_Button = "button";
	
	public static final String MENU_list = "menu_list";// 菜单信息
	public static final String MENUS = "menus";
	public static final String PROJECTS = "projects";
	public static final String USERS = "users";
	public static final String loginError = "loginError";//登陆错误

	public static final int PAGE_SIZE = 10;// 默认分页条数
	
	/**
	 * 商品目录树
	 */
	public static final String typeTree = "typeTree";
	
	public static final String sessionID = "sessionID";//sessionIDKEY
	public static final String imgList = "imgList";
	public static final String resource_menus = "resource_menus";//用户资源菜单
	public static final String user_resource_menus_button = "user_resource_menus_button";//用户资源功能菜单
	public static final String SystemSetting = "SystemSetting";//资源菜单
	public static final String session_user_info = "manage_session_user_info";//用户信息
	public static String session_member_info = "session_account_info";//会员信息
	public static String session_log_user_info = "session_log_user_info";
	public static final String session_setting_info = "manage_session_setting_info";//
	public static final String session_link_info = "manage_session_link_info";//
	public static final String session_banner_info = "manage_session_banner_info";//
	//当前门户上的菜单
	public static final String current_menu = "current_menu";
	//我的二维码模块的子项菜单
	public static final String current_item_menu = "current_item_menu";
	public static final String product_images_spider = ",";//商品图片分割符
	
	
//	public static final String user_db_privilege = "user_db_privilege";//用户数据库权限
	public static final String db_privilege_select = "db_privilege_select";//数据库权限-查询
	public static final String db_privilege_insert = "db_privilege_insert";//数据库权限-添加
	public static final String db_privilege_update = "db_privilege_update";//数据库权限-修改
	public static final String db_privilege_delete = "db_privilege_delete";//数据库权限-删除
	
	public static final String db_privilege_insert_error = "权限受限：该用户没有添加数据的权限！";//数据库权限-添加-错误信息
	public static final String db_privilege_update_error = "权限受限：该用户没有修改数据的权限！";//数据库权限-修改-错误信息
	public static final String db_privilege_delete_error = "权限受限：该用户没有删除数据的权限！";//数据库权限-删除-错误信息
	
	public static final String action_db_error = "action_db_error";//action操作DB异常信息
	public static final String action_exception_error = "action_exception_error";//action异常的错误信息
	public static final String action_exception_stack_error = "action_exception_stack_error";//action异常栈的错误信息
	
	/**
	 * 数据库操作
	 */
	public static final String db_insert = "insert";
	public static final String db_update = "update";
	public static final String db_delete = "delete";
	
	public static final String not_this_method = "不支持此操作!";
	
	public static final String RoleAction_update_error = "非法操作！";
	
	public static final String RoleAction_delete_error = "非法操作！";
	
	public static final String RoleAction_delete_self_error = "操作有误,不能删除自己";
	public static final String RoleAction_delete_users_error = "操作有误,该角色有用户";
	
	public static final String UserAction_delete_error = "非法操作！";
	
	public static final String UserAction_delete_self_error = "操作有误,不能删除自己！";
	
}

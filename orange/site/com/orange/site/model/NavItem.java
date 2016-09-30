package com.orange.site.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.orange.site.entity.NavEntity;


/**
 * 页面上显示的菜单项，每一个MenuItem对应一个节点
 */
public class NavItem implements Serializable{
	private static final long serialVersionUID = -9047492062171613244L;
	
	
	private String id;// 0：根节点，否则是子节点
	private String pid;// 菜单项的父亲节点
	private String url;
	private int orders;
	private String target = "rightFrame";// 打开的目标
	private String name;// 菜单名称
	private boolean open = false;// 是否展开
	private boolean checked;// true:勾选
	private List<NavItem> children;// 子节点
	private String model;
	private String banner;
	
	public boolean isRootMenu() {
		return "0".equals(pid);
	}

	public void addClild(NavItem item) {
		if(children == null){
			children = new ArrayList<NavItem>();
		}
		children.add(item);
	}

	public List<NavItem> getChildren() {
		return children;
	}

	public void setChildren(List<NavItem> children) {
		this.children = children;
	}

	public NavItem(String name, List<NavItem> children) {
		super();
		this.name = name;
		this.children = children;
	}

	public NavItem() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	

}


package com.orange.site.action;

import com.orange.core.base.dao.page.PagerModel;
import com.orange.core.base.service.Services;
import com.orange.oscache.FrontCache;
import com.orange.site.entity.ModelEntity;
import com.orange.site.entity.NavEntity;
import com.orange.site.model.NavItem;
import com.orange.site.service.ModelService;
import com.orange.site.service.NavService;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;

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
import java.io.IOException;
import java.util.*;


/**
 * 管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/site/nav")
public class NavAction extends BaseAction<NavEntity> {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
    @Autowired
	private NavService siteNavService;
    @Autowired
   	private ModelService siteModelService;
    
    @Autowired
	private FrontCache frontCache;
    
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(NavAction.class);
    private static final String page_toList = Commons.SCOPE_MANAGE + "/site/nav/list";
    private static final String page_toEdit = null;
    private static final String page_addOrUpdate = Commons.SCOPE_MANAGE + "/site/nav/addOrUpdate";

    public NavAction() {
        super.page_toList = page_toList;
        super.page_toEdit = page_toEdit;
        super.page_toAdd = page_toEdit;
    }
    
	@Override
    public Services<NavEntity> getService() {
        return siteNavService;
    }
    
    @Override
	protected void selectListAfter(PagerModel pager) {
		pager.setPagerUrl("selectList");
	}
	/**
	 * 转到 添加/修改分类 页面
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("toAddOrUpdate")
	public String toAddOrUpdate(@ModelAttribute("e") NavEntity e, ModelMap model) throws Exception{
		e = siteNavService.selectOne(e);
        model.addAttribute("e", e);
        model.addAttribute("models", siteModelService.selectList(new ModelEntity(ModelEntity.S_YES)));
		return page_addOrUpdate;
	}
	/**
	 * 添加/修改分类
	 * 修改选中的分类，为该分类添加子分类
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addOrUpdate", method = RequestMethod.POST)
    @ResponseBody
	public String addOrUpdate(HttpServletRequest request) throws Exception{
		//选中分类的信息
		String updateP = request.getParameter("updateP");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String model = request.getParameter("model");
		String orderNum = request.getParameter("orderNum");
		String banner = request.getParameter("banner");
		
		//要添加的子分类
		String url = request.getParameter("url");
		String n_name = request.getParameter("n_name");
		String n_url = request.getParameter("n_url");
		String n_model = request.getParameter("n_model");
		String parentOrChild = request.getParameter("parentOrChild");
		String n_orderNum = request.getParameter("n_orderNum");
		String n_banner = request.getParameter("n_banner");
		
		NavEntity itemNav = null;
		if(n_name!=null && !n_name.trim().equals("")){
			itemNav = new NavEntity();
			//添加子分类
			if(parentOrChild.equals("0")){
				itemNav.setPid("0");
			} else if(parentOrChild.equals("1")){
				itemNav.setPid(id);
			} else {
				throw new IllegalAccessException("添加导航异常。");
			}
			itemNav.setName(n_name);
			itemNav.setOrders(Integer.valueOf(n_orderNum));
			itemNav.setUrl(n_url);
			itemNav.setModel(n_model);
			itemNav.setBanner(n_banner);
		}
		//修改父分类
		NavEntity m = new NavEntity();
		m.setId(id);
		m.setName(name);
		m.setOrders(Integer.valueOf(orderNum));
		m.setUrl(url);
		m.setModel(model);
		m.setBanner(banner);
		
		
		siteNavService.addOrUpdate(updateP,m, itemNav);
		
		return "ok";
	}
	
	
	/**
	 * 从PID=0开始加载分类资源
	 * 获取指定节点的全部子分类（包括当前分类节点）
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("getNavsByPid")
    @ResponseBody
	public String getNavsByPid(HttpServletRequest request) throws Exception {
		String pid = request.getParameter("pid");
		if(pid==null || pid.trim().equals(""))
			pid = "0";
		List<NavItem> navs = siteNavService.loadNavs(pid);
		return writeNavs(navs);
	}
	
	//输出分类到页面
	private String writeNavs(List<NavItem> root) throws IOException{
		JSONArray json = JSONArray.fromObject(root);
		String jsonStr = json.toString();
		try {
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

    @RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("e") NavEntity e) throws Exception {
		if (e.getId() == null || e.getId().equals("")) {
			siteNavService.insert(e);
		} else {
			siteNavService.update(e);
		}
		return "redirect:selectList";
	}

	/**
	 * 删除分类
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
	public String delete(HttpServletRequest request) throws Exception {
		
		String ids = request.getParameter("ids");
		if(ids==null || ids.trim().equals(""))
				throw new Exception("删除导航异常！");
		
		this.siteNavService.deletes(ids,request.getParameter("deleteParent"));
		
		//删除成功返回1
		return "1";
	}

	@Override
	public void insertAfter(NavEntity e) {
		// TODO Auto-generated method stub
		e.clear();
	}
	
	@RequestMapping("statics")
	@ResponseBody
    public String statics() {
		if(frontCache.getInstance().loadNav()!=1){
			return "0";
		}
		return "1";// "redirect:back";
	}
	
}

package com.orange.web.action.base;

import com.orange.core.annotation.ControllerLogAnnotation;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.core.base.service.Services;
import com.orange.oscache.SystemManager;
import com.orange.web.action.holder.RequestHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseAction<E extends PagerModel> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected String page_toList = null;
    protected String page_toEdit = null;
    protected String page_toAdd = null;
    public abstract Services<E> getService();
    
    @Autowired
    protected SystemManager systemManager;

    /**
     * 后台左边导航菜单的初始化查询方法
     */
    protected void initPageSelect() {
        logger.error("initPageSelect..init=n!");
    }

    /**
     * 初始化查询的时候，会清除所有的查询参数(所以在e中的)，但可以设置不在e中的参数，然后在此方法中进行e.setXxx(参数)的方式进行保留。
     */
    protected void setParamWhenInitQuery(E e) {
        //BaseAction 的子类如有初始化页面的时候进行相关查询 ，则可以实现此方法。
    }

    /**
     * 公共的分页方法
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("selectList")
    @ControllerLogAnnotation(description = "列表查询", client="0",  opt="4")
    public String selectList(HttpServletRequest request, @ModelAttribute("e") E e) throws Exception {
        /**
         * 由于prepare方法不具备一致性，加此代码解决init=y查询的时候条件不被清除干净的BUG
         */
        this.initPageSelect();

        setParamWhenInitQuery(e);

        int offset = 0;//分页偏移量
        if (request.getParameter("pager.offset") != null) {
            offset = Integer
                    .parseInt(request.getParameter("pager.offset"));
        }
        if (offset < 0)
            offset = 0;
        e.setOffset(offset);
        PagerModel pager = getService().selectPageList(e);
        if (pager == null) {
            pager = new PagerModel();
        }
        // 计算总页数
        pager.setPagerSize((pager.getTotal() + pager.getPageSize() - 1)
                / pager.getPageSize());

        selectListAfter(pager);
        request.setAttribute("pager", pager);
        request.setAttribute("e", e);
        return page_toList;
    }

    @RequestMapping("toEdit")
    @ControllerLogAnnotation(description = "编辑界面访问", client="0",  opt="0")
    public String toEdit(@ModelAttribute("e") E e, ModelMap model) throws Exception {
        e = getService().selectOne(e);
        model.addAttribute("e", e);
        return page_toEdit;
    }

    @RequestMapping("toAdd")
    @ControllerLogAnnotation(description = "添加界面访问", client="0",  opt="0")
    public String toAdd(@ModelAttribute("e") E e, ModelMap model) throws Exception {
        e.clear();
        return page_toAdd;
    }

    /**
     * 子类必须要实现的方法当分页查询后.
     * 解决了用户先点击新增按钮转到新增页面,然后点击返回按钮返回后,再点分页控件出错的BUG.
     * 原因是分页查询后未将pageUrl重新设置为正确的URL所致
     */
    protected void selectListAfter(PagerModel pager) {
        pager.setPagerUrl("selectList");
    }

    /**
     * 返回到查询页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("back")
    public String back(@ModelAttribute("e") E e, ModelMap model) throws Exception {
        return selectList(RequestHolder.getRequest(), e);
    }

    /**
     * 公共的批量删除数据的方法，子类可以通过重写此方法实现个性化的需求。
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deletes", method = RequestMethod.POST)
    @ControllerLogAnnotation(description = "数据删除", client="0",  opt="3")
    public String deletes(HttpServletRequest request, String[] ids, @ModelAttribute("e") E e, RedirectAttributes flushAttrs) throws Exception {
        getService().deletes(ids);
        addMessage(flushAttrs, "操作成功！");
        return "redirect:selectList";
    }

    /**
     * 公共的更新数据的方法，子类可以通过重写此方法实现个性化的需求。
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ControllerLogAnnotation(description = "数据更新", client="0",  opt="2")
    public String update(HttpServletRequest request, @ModelAttribute("e") E e, RedirectAttributes flushAttrs) throws Exception {
        getService().update(e);
        insertAfter(e);
        addMessage(flushAttrs, "操作成功！");
        return "redirect:selectList";
    }

    /**
     * insert之后，selectList之前执行的动作，一般需要清除添加的E，否则查询会按照E的条件进行查询.
     * 部分情况下需要保留某些字段，可以选择不清除
     *
     * @param e
     */
    protected void insertAfter(E e){
    }

    /**
     * 公共的插入数据方法，子类可以通过重写此方法实现个性化的需求。
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    @ControllerLogAnnotation(description = "数据添加", client="0",  opt="1")
    public String insert(HttpServletRequest request, @ModelAttribute("e") E e, RedirectAttributes flushAttrs) throws Exception {
        getService().insert(e);
        insertAfter(e);
        addMessage(flushAttrs, "操作成功！");
        return "redirect:selectList";
    }

    @RequestMapping("loadData")
    @ResponseBody
    @ControllerLogAnnotation(description = "数据加载请求", client="0",  opt="4")
    public PagerModel loadData(HttpServletRequest request, E e){
        int offset = 0;
        int pageSize = 10;
        if (request.getParameter("start") != null) {
            offset = Integer
                    .parseInt(request.getParameter("start"));
        }
        if (request.getParameter("length") != null) {
            pageSize = Integer
                    .parseInt(request.getParameter("length"));
        }
        if (offset < 0)
            offset = 0;
        if(pageSize < 0){
            pageSize = 10;
        }
        e.setOffset(offset);
        e.setPageSize(pageSize);
        PagerModel pager = getService().selectPageList(e);
        pager.setRecordsTotal(pager.getTotal());
        pager.setRecordsFiltered(pager.getTotal());
        return pager;
    }
    protected void addMessage(ModelMap modelMap, String message) {
        modelMap.addAttribute("message", message);
    }
    protected void addWarning(ModelMap modelMap, String warning) {
        modelMap.addAttribute("warning", warning);
    }
    protected void addError(ModelMap modelMap, String warning) {
        modelMap.addAttribute("errorMsg", warning);
    }
    protected void addMessage(RedirectAttributes flushAttrs, String message) {
        flushAttrs.addFlashAttribute("message", message);
    }
    protected void addWarning(RedirectAttributes flushAttrs, String warning) {
        flushAttrs.addFlashAttribute("warning", warning);
    }
    protected void addError(RedirectAttributes flushAttrs, String warning) {
        flushAttrs.addFlashAttribute("errorMsg", warning);
    }
}

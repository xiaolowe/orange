package com.orange.front.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.icu.text.SimpleDateFormat;
import com.orange.cas.entity.MemberEntity;
import com.orange.cas.service.MemberService;
import com.orange.common.ManageContainer;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.core.util.MD5;
import com.orange.news.entity.ArticleEntity;
import com.orange.news.entity.RaceEntity;
import com.orange.news.service.ArticleService;
import com.orange.news.service.RaceService;
import com.orange.race.entity.RaceAddonEntity;
import com.orange.race.entity.RaceAddonManEntity;
import com.orange.race.entity.RaceInfoEntity;
import com.orange.race.service.RaceAddonManService;
import com.orange.race.service.RaceAddonService;
import com.orange.race.service.RaceGroupService;
import com.orange.race.service.RaceInfoService;
import com.orange.statics.entity.ContentEntity;
import com.orange.statics.service.CatelogService;
import com.orange.statics.service.ContentService;
import com.orange.util.StringUtil;
import com.orange.web.action.common.statics.Commons;
import com.orange.web.action.holder.LoginUserHolder;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/front/")
public class IndexAction
{
    private static final Logger logger = LoggerFactory.getLogger(IndexAction.class);
    
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CatelogService staticsCatelogService;
    
    @Autowired
    private ContentService staticsContentService;
    
    @Autowired
    private RaceInfoService raceInfoService;
    
    @Autowired
    private RaceAddonService raceAddonService;
    
    @Autowired
    private RaceAddonManService raceAddonManService;
    
    @Autowired
    private ArticleService newsArticleService;
    
    @Autowired
    private RaceService newsRaceService;
    
    @Autowired
    private MemberService casMemberService;
    
    @Autowired
    private RaceGroupService raceGroupService;
    
    private static final String m_index = "index";
    
    private static final String page_index = Commons.SCOPE_FRONT + "/" + m_index;
    
    private static final String m_pages = "pages";
    
    private static final String page_static = Commons.SCOPE_FRONT + "/" + m_pages;
    
    private static final String m_news = "news";
    
    private static final String page_news = Commons.SCOPE_FRONT + "/" + m_news;
    
    private static final String m_races = "races";
    
    private static final String page_races = Commons.SCOPE_FRONT + "/" + m_races;
    
    private static final String m_races_score = "score";
    
    private static final String page_races_score = Commons.SCOPE_FRONT + "/" + m_races_score;
    
    private static final String m_news_list = "news_list";
    
    private static final String page_news_list = Commons.SCOPE_FRONT + "/" + m_news_list;
    
    private static final String m_races_list = "races_list";
    
    private static final String page_races_list = Commons.SCOPE_FRONT + "/" + m_races_list;
    
    private static final String m_race_addon = "/member/addon";
    
    private static final String page_race_addon = Commons.SCOPE_FRONT + "/" + m_race_addon;
    
    private static final String m_race_addon_info = "/member/addon_info";
    
    private static final String page_race_addon_info = Commons.SCOPE_FRONT + "/" + m_race_addon_info;
    
    private static final String m_cas_login = "/member/login";
    
    private static final String page_cas_login = Commons.SCOPE_FRONT + m_cas_login;
    
    private static final String m_cas_index = "/member/index";
    
    private static final String page_cas_index = Commons.SCOPE_FRONT + m_cas_index;
    
    /**
     * 门户首页
     * 
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String page_index(ModelMap model)
    {
        // TODO Auto-generated method stub
        List<RaceInfoEntity> races = raceInfoService.selectListTop3(new RaceInfoEntity(RaceInfoEntity.S_YES));
        model.addAttribute("races", races);
        if (races.size() > 0)
        {
            model.addAttribute("race", races.size() > 0 ? races.get(0) : null);
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try
            {
                date = simpleDateFormat.parse(races.get(0).getTime());
            }
            catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            model.addAttribute("end", date.getTime() / 1000);
            model.addAttribute("now", System.currentTimeMillis() / 1000);
        }
        else
        {
            model.addAttribute("end", 0);
            model.addAttribute("now", 0);
            model.addAttribute("race", null);
        }
        
        model.addAttribute("newsA", newsArticleService.selectList(new ArticleEntity(ArticleEntity.S_P)));
        model.addAttribute("newsR", newsRaceService.selectList(new RaceEntity(RaceEntity.S_P)));
        
        return page_index;
    }
    
    /**
     * 静态页面详细
     * 
     * @param catId
     * @param cId
     * @param model
     * @return
     */
    @RequestMapping("pages/{catId}/{cId}")
    public String page_pages(@PathVariable String catId, @PathVariable String cId, ModelMap model)
    {
        // TODO Auto-generated method stub
        List<ContentEntity> ctes = staticsContentService.selectList(new ContentEntity(catId));
        model.addAttribute("catalogs", ctes);
        model.addAttribute("catelogId", catId);
        model.addAttribute("cte", staticsContentService.selectById(cId));
        model.addAttribute("cate", staticsCatelogService.selectById(catId));
        
        return page_static;
    }
    
    /**
     * 新闻详细
     * 
     * @param type
     * @param nId
     * @param model
     * @return
     */
    @RequestMapping("s/{type}/{nId}")
    public String page_news_info(@PathVariable String type, @PathVariable String nId, ModelMap model)
    {
        // TODO Auto-generated method stub
        if (type.equals("r"))
        {
            RaceEntity race = newsRaceService.selectById(nId);
            int num = race.getNum();
            race.setNum(num++);
            newsRaceService.update(race);
            model.addAttribute("news", race);
            model.addAttribute("pre", newsRaceService.selectPreBy(race));
            model.addAttribute("next", newsRaceService.selectNextBy(race));
        }
        if (type.equals("n"))
        {
            ArticleEntity art = newsArticleService.selectById(nId);
            int num = art.getNum();
            art.setNum(num++);
            newsArticleService.update(art);
            model.addAttribute("news", art);
            model.addAttribute("pre", newsArticleService.selectPreBy(art));
            model.addAttribute("next", newsArticleService.selectNextBy(art));
        }
        model.addAttribute("type", type);
        
        return page_news;
    }
    
    /**
     * 赛事详细
     * 
     * @param type :events:最新赛事，cases:赛事案例；scores:成绩
     * @param rId
     * @param model
     * @return
     */
    @RequestMapping("r/{type}/{rId}")
    public String page_race_info(@PathVariable String type, @PathVariable String rId,
        @RequestParam(required = false) String d, ModelMap model)
    {
        // TODO Auto-generated method stub
        String page = page_races;
        model.addAttribute("raceId", rId);
        if (type.equals("scores"))
        {
            model.addAttribute("race", raceInfoService.selectById(rId));
            page = page_races_score;
        }
        else
        {
            model.addAttribute("race", raceInfoService.selectFrontRaceById(rId).getList().get(0));
            
            // 报名组别信息
            model.addAttribute("groups", raceGroupService.selectListByRaceId(rId));
            
            page = page_races;
        }
        model.addAttribute("type", type);
        model.addAttribute("d", d);
        return page;
    }
    
    /**
     * 赛事报名页
     * 
     * @param rId
     * @param model
     * @return
     */
    @RequestMapping("race/addon/{rId}/{gId}")
    public String page_addon(@PathVariable String rId, @PathVariable String gId, ModelMap model)
    {
        String page = page_race_addon;
        
        model.addAttribute("race", raceInfoService.selectFrontRaceById(rId).getList().get(0));
        model.addAttribute("group", raceGroupService.selectById(gId));
        
        return page;
    }
    
    /**
     * 报名后详细
     * 
     * @param rId
     * @param model
     * @return
     */
    @RequestMapping("race/addon/info/{aId}/{teamId}")
    public String page_addon_info(@PathVariable String aId, @PathVariable String teamId,
        @RequestParam(required = false) String d, ModelMap model)
    {
        String page = page_race_addon_info;
        
        RaceAddonEntity e = new RaceAddonEntity();
        e.setId(aId);
        model.addAttribute("race", raceAddonService.selectPageList(e).getList()
                .get(0));
        
        model.addAttribute("mans", raceAddonManService.selectList(new RaceAddonManEntity(teamId)));
        return page;
    }
    
    /**
     * 资讯列表
     * 
     * @param type
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("news/{type}")
    public String page_list(@PathVariable String type, ModelMap model, HttpServletRequest request)
    {
        // TODO Auto-generated method stub
        String result = null;
        int offset = 0;// 分页偏移量
        if (request.getParameter("pager.offset") != null)
        {
            offset = Integer.parseInt(request.getParameter("pager.offset"));
        }
        if (offset < 0)
            offset = 0;
        // 计算总页数
        if (type.equals("r"))
        {
            RaceEntity race = new RaceEntity();
            race.setState(RaceEntity.S_P);
            race.setOffset(offset);
            race.setPageSize(4);
            PagerModel pager = newsRaceService.selectPageList(race);
            pager.setPageSize(4);
            pager.setPagerSize((pager.getTotal() + pager.getPageSize() - 1) / pager.getPageSize());
            pager.setPagerUrl("news/" + type + "/list.html");
            request.setAttribute("pager", pager);
            result = page_news_list;
        }
        if (type.equals("n"))
        {
            ArticleEntity art = new ArticleEntity();
            art.setState(ArticleEntity.S_P);
            art.setOffset(offset);
            art.setPageSize(4);
            PagerModel pager = newsArticleService.selectPageList(art);
            pager.setPageSize(4);
            pager.setPagerSize((pager.getTotal() + pager.getPageSize() - 1) / pager.getPageSize());
            pager.setPagerUrl("news/" + type + "/list.html");
            request.setAttribute("pager", pager);
            result = page_news_list;
        }
        model.addAttribute("type", type);
        return result;
    }
    
    /**
     * 赛事列表
     * 
     * @param type
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("races/{type}/list")
    public String page_races(@PathVariable String type, ModelMap model, HttpServletRequest request)
    {
        // TODO Auto-generated method stub
        String result = null;
        int offset = 0;// 分页偏移量
        if (request.getParameter("pager.offset") != null)
        {
            offset = Integer.parseInt(request.getParameter("pager.offset"));
        }
        if (offset < 0)
            offset = 0;
        PagerModel pager = null;
        // 计算总页数
        RaceInfoEntity races = new RaceInfoEntity();
        races.setStatus(RaceEntity.S_P);
        races.setOffset(offset);
        races.setPageSize(3);
        if (type.equals("events"))
        {
            races.setType(RaceInfoEntity.T_UNFINISHED);
        }
        if (type.equals("cases"))
        {
            races.setType(RaceInfoEntity.T_FINISHED);
        }
        if (type.equals("scores"))
        {
            races.setType(RaceInfoEntity.T_FINISHED);
        }
        pager = raceInfoService.selectPageList(races);
        pager.setPageSize(3);
        pager.setPagerSize((pager.getTotal() + pager.getPageSize() - 1) / pager.getPageSize());
        pager.setPagerUrl("races/" + type + "/list.html");
        request.setAttribute("pager", pager);
        request.setAttribute("type", type);
        result = page_races_list;
        return result;
    }
    
    /**
     * 登录页
     * 
     * @param session
     * @param from
     * @param model
     * @return
     */
    @RequestMapping(value = "m/toLogin")
    public String page_login(HttpSession session, @ModelAttribute("e") MemberEntity e,
        @RequestParam(required = false) String from, ModelMap model)
    {
        String page = page_cas_login;
        model.addAttribute("e", e);
        if (session.getAttribute(ManageContainer.session_member_info) != null)
        {
            String redirect = "redirect:/member/home.html";
            return redirect;
        }
        return page;
    }
    
    /**
     * 个人中心
     * 
     * @param model
     * @param from
     * @return
     */
    @RequestMapping(value = "m/home")
    public String page_home(ModelMap model, @RequestParam(required = false) String from,
        @ModelAttribute("e") MemberEntity e)
    {
        model.addAttribute("e", e);
        String page = page_cas_index;
        if (LoginUserHolder.getLoginMember() == null)
        {
            return page_cas_login;
        }
        if (StringUtil.isNotEmptyString(from) && !from.equals("null") && !from.equals("login"))
        {
            return "redirect:" + from;
        }
        MemberEntity m = LoginUserHolder.getLoginMember();
        
        RaceAddonEntity addon = new RaceAddonEntity();
        addon.setmId(m.getId());
        addon.setPagerSize(1000);
        
        model.addAttribute("races", raceAddonService.selectPageList(addon));
        return page;
    }
    
    /**
     * 登录操作
     * 
     * @param request
     * @param e
     * @param from
     * @param response
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "m/login")
    public String opt_login(HttpServletRequest request, @ModelAttribute("e") MemberEntity e,
        @RequestParam(required = false) String from, @RequestParam(required = false) String d,
        HttpServletResponse response, HttpSession session, ModelMap model)
    {
        System.out.println("from=" + from);
        if (StringUtils.isBlank(e.getMobile()) || StringUtils.isBlank(e.getPassword()))
        {
            logger.error("帐号和密码不能为空!");
            model.addAttribute("errorMsg", "账户和密码不能为空!");
            e.clear();
            return page_cas_login;
        }
        else
        {
            MemberEntity u = casMemberService.selectOne(e);
            if (u == null)
            {
                logger.error("该帐号不存在,{}", e.getAccount());
                model.addAttribute("errorMsg", "该账户不存在，请确认");
                e.clear();
                return page_cas_login;
            }
            else
            {
                if (u.getStatus().equals(MemberEntity.S_NO))
                {
                    logger.error("帐号已被禁用，请联系管理员,{}", u.getAccount());
                    model.addAttribute("errorMsg", "帐号已被禁用，请联系管理员");
                    e.setPassword(null);
                    return page_cas_login;
                }
                else if (!u.getPassword().equals(MD5.md5(e.getPassword())))
                {
                    logger.error("登陆失败，账户或密码错误,{}", u.getAccount());
                    model.addAttribute("errorMsg", "登陆失败，账户或密码错误");
                    e.setPassword(null);
                    return page_cas_login;
                }
                else
                {
                    e.setPassword(null);
                    session.setAttribute(ManageContainer.session_member_info, u);
                    return "redirect:/member/home.html?from=" + from;
                }
            }
        }
    }
    
    /**
     * 注册操作
     * 
     * @param request
     * @param e
     * @param response
     * @param session
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "m/regist")
    public String opt_regist(HttpServletRequest request, @ModelAttribute("e") MemberEntity e,
        @RequestParam(required = false) String from, HttpServletResponse response, HttpSession session, ModelMap model)
        throws Exception
    {
        if (StringUtils.isBlank(e.getMobile()) || StringUtils.isBlank(e.getPassword()))
        {
            logger.error("帐号和密码不能为空!");
            model.addAttribute("errorMsg", "帐号和密码不能为空!");
            return page_cas_login;
        }
        else
        {
            MemberEntity eu = new MemberEntity();
            eu.setMobile(e.getMobile());
            MemberEntity u = casMemberService.selectOne(eu);
            if (u == null)
            {
                e.setPassword(MD5.md5(e.getPassword()));
                e.setStatus(MemberEntity.S_YES);
                casMemberService.insert(e);
                session.setAttribute(ManageContainer.session_member_info, e);
                return "redirect:/member/home.html?from=" + from;
            }
            else
            {
                if (u.getStatus().equals(MemberEntity.S_NO))
                {
                    logger.error("帐号已被禁用，请联系管理员,{}", u.getAccount());
                    model.addAttribute("errorMsg", "帐号已被禁用，请联系管理员");
                    return page_cas_login;
                }
                else
                {
                    logger.error("帐号已注册，请勿重复注册,{}", u.getAccount());
                    model.addAttribute("errorMsg", "帐号已注册，请勿重复注册");
                    return page_cas_login;
                }
            }
        }
    }
    
    /**
     * 会员检查
     * 
     * @param e
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("m/check")
    @ResponseBody
    public String ajax_checkMember(@ModelAttribute("e") MemberEntity e, HttpSession session)
        throws Exception
    {
        logger.error("checkMember.." + e.getMobile());
        if (StringUtils.isBlank(e.getMobile()))
        {
            return "{\"error\":\"帐号不能为空!\"}";
        }
        else
        {
            MemberEntity m = new MemberEntity();
            m.setMobile(e.getMobile());
            m = casMemberService.selectOne(m);
            // 检查旧密码输入的是否正确
            if (m == null)
            {
                return "{\"ok\":\"该帐号可以使用!\"}";
            }
            else
            {
                if (m.getStatus().equals(MemberEntity.S_NO))
                {
                    return "{\"error\":\"该帐号已被禁用!\"}";
                }
                else
                {
                    return "{\"error\":\"该帐号已被使用!\"}";
                }
            }
        }
    }
    
}

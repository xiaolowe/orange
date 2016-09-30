package com.orange.race.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.orange.core.base.service.Services;
import com.orange.race.entity.RaceAddonEntity;
import com.orange.race.entity.RaceAddonManEntity;
import com.orange.race.entity.RaceInfoEntity;
import com.orange.race.service.RaceAddonManService;
import com.orange.race.service.RaceAddonService;
import com.orange.race.service.RaceAddonTeamService;
import com.orange.race.service.RaceInfoService;
import com.orange.utils.component.ExcelUtil;
import com.orange.web.action.base.BaseAction;
import com.orange.web.action.common.statics.Commons;

@Controller
@RequestMapping("/race/addon")
public class RaceAddonAction extends BaseAction<RaceAddonEntity> {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(RaceAddonAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Autowired
	private RaceAddonService raceAddonService;
	
	@Autowired
	private RaceInfoService raceInfoService;
	
	@Autowired
	private RaceAddonManService raceAddonManService;
	
	@Autowired
	private RaceAddonTeamService raceAddonTeamService;

	private static final String page_toList = Commons.SCOPE_MANAGE + "/race/addon/list";
	private static final String page_toAdd = Commons.SCOPE_MANAGE + "/race/addon/edit";
	private static final String page_toEdit = Commons.SCOPE_MANAGE + "/race/addon/edit";
	private static final String page_toShow = Commons.SCOPE_MANAGE + "/race/addon/man/list";
	private static final String page_toScore = Commons.SCOPE_MANAGE + "/race/addon/score/list";

	public RaceAddonAction() {
		super.page_toList = page_toList;
		super.page_toAdd = page_toAdd;
		super.page_toEdit = page_toEdit;
	}

	@Override
	public Services<RaceAddonEntity> getService() {
		return raceAddonService;
	}
	
	@Override
	@RequestMapping("selectList")
	public String selectList(HttpServletRequest request,
			@ModelAttribute("e") RaceAddonEntity e) throws Exception {
		request.setAttribute("races", raceInfoService.selectList(new RaceInfoEntity(RaceInfoEntity.S_YES)));
		return super.selectList(request, e);
	}
	
	@RequestMapping("selectByRaceId")
	public String selectByRaceId(HttpServletRequest request,ModelMap model,
			@RequestParam(required=true) String raceId) throws Exception {
		model.addAttribute("races", raceInfoService.selectList(new RaceInfoEntity(RaceInfoEntity.S_YES)));
		RaceAddonEntity e = new RaceAddonEntity();
		e.setRaceId(raceId);
		return super.selectList(request, e);
	}
	
	@Override
    @RequestMapping("toAdd")
	public String toAdd(@ModelAttribute("e") RaceAddonEntity e, ModelMap model)
			throws Exception {
		return page_toAdd;
	}
	
	@Override
	@RequestMapping("toEdit")
	public String toEdit(@ModelAttribute("e") RaceAddonEntity e, ModelMap model)
			throws Exception {
		model.addAttribute("e", raceAddonService.selectById(e.getId()));
		return page_toEdit;
	}

	
	@Override
	@RequestMapping("insert")
	public String insert(HttpServletRequest request,
			@ModelAttribute("e") RaceAddonEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	@Override
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			@ModelAttribute("e") RaceAddonEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		return save(e, flushAttrs);
	}

	private String save(@ModelAttribute("e")RaceAddonEntity e, RedirectAttributes flushAttrs)
			throws Exception {
		if (StringUtils.isBlank(e.getId())) {// 添加
			raceAddonService.insert(e);
		} else {// 修改
			raceAddonService.update(e);
		}
		flushAttrs.addFlashAttribute("message", "操作成功!");
		return "redirect:selectList";
	}
	
	@RequestMapping("show")
	public String show(@RequestParam(required = true) String teamId, ModelMap model)
			throws Exception {
		model.addAttribute("mans", raceAddonManService.selectList(new RaceAddonManEntity(teamId)));
		return page_toShow;
	}
	
	@RequestMapping("scores")
	public String scores(@RequestParam(required = true) String raceId,@RequestParam(required = false) String isTeam, ModelMap model)
			throws Exception {
		if(StringUtils.isNotEmpty(isTeam) && isTeam.equals("1")){
			model.addAttribute("mans", raceAddonTeamService.selectListByRaceId(raceId).getList());
		}else{
			model.addAttribute("mans", raceAddonManService.selectListByRaceId(raceId).getList());	
		}
		model.addAttribute("races", raceInfoService.selectList(new RaceInfoEntity(RaceInfoEntity.S_YES)));
		model.addAttribute("raceId", raceId);
		return page_toScore;
	}
	
	@RequestMapping("cancel")
	@ResponseBody
    public String cancel(HttpServletRequest request, String ids, String teamId,
			RedirectAttributes flushAttrs, HttpServletResponse response) {
		RaceAddonManEntity ram = new RaceAddonManEntity(teamId);
		raceAddonManService.delete(ram);
		raceAddonTeamService.deleteById(teamId);
		raceAddonService.deleteById(ids);
		return "1";// "redirect:back";
	}
   
	/**
	 * 下载报名
	 * 重载方法
	 * @param request
	 * @param e
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("downloadList")
    public void downloadList(HttpServletRequest request, HttpServletResponse response,
        @ModelAttribute("e") RaceAddonEntity e)
        throws Exception
    {
        
        List addonList = raceAddonService.selectAddonList(e);
        
        // 构建报名excel
        // 列名
        String columnNames[] = {"赛事名称", "报名时间", "报名人", "联系方式", "报名分组", "报名人数", "报名费用", "支付金额", "支付状态"};
        // map中的key
        String keys[] = {"rName", "createTime", "contactor", "mobile", "gType", "num", "rPrice", "rPriceTotal", "status"};
        
        String fileName = "报名名单";
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("报名名单");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < columnNames.length; i++)
        {
            sheet.setColumnWidth((short)i, (short)(35.7 * 150));
        }
        
        // 创建第一行
        Row row = sheet.createRow((short)0);
        
        // 设置列名
        for (int i = 0; i < columnNames.length; i++)
        {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(ExcelUtil.getCs1(wb));
        }
        
        // 设置每行每列的值
        for (short i = 0; i < addonList.size(); i++)
        {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(i+1);
            Map<String, Object> addonMap = (HashMap<String, Object>)addonList.get(i);
            // 在row行上创建一个方格
            for (short j = 0; j < keys.length; j++)
            {
                
                Cell cell = row1.createCell(j);
                int gType = (Integer)addonMap.get("gType");
                
                String key = keys[j];
                if (key.equals("contactor"))
                {
                    
                    cell.setCellValue(gType == 2? "【" + addonMap.get("tName") + "】" + String.valueOf(addonMap.get("contactor")):String.valueOf(addonMap.get("contactor")));
                    
                } else if (key.equals("gType"))
                {
                    String typeName = gType == 1 ? "个人" : "团队";
                    
                    cell.setCellValue("【" + typeName + "】" + addonMap.get("gName"));
                    
                }
                else if (key.equals("rPriceTotal"))
                {
                    int rPrice = (Integer)addonMap.get("rPrice");
                    long num = (Long)addonMap.get("num");
                    
                    cell.setCellValue(rPrice * num);
                    
                }
                else if (key.equals("status"))
                {
                    String status = String.valueOf(addonMap.get("status"));
                    cell.setCellValue(status.equals("1") ? "已支付" : "未支付");
                    
                }
                else
                {
                    cell.setCellValue(String.valueOf(addonMap.get(keys[j])));
                }
                
                cell.setCellStyle(ExcelUtil.getCs2(wb));
            }
        }
        
        wb.write(os);
        
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="
            + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
            {
                bos.write(buff, 0, bytesRead);
            }
        }
        catch (final IOException ioe)
        {
            throw ioe;
        }
        finally
        {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        
    }
}

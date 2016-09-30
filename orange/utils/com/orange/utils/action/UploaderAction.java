package com.orange.utils.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.orange.util.StringUtil;
import com.orange.web.action.common.JsonUtil;
import com.orange.web.action.common.entity.ResultJson;
import com.orange.web.action.common.entity.UploadPicEntity;
import com.orange.web.action.common.statics.Commons;

/**
 * 文件上传
 * 
 * 接口地址：http://host:port/orange/common/uploader.do
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/common/uploader.do")
public class UploaderAction {
	
	
	/**
	 * 服务端上传接口
	 * 
	 * @param type		上传对象分类
	 */
	@RequestMapping(params = "method=suploader")
	public void uploaderFromServer(HttpServletRequest request,
			@RequestParam(required = false) String fileId,
			@RequestParam(required = false) String type,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String parentDir = "../";
		String basePathDir = "files/";
		if(StringUtil.isNotEmptyString(type)){
			basePathDir = basePathDir + type;
		}else{
			basePathDir = basePathDir + "others";
		}
		String dirPath = "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
		String pathDir =  parentDir + basePathDir + dirPath;
		
		/** 得到图片保存目录的真实路径 **/
		String realPathDir = request.getSession().getServletContext()
				.getRealPath(pathDir);
		/** 根据真实路径创建目录 **/
		File saveFile = new File(realPathDir);
		if (!saveFile.exists())
			saveFile.mkdirs();
		
		MultipartFile multipartFile = multipartRequest.getFile(fileId);
		/** 获取文件的后缀 **/
		String _suffix = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf("."));
		/** 拼成完整的文件保存路径加文件 **/
		String name = System.currentTimeMillis() + _suffix;
		if (StringUtil.isNotEmptyString(name)) {
			String fileName = realPathDir + File.separator + name;
			File file = new File(fileName);
			try {
				multipartFile.transferTo(file);
				String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
				url = url + "/" + basePathDir + dirPath;
				url = url.replace("\\", "/");
				String json = new Gson().toJson(url + name);
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print(json);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(pathDir + name);
		}
	}
	
	/**
	 * APP端BASE64编码上传接口
	 * 
	 * @param type			上传分类：用户头像：avator；提问：ask；申请专家：expert；申请店铺：shop；添加商品：goods
	 * @param pic			通过base64编码的JSON
	 */
	@RequestMapping(params = "method=auploader")
	public void submitAvatar(
			HttpServletRequest request,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String pic,//图片信息
			HttpServletResponse response) throws IOException {
		ResultJson result = new ResultJson();
		List<UploadPicEntity> pics = new ArrayList<UploadPicEntity>();
		if(StringUtil.isNotEmptyString(pic)){
			String parentDir = "../";
			String basePathDir = "files/apps";
			if(StringUtil.isNotEmptyString(type)){
				basePathDir = basePathDir + "/" + type;
			}else{
				basePathDir = basePathDir + "/" + "others";
			}
			String dirPath = "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
			String pathDir =  parentDir + basePathDir + dirPath;
			//实际路径
			String logoRealPathDir = request.getSession().getServletContext().getRealPath(pathDir);
			
			//网络路径前缀
			String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			url = url + "/" + basePathDir + dirPath;
			url = url.replace("\\", "/");
			
			if(!pic.equals("")){
				JSONArray picInfoA = JSONObject.parseArray(pic);
				for(int i=0;i<picInfoA.size();i++){
					JSONObject jInfo = picInfoA.getJSONObject(i);
					String tempPicName = jInfo.getString("name");
					String tempContent = jInfo.getString("content");
					String picname = picResolve(tempPicName,tempContent,logoRealPathDir);
					if(picname.equals("")){
						
					}else{
						UploadPicEntity uPic = new UploadPicEntity();
						uPic.setRealPath(tempPicName);
						uPic.setUploadPath(url + picname);
						pics.add(uPic);
					}
				}
			}
		}else{
			result.setCode(Commons.FAIL_CODE);
			result.setMsg(Commons.UPLOAD_FILE_FAIL);
		}
		if(pics != null && pics.size() > 0){
			result.setCode(Commons.SUCCESS_CODE);
			result.setMsg(new Gson().toJson(pics));
		}
		
		JsonUtil.returnJson(response, result);
	}
	
	/**
	 * 图片解析公共方法
	 * 
	 * @param licensePicName
	 * @param licensePic
	 * @param logoRealPathDir
	 * @return
	 */
	private String picResolve(String licensePicName,String licensePic,String logoRealPathDir){
		try {  
			String name = ""; 
			if(StringUtil.isNotEmptyString(licensePicName) && StringUtil.isNotEmptyString(licensePic)) {
            // 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密  
            byte[] photoimg = new BASE64Decoder().decodeBuffer(licensePic);  
            for (int i = 0; i < photoimg.length; ++i) {  
                if (photoimg[i] < 0) {
                    // 调整异常数据  
                    photoimg[i] += 256;  
                }  
            }  
            // byte[] photoimg = Base64.decode(photo);//此处不能用Base64.decode（）方法解密，我调试时用此方法每次解密出的数据都比原数据大 
            System.out.println("图片的大小：" + photoimg.length);  
            
    		/** 根据真实路径创建目录 **/
    		File logoSaveFile = new File(logoRealPathDir);
    		if (!logoSaveFile.exists())
    			logoSaveFile.mkdirs();
    		String _suffix = licensePicName.substring(licensePicName.lastIndexOf("."));
    		/** 拼成完整的文件保存路径加文件 **/
    		name = System.currentTimeMillis() + _suffix;
    		if (StringUtil.isNotEmptyString(name)) {
    			String fileName = logoRealPathDir + File.separator + name;
    			File file = new File(fileName);
    			try {
    				FileOutputStream out = new FileOutputStream(file); 
    				out.write(photoimg);  
    	            out.flush();  
    	            out.close(); 
    			} catch (IllegalStateException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
			}    			
    		return name;
        } catch (Exception e) {  
            e.printStackTrace(); 
            return "";
        }
	}

}

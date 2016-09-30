package com.orange.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * jsp转成静态页面
 * @author wf 
 * @createDate 2013-12-4
 */
public class ReadPageContent {
	

	public String getWebCon(String domain,String encoding) {
		// System.out.println("开始读取内容...("+domain+")");
		StringBuffer sb = new StringBuffer();
		try {
			java.net.URL url = new java.net.URL(domain);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(),encoding));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
//			System.out.println(sb.toString());
			in.close();
		} catch (Exception e) { // Report any errors that arise
			sb.append(e.toString());
			System.err.println(e);
			System.err.println("Usage:   java   HttpClient   <URL>   [<filename>]");
		}
		return sb.toString();
	}
	
	public String getWebCon1(String domain) {
		// System.out.println("开始读取内容...("+domain+")");
		StringBuffer sb = new StringBuffer();
		try {
			java.net.URL url = new java.net.URL(domain);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;
			if ((line = in.readLine()) != null) {
				sb.append(line);
			}
//			System.out.println(sb.toString());
			in.close();
		} catch (Exception e) { // Report any errors that arise
			sb.append(e.toString());
			System.err.println(e);
			System.err
					.println("Usage:   java   HttpClient   <URL>   [<filename>]");
		}
		return sb.toString();
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePathAndName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @return 返回文本文件的内容
	 */
	public String readTxt(String filePathAndName, String encoding) {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		try {
			FileInputStream fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			BufferedReader br = new BufferedReader(isr);

			String data = "";
			while ((data = br.readLine()) != null) {
				//data = data.replace(data, "\r\n");
				str.append(data + " ");
			}

		} catch (Exception es) {
			es.printStackTrace();
		}
//		System.out.println("get String :" + str.toString());
		return str.toString();
	}

	/**
	 * 将文本内容写入文件
	 * 
	 * @param fileName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @param towrite
	 *            文本内容
	 */
	public void SaveStringToFile(String fileName, String towrite,
			String encoding) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;

		try {
//			System.out.println("fileName"+fileName);
			File file = new File(fileName);
			if(!file.exists()){
				if(!file.getParentFile().mkdirs()){
					System.out.println("创建父目录失败！");
				}
				try{
					if(!file.createNewFile()){
						System.out.println("文件创建失败！"); 
					}
				}catch(IOException e){
					e.printStackTrace();
				}
//			if (!file.exists()) {
//				//file.createNewFile();
				file = new File(fileName);
			}
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, encoding);

			osw.write(towrite);
			osw.flush();
		} catch (IOException iex) {
			iex.printStackTrace();
		} finally {
			try {
				if (null != osw)
					osw.close();
				if (null != fos)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建目录
	 * @param path
	 * @author wf 
	 * @createDate 2013-12-23
	 * @lastAuthor wf
	 * @lastDate 2013-12-23
	 */
	public void creatDirectory(String path){
		if(!(new File(path).isDirectory()))
		{
		new File(path).mkdir();
		}
	}
	
	/**
	 * 转换操作
	 * @param domain
	 * @param fileName
	 * @author wf 
	 * @createDate 2013-12-4
	 * @lastAuthor wf
	 * @lastDate 2013-12-4
	 */
	public void readPageContent(String domain,String fileName){
		String towrite  = this.getWebCon(domain, "UTF-8");
		this.SaveStringToFile(fileName, towrite, "UTF-8");
	}
	public static void main(String[] args) {
		new ReadPageContent().readPageContent("http://192.168.0.66:8080/orange/news/article/show?id=3", "E:/cms/html/test.html");
	}
}

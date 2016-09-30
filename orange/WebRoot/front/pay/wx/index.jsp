<%@page import="com.orange.util.StringUtil"%>
<%@ page language="java" import="java.util.*, com.orange.pay.utils.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println("basePath=" + basePath);
	
	String method=request.getParameter("method");
	System.out.println("method=" + method);
	String code_url = "";
	if(StringUtil.isNotEmptyString(method)){
		if(method.equals("unified_order")){
			//code_url = WXPayUtil.weixin_pay(pro_id, "报名费用", pro_money, "");
		}
	}
	if(StringUtil.isNotEmptyString(method) && method.equals("notify")){
		//Boolean result = WXPayUtil.weixin_notify(request, response);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
	<title>微信扫码支付</title>
	<style type="text/css">
		*{margin:0;padding:0;}body{font:14px Microsoft YaHei,\5FAE\8F6F\96C5\9ED1,SimSun,\5B8B\4F53,Arial,Verdana;color:#000;text-align:left;padding-top:3%;background:#FFF;}
		a:link,a:visited{color:#F00;text-decoration:none;}a:hover{color:#090;text-decoration:underline;}
		img{border:0 none;vertical-align:middle;}
		.wenxin{width:100%;height:auto;overflow:hidden;text-align:center;}
		.top{width:60%;height:auto;overflow:hidden;line-height:40px;margin:0 auto 20px;padding:5px 15px;text-align:left;border:2px solid #E6E6E6;background:#FFF;}
		.top span{float:left;}
		.top span.a{color:#390;font-size:16px;font-weight:bold;margin-right:10px;}
		.top span.b{color:#999;}
		.top span.c{float:right;}
		.codeimg{height:270px;text-align:center;}
		.bottom{padding:5px 10px;color:#090;}
		.codeimg img{width:220px;height:220px;padding:8px;border:8px solid #F2F2F2;}
		@media(max-width:500px){.top{width:90%;line-height:25px;padding:5px 3%;}.top span{float:left;display:block;}}
	</style>
	<style type="text/css">
		object,embed{                
		-webkit-animation-duration:.001s;-webkit-animation-name:playerInserted;                
		-ms-animation-duration:.001s;-ms-animation-name:playerInserted;                
		-o-animation-duration:.001s;-o-animation-name:playerInserted;                
		animation-duration:.001s;animation-name:playerInserted;}                
		@-webkit-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}                
		@-ms-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}               
		@-o-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}                
		@keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}
	</style>
	</head>
  
  <body youdao="bind">
	<div class="wenxin">
	    <div class="top">        
	        <span class="a"><img src="<%=basePath %>/resources/front/images/wx.gif"> <strong>微信支付</strong></span> 
	        <span class="b">亿万用户选择，更快更安全！</span>
	        <span class="c">支付：<strong style="font-size:18px; color:#F60;">${pro_money}</strong>元</span>
	    </div>
	    <div class="codeimg"><img src="<%=basePath %>/pay/prepare/wx.html?code=${code_url}"></div>
	    <div class="bottom"><img src="<%=basePath %>/resources/front/images/sm.gif"> 
		电脑用户：直接用手机打开微信扫描二维码支付；<br>手机用户：保存图片后，用微信扫一扫打开相册扫描支付；
		</div>    
	</div>
</body>
</html>

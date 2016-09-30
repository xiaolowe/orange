<!DOCTYPE html>
<#import "/front/common/tpl/htmlBase.ftl" as html />
<@html.htmlBase title="微信扫码支付" fav=currentSetting().fav descs=currentSetting().descs keywords=currentSetting().keywords 
jsFiles=["jquery-1.7.1.min.js"] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin=false>
<head>
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
	<script type="text/javascript">
		$( function() {
		    setInterval("ajaxstatus()", 5000);    
		});
		
		function ajaxstatus() {
			var addonId = $("#addonId").val();
			//alert("check" + addonId);
			if ($("#addonId").val() != '0') {
		        $.ajax({
		            url: "${basepath}/pay/state/query.html",
		            type: "post",
		            dataType:"json",
		            data: {state:addonId, mobile:$("#mobile").val(), raceName:$("#raceName").val()},
		            success: function (data) {
	 	            	//alert(data.code);
	 	            	$("#addonId").val(data.msg);
		           		if(data.code=='ok') { //订单状态为1表示支付成功
				            var r=confirm("支付完成，是否到个人中心查看？");
							if (r==true){
								window.location.href=basepath+"/member/home.html";
							}else{
								window.location.href=basepath+"/index.html";
							}
		           		}
		            },
		            error: function () {
		            	alert("请求支付状态出错"); 
		            }
		        });
		    }
		}
	</script>
</head>
<body>
	<div class="wenxin">
	    <div class="top">        
	        <span class="a"><img src="${frontpath}/images/wx.gif"> <strong>微信支付</strong></span> 
	        <span class="b">亿万用户选择，更快更安全！</span><span class="b" style="color:red">${error!''}</span>
	        <span class="c">支付：<strong style="font-size:18px; color:#F60;">${pro_money}</strong>元</span>
	        <input type="hidden" id="addonId" name="addonId" value="${addonId!'0'}"/> 
	        <input type="hidden" id="mobile" name="mobile" value="${mobile!''}"/> 
	        <input type="hidden" id="raceName" name="raceName" value="${raceName!''}"/> 
	    </div>
	    <div class="codeimg"><#if code_url?default('')!=''><img src="${basepath}/pay/prepare/wx.html?code=${code_url!''}"></#if></div>
	    <div class="bottom"><img src="${frontpath}/images/sm.gif"> 
		电脑用户：直接用手机打开微信扫描二维码支付
		</div>    
	</div>
</body>
</@html.htmlBase>
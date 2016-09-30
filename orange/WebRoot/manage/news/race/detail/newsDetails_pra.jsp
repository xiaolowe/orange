<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base/commons/pages/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="PRAGMA" content="NO-CACHE"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<title>文章详情</title>
<link href="${ctx}/d/template/css/style.css" rel="stylesheet" type="text/css" /></link>
</head>
${jquery_js} ${jmpopups_js} ${flexigrid_js} ${friendone_js}
${jquery_form_js} ${WdatePicker_js} ${jquery_select_js}
${jquery_easyui_min_js} ${permission_js}
<body>
<or-cms:article articleid="${param.id}" mid="${param.mid}" >
           <div class="lump1">
	      <%if(request.getParameter("mid").equals("")) {%>
	      	 <% if(request.getParameter("action").equals("z") ){ %>
	           <div class="zan" style="cursor: pointer;color: red;background:url(${ctx}/d/template/images/hongse-zan.png) no-repeat 30%;"  onclick="addPraise('${param.mid}','${param.id}','zg','w')" ><a><%=article.getZaction()%></a></div>
	           <div class="buzan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-cai.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','zg','w')" ><a><%=article.getCaction()%></a></div>
	       <%}else if(request.getParameter("action").equals("c") ){%>
	       	   <div class="zan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-zan.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','cg','w')" ><a><%=article.getZaction()%></a></div>
	           <div class="buzan" style="cursor: pointer;color: red;background:url(${ctx}/d/template/images/hongse-cai.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','cg','w')" ><a><%=article.getCaction()%></a></div>
	        <%}else{%>
	        	<div class="zan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-zan.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','z','w')" ><a><%=article.getZaction()%></a></div>
	            <div class="buzan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-cai.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','c','w')" ><a><%=article.getCaction()%></a></div>
			<%} %>
	      <%}else{ %>
	      	<% if(article.getIsaction().equals("z")){ %>
	           <div class="zan" style="cursor: pointer;color: red;background:url(${ctx}/d/template/images/hongse-zan.png) no-repeat 30%;"  onclick="addPraise('${param.mid}','${param.id}','zg','w')" ><a><%=article.getZaction()%></a></div>
	           <div class="buzan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-cai.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','zg','w')" ><a><%=article.getCaction()%></a></div>
	       <%}else if(article.getIsaction().equals("c")){%>
	       	   <div class="zan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-zan.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','cg','w')" ><a><%=article.getZaction()%></a></div>
	           <div class="buzan" style="cursor: pointer;color: red;background:url(${ctx}/d/template/images/hongse-cai.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','cg','w')" ><a><%=article.getCaction()%></a></div>
	        <%}else{%>
	        	<div class="zan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-zan.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','z','w')" ><a><%=article.getZaction()%></a></div>
	            <div class="buzan" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-cai.png) no-repeat 30%;" onclick="addPraise('${param.mid}','${param.id}','c','w')" ><a><%=article.getCaction()%></a></div>
			<%} %>
	      
	      <%} %>
	      </div>
</or-cms:article>
</body>
</html>
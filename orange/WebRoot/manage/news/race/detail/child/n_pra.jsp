<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base/commons/pages/taglibs.jsp" %>
<%@ page import="cn.finedo.base.utils.string.StringHelper"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="PRAGMA" content="NO-CACHE"/>
<title>赞踩</title>

</head>

<body>


<% String mid =  request.getParameter("mid");
String action = request.getParameter("action");

String zimg ="huise-zan.png";
String optz  ="z";
String cimg ="huise-cai.png";
String optc  ="c";

String  zzi = "f999";
String  czi = "f999";
%>
<div class="xqbtn">
		<or-cms:article articleid="${param.id}" mid="${param.mid}" >
			<% if(StringHelper.isNotNull(mid)){
				 
				if("z".equals(article.getIsaction())){
					zimg ="hongse-zan.png";
					 optz="zg";
					 zzi = "fred";
				}										
				if("c".equals(article.getIsaction())){
					cimg ="hongse-cai.png";
					optc ="cg";
					czi="fred";
				}
			%>
	
				<a id="wapxfxqy_B02_24" onclick="addPraise('${param.mid}','${param.id}','<%=optz%>','w','<%=optc%>');" class="tup"><span class="<%=zzi %>" style="background: url(${ctx}/d/newsDetails/img/<%=zimg %>) left center no-repeat; background-size: 18px auto;">${article.zaction }</span></a>
				<a id="wapxfxqy_B02_24" onclick="addPraise('${param.mid}','${param.id}','<%=optc%>','w','<%=optz%>');" class="tdw" style="margin-left:20px;"><span class="<%=czi %>" style="background: url(${ctx}/d/newsDetails/img/<%=cimg %>) left center no-repeat; background-size: 18px auto;">${article.caction }</span></a>
			<%}else{ 

				if("z".equals(action)){
					zimg ="hongse-zan.png";
					 optz="zg";
					 zzi = "fred";
				}										
				if("c".equals(action)){
					cimg ="hongse-cai.png";
					optc ="cg";
					czi="fred";
				}
			%>
				<a id="wapxfxqy_B02_24" onclick="addPraise('${param.mid}','${param.id}','<%=optz%>','w','<%=optc%>');" class="tup"><span class="<%=zzi %>" style="background: url(${ctx}/d/newsDetails/img/<%=zimg %>) left center no-repeat; background-size: 18px auto;">${article.zaction }</span></a>
				<a id="wapxfxqy_B02_24" onclick="addPraise('${param.mid}','${param.id}','<%=optc%>','w','<%=optz%>');" class="tdw" style="margin-left:20px;"><span class="<%=czi %>" style="background: url(${ctx}/d/newsDetails/img/<%=cimg %>) left center no-repeat; background-size: 18px auto;">${article.caction }</span></a>
			<%} %>
		</or-cms:article>
		</div>

</body>
</html>
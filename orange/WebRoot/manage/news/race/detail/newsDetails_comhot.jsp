<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base/commons/pages/taglibs.jsp" %>
<%@ page import="cn.finedo.base.utils.string.StringHelper"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="PRAGMA" content="NO-CACHE"/>
<title>文章详情</title>
<link href="${ctx }/d/template/css/style.css" rel="stylesheet" type="text/css" /></link>
</head>
${jquery_js} ${jmpopups_js} ${flexigrid_js} ${friendone_js}
${jquery_form_js} ${WdatePicker_js} ${jquery_select_js}
${jquery_easyui_min_js} ${permission_js}
<body>

<or-cms:comment sort="'x','r'" type="x" id="${param.id}" mid="${param.mid}" pagesize="0,5" >
<c:if test="<%=Integer.parseInt(comment.getActionZ()) >= 10%>">
           <dl class="dl-horizontal clearfix">
            <dt>
            <a href="#">
            	<%if(StringHelper.isNotNull(comment.getImg())){ %>
            		<img alt="#" style="width:50px;height:50px;" src="${ctx }/upload/file/<%=comment.getImg() %>" />
            	<%}else{ %>
            		<img alt="#" style="width:50px;height:50px;" src="${ctx }/d/template/images/news_21.png" />
            	<%}%>
            </a>
            </dt>
            <dd>
              <a><%=comment.getMname() %></a>
              </br><p class="time"><a><%=comment.getCtime() %></a></p>
              </br><p class="talk"><a><%=comment.getConcent() %></a></p>
              <div class="comment">
             <%if(StringHelper.isNotNull(request.getParameter("mid"))){ %> 
              		<% if(comment.getIsaction().equals("z")){ %>
                		<div class="zan_a">
                		<a onclick="addPraise('${param.mid}','${param.id}','zg','p')" style="cursor: pointer;color: red;" ><%=comment.getActionZ() %></a>
			        	</div>
			        	<div class="zan0" onclick="addPraise('${param.mid}','${param.id}','zg','p')" style="cursor: pointer;color: red;background:url(${ctx}/d/template/images/hongse-zan.png) no-repeat;"></div>
			        	
			        	<%-- <div class="zan_a">
			        	<a onclick="addPraise('${param.mid}','${param.id}','zg','p')" style="cursor: pointer;" ><%=comment.getActionC() %></a>
			        	</div>
                		<div class="buzan0" onclick="addPraise('${param.mid}','${comment.cid}','zg','p')" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-cai.png) no-repeat;"></div> --%>
			        
			        <%}else if(comment.getIsaction().equals("c")){%>
			        	<%-- <div class="zan_a">
			        	<a onclick="addPraise('${param.mid}','${param.id}','cg','p')" style="cursor: pointer;" ><%=comment.getActionZ() %></a>
			        	</div>
			        	<div class="zan0" onclick="addPraise('${param.mid}','${comment.cid}','cg','p')" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-zan.png) no-repeat;"></div> --%>
			        	
			        	<div class="zan_a">
			        	<a onclick="addPraise('${param.mid}','${param.id}','cg','p')" style="cursor: pointer;color: red;" ><%=comment.getActionC() %></a>
			        	</div>
			        	<div class="zan0" onclick="addPraise('${param.mid}','${comment.cid}','cg','p')" style="cursor: pointer;color: red;background:url(${ctx}/d/template/images/hongse-cai.png) no-repeat;"></div>
			        	
					<%}else{ %>
						<div class="zan_a">
			        	<a onclick="addPraise('${param.mid}','${param.id}','z','p')" style="cursor: pointer;" ><%=comment.getActionZ() %></a>
			        	</div>
			        	<div class="zan0" onclick="addPraise('${param.mid}','${comment.cid}','z','p')" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-zan.png) no-repeat;"></div><div class="zan_a">
			        	<a onclick="addPraise('${param.mid}','${param.id}','c','p')" style="cursor: pointer;" ><%=comment.getActionC() %></a>
			        	</div>
                		<div class="buzan0" onclick="addPraise('${param.mid}','${comment.cid}','c','p')" style="cursor: pointer;background:url(${ctx}/d/template/images/huise-cai.png) no-repeat;"></div>
					<%} %>
					<% String str = "//@"+comment.getMname()+":"+comment.getConcent(); %>
					<%if(StringHelper.isNotNull(comment.getAtarget())) {%>
	                	<div class="discuss" onclick="th('${param.mid}','${comment.mid}','r','h','${comment.atarget}','<%=str%>')" style="cursor: pointer;" ></div>
	                <%}else{%>
	                	<div class="discuss" onclick="th('${param.mid}','${comment.mid}','r','p','${comment.cid}','<%=str%>')" style="cursor: pointer;" ></div>
	                <%} %>
	            <%} %> 
             </div>
            </dd>
          </dl>
          </c:if>
     </or-cms:comment>

<script type="text/javascript">
</script>
</body>
</html>
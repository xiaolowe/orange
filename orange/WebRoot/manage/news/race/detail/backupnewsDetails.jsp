<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base/commons/pages/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="PRAGMA" content="NO-CACHE"/>
<title>文章详情</title>
<link href="${ctx }/d/template/css/style.css" rel="stylesheet" type="text/css" /></link>
</head>
${jquery_js}
<body>
<or-cms:article articleid="${param.id}" mid="${param.mid}" >
<div class="content">
   <div class="top"></div>
      <div class="content-area">
          <h1><%=article.getTitle() %></h1>
           <h2>来源： &nbsp;<%=article.getInfosource() %>&nbsp;&nbsp;&nbsp;&nbsp; <%=article.getCreatetime() %></h2>
          <div class="pic">
          <or-cms:articlefile articleid="${param.id}" >
         	<img src="${ctx }<%=articlefile.getFilepath() %>" />
          </or-cms:articlefile>
          </div>
          <p><%=article.getContent()%></p>
      </div>
       <div class="lump1">
       <% if(article.getIsaction().equals("z")){ %>
           <div class="zan" style="cursor: pointer;color: red;" onclick="addPraise('${param.mid}','${param.id}','zg')" ><a><%=article.getZaction()%></a></div>
           <div class="buzan" style="cursor: pointer;" onclick="addPraise('${param.mid}','${param.id}','zg')" ><a><%=article.getCaction()%></a></div>
       <%}else if(article.getIsaction().equals("c")){%>
       	   <div class="zan" style="cursor: pointer;" onclick="addPraise('${param.mid}','${param.id}','cg')" ><a><%=article.getZaction()%></a></div>
           <div class="buzan" style="cursor: pointer;color: red;" onclick="addPraise('${param.mid}','${param.id}','cg')" ><a><%=article.getCaction()%></a></div>
        <%}else{%>
        	<div class="zan" style="cursor: pointer;" onclick="addPraise('${param.mid}','${param.id}','z')" ><a><%=article.getZaction()%></a></div>
            <div class="buzan" style="cursor: pointer;" onclick="addPraise('${param.mid}','${param.id}','c')" ><a><%=article.getCaction()%></a></div>
		<%} %>
      </div>
      <div class="lump2">
        <div class="red"></div>
         <h3>&nbsp;&nbsp;&nbsp;评论</h3>
       </div>
       <div class="cloumn">
       	 <or-cms:comment sort="'x','r'" type="x" id="${param.id}">
          <dl class="dl-horizontal clearfix">
            <dt>
            <a href="#" title="#">
            	<%if(comment.getImg() == ""){ %>
            		<img alt="#" src="${ctx }/d/template/images/news_21.png" />
            	<%}else{ %>
            		<img alt="#" src="${ctx }/upload/file/<%=comment.getImg() %>" />
            	<%}%>
            </a>
            </dt>
            <dd>
              <a><%=comment.getLoginname() %></a>
              <div class="zan_a"><a><%=comment.getActionZ() %></a></div>
              <div class="zan0"></div>
              <br><p class="time"><a><%=comment.getCtime() %></a></p></br>
              <br /><div class="comment"><a><%=comment.getConcent() %></a></div></br>
              
            </dd>
          </dl>
         </or-cms:comment>
       </div>
</div>
</or-cms:article>
<script type="text/javascript">

function addPraise(mid,id,action){
	if(action == "zg"){
		alert("您已经顶过!");
		return false;
	}
	if(action == "cg"){
		alert("您已经踩过!");
		return false;
	}
	var url = "${ctx }/clientAction.do?method=json&classes=commentServiceImpl&common=addPraise&mid="+mid+"&id="+id+"&action="+action;
	$.getJSON(url, callback);
}
function callback(data){
	if(data.jsondata.retCode == "0000"){
		alert(data.jsondata.retDesc);
	}else{
		alert(data.jsondata.retDesc);
	}
}

</script>
</body>
</html>
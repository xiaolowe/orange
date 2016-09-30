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
<link href="${ctx}/d/template/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/d/template/js/iscroll.js"></script>
${jquery_js} ${jmpopups_js} ${flexigrid_js} ${friendone_js}
${jquery_form_js} ${WdatePicker_js} ${jquery_select_js}
${jquery_easyui_min_js} ${permission_js}
</head>

<body>
 <% int hotcount=0,ptcount=0,yuedu=0; %>
<div id="wrapper">
<or-cms:article articleid="${param.id}" mid="${param.mid}"  >
<%-- <input type="hidden" id="cid" name="cid" value="${article.cid}" /> --%>
<div class="content">
   <!-- <div class="top"></div> -->
      <div class="content-area">
          <h1 style="width:90%;margin-left:1%; font-size:25px;" ><%=article.getTitle() %></h1>
           <h2 style="font-size:14px; color:#828282; margin-top:-5px;padding:0;">&nbsp;&nbsp;来源：<%=article.getInfosource() %>&nbsp;&nbsp;&nbsp;&nbsp; <%=article.getCreatetime() %></h2>
          <div class="pic">
          <or-cms:articlefile articleid="${param.id}" >
         	<img src="${ctx }<%=articlefile.getFilepath() %>" />
          </or-cms:articlefile>
          </div>
          <div class="lol" style="font-size:19px;";><%=article.getContent()%></div>
      </div>
      <div class="lump1" id="pra" >
       </div>
           <%--    
     
      
         <div class="keywords"><a>关键字：</a>
            &nbsp;&nbsp;<a class="keywordsA">${article.keyword}</a>
          
            
           </div>
            --%>
             <div class="keywords"><p><span>关键字：</span>${article.keyword}</p></div>
             
            <or-cms:article articleid="${param.id}" mid="${param.mid}" keyword="<%=article.getKeyword() %>" pagesize="0,5" >
             <% yuedu++;%>
            </or-cms:article>
            <%if(yuedu>0) {%>
      <div class="hot">
         <div class="red"></div>
           <h3>相关阅读</h3>
        </div>
        <div class="relate clearfix">
         <dl>
            <or-cms:article articleid="${param.id}" mid="${param.mid}" keyword="<%=article.getKeyword() %>" pagesize="0,5" >
            <dd onclick="show('<%=article.getArticleid() %>');"><%=article.getTitle() %></dd>
              </or-cms:article>
           
         </dl>
        </div>
        <%} %>
        <or-cms:comment sort="'x','r'" type="x" id="${param.id}" mid="${param.mid}" pagesize="0,5" >
		<c:if test="<%=Integer.parseInt(comment.getActionZ()) >= 10%>">
		<%hotcount++;%>
         	</c:if>
     	</or-cms:comment>
     	<%if(hotcount>0) {%>
          <div class="hot">
        <div class="red"></div>
         <h3>热点评论</h3>
       </div>
	       <div class="cloumn" id="plh" >
           </div>
    <%} %>
      
      
      <or-cms:comment sort="'x','r'" type="x" id="${param.id}" mid="${param.mid}"  >
      <%ptcount++;%>
              </or-cms:comment>
                 
       <div class="pinglun" id="hidehd" <%if(ptcount<=0) {%>style="display:none" <%}%>>
        <div class="red"></div>
         <h3>最新评论</h3>
       </div>
       
       <div class="cloumn" id="pl" <%if(ptcount<=0) {%>style="display:none" <%}%>>
       </div>
      
	 
       
</div>

</or-cms:article>



</div>
<script type="text/javascript">
var i_a = '${param.i_a}';
var id = "<%=request.getParameter("id") == null?"":request.getParameter("id")%>";
var mid = "<%=request.getParameter("mid") == null?"":request.getParameter("mid")%>";
var actions = "<%=request.getParameter("action") == null?"":request.getParameter("action")%>";
//alert("i_a=" + i_a + ",id=" + id + ",mid = " + mid + ",actions = " + actions);
$(document).ready(function() {
	actions ='${param.action}';
	querypage(id,mid);
	
	querypagehot(id,mid);
    querypra(id,mid,actions);
    
});


function querypagehot(id,mid){
	
	var url="${ctx}/clientAction.do?method=client&nextPage=/d/newsDetails/newsDetails_comhot.jsp&id="+id+"&mid="+mid;
	$.ajax({
	    url: url,
	    success: function (data) {
	        $("#plh").html(data);
	    },
	    cache: false
	}); 
}

function querypage(id,mid){
	/* var cid = $("#cid").val(); */
	//alert("querypage:id=" + id + ",mid = " + mid);
	var url="${ctx}/clientAction.do?method=client&nextPage=/d/newsDetails/newsDetails_com.jsp&id="+id+"&mid="+mid;
	$.ajax({
	    url: url,
	    success: function (data) {
	    	//alert(data);
	        $("#pl").html(data);
	    	//$("#pl").html("1234");
	    },
	    cache: false
	}); 
}
function querypra(id,mid,action){
	
	var url="${ctx}/clientAction.do?method=client&nextPage=/d/newsDetails/newsDetails_pra.jsp&id="+id+"&mid="+mid+"&action="+action;
	$.ajax({
	    url: url,
	    success: function (data) {
	        $("#pra").html(data);
	    },
	    cache: false
	}); 
}

function addPraise(mid,id,action,type){
	
	if(mid == "" || mid == null){
		
		actions = action;
		if(i_a=="IOS"){
 			document.location = "testapp:addPraise:"+action;
 		}
 		else{
			window.android.addPraise(action);
 		}
	}
	
	//if(!mid){
		
//		if(i_a=="IOS"){
 //			document.location = "testapp:showLoginDialog:登录";
 	//	}
 		//else{
	//		window.android.showLoginDialog();
 		//}
		//return false;
	//}
	if(action == "zg"){
		if(i_a=="IOS"){
 			document.location = "testapp:alert:您已经顶过";
 		}
 		else{
			window.android.showAlert("您已经顶过!");
 		}
		return false;
	}
	if(action == "cg"){
		if(i_a=="IOS"){
 			document.location = "testapp:alert:您已经踩过";
 		}
 		else{
			window.android.showAlert("您已经踩过!");
 		}
		return false;
	}
	if(mid == "" || mid == null){
		actions = action;
		if(i_a=="IOS"){
 			document.location = "testapp:addPraise:"+action;
 		}
 		else{
			window.android.addPraise(action);
 		}
	}
		
	var url = "${ctx }/clientAction.do?method=json&classes=commentServiceImpl&common=addPraise&mid="+mid+"&id="+id+"&action="+action+"&type="+type;
	$.getJSON(url, callback);
}

function show(id){
	if(i_a=="IOS"){
			document.location = "testapp:showNewsDetail:"+id;
		}
	else{
		window.android.showNewsDetail(id);
	}
}

function callback(data){
	
	//alert(data.jsondata.retCode);
	if(data.jsondata.retCode == "0000"){
		querypage(id,mid);
		querypra(id,mid,actions);
		if(i_a=="IOS"){
			document.location = "testapp:alert:"+data.jsondata.retDesc;
		}else{
			window.android.showAlert(data.jsondata.retDesc);
		}
	}else{
		querypage(id,mid);
		querypra(id,mid,actions);
		if(i_a=="IOS"){
			document.location = "testapp:alert:"+data.jsondata.retDesc;
		}else{
			window.android.showAlert(data.jsondata.retDesc);
		}
	}
}
function th(mid,id,type,action,atarget,str){
	//alert("th");
	$("#mid").val(mid);
	$("#id").val(id);
	$("#type").val(type);
	$("#action").val(action);
	$("#atarget").val(atarget);
	if(i_a=="IOS"){
		document.location = "testapp:showCommentDialog:"+str;
	}else{
		window.android.showCommentDialog(str);
	}
}


function add(concent){
	$("#hidehd").show();
	$("#pl").show();
	//alert("th1");
	$("#concent").val(concent);
	var options = { 
	 	        success:   callback,
	 	        type:      'post',
	 	        dataType:  'json',
	 	        clearForm: false        
	 	    };
	 $('#addform').ajaxSubmit(options);
}

</script>
</body>
</html>
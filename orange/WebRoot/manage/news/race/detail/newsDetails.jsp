<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/base/commons/pages/taglibs.jsp"%>
<%@ page import="cn.finedo.base.utils.string.StringHelper"%>
<%@ page import="cn.finedo.base.utils.date.DateHelper"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta name="keywords" content="${e.title}">
<meta name="description" content="新闻">
<title>新闻</title>
<link rel="stylesheet" type="text/css" href="${ctx}/d/newsDetails/css/common.css">
<link rel="stylesheet" type="text/css" href="${ctx}/d/newsDetails/css/css.css">
${jquery_js} ${friendone_js}
${jquery_form_js} 
<%--<script type="text/javascript" src="${ctx}/d/newsDetails/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/d/newsDetails/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${ctx}/d/newsDetails/js/swipe.js"></script>

--%>

<body> 
	
   
	<or-cms:article articleid="${param.id}" mid="${param.mid}"  >

	<div class="mainConBox">
		<div class="mainContent">
			<div class="conTitle">
				<h1>${article.title}</h1>
				<p style="font-weight:bold;">来源：${article.infoSource}&nbsp;&nbsp;&nbsp;&nbsp;<span class="time">${article.createTime}</span></p>
			</div>
			<div class="conWord">
			<or-cms:articlefile articleid="${param.id}" >
				<div style="text-indent:0;">
					<a >
						<img title="${article.title}" alt="${article.title}" src="${ctx}/${articlefile.filepath}" type="image">
					</a>
					         
				</div><br>
				</or-cms:articlefile>
				<p align="center"></p>
				<p>${article.content}</p>
				
			</div>
		</div>
		
		<div class="xqbox_bw pd10" id="meopt">
		
		</div>

		<%if (StringHelper.isNotNull(article.getKeyword())){%>
		<div class="dzNew">
			<span class="f999">关键字：</span><span class="f999">${article.keyword}</span>
		</div>
		<% }%>
		<!--2013-12-12 end-->
	
		<div style="height: 13px;"></div>
		
		<div class="otherlist">
		<div class="title_red"><span class="f999" style="margin-left:15px;" >相关阅读<span></div>
			<ul class="new_list">
			<or-cms:article  pagesize="0,3" rand="yes">
				<a onclick="show('<%=article.getArticleid() %>');" rel="nofollow">
					<li><%=article.getTitle() %></li>
				</a>
				</or-cms:article>
			</ul>
	</div>
	</or-cms:article>	
		
		<%int rdp=0,ptp=0;%>
		<or-cms:comment sort="'x','r'" type="x" id="${param.id}" mid="${param.mid}"  pagesize="0,20">
		<c:if test="<%=Integer.parseInt(comment.getActionZ()) >= 10%>">
		<%rdp++;%>
		</c:if>
		</or-cms:comment>
		<div class="otherlist" id="rdp" <%if(rdp<=0) {%>style="display:none" <%}%>>
			<div class="title_gray"><span class="f999" style="margin-left:15px;" >热门评论<span></div>
			
		</div>
	
	<!-- 评论内容 -->
	
		<div class="xqbox pd10" id="plh" <%if(rdp<=0) {%>style="display:none" <%}%>>
			
		</div>
	
		
	
	</div>

	
		<or-cms:comment sort="'x','r'" type="x" id="${param.id}" mid="${param.mid}"  pagesize="0,5">
				<%ptp++;%>
	</or-cms:comment>
						
	<div class="otherlist" id="ptp" <%if(ptp<=0) {%>style="display:none" <%}%>>
			<div class="title_gray"><span class="f999" style="margin-left:15px;" >最新评论<span></div>
			
		</div>
	
	<!-- 最新评论内容 -->
		<div class="xqbox pd10" id="pl" <%if(ptp<=0) {%>style="display:none" <%}%>>
		
		</div>
	
		
	
	</div>


<form action="${ctx }/clientAction.do?method=json&classes=commentServiceImpl&common=add" id="addform" method="post" >
	<input type="hidden" id="mid" name="mid"  />
	<input type="hidden" id="id" name="id" />
	<input type="hidden" id="type" name="type" />
	<input type="hidden" id="action" name="action" />
	<input type="hidden" id="atarget" name="atarget" />
	<input type="hidden" id="concent" name="concent" />
</form>

<!--foot begin-->
<!-- <div id="foot" style="background:#EAE7E7; padding-bottom:16px;margin-top: 5px;">
	<div class="nnav" style="background:#df3031; height:30px">
		<div class="flexbox" style="max-width:320px; margin:0px auto">
			<a href="#" style="width:13%">新房</a>
			<a href="#" style="width:17%">二手房</a>
			<a href="#" style="width:13%">租房</a>
			<a href="#" style="width:13%">家居</a>
			<a href="#" style="width:13%">资讯</a>
			<a href="#" style="width:13%">论坛</a>
		</div>
	</div>
</div> -->

<!-- 回顶部 -->
<a id="backToTop" href="javascript:void(0);" style="position: fixed; height: 30px; width: 30px; right: 8px; bottom: 65px; display: block; background: url(./img/backtop.png) 50% 50% / 30px 30px no-repeat;">&nbsp;</a>
<script>
var nowPage	 = "detail";
var city = 'hf';
</script>
<script>
///滑动超过1屏之后返回顶部效果
/*$(function(){
var topup = $("#backToTop");
	topup.click(function() {
		$('html, body').animate({scrollTop: 0}, 200);
	})
	$(window).bind('scroll', function() {
		if(window.pageYOffset <= (window.innerHeight*1-60)) {
			if($(topup).show()) 
				$(topup).hide();
		} else {
			if($(topup).hide())
				$(topup).show();
			
		}

	});
});
window.scrollTo(0, 1);

*/
var i_a = '${param.i_a}';
var id = "<%=request.getParameter("id") == null?"":request.getParameter("id")%>";
var mid = "<%=request.getParameter("mid") == null?"":request.getParameter("mid")%>";
var actions = "<%=request.getParameter("action") == null?"":request.getParameter("action")%>";

//alert("i_a=" + i_a + ",id=" + id + ",mid = " + mid + ",actions = " + actions);
$(document).ready(function() {
	actions ='${param.action}';
	querypra('${param.id}','${param.mid}','${param.action}');
	
	
	querypagehot('${param.id}','${param.mid}');
	querypage('${param.id}','${param.mid}'); 
    
});


function querypagehot(id,mid){
	
	var url="${ctx}/clientAction.do?method=client&nextPage=/d/newsDetails/child/n_comhot.jsp&id="+id+"&mid="+mid;
	$.ajax({
	    url: url,
	    success: function (data) {
	   
	        $("#plh").html(data);
	    },
	    cache: false
	}); 
}

function querypage(id,mid){
	var url="${ctx}/clientAction.do?method=client&nextPage=/d/newsDetails/child/n_com.jsp&id="+id+"&mid="+mid;
	$.ajax({
	    url: url,
	    success: function (data) {
	        $("#pl").html(data);
	    	
	    },
	    cache: false
	}); 
}
function querypra(id,mid,action){

	var url="${ctx}/clientAction.do?method=client&nextPage=/d/newsDetails/child/n_pra.jsp&id="+id+"&mid="+mid+"&action="+action;

	$.ajax({
	    url: url,
	    success: function (data) {
	 	
	        $("#meopt").html(data);
	    },
	    cache: false
	}); 
}

function addPraise(mid,id,action,type,opt,so){
	
	if(mid == "" || mid == null){
		
		actions = action;
		if(i_a=="IOS"){
 			document.location = "testapp:addPraise:"+action;
 		}
 		else{
			window.android.addPraise(action);
 		}
	}
	
	if(so=="pl"&&!mid){
	
		if(i_a=="IOS"){
 			document.location = "testapp:alert:请先登录";
 		}
 		else{
			window.android.showAlert("请先登录!");
		}
		return false;
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
	//if(action == "zg"){
	//	if(i_a=="IOS"){
 	//		document.location = "testapp:alert:您已经顶过";
 	//	}
 	//	else{
	//		window.android.showAlert("您已经顶过!");
 		//}
	//	return false;
	//}
	//if(action == "cg"){
	//	if(i_a=="IOS"){
 	//		document.location = "testapp:alert:您已经踩过";
 	//	}
 		//else{
		//	window.android.showAlert("您已经踩过!");
 		//}
	//	return false;
	//}
	
	if(opt=="zg"||action=="zg"){
		if(i_a=="IOS"){
 			document.location = "testapp:alert:您已经顶过";
 		}
 		else{
		window.android.showAlert("您已经顶过!");
 		}
		return false;
	}
	if(opt=="cg"||action=="cg"){
		if(i_a=="IOS"){
 			document.location = "testapp:alert:您已经踩过";
 		}
 		else{
			window.android.showAlert("您已经踩过!");
 		}
		return false;
	}
	
	if(!mid){
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
		querypagehot(id,mid);
		if(i_a=="IOS"){
			document.location = "testapp:alert:"+data.jsondata.retDesc;
		}else{
			window.android.showAlert(data.jsondata.retDesc);
		}
	}else{
		querypage(id,mid);
		querypra(id,mid,actions);
		querypagehot(id,mid);
		if(i_a=="IOS"){
			document.location = "testapp:alert:"+data.jsondata.retDesc;
		}else{
			window.android.showAlert(data.jsondata.retDesc);
		}
	}
}
function th(mid,id,type,action,atarget,str){
	
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
	$("#rdp").show();
	$("#ptp").show();
	$("#pl").show();
	$("#plh").show();
	
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
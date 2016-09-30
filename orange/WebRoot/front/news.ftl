<#import "/front/common/tpl/fPageBase.ftl" as page>

<@page.pageBase currentIndex="news_list" jsFiles=["jquery-1.10.2.min.js","pageGroup.js"] cssFiles=["main.css"] checkLogin=false>
<script>
$(function(){
	var type="${type}";
	var link = "";
	if(type=='r'){
		link = basepath + "/news/r/list.html";
	}
	if(type=='n'){
		link = basepath + "/news/n/list.html";
	}
	$("a.menu-item").each(function(index){
            var href = $(this).attr("href");
            if($(this).attr("href")==link){
            	$("ul.nav_menu").find("li.nav_menu-item").removeClass("nav_menu-item on").addClass('nav_menu-item');
            	var parent = $(this).parent();
	        	if(parent.is("li.nav_menu-item")){
	        		$(parent).removeClass("nav_menu-item").addClass('nav_menu-item on');
	        	}
	        	if(parent.is("li.nav_submenu-item")){
	        		$(parent).parent().parent().removeClass("nav_menu-item").addClass('nav_menu-item on');
	        	}
	        	var img = $(parent).data("img");
	        	var index_select_index_img = img;//getCookie("jsession_index_selected_index_img");
		        if(index_select_index_img.length>0){
				        $("#topImg").attr("src", index_select_index_img);
						$("#topimg").css("display", "block");
						$(".here").css("display", "block");
		        }else{
			        	$("#topImg").attr("src", "");
						$("#topimg").css("display", "none");
						$(".here").css("display", "none");
			    }
			    var name = $(parent).data("name");
			    var index_select_header_name =name;
			    if(index_select_header_name.length>0){
				       $("#menuA").html(index_select_header_name);
						$(".here").css("display", "block");
		       	}else{
						$(".here").css("display", "none");
			    }
			   
            }
        });
	
});
</script>
<div class="article ">
<div class="w1200">
	<div class="title">${news.title}</div>
	<div class="info"><span>时间:${news.publishTime}</span><span>阅读：${news.num}</span></div>
	<div class="content">
		${news.content}
	</div>
</div>
</div>

<div class="next_article">
	<div class="w1200">
		<ul>
			<li>上一篇：<#if pre??><a href="${basepath}/news/${type}/${pre.id}.html">${pre.title}</a><#else>没有了</#if></li>
			<li>下一篇：<#if next??><a href="${basepath}/news/${type}/${next.id}.html">${next.title}</a><#else>没有了</#if></li>
		</ul>
	</div>
</div>
</@page.pageBase>
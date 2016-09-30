<#macro header navIndexs=[] topIndex="" currentIndex="" setting=[] indexImg="">
<script>
    $(function(){
    	var path = window.location.pathname;
    	$("#topimg").css("display", "none");
    	$(".here").css("display", "none");
        $("a.menu-item").each(function(index){
            var href = $(this).attr("href");
            if(href=="http://192.168.0.66:9092/cms/www/admin.html"){
            	$(this).attr("href", href);
            }else{
            	$(this).attr("href", href==""?"#": href);
            }
            if($(this).attr("href")==path){
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
        		//alert("head_img=" + index_select_index_img);
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
			    //alert("head_name=" + index_select_header_name);
			    if(index_select_header_name.length>0){
				       $("#menuA").html(index_select_header_name);
						$(".here").css("display", "block");
						if(path==index_html){
							$("#topimg").css("display", "none");
							$(".here").css("display", "none");
						}
		       	}else{
						$(".here").css("display", "none");
			    }
            }
        });
        
		
		var img = $("#topImg").attr('src');
		if(img.length==0){
			$("#topimg").css("display", "none");
		}else{
			var index_html = $("ul.nav_menu").find("li.nav_menu-item:first").data('link');
			if(path==index_html){
				$("#topimg").css("display", "none");
			}else{
				$("#topimg").css("display", "block");
			}
		}
		
		var name = $("#menuA").html();
		if(name.length==0){
			$(".here").css("display", "none");
		}else{
			var index_html = $("ul.nav_menu").find("li.nav_menu-item:first").data('link');
			if(path==index_html){
				$(".here").css("display", "none");
			}else{
				$(".here").css("display", "block");
			}
		}
	     
    });
    
</script>


<div class="topimg" id="topimg"><img id="topImg" src=""/></div>
<div class="head_box">
	<div class="top">
		<div class="fl">${setting.slogan!"爱跑汇体育欢迎您！坚持，过程，幸福……"}</div>
		<div class="fr login"><span class="call">${setting.tel!"400-020-0110"}</span>
			<#if currentMember()??>
				<span><a href="">${currentMember().name!currentMember().mobile}</a></span>
				<span><a href="${basepath}/member/home.html" style="color:#ed710c; font-weight:600;">个人中心</a></span>
			<#else>
				<span><a href="${basepath}/member/login.html?a=login">登录</a></span>
				<span><a href="${basepath}/member/login.html?a=regist">注册</a></span>
			</#if>
		</div>
	</div>
	<div class="header">
		<div class="logo fl"><a href=""><img src="${setting.logo}"></a></div>
		<div class="nav fr" id="menu_div">
		  <ul class="nav_menu">
		  
		  <#list navIndexs as navIndex>
						<li class="nav_menu-item" data-select="${navIndex.id}" data-select="${navIndex.id}" data-name="${navIndex.name}" data-img="${navIndex.banner!''}" data-link="${basepath}/${navIndex.url!''}">
							<#if navIndex.children?? && navIndex.children?size gt 0>
								<a class="menu-item" data-name="${navIndex.name}" data-select="${navIndex.id}" data-name="${navIndex.name}" data-img="${navIndex.banner!''}">${navIndex.name}</a>
								<ul class="nav_submenu">
	                            	<#list navIndex.children as item>
	                                	<li class="nav_submenu-item" data-select="${item.id}" data-name="${item.name}" data-img="${item.banner!''}"  data-link="${basepath}/${item.url!''}">
	                                    	<a class="menu-item" href="${basepath}/${item.url}">${item.name!""}</a>
	                                	</li>
	                            	</#list>
	                            </ul>
	                        <#else>
	                        	<a class="menu-item" href="${basepath}/${navIndex.url}">${navIndex.name}</a>
							</#if>
						</li>
					</#list>
		  </ul>
		</div>
	</div>
</div>
<div class="here">
	<div class="w1200">
		<a href="${basepath}/index.html">首页</a><span>>></span><a id="menuA" href="#"></a>
	</div>
</div>
</#macro>
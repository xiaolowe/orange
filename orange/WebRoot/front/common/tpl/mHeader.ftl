<#macro header navIndexs=[] topIndex="" currentIndex="" setting=[] indexImg="">
<script>
    $(function(){
        $("a.menu-item").each(function(index){
            var href = $(this).attr("href");
            if(href=="http://192.168.0.66:9092/cms/www/admin.html"){
            	$(this).attr("href", href);
            }else{
            	$(this).attr("href", href==""?"#": href);
            }
        });
        
        $("a.menu-item").click(function(index){
        	var parent = $(this).parent();
        	if(parent.is("li.nav_menu-item")){
        		index_select_name = $(parent).data("name");
        		index_select_index_name = '';
        		index_select_index_img = $(parent).data("img");
        	}
        	if(parent.is("li.nav_submenu-item")){
        		index_select_name = $(parent.parent().parent()).data("name");
        		index_select_index_name = $(parent).data("name");
        		index_select_index_img = $(parent).data("img");
        	}
	        setCookie("jsession_index_selected_name", index_select_name);
	        setCookie("jsession_index_selected_index_name", index_select_index_name);
	        setCookie("jsession_index_selected_index_img", index_select_index_img);
		});
    });
    
    function logout(){
    	delAllCookie();
	 			$.ajax({
					url:"${basepath}/rest/m/logout",
					type:"post",
					data:{},
					dataType:"text",
					success:function(data){
						if(data=='ok'){
							alert("退出登录成功");
							window.location.href= basepath + "/index.html"
						}
					},
					error:function(){
					}
				});
    }	
    
</script>

<style type="text/css">
.top .login a .on{
 	color: red;
}
</style>

<div class="topimg"><img id="topImg" src="${frontpath}/images/member_bg.jpg"/></"/></div>
<div class="head_box">
	<div class="top">
		<div class="fl">${setting.slogan!"爱跑汇体育欢迎您！坚持，过程，幸福……"}</div>
		<div class="fr login"><span class="call">${setting.tel!"400-020-0110"}</span>
			<#if currentMember()??>
				<span><a href="">${currentMember().name!currentMember().mobile}</a></span>
				<span><a href="javascript:void(0);" onclick="logout();" style="color:#ed710c; font-weight:600;">退出登录</a></span>
			<#else>
				<span><a href="${basepath}/member/login.html?a=login">登录</a></span>
				<span><a href="${basepath}/member/login.html?a=regist">注册</a></span>
			</#if>
		</div>
	</div>
	<div class="header">
		<div class="logo fl"><a href=""><img src="${setting.logo}"></a></div>
		<div class="nav fr">
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
</#macro>
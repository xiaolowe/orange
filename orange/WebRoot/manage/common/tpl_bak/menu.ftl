<#macro menu menus=[] topMenu="" currentMenu="首页">
<script>
    $(function(){
       	$("a.item_menu_p").each(function(){
            $(this).click(function(){
        		$(this).parent().addClass("active open");
    		});
        })
       /*$("a.item_menu").each(function(){
            $(this).click(function(){
        		$(this).parent().addClass("active");
    		});
       })*/
    });
</script>


		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu page-sidebar-menu-light" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<li class="sidebar-search-wrapper">
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
					<form class="sidebar-search " action="#" method="POST">
						<a href="javascript:;" class="remove">
						<i class="icon-close"></i>
						</a>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
							<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
							</span>
						</div>
					</form>
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
				
				<#list menus as menu>
				
                    <li >
                    	<a href="${basepath + menu.url}" data-href="${basepath + menu.url}" class="item_menu_p">
							<i class="icon-home"></i>
							<span class="title">${menu.name!""}</span>
							<#if menu.children?? && menu.children?size gt 0>
								<#if currentMenu==menu.name>
									<span class="selected"></span>
									<span class="arrow open"></span>
								<#else>
									<span class="arrow"></span>
								</#if>
							<#else>
								<#if currentMenu==menu.name>
									<span class="selected"></span>
								</#if>
							</#if>
							
						</a>
                        
                        <#if menu.children?? && menu.children?size gt 0>
                            <ul class="sub-menu">
                            <#list menu.children as menuc>
                                <li>
                                    <a href="${basepath + menuc.url}" data-href="${basepath + menu.url}" class="item_menu">
                                    	<i class="fa fa-files-o fa-fw"></i> 
                                    	${menuc.name!""}
                                    </a>
                                </li>
                            </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>
				
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	<!-- END SIDEBAR -->
</#macro>
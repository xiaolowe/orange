<#import "htmlBase.ftl" as html />
<#import "menu.ftl" as menu />
<#import "header.ftl" as header />
<#import "footer.ftl" as footer />
<#import "jsBase.ftl" as jsBase />
<#--currentMenu:当前菜单项-->
<#macro pageBase currentMenu topMenu="" title="" jsFiles=[] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin=true>
<@html.htmlBase title=title jsFiles=jsFiles cssFiles=cssFiles staticJsFiles=staticJsFiles staticCssFiles=staticCssFiles checkLogin=checkLogin>
<body class="page-header-fixed page-header-fixed-mobile page-footer-fixed page-footer-fixed-mobile page-container-bg-solid">
    <!-- Start Header-->
    <div class="page-header navbar navbar-fixed-top">
		<@header.header />
	</div>
    <!-- END HEADER -->
    <div class="clearfix"></div>
    
    <!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<@menu.menu menus=userMenus topMenu=topMenu currentMenu=currentMenu/>	
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN STYLE CUSTOMIZER(optional) -->
				<div class="theme-panel hidden-xs hidden-sm">				
				</div>
				<!-- END STYLE CUSTOMIZER -->
						
				<!-- BEGIN ACTUAL CONTENT -->
				<div ui-view class="fade-in-up">
				</div> 
				<!-- END ACTUAL CONTENT -->
			</div>
		</div>
		<!-- END CONTENT -->
		<!-- BEGIN QUICK SIDEBAR -->
		<a href="javascript:;" class="page-quick-sidebar-toggler">
			<i class="icon-close"></i>
		</a>
		<div class="page-quick-sidebar-wrapper">
		</div>
		<!-- END QUICK SIDEBAR -->
    </div>
    <!-- END CONTAINER -->
    <!-- BEGIN FOOTER -->
	<div class="page-footer">
		<@footer.footer />
	</div>
	<!-- END FOOTER -->

<@jsBase.jsBase/>
<script type="text/javascript" src="${staticpath}/manage.js"></script>
</body>
</@html.htmlBase>
</#macro>
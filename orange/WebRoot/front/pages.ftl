<#import "/front/common/tpl/fPageBase.ftl" as page>

<@page.pageBase currentIndex="pages" jsFiles=["jquery-1.10.2.min.js","pageGroup.js"] cssFiles=["main.css"]  checkLogin=false>
<div class="about_nav">
	<#if catalogs?size gt 1>
		<#list catalogs as item>
			<a href="${basepath}/pages/${catelogId}/${item.id}.html" <#if item.id==cte.id>class="on"</#if>>${item.name}</a>
		</#list>
	</#if>
</div>

<div class="article ">
	<div class="w1200">
		<div class="content">
			${cte.content!''}
		</div>
	</div>
</div>
</@page.pageBase>
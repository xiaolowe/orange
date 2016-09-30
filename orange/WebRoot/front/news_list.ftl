<#import "/front/common/tpl/fPageBase.ftl" as page>

<@page.pageBase currentIndex="news_list" jsFiles=["jquery-1.10.2.min.js"] cssFiles=["main.css"] checkLogin=false>
	
<div class="news_list">
	<#list pager.list as item>
		<div class="news_list_item">
		<div class="left">
			<a href="${basepath}/news/${type}/${item.id}.html">
				<img src="${item.sltImg}" class="zoom"/>
			</a>
		</div>
		<div class="right">
			<div class="title"><a href="${basepath}/news/${type}/${item.id}.html">${item.title}</a></div>
			<div class="time">${item.publishTime}</div>
			<div class="desc"><#if item.digest?? && item.digest?length gt 50>${item.digest?substring(0,50)}...<#else>${item.digest!""}</#if></div>
			<div class="go_button"><a href="${basepath}/news/${type}/${item.id}.html" target="_blank">查看详情</a></div>
		</div>
	</div>
	</#list>
</div>
 <#include "/front/common/tpl/pager.ftl"/>
</@page.pageBase>
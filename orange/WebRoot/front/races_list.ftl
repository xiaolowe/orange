<#import "/front/common/tpl/fPageBase.ftl" as page>

<@page.pageBase currentIndex="races_list" jsFiles=["jquery-1.10.2.min.js","pageGroup.js"] cssFiles=["main.css"] checkLogin=false>

<div class="game_list w1200">
	<#list pager.list as item>
		<div class="game_list_item">
		<div class="game_left">
			<#assign images = item.ads?split(";")>
			<#list images as image>
				<#if image_index==0>
					<a href="${basepath}/races/${type}/${item.id}.html"><img src="${image}"  title="${item.name}"/></a>
				</#if>
			</#list>
		</div>
		<div class="game_right">
			<div class="title"><a href="${basepath}/races/${type}/${item.id}.html">${item.name}</a></div>
			<p class="info"><span>时间：</span>${item.time}</p>
			<p class="info"><span>地点：</span>${item.address}</p>
			<p class="intro"><span>简介：</span>${item.descs}</p>
			<div class="join">
				
					<#if type?default('cases')=='events'><a href="${basepath}/races/${type}/${item.id}.html">立即参赛</a></#if>
					<#if type?default('cases')=='cases'><a href="${basepath}/races/${type}/${item.id}.html">查看详情</a></#if>
					<#if type?default('cases')=='scores'><a href="${basepath}/races/scores/${item.id}.html">查询成绩</a></#if>
			</div>
		</div>
	</div>
	
	</#list>
</div>
 <#include "/front/common/tpl/pager.ftl"/>
</@page.pageBase>
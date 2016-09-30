<#assign pg = JspTaglibs["/WEB-INF/jsp/pager-taglib.tld"]/>
<style type="text/css">
.pageLink {
	border: 1px solid #dddddd;
	padding: 4px 12px;
	text-decoration: none;
}

.selectPageLink {
	border: 1px solid #0088cc;
	padding: 4px 12px;
	color: #0088cc;
	background-color: #dddddd;
	text-decoration: none;
}
</style>
	<!-- 分页标签 -->
	<div style="text-align: right; border: 0;padding: 4px 12px;" class="pageDiv">
		<@pg.pager url="${pager.pagerUrl}" items=pager.total
			export="currentPageNumber=pageNumber"
			maxPageItems=pager.pageSize maxIndexPages=5 isOffset=true>
					总共：${pager.total}条,共:${pager.pagerSize}页
                <@pg.param name="cc"/>
                
			<@pg.first>
			<#if pager.pagerSize gt 1>
					<!--<a href="${pageUrl}" class="pageLink">首页</a>-->
					<a href="#" class="pageLink" onclick="selectPageList('${pageUrl}')">首页</a>
			<#else>
				<span class="pageLink">首页</span>
            </#if>	
			</@pg.first>
			<@pg.prev>
				<!--<a href="${pageUrl}" class="pageLink">上一页</a>-->
				<a href="#" class="pageLink" onclick="selectPageList('${pageUrl}')">上一页</a>
			</@pg.prev>
			<@pg.pages>
            <#if currentPageNumber==pageNumber>
                <span class="selectPageLink">${pageNumber}</span>
                <#else >
                    <!--<a href="${pageUrl}" class="pageLink">${pageNumber}</a>-->
                    <a href="#" class="pageLink" onclick="selectPageList('${pageUrl}')">${pageNumber}</a>
            </#if>
			</@pg.pages>
			<@pg.next>
				<!--<a href="${pageUrl}" class="pageLink">下一页</a>-->
				<a href="#" class="pageLink" onclick="selectPageList('${pageUrl}')">下一页</a>
			</@pg.next>
			<@pg.last>
			<#if pager.pagerSize gt 1>
					<!--<a href="${pageUrl}" class="pageLink">尾页</a>-->
					<a href="#" class="pageLink" onclick="selectPageList('${pageUrl}')">尾页</a>
			<#else>
				<span class="pageLink">尾页</span>
            </#if>	
			</@pg.last>
		</@pg.pager>
	</div>

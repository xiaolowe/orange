<#assign pg = JspTaglibs["/WEB-INF/jsp/pager-taglib.tld"]/>
<style type="text/css">
.pageLink {
	  font-size: 12px;
  color: #999;
  line-height: 23px;
  float: left;
  margin-left: 5px;
  width: 22px;
  text-align: center;
  border: 1px solid #999;
  cursor: pointer;
}

.selectPageLink {
	color: #fff;
  	background: #004f85;
  	border: 1px solid #004f85;
  	width: 22px;
  	text-align: center;
  	font-size: 12px;
  	color: #fff;
  	line-height: 23px;
  	float: left;
  	margin-left: 5px;
}

.indexPage{
	font-size: 12px;
  color: #999;
  line-height: 23px;
  float: left;
  margin-left: 5px;
  width: 63px;
  border: 1px solid #999;
  cursor: pointer;
	
}

.prePage{
text-indent: 23px;
  background: url(${frontpath}/images/pageUp.png) 5px 7px no-repeat;
}

.nextPage{
	text-indent: 5px;
  	background: url(${frontpath}/images/pageDown.png) 46px 6px no-repeat;
}

</style>
	<!-- 分页标签 -->
<div class="pages w1200">
<div id="pageGro" class="cb">
		<@pg.pager url="${pager.pagerUrl}" items=pager.total
			export="currentPageNumber=pageNumber"
			maxPageItems=pager.pageSize maxIndexPages=5 isOffset=true>
                <@pg.param name="cc"/>
                
			<@pg.first>
				
			</@pg.first>
			<@pg.prev>
					<a href="${basepath}/${pageUrl}" class="indexPage prePage">上一页</a>
			</@pg.prev>
			<@pg.pages>
            	<#if currentPageNumber==pageNumber>
                	<span class="selectPageLink">${pageNumber}</span>
                <#else >
                    <a href="${basepath}/${pageUrl}" class="pageLink">${pageNumber}</a>
            	</#if>
			</@pg.pages>
			<@pg.next>
				<a href="${basepath}/${pageUrl}" class="indexPage nextPage">下一页</a>
			</@pg.next>
			<@pg.last>
			
			</@pg.last>
		</@pg.pager>
	</div>
</div>

<#import "/manage/common/tpl/pageBase.ftl" as page>

<@page.pageBase currentMenu="赛事资讯">



<form action="${basepath}/news/race" method="post" class="form-horizontal">
<input type="hidden" name="type" id="type" value="${e.type!""}">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-2">标题</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="title" name="title" value="${e.title!""}"/>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-2">作者</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="author" name="author" value="${e.author!""}"/>
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-2">状态</label>
					<div class="col-md-10">
						<#assign map = {'':'全部','1':'已发布','0':'未发布'}>
                    	<select id="state" name="state" class="form-control">
                        	<#list map?keys as key>
                            	<option value="${key}" <#if e.state?? && e.state==key>selected="selected" </#if>>${map[key]}</option>
                        	</#list>
						</select>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-2">推荐</label>
					<div class="col-md-10">
						<#assign map = {'':'全部','1':'推荐','0':'普通'}>
                    	<select id="recommend" name="recommend" class="form-control">
                        	<#list map?keys as key>
                            	<option value="${key}" <#if e.recommend?? && e.recommend==key>selected="selected" </#if>>${map[key]}</option>
                        	</#list>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-2">置顶</label>
					<div class="col-md-10">
						<#assign map = {'':'全部','1':'置顶','0':'普通'}>
                    	<select id="stick" name="stick" class="form-control">
                        	<#list map?keys as key>
                            	<option value="${key}" <#if e.stick?? && e.stick==key>selected="selected" </#if>>${map[key]}</option>
                        	</#list>
						</select>
					</div>
				</div>
			</div>
			
			
		</div>
		
		
	
	<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<#if checkPrivilege("/news/race/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
					<#if checkPrivilege("/news/race/insert") >
						<a class="btn btn-success" href="${basepath}/news/race/toAdd">
						添加<i class="fa fa-plus"></i></a>
					</#if>
					<#if checkPrivilege("/news/race/deletes")>
						<a class="btn btn-danger" onclick="submitIDs(this,'确定删除选择的记录?');" method="deletes" >
						删除<i class="fa fa-times"></i></a>
	                 </#if>
	                 
                 </div>
			</div>
			<div class="col-md-6">
                 <#include "/manage/common/pager.ftl"/>
			</div>
		</div>
   
		<div class="table-scrollable">
			<table class="table table-striped table-bordered table-hover dataTable no-footer" id="sample_1" role="grid" aria-describedby="sample_1_info">
				<thead>
					<tr role="row">
						<th style="width:10px;">
								<input type="checkbox" id="firstCheckbox"/>
						</th>
						<th rowspan="1" colspan="1" aria-label="标题">
							 标题
						</th>
						<th rowspan="1" colspan="1" aria-label="作者">
							作者
						</th>
						<th rowspan="1" colspan="1" aria-label="是否推荐">
							是否推荐
						</th>
						<th rowspan="1" colspan="1" aria-label="是否置顶">
							是否置顶
						</th>
						<th rowspan="1" colspan="1" aria-label="创建时间">
							创建时间
						</th>
						<th rowspan="1" colspan="1" aria-label="更新时间">
							更新时间
						</th>
						<th rowspan="1" colspan="1" aria-label="发布时间">
							发布时间
						</th>
						<th rowspan="1" colspan="1" aria-label="操作">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<#list pager.list as item>
						<tr role="row">
							<td style="width:10px; padding-left:18px;">
								<input type="checkbox" name="ids" value="${item.id}"/>
							</td>
							<td style="display: none;">&nbsp;${item.id!""}</td>
							<td>${item.title!""}</td>
							<td>${item.author!""}</td>
							<td>
			                    <#if item.recommend == "1">
			                        <span class="label label-sm label-danger">推荐</span>
			                    <#else >
			                       <span class="label label-sm label-default">普通</span>
			                    </#if>
			                    <#if checkPrivilege("/news/race/recommend")>
			                    	 <#if item.recommend == "0">
			                        	<a class="label label-sm label-danger" method="recommend" onclick="recommend(this,'${item.id}','确定推荐?', '1');">添加推荐</a>
			                    	<#else >
			                       		<a class="label label-sm label-default" method="recommend" onclick="recommend(this,'${item.id}','确定撤消推荐?', '0');">撤消推荐</a>
			                    	</#if>
			                    </#if>
							</td>
							<td>
			                    <#if item.stick == "1">
			                        <span class="label label-sm label-success">置顶</span>
			                    <#else >
			                       <span class="label label-sm label-warning">普通</span>
			                    </#if>
			                   
			                    <#if checkPrivilege("/news/race/stick")>
			                    	 <#if item.stick == "0">
			                        	<a class="label label-sm label-success" method="stick" onclick="stick(this,'${item.id}','确定置顶?', '1');">添加置顶</a>
			                    	<#else >
			                       		<a class="label label-sm label-warning" method="stick" onclick="stick(this,'${item.id}','确定撤消置顶?', '0');">撤消置顶</a>
			                    	</#if>
			                    </#if>
							</td>
							<td>
			                   ${item.createTime!""}
							</td>
							<td>
			                   ${item.updateTime!""}
							</td>
							<td>
			                    <#if item.state == "1">
			                         ${item.publishTime!""}
			                    <#else >
			                       <span class="label label-sm label-primary">未发布</span>
			                    </#if>
							</td>
							<td>
								<#if checkPrivilege("/news/race/show")>
			                        <a href="${basepath}/news/race/show?id=${item.id}" target="_blank">预览</a>
			                    </#if>
			                    <#if checkPrivilege("/news/race/update")>
			                        <a href="${basepath}/news/race/toEdit?id=${item.id}">编辑</a>
			                    </#if>
			                    <#if checkPrivilege("/news/race/publish")>
			                    	 <#if item.state == "0">
			                        	<a class="label label-sm label-success" method="publish" onclick="publish(this,'${item.id}','确定发布?', '1');">发布</a>
			                    	<#else >
			                       		<a class="label label-sm label-warning" method="publish" onclick="publish(this,'${item.id}','确定撤消发布?', '0');">撤消</a>
			                    	</#if>
			                    </#if>
							</td>
						</tr>
			        </#list>
				</tbody>
			</table>
		</div>
		<div class="row">
             <#include "/manage/common/pager.ftl"/>
		</div>
</form>
<script type="text/javascript">

function publish(obj, id,tip, flag){

	if(confirm(tip)){
		$.ajax({
					url:"${basepath}/news/race/publish",
					type:"post",
					data:{ids:id},
					dataType:"text",
					success:function(data){
						if(data==1){
							window.location.reload();
						}else{
							if(flag == "1"){
								alert("发布失败！");
							}else{
								alert("撤消发布失败！");
							}
						}
					},
					error:function(){
						if(flag == "1"){
								alert("发布失败！");
							}else{
								alert("撤消发布失败！");
							}
					}
				});
	}
	return false;
}

function recommend(obj, id,tip, flag){

	if(confirm(tip)){
		$.ajax({
					url:"${basepath}/news/race/recommend",
					type:"post",
					data:{ids:id},
					dataType:"text",
					success:function(data){
						if(data==1){
							window.location.reload();
						}else{
							if(flag == "1"){
								alert("推荐失败！");
							}else{
								alert("取消推荐失败！");
							}
						}
					},
					error:function(){
						if(flag == "1"){
							alert("推荐失败！");
						}else{
							alert("取消推荐失败！");
						}
					}
				});
	}
	return false;
}

function stick(obj, id,tip, flag, slt){
	if(confirm(tip)){
		$.ajax({
					url:"${basepath}/news/race/stick",
					type:"post",
					data:{ids:id},
					dataType:"text",
					success:function(data){
						if(data==1){
							window.location.reload();
						}else{
							if(flag == "1"){
								alert("置顶失败！");
							}else{
								alert("取消置顶失败！");
							}
						}
					},
					error:function(){
						if(flag == "1"){
							alert("置顶失败！");
						}else{
							alert("取消置顶失败！");
						}
					}
				});
	}
	return false;
}

</script>

</@page.pageBase>
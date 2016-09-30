<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="门户滚动图片">

<form action="${basepath}/statics/banner" method="post" class="form-horizontal">

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">名称</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="name" name="name" value="${e.name!""}"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">状态</label>
					<div class="col-md-10">
						<#assign map = {'':'全部','1':'启用','0':'禁用'}>
                    	<select id="status" name="status" class="form-control">
                        	<#list map?keys as key>
                            	<option value="${key}" <#if e.status?? && e.status==key>selected="selected" </#if>>${map[key]}</option>
                        	</#list>
						</select>
					</div>
				</div>
			</div>
		</div>
		
	
	<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<#if checkPrivilege("/statics/banner/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
					<#if checkPrivilege("/statics/banner/insert") >
						<a class="btn btn-success" href="${basepath}/statics/banner/toAdd">
						添加<i class="fa fa-plus"></i></a>
					</#if>
					<#if checkPrivilege("/statics/banner/deletes")>
						<a class="btn btn-danger" onclick="submitIDs(this,'确定删除选择的记录?');" method="deletes" >
						删除<i class="fa fa-times"></i></a>
	                 </#if>
	                 <#if checkPrivilege("/statics/banner/statics")>
						<a class="btn btn-primary" onclick="statics('确定静态化?');">
						静态化</a>
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
						<th rowspan="1" colspan="1" aria-label="名称">
							名称
						</th>
						<th rowspan="1" colspan="1" aria-label="图标">
							图标
						</th>
						<th rowspan="1" colspan="1" aria-label="状态">
							状态
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
							<td>
			                    ${item.name!""}
							</td>
							<td>
								<a href="${item.banner!""}" target="_blank" alt="${item.banner!""}">
									<img style="height: 40px; width:120px;" src="${item.img}" alt="${item.name}" />
								</a>
							</td>
							<td>
			                    <#if item.status == "1">
			                        <span class="label label-sm label-success">启用 </span>
			                    <#else >
			                       <span class="label label-sm label-warning">禁用 </span>
			                    </#if>
							</td>
							<td>
			                    <#if checkPrivilege("/statics/banner/update")>
			                        <a href="${basepath}/statics/banner/toEdit?id=${item.id}">编辑</a>
			                    </#if>
			                    <#if checkPrivilege("/statics/banner/work")>
			                     	<#if item.status == "0">
			                        	<a class="label label-sm label-success" method="work" onclick="work(this,'${item.id}','确定启用?', '1');">启用</a>
			                    	<#else >
			                       		<a class="label label-sm label-warning" method="work" onclick="work(this,'${item.id}','确定禁用?', '0');">禁用</a>
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

function work(obj, id,tip, flag){

	if(confirm(tip)){
		$.ajax({
					url:"${basepath}/statics/banner/work",
					type:"post",
					data:{ids:id},
					dataType:"text",
					success:function(data){
						if(data==1){
							window.location.reload();
						}else{
							if(flag == "1"){
								alert("启用失败！");
							}else{
								alert("禁用失败！");
							}
							
						}
					},
					error:function(){
						if(flag == "1"){
								alert("启用失败！");
							}else{
								alert("禁用失败！");
							}
					}
				});
	}
	return false;
}

function statics(tip){
	if(confirm(tip)){
		$.ajax({
					url:"${basepath}/statics/banner/statics",
					type:"post",
					data:{},
					dataType:"text",
					success:function(data){
						if(data==1){
							alert("静态化成功");
							window.location.reload();
						}else{
							alert("静态化失败");
						}
					},
					error:function(){
						alert("静态化失败");
					}
				});
	}
	return false;
}

</script>
</@page.pageBase>
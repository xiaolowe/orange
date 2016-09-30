<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="赛事信息">

<form action="${basepath}/race/info" method="post" class="form-horizontal">

		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-2">名称</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="name" name="name" value="${e.name!""}"/>
					</div>
				</div>
			</div>
			<div class="col-md-4">
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
					<#if checkPrivilege("/race/info/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
					<#if checkPrivilege("/race/info/insert") >
						<a class="btn btn-success" href="${basepath}/race/info/toAdd">
						添加<i class="fa fa-plus"></i></a>
					</#if>
					<#if checkPrivilege("/race/info/deletes")>
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
						<th rowspan="1" colspan="1" aria-label="名称">
							名称
						</th>
						<th rowspan="1" colspan="1" aria-label="比赛时间">
							比赛时间
						</th>
						<th rowspan="1" colspan="1" aria-label="赛事状态">
							赛事状态
						</th>
						<th rowspan="1" colspan="1" aria-label="地点">
							地点
						</th>
						<th rowspan="1" colspan="1" aria-label="报名数">
							报名数
						</th>
						<th rowspan="1" colspan="1" aria-label="是否允许儿童">
							是否允许儿童
						</th>
						<th rowspan="1" colspan="1" aria-label="报名开始时间">
							报名开始时间
						</th>
						<th rowspan="1" colspan="1" aria-label="报名结束时间">
							报名结束时间
						</th>
						<th rowspan="1" colspan="1" aria-label="报名状态">
							报名状态
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
								 ${item.time!""}
							</td>
							<td>
								<#if item.isFinish == "1">
				                	<span class="label label-sm label-danger">已开赛</span>
				             	<#else>
				             		<span class="label label-sm label-default">未开赛</span>
				             	</#if>
				             	<a class="label label-sm label-success" href="${basepath}/race/addon/scores?raceId=${item.id}&isTeam=${item.isTeam}">成绩录入</a>
				            </td>
							<td>
			                     ${item.address!""}
							</td>
							<td>
			                    <#if checkPrivilege("/race/addon/selectList")>
									<a href="${basepath}/race/addon/selectList?raceId=${item.id}">${item.num}</a>
								<#else>
									${item.num!"0"}
	                			</#if>
							</td>
							<td>
			                     <#if item.isChild?default('0')=="1">是<#else>否</#if>
							</td>
							<td>
			                     ${item.startTime!''}
							</td>
							<td>
			                     ${item.endTime!''}
							</td>
				            <td>
								<#if item.isStop?default('0') == "1">
				                	<span class="label label-sm label-danger">人工停止</span>
				             	<#else>
				             		<#if item.isGoing == "1">
				                		<#if item.isLimit?default('0')=='1'>
				                			 <#if item.limitNum?? && item.limitNum!=0 && (item.num >=item.limitNum?number)>
				                				<span class="label label-sm label-primary">报名已满</span>
							                <#else>
							                	<span class="label label-sm label-success">进行中</span>
							                </#if>
				                		<#else>
				                			<span class="label label-sm label-success">进行中</span>
				                		</#if>
				                	<#else>
				                		<#if item.isStart == "0">
				                			<span class="label label-sm label-default">未开始</span>
				                		<#else>
				                			<#if item.isEnd == "1">
				                				<span class="label label-sm label-primary">已结束</span>
				                			</#if>
				                		</#if>
				                	</#if>
				             	</#if>
				            </td>
				            <td>
								<#if item.status?? && item.status == "1">
				                	<span class="label label-sm label-success">启用 </span>
				             	<#else >
				                    <span class="label label-sm label-warning">禁用 </span>
				                </#if>
				            </td>
							<td>
			                    <#if checkPrivilege("/race/info/update")>
			                        <a href="${basepath}/race/info/toEdit?id=${item.id}">编辑</a>
			                    </#if>
			                    <#if checkPrivilege("/race/info/work")>
				                   <#if item.status?? && item.status == "0">
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
					url:"${basepath}/race/info/work",
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

</script>
</@page.pageBase>
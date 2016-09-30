<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="报名管理">

<form action="${basepath}/race/addon" method="post" class="form-horizontal">

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">赛事名称</label>
					<div class="col-md-8">
						<select name="raceId" id="raceId" class="form-control">
						        <option value="" >全部</option>
		                	<#list races as item>
		                   		<option value="${item.id}" <#if e.raceId?? && item.id?string == e.raceId?string>selected="selected"</#if>> ${item.name}</option>
		                	</#list>
                		</select>
					</div>
				</div>
			</div>
		</div>
		
	
		<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<#if checkPrivilege("/race/addon/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
						<button class="btn btn-info" onclick="downloadList(this)" method="downloadList">
                        下载名单 <i class="fa fa-download"></i></button>
	                </#if>
					<#if checkPrivilege("/race/addon/insert") >
						<a class="btn btn-success" href="${basepath}/race/addon/toAdd">
						添加<i class="fa fa-plus"></i></a>
					</#if>
					<#if checkPrivilege("/race/addon/deletes")>
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
						<th rowspan="1" colspan="1" aria-label="赛事名称">
							赛事名称
						</th>
						<th rowspan="1" colspan="1" aria-label="报名时间">
							报名时间
						</th>
						<th rowspan="1" colspan="1" aria-label="报名人">
							报名人
						</th>
						<th rowspan="1" colspan="1" aria-label="联系方式">
							联系方式
						</th>
						<th rowspan="1" colspan="1" aria-label="报名分组">
							报名分组
						</th>
						<th rowspan="1" colspan="1" aria-label="报名人数">
							报名人数
						</th>
						<th rowspan="1" colspan="1" aria-label="报名费用">
							报名费用
						</th>
						<th rowspan="1" colspan="1" aria-label="支付金额">
							支付金额
						</th>
						<th rowspan="1" colspan="1" aria-label="支付状态">
							支付状态
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
			                    ${item.rName!""}
							</td>
							<td>
			                     ${item.createTime!""}
							</td>
							<td>
			                     <#if item.gType==2>[${item.tName!''}]</#if>${item.contactor!""}
							</td>
							<td>
			                     ${item.mobile!""}
							</td>
							<td>
								【<#if item.gType==2>团队<#else>个人</#if>】${item.gName}
							</td>
							<td>
								${item.num}
							</td>
							<td>
								${item.rPrice}
							</td>
							<td>
								${item.rPrice?number * item.num}
							</td>
							<td>
			                   <#if item.status?default('0')=='1'>
			                   		<span class="label label-sm label-success">已支付</span>
			                   <#else>
			                   		<span class="label label-sm label-danger">未支付</span>
			                   		 <a href="javascript:void(0);" onclick="javascript:cancel('${item.id}', '${item.teamId}');">删除</a>
			                   	</#if>
							</td>
							<td>
			                    <#if checkPrivilege("/race/addon/update")>
			                        <a href="${basepath}/race/addon/toEdit?id=${item.id}">编辑</a>
			                    </#if>
			                    <#if checkPrivilege("/race/addon/show")>
			                        <a href="${basepath}/race/addon/show?teamId=${item.teamId}">详细</a>
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
function cancel(addonId, teamId){
	var tip="确定取消报名？";
	if(confirm(tip)){
		$.ajax({
					url:"${basepath}/race/addon/cancel",
					type:"post",
					data:{ids:addonId,
					teamId:teamId
					},
					dataType:"text",
					success:function(data){
						if(data==1){
							alert("取消报名成功");
							window.location.reload();
						}else{
							alert("取消报名失败");
						}
					},
					error:function(){
						alert("系统错误");
					}
				});
	}
}
</script>
</@page.pageBase>
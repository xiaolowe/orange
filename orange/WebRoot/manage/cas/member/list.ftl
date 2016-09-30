<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="会员管理">

<form action="${basepath}/cas/member" method="post" class="form-horizontal">

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">用户名</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="name" name="name" value="${e.name!""}"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">手机</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="mobile" name="mobile" value="${e.mobile!""}"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
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
					<#if checkPrivilege("/cas/member/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
					<#if checkPrivilege("/cas/member/insert") >
						<a class="btn btn-success" href="${basepath}/cas/member/toAdd">
						添加<i class="fa fa-plus"></i></a>
					</#if>
					<#if checkPrivilege("/cas/member/deletes")>
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
						<th rowspan="1" colspan="1" aria-label="帐号">
							帐号
						</th>
						<th rowspan="1" colspan="1" aria-label="用户名">
							用户名
						</th>
						<th rowspan="1" colspan="1" aria-label="注册时间">
							注册时间
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
			                     ${item.mobile!""}
							</td>
							<td>
			                     ${item.name!""}
							</td>
							<td>${item.createTime!""}</td>
							<td>
			                    <#if item.status == "1">
			                        <span class="label label-sm label-success">启用 </span>
			                    <#else >
			                       <span class="label label-sm label-warning">禁用 </span>
			                    </#if>
							</td>
							<td>
								<a href="${basepath}/cas/member/show?id=${item.id}" target="_blank">查看</a>
			                    <#if checkPrivilege("/cas/member/update")>
			                        <a href="${basepath}/cas/member/toEdit?id=${item.id}">编辑</a>
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
</@page.pageBase>
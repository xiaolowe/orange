<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="用户管理">

<form action="${basepath}/cas/user" method="post" class="form-horizontal">

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">帐号</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="username" name="username" value="${e.username!""}"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">昵称</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="nickname" name="nickname" value="${e.nickname!""}"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">状态</label>
					<div class="col-md-10">
						<#assign map = {'':'全部','y':'启用','n':'禁用'}>
                    	<select id="status" name="status" class="form-control">
                        	<#list map?keys as key>
                            	<option value="${key}" <#if e.status?? && e.status==key>selected="selected" </#if>>${map[key]}</option>
                        	</#list>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">角色</label>
					<div class="col-md-10">
						<select name="rid" class="form-control">
							<option value="">请选择角色</option>
		                	<#list roleList as item>
		                   		<option value="${item.id}" <#if e.rid?? && item.id?string == e.rid?string>selected="selected"</#if>> ${item.role_name}</option>
		                	</#list>
                		</select>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<#if checkPrivilege("/cas/user/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
					<#if checkPrivilege("/cas/user/insert") >
						<a class="btn btn-success" href="${basepath}/cas/user/toAdd">
						添加<i class="fa fa-plus"></i></a>
					</#if>
					<#if checkPrivilege("/cas/user/deletes")>
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
						<th rowspan="1" colspan="1" aria-label="昵称">
							 昵称
						</th>
						<th rowspan="1" colspan="1" aria-label="时间">
							时间
						</th>
						<th rowspan="1" colspan="1" aria-label="角色">
							角色
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
								<#if item.username!="admin" && currentUser().username != item.username ><input type="checkbox" name="ids" value="${item.id}"/></#if>
							</td>
							<td style="display: none;">&nbsp;${item.id!""}</td>
							<td>${item.username!""}</td>
							<td>${item.nickname!""}</td>
							<td>${item.createtime!""}</td>
							<td>${item.role_name}</td>
							<td>
			                    <#if item.status == "y">
			                        <span class="label label-sm label-success">启用 </span>
			                    <#else >
			                       <span class="label label-sm label-warning">禁用 </span>
			                    </#if>
							</td>
							<td>
								<a href="${basepath}/cas/user/show?account=${item.username}" target="_blank">查看</a>
			                    <#if checkPrivilege("/cas/user/update")>
			                        <a href="${basepath}/cas/user/toEdit?id=${item.id}">编辑</a>
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
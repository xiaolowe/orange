<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="角色管理">

<form action="${basepath}/cas/role" method="post" class="form-horizontal">

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">名称</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="role_name" name="role_name" value="${e.role_name!""}"/>
					</div>
				</div>
			</div>
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
		</div>
		
		<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<#if checkPrivilege("/cas/role/selectList")>
						<button class="btn btn-primary col-md-4" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
					<#if checkPrivilege("/cas/role/insert") >
						<a class="btn btn-success col-md-4" href="${basepath}/cas/role/toAdd">
						添加<i class="fa fa-plus"></i></button>
					</#if>
					<#if currentUser().rid=="1"  || checkPrivilege("/cas/role/deletes")>
						<a class="btn btn-danger col-md-4" onclick="submitIDs(this,'确定删除选择的记录?');" method="deletes" >
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
						<th rowspan="1" colspan="1" aria-label="描述">
							 描述
						</th>
						
						<th rowspan="1" colspan="1" aria-label="数据库权限">
							数据库权限
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
								<#if item.id!=1 && item.id?string('#') !=  currentUser().rid><input type="checkbox" name="ids" value="${item.id}"/></#if>
							</td>
							<td style="display: none;">&nbsp;${item.id!""}</td>
							<td>${item.role_name!""}</td>
							<td>${item.role_desc!""}</td>
							<td>${item.role_dbPrivilege}</td>
							<td>
			                    <#if item.status == "y">
			                        <span class="label label-sm label-success">启用 </span>
			                    <#else >
			                       <span class="label label-sm label-warning">禁用 </span>
			                    </#if>
							</td>
							<td>
			                    <!-- 系统角色只能是超级管理员编辑 -->
	                            <#if currentUser().username == "admin">
									<a href="${basepath}/cas/role/toEdit?id=${item.id}">编辑</a>
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
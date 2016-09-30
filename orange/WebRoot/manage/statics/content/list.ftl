<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="单页内容管理">

<form action="${basepath}/statics/content" method="post" class="form-horizontal">

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
					<label class="control-label col-md-2">编码</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="code" name="code" value="${e.code!""}"/>
					</div>
				</div>
			</div>
		</div>
		
	
	<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<#if checkPrivilege("/statics/content/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="selectList">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
					<#if checkPrivilege("/statics/content/insert") >
						<a class="btn btn-success" href="${basepath}/statics/content/toAdd">
						添加<i class="fa fa-plus"></i></a>
					</#if>
					<#if checkPrivilege("/statics/content/deletes")>
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
						<th rowspan="1" colspan="1" aria-label="编码">
							编码
						</th>
						<th rowspan="1" colspan="1" aria-label="分类">
							分类
						</th>
						<th rowspan="1" colspan="1" aria-label="链接地址">
							链接地址
						</th>
						<th rowspan="1" colspan="1" aria-label="时间">
							时间
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
			                     ${item.code!""}
							</td>
							<td>
								 ${item.catelog!""}
							</td>
							<td>
								pages/${item.catelogId}/${item.id}.html
							</td>
							<td>
			                    ${item.createTime!""}
							</td>
							<td>
			                    <#if checkPrivilege("/statics/content/update")>
			                        <a href="${basepath}/statics/content/toEdit?id=${item.id}">编辑</a>
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
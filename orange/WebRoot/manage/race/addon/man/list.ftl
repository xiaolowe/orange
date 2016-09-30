<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="报名管理">

		<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<button class="btn btn-primary" onclick="javascript:history.back(-1);">返回</button>
                 </div>
			</div>
		</div>
   
		<div class="table-scrollable">
			<table class="table table-striped table-bordered table-hover dataTable no-footer" id="sample_1" role="grid" aria-describedby="sample_1_info">
				<thead>
					<tr role="row">
						<th style="width:10px;">
							No.
						</th>
						<th rowspan="1" colspan="1" aria-label="姓名">
							姓名
						</th>
						<th rowspan="1" colspan="1" aria-label="手机">
							手机
						</th>
						<th rowspan="2" colspan="1" aria-label="报名人">
							身份证号码
						</th>
						<th rowspan="1" colspan="1" aria-label="性别">
							性别
						</th>
						<th rowspan="1" colspan="1" aria-label="用户类型">
							用户类型
						</th>
					</tr>
				</thead>
				<tbody>
					<#list mans as item>
						<tr role="row">
							<td style="width:10px; padding-left:18px;">
								${item_index+1}
							</td>
							<td>
			                    ${item.name!""}
							</td>
							<td>
			                     ${item.mobile!""}
							</td>
							<td>
			                     ${item.cardId!""}
							</td>
							<td>
			                     <#if item.gender?default('1')=='1'>男<#else>女</#if>
							</td>
							<td>
			                     <#if item.isChild?default('0')=='0'>成人<#else>儿童</#if>
							</td>
						</tr>
			        </#list>
				</tbody>
			</table>
		</div>

</@page.pageBase>
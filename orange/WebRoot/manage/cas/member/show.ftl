<#import "/manage/common/tpl/htmlBase.ftl" as html>
<@html.htmlBase>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">基本信息</a></li>
	</ul>
	<div id="tabs-1">
		<div class="alert alert-info" style="margin-bottom: 2px;text-align: left;">
			<strong>会员信息：</strong>
		</div>
		<table class="table table-bordered">
					<tr style="display: none;">
						<td>id</td>
						<td><input type="hidden" value="${e.id!""}" name="id" label="id" id="id"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">账号</td>   
						<td style="text-align: left;">${e.mobile!""}</td>
					</tr>
					<tr>
						<td style="text-align: right;">用户名</td>
						<td style="text-align: left;">${e.account!""}</td>
					</tr>
					<tr>
						<td style="text-align: right;">是否禁用</td>
						<td style="text-align: left;">
							<#if e.status=="1">
								<span class="badge badge-important">
									启用
								</span>
							<#else><span class="badge badge-success">禁用</span></#if>
						</td>
					</tr>
					
					<tr>
						<td style="text-align: right;">注册日期</td>
						<td style="text-align: left;">${e.createTime!""}</td>
					</tr>
				</table>
	</div>
</div>

<script type="text/javascript">
$(function() {
	$( "#tabs" ).tabs({
		//event: "mouseover"
	});
	
});

</script>

</@html.htmlBase>
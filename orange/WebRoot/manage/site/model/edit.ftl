<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="模板管理">

<script type="text/javascript">
	$(function() {
		 $("#name").focus();
	});
</script>

<body>
<#if e.id??>
    <#assign formAction="">
	<#assign insertAction=false />
<#else >
	<#assign formAction="insert">
    <#assign insertAction=true />
</#if>
<form action="${basepath}/site/model" id="form" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${e.id!""}">
	<div class="form-body">
	
		<div class="form-group">
			<label class="col-md-3 control-label">名称</label>
			<div class="col-md-4">
				<#if insertAction>
                	<input type="text" class="form-control input-circle" name="name" id="name"  data-rule="名称:required;">
                <#else >
                	<input type="text" class="form-control input-circle" name="name" id="name" value="${e.name!""}" data-rule="名称:required;">
                </#if>
			</div>
		</div>
	
		<div class="form-group">
			<label class="col-md-3 control-label">模板</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="code"  id="code" value="${e.code!""}" data-rule="模板:required;"/>
			</div>
		</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">状态</label>
			<div class="col-md-4">
                <select class="form-control" id="status" name="status" >
                	<option value="0" <#if e.status?? && e.status == "0">selected="selected" </#if>>禁用</option>
                    <option value="1" <#if e.status?? && e.status == "1">selected="selected" </#if>>启用</option>
                </select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">排序</label>
			<div class="col-md-4">
                <input type="text" class="form-control input-circle" name="orders"  id="orders"  value="${e.orders!""}"/>
				 (按照数字从小到大排列，数字越小，越靠前)
			</div>
		</div>
		<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-4">
					<button type="button" class="btn btn-default" onclick="javascript:history.back(-1)">取消</button>
					<#if insertAction>
						<button method="insert" class="btn btn-success">新增</button>
                    <#else >
						<button method="update" class="btn btn-success">保存</button>
                    </#if>
				</div>
			</div>
		</div>
	</div>
</form>

</@page.pageBase>
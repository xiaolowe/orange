<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="会员管理">

<script type="text/javascript">
	$(function() {
		 $("#mobile").focus();
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
<form action="${basepath}/cas/member" id="form" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${e.id!""}">
	<div class="form-body">
	
		<div class="form-group">
			<label class="col-md-3 control-label">帐号</label>
			<div class="col-md-4">
				<#if insertAction>
                	<input type="text" class="form-control input-circle" name="mobile" id="mobile"  data-rule="帐号:required;mobile;">
                <#else >
                	<input type="text" class="form-control input-circle" name="mobile" id="mobile" value="${e.mobile!""}">
                </#if>
			</div>
		</div>
	
		<div class="form-group">
			<label class="col-md-3 control-label">用户名</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="name"  id="name" value="${e.name!""}"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label">密码</label>
			<div class="col-md-4">
				<input type="password" class="form-control input-circle" name="password"  id="password" data-rule="密码:password;length[6~20];"/>
				 <#if !insertAction> <br>(不输入表示不修改密码)</#if>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">状态</label>
			<div class="col-md-4">
                <select class="form-control" id="status" name="status" >
                     <option value="1" <#if e.status?? && e.status == "1">selected="selected" </#if>>启用</option>
                     <option value="0" <#if e.status?? && e.status == "0">selected="selected" </#if>>禁用</option>
                </select>
			</div>
		</div>
		<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-4 col-md-8">
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
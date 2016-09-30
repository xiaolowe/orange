<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="用户管理">

<script type="text/javascript">
	$(function() {
		 $("#username").focus();
	});
</script>
</head>

<body>
<#if e.id??>
    <#assign formAction="">
	<#assign insertAction=false />
<#else >
	<#assign formAction="insert">
    <#assign insertAction=true />
</#if>
<form action="${basepath}/cas/user" id="form" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${e.id!""}">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-3 control-label">帐号</label>
			<div class="col-md-4">
				<#if insertAction>
                	<input type="text" class="form-control input-circle" name="username" id="username"  data-rule="帐号:required;username;length[4~20];remote[unique];">
                <#else >
                	<input type="text" class="form-control input-circle" name="username" id="username" value="${e.username!""}">
                </#if>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">昵称</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="nickname" value="${e.nickname!""}" id="nickname"  data-rule="昵称:required;nickname;length[2~20];remote[unique, id];"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">密码</label>
			<div class="col-md-4">
				<#if !insertAction>
				<input type="password" class="form-control input-circle" name="password" data-rule="密码:password;length[6~20];" id="password" />
				<br>(不输入表示不修改密码)</#if>
				<#if insertAction>
				<input type="password" class="form-control input-circle" name="password" data-rule="密码:password;required;length[6~20];" id="password" />
				</#if>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">确认密码</label>
			<div class="col-md-4">
				<input type="password" class="form-control input-circle" name="newpassword2" data-rule="确认密码:match(password);" id="newpassword2" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">手机号码</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="mobile" value="${e.mobile!""}" data-rule="手机号码:mobile;" id="mobile" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">邮箱</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="email" value="${e.email!""}" data-rule="邮箱:email;" id="email" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">选择角色</label>
			<div class="col-md-4">
				<select name="rid" class="form-control">
                	<#list roleList as item>
                   		<option value="${item.id}" <#if e.rid?? && item.id?string == e.rid?string>selected="selected"</#if>> ${item.role_name}</option>
                	</#list>
                </select>
			</div>
		</div>
		<#if !e.username?exists || e.username?exists && e.username != "admin">
		<div class="form-group">
			<label class="col-md-3 control-label">状态</label>
			<div class="col-md-4">
                <select class="form-control" id="status" name="status" >
                     <option value="y" <#if e.status?? && e.status == "y">selected="selected" </#if>>启用</option>
                     <option value="n" <#if e.status?? && e.status == "n">selected="selected" </#if>>禁用</option>
                </select>
			</div>
		</div>
		</#if>
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
<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="单页内容管理">
<script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/ueditor.config.js"></script>
 <script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/ueditor.all.min.js"></script>
 <script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	$(function() {
		 $("#name").focus();
		 if($('#code').val().length>0&&$('#catelogId').find("option:selected").attr('value').length>0){
		 	$('#link').val($('#catelogId').find("option:selected").data('link')+"#"+$('#code').val());  
		 }
		 $('#code').bind('input propertychange', function() {
		 	var link = $('#catelogId').find("option:selected").data('link'); 
    		$('#link').val(link+"#"+$(this).val());  
		}); 
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
<form action="${basepath}/statics/content" id="form" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${e.id!""}">
	<div class="form-body">
	
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">名称</label>
					<div class="col-md-8">
						<#if insertAction>
		                	<input type="text" class="form-control input-circle" name="name" id="name"  data-rule="名称:required;">
		                <#else >
		                	<input type="text" class="form-control input-circle" name="name" id="name" value="${e.name!""}" data-rule="名称:required;">
		                </#if>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">编码</label>
					<div class="col-md-8">
						<input type="text" class="form-control input-circle" name="code"  id="code" value="${e.code!""}" data-rule="编码:required;"/>
					</div>
				</div>
			</div>
		</div>	
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">分类</label>
					<div class="col-md-8">
						<select name="catelogId" id="catelogId" class="form-control" data-rule="分类:required;" onchange="selects(this);">
				        	<#list catelogs as item>
				            	<option data-link="<#if item.link??>${basepath}/statics/page/${item.id!""}<#else>${item.link}</#if>" value="${item.id}" <#if e.catelogId?? && item.id?string == e.catelogId?string>selected="selected"</#if>> ${item.name}</option>
				            </#list>
		                </select>
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				
		           <div class="form-group">
						<label class="col-md-2 control-label">排序</label>
						<div class="col-md-8">
							<input type="text" class="form-control input-circle" name="orders"  id="orders"  value="${e.orders!''}"/>
							 (按照数字从小到大排列，数字越小，在所在分类中越靠前)
						</div>
					</div>
					
			</div>
		</div>
        
        <div class="row">	
			<div class="col-md-offset-1 col-md-10">
    			<script id="content" name="content" type="text/plain" style="width:100%;height:100%; min-height:200px;">${e.content!""}</script>
			</div>
		</div>
		
		<div class="row">
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
        
		
	</div>
</form>

<script type="text/javascript">
	var ue = UE.getEditor('content');
	
	function selects(obj){
		var val = $(obj).find("option:selected").attr("value");
		var link = $(obj).find("option:selected").data('link');
		var code = $('#code').val();
		if(code.length>0){
			$('#link').val(link+"#"+code);
		}
		
	}
</script>
</@page.pageBase>
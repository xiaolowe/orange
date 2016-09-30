<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="友情链接">

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
<form action="${basepath}/statics/link" id="form" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${e.id!""}">
	<div class="form-body">
	
		<div class="form-group">
			<label class="col-md-3 control-label">名称</label>
			<div class="col-md-4">
				<#if insertAction>
                	<input type="text" class="form-control input-circle" name="name" id="name"  data-rule="名称:required;">
                <#else >
                	<input type="text" class="form-control input-circle" name="name" id="name" value="${e.name!""}" data-rule="名称:required;name;">
                </#if>
			</div>
		</div>
	
		<div class="form-group">
			<label class="col-md-3 control-label">编码</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="code"  id="code" value="${e.code!""}"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">图标</label>
			<div class="col-md-4">
				<input type="file" id="imgFile" name="imgFile" style="display: none" onchange="imgFileChange()" />
				<input type="text" class="form-control input-circle" name="img" id="img" value="${e.img!""}" onclick="openImgFile()" data-rule="required;"/>
				<#if insertAction>
                	<img id="imgs" name="imgs" style="height: 35px; width:140px; display:none;" src="" alt=""/>
                <#else >
                	<img id="imgs" name="imgs" style="height: 35px; width:140px; display:<#if e.img??>block<#else>none</#if>" src="${e.img!""}" alt="${e.name!""}"/>
                </#if>
				
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">链接</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="link"  id="link"  value="${e.link!""}" data-rule="链接:required;"/>
				 (完整网址，含http。如：百度-http://www.baidu.com)
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
<script type="text/javascript">
	
	function openImgFile() {
		$("#imgFile").click();
	}
	
	
	function imgFileChange() {
		saveImgPic();
	}
	
	function saveImgPic(){
		
		var imgFileName = $("#imgFile").val();
		if(imgFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=statics&fileId=imgFile',
						secureuri : false,
						fileElementId : 'imgFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#img").val(data);
								$("#imgs").attr("src", data);
								$("#imgs").attr("alt", data);
								$("#imgs").css("display", "block");
							}
						},
						error : function(data, status, e)
						{
							alert("上传失败！");
						}
					});
		} else {
			alert("上传失败！");
		}
	}
	
</script>
</@page.pageBase>
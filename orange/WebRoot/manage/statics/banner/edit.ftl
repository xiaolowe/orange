<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="门户滚动图片">

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
<form action="${basepath}/statics/banner" id="form" method="post" class="form-horizontal">
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
			<label class="col-md-3 control-label">图片</label>
			<div class="col-md-4">
				<input type="file" id="imgFile" name="imgFile" style="display: none" onchange="imgFileChange()" />
				<input type="text" class="form-control input-circle" name="img" id="img" value="${e.img!""}" onclick="openImgFile()" data-rule="required;"/>
				<#if insertAction>
                	<img id="imgs" name="imgs" style="height: 40px; width:120px; display:none;" src="" alt=""/>
                <#else >
                	<img id="imgs" name="imgs" style="height: 40px; width:120px; display:<#if e.img??>block<#else>none</#if>" src="${e.img!""}" alt="${e.name!""}"/>
                </#if>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label">链接</label>
			<div class="col-md-4">
				<input type="text" class="form-control input-circle" name="link"  id="link"  value="${e.link!""}" data-rule="链接:required;"/>
				 (如：百度-http://www.baidu.com)&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:open_modal();" style="display:none;">选择</a>
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

	<div class="modal fade in" id="link_modal" tabindex="-1" role="basic" aria-hidden="true" style="display: none;">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="javascript:close_modal();">X</button>
											<h4 class="modal-title">Modal Title</h4>
										</div>
										<div class="modal-body">
											<p>
												<a href="javascript:;" class="btn blue" id="blockui_sample_3_1_0">
												Block Whole Modal </a>
											</p>
											<p>
												<a href="javascript:;" class="btn green" id="blockui_sample_3_1">
												Block Below Element </a>
												<a href="javascript:;" class="btn default" id="blockui_sample_3_1_1">
												Unblock Below Element </a>
											</p>
											
										</div>
										<div class="modal-footer">
											<button type="button" class="btn default" data-dismiss="modal" onclick="javascript:close_modal();">Close</button>
											<button type="button" class="btn blue" onclick="javascript:close_modal();">Save changes</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							
<script type="text/javascript">
	function open_modal(){
		$("#link_modal").css("display","block");
	}
	
	function close_modal(){
		$("#link_modal").css("display","none");
	}
	
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
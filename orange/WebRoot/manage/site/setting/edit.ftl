<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="站点配置">
<style type="text/css">
input,textarea{
	width: 80%;
}

.leftTd{
	text-align: right;width: 100px
}

.rightTd{
	text-align: left;
}
</style>
<script type="text/javascript">
$(function() {
	$( "#tabs" ).tabs({
		
	});
	
});

</script>

<script type="text/javascript">
					$(function(){
						$("input:checkbox").on("click",function(){
							if($(this).prop("checked")){
								$("#isOpen").val('1');
								$(this).attr('checked', 'checked');
							}else{
								$("#isOpen").val('0');
								$(this).removeAttr('checked');
							}
						});
					});
				
</script>
</head>

<body style="padding: 5px;">
<form action="${basepath}/site/setting" id="form"  class="form-horizontal">
<input type="hidden" name="id" value="${e.id!""}" id="id"/>
<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<button method="update" class="btn btn-success">保存</button>
					<#if checkPrivilege("/site/setting/statics")>
						<a class="btn btn-primary" onclick="statics('确定静态化?');">
						静态化</a>
	                 </#if>
                 </div>
			</div>
</div>
<hr/>
<div class="tabbable-line">
	<ul class="nav nav-tabs ">
		<li class="active"><a href="#tab_global" data-toggle="tab" aria-expanded="true">全局设置</a></li>
		<li><a href="#tab_basic" data-toggle="tab" aria-expanded="false">基本设置</a></li>
		<!--<li><a href="#tab_help" data-toggle="tab" aria-expanded="false">其他设置</a></li>-->
	</ul>
	<br />
	<div class="tab-content">
		<div id="tab_global" class="tab-pane active">
			<div class="form-group">
				<label class="col-md-2 control-label">名称</label>
				<div class="col-md-4">
					<input type="text" class="form-control input-circle" name="name" id="name" value="${e.name!""}" data-rule="名称:required;">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">网站icon</label>
				<div class="col-md-4">
					<input type="file" id="favFile" name="favFile" style="display: none" onchange="favFileChange()" />
					<input type="text" class="form-control input-circle" name="fav" id="fav" value="${e.fav!""}" onclick="openFavFile()" data-rule="网站icon:required;"/>
                	<img id="favs" name="favs" style="height: 40px; width:40px; display:none" src="${e.fav!""}" alt="${e.fav!""}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">标题</label>
				<div class="col-md-4">
					<input type="text" class="form-control input-circle" name="title" id="title" value="${e.title!""}" data-rule="标题:required;">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">描述</label>
				<div class="col-md-4">
					<input type="text" class="form-control input-circle" name="descs" id="descs" value="${e.descs!""}" data-rule="备案号:required;">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">关键字</label>
				<div class="col-md-4">
					<input type="text" class="form-control input-circle" name="keywords" id="keywords" value="${e.keywords!""}" data-rule="关键字:required;">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">是否开放网站</label>
				<div class="col-md-1">
					<input type="hidden"name="isOpen" id="isOpen" value="${e.isOpen!'0'}">
					<input type="checkbox" style="margin-top:10px;" class="col-md-2 input-circle" id="isOpenC" name="isOpenC" value="0" <#if e.isOpenC?default('0')=="1">checked="checked"</#if>/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">关闭信息</label>
				<div class="col-md-5">
					<textarea id="closeMsg" rows="6" name="closeMsg" data-rule="required;closeMsg;length[10~1000];">${e.closeMsg!""}</textarea>
				</div>
			</div>
		</div>
	
		<div id="tab_basic" class="tab-pane">
				<div class="form-group">
					<label class="col-md-2 control-label">口号</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="slogan" id="slogan" value="${e.slogan!""}" data-rule="口号:required;">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">Logo</label>
					<div class="col-md-4">
						<input type="file" id="logoFile" name="logoFile" style="display: none" onchange="logoFileChange()" />
						<input type="text" class="form-control input-circle" name="logo" id="logo" value="${e.logo!""}" onclick="openLogoFile()" data-rule="required;"/>
						<img id="logos" name="logos" style="height: 40px; width:120px; display:none" src="${e.logos!""}" alt="${e.logos!""}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">热线</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="tel" id="tel" value="${e.tel!""}" data-rule="热线:required;">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">邮箱</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="email" id="email" value="${e.email!""}" data-rule="邮箱:required;email">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">地址</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="address" id="address" value="${e.address!""}" data-rule="地址:required;">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">版权</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="cpr" id="cpr" value="${e.cpr!""}" data-rule="版权:required;">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">备案</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="icp" id="icp" value="${e.icp!""}" data-rule="备案:required;">
					</div>
				</div>
		</div>
		
		<div id="tab_help"  class="tab-pane">
				<div class="form-group">
					<label class="col-md-2 control-label">QQ助手代码</label>
					<div class="col-md-5">
						<textarea id="qqHelp" rows="6" name="qqHelp" data-rule="qqHelp;length[0~1000];">${e.qqHelp!""}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">站长统计代码</label>
					<div class="col-md-5">
						<textarea id="totalHelp" rows="6" name="totalHelp" data-rule="totalHelp;length[0~1000];">${e.totalHelp!""}</textarea>
					</div>
				</div>
		</div>
	</div>
</div>
</form>
<script type="text/javascript">
	
	function openFavFile() {
		$("#favFile").click();
	}
	
	function openLogoFile() {
		$("#logoFile").click();
	}
	
	function favFileChange() {
		saveFavPic();
	}
	
	function logoFileChange() {
		saveLogoPic();
	}
	
	function saveFavPic(){
		
		var imgFileName = $("#favFile").val();
		if(imgFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=statics&fileId=favFile',
						secureuri : false,
						fileElementId : 'favFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#fav").val(data);
								$("#favs").attr("src", data);
								$("#favs").attr("alt", data);
								$("#favs").css("display", "block");
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
	
	function saveLogoPic(){
		
		var imgFileName = $("#logoFile").val();
		if(imgFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=statics&fileId=logoFile',
						secureuri : false,
						fileElementId : 'logoFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#logo").val(data);
								$("#logos").attr("src", data);
								$("#logos").attr("alt", data);
								$("#logos").css("display", "block");
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
	
	
	function statics(tip){
	if(confirm(tip)){
		$.ajax({
					url:"${basepath}/site/setting/statics",
					type:"post",
					data:{},
					dataType:"text",
					success:function(data){
						if(data==1){
							alert("静态化成功");
							window.location.reload();
						}else{
							alert("静态化失败");
						}
					},
					error:function(){
						alert("静态化失败");
					}
				});
	}
	return false;
}
	
</script>


</@page.pageBase>
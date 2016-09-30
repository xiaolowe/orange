<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="新闻管理">

 <script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/ueditor.config.js"></script>
 <script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/ueditor.all.min.js"></script>
 <script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
<#if e.id??>
    <#assign formAction="">
	<#assign insertAction=false />
<#else >
	<#assign formAction="insert">
    <#assign insertAction=true />
</#if>
<form action="${basepath}/news/article" id="form" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${e.id!""}">
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">标题</label>
					<div class="col-md-8">
						<input type="text" class="form-control input-circle" name="title"  id="title" value="${e.title!""}" data-rule="标题:required;length[0~50];"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">作者</label>
					<div class="col-md-8">
						<input type="text" class="form-control input-circle" name="author"  id="author" value="${e.author!""}"/>
					</div>
				</div>
			</div>
		</div>	
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">是否推荐</label>
					<div class="col-md-8">
						<#assign map = {'0':'普通', '1':'推荐'}>
                    	<select id="recommend" name="recommend" class="form-control">
                        	<#list map?keys as key>
                            	<option value="${key}" <#if e.recommend?? && e.recommend==key>selected="selected" </#if>>${map[key]}</option>
                        	</#list>
						</select>
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">是否置顶</label>
					<div class="col-md-8">
						<#assign map = {'0':'普通', '1':'置顶'}>
                    	<select id="stick" name="stick" class="form-control">
                        	<#list map?keys as key>
                            	<option value="${key}" <#if e.stick?? && e.stick==key>selected="selected" </#if>>${map[key]}</option>
                        	</#list>
						</select>
					</div>
				</div>
			</div>
			
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">排序</label>
					<div class="col-md-8">
						<input type="text" class="form-control input-circle" name="orders"  id="orders" value="${e.orders!""}" data-rule="digits;"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">关键字</label>
					<div class="col-md-8">
						<input type="text" class="form-control input-circle" name="keyWord"  id="keyWord" value="${e.keyWord!""}" data-rule="关键字:length[0~30];"/>
						<br>(以英文半角逗号","分割)
					</div>
				</div>
			</div>
			
		</div>
		<div class="row">
			<div class="col-md-6" id="sltDiv">
				<div class="form-group">
					<label class="col-md-2 control-label">列表缩略图</label>
					<div class="col-md-8">
						<input type="file" id="sltFile" name="sltFile" style="display: none" onchange="sltFileChange()" />
						<input type="text" class="form-control input-circle" name="sltImg" id="sltImg" value="${e.sltImg!""}" onclick="openSltFile()" data-rule="required;"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-2 control-label">浏览量</label>
					<div class="col-md-8">
						<input type="text" class="form-control input-circle" name="num"  id="num" value="${e.num!""}" data-rule="digits;"/>
					</div>
				</div>
			</div>
			<!--<div class="col-md-6" id="sltDiv">
				<div class="form-group">
					<label class="col-md-2 control-label">详细页大图</label>
					<div class="col-md-8">
						<input type="file" id="bannerFile" name="bannerFile" style="display: none" onchange="bannerFileChange()" />
						<input type="text" class="form-control input-circle" name="bannerImg" id="bannerImg" value="${e.bannerImg!""}" onclick="openBannerFile()" />
					</div>
				</div>
			</div>-->
		</div>
		<div class="row"  style="margin-bottom:15px;">
					<label class="col-md-1 control-label">摘要</label>
					<div class="col-md-10">
						<textarea class="form-control input-circle" name="digest" rows="3"  id="digest" data-rule="摘要:length[16~200];">${e.digest!''}</textarea>
					</div>
		</div>
		
		<div class="row">
			<div class="form-group">	
				<div class="col-md-11">
	    			<script id="content" name="content" type="text/plain" style="width:100%;height:100%; min-height:200px;">${e.content!""}</script>
				</div>
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
		<input type="hidden" name="createTime"  id="createTime" value="${e.createTime!""}" />
		<input type="hidden" name="updateTime"  id="updateTime" value="${e.updateTime!""}" />
		<input type="hidden" name="publishTime"  id="publishTime" value="${e.publishTime!""}" />
		<input type="hidden" name="publishAccount"  id="publishAccount" value="${e.publishAccount!""}" />
		<input type="hidden" name="num"  id="num" value="${e.num!""}" />
		<input type="hidden" name="znum"  id="znum" value="${e.znum!""}" />
		<input type="hidden" name="favNum"  id="favNum" value="${e.favNum!""}" />
		<input type="hidden" name="cnum"  id="cnum" value="${e.cnum!""}" />
		<input type="hidden" name="type"  id="type" value="${e.type!""}" />
		
	</div>
</form>

<script type="text/javascript">
var ue = UE.getEditor('content');
	
	function openSltFile() {
		$("#sltFile").click();
	}
	
	function openBannerFile() {
		$("#bannerFile").click();
	}
	
	function sltFileChange() {
		saveSltPic();
	}
	
	function bannerFileChange() {
		saveBannerPic();
	}
	
	function saveSltPic(){
		
		var sltFileName = $("#sltFile").val();
		if(sltFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=news&fileId=sltFile',
						secureuri : false,
						fileElementId : 'sltFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#sltImg").val(data);
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
	
	function saveBannerPic(){
		
		var bannerFileName = $("#bannerFile").val();
		if(bannerFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=news&fileId=bannerFile',
						secureuri : false,
						fileElementId : 'bannerFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#bannerImg").val(data);
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
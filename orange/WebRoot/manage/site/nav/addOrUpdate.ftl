<#import "/manage/common/tpl/htmlBase.ftl" as html/>
<@html.htmlBase>
<body>
	<SCRIPT type="text/javascript">
		
		$(function(){
			
	 		
	 		$("#input_menu_name").focus();
	 		
	 		$('input:radio[name="parentOrChildRadio"]').click(function(){
	 			var parentOrChild =$(this).val();//子菜单还是根节点
	 			if(parentOrChild==0 || parentOrChild==1){
	 				//添加顶级菜单
	 				$("#result_table_root").hide();
	 			}else{
	 				$("#result_table_root").show();
	 			}
	 		});
	 		
	 		//添加子菜单/修改父菜单
	 		$("#add").click(function(){
	 			var id = $("#input_menu_id").val();
	 			var name = $("#input_menu_name").val();
	 			var url = $("#input_menu_url").val();
	 			var orderNum = $("#input_menu_orderNum").val();
	 			var type = $("#input_menu_type").val();
	 			var model = $("#input_menu_model").val();
	 			var banner = $("#input_menu_banner").val();
	 			
	 			var n_name = $("#input_new_menu_name").val();
	 			var n_url = $("#input_new_menu_url").val();
	 			var parentOrChild =$('input:radio[name="parentOrChildRadio"]:checked').val();//子菜单还是根节点
	 			var n_orderNum = $("#input_new_menu_level").val();
	 			var n_type = $("#input_new_menu_type").val();
	 			var n_model = $("#input_new_menu_model").val();
	 			var n_banner = $("#input_new_menu_banner").val();
	 			
	 			$.ajax({
					url:"${basepath}/site/nav/addOrUpdate",
					type:"post",
					data:{
						updateP:2,//-1不修改父菜单，1修改
						//父菜单信息
						id:id,
						name:name,
						url:url,
						orderNum:orderNum,
						type:type,
						model:model,
						banner:banner,
						//子菜单信息
						n_name:n_name,
						n_url:n_url,
						parentOrChild:parentOrChild,
						n_orderNum:n_orderNum,
						n_type:n_type,
						n_model:n_model,
						n_banner:n_banner
					},
					dataType:"text",
					success:function(data, textStatus){
						$("#showMsgDiv").html("修改导航成功!");
						setTimeout(function(){$("#showMsgDiv").html("");},2000);
					},
					error:function(){
						alert("修改导航失败!");
					}
				});
	 		});
	 		
		});
		
	</SCRIPT>
<style>
	body{text-align:center;}
</style>
<form action="${basepath}/site/nav" name="form" method="post" theme="simple">
<div id="contians_div" style="text-align: right; border: 0px solid red; margin: auto;">
			<div id="context_div" style="margin-top: 20px;">
			
			<div id="showMsgDiv" style="text-align: center;"></div>
	<table id="result_table_root" class="table table-bordered" style="width: 500px;margin: auto;margin-top: 20px;">
	<tr>
			<td colspan="2" style="background-color: #dff0d8;text-align: center;">
				<strong>当前选中的导航</strong>
			</td>
		</tr>
		<tr style="display: none;">
			<td>id</td>
			<td>
				<input id="input_menu_id" readonly="readonly" value='${e.id}'/>
			</td>
		</tr>
		<tr style="display: none;">
			<td>pid</td>
			<td>
				<input id="input_menu_pid" readonly="readonly" value='${e.pid!""}'/>
			</td>
		</tr>
		<tr>
			<th>名称</th>
			<td style="text-align: left;">
				<input type="text"  id="input_menu_name" value='${e.name!""}' class="form-control input-circle"/>
			</td>
		</tr>
		<tr>
			<th>url</th>
			<td style="text-align: left;">
				<input type="text"  id="input_menu_url" value='${e.url!""}' class="form-control input-circle"/>
			</td>
		</tr>
		<tr>
			<th>页内图</th>
			<td style="text-align: left;">
				<input type="file" id="imgFile" name="imgFile" style="display: none" onchange="imgFileChange()" />
				<input type="text" class="form-control input-circle" name="input_menu_banner" id="input_menu_banner" value="${e.banner!""}" onclick="openImgFile()"/>
				<img id="imgs" name="imgs" style="height: 40px; width:120px; display:none;" src="" alt=""/>
			</td>
		</tr>
		<!--<tr>
			<th>模板</th>
			<td style="text-align: left;">
				<select id="input_menu_model" name="input_menu_model" class="form-control" style="width:360px;">
                   <#list models as model>
                      <option value="${model.code}" <#if e.model?? && e.model==model.code>selected="selected" </#if>>${model.name}</option>
                    </#list>
				</select>
			</td>
		</tr>-->
		<tr>
			<th>顺序</th>
			<td style="text-align: left;">
				<input type="text"  id="input_menu_orderNum" value='${e.orders!""}' class="form-control input-circle"/>
				(导航顺序从1开始，小的显示在前面)
			</td>
		</tr>
		</table>
		<!-- item -->
		
		<table id="result_table2" class="table table-bordered" style="width: 500px;margin: auto;margin-top: 20px;">
		<tr>
			<td colspan="2" style="background-color: #dff0d8;text-align: center;">
				<strong>添加导航</strong>
				&nbsp;&nbsp;<input type="radio" name="parentOrChildRadio" value="0"/>顶级导航
				&nbsp;&nbsp;<input type="radio" name="parentOrChildRadio" value="1"/>子导航
			</td>
		</tr>
		<tr style="display: none;">
			<td>id</td>
			<td>
				<input type="text"  id="input_new_menu_id" readonly="readonly"/>
			</td>
		</tr>
		<tr style="display: none;">
			<td>pid</td>
			<td>
				<input type="text"  id="input_new_menu_pid" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<th>名称</th>
			<td style="text-align: left;">
				<input type="text"  id="input_new_menu_name" class="form-control input-circle" />
			</td>
		</tr>
		<tr>
			<th>url</th>
			<td style="text-align: left;">
				<input type="text"  id="input_new_menu_url" class="form-control input-circle"/>
			</td>
		</tr>
		<tr>
			<th>页内图</th>
			<td style="text-align: left;">
				<input type="file" id="cimgFile" name="cimgFile" style="display: none" onchange="cimgFileChange()" />
				<input type="text" class="form-control input-circle" name="input_new_menu_banner" id="input_new_menu_banner" onclick="opencImgFile()"/>
				<img id="cimgs" name="cimgs" style="height: 40px; width:120px; display:none;" src="" alt=""/>
			</td>
		</tr>
		<!--<tr>
			<th>模板</th>
			<td style="text-align: left;">
				<select id="input_new_menu_model" name="input_new_menu_model" class="form-control" style="width:360px;">
                   <#list models as model>
                      <option value="${model.code}" <#if e.model?? && e.model==model.code>selected="selected" </#if>>${model.name}</option>
                    </#list>
				</select>
			</td>
		</tr>-->
		<tr>
			<th>顺序</th>
			<td style="text-align: left;">
				<input type="text"  id="input_new_menu_level" class="form-control input-circle"/>(导航顺序从1开始，小的显示在前面)
			</td>
		</tr>
		<tr>
			<td style="text-align: center;" colspan="2">
				<input type="button" id="add" value="修改或添加" class="btn btn-primary"/>
			</td>
		</tr>
	</table>
</div></div>
</form>
<script type="text/javascript">
	if($("#input_menu_banner").val().length==0){
		$("#imgs").css("display", "none");
	}else{
		$("#imgs").attr("src", $("#input_menu_banner").val());
		$("#imgs").css("display", "block");
	}
	
	if($("#input_new_menu_banner").val().length==0){
		$("#cimgs").css("display", "none");
	}else{
		$("#cimgs").attr("src", $("#input_new_menu_banner").val());
		$("#cimgs").css("display", "block");
	}
	
	function openImgFile() {
		$("#imgFile").click();
	}
	
	function opencImgFile() {
		$("#cimgFile").click();
	}
	
	function imgFileChange() {
		saveImgPic();
	}
	
	function cimgFileChange() {
		savecImgPic();
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
								$("#input_menu_banner").val(data);
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
	
	function savecImgPic(){
		
		var imgFileName = $("#cimgFile").val();
		if(imgFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=statics&fileId=cimgFile',
						secureuri : false,
						fileElementId : 'cimgFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#input_new_menu_banner").val(data);
								$("#cimgs").attr("src", data);
								$("#cimgs").attr("alt", data);
								$("#cimgs").css("display", "block");
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
</body>
</@html.htmlBase>
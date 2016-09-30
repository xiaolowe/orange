<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="赛事信息">
<script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/ueditor.config.js"></script>
 <script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/ueditor.all.min.js"></script>
 <script type="text/javascript" charset="utf-8" src="${staticpath}/ueditor/lang/zh-cn/zh-cn.js"></script>
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
	$("#tabs" ).tabs({
		
	});
	
});

</script>
</head>

<body style="padding: 5px;">

<#if e.id??>
    <#assign formAction="">
	<#assign insertAction=false />
<#else>
	<#assign formAction="insertRace">
    <#assign insertAction=true />
</#if>

<#assign x = 0 />
<#assign y = 0 />

<form action="${basepath}/race/info" id="form"  class="form-horizontal" data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
<input type="hidden" name="id" value="${e.id!""}" id="id"/>
<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<button type="button" class="btn btn-default" onclick="javascript:history.back(-1)">取消</button>
					<#if insertAction>
						<button method="insertRace" class="btn btn-success">添加</button>
                    <#else >
						<button method="updateRace" class="btn btn-success">保存</button>
                    </#if>
                 </div>
			</div>
</div>
<hr/>
<div class="tabbable-line">
	<ul class="nav nav-tabs ">
		<li class="active"><a href="#tab_basic" data-toggle="tab" aria-expanded="true">基本信息</a></li>
		<li><a href="#tab_addon" data-toggle="tab" aria-expanded="false">报名信息</a></li>
		<li><a href="#tab_detail" data-toggle="tab" aria-expanded="false">赛事介绍</a></li>
		<li><a href="#tab_intro" data-toggle="tab" aria-expanded="false">赛事详情</a></li>
		<li><a href="#tab_frees" data-toggle="tab" aria-expanded="false">报名须知</a></li>
		<li><a href="#tab_tips" data-toggle="tab" aria-expanded="false">赛事规则</a></li>
		<li><a href="#tab_contact" data-toggle="tab" aria-expanded="false">温馨提示</a></li>
	</ul>
	<br />
	<div class="tab-content">
		<div id="tab_basic" class="tab-pane active">
			<div class="form-group">
				<label class="col-md-2 control-label">名称</label>
				<div class="col-md-4">
					<input type="text" class="form-control input-circle" name="name" id="name" value="${e.name!""}" data-rule="名称:required;">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">时间</label>
				<div class="col-md-4">
					<input type="text" class="form-control input-circle" name="time" id="time" value="${e.time!""}" data-rule="时间:required;"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('time'),readOnly:true})">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">地点</label>
				<div class="col-md-4">
					<input type="text" class="form-control input-circle" name="address" id="address" value="${e.address!""}" data-rule="地点:required;">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">缩略图</label>
				<div class="col-md-4">
					<input type="file" id="sltFile" name="sltFile" style="display: none" onchange="sltFileChange()" />
					<input type="text" class="form-control input-circle" name="slt" id="slt" value="${e.slt!""}" onclick="openSltFile()" data-rule="缩略图:required;"/>
                	<img id="slts" name="slts" style="height: 90px; width:60px; display:<#if insertAction>none<#else>block</#if>;" src="${e.slt!""}" alt="${e.slt!""}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">宣传图</label>
				<div class="col-md-4">
					<input type="file" id="bannerFile" name="bannerFile" style="display: none" onchange="bannerFileChange()" />
					<input type="text" class="form-control input-circle" name="banner" id="banner" value="${e.banner!""}" onclick="openBannerFile()" data-rule="宣传图:required;"/>
                	<img id="banners" name="banners" style="height: 48px; width:192px; display:<#if insertAction>none<#else>block</#if>;" src="${e.banner!""}" alt="${e.banner!""}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">广告图</label>
				<div class="col-md-4">
					<input type="file" id="adsFile" name="adsFile" style="display: none" onchange="adsFileChange()" />
					<input type="text" class="form-control input-circle" name="ads" id="ads" value="${e.ads!""}" onclick="openAdsFile()" data-rule="广告图:required;"/>
                	<div id="div_ads">
                		
                	</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">描述</label>
				<div class="col-md-5">
					<textarea id="descs" rows="6" name="descs" data-rule="required;descs;length[10~1000];">${e.descs!""}</textarea>
				</div>
			</div>
		</div>
	
		<div id="tab_addon" class="tab-pane">
				<div class="form-group">
					<label class="col-md-2 control-label">开始时间</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="startTime" id="startTime" value="${e.startTime!""}" data-rule="开始时间:required;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('startTime'),readOnly:true})" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">结束时间</label>
					<div class="col-md-4">
						<input type="text" class="form-control input-circle" name="endTime" id="endTime" value="${e.endTime!""}" data-rule="结束时间:required;"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('endTime'),readOnly:true})" /></li>
					</div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label">赛事组别</label>
                  <div class="col-md-6 btn-group" role="group" aria-label="Large button group" name="tab">
                          <button type="button" class="btn btn-success">个人比赛</button>
                          <button type="button" class="btn btn-default">团队比赛</button>
                  </div>
                  
                  <div id="table1" class="ul col-md-6 col-md-offset-2">
                      <table class="table" style="border:1px solid #ddd;">
                                  <thead>
                                    <tr>
                                      <th>组别名称</th>
                                      <th>名额</th>
                                      <th>金额</th>
                                      <th>操作</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    
                                    <#if !insertAction>
                                    
                                    <#list singleGroups as group>
                                        
                                        <tr>
                                            <input type="hidden" name="singleGroups[${x}].id" value="${group.id}">
                                            <input type="hidden" name="singleGroups[${x}].type" value="1">
                                            <input type="hidden" name="singleGroups[${x}].signUpNum" value="1">
                                          <td><input class="form-control input-circle" type="text" name="singleGroups[${x}].name" value="${group.name}" data-rule="组别名称:required;" placeholder="请输入名称"></td>
                                          <td><input class="form-control input-circle" type="number" name="singleGroups[${x}].maxNum" value="${group.maxNum}" data-rule="名额:required;digits;"></td>
                                          <td><input class="form-control input-circle" type="number" name="singleGroups[${x}].price" value="${group.price}" data-rule="金额:required;digits;"></td>
                                          <td><button type="button" class="btn btn-danger" onclick="delGroup(this, ${group.id})">删除</button></td>
                                        </tr>
                                        <#assign x = x + 1 />
                                    </#list>
                                    
                                    </#if>
                                    
                                  </tbody>
                            </table>
                               <div>
                                    <button type="button" class="btn btn-info" id="addPerson">增加组别</button>
                               </div>
                      </div>
                      <div id="table2" style="display:none"  class="ul col-md-8 col-md-offset-2">
                      <table class="table" style="border:1px solid #ddd;">
                                  <caption></caption>
                                  <thead>
                                    <tr>
                                      <th>组别名称</th>
                                      <th>名额</th>
                                      <th>金额</th>
                                      <th>报名人数</th>
                                      <th>操作</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <#if !insertAction>
                                    
                                    <#assign y = 0 />
                                    
                                    <#list teamGroups as group>
                                        <tr>
                                            <input type="hidden" name="teamGroups[${y}].id" value="${group.id}">
                                            <input type="hidden" name="teamGroups[${y}].type" value="1">
                                         <td><input class="form-control input-circle" type="text" name="teamGroups[${y}].name" value="${group.name}" data-rule="组别名称:required;" placeholder="请输入名称"></td>
                                          <td><input class="form-control input-circle" type="number" name="teamGroups[${y}].maxNum" value="${group.maxNum}" data-rule="名额:required;digits;"></td>
                                          <td><input class="form-control input-circle" type="number" name="teamGroups[${y}].price" value="${group.price}" data-rule="金额:required;digits;"></td>
                                          <td><input class="form-control input-circle" type="number" name="teamGroups[${y}].signUpNum" value="${group.signUpNum}" data-rule="报名人数:required;digits;range[2~]"></td>
                                          <td><button type="button" class="btn btn-danger" onclick="delGroup(this, ${group.id})">删除</button></td>
                                        </tr>
                                        
                                        <#assign y = y + 1 />
                                    </#list>
                                    
                                    </#if>
                                  </tbody>
                            </table>
                               <div>
                                    <button type="button" class="btn btn-info" id="addTeam">增加组别</button>
                               </div>
                      </div>
              </div>       
				
				<script type="text/javascript">
					$(function(){
						$("#isLimitC").on("click",function(){
							if($(this).prop("checked")){
								$(this).attr('checked', 'checked');
								$("#isLimit").val('1');
								$("#div_limit_num").css("display", "block");
							}else{
								$(this).removeAttr('checked');
								$("#isLimit").val('0');
								$("#div_limit_num").css("display", "none");
							}
						});
						/*
						$("#isTeamC").on("click",function(){
							if($(this).prop("checked")){
								$(this).attr('checked', 'checked');
								$("#isTeam").val('1');
								$("#div_max_team_num").css("display", "block");
							}else{
								$(this).removeAttr('checked');
								$("#isTeam").val('0');
								$("#div_max_team_num").css("display", "none");
							}
						});*/
						
						$("#isChildC").on("click",function(){
							if($(this).prop("checked")){
								$(this).attr('checked', 'checked');
								$("#isChild").val('1');
							}else{
								$(this).removeAttr('checked');
								$("#isChild").val('0');
							}
						});
					});
					var num= ${x};
					//个人报名增加
                    $("#addPerson").click(function(){
                        
                        var tr = "<tr><td><input class=\"form-control input-circle\" type=\"text\" name=\"singleGroups["+num+"].name\" placeholder=\"请输入名称\" data-rule=\"组别名称:required;\">"+
                                 "</td><td><input class=\"form-control input-circle\" type=\"number\" name=\"singleGroups["+num+"].maxNum\" data-rule=\"名额:required;digits;\"></td><td><input class=\"form-control input-circle\" type=\"number\" name=\"singleGroups["+num+"].price\" data-rule=\"金额:required;digits;\">"+
                                 "</td><input type=\"hidden\" name=\"singleGroups["+num+"].type]\" value=\"1\"><td><input type=\"hidden\" name=\"singleGroups["+num+"].signUpNum\" value=\"1\"><button type=\"button\" class=\"btn btn-danger\" onclick='delbtn(this)'>删除</button></td></tr>";
                        $('#table1 .table').append(tr);   
                        num++;
                    });
                    var ynum= ${y};
                    //团体报名增加
                    $("#addTeam").click(function(){
                    
                    
                        var tr = "<tr><td><input class=\"form-control input-circle\" type=\"text\" name=\"teamGroups["+ynum+"].name\" placeholder=\"请输入名称\" data-rule=\"组别名称:required;\">"+
                                 "</td><td><input class=\"form-control input-circle\" type=\"number\" name=\"teamGroups["+ynum+"].maxNum\" data-rule=\"名额:required;digits;\"></td><td><input class=\"form-control input-circle\" type=\"number\" name=\"teamGroups["+ynum+"].price\" data-rule=\"金额:required;digits;\">"+
                                 "</td><input type=\"hidden\" name=\"teamGroups["+ynum+"].type\" value=\"2\"><td><input class=\"form-control input-circle\" type=\"number\" name=\"teamGroups["+ynum+"].signUpNum\" data-rule=\"报名人数:required;digits;range[2~]\"></td><td><button type=\"button\" class=\"btn btn-danger\" onclick='delbtn(this)'>删除</button></td></tr>";
                        $('#table2 .table').append(tr);   
                        ynum++;          
                    });
                    //tab切换
                    $("div[name='tab'] button").click(function(){
                    $(this).addClass("btn-success").siblings().removeClass("btn-success");
                    var a = $(this).index();
                    $(".ul").hide();
                    $(".ul").eq(a).show();
                    });
                    
                    //删除
                    function delbtn(del) {
                    
                        $(del).parents("tr").remove();
                    
                    }
                    
                    //删除赛事组别
                    function delGroup(del, groupId){
                        // 验证此组别是否可以删除
                        $.ajax({
                            url:"${basepath}/race/info/delRaceGroup",
                            type:"post",
                            data:{
                                groupId:groupId
                                ,raceId:${e.id!"0"}
                            },
                            dataType:"text",
                            success:function(data){
                            
                                if(data == '0') {
                                    $(del).parents("tr").remove();
                                } else {
                                    alert("此赛事组别已经有选手报名，不能删除！");
                                }
                            
                            },
                            error:function(){
                                alert("删除赛事组别失败!");
                            }
                        });
                    }
                    
				
				</script>
				
				<div class="form-group">
					<label class="col-md-2 control-label">儿童报名</label>
					<div class="col-md-2">
						<input type="hidden"name="isChild" id="isChild" value="${e.isChild!'0'}">
						<input type="checkbox" style="margin-top:10px;" class="col-md-2 input-circle" id="isChildC" name="isChildC" value="0" <#if e.isChild?default('0')=="1">checked="checked"</#if>/>
					</div>
				</div>
				
		</div>
		
		<div id="tab_detail"  class="tab-pane">
					<div class="row">	
						<div class="col-md-offset-1 col-md-10">
    						<script id="detail" name="detail" type="text/plain" style="width:100%;height:100%;  min-height:300px;">${e.detail!""}</script>
						</div>
					</div>
		</div>
		<div id="tab_intro"  class="tab-pane">
					<div class="row">	
						<div class="col-md-offset-1 col-md-10">
    						<script id="introduce" name="introduce" type="text/plain" style="width:100%;height:100%;  min-height:300px;">${e.introduce!""}</script>
						</div>
					</div>
		</div>
		
		<div id="tab_frees"  class="tab-pane">
					<div class="row">	
						<div class="col-md-offset-1 col-md-10">
    						<script id="frees" name="frees" type="text/plain" style="width:100%;height:100%;  min-height:300px;">${e.frees!""}</script>
						</div>
					</div>
		</div>
		<div id="tab_tips"  class="tab-pane">
					<div class="row">	
						<div class="col-md-offset-1 col-md-10">
    						<script id="tips" name="tips" type="text/plain" style="width:100%;height:100%;  min-height:300px;">${e.tips!""}</script>
						</div>
					</div>
		</div>
		<div id="tab_contact"  class="tab-pane">
					<div class="row">	
						<div class="col-md-offset-1 col-md-10">
    						<script id="contact" name="contact" type="text/plain" style="width:100%;height:100%;  min-height:300px;">${e.contact!""}</script>
						</div>
					</div>
		</div>
	</div>
</div>
</form>

<script type="text/javascript">
	var detail = UE.getEditor('detail');
	var introduce = UE.getEditor('introduce');
	var frees = UE.getEditor('frees');
	var tips = UE.getEditor('tips');
	var contact = UE.getEditor('contact');
</script>

<script type="text/javascript">
	
	function openSltFile() {
		$("#sltFile").click();
	}
	
	function openBannerFile() {
		$("#bannerFile").click();
	}
	
	function openAdsFile() {
		$("#adsFile").click();
	}
	
	function sltFileChange() {
		saveSltPic();
	}
	
	function bannerFileChange() {
		saveBannerPic();
	}
	
	function adsFileChange() {
		saveAdsPic();
	}
	
	function saveSltPic(){
		
		var imgFileName = $("#sltFile").val();
		if(imgFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=race&fileId=sltFile',
						secureuri : false,
						fileElementId : 'sltFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#slt").val(data);
								$("#slts").attr("src", data);
								$("#slts").attr("alt", data);
								$("#slts").css("display", "block");
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
		
		var imgFileName = $("#bannerFile").val();
		if(imgFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=race&fileId=bannerFile',
						secureuri : false,
						fileElementId : 'bannerFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								$("#banner").val(data);
								$("#banners").attr("src", data);
								$("#banners").attr("alt", data);
								$("#banners").css("display", "block");
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
	
	function saveAdsPic(){
		
		var imgFileName = $("#adsFile").val();
		if(imgFileName != ''){
			$.ajaxFileUpload({
				url : '${basepath}/common/uploader.do?method=suploader&type=race&fileId=adsFile',
						secureuri : false,
						fileElementId : 'adsFile',
						dataType : 'json', 
						success : function(data, status)
						{
							if(data != ""){
								var ads = $("#ads").val();
								if(ads.length>0){
									ads = ads + ";" + data;
									$("#ads").val(ads);
								}else{
									$("#ads").val(data);
								}
								updateAds($("#ads").val());								
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
	
	
	function updateAds(ads){
		
		$("#div_ads").html('');
		if(ads.length>0){
			var adss = ads.split(";");
			var htmls = "";
			for(var i=0;i<adss.length; i++){
				var html = "<img src=\"" + adss[i] + "\" alt=\"" + adss[i] +"\" style=\"width:180px; height:60px;\" onclick=\"javascript:deleteImg(this);\"/>";
				htmls = htmls + html;
			}
			$("#div_ads").html(htmls);
		}
	}
	
	$(function() {
		var ads = $("#ads").val();
		if(ads.length>0){
			updateAds(ads);
		}
	
	});
	
	function deleteImg(obj){
		var data = $(obj).attr('src');
		var ads = $("#ads").val();
		var adsn = "";
		if(ads.length>0){
			var adss = ads.split(";");
			for(var i=0;i<adss.length; i++){
				if(adss[i]!=data){
					adsn = adsn + adss[i] + ";";
				}
			}
			if(adsn.length>0){
				adsn = adsn.substring(0, adsn.length-1);
			}
			$("#ads").val(adsn)
			updateAds($("#ads").val());
		}
		
		
		
	}	
	
</script>


</@page.pageBase>
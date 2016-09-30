<#import "/manage/common/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="报名管理">
<style type="text/css">
.saveA {
  display: inline-block;
  width: 60px;
  line-height: 25px;
  height: 25px;
  text-align: center;
  color: #fff;
  background: #ED3B0C;
  border-radius: 3px;
  margin-left: 10px;
  text-decoration:none;
}
.saveA:hover {
  	color: #fff;
  	background: #004f85;
  	text-decoration:none;
}
.saveA:visited {
	color: #fff;
  	text-decoration:none;
}
.saveA:link {
	color: #fff;
  	text-decoration:none;
}
.saveA:active{
	color: #fff;
	text-decoration:none;
}
</style>
<form action="${basepath}/race/addon" method="post" class="form-horizontal">

		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-2">赛事名称</label>
					<div class="col-md-8">
						<select name="raceId" id="raceId" class="form-control">
		                	<#list races as item>
		                   		<option value="${item.id}" <#if raceId?? && item.id?string == raceId?string>selected="selected"</#if>> ${item.name}</option>
		                	</#list>
                		</select>
					</div>
				</div>
			</div>
		</div>
		
	
		<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<#if checkPrivilege("/race/addon/selectList")>
						<button class="btn btn-primary" onclick="selectList(this)" method="scores">
						查询 <i class="fa fa-search"></i></button>
	                </#if>
	                <button class="btn btn-danger" onclick="javascript:history.back(-1);">返回</button>
                 </div>
			</div>
		</div>
   
		<div class="table-scrollable">
			<table class="table table-striped table-bordered table-hover dataTable no-footer" id="sample_1" role="grid" aria-describedby="sample_1_info">
				<thead>
					<tr role="row">
						<th style="width:40px;">
							序号
						</th>
						<th rowspan="1" colspan="1" aria-label="赛事名称">
							赛事名称
						</th>
						<th rowspan="1" colspan="1" aria-label="报名时间">
							报名时间
						</th>
						<th rowspan="1" colspan="1" aria-label="报名人">
							报名人
						</th>
						<th rowspan="1" colspan="1" aria-label="联系方式">
							联系方式
						</th>
						<th rowspan="1" colspan="1" aria-label="报名类型">
							报名类型
						</th>
						<th rowspan="1" colspan="1" aria-label="报名人数">
							报名人数
						</th>
						<th rowspan="1" colspan="1" aria-label="成绩">
							成绩
						</th>
						<th rowspan="1" colspan="1" aria-label="名次">
							名次
						</th>
						<th rowspan="1" colspan="1" aria-label="操作">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<#list mans as item>
						<tr role="row" id="score${item_index}">
							<td style="width:40px; padding-left:18px;">
								${item_index+1}
							</td>
							<td style="display: none;">&nbsp;${item.id!""}</td>
							<td>
			                    ${item.rName!""}
							</td>
							<td>
			                     ${item.time!""}
							</td>
							<td>
			                     <#if item.isTeam?default('0')=='1'>[${item.name!''}]${item.contact!''}<#else>${item.name!""}</#if>
							</td>
							<td>
			                     ${item.mobile!""}
							</td>
							<td>
								<#if item.isTeam?default('0')=='1'>团队<#else>个人</#if>
							</td>
							<td>
			                     ${item.num!"1"}
							</td>
							<td>
								<input type="text" class="form-control" id="score" name="score" value="${item.score!''}"/>
							</td>
							<td>
								<input type="text" class="form-control" id="rank" name="rank" value="${item.rank!''}"/>
							</td>
							<td>
			                    <a href="javascript:void(0);" onclick="saveOrUpdate(this)" data-team="${item.isTeam}" data-sId="${item.sId!''}" data-addon="${item.id}" class="saveA">保存</a>
							</td>
						</tr>
			        </#list>
				</tbody>
			</table>
		</div>

</form>

<script type="text/javascript">
function saveOrUpdate(obj){
	var parent = $(obj).parent().parent();
	var score = $(parent).find("#score").val();
	var rank = $(parent).find("#rank").val();
	var addonId = $(obj).attr("data-addon");
	var sId = $(obj).attr("data-sId");
	var isTeam = $(obj).attr("data-team");
		$.ajax({
					url:"${basepath}/race/score/saveOrUpdate",
					type:"post",
					data:{
						isTeam:isTeam,
						sId:sId,
						addonId:addonId,
						score:score,
						rank:rank
					},
					dataType:"json",
					success:function(data){
						if(data.code=='ok'){
							$(obj).attr("data-sId", data.msg);
							alert("操作成功！");
						}else{
							alert(data.msg);
						}
					},
					error:function(){
						alert("系统错误!");
					}
				});
				
}
</script>
</@page.pageBase>
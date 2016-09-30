<#import "/front/common/tpl/mPageBase.ftl" as page>

<@page.mPageBase currentIndex="news_list" jsFiles=["jquery-1.7.1.min.js","jquery.SuperSlide.2.1.1.js"] cssFiles=["main.css"] checkLogin=true>
<SCRIPT>
		function update(){
	 			var name = $("#input_name").val();
	 			var password = $("#input_password").val();
	 			var mobile = $("#input_mobile").val();
	 			if(password.length>0){
	 				if(password.length<6 || password.length>20){
		 				alert("密码由6-16位数字、字母组成，请确认");
		 				return;
	 				}
	 			}
	 			$.ajax({
					url:"${basepath}/rest/m/update",
					type:"post",
					data:{
						name:name,
						password:password,
						mobile:mobile
					},
					dataType:"text",
					success:function(data, textStatus){
						alert("修改成功");
					},
					error:function(){
						alert("修改失败!");
					}
				});
		}
		
	</SCRIPT>
	<style type="text/css">
.cash{
  display: inline-block;
  width: 60px;
  line-height: 25px;
  height: 25px;
  text-align: center;
  color: #fff;
  background: #ED3B0C;
  border-radius: 3px;
  margin-left: 5px;
}
.cash:hover{
  	color: #fff;
  	background: #004f85;
}
</style>

<script type="text/javascript">
function payWX(order_id, order_money, order_name, order_num, order_price, mobile){
	//alert(order_id + "=" + order_name + "=" + order_money + "=" + order_price + "=" + order_num);
	window.location.href=basepath + "/pay/wx/"+order_id+"/"+order_money+".html?pro_name="+encodeURI(order_name)+"&pro_num="+order_num+"&pro_price=" + order_price;
}

 function addon_check(raceId, order_id, order_money, order_name, order_num, order_price, mobile){
		$.ajax({
					url:"${basepath}/rest/r/addon/check",
					type:"post",
					data:{raceId:raceId, raceNum:order_num},
					dataType:"json",
					success:function(data){
						if(data.code=='ok'){
							payWX(order_id, order_money, order_name, order_num, order_price, mobile)
						}else{
							alert(data.msg);
						}
					},
					error:function(){
						alert("网络错误,请重试!");
					}
				});
}

</script>
	<div class="w1200">
			<div class="member_slide">
				<div class="hd">
					<ul><li>我的赛事</li><li>个人资料</li></ul>
				</div>
				<div class="bd">
					<ul class="race">
<table width="100%">
<tr>
								<th scope="col" width="5%">No.</th>
								<th scope="col"  width="20%">赛事</th>
								<th scope="col"  width="10%">时间</th>
								<th scope="col"  width="5%">类型</th>
								<th scope="col"  width="5%">人数</th>
								<th scope="col"  width="10%">联系人</th>
								<th scope="col"  width="10%">电话</th>
								<th scope="col"  width="10%">费用</th>
								<th scope="col"  width="10%">总费用</th>
								<th scope="col"  width="10%">状态</th>
								<th scope="col"  width="5%">详情</th>
</tr>
<#list races.list as race>
<tr>
								<td>${race_index + 1}</td>
								<td>
								    <a href="${basepath}/races/cases/${race.raceId}.html">${race.rName}</a></br>
								    <i>${race.gName}</i>
								</td>
								<td>${race.createTime}</td>
								<td><#if race.gType ==2>团队<#else>个人</#if></td>
								<td>${race.num}</td>
								<td><#if race.isTeam?default('0')=='1'>[${race.tName!''}]</#if>${race.contactor}</td>
								<td>${race.mobile}</td>
								<td>${race.rPrice}（元）</td>
								<td>${race.rPrice?number * race.num}（元）</td>
								<td><#if race.status?default('0')=='1'>已支付<#else>未支付/<a href="javascript:void(0);" onclick="javascript:addon_check('${race.raceId}','${race.tradeNo!""}', '1', '${race.rName}','${race.num}', '${race.rPrice}', '${race.mobile}');" class="cash">去支付</a></#if></td>
								<td><a href="${basepath}/race/addon/info/${race.id}/${race.teamId}.html" style="color:#004f85" target="_blank">查看</a></td>
</tr>
</#list>

</table>		
</ul>
					
					<ul class="profile" id="profile">
							<table width="100%">
							  <tr>
								<td  class="p_l">用户名：</td>
								<td  class="p_r"><input name="input_name" id="input_name" type="text" value="<#if currentMember()??>${currentMember().name}</#if>" /> </td>
							  </tr>
							  <tr>
								<td  class="p_l">密码：</td>
								<td  class="p_r"><input name="input_password" id="input_password" type="password" value=""/><span>(不输入表示不修改密码)</span></td>
							  </tr>
							  <tr>
								<td class="p_l">手机号码：</td>
								<td  class="p_r"><input name="input_mobile" id="input_mobile" type="txt" value="<#if currentMember()??>${currentMember().mobile}</#if>" readonly="readonly" /></td>
							  </tr>
							  
							  <tr class="button">
								<td colspan="2">
									<input type="button" name="edit_btn" id="edit_btn" value="确定修改" onclick="javascript:update();"  style="cursor: pointer"/>
								</td>
							  </tr>
							  
							</table>
					</ul>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			jQuery(".member_slide").slide({trigger:"click"});
		
		</script>

</@page.mPageBase>
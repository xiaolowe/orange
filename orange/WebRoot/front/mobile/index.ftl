<#import "/front/common/tpl/htmlBase.ftl" as html>
<@html.htmlBase checkLogin=true jsFiles=["jquery-1.10.2.min.js","jquery.SuperSlide.2.1.1.js"] cssFiles=["main-wap.css"] checkLogin=true>
<body>
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
.cash:link{
	color:#fff;
}
.cash:visited{
	color:#fff;	
	background: #004f85;
}
.cash{
  color:#fff;
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
	window.location.href=basepath + "/pay/wx/h5/"+order_id+"/"+order_money+".html?pro_name="+encodeURI(order_name)+"&pro_num="+order_num+"&pro_price=" + order_price + "&mobile=" + mobile;
			      	
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

<nav><a href="javascript:history.go(-1);" class='goback'> <b></b></a>会员中心</nav>

<div class="member_bg"><center><img src="${frontpath}/images/member_bg.jpg" height="150"/><center></div>

<div class="w100">
	<div class="w1200">
			<div class="member_slide">
				<div class="hd">
					<ul><li>我的赛事</li><li>个人资料</li></ul>
				</div>
				<div class="bd">
					<ul class="race">
						<table width="100%">
							<tr>
								<th scope="col"  width="30%"><center>赛事</center></th>
								<th scope="col"  width="15%"><center>人数</center></th>
								<th scope="col"  width="15%"><center>金额</center></th>
								<th scope="col"  width="20%"><center>状态</center></th>
								<th scope="col"  width="20%"><center>详情</center></th>
							</tr>

							<#list races.list as race>
								<tr>
									<td>${race.rName}</td>
									<td>${race.num}</td>
									<td>${race.rPrice?number * race.num}（元）</td>
									<td><#if race.status?default('0')=='1'>已支付<#else>未支付/<a href="javascript:void(0);" onclick="javascript:addon_check('${race.raceId}','${race.tradeNo!""}', '${race.rPrice?number * race.num*100}', '${race.rName}','${race.num}', '${race.rPrice}', '${race.mobile}');" class="cash">去支付</a></#if></td>
									<td><a href="${basepath}/mobile/addon/info/${race.id}/${race.teamId}.html" style="color:#004f85">查看</a></td>					  
								</tr>
							</#list>
						</table>		
					</ul>
					
					<ul class="profile">
							<table width="100%">
							  <tr>
								<td  class="p_l">用户名：</td>
								<td  class="p_r"><input name="input_name" id="input_name" type="text" value="<#if currentMember()??>${currentMember().name}</#if>" /> </td>
							  </tr>
							  <tr>
								<td  class="p_l">密码：</td>
								<td  class="p_r"><input name="input_password" id="input_password" type="password" value=""/></td>
							  </tr>
							  <tr>
								<td class="p_l">手机号码：</td>
								<td  class="p_r"><input name="input_mobile" id="input_mobile" type="txt" value="<#if currentMember()??>${currentMember().mobile}</#if>" readonly="readonly" /></td>
							  </tr>
							  <tr class="button">
								<td colspan="2">
									<center><input type="button" name="edit_btn" id="edit_btn" value="确定修改" onclick="javascript:update();"  style="cursor: pointer"/></center>
								</td>
							  </tr>
							</table>
						</table>
					</ul>
				</div>
			</div>
	</div>
</div>
<script type="text/javascript">jQuery(".member_slide").slide({trigger:"click"});</script>	
</body>
</@html.htmlBase>
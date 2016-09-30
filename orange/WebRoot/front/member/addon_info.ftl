<#import "/front/common/tpl/mPageBase.ftl" as page>

<@page.mPageBase currentIndex="news_list" jsFiles=["jquery-1.7.1.min.js"] cssFiles=["main.css"] checkLogin=true>
<style type="text/css">
p span {
  width: 84px;
  text-align: right;
  display: inline-block;
 }
 
.btn {
  display: inline-block;
  width: 60px;
  line-height: 25px;
  height: 25px;
  text-align: center;
  color: #fff;
  background: #ED3B0C;
  border-radius: 3px;
  margin-left: 10px;
}
.btn:hover {
  	color: #fff;
  	background: #004f85;
}
.back {
  display: inline-block;
  width: 60px;
  line-height: 24px;
  height: 24px;
  text-align: center;
  color: #fff;
  background: #ED3B0C;
  border-radius: 3px;
  margin-left: 3px;
}
</style>
<div class="w1200">
			<div class="member_slide" >
				<div class="hd" style="background:#004f85; color:#fff; text-align: center;">
					</a><h2>我的赛事</h2>
				</div>
				<div class="bd" style="margin-top:0;">
					<ul class="race">
						<div class="base" style="margin-top:0;">
						<div class="title">基础信息</div>
							<p><span>赛事名称：</span>${race.rName}</p>
							<p><span>报名组别：</span>${race.gName}</p>
							<p><span>赛事地点：</span>${race.address}</p>
							<p><span>报名时间：</span>${race.createTime}</p>
							<p><span>报名类型：</span><#if race.gType ==2>团队<#else>个人</#if></p>
							<p><span>报名人：</span><#if race.isTeam?default('0')=='1'>[${race.tName!''}]</#if>${race.contactor!""}</p>
							<p><span>报名人数：</span>${race.num}</p>
							<#if race.status?default('0')=='1'>
								<p><span>支付金额：</span>已支付/${race.rPrice?number*race.num}元</p>
							<#else>
								<p><span>支付状态：</span>未支付/<a href="javascript:void(0);" onclick="javascript:addon_check('${race.raceId}', '${race.tradeNo}', '${race.rPrice?number * race.num*100}', '${race.rName}','${race.num}', '${race.rPrice}', '${race.mobile}');" class="btn">去支付</a></p>
							</#if>
						</div>
						
							<div class="people">
								<div class="title">参赛人员</div>
								<#list mans as man>
									<div class="person">
										<div class="ptitle">人员<span>${man_index+1}</span><i><#if man.isChild?default('1')=='0'>成人<#else>儿童</#if></i></div>
										<p><span>姓名：</span>${man.name}</p>
										<p><span>手机号：</span>${man.mobile}</p>
										<p><span>身份证：</span>${man.cardId}</p>
										<p>
											<span>成绩：</span>
											<a href="javascript:void(0);" onclick="score(this,'${race.raceId}', '${man.mobile}','${man.cardId}')" class="btn">查成绩</a>
										</p>
										<p id="score_p" style="display:none;">
											<span style="font-size:13px;color:#004f85;">名次：</span>
											<span style="width:110px;text-align:left;font-size:20px;color:#ed710c;" id="rank_span"></span>
											<span style="font-size:13px;color:#004f85;">成绩：</span>
											<span style="width:110px;text-align:left;font-size:20px;color:#ed710c;" id="score_span"></span>
										</p>
									</div>
								</#list>
							</div>
								
					</ul>
				</div>
			</div>
		</div>
<script type="text/javascript">
function score(obj,raceId, mobile, cardId){
var parent = $(obj).parent().parent();
	$.ajax({
					url: basepath + "/rest/r/score",
					type:"post",
					data:{
						raceId:raceId,
						mobile:mobile,
						cardId:cardId
					},
					dataType:"json",
					success:function(data){
						if(data.code=='ok'){
							$(parent).find("#rank_span").text(data.rank);
							$(parent).find("#score_span").text(data.score);
							$(parent).find("#score_p").css("display", "block");
						}else{
							alert(data.msg);
							$(parent).find("#score_p").css("display", "none");
						}
					},
					error:function(){
						alert("系统错误!");
						$(parent).find("#score_p").css("display", "none");
					}
				});
	
}

function payWX(order_id, order_money, order_name, order_num, order_price){
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
</@page.mPageBase>
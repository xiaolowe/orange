<#import "/front/common/tpl/fPageBase.ftl" as page>

<@page.pageBase currentIndex="races" jsFiles=["jquery-1.7.1.min.js","jquery.SuperSlide.2.1.1.js"] cssFiles=["main.css", "inside.css", "races.css"] checkLogin=false>

<#if race.banner!=''>
<script>
	$("#topImg").attr("src", "${race.banner}");
	$("#topimg").css("display", "block");
	$(".here").css("display", "none");
	var link = basepath + "/races/${type}/list.html";
	$("a.menu-item").each(function(index){
            var href = $(this).attr("href");
            if($(this).attr("href")==link){
            	$("ul.nav_menu").find("li.nav_menu-item").removeClass("nav_menu-item on").addClass('nav_menu-item');
            	var parent = $(this).parent();
	        	if(parent.is("li.nav_menu-item")){
	        		$(parent).removeClass("nav_menu-item").addClass('nav_menu-item on');
	        	}
	        	if(parent.is("li.nav_submenu-item")){
	        		$(parent).parent().parent().removeClass("nav_menu-item").addClass('nav_menu-item on');
	        	}
            }
        });
</script>	
</#if>

<input type="hidden" id="d" name="d" value="${d!''}" />
<script>
 function addon(raceId, groupId){
		$.ajax({
					url:"${basepath}/rest/r/addon/check",
					type:"post",
					data:{raceId:raceId, groupId:groupId},
					dataType:"json",
					success:function(data){
						if(data.code=='ok'){
							var d = $("#d").val();
							var url = basepath + "/race/addon/"+raceId+ "/" + groupId + ".html";
							if(d.length>0){
								url = basepath + "/mobile/addon/"+raceId+".html";
							}
							window.location.href=url;
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
	<div class="recename">${race.name}</div>
	<div class="join_info">
			<span>时间：<#if race.time?? && race.time?length gt 10>${race.time?substring(0,10)}<#else>${race.time!""}</#if></span><span>地点：${race.address}</span></span>
	</div>
</div>
<#assign imgs = race.ads?split(";")>
<#if imgs?size gt 0>
<div class="w1200">
<div id="slideBox" class="slideBox">
	<div class="bd">
		<ul>
			<#list imgs as img>
				<li><a href="#"><img src="${img}" /></a></li>
			</#list>
		</ul>
	</div>
	<#if imgs?size gt 1>
		<a class="prev" href="javascript:void(0)"></a>
		<a class="next" href="javascript:void(0)"></a>
	</#if>
</div>
</div>
</#if>
<script type="text/javascript">
jQuery(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true});
</script>
<div class="w1200" style="border:1px solid #e6e6e6;margin-bottom:20px;">
		<#if type?default('cases')=='events'>
			<div class="now_join">
			    <table>
                  <thead>
                    <tr>
                      <th>组别</th>
                      <th>类型</th>
                      <th class="hidden-xs">报名时间</th>
                      <th>费用</th>
                      <th>状态</th>
                    </tr>
                  </thead>
                  <tbody>
                    <#list groups as item>
                    <tr>
                      <td>${item.name}</td>
                      <td>
                        ${(item.type == "1")?string("个人", "团队")}
                      </td>
                      <td class="hidden-xs">
                        ${race.startTime}
                          ~ ${race.endTime}
                      </td>
                      <td>
                          ¥ ${item.price}
                      </td>
                      <td>
                          <a onclick="javascript:addon(${raceId}, ${item.id});" href="javascript:void(0);">立即参赛</a>
                      </td>
                    </tr>
                    </#list>
                  </tbody>
                </table>
			</div>
		</#if>
</div>



<div class="empty-placeholder hidden"></div>
<div id="subNav">
<div class="w1200">
  <ul class="wrap">
    <li class="adv_active"><a href="#race_001">赛事介绍</a></li>
    <li><a href="#race_002">赛事详情</a></li>
    <li><a href="#race_003">报名须知</a></li>
    <li><a href="#race_004">赛事规则</a></li>
    <li><a href="#race_005">温馨提示</a></li>
  </ul>
</div>
</div>

<div class="w1200 race">
<div id="race_001">
	<div class="title">赛事介绍</div>
	<div class="race_info">
			${race.detail!""}
	</div>
</div>

<div id="race_002">
	<div class="title">赛事详情</div>
	<div class="race_info">
		${race.introduce!""}
	</div>
	
</div>

<div id="race_003">
	<div class="title">报名须知</div>
	<div class="race_info!""">
	${race.frees!""}
	</div>
 </div>

<div id="race_004">
	<div class="title">赛事规则</div>
	<div class="race_info">
			${race.tips!""}
	</div>
</div>
<div id="race_005">
	<div class="title">温馨提示</div>
	<div class="race_info">
		${race.contact!""}
	</div>
</div>

</div>

<script>
$(function(){
//优势页面点击子导航
	var subNav_active = $(".adv_active");
	var subNav_scroll = function(target){
		subNav_active.removeClass	("adv_active");
		target.parent().addClass("adv_active");
		subNav_active = target.parent();
	};
	$("#subNav a").click(function(){
		subNav_scroll($(this));
		var target = $(this).attr("href");
		var targetScroll = $(target).offset().top - 80;
		$("html,body").animate({scrollTop:targetScroll},300);
		return false;
	});
	//页面跳转时定位
	if(window.location.hash){
		var targetScroll = $(window.location.hash).offset().top - 80;
		$("html,body").animate({scrollTop:targetScroll},300);
	}
	$(window).scroll(function(){
		var $this = $(this);
		var targetTop = $(this).scrollTop();
		var footerTop = $(".footer_box").offset().top;
		var height = $(window).height();
		
		if (targetTop >= 1000){
			$("#subNav").addClass("fixedSubNav");
			$(".empty-placeholder").removeClass("hidden");
		}else{
			$("#subNav").removeClass("fixedSubNav");
			$(".empty-placeholder").addClass("hidden");
		}
	})
}());
</script>
</@page.pageBase>
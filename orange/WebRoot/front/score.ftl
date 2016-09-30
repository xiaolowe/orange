<#import "/front/common/tpl/fPageBase.ftl" as page>

<@page.pageBase currentIndex="races" jsFiles=["jquery-1.10.2.min.js","pageGroup.js"] cssFiles=["main.css", "inside.css"] checkLogin=false>
<script>
	var link = basepath + "/races/${type}/list.html";
	$("a.menu-item").each(function(index){
            var href = $(this).attr("href");
            var img = "";
            if($(this).attr("href")==link){
            	$("ul.nav_menu").find("li.nav_menu-item").removeClass("nav_menu-item on").addClass('nav_menu-item');
            	var parent = $(this).parent();
	        	if(parent.is("li.nav_menu-item")){
	        		$(parent).removeClass("nav_menu-item").addClass('nav_menu-item on');
	        		img = $(parent).data("img");
	        	}
	        	if(parent.is("li.nav_submenu-item")){
	        		$(parent).parent().parent().removeClass("nav_menu-item").addClass('nav_menu-item on');
	        		img = $(parent).parent().parent().data("img");
	        	}
	        	
	        	var index_select_index_img = img;
		        if(index_select_index_img.length>0){
				    $("#topImg").attr("src", index_select_index_img);
					$("#topimg").css("display", "block");
					$(".here").css("display", "css");
		        }
            }
        });
</script>
<form action="${basepath}/races/score" method="post">
<div class="w1200">
	<div class="score">
		<h1>${race.name}</h1>
		
			<input type="hidden" id="raceId" name="raceId" value="${raceId}" />
			<div class="identity" id="score_div" style="display:none;">
				<span style="font-size:13px;color:#004f85;">名次：</span>
					<span style="width:110px;text-align:left;font-size:20px;color:#ed710c;" id="rank_span"></span>
				<span style="font-size:13px;color:#004f85;">成绩：</span>
					<span style="width:110px;text-align:left;font-size:20px;color:#ed710c;" id="score_span"></span>
			</div>
			<div class="identity"><span>姓名：</span><input type="text" id="name" name="name" class="text"><i>&nbsp;</i></div>
			<div class="identity"><span>手机号码：</span><input type="text" id="mobile" name="mobile" class="text"><i>*</i></div>
			<div class="identity"><span>身份证号码：</span><input type="text" id="cardId" name="cardId" class="text"><i>*</i></div>
			<a href="javascript:void(0);" onclick="score();">立即查询</a>
	</div>
</div>
</form>
<script type="text/javascript">
function isCardNo(card) { 
	 	var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	 	return pattern.test(card); 
}
function score(){
	var mobile = $("#mobile").val();
	var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
	if (!mobileReg.test(mobile) || mobile.length<0 || mobile.length > 11) {
		alert("手机号码格式不正确!");
        return false;
	}
	var cardId = $("#cardId").val();
	if(cardId.length==0 || isCardNo(cardId)==false){
		alert("请确认身份证号码!");
        return;
	}
	var raceId=$("#raceId").val();
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
							$("#rank_span").text(data.rank);
							$("#score_span").text(data.score);
							$("#score_div").css("display", "block");
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
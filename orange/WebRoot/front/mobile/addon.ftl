<#import "/front/common/tpl/htmlBase.ftl" as html>
<@html.htmlBase checkLogin=true jsFiles=["jquery-1.10.2.min.js"] cssFiles=["main-wap.css"] checkLogin=true from="mobile">
<body>
<style type="text/css">
.ptitle a {
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
</style>
<nav><a href="javascript:history.go(-1);" class='goback'> <b></b></a>选择参赛人数</nav>

<form action="" id="addon_form" name="addon_form" accept-charset="utf-8" method="post">
<div class="w1200">
	<div class="join_content" style="padding: 0px 0;">
	<h1>${race.name}</h1>
	<input type="hidden" id="price_per" name="price_per" value="${race.price}" />
	<div class="num">		
				<div class="i_box">成人：
					<a href="javascript:;" class="J_minus">-</a>
					<input id="manb" type="text" value="${race.minNum}" class="J_input" readonly/>
					<a href="javascript:;" class="J_add">+</a>
				</div>
				<div class="i_box" style="display:<#if race.isChild=='1'><#else>none</#if>;">儿童：
					<a href="javascript:;" class="J_minus">-</a>
					<input type="text" id="childb" value="0" class="J_input" readonly/>
					<a href="javascript:;" class="J_add">+</a>
				</div>	
	</div>
	
	<div class="contact">
		<div class="title">联系人信息</div>
		<#if race.isTeam?default('0')=='1'>
			<div class="identity"><span>队名：</span><input type="text" name="teamName" id="teamName" value="" class=""><i>*</i></div>
		</#if>
		<div class="identity"><span>姓名：</span><input type="text" name="contactor" id="contactor" value="${currentMember().name}" class=""><i>*</i></div>
		<div class="identity"><span>手机号：</span><input type="text" name="mobile" id="mobile" value="${currentMember().mobile}" class=""><i>*</i></div>
		<div class="identity"><span>备注：</span><input type="text" name="descs" id="descs" value="" class=""><i>&nbsp;</i></div>
	</div>	

		<!-- 母体 -->
		<div id="cloneDIV" class="people" style="display:none;">
			<div class="ptitle">人员<span id="numc"></span><i id="typec">成人</i><!--<a href="javascript:void(0);" onclick="javascript:deleteMan(this);">删除</a>--></div>
			<div class="p_content">
				<div class="identity"><span>姓名：</span><input type="text" id="name" name="name" class="text"><i>*</i></div>
				<div class="identity">
					<span>性别：</span>
					<input name="gender" type="radio" value="1" checked />男
					<input name="gender" type="radio" value="0" />女<i>*</i>
				</div>
				<div class="identity"><span>手机：</span><input type="text" id="mobile" name="mobile" class="text"><i>*</i></div>
				<div class="identity"><span>身份证：</span><input type="text" id="cardId" name="cardId" class="text"><i>*</i></div>
				<input type="hidden" id="isChild" name="isChild">
			</div>
		</div>

	<div class="join_list" id="list">
		
	</div>
	
	<div class="pay">
		<div class="cost">总价<i id="price_total">￥${race.price}</i>元</div>
	</div>
	
	</div>
</div>
<section class="register-btn-2">
	<a href="javascript:void(0);" onclick="postData();" id="purchase_btn">立即去支付</a>
</section>
</form>
<script>
	function deleteMan(obj){
	}
	
	function getTeam(){
		var addon = new Object();
		var isTeam = '${race.isTeam?default("0")}';
		addon.isTeam = isTeam;
		var teamName = $(".contact #teamName").val();
		addon.name = teamName;
		var contact=$(".contact #contactor").val();
		addon.contact = contact;
		var mobile = $(".contact #mobile").val();
		addon.mobile=mobile;
		var descs=$(".contact #descs").val();
		addon.descs=descs;
		return JSON.stringify(addon);
	}
	
	function getParams(){
		var postArray= new Array();
		$("div.people.man").each(function(index){
            var id = $(this).attr("id");
            if(id!='cloneDIV'){
            	var name = $(this).find("#name").val();
				var gender = $(this).find("input:radio:checked").val();
				var mobile = $(this).find("#mobile").val();
				var cardId = $(this).find("#cardId").val();
				var isChild = $(this).find("#isChild").val();
				var mano = new Object();
				mano.name = name;
				mano.gender = gender;
				mano.mobile = mobile;
				mano.cardId = cardId;
				mano.isChild = isChild;
				
				postArray.push(mano);
            }
        });
		return JSON.stringify(postArray);
	}
	
	function isCardNo(card) { 
	 	var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	 	return pattern.test(card); 
	} 
	
	function validateParam(){
		var isTeam = '${race.isTeam?default("0")}';
		if(isTeam=='1'){
			var teamName = $(".contact #teamName").val();
			if(teamName.length==0 || teamName.length > 20){
				alert("请填写团队名称,1-20个字符!");
				return false;
			}
		}
		var contact=$(".contact #contactor").val();
		if(contact.length>20||contact<2){
			alert("请填写联系人姓名,2-20个字符!");
			return false;
		}
		var mobile = $(".contact #mobile").val();
		var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
		if (!mobileReg.test(mobile)|| mobile.length<0 || mobile.length > 11) {
			alert("联系人手机号码格式不正确!");
			return false;
		}
		var descs=$(".contact #descs").val();
		var result = true;
		$("div.people.man").each(function(index){
            var id = $(this).attr("id");
            if(id!='cloneDIV'){
            	var name = $(this).find("#name").val();
            	if(name.length<2 || name.length>20){
            		alert("请填写报名人员姓名，2-20个字符!");
            		result = false;
            		return false;
            	}
				var gender = $(this).find("input:radio:checked").val();
				if(gender.length==0){
					alert("请选择报名人员性别!");
					result = false;
            		return false;
				}
				var mobile = $(this).find("#mobile").val();
				var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
				if (!mobileReg.test(mobile) || mobile.length<0 || mobile.length > 11) {
					alert("报名人员手机号码格式不正确!");
					result = false;
            		return false;
				}
				var cardId = $(this).find("#cardId").val();
				if(cardId.length==0 || isCardNo(cardId)==false){
					alert("请确认报名人员身份证号码!");
					result = false;
            		return false;
				}
            }
            result = true;;
        });
        return result;
	}
	
	
	function postData(){
		var v= validateParam();
		if(v){
			$('#purchase_btn').attr("disabled", "true"); 
			var postData = getParams();
			var team = getTeam();
			//alert(postData + ";" + team);
			var url = basepath + "/rest/r/purchase";
			var mId = '${currentMember().id}';
			var order_money = parseFloat($('#price_total').text(), 2)*100;
			var order_num = parseInt($("#manb").val())+parseInt($("#childb").val());
			$.ajax({     
			   url:url,     
			   type:'post', 
			   dataType:'json',     
			   data:{
			   		postData:postData,
			   		team:team,
			   		mId:mId,
			   		raceId:'${race.id}',
			   		raceNum:order_num,
			   		money:order_money,
			   		raceName:'${race.name}'
			   		}, 
			   success:function(data){     
			      if(data.code=='ok'){
			      	//跳到微信支付界面
			      	//alert(data.code);
			      	var order_id= data.msg;
			      	var order_price = $('#price_per').val();
			      	var order_name='${race.name}';
			      	var mobile = $(".contact #mobile").val();
			      	//alert(order_id + "=" + order_name + "=" + order_money + "=" + order_price + "=" + order_num);
			      	if(order_money==0){
						window.location.href=basepath+"/mobile/home.html";			      	
			      	}else{
			      		window.location.href=basepath + "/pay/wx/h5/"+order_id+"/"+order_money+".html?pro_name="+encodeURI(order_name)+"&pro_num="+order_num+"&pro_price=" + order_price + "&mobile=" + mobile;
			      	}
			      }else{
			      	alert(data.msg);
			      }
			   },
			   error:function(){
			   		alert("系统错误");
			   }  
			});
		}
	}
	
	
</script>
<script>
$.fn.iVaryVal=function(iSet,CallBack){
	/*
	 * Minus:点击元素--减小
	 * Add:点击元素--增加
	 * Input:表单元素
	 * Min:表单的最小值，非负整数
	 * Max:表单的最大值，正整数
	 */
	iSet=$.extend({Minus:$('.J_minus'),Add:$('.J_add'),Input:$('.J_input'),Min:'0',Max:'<#if race.limitNum?? && race.limitNum?number!=0>${race.limitNum}<#else>${race.maxNum}</#if>'},iSet);
	var C=null,O=null;
	//插件返回值
	var $CB={};
	//增加
	iSet.Add.each(function(i){
		$(this).click(function(){
			O=parseInt(iSet.Input.eq(i).val());
			(O+1<=iSet.Max) || (iSet.Max==null) ? iSet.Input.eq(i).val(O+1) : iSet.Input.eq(i).val(iSet.Max);
			//输出当前改变后的值
			$CB.val=iSet.Input.eq(i).val();
			$CB.index=i;
			//回调函数
			if (typeof CallBack == 'function') {
                CallBack($CB.val,$CB.index, 1);
            }
		});
	});
	//减少
	iSet.Minus.each(function(i){
		$(this).click(function(){
			O=parseInt(iSet.Input.eq(i).val());
			O-1<iSet.Min ? iSet.Input.eq(i).val(iSet.Min) : iSet.Input.eq(i).val(O-1);
			$CB.val=iSet.Input.eq(i).val();
			$CB.index=i;
			//回调函数
			if (typeof CallBack == 'function') {
				CallBack($CB.val,$CB.index, 0);
		  	}
		});
	});
	//手动
	/*iSet.Input.bind({
		'click':function(){
			O=parseInt($(this).val());
			$(this).select();
		},
		'keyup':function(e){
			if($(this).val()!=''){
				C=parseInt($(this).val());
				//非负整数判断
				if(/^[1-9]\d*|0$/.test(C)){
					$(this).val(C);
					O=C;
				}else{
					$(this).val(O);
				}
			}
			//键盘控制：上右--加，下左--减
			if(e.keyCode==38 || e.keyCode==39){
				iSet.Add.eq(iSet.Input.index(this)).click();
			}
			if(e.keyCode==37 || e.keyCode==40){
				iSet.Minus.eq(iSet.Input.index(this)).click();
			}
			//输出当前改变后的值
			$CB.val=$(this).val();
			$CB.index=iSet.Input.index(this);
			//回调函数
			if (typeof CallBack == 'function') {
                CallBack($CB.val,$CB.index,4);
            }
		},
		'blur':function(){
			$(this).trigger('keyup');
			if($(this).val()==''){
				$(this).val(O);
			}
			//判断输入值是否超出最大最小值
			if(iSet.Max){
				if(O>iSet.Max){
					$(this).val(iSet.Max);
				}
			}
			if(O<iSet.Min){
				$(this).val(iSet.Min);
			}
			//输出当前改变后的值
			$CB.val=$(this).val();
			$CB.index=iSet.Input.index(this);
			//回调函数
			if (typeof CallBack == 'function') {
                CallBack($CB.val,$CB.index, 3);
            }
		}
	});*/
	
}
//调用
$( function() {
	var isP = 1;
	var cur_total = 0;
	var min = parseInt('${race.minNum}')
	var max = parseInt('${race.maxNum}');
	var price_pre = parseFloat($("#price_per").val(), 2);
	var total = parseInt(min);
	var mArray = new Array();
	var cArray = new Array();
	if(min>0){
		for(var i=0; i<min; i++){
			addMans(min,"man" + (i+1) + "0", 1);
		}
	}
	$("#price_total").text(price_pre*total);
	$('.i_box').iVaryVal({},function(value,index,flg){
		if(index == 0){
			cur_total = parseInt(value) + parseInt($("#childb").val());
			if(cur_total < min){
				alert("本次报名最少填报" + min + "人");
				isP=0;
				var set = parseInt(min)-parseInt($("#childb").val());
				$("#manb").val(set);
			}else if(cur_total>max){
				alert("本次报名最多只能填报" + max + "人");
				isP=1;
				var set=parseInt(max)-parseInt($("#childb").val());
				$("#manb").val(set);
			}else{
				if(flg==1){
					addMans(cur_total, "man" + $("#manb").val() + $("#childb").val(), 1);
				}else{
					deleteMans(1);
				}
			}
		}
		if(index == 1){
			cur_total = parseInt(value) + parseInt($("#manb").val());
			if(cur_total < min){
				alert("本次报名最少填报" + min + "人");
				isP=0;
				var set = parseInt(min)-parseInt($("#manb").val());
				$("#childb").val(set);
			}else if(cur_total>max){
				alert("本次报名最多只能填报" + max + "人");
				isP=1;
				var set=parseInt(max)-parseInt($("#manb").val());
				$("#childb").val(set);
			}else{
				if(flg==1){
					addMans(cur_total, "child" + $("#manb").val() +$("#childb").val(), 0);
				}else{
					deleteMans(0);
				}
			}
		}
		total = parseInt($("#manb").val())+parseInt($("#childb").val());
		$("#price_total").text(price_pre*total);
	});
	
	function addMans(cur_total, choseId, isMan){
		var manc = $("#cloneDIV").clone();
		manc.addClass("man");
		if(isMan=='1'){
			manc.find("#typec").text("成人");
			manc.attr("id", choseId);
			manc.find("#isChild").val("0");
			mArray.push(choseId);
		}else{
			manc.find("#typec").text("儿童");
			manc.attr("id", choseId);
			manc.find("#isChild").val("1");
			cArray.push(choseId);
		}
		manc.find("input:radio").attr("name", "gender" + choseId);
		$(".join_list").append(manc.show());
		manc.find("input[type=button]").show();
	}
	
	function deleteMans(isMan){
		$(".people.man").each(function(indexs){
            var id = $(this).attr("id");
            var choseId = "";
            if(isMan=='1'){
            	choseId = mArray[mArray.length-1];
            }else{
            	choseId = cArray[cArray.length-1];
            }
            if(id==choseId){
            	var obj=$("#"+choseId);
            	$(obj).remove();
            	if(isMan==1){
            		mArray.remove(choseId);
            	}else{
            		cArray.remove(choseId);
            	}
            }
        });
	}
});

	Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};
	Array.prototype.indexOf = function(val) {
		for (var i = 0; i < this.length; i++) {
			if (this[i] == val) return i;
		}
		return -1;
	};
</script>

</body>
</@html.htmlBase>
$(function(){
	
	if(getParam("a")=='login'){
		loginCtr();
	}
	if(getParam("a")=='regist'){
		registCtr();
	}
	if(getParam("from") && getParam("from").length>0){
		$("form").each(function(){
			$(this).append("<input type=\"hidden\" id=\"from\" name=\"from\" value=\"" + getParam("from") + "\" />");
		});
	}
	
	$('#switch_qlogin').click(function(){
		loginCtr();
	});
	$('#switch_login').click(function(){
		registCtr();
	});
	
	$('#switch_qforget').click(function(){
		forgetCtr();
	});
	
	$('#switch_qforget_back').click(function(){
		loginCtr();
	});
	
	/*$('#login_btn').click(function(){
		login_btn_ctr();
	});*/
	
	$('#regist_btn').click(function(){
		regist_btn_ctr();
	});
	
	$('#forget_btn').click(function(){
		forget();
	});
	

});

function regist_btn_ctr(){
	if(getCookie($('.qlogin #mobile').val())!=$('.qlogin #code').val()){
		alert("验证码错误");
	}else{
		delCookie($('.qlogin #mobile').val());
		$("#regist_form").submit();
	}
}
	
//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) { 
    var params = location.search.substr(1); // 获取参数 平且去掉？
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //只有一个参数的情况 
        return params.split('=')[1]; 
    }else { 
         //多个参数参数的情况 
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
} 

function loginCtr(){
	$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'0px',width:'70px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
	$('#qforget').css('display', 'none');
}

function registCtr(){
	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_bottom').animate({left:'154px',width:'70px'});
	$('#qlogin').css('display','block');
	$('#web_qr_login').css('display','none');
	$('#qforget').css('display', 'none');
}

function forgetCtr(){
	$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'0px',width:'70px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','none');
	$('#qforget').css('display', 'block');
}



var countdown=60; 
function set_regist_time() { 
    if (countdown == 0) { 
    	var mobile = $('#regist_form #mobile').val();
        $("#regist_form #code_btn").attr("disabled", "false");  
        $("#regist_form #code_btn").html("获取验证码"); 
        countdown = countdown; 
        return;
    } else { 
    	$("#regist_form #code_btn").attr("disabled", "true"); 
        $("#regist_form #code_btn").html("重新获取(" + countdown + ")"); 
        countdown--; 
    } 
    setTimeout(function() { 
    	set_regist_time(); 
    } ,1000);
}

function send_regist_code(){
	var mobile = $('#regist_form #mobile').val();
	var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
	if (!mobileReg.test(mobile)|| mobile.length<0 || mobile.length > 11) {
		alert("手机号码格式不正确");
		return;
	}
	$.ajax({
					url: basepath + "/rest/common/sms/code",
					type:"post",
					data:{mobile:mobile},
					dataType:"text",
					success:function(data){
						if(data!=0){
							delCookie(mobile);
							setCookie(mobile, data);
							set_regist_time($("#regist_form #code_btn"));
						}else{
							alert("验证码发送失败，请重新获取");
							$("#regist_form #code_btn").html("获取验证码");
						}
					},
					error:function(){
						alert("验证码发送失败，请重新获取");
						$("#regist_form #code_btn").html("获取验证码");
					}
	});	
}

function set_forget_time() { 
    if (countdown == 0) { 
    	var mobile = $('#forget_form #mobile').val();
        $("#forget_form #code_btn").attr("disabled", "false");  
        $("#forget_form #code_btn").html("获取验证码"); 
        countdown = countdown; 
        return;
    } else { 
    	$("#forget_form #code_btn").attr("disabled", "true"); 
        $("#forget_form #code_btn").html("重新获取(" + countdown + ")"); 
        countdown--; 
    } 
    setTimeout(function() { 
    	set_forget_time(); 
    } ,1000);
}

function send_forget_code(){
	var mobile = $('#forget_form #mobile').val();
	var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
	if (!mobileReg.test(mobile)|| mobile.length<0 || mobile.length > 11) {
		alert("手机号码格式不正确");
		return;
	}
	$.ajax({
					url: basepath + "/rest/common/sms/code",
					type:"post",
					data:{mobile:mobile},
					dataType:"text",
					success:function(data){
						if(data!=0){
							delCookie(mobile);
							setCookie(mobile, data);
							set_forget_time($("#forget_form #code_btn"));
						}else{
							alert("验证码发送失败，请重新获取");
							$("#forget_form #code_btn").html("获取验证码");
						}
					},
					error:function(){
						alert("验证码发送失败，请重新获取");
						$("#forget_form #code_btn").html("获取验证码");
					}
	});	
}


function forget(){
			var mobile = $('#forget_form #mobile').val();
			var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
			if (!mobileReg.test(mobile)|| mobile.length<0 || mobile.length > 11) {
				alert("手机号码格式不正确");
				return;
			}
			var password = $("#forget_form #password").val();
			if(password.length==0){
				alert("请输入密码");
				return;
			}
			if(password.length<6 || password.length>20){
				alert("密码由6-16位数字、字母组成");
				return;
			}
			var password2 = $("#forget_form #password2").val();
			if(password2 != password){
				alert("确认密码不一致");
				return;
			}
			var code = $("#forget_form #code").val();
			if(code.length==0){
				alert("请输入验证码");
				return;
			}
			if(getCookie($('#forget_form #mobile').val())!=$('#forget_form #code').val()){
				alert("验证码不正确");
				return;
			}
			$.ajax({
					url: basepath + "/rest/m/update",
					type:"post",
					data:{
						mobile:mobile,
						password:password
					},
					dataType:"json",
					success:function(data){
						delCookie(mobile);
						if(data.code=='ok'){
							alert("密码重置成功");
							loginCtr();
						}else{
							alert("密码重置失败");
						}
					},
					error:function(){
						alert("系统错误");
					}
			});	
}
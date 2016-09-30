<#import "/front/common/tpl/htmlBase.ftl" as html>
<@html.htmlBase checkLogin=true jsFiles=["jquery-1.10.2.min.js"] cssFiles=["main-wap.css"] checkLogin=false title="注册">
<body>
<script>

$(function(){
		
		$('#regist_btn').click(function(){
			var name = $("#name").val();
			if(name.length==0){
				$("#error_display").html("请输入姓名");
				return;
			}
			if(name.length>20){
				$("#error_display").html("姓名最多20字符,请确认");
				return;
			}
			var password = $("#password").val();
			if(password.length==0){
				$("#error_display").html("请输入密码");
				return;
			}
			if(password.length<6 || password.length>20){
				$("#error_display").html("密码由6-16位数字、字母组成");
				return;
			}
			var password2 = $("#password2").val();
			if(password2 != password){
				$("#error_display").html("确认密码不一致");
				return;
			}
			var mobile = $("#mobile").val();
			var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
			if (!mobileReg.test(mobile)|| mobile.length<0 || mobile.length > 11) {
				$("#error_display").html("手机号码格式不正确");
				return;
			}
			var code = $("#code").val();
			if(code.length==0){
				$("#error_display").html("请输入验证码");
				return;
			}
			if(getCookie($('#register_form #mobile').val())!=$('#register_form #code').val()){
				$("#error_display").html("验证码不正确");
				return;
			}
			delCookie($('#register_form #mobile').val());
			$("#register_form").submit();
		});
});


var countdown=60; 
function set_time() { 
    if (countdown == 0) { 
        $("#code_btn").attr("disabled", "false");  
        $("#code_btn").html("获取验证码"); 
        countdown = countdown; 
        return;
    } else { 
    	$("#code_btn").attr("disabled", "true"); 
        $("#code_btn").html("重新获取(" + countdown + ")"); 
        countdown--; 
    } 
    setTimeout(function() { 
    	set_time(); 
    } ,1000);
}

function send_code(){
	var mobile = $('#register_form #mobile').val();
			$.ajax({
					url: basepath + "/rest/common/sms/code",
					type:"post",
					data:{mobile:mobile},
					dataType:"text",
					success:function(data){
						if(data!=0){
							delCookie(mobile);
							setCookie(mobile, data);
							set_time($("#code_btn"));
						}else{
							alert("验证码发送失败，请重新获取");
							$("#code_btn").html("获取验证码");
						}
					},
					error:function(){
						alert("验证码发送失败，请重新获取");
						$("#code_btn").html("获取验证码");
					}
				});
}

</script>
<nav><a href="javascript:history.go(-1);" class='goback'> <b></b></a>注册帐号</nav>
<form action="${basepath}/mobile/regist" id="register_form" autocomplete="off" method="post">
        <!-- 手机注册开始 -->
		<div id="error_display" style="text-align:center;color:red;">
                <!--错误信息显示区域-->
        </div>
        <section class="phone-register">
        	 <div id="password_tip_div" class="tip">请输入姓名</div>
        	<!-- 用户名 -->
            <div class="register-item">
                <div class="inputs ">
                    <label for="username">姓名：</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="输入姓名" value="${e.name!''}"/>
                </div>
                <div class="tip">请输入姓名</div>
            </div>
			
            <div class="register-item ">
                <div class="inputs ">
                    <label for="password">密 码：</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="输入密码" value="${e.password!''}" />
                </div>
                <div id="password_tip_div" class="tip">请输入密码</div>
            </div>
			
			<div class="register-item ">
                <div class="inputs ">
                    <label for="repassword">确认密码：</label>
                    <input type="password" class="form-control" name="password2" id="password2" placeholder="请确认密码" />
                </div>
                <div id="re_password_tip_div" class="tip">确认密码不一致</div>
            </div>
            <div class="register-item">
                <div class="inputs ">
                    <label for="phone">手机号：</label>
                    <input type="phone" class="form-control" id="mobile" name="mobile"  value="${e.mobile!''}" placeholder="输入手机号码"/>
                </div>
                <div class="tip">请输入手机号码</div>
            </div>
			
            <div class="register-item ">
                <div class="inputs verifycode-inputs">
                    <label for="phone_vcode">验证码：</label>
                    <input type="text" name="code" class="form-control" id="code" placeholder="输入手机验证码">
					<span id="getcode"><a id="code_btn" href="javascript:void(0);" onclick="javascript:send_code();" class="getcode">点击获取验证码</a></span>
                </div>
                <div class="tip">请输验证码</div>
            </div>
			
        </section>
        <!-- 注册按键 -->
        <section class="register-btn-2">
			<a href="javascript:void(0);" id="regist_btn" style="background-color:#004f85;">注 册</a>
        </section>
		<section class="register-btn-2">
            <a href="${basepath}/mobile/login.html<#if from??>?from=${from}</#if>">已有账号，去登 录</a>
        </section>
 		<input type="hidden" id="from" name="from" value="${from!''}" />
</form>
</body>
</@html.htmlBase>
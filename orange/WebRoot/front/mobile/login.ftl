<#import "/front/common/tpl/htmlBase.ftl" as html>
<@html.htmlBase checkLogin=true jsFiles=["jquery-1.10.2.min.js"] cssFiles=["main-wap.css"] checkLogin=false title="登录">
<body>
<script>
	$(function(){
		$('#login_btn').click(function(){
			var mobile = $("#mobile").val();
			var mobileReg = /^(((1[0-9]{2}))+\d{8})$/;
			if (!mobileReg.test(mobile)|| mobile.length<0 || mobile.length > 11) {
				$("#error_display").html("手机号码格式不正确");
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
			$("#login_form").submit();
		});
});

</script>
<nav><a href="javascript:history.go(-1);" class='goback'> <b></b></a>登录</nav>

<form action="${basepath}/mobile/login" id="login_form" name="login_form" autocomplete="off" accept-charset="utf-8"  method="post">
		   <div id="error_display" style="text-align:center;color:red;">
                <!--错误信息显示区域-->${errorMsg!''}
           </div>
		<section class="login-box">
			 <div id="password_tip_div" class="tip">请输入手机</div>
            <div class="register-item">
                <div class="inputs ">
                    <label for="mobile">手机：</label>
                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="请输入手机号码" value="${e.mobile!''}"/>
                </div>
                <div class="tip">请输入手机号码</div>
            </div>
            <div class="register-item">
                <div class="inputs ">
                    <label for="password">密 码：</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" value="${e.password!''}">
                </div>
                <div id="password_tip_div" class="tip">请输入密码</div>
            </div>
        </section>
        <!-- 登录按键 -->
        <section class="register-btn-2">
        	<a href="javascript:void(0);" id="login_btn" style="background-color:#004f85;">登 录</a>
        </section>
         <section class="register-btn-2">
			<a href="${basepath}/mobile/regist.html<#if from??>?from=${from}</#if>">立即去注册</a>
        </section>
        <section class="register-btn-2">
			<a href="${basepath}/mobile/forget.html<#if from??>?from=${from}</#if>">忘记密码</a>
        </section>
         <input type="hidden" id="from" name="from" value="${from!''}" />
</form>

</body>
</@html.htmlBase>
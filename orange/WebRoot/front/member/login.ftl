<#import "/front/common/tpl/htmlBase.ftl" as html>
<@html.htmlBase checkLogin=true jsFiles=["jquery-1.10.2.min.js","login.js"] cssFiles=["main.css", "login2.css"] checkLogin=false>
<body>
<link rel="stylesheet" href="${staticpath}/bootstrap3.3.4/css/bootstrap.min.css"  type="text/css">
<script type="text/javascript" src="${staticpath}/validator-0.7.0/jquery.validator.js"></script>
<script type="text/javascript" src="${staticpath}/validator-0.7.0/local/zh_CN.js"></script>
<script type="text/javascript" src="${staticpath}/bootstrap3.3.4/js/bootstrap.min.js"></script>
 <style tpe="text/css">
.login label{font-weight: 100;}
.login span{color:#ccc;}
 </style>
<div class="login_logo">
	<a href="${basepath}"><img src="${frontpath}/images/logo.png"/></a>
</div>

<div class="login" style="margin-top:100px;">
    <div class="header_login">
        <div class="switch" id="switch">
        	<a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
			<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;">
			</div>
        </div>
    </div>
    <div class="form-group">
    	<#if errorMsg??>
		    <div class="alert alert-danger alert-dismissable" style="border-color: #a94442;border-radius:0px;margin-bottom: 0px;">
			    <button type="button" class="close" data-dismiss="alert"
			   	aria-hidden="true">&times;</button>
		    	${errorMsg}
		    </div>
	   	</#if>
    </div>   
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">    
            <!--登录-->
            <div class="web_login" id="web_login">
               <div class="login-box">
					<div class="login_form">
						<form action="${basepath}/member/login" id="login_form" name="login_form" accept-charset="utf-8"  class="loginForm" method="post">
		                	<div class="uinArea" id="uinArea">
			                	<label class="input-tips" for="mobile">手机：</label>
			                	<div class="inputOuter" id="uArea">
	                    			<input type="text" id="mobile" name="mobile" value="${e.mobile!''}" class="inputstyle" data-rule="手机:required;mobile;"/>
	                			</div>
               				</div>
			                <div class="pwdArea" id="pwdArea">
				               	<label class="input-tips" for="password">密码：</label> 
				               	<div class="inputOuter" id="pArea">
				                     <input type="password" id="password" name="password" value="${e.password!''}" class="inputstyle" data-rule="密码:required;password;length[6~20];"/>
				                </div>
			                </div>
                			<div style="padding-left:50px;margin-top:20px;">
                				<input type="submit" id="login_btn" name="login_btn" value="登 录" style="width:150px;" class="button_blue"/>
                				<a href="javascript:void(0);" id="switch_qforget" class="zcxy">忘记密码</a>
                			</div>
             			 </form>
           			</div>    
            	</div>   
            </div>
  	</div>
  	
  	
  	<!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
    		<div class="web_login">
    			<form  action="${basepath}/member/regist" id="regist_form" name="regist_form" accept-charset="utf-8" method="post">
        			<ul class="reg_form" id="reg_ul">
                		<li>
	                		<label for="user"  class="input-tips2">姓名：</label>
		                    <div class="inputOuter2">
		                        <input type="text" id="name" name="name" maxlength="16" value="${e.name!''}" class="inputstyle2" data-rule="用户名:required;"/>
		                    </div>
                		</li>
                
		                <li>
		                	<label for="password" class="input-tips2">密码：</label>
		                    <div class="inputOuter2">
		                        <input type="password" id="password"  name="password" value="${e.password!''}" maxlength="16" class="inputstyle2" data-rule="密码:required;password;length[6~20];"/>
		                    </div>
		                </li>
		                <li>
		                	<label for="password2" class="input-tips2">确认密码：</label>
		                    <div class="inputOuter2">
		                        <input type="password" id="password2" name="password2" maxlength="16" class="inputstyle2" data-rule="确认密码:required;match(password);"/>
		                    </div>
		                </li>
		                <li>
		                	<label for="mobile" class="input-tips2">手机：</label>
		                    <div class="inputOuter2">
		                        <input type="text" id="mobile" name="mobile" value="${e.mobile!''}" maxlength="11" class="inputstyle2" data-rule="手机号码:required;mobile;remote[check];"/>
		                    </div>
		                </li>
						<li>
		                	<label for="code" class="input-tips2">验证码：</label>
		                    <div class="inputOuter2">
		                        <input type="text" id="code" name="code" maxlength="6" class="inputstyle2" data-rule="验证码:required;"/>
								<a id="code_btn" href="javascript:void(0);" class="zcxy" onclick="javascript:send_regist_code();">获取验证码</a>
		                    </div>
		                </li>
		                
		                <li>
		                    <div class="inputArea">
		                        <input type="button" id="regist_btn" name="regist_btn"  style="margin-top:10px;margin-left:85px;" class="button_blue" value="同意协议并注册"/> 
		                    </div>
		                </li>
		                <div class="cl"></div>
            		</ul>
            	</form>    
    		</div>
    </div>
    
    <!--注册-->
    <div class="qlogin" id="qforget" style="display: none; ">
    		<div class="web_login">
    			<form  action="${basepath}/member/forget" id="forget_form" name="forget_form" accept-charset="utf-8" method="post">
        			<ul class="reg_form" id="reg_ul">
        				<li>
		                	<label for="mobile" class="input-tips2">手机：</label>
		                    <div class="inputOuter2">
		                        <input type="text" id="mobile" name="mobile" value="${e.mobile!''}" maxlength="11" class="inputstyle2" data-rule="手机号码:required;mobile;"/>
		                    </div>
		                </li>
		                <li>
		                	<label for="password" class="input-tips2">密码：</label>
		                    <div class="inputOuter2">
		                        <input type="password" id="password"  name="password" value="${e.password!''}" maxlength="16" class="inputstyle2" data-rule="密码:required;password;length[6~20];"/>
		                    </div>
		                </li>
		                <li>
		                	<label for="password2" class="input-tips2">确认密码：</label>
		                    <div class="inputOuter2">
		                        <input type="password" id="password2" name="password2" maxlength="16" class="inputstyle2" data-rule="确认密码:required;match(password);"/>
		                    </div>
		                </li>
						<li>
		                	<label for="code" class="input-tips2">验证码：</label>
		                    <div class="inputOuter2">
		                        <input type="text" id="code" name="code" maxlength="6" class="inputstyle2" data-rule="验证码:required;"/>
								<a id="code_btn" href="javascript:void(0);" class="zcxy" onclick="javascript:send_forget_code();">获取验证码</a>
		                    </div>
		                </li>
		                
		                <li>
		                    <div class="inputArea">
		                        <input type="button" id="forget_btn" name="forget_btn"  style="margin-top:10px;margin-left:85px;" class="button_blue" value=" 确  定  "/> 
		                        <a href="javascript:void(0);" id="switch_qforget_back" class="zcxy">取消</a>
		                    </div>
		                </li>
		                <div class="cl"></div>
            		</ul>
            	</form>    
    		</div>
    </div>
    <!--注册end-->
</div>

</body>
</@html.htmlBase>
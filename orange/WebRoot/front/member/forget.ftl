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
        	<a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">忘记密码</a>
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
  	
  	
    <!--注册end-->
</div>

</body>
</@html.htmlBase>
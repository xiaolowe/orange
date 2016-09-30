<#import "/manage/common/tpl/htmlBase.ftl" as html>
<@html.htmlBase checkLogin=false staticJsFiles=["login.js"]>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title"><b></b>后台管理系统</h3>
                </div>
                <div class="panel-body">
                    <form role="form" id="formLogin" action="${basepath}/cas/user/login" method="post">
                        <fieldset>
                            <div class="form-group">
                                <#if errorMsg??>
                                    <div class="alert alert-danger alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert"
                                                aria-hidden="true">&times;</button>
                                    ${errorMsg}
                                    </div>
                                </#if>
                            </div>
                            <div class="form-group has-success">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <span class="fa fa-user fa-fw"></span>
                                    </span>
                                    <input type="text" value="${e.username!""}" placeholder="账号" name="username"
                                           class="len form-control" id="username" autofocus/>
                                </div>
                            </div>
                            <div class="form-group has-success">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <span class="fa fa-key fa-fw"></span>
                                    </span>
                                    <input type="password" name="password" placeholder="密码" class="len form-control"
                                           label="密码"/>
                                </div>
                            </div>
                            
                            <input type="submit" id="btnLogin" class="btn btn-lg btn-success btn-block" value="登录">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
   
</div>
</body>
</@html.htmlBase>
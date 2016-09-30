<#macro htmlBase title="" jsFiles=[] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin=true>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script>
        var basepath = "${basepath}";
        var staticpath = "${staticpath}";
         <#if currentUser()??>
            var login = true;
        var currentUser = "${currentUser().username}";
        <#else >
        var login = false;
        var currentUser = "";
            <#if checkLogin>
                top.location = "${basepath}/cas/user/logout";
            </#if>
        </#if>
    </script>
    <meta name="description" content="CAS-ORANGE用户中心"/>
    <meta name="keywords" content="CAS-ORANGE用户中心"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${(title?? && title!="")?string("CAS-ORANGE" , "CAS-ORANGE用户中心")}</title>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${staticpath}/fonts/fonts.css"" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${staticpath}/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->

<!-- BEGIN PAGE STYLES -->
<!-- END PAGE STYLES -->

<!-- BEGIN THEME STYLES -->
<link href="${staticpath}/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/local/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${staticpath}/local/layout/css/themes/light2.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${staticpath}/local/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" type="image/x-icon" href="${staticpath}/images/favicon.ico">
<script src="${staticpath}/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${staticpath}/manage.js" type="text/javascript"></script>
	<#list staticJsFiles as jsFile>
        <script src="${staticpath}/${jsFile}"></script>
    </#list>
    <#list staticCssFiles as cssFile>
        <link rel="stylesheet" href="${staticpath}/${cssFile}" />
    </#list>

    <#list jsFiles as jsFile>
        <script src="${staticpath}/${jsFile}"></script>
    </#list>
    <#list cssFiles as cssFile>
        <link rel="stylesheet" href="${staticpath}/${cssFile}" />
    </#list>
</head>
<#nested />
</html>
</#macro>

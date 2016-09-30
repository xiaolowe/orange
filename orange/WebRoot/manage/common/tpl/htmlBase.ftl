<#macro htmlBase title="" jsFiles=[] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin=true>
<!DOCTYPE html>
<html>
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
    <link rel="shortcut icon" type="image/x-icon" href="${staticpath}/images/favicon.ico">
    <link rel="stylesheet" href="${staticpath}/zTree3.5/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${staticpath}/bootstrap3.3.4/css/bootstrap.min.css"  type="text/css">
    <link rel="stylesheet" href="${staticpath}/jquery-ui-1.11.2/jquery-ui.css">
    <link rel="stylesheet" href="${staticpath}/validator-0.7.0/jquery.validator.css" />
    <script type="text/javascript" src="${staticpath}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${staticpath}/zTree3.5/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="${staticpath}/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${staticpath}/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="${staticpath}/ui-alert-dialog-api.js"></script>
    <script type="text/javascript" src="${staticpath}/ui-blockui.js"></script>
    <script type="text/javascript" src="${staticpath}/bootstrap3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${staticpath}/sb-admin/css/sb-admin-2.css" />
    <script src="${staticpath}/sb-admin/js/sb-admin-2.js" ></script>

    <link href="${staticpath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <script src="${staticpath}/jquery-ui-1.11.2/jquery-ui.js"></script>

    <script type="text/javascript" src="${staticpath}/validator-0.7.0/jquery.validator.js"></script>
    <script type="text/javascript" src="${staticpath}/validator-0.7.0/local/zh_CN.js"></script>

    <script type="text/javascript" src="${staticpath}/My97DatePicker/WdatePicker.js"></script>

    <link rel="stylesheet" href="${staticpath}/kindeditor-4.1.7/themes/default/default.css" />
    <script charset="utf-8" src="${staticpath}/kindeditor-4.1.7/kindeditor-min.js"></script>
    <script charset="utf-8" src="${staticpath}/kindeditor-4.1.7/lang/zh_CN.js"></script>

    <!-- datatables -->
    <link rel="stylesheet" href="${staticpath}/datatables/css/jquery.dataTables.css" />
    <script charset="utf-8" src="${staticpath}/datatables/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="${staticpath}/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
    <script charset="utf-8" src="${staticpath}/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"></script>

    <!-- metisMenu -->
    <link href="${staticpath}/metisMenu/metisMenu.min.css" rel="stylesheet">
    <script src="${staticpath}/metisMenu/metisMenu.min.js"></script>
    <script type="text/javascript" src="${staticpath}/manage.js"></script>
    <#list staticJsFiles as jsFile>
        <script src="${staticpath}/${jsFile}"></script>
    </#list>
    <#list staticCssFiles as cssFile>
        <link rel="stylesheet" href="${staticpath}/${cssFile}" />
    </#list>

    <#list jsFiles as jsFile>
        <script src="${basepath}/${jsFile}"></script>
    </#list>
    <#list cssFiles as cssFile>
        <link rel="stylesheet" href="${basepath}/${cssFile}" />
    </#list>
</head>
<#nested />
</html>
</#macro>

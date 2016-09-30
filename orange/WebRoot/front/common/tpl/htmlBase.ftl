<#macro htmlBase title="" fav="" keywords="" descs="" jsFiles=[] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin="" from="">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="renderer" content="webkit">
    <script>
        var basepath = "${basepath}";
        var frontpath = "${frontpath}";
        <#if currentMember()??>
            var login = true;
        	var currentUser = "${currentMember().name}";
        <#else >
        	var login = false;
        	var currentUser = "";
            <#if checkLogin>
            	<#if from?default('')=='mobile'>
                	top.location = "${basepath}/mobile/login.html?from=" + window.location.href;
                <#else>
                	top.location = "${basepath}/member/login.html?a=login&from=" + window.location.href;
                </#if>
            </#if>
        </#if>
        
        //cookie 操作
function setCookie(name,value){ 
    var Days = 365; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ encodeURIComponent (value) + ";expires=" + exp.toGMTString()+";path="+basepath+";domain="+document.domain; 
}
//获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : '';
	}
}


function delCookie(name) 
{ 
    var cval=getCookie(name); 
    if(cval!=null)
        document.cookie= name + '='+null+';expires='+ new Date(0).toGMTString()+";path="+basepath+";domain="+document.domain; ;
}

function delAllCookie(){
	var keys=document.cookie.match(/[^ =;]+(?=\=)/g); 
	if (keys) {
		for (var i = keys.length; i--;)
			document.cookie=keys[i]+'='+null + ';expires=' + new Date(0).toUTCString() +";path="+basepath+";domain="+document.domain; ;
	} 
}
</script>
    <meta name="description" content="${descs}"/>
    <meta name="keywords" content="${keywords}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><#if title??>${title}<#else>Orange</#if></title>
    <link rel="shortcut icon" type="image/x-icon" href="${fav}">
    <!--
    <link rel="stylesheet" href="${frontpath}/css/main.css" type="text/css">
    <link rel="stylesheet" href="${frontpath}/css/login2.css"  type="text/css">
    <link rel="stylesheet" href="${frontpath}/css/inside.css">
    <script type="text/javascript" src="${frontpath}/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${frontpath}/js/excanvas.js"></script>
    <script type="text/javascript" src="${frontpath}/js/jquery.ba-throttle-debounce.min.js"></script>
    <script type="text/javascript" src="${frontpath}/js/jquery.kinMaxShow-1.1.min.js"></script>
    <script type="text/javascript" src="${frontpath}/js/jquery.kinMaxShow-1.1.src.js"></script>
    <script type="text/javascript" src="${frontpath}/js/jquery.knob.min.js"></script>
    <script type="text/javascript" src="${frontpath}/js/jquery.redcountdownn.js"></script>
    <script type="text/javascript" src="${frontpath}/js/jquery.SuperSlide.2.1.1.js"></script>
    <script type="text/javascript" src="${frontpath}/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${frontpath}/js/login.js"></script>
    <script type="text/javascript" src="${frontpath}/js/lrscroll.js"></script>
    <script type="text/javascript" src="${frontpath}/js/pageGroup.js"></script>
    <script type="text/javascript" src="${frontpath}/js/simplefoucs.js"></script>
    <script type="text/javascript" src="${frontpath}/js/zoom.js"></script>-->
    
    <#list staticJsFiles as jsFile>
        <script src="${frontpath}/${jsFile}"></script>
    </#list>
    <#list staticCssFiles as cssFile>
        <link rel="stylesheet" href="${frontpath}/${cssFile}" />
    </#list>

    <#list jsFiles as jsFile>
        <script src="${frontpath}/js/${jsFile}"></script>
    </#list>
    <#list cssFiles as cssFile>
        <link rel="stylesheet" href="${frontpath}/css/${cssFile}" />
    </#list>
</head>
<#nested />
</html>
</#macro>
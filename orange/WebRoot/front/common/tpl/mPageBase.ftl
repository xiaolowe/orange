<#import "htmlBase.ftl" as html />
<#import "mHeader.ftl" as header />
<#import "foot.ftl" as foot />
<#macro mPageBase currentIndex="" jsFiles=[] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin="">
<@html.htmlBase title=currentSetting().title fav=currentSetting().fav descs=currentSetting().descs 
keywords=currentSetting().keywords jsFiles=jsFiles cssFiles=cssFiles staticJsFiles=staticJsFiles staticCssFiles=staticCssFiles checkLogin=checkLogin >
<body>
    <@header.header navIndexs=currentNavs() topIndex=topIndex currentIndex=currentIndex setting=currentSetting()/>
     <#nested />
     <@foot.foot links=currentLinks() helps=currentCatelogs() setting=currentSetting()/>
</body>
</@html.htmlBase>
</#macro>
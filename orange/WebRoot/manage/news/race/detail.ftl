<#import "/manage/common/tpl/html.ftl" as html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta name="keywords" content="${e.title}">
<meta name="description" content="${e.title}">
<title>${e.title}</title>
<link rel="stylesheet" type="text/css" href="${basepath}/manage/news/article/css/page_mp_article_improve.css">
<link rel="stylesheet" type="text/css" href="${basepath}/manage/news/article/css/not_in_mm.css">
<@html.html>

<style type="text/css">

img{
	width:100%;
}
</style>

    <div id="js_article" class="rich_media">
        
        <div id="js_top_ad_area" class="top_banner">
 
        </div>
        <div class="rich_media_inner">
            <div id="page-content">
                <div id="img-content" class="rich_media_area_primary">
                    <h2 class="rich_media_title" id="activity-name">
                        ${e.title}
                    </h2>
                    <div class="rich_media_meta_list">
						<#if e.publishTime??>
                        	<em id="post-date" class="rich_media_meta rich_media_meta_text">${e.publishTime?substring(0,10)}</em>
                        </#if>
                        <span class="rich_media_meta rich_media_meta_text rich_media_meta_nickname">orange</span>
						 <a class="rich_media_meta rich_media_meta_link rich_media_meta_nickname" href="#">orange</a>
                    </div>
                    <div class="rich_media_content " id="js_content">
                    	${e.content!""}
					</div>
                    
                    <link rel="stylesheet" type="text/css" href="${basepath}/manage/news/article/css/page_mp_article_improve_combo.css">
                    <div class="rich_media_tool" id="js_toobar3">
                        <div id="js_read_area3" class="media_tool_meta tips_global meta_primary">阅读 
	                        <span id="readNum3">
	                        	<#if e.num??>
	                        		${e.num}
	                        	</#if>
	                        </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@html.html>
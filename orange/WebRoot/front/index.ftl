<#import "/front/common/tpl/fPageBase.ftl" as page>

<@page.pageBase currentIndex="index" jsFiles=["jquery-1.10.2.min.js","jquery.kinMaxShow-1.1.min.js","lrscroll.js","jquery.knob.min.js","jquery.ba-throttle-debounce.min.js","jquery.redcountdown.js"] 
cssFiles=["main.css"] checkLogin=false>

<script src="${frontpath}/js/jquery.SuperSlide.2.1.1.js"></script>

<style type="text/css">
/* 本例子css */
.slide{ width:100%; height:auto; overflow:hidden; position:relative;}
.slide .hd{ height:20px; overflow:hidden; position:absolute; right:25px; bottom:25px; z-index:1; }
.slide .hd ul{ overflow:hidden; zoom:1; float:left;  }
.slide .hd ul li{ float:left; margin-right:10px;  width:20px; height:20px; text-align:center; background:#fff; cursor:pointer; border-radius:10px;}
.slide .hd ul li.on{ background:#004f85; color:#fff; }
.slide .bd{ position:relative; height:100%; z-index:0;   }
.slide .bd li{ zoom:1; vertical-align:middle; }
.slide .bd img{ width:100%;  height:auto; display:block;  }
/* 下面是前/后按钮代码，如果不需要删除即可 */
.slide .prev,
.slide .next{ position:absolute; left:3%; top:50%; margin-top:-25px; display:block; width:32px; height:40px; background:url(${frontpath}/images/slider-arrow.png) -110px 5px no-repeat; filter:alpha(opacity=50);opacity:0.5;   }
.slide .next{ left:auto; right:3%; background-position:8px 5px; }
.slide .prev:hover,
.slide .next:hover{ filter:alpha(opacity=100);opacity:1;  }
.slide .prevStop{ display:none;  }
.slide .nextStop{ display:none;  }
</style>
<div id="slideBox" class="slide">
	<div class="hd">
		<ul><li>1</li><li>2</li><li>3</li></ul>
	</div>
	<div class="bd">
		<ul>
		<#list currentBanners() as banner>
			<li><a href="${banner.link}" target="_blank"><img src="${banner.img}" /></a></li>
			
		</#list>	
		</ul>
	</div>

	<!-- 下面是前/后按钮代码，如果不需要删除即可 -->
	<a class="prev" href="javascript:void(0)"></a>
	<a class="next" href="javascript:void(0)"></a>

</div>
<script type="text/javascript">
//delAllCookie();
jQuery(".slide").slide({mainCell:".bd ul",effect:"left",autoPlay:true});
</script>


<!-- 开始 -->
<div class="data">
	<div class="w1200">
		<div class="dao">开始倒计时</div>
		<div id="rC_D" class="redCountdownDemo"></div>
		<div class="clear"></div>
		<#if race??><div class="go_join"><a href="${basepath}/races/events/${race.id}.html"> target="_blank">><span>${race.name}</span></a></div></#if>
		<script type="text/javascript">
			var MAX_LABEL_ZOOM = 24;//px
			var COLOR_RGBA_NUM = '255,255,255,1';//num
			var COLOR_RGBA_LABEL = '255,255,255,0.4';//DHMS
			var COLOR_RGBA_GUAGE_FG = '255,255,255,0.4';//ring left
			var COLOR_RGBA_GUAGE_BG = '255,255,255,1';//ring run
			$('#rC_D').redCountdown({
				end: '${end}',
				now: '${now}',
				labels: true,
				labelsOptions: {
					lang: {
						days: '天',
						hours: '时',
						minutes: '分',
						seconds: '秒'
					},
					style: 'font-size:'+MAX_LABEL_ZOOM+'px; text-transform:uppercase;color:rgba('+COLOR_RGBA_LABEL+');'
				},
				style: {
					element: "",
					textResponsive: .5,
					daysElement: {
						gauge: {
							thickness: .02,
							bgColor: 'rgba('+COLOR_RGBA_GUAGE_FG+')',
							fgColor: 'rgba('+COLOR_RGBA_GUAGE_BG+')',
							lineCap: 'round'
						},
						textCSS: 'font-family:\'Microsoft yahei\'; font-size:1.5em; font-weight:300; color:rgba('+COLOR_RGBA_NUM+');'
					},
					hoursElement: {
						gauge: {
							thickness: .02,
							bgColor: 'rgba('+COLOR_RGBA_GUAGE_FG+')',
							fgColor: 'rgba('+COLOR_RGBA_GUAGE_BG+')',
							lineCap: 'round'
						},
						textCSS: 'font-family:\'Microsoft yahei\'; font-size:1.5em; font-weight:300; color:rgba('+COLOR_RGBA_NUM+');'
					},
					minutesElement: {
						gauge: {
							thickness: .02,
							bgColor: 'rgba('+COLOR_RGBA_GUAGE_FG+')',
							fgColor: 'rgba('+COLOR_RGBA_GUAGE_BG+')',
							lineCap: 'round'
						},
						textCSS: 'font-family:\'Microsoft yahei\'; font-size:1.5em; font-weight:300; color:rgba('+COLOR_RGBA_NUM+');'
					},
					secondsElement: {
						gauge: {
							thickness: .02,
							bgColor: 'rgba('+COLOR_RGBA_GUAGE_FG+')',
							fgColor: 'rgba('+COLOR_RGBA_GUAGE_BG+')',
							lineCap: 'round'
						},
						textCSS: 'font-family:\'Microsoft yahei\'; font-size:1.5em; font-weight:300; color:rgba('+COLOR_RGBA_NUM+');'
					},
					
				},
				onEndCallback: function() { console.log("Time out!"); }
			});
		</script>		
	</div>
</div>

<script>
	$(function(){
	$(".game_item").each(function(){
		var that=this
		$(that).bind({
			mouseenter:function(){
				item4Timer=setTimeout(function(){
					$(that).find('div.content-block-inner').fadeIn(400);
				},100);
			},
			mouseleave:function(){
				clearTimeout(item4Timer);	
				$(that).find('div.content-block-inner').fadeOut(400);
				}
		});
	})	
});
</script>


<#if races?size gt 0>
	<div class="game_box">
		<div class="w1200">
			<div class="game">
				<div class="con_title"><h3>最新赛事</h3></div>
				<ul>
						<#list races as race>
							<li class="game_item">
								<p class="p1">${race.name}<br/><span>
								<#if race.time?? && race.time?length gt 10>${race.time?substring(0,10)}<#else>${race.time!""}</#if></span></p>
								<p class="p4">
									<a href="${basepath}/races/events/${race.id}.html" target="_blank">
										<img src="${race.slt}"  class="zoom"/>	
									</a>
								</p>
								<div class="content-block-inner">
									${race.descs}
								</div>
							</li>
						</#list>
				</ul>
			</div>
		</div>
	</div>
</#if>

<#if (newsA?size + newsR?size) gt 0>
	<div class="news_box">
		<div class="w1200">
			<div class="news">
				<div class="con_title"><h3>赛事资讯</h3></div>
				<div id="featureContainer">
					<div id="feature">
						<div id="block">
							<div id="botton-scroll">
								<ul class="featureUL">
									<#list newsR as racN>
										<li class="featureBox">
				  							<div class="box">
				  								<a href="${basepath}/news/r/${racN.id}.html" target="_blank">
				  									<div class="img">
				  										<img  class="zoom" src="${racN.sltImg}" width=240 height=150/>
				  									</div>
				  									<p class="p1"><#if racN.title?? && racN.title?length gt 16>${racN.title?substring(0,16)}<#else>${racN.title!''}</#if></p>
				  								</a>
				  								<p>${racN.digest}</P>
				  							</div>
				  						</li>
				  					</#list>
				  					<#list newsA as artN>
				  						<li class="featureBox">
				  							<div class="box">
				  								<a href="${basepath}/news/n/${artN.id}.html" target="_blank">
				  									<div class="img">
				  										<img  class="zoom" src="${artN.sltImg}" width=240 height=150/>
				  									</div>
				  									<p class="p1"><#if artN.title?? && artN.title?length gt 16>${artN.title?substring(0,16)}<#else>${artN.title!""}</#if></p>
				  								</a>
				  								<p>${artN.digest}</P>
				  							</div>
				  						</li>
				  					</#list>
								</ul>
			  				</div>
			  			</div>
			  			<#if (newsA?size + newsR?size) gt 4>
							<a class="prev" href="javascript:void();">Previous</a>
							<a class="next" href="javascript:void();">Next</a>
						</#if>
					</div>
				</div>
			</div>
		</div>
	</div>
</#if>

</@page.pageBase>
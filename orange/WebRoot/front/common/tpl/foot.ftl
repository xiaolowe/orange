<#macro foot links=[] helps=[] setting=[]>

<div class="footer_box">
	<!-- link -->
	<div class="partner_box">
		<div class="partner w1200">
			<div class="con_title" style="padding-bottom:20px"><h3>合作伙伴</h3></div>
			<#list links as item>
				<a href="${item.link}" target="_blank"><img src="${item.img}"/></a>
			</#list>
		</div>
	</div>
	
	<script>
	 $(function(){
	 	 var path = window.location.pathname;
		 $("a.footer-item").each(function(index){
	            var href = $(this).attr("href");
	             if($(this).attr("href")==path){
		        	var img = $(this).data("img");
		        	var index_select_footer_img =img;
		        	//alert("foot_img=" + index_select_footer_img);
		        	if(index_select_footer_img.length>0){
				        $("#topImg").attr("src", index_select_footer_img);
						$("#topimg").css("display", "block");
						$(".here").css("display", "block");
		        	}else{
			        	$("#topImg").attr("src", "");
						$("#topimg").css("display", "none");
						$(".here").css("display", "none");
			        }
			        var name = $(this).data("name");
			        var index_select_footer_name =name;
			        //alert("foot_name=" + index_select_footer_name);
			        if(index_select_footer_name.length>0){
				       $("#menuA").html(index_select_footer_name);
						$(".here").css("display", "block");
		        	}else{
						$(".here").css("display", "none");
			        }
		         }
	      });
	 
		
	});
	</script>
	<!-- help -->
	<div class="footer w1200">
	
		<#list helps as item>
			<ul>
				<li class="title">${item.cateloge.name}</li>
				<#list item.contents as cts>
					<li><a class="footer-item" href="${basepath}/pages/${item.cateloge.id}/${cts.id}.html" data-img="${item.cateloge.banner}" data-name="${item.cateloge.name}">${cts.name}</a></li>
				</#list>
			</ul>
		</#list>
		
		<div class="clearfix"></div>
		<div class="copyright">${setting.cpr!''}<br/>${setting.icp!''}<br/>技术支持：<a href="http://www.cnshine.net" target="_blank">尚赢网络</a></div>
	</div>
</div>
</#macro>
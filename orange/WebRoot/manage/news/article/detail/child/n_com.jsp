<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base/commons/pages/taglibs.jsp" %>
<%@ page import="cn.finedo.base.utils.string.StringHelper"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="PRAGMA" content="NO-CACHE"/>
<title>评论内容</title>

</head>

<body>

<span id="wapxfxqy_B03_02">
				<div class="bbslist">
				
					<ul>
					<or-cms:comment sort="'x','r'" type="x" id="${param.id}" mid="${param.mid}"  pagesize="0,5">
						<li>
							<a  class="arr-rt1">
								<div class="lt1">
									<img src="${ctx }/upload/file/${comment.img}" style="border-radius:60px;" onerror="this.src='${ctx}/upload/file/news_21.png'" alt="" class="header_img">
								</div>
								<div class="rt">
									<p class="f12 f999" >${comment.mname}
										
										<span style="margin-right:.5em;"></span>
										${comment.ctime}
										
									</p>
									<p>${comment.concent}
									</p>
									
									<p>
										<% String zimg ="huise-zan.png";
										   String optz  ="z";
										   String cimg ="huise-cai.png";
										   String optc  ="c";
										if("z".equals(comment.getIsaction())){
											zimg ="hongse-zan.png";
											 optz="zg";
										}										
										if("c".equals(comment.getIsaction())){
											cimg ="hongse-cai.png";
											optc ="cg";
										}
										//System.out.println("zimg@"+zimg);
										//System.out.println("cimg@"+zimg);
										//System.out.println("optz@"+zimg);
										//System.out.println("optc@"+zimg+"\r");
										%>
										
										<a onclick="addPraise('${param.mid}','${comment.cid}','<%=optz%>','p','<%=optc%>','pl')" class="flor" style="margin-right:15px;">
									
											<img src="${ctx}/d/newsDetails/img/<%=zimg %>" style="width:18px; height:18px;"/>
											<span class="f12 f999">${comment.actionZ}</span>
										</a>
									
										
										<a onclick="addPraise('${param.mid}','${comment.cid}','<%=optc%>','p','<%=optz%>','pl')" class="flor" style="margin-right:15px;">
										
											<img src="${ctx}/d/newsDetails/img/<%=cimg %>" style="width:18px; height:18px;"/>
											<span class="f12 f999">${comment.actionC}</span>
										</a>
										
										
										<% String str = "//@"+comment.getMname()+":"+comment.getConcent(); %>
										<%if(StringHelper.isNotNull(comment.getAtarget())) {%>
										<a onclick="th('${param.mid}','${comment.mid}','r','h','${comment.atarget}','<%=str%>')" class="flor" style="margin-right:15px;">
											<img src="${ctx}/d/newsDetails/img/discuss.png"  style="width:18px; height:18px;"/>
										</a>
										 <%}else{%>
										 <a onclick="th('${param.mid}','${comment.mid}','r','p','${comment.cid}','<%=str%>')" class="flor" style="margin-right:15px;">
											<img src="${ctx}/d/newsDetails/img/discuss.png"  style="width:18px; height:18px;"/>
										</a>
										  <%} %>
									</p>
									
								</div>
								
							</a>
						</li>
					 </or-cms:comment>
					</ul>
				</div>
			</span>
  
<script type="text/javascript">
</script>
</body>
</html>
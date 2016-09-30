<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/base/commons/pages/taglibs.jsp"%>
<link href="${ctx}/base/commons/scripts/checktree/style/tree.css"
	rel="stylesheet" type="text/css" />
<script src="${ctx}/base/commons/scripts/checktree/jquery.tree.js"
	type="text/javascript"></script>
<div align="center">
	<div align="left" style="width: 98%">
		<div id="divadd" style="display: block;">
			<div class="popup">
				<div class="popup-header">
					<h2>添加评论</h2>
					<a href="javascript:;" onclick="$.closePopupLayer('comment')"
						title="关闭" class="close-link">关闭</a>
				</div>
				<div id="ptable1" style="text-align: left;margin-top: 4px;">
				<form action="${ctx }/clientAction.do?method=json&classes=commentServiceImpl&common=add" id="addform" method="post" >
					<input type="text" id="mid" name="mid" value="${param.mid}" />
					<input type="text" id="id" name="id" value="${param.id}" />
					<input type="text" id="type" name="type" value="${param.type}" />
					<input type="text" id="action" name="action" value="${param.action}" />
					<input type="text" id="atarget" name="atarget" value="${param.atarget}" />
				  	<table>
						<tr>
							<td>
								<textarea rows="5" cols="8" name="concent" id="concent" ></textarea>
							</td>
						</tr>
	    			</table>
			  		<div style="text-align: center;">
						<input type="button" value="提交" onclick="save()" />
					</div>
				</form>
				</div>	
			</div>
		</div>
		</div>
		
	</div>
	<script type="text/javascript">
	function save(){
		var options = { 
	 	        success:       addcallback,
	 	        type:      'post',
	 	        dataType:  'json',
	 	        clearForm: false        
	 	    };
	 	$('#addform').ajaxSubmit(options);
	}
	function addcallback(data){
		if(data.jsondata.retCode == "0000"){
			$.closePopupLayer('comment');
			alert(data.jsondata.retDesc);
			querypage(id,mid);
		}else{
			$.closePopupLayer('comment');
			alert(data.jsondata.retDesc);
			querypage(id,mid);
		}
	}
	</script>
</div>
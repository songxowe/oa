<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML >
<html>
<head>
<meta charset="utf-8">
<title>邮件管理--草稿箱个人</title>
<script
	src="${pageContext.request.contextPath }/resources/js/jquery-2.2.2.js"></script>
</head>

<body>

	
	
	<form action="mofify" method="post" name="from1" id="uploadForm"
		enctype="multipart/form-data" >
		<input type="hidden" name="fromId"
			value="${sessionScope['NEWER_USER_LOGIN_INFO'].userTrueName}">
			<input type="hidden" name="mailId" id="mailId"
			value="${priMail.mailId}">
		<table border="1">
			<tr>
				<td colspan="2">收件人：<input name="toId" id="toId" value="${priMail.toId}"
					type="text" size="40px">
				</td>
				<td>发送方式：<select name="copy" id="copy">
						<option value="抄送">抄送</option>
						<option value="密送" selected="selected">密送</option>
				</select></td>
				<td>重要程度：<select name="important">
						<option value="一般">一般</option>
						<option value="重要" selected="selected">重要</option>
						<option value="非常重要">非常重要</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="4">主题：<input type="text" name="subject" value="${priMail.subject}"></td>
			</tr>
			<tr>
				<td colspan="4">附件：<input type="text" id="name" name="name" value="${priMail.attachment}">
				</td>
			</tr>
			<tr>
				<td colspan="2" rowspan="10"><textarea rows="40" cols="73" name="content">
				${priMail.content}</textarea></td>
				<td>
					<table id="tab" style="display: none;"></table>
					

				</td>
			</tr>
		</table>
		<input type="submit" value="提交" id="but" onclick="addOut.add('but');">
		<input type="submit" value="草稿箱" id="sub" onclick="addOut.add('sub');">

	</form>

	<div></div>


	<script type="text/javascript">
		/* 提交或者存入草稿箱 */
		$(function() {
			addOut = {
				add : function(state) {
					if (state == 'but') {
						url = "mofify?sendFlag=1&mailId="+$("#mailId").val();
					} else if (state == 'sub') {
						url = "mofify?sendFlag=0&mailId="+$("#mailId").val();
					}
					
					$("#uploadForm").form({
						url : url,
						success : function(data){
							if(data) {
								$.messager.show({
									title : "提示",
									msg : data
								});
								
							}
						}
					});
				}
			}
		});

	</script>
</body>
</html>

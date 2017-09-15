<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css">
</head>
<body>
	<p><a href="javascript:showLogin()">修改基本信息</a>
    <a href="javascript:showreg()">修改投票项</a></p>
	<div id="modifyVote">
		<form action="${pageContext.request.contextPath }/pubVote.html" onsubmit="return checkInput()" method="post">
			<input type="hidden" name="_method" value="post">
			<input type="text" id="voteId" name="voteId" value="${pubVote.voteId }">
			<table id="tab1" cellpadding="0" cellspacing="0" style="float: left; height:321px;">
				<tr>
					<th>投票标题</th>
					<td><input type="text" name="subject" value="${pubVote.subject }" required></td>
				</tr>
				<tr>
					<th>投票描述</th>
					<td><input type="text" name="descn" value="${pubVote.descn }" required></td>
				</tr>
				<tr>
					<th>终止时间</th>
					<td><input class="easyui-datetimebox" name="endTime" value='<fmt:formatDate value="${pubVote.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' style="width: 97%" data-options="required:true"></td>
				</tr>
				<tr>
					<th>发布范围(部门)</th>
					<td><input type="text" name="toDept" value="${pubVote.toDept }" ></td>
				</tr>
				<tr>
					<th>发布范围(职位)</th>
					<td><input type="text" name="toRank" value="${pubVote.toRank }"></td>
				</tr>
				<tr>
					<th>发布范围(个人)</th>
					<td><input type="text" name="toId" value="${pubVote.toId }"></td>
				</tr>
				<tr align="center" >
					<th colspan="2"><input type="submit" value="提交投票"></th>
				</tr>
			</table>
			<table id="tab2" border="1" style="float: left;">
				<c:if test="${empty pubVote }">
					<tr>
						<th colspan="3">点击右侧按钮添加投票项输入</th>
					</tr>
				</c:if>
				<c:if test="${!empty pubVote }">
					<tr align="center">
					<th>编号</th>
					<th>投票项</th>
					<th>投票口号</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pubVote.subs }" var="sub" varStatus="status">
					<tr name="${sub.subId }">
						<th>${sub.subId }</th>
						<th><input type="text" value="${sub.title }" readonly></th>
						<th><input type="text" value="${sub.descn }"></th>
						<td><input type='button' value='删除' onclick="removeVote(${sub.subId })"/></td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<input type="button" id="additem" value="添加投票项"> 
			<input type="hidden" name="votename" id="votename">
		</form>
	</div>
	<script type="text/javascript">
		var count=$("#tab2 input[type='text']").length+1;
		var row = 1;//记录加载多少个投票项
		var ids = [];//用于保存所有输入的投票项

		$(function() {
			//alert(1123);
			$("#additem")
					.click(
							function() {
								$("#votename").val(row);
								if (row <= 10) {
									var tr = "<tr name="+row+"><th>"
											+ count
											+ "</th><td><input id='"
											+ row
											+ "' type='text' style='width:97%' required/></td><td><input type='button' value='删除' onclick='removeVote(" + row + ")'/></td></tr>";
									$("#tab2").append(tr);
									row++;
									count++;
								}
								//row是变化了的
								if (row > 10) {
									$("#additem").hide();
								}
							});
		});

		function checkInput() {
			//alert(123);
			if (row < 3) {
				alert("至少添加2个投票项!");
				return false;
			}
			$("#tab2 input[type='text']").each(function() {
				//alert($(this).val());
				ids.push($(this).val());//把输入的投票项存入数组中
			});
			var str = ids.join(",");//join方法,将数组每个元素按定义的分隔符拼接成字符串
			$("#votename").val(str);
			return true;
		}

		function removeVote(id) {
			$("#additem").show();
			$("#tab2 tr[name=" + id + "]").remove();
			//alert($("#tab2 input[type='text']").length);
			row=$("#tab2 input[type='text']").length+1;
		}
	</script>
</body>
</html>
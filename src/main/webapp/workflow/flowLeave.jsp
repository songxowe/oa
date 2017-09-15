<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>审批记录</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
		<c:if test="${empty flowLeaves}">
			<h2>没有审批记录</h2>
		</c:if>
		<c:if test="${!empty flowLeaves}">
			<table>
				<tr>
					<td>序号</td>
					<td>审批人</td>
					<td>审批时间</td>
					<td>审批意见</td>
					<td>审批结果</td>
				</tr>
				<c:forEach items="${flowLeaves}" var="flowLeave" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td>${flowLeave.checker}</td>
						<td><fmt:formatDate value="${flowLeave.checkDate}" pattern="yyyy-MM-dd"/> </td>
						<td>${flowLeave.checkIdea}</td>
						<td>${flowLeave.checkStatus}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>

</html>

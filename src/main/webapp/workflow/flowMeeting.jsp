<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>会议审批记录</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
		<c:if test="${empty flowMeetings}">
			<h2>没有审批记录</h2>
		</c:if>
		<c:if test="${!empty flowMeetings}">
			<table>
				<tr>
					<td>序号</td>
					<td>审批人</td>
					<td>审批时间</td>
					<td>审批意见</td>
					<td>审批结果</td>
				</tr>
				<c:forEach items="${flowMeetings}" var="flowMeeting" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td>${flowMeeting.checker}</td>
						<td><fmt:formatDate value="${flowMeeting.checkDate}" pattern="yyyy-MM-dd"/> </td>
						<td>${flowMeeting.checkIdea}</td>
						<td>${flowMeeting.checkStatus}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>

</html>

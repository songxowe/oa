<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>会议申请详情</title>
		<%@ include file="../commons/meta.jsp"%>
	</head>

	<body>
		<div style="margin: auto;width: 500px;">
			<div style="margin-top: 50px;">
				<table class="table" style="width: 500px;" >
					<tbody>
					<tr>
						<td align="right">申请人</td>
						<td>${meeting.proposer}</td>
					</tr>
					<tr>
						<td align="right">主题</td>
						<td>${meeting.MTopic}</td>
					</tr>
					<tr>
						<td align="right">会议室</td>
						<td>
							${meeting.meetingRoom.mrName}
						</td>
					</tr>
					<tr>
						<td align="right">开始时间</td>
						<td><fmt:formatDate value="${meeting.MStart}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td align="right">结束时间</td>
						<td><fmt:formatDate value="${meeting.MEnd}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td align="right">会议描述</td>
						<td><textarea name="leaveReason" readonly rows="4" cols="30">${meeting.MDesc}</textarea></td>
					</tr>
					<tr>
						<td align="right">会议人员</td>
						<td><textarea name="leaveReason" readonly rows="4" cols="30">${meeting.MAttendee}</textarea></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>

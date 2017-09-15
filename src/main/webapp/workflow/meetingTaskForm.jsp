<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>会议审批</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
		<div style="margin: auto;width: 500px;">
			<form action="" method="post" id="finishTask">
				<input type="hidden" name="taskId" value="${taskId}">
				<input type="hidden" name="checker" value="${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName}">
			<table style="width: 500px;" >
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
				<tr>
					<td>审批意见</td>
					<td>
						<textarea name="checkIdea" rows="4" cols="30"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">批准</button>&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			</form>
			<c:if test="${!empty flowMeetings}">
				<table>
					<tr>
						<td>审批人</td>
						<td>审批时间</td>
						<td>审批意见</td>
						<td>审批结果</td>
					</tr>
					<c:forEach items="${flowMeetings}" var="flowMeeting">
						<tr>
							<td>${flowMeeting.checker}</td>
							<td><fmt:formatDate value="${flowMeeting.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							<td>${flowMeeting.checkIdea}</td>
							<td>${flowMeeting.checkStatus}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>

		</div>
	<script>
        $("#finishTask").form({
            url : "finishMeetingTask",
            success : function(data){
                if(data) {
                    $.messager.show({
                        title : "提示",
                        msg : "会议审批成功!"
                    });
                    $("#editWindow_meeting").window("close",true);
                }
            }
        });
	</script>
	</body>
</html>

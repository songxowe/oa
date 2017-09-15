<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>请假审批</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
		<div style="margin: auto;width: 500px;">
			<form action="" method="post" id="finishTask">
				<input type="hidden" name="taskId" value="${taskId}">
				<input type="hidden" name="checker" value="${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName}">
			<table style="width: 500px;" >
				<tr>
					<td align="right">请假人</td>
					<td>${leaveBill.proposer}</td>
				</tr>
				<tr>
					<td align="right">请假类型</td>
					<td>
						${leaveBill.leaveType}
					</td>
				</tr>
				<tr>
					<td align="right">开始时间</td>
					<td><fmt:formatDate value="${leaveBill.startDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td align="right">结束时间</td>
					<td><fmt:formatDate value="${leaveBill.endDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td align="right">请假原因</td>
					<td><textarea name="leaveReason" readonly rows="5" cols="30">${leaveBill.leaveReason}</textarea></td>
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
						<button type="reset">重置</button>
					</td>
				</tr>
			</table>
			</form>
			<c:if test="${!empty flowLeaves}">
				<table>
					<tr>
						<td>审批人</td>
						<td>审批时间</td>
						<td>审批意见</td>
						<td>审批结果</td>
					</tr>
					<c:forEach items="${flowLeaves}" var="flowLeave">
						<tr>
							<td>${flowLeave.checker}</td>
							<td><fmt:formatDate value="${flowLeave.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							<td>${flowLeave.checkIdea}</td>
							<td>${flowLeave.checkStatus}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>

		</div>
	<script>
        $("#finishTask").form({
            url : "finishLeaveTask",
            success : function(data){
                if(data) {
                    $.messager.show({
                        title : "提示",
                        msg : "请假审批成功!"
                    });
                    $("#editLeave2").window("close",true);
                }
            }
        });
	</script>
	</body>
</html>

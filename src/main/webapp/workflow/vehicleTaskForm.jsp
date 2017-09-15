<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>车辆审批</title>
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
					<td>${vehicleUsage.proposer}</td>
				</tr>
				<tr>
					<td align="right">部门</td>
					<td>${vehicleUsage.hrDept.deptName}</td>
				</tr>
				<tr>
					<td align="right">车牌</td>
					<td>
						${vehicleUsage.pubVehicle.VNum}
					</td>
				</tr>
				<tr>
					<td align="right">开始时间</td>
					<td><fmt:formatDate value="${vehicleUsage.vuStart}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td align="right">结束时间</td>
					<td><fmt:formatDate value="${vehicleUsage.vuEnd}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td align="right">申请原因</td>
					<td><textarea readonly rows="4" cols="30">${vehicleUsage.vuReason}</textarea></td>
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
			<c:if test="${!empty flowVehicles}">
				<table>
					<tr>
						<td>审批人</td>
						<td>审批时间</td>
						<td>审批意见</td>
						<td>审批结果</td>
					</tr>
					<c:forEach items="${flowVehicles}" var="flowVehicle">
						<tr>
							<td>${flowVehicle.checker}</td>
							<td><fmt:formatDate value="${flowVehicle.checkDate}" pattern="yyyy-MM-dd"/> </td>
							<td>${flowVehicle.checkIdea}</td>
							<td>${flowVehicle.checkStatus}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>

		</div>
	<script>
        $("#finishTask").form({
            url : "finishVehicleTask",
            success : function(data){
                if(data) {
                    $.messager.show({
                        title : "提示",
                        msg : "车辆申请审批成功!"
                    });
                    $("#editWindow").window("close",true);
                }
            }
        });
	</script>
	</body>
</html>

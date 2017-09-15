<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>车辆申请详情</title>

	</head>

	<body>
		<div style="margin: auto;width: 500px;">
			<div style="margin-top: 50px;">
				<table class="table" style="width: 500px;" >
					<tbody>
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
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>

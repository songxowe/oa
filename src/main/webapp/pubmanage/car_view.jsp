<%--
	author:SONG
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>车辆详情</title>
		<%@ include file="../commons/meta.jsp"%>
	</head>

	<body>
		<style>
.input {
	width: 200px;
	height: 20px;
	border: 1px solid #95B8E7;
}

.btn {
	width: 100px;
	height: 20px;
	border: 1px solid #95B8E7;
}
</style>

			<table width="500" height="198" id="empTable"
				style="margin: 10px auto;">
				<tr>
					<td height="35">
						&nbsp;
					</td>
					<td>
						<div align="right">
							车牌：
						</div>
					</td>
					<td>
						${pubVehicle.VNum }
					</td>
					<td>
						&nbsp;
					</td>
				</tr>				
				<tr>
					<td height="35">
						&nbsp;
					</td>
					<td>
						<div align="right">
							车辆类型：
						</div>
					</td>
					<td>
						${pubVehicle.VType }
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
								
				<tr>
					<td height="35">
						&nbsp;
					</td>
					<td>
						<div align="right">
							司机：
						</div>
					</td>
					<td>
					  ${pubVehicle.VDriver}
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35">
						&nbsp;
					</td>
					<td>
						<div align="right">
							价格：
						</div>
					</td>
					<td>
						￥${pubVehicle.VPrice}
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35">
						&nbsp;
					</td>
					<td>
						<div align="right">
							购买时间：
						</div>
					</td>
					<td>
						<spring:eval expression="pubVehicle.buyDate"/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
				<td height="35">
					&nbsp;
				</td>
				<td>
					<div align="right">
						状态：
					</div>
				</td>
				<td>
					<c:if test="${pubVehicle.useingFalg==1}">
						使用中
					</c:if>
					<c:if test="${pubVehicle.useingFalg==0}">
						空闲
					</c:if>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
				<tr>
					<td height="35">
						&nbsp;
					</td>
					<td>
						<div align="right">
							备注：
						</div>
					</td>
					<td>
						<input name="VRemark" class="easyui-textbox" data-options="multiline:true" readonly  value="${pubVehicle.VRemark}" style="height: 60px;">
					</td>
					<td>
						&nbsp;
					</td>
				</tr>

			</table>
	</body>
</html>

<%--
	author:SONG
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>车辆管理</title>
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
		<form action="" method="post" id="carForm" name="carForm">
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
						<input class="easyui-textbox" data-options="required:true" name="VNum" value="${pubVehicle.VNum }"/>
						<input type="hidden" name="VId" value="${pubVehicle.VId }" />
						<input type="hidden" name="VStatus" value="正常" />
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
						<input name="VType" type="text" class="easyui-textbox" value="${pubVehicle.VType }" />
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
					  <input name="VDriver" class="easyui-textbox" value="${pubVehicle.VDriver}">
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
						<input name="VPrice" class="easyui-numberbox" value="${pubVehicle.VPrice}">
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
						<c:if test="${empty pubVehicle }">
							<input name="buyDate" class="easyui-datebox">
						</c:if>
						<c:if test="${!empty pubVehicle }">
							<input type= "text" class= "easyui-datebox" name="buyDate" value="<spring:eval expression="pubVehicle.buyDate"/>" />
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
						状态：
					</div>
				</td>
				<td>
					<select name="useingFalg" class="easyui-combobox" style="width:100px;">
						<option value="1" ${pubVehicle.useingFalg==1?"selected":""}>使用中</option>
						<option value="0" ${pubVehicle.useingFalg==0?"selected":""}>空闲</option>
					</select>
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
						<input name="VRemark" class="easyui-textbox" data-options="multiline:true"  value="${pubVehicle.VRemark}" style="height: 60px;">
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						<div align="center">
							<input type="submit" value="保存" />
							<input type="reset" value="重置" />
						</div>
					</td>
					<td height="20">
						&nbsp;
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
	$("#carForm").form( {
		url : "carController_save",
		success : function(data) {
			if (data) {
				$.messager.show( {
					title : "提示",
					msg : "车辆保存成功!"
				});
				$("#editCar").window("close",true);
			}
		}
	});
</script>
	</body>
</html>

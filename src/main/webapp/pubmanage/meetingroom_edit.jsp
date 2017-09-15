<%--
	author:SONG
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>会议室管理</title>
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
		<form action="" method="post" id="roomForm" name="roomForm">
			<table width="500" height="198" id="empTable"
				style="margin: 10px auto;">
				<tr>
					<td height="35">
						&nbsp;
					</td>
					<td>
						<div align="right">
							会议室名：
						</div>
					</td>
					<td>
						<input class="easyui-textbox" data-options="required:true" name="mrName" value="${pubMeetingRoom.mrName }"/>
						<input type="hidden" name="mrId" value="${pubMeetingRoom.mrId }" />
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
							容纳人数：
						</div>
					</td>
					<td>
						<input name="mrCapacity" type="text" class="easyui-numberbox" value="${pubMeetingRoom.mrCapacity }" />
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
							地址：
						</div>
					</td>
					<td>
					  <input name="mrPlace" class="easyui-textbox" value="${pubMeetingRoom.mrPlace}">
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
						<option value="1" ${pubMeetingRoom.useingFalg==1?"selected":""}>使用中</option>
						<option value="0" ${pubMeetingRoom.useingFalg==0?"selected":""}>空闲</option>
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
							设备情况：
						</div>
					</td>
					<td>
						<input name="mrDevice" class="easyui-textbox" data-options="multiline:true"  value="${pubMeetingRoom.mrDevice}" style="height: 60px;">
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
							描述：
						</div>
					</td>
					<td>
						<input name="mrDesc" class="easyui-textbox" data-options="multiline:true"  value="${pubMeetingRoom.mrDesc}" style="height: 60px;">
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
	$("#roomForm").form( {
		url : "roomController_save",
		success : function(data) {
			if (data) {
				$.messager.show( {
					title : "提示",
					msg : "会议室保存成功!"
				});
				$("#editRoom").window("close",true);
			}
		}
	});
</script>
	</body>
</html>

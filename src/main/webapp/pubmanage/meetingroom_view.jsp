<%--
	author:SONG
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>会议室详情</title>
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
			<table width="500" height="198" style="margin: 10px auto;">
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
						${pubMeetingRoom.mrName }
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
						${pubMeetingRoom.mrCapacity }
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
					  ${pubMeetingRoom.mrPlace}
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
					<c:if test="${pubMeetingRoom.useingFalg==1}">
						使用中
					</c:if>
					<c:if test="${pubMeetingRoom.useingFalg==0}">
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
							设备情况：
						</div>
					</td>
					<td>
						${pubMeetingRoom.mrDevice}
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
						${pubMeetingRoom.mrDesc}
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
	</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>流程图</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
	<div style="width:80%;height: 80%">
	<img style="position: absolute;top: 25px;left: 0px;" src="${pageContext.request.contextPath}/flowImg?pdid=meetingProcess:1:22504" >
	<div style="position: absolute;border:2px solid red;top:${activity.y+25 }px;left: ${activity.x }px;width: ${activity.width }px;height:${activity.height }px;   "></div>
	</div>
	</body>

</html>

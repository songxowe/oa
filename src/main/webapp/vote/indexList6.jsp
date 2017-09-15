<%@ page language="java" import="com.softfactory.pojo.User,com.softfactory.core.util.Constants" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%
 User user= (User)session.getAttribute(Constants.USER_IN_SESSION);
if(user==null){
	response.sendRedirect("${pageContext.request.contextPath }/login.jsp");
}
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css">
</head>
<body>
	<input type="hidden" id="voteId6" value="${param.voteId}">
	<div id="div6" style="float:left;width: 600px; height: 400px;">
		<div id="main6" style="width: 600px; height: 400px;"></div>
	</div>
	<div id="radio"
		style="float:left;margin : 100px 0 0 20px; width: 20%; height: 250px;">
		<form  method="post" id="myform6">
			
		</form>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/vote/js/echarts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/vote/js/indexecharts6.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/vote/js/macarons.js"></script>
</body>
</html>
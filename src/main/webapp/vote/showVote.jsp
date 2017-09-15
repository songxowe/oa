<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/jquery-3.2.0.js"></script>
</head>
<body>
		<div>
		<table id="tab1" cellpadding="0" cellspacing="0" style="float: left;width:50%;">
				<tr>
					<th>投票标题</th>
					<td><input type="text" name="subject" value="${pubVote.subject }" readonly></td>
				</tr>
				<tr>
					<th>投票描述</th>
					<td><input type="text" name="descn" value="${pubVote.descn }" readonly></td>
				</tr>
				<tr>
					<th>创建时间</th>
					<td><input class="text" name="createTime" value='<fmt:formatDate value="${pubVote.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly></td>
				</tr>
				<tr>
					<th>终止时间</th>
					<td><input class="text" name="endTime" value='<fmt:formatDate value="${pubVote.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly></td>
				</tr>
				<tr>
					<th>发布范围(部门)</th>
					<td><input type="text" name="toDept" value="${pubVote.toDept }" readonly></td>
				</tr>
				<tr>
					<th>发布范围(职位)</th>
					<td><input type="text" name="toRank" value="${pubVote.toRank }" readonly></td>
				</tr>
				<tr>
					<th>发布范围(个人)</th>
					<td><input type="text" name="toId" value="${pubVote.toId }" readonly></td>
				</tr>
			</table>
			<div style="float:left;border:1px solid #99ccff;">
			<table id="tab2">
				<tr align="center">
					<th>序号</th>
					<th>投票项</th>
					<th>总票数</th>
				</tr>
				<c:forEach items="${pubVote.subs }" var="sub" varStatus="status">
					<tr>
						<th>${status.index+1 }</th>
						<th><input type="text" value="${sub.title }" readonly></th>
						<th>${sub.voteCount }</th>
					</tr>
				</c:forEach>
			</table>
			</div>
	</div>
</body>
</html>
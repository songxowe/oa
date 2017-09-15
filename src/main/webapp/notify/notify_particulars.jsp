<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>公告详情</title>
    <script src="${pageContext.request.contextPath }/resources/js/jquery-2.2.2.js"></script>
    <link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
</head>

<body>
<div style="width:500px;margin:auto;">
    <center>
        <h2>${notify.subject }</h2>
        <p>
            发布人：<span style="color:red">${user.userTrueName}</span>&nbsp;&nbsp;&nbsp;
            发布时间：<fmt:formatDate value="${notify.createTime }" pattern="yyyy-MM-dd"/>
        </p>
    </center>
    <p><textarea rows="4" cols="70" readonly>${notify.content }</textarea></p>
    <br><br><br>
    <form action="download.do?attachment=${notify.attachment}" method="post" name="from1" id="uploadForm"
          enctype="multipart/form-data">
        <table>
            <tr>
                <td>附件：${notify.attachment}</td>
                <td>
                    <input type="hidden" id="attachment" name="attachment" value="${notify.attachment}">
                    <input type="submit" value="下载">
                </td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>

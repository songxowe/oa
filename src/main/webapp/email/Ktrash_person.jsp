<%@ page language="java" pageEncoding="UTF-8" %>


<!DOCTYPE HTML >
<html>
<head>
    <meta charset="utf-8">
    <title>邮件管理-废件箱个人信息表</title>
    <script src="${pageContext.request.contextPath }/resources/js/jquery-2.2.2.js"></script>
</head>

<body>
<form action="" method="post" name="from1" id="uploadForm"
      enctype="multipart/form-data">

    <table border="1">
        <tr>
            <td colspan="2">发件人：<input type="text" name="userTrueName" readonly="readonly" value="${priMailTo.priMail.fromId }">
            </td>
        </tr>
        <tr>
            <td colspan="2">收件人：<input type="text" readonly="readonly" value="${priMailTo.priMail.toId }"></td>
        </tr>
        <tr>
            <td>重要程度：<input type="text" readonly="readonly" name="important" value="${ priMail.important}"></td>
        </tr>
        <tr>
            <td colspan="4">主题：<input type="text" name="subject"
                                      value="${priMailTo.priMail.subject}" readonly="readonly"></td>
        </tr>
        <tr>
            <td colspan="4">附件：<input type="text" id="attachment" name="attachment"
                                      value="${priMailTo.priMail.attachment}" readonly="readonly">

            </td>
        </tr>
        <tr>
            <td colspan="1" rowspan="10"><textarea rows="40" cols="73" name="content"
                                                   readonly="readonly">${priMailTo.priMail.content }</textarea></td>
        </tr>
    </table>
</form>
</body>
</html>

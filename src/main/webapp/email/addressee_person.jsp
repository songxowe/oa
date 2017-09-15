<%@ page language="java" pageEncoding="UTF-8" %>


<!DOCTYPE HTML >
<html>
<head>
    <meta charset="utf-8">
    <title>邮件管理-收件箱个人信息表</title>
    <script src="${pageContext.request.contextPath }/resources/js/jquery-2.2.2.js"></script>
</head>

<body>
<form action="download.do?attachment=${priMailTo.priMail.attachment}" method="post" name="from1" id="uploadForm"
      enctype="multipart/form-data">
    <input type="text" name="mailToId" id="mailToId"
           value="${priMailTo.mailToId}">
    <table border="1">
        <tr>
            <td colspan="2">发件人：<input type="text" name="userTrueName" value="${priMailTo.priMail.fromId }"></td>
        </tr>
        <tr>
            <td colspan="2">收件人：<input type="text" value="${priMailTo.priMail.toId }"></td>
        </tr>
        <tr>
            <td>重要程度：<input type="text" name="important" value="${priMailTo.priMail.important}"></td>
        </tr>
        <tr>
            <td colspan="4">主题：<input type="text" name="subject"
                                      value="${priMailTo.priMail.subject}"></td>
        </tr>
        <tr>
            <td colspan="4">附件：<input type="text" id="attachment" name="attachment"
                                      value="${priMailTo.priMail.attachment}"><input type="submit" value="下载">
                <!-- onchange="checkFile()" -->
            </td>
        </tr>
        <tr>
            <td colspan="1" rowspan="10"><textarea rows="40" cols="73"
                                                   name="content">${priMailTo.priMail.content }</textarea></td>
        </tr>
    </table>


</form>

<div></div>
<script type="text/javascript">

    function checkFile() {
        var attachment = document.getElementById('attachment').value;
        $.ajax({
            url: 'download.do?attachment=' + attachment,
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadForm')[0]),
            processData: false,
            contentType: false
        }).done(function (res) {
        }).fail(function (res) {
        });

    }

    $(function () {
        $.ajax({
            type: "post",
            url: "modifyBy?readFlag=1&mailToId=" + $("#mailToId").val(),
            success: function (data) {
                if (data) {

                    $.messager.show({
                        title: "提示",

                        msg: data + "个邮件阅读成功"
                    });
                }
            }

        });
    });
</script>
</body>
</html>

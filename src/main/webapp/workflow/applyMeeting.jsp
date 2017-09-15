<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>会议申请</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
		<div style="margin: auto;width: 500px;">
			<input type="hidden" id="proposer" value="${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName}">
			<table style="width: 500px;" >
				<tr>
					<td align="right">会议室</td>
					<td><select id="meetingRoom"></select></td>
				</tr>
				<tr>
					<td align="right">会议主题</td>
					<td>
						<input type="text" id="MTopic">
					</td>
				</tr>
				<tr>
					<td align="right">开始时间</td>
					<td><input class="easyui-datetimebox" id="MStart"></td>
				</tr>
				<tr>
					<td align="right">结束时间</td>
					<td><input class="easyui-datetimebox" id="MEnd"></td>
				</tr>
				<tr>
					<td align="right">会议描述</td>
					<td>
						<textarea rows="5" cols="30" id="MDesc"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right">会议人员</td>
					<td><textarea id="MAttendee" rows="5" cols="30"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" id="applyMeeting">提交申请</button>&nbsp;&nbsp;
						<button type="reset">重置</button>
					</td>
				</tr>
			</table>
		</div>
	<script>
		$(function () {
            $.ajax({
                url: 'meetingRoomList',
                dateType: 'json',
                type: 'post',
                success: function (data) {

                    $.each(data, function () {
                        $("#meetingRoom").append("<option value='" + this.mrId + "'>" + this.mrName + "</option>")
                    })
                }
            })
            $("#applyMeeting").click(function () {
                $.ajax({
                    url: 'addMeeting',
                    data: {
                        proposer: $("#proposer").val(),
                        meetingRoom: $("#meetingRoom").val(),
                        MTopic: $("#MTopic").val(),
                        MStart: $("#MStart").datetimebox('getValue'),
                        MEnd: $("#MEnd").datetimebox('getValue'),
                        MDesc: $("#MDesc").val(),
                        MAttendee: $("#MAttendee").val()
                    },
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        if (data) {
                            $.messager.show({
                                title: "提示",
                                msg: "会议申请成功!"
                            });
                            $("#editWindow_meeting").window("close", true);
                        }
                    }
                })
            })
        })
	</script>
	</body>
</html>

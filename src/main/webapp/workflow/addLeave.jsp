<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>请假申请</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
		<div style="margin: auto;width: 500px;">
			<input type="hidden" id="proposer" value="${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName}">
			<table style="width: 500px;" >
				<tr>
					<td align="right">部门</td>
					<td><select name="hrDept" id="hrDept"></select></td>
				</tr>
				<tr>
					<td align="right">请假类型</td>
					<td><select id="leaveType">
						<option value="事假">事假</option>
						<option value="病假">病假</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">开始时间</td>
					<td><input class="easyui-datebox" id="startDate"></td>
				</tr>
				<tr>
					<td align="right">结束时间</td>
					<td><input class="easyui-datebox" id="sendDate"></td>
				</tr>
				<tr>
					<td align="right">请假原因</td>
					<td><textarea id="leaveReason" rows="5" cols="30"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" id="applyLeave">提交申请</button>&nbsp;&nbsp;
						<button type="reset">重置</button>
					</td>
				</tr>
			</table>
		</div>
	<script>
		$(function () {

            $.ajax({
                url: 'hrDeptList',
                dateType: 'json',
                type: 'post',
                success: function (data) {

                    $.each(data, function () {
                        $("#hrDept").append("<option value='" + this.deptId + "'>" + this.deptName + "</option>")
                    })
                }
            })
            $("#applyLeave").click(function () {
                $.ajax({
                    url: 'applyLeave',
                    data: {
                        proposer: $("#proposer").val(),
                        deptId: $("#hrDept").val(),
                        leaveType: $("#leaveType").val(),
                        startDate: $("#startDate").datebox('getValue'),
                        endDate: $("#sendDate").datebox('getValue'),
                        leaveReason: $("#leaveReason").val()
                    },
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        if (data) {
                            $.messager.show({
                                title: "提示",
                                msg: "请假申请成功!"
                            });
                            $("#editWindow_leave").window("close", true);
                        }
                    }
                })
            })
        })
	</script>
	</body>
</html>

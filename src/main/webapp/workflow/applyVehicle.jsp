<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>车辆申请</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
		<div style="margin: auto;width: 500px;">
			<input type="hidden" id="proposer" value="${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName}">
			<table style="width: 500px;" >
				<tr>
					<td align="right">部门</td>
					<td><select id="hrDept"></select></td>
				</tr>
				<tr>
					<td align="right">车辆</td>
					<td><select id="pubVehicle"></select></td>
				</tr>
				<tr>
					<td align="right">开始时间</td>
					<td><input class="easyui-datebox" id="vuStart"></td>
				</tr>
				<tr>
					<td align="right">结束时间</td>
					<td><input class="easyui-datebox" id="vuEnd"></td>
				</tr>

				<tr>
					<td align="right">申请原因</td>
					<td><textarea id="vuReason" rows="5" cols="30"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" id="applyVehicle">提交申请</button>&nbsp;&nbsp;
						<button type="reset">重置</button>
					</td>
				</tr>
			</table>
		</div>
	<script>
		$(function () {

		    /**
			 * 加载所有可用车辆
			 */
            $.ajax({
                url: 'carList',
                dateType: 'json',
                type: 'post',
                success: function (data) {

                    $.each(data, function () {
                        $("#pubVehicle").append("<option value='" + this.VId + "'>" + this.VNum + "</option>")
                    })
                }
            })

            /**
			 * 加载部门列表
             */
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
            $("#applyVehicle").click(function () {
                $.ajax({
                    url: 'finishVehicleApply',
                    data: {
                        proposer: $("#proposer").val(),
                        deptId: $("#hrDept").val(),
                        VId: $("#pubVehicle").val(),
                        vuStart: $("#vuStart").datebox('getValue'),
                        vuEnd: $("#vuEnd").datebox('getValue'),
                        vuReason: $("#vuReason").val()
                    },
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        if (data) {
                            $.messager.show({
                                title: "提示",
                                msg: "车辆申请成功!"
                            });
                            $("#editWindow_vehicle").window("close", true);
                        }
                    }
                })
            })
        })
	</script>
	</body>
</html>

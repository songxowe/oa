<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<!-- 登录用户请假审批列表 -->
<!DOCTYPE HTML>
<html>
	<head>
		<title>会议审批</title>
		<%@ include file="../commons/meta.jsp"%>
		<script type="text/javascript"></script>
	</head>

	<body>
		<div style="margin: 10px 30px;">

			<input type="hidden" id="proposer" value="${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName}">
			<div id="editWindow">

			</div>

			<div style="margin-top: 20px;">
				<table id="meetingTaskDataGrid">

				</table>

			</div>
		</div>
		<script type="text/javascript">
	$(function() {

		$("#meetingTaskDataGrid").datagrid( {
			url : "meetingTaskList",
			title : "会议审批列表",
			fitColumns : true,
			striped : true,
			rownumbers : true,
			columns : [ [  {
                field : "no",
                title : "序号",
                width : 100,
                checkbox : true
            },{
				field : "id",
				title : "任务序号",
				width : 100

			}, {
				field : "name",
				title : "任务名称",
				width : 100

			}, {
				field : "createTime",
				title : "申请时间",
				width : 100

			}, {
                field : "op1",
                title : "操作",
                width : 100,
                align : 'center',
                formatter : function(value, rowData, rowIndex) {
                    var id = rowData["id"];
                    return "<a href='#' onclick=finishTask(" + id + ")>办理审批</a>&nbsp;&nbsp;" +
						"<a href='#' onclick=getImg(" + id + ")>查看流程图</a>"

                },
            } ] ],
			toolbar : "#searchLeaveForm",
			pagination : true,
			pageSize : 5,
			pageList : [ 2, 5, 10, 15, 20 ]
		});
	});

	function getImg(taskId) {
        $("#editWindow").window({
			title : '流程图',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getMeetingImpl?taskId='+taskId,
            onClose : function(){
                $("#meetingTaskDataGrid").datagrid('load');
            }
        });
	}
    function finishTask(taskId) {
        $("#editWindow").window({
            title : '办理审批',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'meetingTaskUI?taskId='+taskId,
            onClose : function(){
                $("#meetingTaskDataGrid").datagrid('load');
            }
        });
	}

</script>
	</body>
</html>

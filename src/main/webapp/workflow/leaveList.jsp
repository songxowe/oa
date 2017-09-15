<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<!-- 登录用户请假审批列表 -->
<!DOCTYPE HTML>
<html>
	<head>
		<title>请假审批</title>
		<%@ include file="../commons/meta.jsp"%>
		<script type="text/javascript"></script>
	</head>

	<body>
		<div style="margin: 10px 30px;">
			<div id="editLeave2">

			</div>


			<!-- 用户列表显示 -->
			<div style="margin-top: 20px;">
				<table id="leaveDataGrid">

				</table>

			</div>
		</div>
		<script type="text/javascript">
	$(function() {


		$("#leaveDataGrid").datagrid( {
			url : "leaveTaskList",
			title : "请假审批列表",
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

                }
            } ] ],
			pagination : true,
			pageSize : 5,
			pageList : [ 2, 5, 10, 15, 20 ]
		});
	});

	function getImg(taskId) {
        $("#editLeave2").window({
			title : '流程图',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getImg?taskId='+taskId,
            onClose : function(){
                $("#leaveDataGrid").datagrid('load');
            }
        });
	}
    function finishTask(taskId) {
        $("#editLeave2").window({
            title : '办理审批',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'leaveTaskUI?taskId='+taskId,
            onClose : function(){
                $("#leaveDataGrid").datagrid('load');
            }
        });
	}

</script>
	</body>
</html>

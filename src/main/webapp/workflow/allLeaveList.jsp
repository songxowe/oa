<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>请假列表</title>
		<%@ include file="../commons/meta.jsp"%>
		<script type="text/javascript"></script>
	</head>

	<body>
		<div style="margin: 10px 30px;">

			<div id="editLeave">

			</div>

			<!-- 用户列表的工具栏设置 -->
			<div id="searchLeaveForm" style="padding: 10px;">
				<div style="padding: 0 0 0 6px;">
					请假人<input class="easyui-textbox" id="proposer">
					请假时间<input class="easyui-datebox" id="beginDate">--
					<input class="easyui-datebox" id="endDate">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"
						onclick="leave_obj.search();">查询</a>
				</div>
			</div>

			<!-- 用户列表显示 -->
			<div style="margin-top: 20px;">
				<table id="allleaveDataGrid">

				</table>

			</div>
		</div>
		<script type="text/javascript">
	$(function() {
        leave_obj = {
			search : function() {//查询
				$("#allleaveDataGrid").datagrid(
						"load",
						{
							proposer : $("#proposer").val(),
							beginDate : $("#beginDate").datebox('getValue'),
							endDate : $("#endDate").datebox('getValue')
						});
			},

			showEdit : function(state){
				var url = "addLeave";
				var info = "";

				if(state == 'add') {//新增
					info = "新增用户信息";
				}
				$("#editLeave").window({
					title : info,
					width : 550,
					height : 250,
					modal : true,
					minimizable : false,
					href : url,
					onClose : function(){
					leave_obj.search();
					}
				});
			}		
		}

		$("#allleaveDataGrid").datagrid( {
			url : "leaveBills",
			title : "请假列表",
			fitColumns : true,
			striped : true,
			rownumbers : true,
			columns : [ [  {
                field : "no",
                title : "序号",
                width : 50,
                checkbox : true,
                sortable : true
            },{
				field : "leaveId",
				title : "序号",
				width : 50,
				sortable : true
			}, {
				field : "proposer",
				title : "申请人",
				width : 100,
				sortable : true
			}, {
				field : "applyDate",
				title : "申请时间",
				width : 100,
				sortable : true
			},{
                field : "hrDept",
                title : "部门",
                width : 100
            },{
                field : "leaveType",
                title : "请假类型",
                width : 100
            },{
                field : "leaveReason",
                title : "请假原因",
                width : 100
            },{
                field : "startDate",
                title : "开始时间",
                width : 100
            },{
                field : "endDate",
                title : "结束时间",
                width : 100
            },{
                field : "currentStep",
                title : "审批步骤",
                width : 100
            }, {
                field : "op1",
                title : "操作",
                width : 200,
                align : 'center',
                formatter : function(value, rowData, rowIndex) {
                    var id = rowData["taskId"];
                    var leaveId = rowData["leaveId"];
                    return "<a href='#' onclick=getImg(" + id + ")>查看流程</a>&nbsp;&nbsp;" +
						"<a href='#' onclick=getFlowLeaves(" + leaveId + ")>查看审批记录</a>"
                }
            } ] ],
			toolbar : "#searchLeaveForm",
			pagination : true,
			pageSize : 5,
			pageList : [ 2, 5, 10, 15, 20 ],
			sortName : "leaveId",
			sortOrder : "desc",
		});
	});

	function getImg(taskId) {
        $("#editLeave").window({
			title : '流程图',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getImg?taskId='+taskId,
            onClose : function(){
                $("#allleaveDataGrid").datagrid('load');
            }
        });
	}
	function getFlowLeaves(leaveId) {
        $("#editLeave").window({
            title : '审批记录',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getFlowLeave?leaveId='+leaveId,
            onClose : function(){
                $("#allleaveDataGrid").datagrid('load');
            }
        });
	}

</script>
	</body>
</html>

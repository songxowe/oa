<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>所有会议申请列表</title>
		<%@ include file="../commons/meta.jsp"%>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
		
	</head>

	<body>
		<div style="margin: 10px 30px;">
			<div id="editWindow_meeting">

			</div>

			<!-- 用户列表的工具栏设置 -->
			<div id="editTool_meeting" style="padding: 10px;">
				<div style="padding: 0 0 0 6px;">
					申请人<input class="textbox" id="proposer">
					申请时间<input class="easyui-datebox" id="beginDate">--
					<input class="easyui-datebox" id="endDate">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"
						onclick="meeting_obj.search();">查询</a>
				</div>
			</div>

			<!-- 用户列表显示 -->
			<div style="margin-top: 20px;">
				<table id="meetingDataGrid">

				</table>

			</div>
		</div>
		<script type="text/javascript">
	$(function() {
        meeting_obj = {
            search: function () {//查询
                $("#meetingDataGrid").datagrid(
                    "load",
                    {
                        proposer: $("#proposer").val(),
                        beginDate: $("#beginDate").datebox('getValue'),
                        endDate: $("#endDate").datebox('getValue')
                    });
            }
        }

		$("#meetingDataGrid").datagrid( {
			url : "meetingList",
			title : "会议申请列表",
			fitColumns : true,
			striped : true,
			rownumbers : true,
			columns : [ [  {
                field : "no",
                title : "序号",
                width : 50,
                checkbox : true
            },{
				field : "id",
				title : "序号",
				width : 50,
				sortable : true,
                align : 'center'
			}, {
                field : "proposer",
                title : "申请人",
                width : 80,
                sortable : true,
                align : 'center'
            }, {
				field : "applyDate",
				title : "申请时间",
				width : 200,
				sortable : true,
                align : 'center'
			},{
                field : "meetingRoom",
                title : "会议室",
                width : 100,
                align : 'center'
            },{
                field : "MTopic",
                title : "会议主题",
                sortable : true,
                width : 100,
                align : 'center'
            },{
                field : "currentStep",
                title : "审批步骤",
                width : 100,
                align : 'center'
            }, {
                field : "op1",
                title : "操作",
                width : 250,
                align : 'center',
                formatter : function(value, rowData, rowIndex) {
                    var taskId = rowData["taskId"];
                    var id = rowData["id"];
                    return "<a href='#' onclick=getImg(" + taskId + ")>查看流程</a>&nbsp;&nbsp;" +
						"<a href='#' onclick=getFlowMeetings(" + id + ")>查看审批记录</a>&nbsp;&nbsp;" +
						"<a href='#' onclick=getDetils(" + id + ")>查看详情</a>"
                }
            } ] ],
			toolbar : "#editTool_meeting",
			pagination : true,
			pageSize : 5,
			pageList : [ 2, 5, 10, 15, 20 ],
			sortName : "applyDate",
			sortOrder : "desc",
		});
	});

	function getImg(taskId) {
        $("#editWindow_meeting").window({
			title : '流程图',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getMeetingImpl?taskId='+taskId,
            onClose : function(){
                $("#meetingDataGrid").datagrid('load');
            }
        });
	}
	function getFlowMeetings(id) {
        $("#editWindow_meeting").window({
            title : '审批记录',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getFlowMeeting?id='+id,
            onClose : function(){
                $("#meetingDataGrid").datagrid('load');
            }
        });
	}

	function getDetils(id) {
        $("#editWindow_meeting").window({
            title : '会议申请详情',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'meetingDetils?id='+id,
            onClose : function(){
                $("#meetingDataGrid").datagrid('load');
            }
        });
	}

</script>
	</body>
</html>

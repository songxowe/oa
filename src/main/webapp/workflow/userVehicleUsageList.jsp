<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>车辆申请</title>
		<%@ include file="../commons/meta.jsp"%>
		<link rel="stylesheet" type="text/css"
			  href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />

	</head>

	<body onload="vehicle_obj.search()">
		<div style="margin: 10px 30px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
				onclick="vehicle_obj.showEdit('add')">车辆申请</a>&nbsp;&nbsp;
			<input type="hidden" id="proposer" value="${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName}">
			<div id="editWindow_vehicle">

			</div>

			<!-- 用户列表的工具栏设置 -->
			<div id="editTool_vehicle" style="padding: 10px;">
				<div style="padding: 0 0 0 6px;">
					申请时间<input class="easyui-datebox" id="beginDate_vehicle">--
					<input class="easyui-datebox" id="endDate_vehicle">
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"
						onclick="vehicle_obj.search();">查询</a>
				</div>
			</div>

			<!-- 用户列表显示 -->
			<div style="margin-top: 20px;">
				<table id="vehicleDataGrid">

				</table>

			</div>
		</div>
		<script type="text/javascript">
	$(function() {
        vehicle_obj = {
			search : function() {//查询
				$("#vehicleDataGrid").datagrid(
						"load",
						{
							proposer : $("#proposer").val(),
							beginDate : $("#beginDate_vehicle").datebox('getValue'),
							endDate : $("#endDate_vehicle").datebox('getValue')
						});
			},

			showEdit : function(state){
				var url = "applyVehicle";
				var info = "";

				if(state == 'add') {//新增
					info = "车辆申请信息";
				}

				$.ajax({
					url : 'carList',
					data : {"action":"action"},
					dataType : 'json',
					type : 'post',
					success : function(data) {
					    if(!data.result) {
					        $.messager.alert('提示','没有可用的车辆')
						}else{
                            $("#editWindow_vehicle").window({
                                title : info,
                                width : 550,
                                height : 250,
                                modal : true,
                                minimizable : false,
                                href : url,
                                onClose : function(){
                                    vehicle_obj.search();
                                }
                            });
						}
					}
				})
			}		
		}

		$("#vehicleDataGrid").datagrid( {
			url : "vehicleUsageList",
			title : "车辆申请列表",
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
				field : "applyDate",
				title : "申请时间",
				width : 200,
				sortable : true,
                align : 'center'
			},{
                field : "hrDept",
                title : "部门",
                width : 100,
                align : 'center'
            },{
                field : "pubVehicle",
                title : "车牌号",
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
			toolbar : "#editTool_vehicle",
			pagination : true,
			pageSize : 5,
			pageList : [ 2, 5, 10, 15, 20 ],
			sortName : "applyDate",
			sortOrder : "desc",
		});
	});

	function getImg(taskId) {
        $("#editWindow_vehicle").window({
			title : '流程图',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getVehicleImpl?taskId='+taskId,
            onClose : function(){
                vehicle_obj.search();
            }
        });
	}
	function getFlowMeetings(id) {
        $("#editWindow_vehicle").window({
            title : '审批记录',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'getFlowVehicle?id='+id,
            onClose : function(){
                vehicle_obj.search();
            }
        });
	}

	function getDetils(id) {
        $("#editWindow_vehicle").window({
            title : '车辆申请详情',
            width : 850,
            height : 550,
            modal : true,
            minimizable : false,
            href : 'vehicleUsageDetils?id='+id,
            onClose : function(){
                vehicle_obj.search();
            }
        });
	}

</script>
	</body>
</html>

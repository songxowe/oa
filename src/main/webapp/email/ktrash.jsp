<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">

<title>邮件管理-废件箱</title>

		<%@ include file="../commons/meta.jsp"%>

</head>

<body><div style="margin: 10px 30px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
				onclick="mailbox_obj_3.remove('remove')">恢复数据</a>&nbsp;&nbsp;
			
			<!-- 用户列表的工具栏设置 -->
			<div id="KtrashUserForm" style="padding: 10px;">
				<div style="padding: 0 0 0 6px">
				<label>收件人:</label> <input id="toIdd" name="toId"  class="easyui-textbox">
				<label>邮件主题:</label> <input id="subjectt" name="subject" class="easyui-textbox">
				<label>重要程度:<select name="important" id="importantt">
						<option value="一般">一般</option>
						<option value="重要">重要</option>
						<option value="非常重要">非常重要</option>
				</select></label> <br> <label>发送日期:</label> <input id="beginDate"
					class="easyui-datebox"> <input id="endDate"
					class="easyui-datebox"> <a href="#"
					class="easyui-linkbutton" iconCls="icon-search"
					onclick="mailbox_obj_3.search()">查询</a>
			</div>
						<div id="KrashMailBox"></div>

			</div>

			<!-- 用户列表显示 -->
			<div style="margin-top: 20px;">
				
				<div style="margin-top: 10px;">
			<table id="KtrashDataGrid"></table>
		</div>

			</div>
				<input type="hidden" id="id" value="${sessionScope['NEWER_USER_LOGIN_INFO'].userTrueName}">

			</div>
		<script type="text/javascript">
	$(function() {
		mailbox_obj_3 = {
			search : function() {//查询
				$("#KtrashDataGrid").datagrid(
						"load",
						{toId : $.trim($("#toIdd").val()),
						important : $.trim($("#importantt").val()),
						subject : $.trim($("#subjectt").val()),
						beginDate : $("#beginDate").datebox('getValue'),
						endDate : $("#endDate").datebox('getValue') 
						});
			},
			remove : function(state){
				var rows = $("#KtrashDataGrid").datagrid("getSelections");
				if(rows.length > 0) {
					$.messager.confirm("消息","确认真的要恢复所选的数据吗",function(flag){
						if(flag){
							var url = "";
							var ids = [];
							for(var i=0;i<rows.length;i++){
								if(rows[i].priMail.toId == $("#id").val()){
									ids.push(rows[i].mailToId);
									url="modifyBy?deleteFlag=0";
								}else if(rows[i].priMail.fromId == $("#id").val()){
									ids.push(rows[i].priMail.mailId);
									url = "modifyByTwo?sendFlag=1";
								}
								
							}
							
							$.ajax({
								type : "post",
								url : url,
								data : {
									ids : ids.join(","),
								},
								beforeSend : function(){
									$("#KtrashDataGrid").datagrid("loading");
								},
								success : function(data){
									if(data) {
										$("#KtrashDataGrid").datagrid("loaded");

										$("#KtrashDataGrid").datagrid("unselectAll");
										$.messager.show({
											title : "提示",
											msg : data+"个数据被恢复成功"
										});
                                        $("#KtrashDataGrid").datagrid("load");
									}
								}
							});
						}
					});
				}else {
					$.messager.alert("警告", "请选中要恢复的数据","warning");
				}
			}	
		}

		$("#KtrashDataGrid").datagrid( {
			url : 'list_email?deleteFlag=1&toId='+$("#id").val()+"&sendFlag=2&fromId="+$("#id").val(),
			title : '邮箱管理系统',
			fitColumns : true,
			striped : true,
			rownumbers : true,
			columns : [ [ {
				field : 'priMail',
				hidden: true
				
			},{
				field : 'io',
				title : '序号',
				width : 50,
				checkbox : true,
				sortable : true
			},{
				field : 'mailId',
				title : '邮件编号',
				width : 200,
				align : 'center',
				sortable : true,
                hidden:true,
				formatter : function(value, rowData, rowIndex) {
					return rowData.priMail.mailId;
				}
			}, {
				field : 'toId',
				title : '接收人',
				width : 100,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return rowData.priMail.toId;
				}
			}, {
				field : 'subject',
				title : '邮件主题',
				width : 100,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return rowData.priMail.subject;
				}
			}, {
				field : 'sendTime',
				title : '日期',
				width : 100,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return rowData.priMail.sendTime;
				}
			}, {
				field : 'attachment',
				title : '附件名称',
				width : 100,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return rowData.priMail.attachment;
				}
			},{
				field : "op1",
				title : "查看详情",
				width : 80,
				align : 'center',
				formatter : function(value, rowData, rowIndex) {
					var mailId = rowData.mailToId;
					return "<a href='#' onclick=getmailbox3(" + "'" + mailId + "'" + ")>查看</a>"
				}
			} ] ],
			toolbar : "#KtrashUserForm",
			pagination : true,
			pageSize : 2,
			pageList : [ 2, 5, 10, 15, 20 ],
			sortName : "toId",
			sortOrder : "asc",
		})
		
	
	});
	
	function getmailbox3(mailId) {
		$("#KrashMailBox").window({
			title : "查看邮件详情",
			width : 350,
			height : 430,
			modal : true,
			minimizable : false,
			href : "findById_email?transmit=2&mailToId="+mailId,
			onClose : function() {
				$("#KtrashDataGrid").datagrid('unselectAll')
			}
		})
		
	}
</script>
</body>
</html>

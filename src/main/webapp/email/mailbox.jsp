<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">

<title>邮件管理-已发送</title>

		<%@ include file="../commons/meta.jsp"%>

</head>

<body><div style="margin: 10px 30px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
				onclick="mailbox_obj_4.remove('remove')">删除</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				onclick="mailbox_obj_4.remove('thoroughly')">永久删除</a>&nbsp;&nbsp;
		

			<!-- 用户列表的工具栏设置 -->
			<div id="searchUserForm" style="padding: 10px;">
				<div style="padding: 0 0 0 6px">
				<label>收件人:</label> <input id="toId" name="toId"  class="easyui-textbox">
				<label>邮件主题:</label> <input id="subject" name="subject" class="easyui-textbox">
				<label>重要程度:<select name="important" id="important">
						<option value="一般">一般</option>
						<option value="重要">重要</option>
						<option value="非常重要">非常重要</option>
				</select></label> <br> <label>发送日期:</label> <input id="beginDate"
					class="easyui-datebox"> <input id="endDate"
					class="easyui-datebox"> <a href="#"
					class="easyui-linkbutton" iconCls="icon-search"
					onclick="mailbox_obj_4.search()">查询</a>
			</div>
						<div id="editmailbox5"></div>

			</div>

			<!-- 用户列表显示 -->
			<div style="margin-top: 20px;">
				
				<div style="margin-top: 10px;">
			<table id="emailDataGrid"></table>
		</div>

			</div>
						<input type="hidden" id="id" value="${sessionScope['NEWER_USER_LOGIN_INFO'].userTrueName}">

		</div>
		<script type="text/javascript">
	$(function() {
		mailbox_obj_4 = {
			search : function() {//查询
				$("#emailDataGrid").datagrid(
						"load",
						{toId : $.trim($("#toId").val()),
						important : $.trim($("#important").val()),
						subject : $.trim($("#subject").val()) ,
						beginDate : $("#beginDate").datebox('getValue'),
						endDate : $("#endDate").datebox('getValue') 
						});
			},
			remove : function(state){
				var rows = $("#emailDataGrid").datagrid("getSelections");
				if(rows.length > 0) {
					$.messager.confirm("消息","确认真的要删除所选的数据吗",function(flag){
						if(flag){
							var ids = [];
							for(var i=0;i<rows.length;i++){
								ids.push(rows[i].priMail.mailId);
							}
							
							if(state == 'thoroughly') {
								url="modifyByTwo?sendFlag=3"
								
							}else if(state =="remove"){
								url="modifyByTwo?sendFlag=2"
							}
							$.ajax({
								type : "post",
								url : url,
								data : {
									ids : ids.join(","),
								},
								beforeSend : function(){
									$("#emailDataGrid").datagrid("loading");
								},
								success : function(data){
									if(data) {
										$("#emailDataGrid").datagrid("loaded");

										$("#emailDataGrid").datagrid("unselectAll");
										$.messager.show({
											title : "提示",
											msg : data+"被删除成功" 
										});
                                        $("#emailDataGrid").datagrid("load");
									}
								}
							});
						}
					});
				}else {
					$.messager.alert("警告", "请选中要删除的数据","warning");
				}
			}	
		}

		$("#emailDataGrid").datagrid( {
			url : 'list_email?sendFlag=1&fromId='+$("#id").val(),
			title : '邮箱管理系统',
			fitColumns : true,
			striped : true,
			rownumbers : true,
			columns : [ [  {
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
			},
			 {
				field : "op1",
				title : "查看详情",
				width : 80,
				align : 'center',
				formatter : function(value, rowData, rowIndex) {
					var mailId = rowData.mailToId;
					return "<a href='#' onclick=getmailbox4(" + "'" + mailId + "'" + ")>查看</a>"
				}
			} ] ],
			toolbar : "#searchUserForm",
			pagination : true,
			pageSize : 2,
			pageList : [ 2, 5, 10, 15, 20 ],
			sortName : "toId",
			sortOrder : "asc",
		})
		
	
		
		
		
		
		
	});
	function getmailbox4(mailId) {
		$("#editmailbox5").window({
			title : "查看邮件详情",
			width : 350,
			height : 430,
			modal : true,
			minimizable : false,
			href : "findById_email?transmit=2&mailToId="+mailId,
			onClose : function() {
				$("#emailDataGrid").datagrid('unselectAll')
			}
		})
		
	}
	
	
</script>
</body>
</html>

<%@ page language="java"  pageEncoding="UTF-8"%>



<!DOCTYPE HTML>
<html>
<head>
<title>公告</title>
<meta charset="utf-8">
</head>
<%@ include file="../commons/meta.jsp" %>
  
  <body>
    <h2>公告栏</h2>
    <div style="margin-top: 20px">
        <table id="notifysManagerDataGrid"></table>
    </div>
    <div id="editManagerTool">
    	
    	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveToy()" id="save" style="display:none">保存</a>
  		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="redoToy()" id="redo" style="display:none">撤销</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addNotify()">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="issue_obj.remove()">删除</a>
         <br>
    	公告标题：&nbsp;&nbsp;  <input type="text" class="easyui-textbox" id="issueSubject">
    	公告发布时间：<input type="date" class="easyui-datebox" id="issueBeginDate">-
    	<input type="date" class="easyui-datebox" id="issueEndDate">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="issue_obj.search()">查询</a>    
    </div>
    <div id="newNotify"></div>
    <script>
       $(function(){

           issue_obj = {
               search : function() {
               
                   $("#notifysManagerDataGrid").datagrid('load',{
                       subject : $.trim($("#issueSubject").val()),
                       beginDate :  $("#issueBeginDate").datebox('getValue'),
                       endDate : $("#issueEndDate").datebox('getValue')
                   })
               },
               
               remove : function(){
               var rows=$("#notifysManagerDataGrid").datagrid('getSelections');
               var subject='';
               var notifyId='';
               $.each(rows,function(){
               		subject=this.subject;
               		notifyId=this.notifyId;
               })
               
				if(rows.length==0){
				$.messager.alert("警告","请选择一行进行删除");
				return;
				}
				$.messager.confirm("确认","您确认要删除"+subject+"?",function(r){
					if(r){
						$.ajax({
               				url : 'delete',
							type : 'POST',
							data : {'notifyId':notifyId},
							dataType : 'json',
							success : function(data){
								$.messager.show({
									title : '系统消息',
									msg : data+'条公告被删除',
									timeout : 3000
								})
								$('#notifysManagerDataGrid').datagrid("reload");
							}
               			})
					
					}
					
				})
				
               		
               }
               
           }

            $("#notifysManagerDataGrid").datagrid({
            url : 'notifys',
            title : '公告列表',
            fitColumns : true,
            striped : true,
            rownumbers : true,
            columns : [[{
                field : 'no',
                title : '序号',
                width : 50,
                checkbox : true
            },{
                field : 'notifyId',
                title : '编号',
                width : 80,
                align : 'center',
                sortable : true
            },{
                field : 'subject',
                title : '标题',
                width : 100,
                align : 'center',
                sortable : true
            },{
                field : 'createTime',
                title : '发布日期',
                width : 100,
                align : 'center',
                sortable : true
            },{
                field : 'notifyStatus',
                title : '公告状态',
                width : 50,
                align : 'center',
                sortable : true
            },{
				field : "op1",
				title : "操作",
				width : 100,
				align : 'center',
				formatter : function(value, rowData, rowIndex) {
					var id = rowData["notifyId"];
					return "<a href='#' onclick=getNotifyParticulars(" + id + ")>查看详情</a>"
					}
				} ]],
			singleSelect : true,
            toolbar : "#editManagerTool",
            pagination : true,
            pageSize : 5,
            pageList : [2, 5, 10, 20, 50 ],
            sortName : 'createTime',
            sortOrder : 'desc'
        });
        });
        
        function addNotify() {
	// 显示窗口 

	$("#newNotify").window({
		title : '新增公告',
		width : 700,
		height : 500,
		modal : true,
		minimizable : false,
		href : 'toadd',
		onClose : function() {
			$('#notifysManagerDataGrid').datagrid("reload");
		}
	})
}
      function getNotifyParticulars(id) {
	// 显示窗口 
	
	$("#newNotify").window({
		title : '公告详情',
		width : 700,
		height : 500,
		modal : true,
		minimizable : false,
		href : 'particulars?notifyId='+id,
		onClose : function() {
			$('#notifysManagerDataGrid').datagrid("reload");
		}
	})
}
    </script>
  </body>
</html>



<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  <title>公告</title>
   <meta charset="utf-8">
  </head>
  <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/resources/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css" />
    <script src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
  
  <body>
    
    <div style="margin-top: 20px">
        <table id="prismsDataGrid"></table>
    </div>
    <div id="prisms"></div>
    <div id="prismsEditTool">
    	<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="notify_obj.edit(id)">编辑</a>
    	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveToy()" id="save" style="display:none">保存</a>
  		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="redoToy()" id="redo" style="display:none">撤销</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="notify_obj.edit()">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="notify_obj.remove()">删除</a> -->
         <br>
    	短信类型：<select id="smsType" name="smsType" class="easyui-combobox">
    				<option value="">--请选择--</option>
    				<option value="系统消息">系统消息</option>
    				<option value="个人短信">个人短信</option>
    				<option value="邮件通知">邮件通知</option>
    	</select>
    	短信状态：<select id="readFlag" name="readFlag" class="easyui-combobox">
    				<option value="">--请选择--</option>
    				<option value="1">未读</option>
    				<option value="2">已读</option>
    	</select>
    	短信发送时间：<input type="date" class="easyui-datebox" id="prismsBeginDate">-
    	<input type="date" class="easyui-datebox" id="prismsEndDate">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="prisms_obj.search()">查询</a><br>
        
    
    </div>
    <script>
       $(function(){

           prisms_obj = {
               search : function() {
               //alert($("#smsType").combobox('getValue'))
               //alert($("#readFlag").combobox('getValue'))
                   $("#prismsDataGrid").datagrid('load',{
                       smsType : $("#smsType").combobox('getValue'),
                       beginDate :  $("#prismsBeginDate").datebox('getValue'),
                       endDate : $("#prismsEndDate").datebox('getValue'),
                       readFlag : $("#readFlag").combobox('getValue')
                   })
               },
               
              
              
           }

            $("#prismsDataGrid").datagrid({
            url : 'priSmsList',
            title : '短信列表',
            fitColumns : true,
            striped : true,
            rownumbers : true,
            columns : [[
            {
                field : 'priSms',
                
                hidden:true
            },
            {
                field : 'no',
                title : '序号',
                width : 50,
                checkbox : true
            },{
                field : 'smsId',
                title : '编号',
                width : 80,
                align : 'center',
                sortable : true,
                formatter: function(value,rowDate,rowIndex){
                	return rowDate.priSms.smsId;
                }
            },{
                field : 'smsType',
                title : '短信类型',
                width : 80,
                align : 'center',
                sortable : true,
                formatter: function(value,rowDate,rowIndex){
                	return rowDate.priSms.smsType;
                }
            },{
                field : 'sendTime',
                title : '发送时间',
                width : 150,
                align : 'center',
                sortable : true,
                formatter: function(value,rowDate,rowIndex){
                	return rowDate.priSms.sendTime;
                }
                
            },{
                field : 'readFlag',
                title : '阅读状态',
                width : 150,
                align : 'center',
                sortable : true,
                formatter: function(readFlag,rowDate,rowIndex){
                	if(rowDate["readFlag"]==1){
                		return "未读";
                	}else if(rowDate["readFlag"]==2){
                		return "已读";
                	}
                }
            },{
				field : "op1",
				title : "操作",
				width : 100,
				align : 'center',
				formatter : function(value, rowData, rowIndex) {
					var id = rowData.priSms.smsId;
					var mId = rowData.smsToId;
					return "<a href='#' onclick=getPrismsParticulars(" + id + ","+mId+")>查看详情</a>"
					}
				} ]],
            toolbar : "#prismsEditTool",
            pagination : true,
            pageSize : 5,
            pageList : [2, 5, 10, 20, 50 ],
            sortName : 'sendTime',
            sortOrder : 'desc'
        });
        });
        function getPrismsParticulars(id,mId) {
	        // 显示窗口

            $("#prisms").window({
                title : '短信详情',
                width : 700,
                height : 500,
                modal : true,
                minimizable : false,
                href : 'PrismsParticulars?smsId='+id +'&smsToId='+mId,
                onClose : function() {
                    $('#prismsDataGrid').datagrid("reload");
                }
            })
        }
    </script>
  </body>
</html>

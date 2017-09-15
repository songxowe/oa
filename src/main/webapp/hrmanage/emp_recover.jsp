<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>人事管理</title>
    <%@ include file="../commons/meta.jsp"%>
  </head>

  <body>
    <div style="margin: 10px 30px;">
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove"
        onclick="emp_obj_recover.recover()">档案恢复</a>
      <div id="editEmp_re"></div>
      <!-- 雇员列表的工具栏设置 -->
      <div id="searchEmpForm_re" style="padding: 10px;">
        <div style="padding: 0 0 0 6px;">
          姓名：
          <input type="text" id="empName_re" class="easyui-textbox"/>
         &nbsp;&nbsp;部门：
          <input id="dept_re" class="easyui-combobox"
               data-options="editable:true,valueField:'deptId',textField:'deptName',url:'hrDeptList'" /><br>
          工号：<input type="text" id="workId_re" class="easyui-textbox">
          &nbsp;&nbsp;入职时间：<input id="beginDate_join_re" class="easyui-datebox">--<input id="endDate_join_re" class="easyui-datebox">
         
          <a href="#" class="easyui-linkbutton" iconCls="icon-search"
            onclick=emp_obj_recover.search();>查询</a>
        </div>
      </div>

      <!-- 雇员列表显示 -->
      <div style="margin-top: 20px;">
        <table id="empDataGrid_re"></table>
      </div>
    </div>
    <script type="text/javascript">
  $(function() {
    emp_obj_recover = {
      search : function() {//查询
        //获得部门号
        var deptId = $('#dept_re').combobox('getValue');

        $("#empDataGrid_re").datagrid(
            "load",
            {
                empName : $.trim($("#empName_re").val()),
                deptId : deptId,
                beginDate : $("#beginDate_join_re").datebox('getValue'),
                endDate : $("#endDate_join_re").datebox('getValue'),
                workId : $.trim($("#workId_re").val())
            });
      },recover : function(){
        var rows = $("#empDataGrid_re").datagrid("getSelections");
        if(rows.length > 0) {
          $.messager.confirm("消息","确认真的要恢复所选的档案吗",function(flag){
            if(flag){
              var ids = [];
              for(var i=0;i<rows.length;i++){
                ids.push(rows[i].empId);
              }
              $.ajax({
                  type : "post",
                  url : "empController_recover",
                  dataType : 'json',
                data : {
                  ids : ids.join(","),
                },
                beforeSend : function(){
                  $("#empDataGrid_re").datagrid("loading");
                },
                success : function(data){
                  if(data) {
                    $("#empDataGrid_re").datagrid("loaded");
                    $("#empDataGrid_re").datagrid("load");
                    $("#empDataGrid_re").datagrid("unselectAll");
                    $.messager.show({
                      title : "提示",
                      msg : data + "个雇员被恢复"
                    });
                    $('#dept').combobox('reload');      
                  }
                }
              });
            }
          });
        }else {
          $.messager.alert("警告", "请选中要恢复的档案","warning");
        }
      }
    }

    $("#empDataGrid_re").datagrid( {
      url : "empList?status=0",
      title : "人事档案待恢复列表",
      fitColumns : true,
      striped : true,
      rownumbers : true,
      columns : [ [ {
        field : "no",
        title : "序号",
        width : 50,
        checkbox : true
      }, {
        field : "empName",
        title : "姓名",
        width : 100,
        sortable : true
      } , {
        field : "workId",
        title : "工号",
        width : 100,
        sortable : true
      } , {
        field : "joinDate",
        title : "入职日期",
        width : 100,
        sortable : true
      } , {
          field : "hrDept",
          title : "部门",
          width : 100,
          sortable : true
        } ,{
        field : "op1",
        title : "操作",
        width : 100,
        formatter : function(value, rowData, rowIndex){
          var id = rowData["empId"];
          return "<a href='#' onclick=getEmp("+id+")>查看详情</a>"
        }
      } ] ],
      toolbar : "#searchEmpForm_re",
      pagination : true,
      pageSize : 5,
      pageList : [ 5, 10, 15, 20, 50 ],
      sortName : "empId",
      sortOrder : "desc"
    });
  });

  //查看指定菜单
  function getEmp(id){
    $("#editEmp_re").window({
      title : "查看雇员详情",
      width : 550,
      height : 550,
      modal : true,
      minimizable : false,
      href : "empController_view?empId="+id
    });
  }
</script>
  </body>
</html>

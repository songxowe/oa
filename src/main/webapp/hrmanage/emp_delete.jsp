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
        onclick="emp_obj_delete.delete()">档案永久删除</a>
      <div id="editEmp_del"></div>
      <!-- 雇员列表的工具栏设置 -->

      <!-- 雇员列表显示 -->
      <div style="margin-top: 20px;">
        <table id="empDataGrid_del"></table>
      </div>
    </div>
    <script type="text/javascript">
  $(function() {
    emp_obj_delete = {
      delete : function(){
        var rows = $("#empDataGrid_l").datagrid("getSelections");
        if(rows.length > 0) {
          $.messager.confirm("消息","确认真的要删除所选的档案吗",function(flag){
            if(flag){
              var ids = [];
              for(var i=0;i<rows.length;i++){
                ids.push(rows[i].empId);
              }
              $.ajax({
                  type : "post",
                  url : "empController_delete",
                  dataType : 'json',
                data : {
                  ids : ids.join(","),
                },
                beforeSend : function(){
                  $("#empDataGrid_del").datagrid("loading");
                },
                success : function(data){
                  if(data) {
                    $("#empDataGrid_del").datagrid("loaded");
                    $("#empDataGrid_del").datagrid("load");
                    $("#empDataGrid_del").datagrid("unselectAll");
                    $.messager.show({
                      title : "提示",
                      msg : data + "个雇员被永久删除"
                    });
                    $('#dept').combobox('reload');      
                  }
                }
              });
            }
          });
        }else {
          $.messager.alert("警告", "请选中要删除的档案","warning");
        }
      }
    }

    $("#empDataGrid_del").datagrid( {
      url : "empList?status=0",
      title : "人事档案删除列表",
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
      pagination : true,
      pageSize : 5,
      pageList : [ 5, 10, 15, 20, 50 ],
      sortName : "empId",
      sortOrder : "desc"
    });
  });

  //查看指定菜单
  function getEmp(id){
    $("#editEmp_del").window({
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

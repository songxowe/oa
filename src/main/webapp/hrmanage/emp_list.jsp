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
      <a href="#" class="easyui-linkbutton" iconCls="icon-add"
        onclick="emp_obj.showEdit('add')">添加</a>&nbsp;&nbsp;
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove"
        onclick="emp_obj.remove()">删除</a>&nbsp;&nbsp;
      <a href="#" class="easyui-linkbutton" iconCls="icon-edit"
        onclick="emp_obj.showEdit('edit')">修改</a>
      <!-- 新增雇员信息窗口 -->
      <div id="editEmp">

      </div>
      <!-- 雇员列表的工具栏设置 -->
      <div id="searchEmpForm" style="padding: 10px;">
        <div style="padding: 0 0 0 6px;">
          姓名：
          <input type="text" id="empName" class="easyui-textbox"/>
         &nbsp;&nbsp;部门：
          <input id="dept" class="easyui-combobox" name="dept"   
               data-options="editable:true,valueField:'deptId',textField:'deptName',url:'hrDeptList'" /><br>
          工号：<input type="text" id="workId" class="easyui-textbox">
          &nbsp;&nbsp;入职时间：<input id="beginDate_join" class="easyui-datebox">--<input id="endDate_join" class="easyui-datebox">
         
          <a href="#" class="easyui-linkbutton" iconCls="icon-search"
            onclick=emp_obj.search();>查询</a>
        </div>
      </div>

      <!-- 雇员列表显示 -->
      <div style="margin-top: 20px;">
        <table id="empDataGrid">

        </table>

      </div>
    </div>
    <script type="text/javascript">
  $(function() {
    emp_obj = {
      search : function() {//查询
        //获得部门号
        var deptId = $('#dept').combobox('getValue');
        alert(deptId);
        $("#empDataGrid").datagrid(
            "load",
            {
                empName : $.trim($("#empName").val()),
                deptId : deptId,
                beginDate : $("#beginDate_join").datebox('getValue'),
                endDate : $("#endDate_join").datebox('getValue'),
                workId : $.trim($("#workId").val())
            });
      },remove : function(){
        var rows = $("#empDataGrid").datagrid("getSelections");
        if(rows.length > 0) {
          $.messager.confirm("消息","确认真的要删除所选的数据吗",function(flag){
            if(flag){
              var ids = [];
              for(var i=0;i<rows.length;i++){
                ids.push(rows[i].empId);
               }
              $.ajax({
                  type : "post",
                  url : "empController_remove",
                  dataType : 'json',
                data : {
                  ids : ids.join(","),
                },
                beforeSend : function(){
                  $("#empDataGrid").datagrid("loading");
                },
                success : function(data){
                  if(data) {
                    $("#empDataGrid").datagrid("loaded");
                    $("#empDataGrid").datagrid("load");
                    $("#empDataGrid").datagrid("unselectAll");
                    $.messager.show({
                      title : "提示",
                      msg : data + "个雇员被删除"
                    });
                    $('#dept').combobox('reload');      
                  }
                }
              });
            }
          });
        }else {
          $.messager.alert("警告", "请选中要删除的数据","warning");
        }
      },
      showEdit : function(state){
        var url = "empController_findById";
        var info = "";
        var id = 0;
        if(state == 'add') {//新增
          info = "新增员工档案";
        }else {//修改
          info = "修改员工档案";
          var rows = $("#empDataGrid").datagrid("getSelections");
          if(rows.length == 1){
            id = rows[0].empId;
            url += "?empId="+id;
          }else{
            $.messager.alert("警告", "必须选中一行", "warning");
            return;
          }
        }
        $("#editEmp").window({
          title : info,
          width : 850,
          height : 600,
          modal : true,
          minimizable : false,
          href : url,
          onClose : function(){
            $("#empDataGrid").datagrid(
            "reload");
            $('#dept').combobox('reload');    
          }
        });
      }
    }

    $("#empDataGrid").datagrid( {
      url : "empList?status=1",
      title : "人事档案列表",
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
      toolbar : "#searchEmpForm",
      pagination : true,
      pageSize : 5,
      pageList : [ 5, 10, 15, 20, 50 ],
      sortName : "empId",
      sortOrder : "desc"
    });
  });

  //查看指定菜单
  function getEmp(id){
    $("#editEmp").window({
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

<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../commons/taglib.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>会议室管理</title>
    <%@ include file="../commons/meta.jsp" %>
</head>

<body>
<div style="margin: 10px 30px;">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add"
       onclick="room_obj.showEdit('add')">添加</a>&nbsp;&nbsp;
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit"
       onclick="room_obj.showEdit('edit')">修改</a>
    <!-- 新增雇员信息窗口 -->
    <div id="editRoom">

    </div>
    <!-- 雇员列表的工具栏设置 -->
    <div id="searchForm_room" style="padding: 10px;">
        <div style="padding: 0 0 0 6px;">
            姓名：
            <input type="text" id="mrName" class="easyui-textbox"/>
            <a href="#" class="easyui-linkbutton" iconCls="icon-search"
               onclick=room_obj.search();>查询</a>
        </div>
    </div>

    <div style="margin-top: 20px;">
        <table id="roomDataGrid">

        </table>

    </div>
</div>
<script type="text/javascript">
    $(function () {
        room_obj = {
            search: function () {//查询
                $("#roomDataGrid").datagrid(
                    "load",
                    {
                        mrName: $.trim($("#mrName").val())

                    });
            },
            showEdit: function (state) {
                var url = "roomController_findById";
                var info = "";
                var id = 0;
                if (state == 'add') {//新增
                    info = "新增会议室";
                } else {//修改
                    info = "修改会议室";
                    var rows = $("#roomDataGrid").datagrid("getSelections");
                    if (rows.length == 1) {
                        id = rows[0].mrId;
                        url += "?mrId=" + id;
                    } else {
                        $.messager.alert("警告", "必须选中一行", "warning");
                        return;
                    }
                }
                $("#editRoom").window({
                    title: info,
                    width: 850,
                    height: 600,
                    modal: true,
                    minimizable: false,
                    href: url,
                    onClose: function () {
                        $("#roomDataGrid").datagrid(
                            "reload");
                    }
                });
            }
        }

        $("#roomDataGrid").datagrid({
            url: "roomController_list",
            title: "会议室列表",
            fitColumns: true,
            striped: true,
            rownumbers: true,
            columns: [[{
                field: "no",
                title: "序号",
                width: 50,
                checkbox: true
            }, {
                field: "mrName",
                title: "姓名",
                width: 100,
                sortable: true
            }, {
                field: "mrCapacity",
                title: "容纳人数",
                width: 100,
                sortable: true
            }, {
                field: "mrPlace",
                title: "地址",
                width: 100,
                sortable: true
            }, {
                field: "useingFalg",
                title: "使用状态",
                width: 100,
                formatter : function(value,rowData,rowIndex) {
                    if(rowData["useingFalg"] == 1) {
                        return "使用中"
                    }else{
                        return "空闲"
                    }
                }
            }, {
                field: "op1",
                title: "操作",
                width: 100,
                formatter: function (value, rowData, rowIndex) {
                    var id = rowData["mrId"];
                    return "<a href='#' onclick=getRoom(" + id + ")>查看详情</a>"
                }
            }]],
            toolbar: "#searchForm_room",
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20, 50],
            sortName: "mrId",
            sortOrder: "desc"
        });
    });

    //查看指定菜单
    function getRoom(id) {
        $("#editRoom").window({
            title: "查看会议室详情",
            width: 550,
            height: 550,
            modal: true,
            minimizable: false,
            href: "roomController_view?mrId=" + id
        });
    }
</script>
</body>
</html>

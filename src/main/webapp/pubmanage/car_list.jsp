<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../commons/taglib.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>车辆管理</title>
    <%@ include file="../commons/meta.jsp" %>
</head>

<body>
<div style="margin: 10px 30px;">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add"
       onclick="car_obj.showEdit('add')">添加</a>&nbsp;&nbsp;
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit"
       onclick="car_obj.showEdit('edit')">修改</a>
    <!-- 新增雇员信息窗口 -->
    <div id="editCar">

    </div>
    <!-- 雇员列表的工具栏设置 -->
    <div id="searchForm_car" style="padding: 10px;">
        <div style="padding: 0 0 0 6px;">
            车牌：
            <input type="text" id="VNum" class="easyui-textbox"/>
            购买日期：<input class="easyui-datebox" id="beginDate_buy">--<input class="easyui-datebox" id="endDate_buy">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search"
               onclick=car_obj.search();>查询</a>
        </div>
    </div>

    <div style="margin-top: 20px;">
        <table id="carDataGrid">

        </table>

    </div>
</div>
<script type="text/javascript">
    $(function () {
        car_obj = {
            search: function () {//查询
                $("#carDataGrid").datagrid(
                    "load",
                    {
                        VNum: $.trim($("#VNum").val()),
                        beginDate : $("#beginDate_buy").datebox('getValue'),
                        endDate : $("#endDate_buy").datebox('getValue')
                    });
            },
            showEdit: function (state) {
                var url = "carController_findById";
                var info = "";
                var id = 0;
                if (state == 'add') {//新增
                    info = "新增车辆";
                } else {//修改
                    info = "修改车辆信息";
                    var rows = $("#carDataGrid").datagrid("getSelections");
                    if (rows.length == 1) {
                        id = rows[0].VId;
                        url += "?VId=" + id;
                    } else {
                        $.messager.alert("警告", "必须选中一行", "warning");
                        return;
                    }
                }
                $("#editCar").window({
                    title: info,
                    width: 850,
                    height: 600,
                    modal: true,
                    minimizable: false,
                    href: url,
                    onClose: function () {
                        $("#carDataGrid").datagrid(
                            "reload");
                    }
                });
            }
        }

        $("#carDataGrid").datagrid({
            url: "carController_list",
            title: "车辆列表",
            fitColumns: true,
            striped: true,
            rownumbers: true,
            columns: [[{
                field: "no",
                title: "序号",
                width: 50,
                checkbox: true
            }, {
                field: "VNum",
                title: "车牌",
                width: 100,
                sortable: true
            }, {
                field: "VType",
                title: "车辆类型",
                width: 100,
                sortable: true
            }, {
                field: "VPrice",
                title: "价格",
                width: 100,
                sortable: true
            }, {
                field: "buyDate",
                title: "购买时间",
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
                    var id = rowData["VId"];
                    return "<a href='#' onclick=getCar(" + id + ")>查看详情</a>"
                }
            }]],
            toolbar: "#searchForm_car",
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20, 50],
            sortName: "VId",
            sortOrder: "asc"
        });
    });

    //查看指定菜单
    function getCar(id) {
        $("#editCar").window({
            title: "查看车辆详情",
            width: 550,
            height: 550,
            modal: true,
            minimizable: false,
            href: "carController_view?VId=" + id
        });
    }
</script>
</body>
</html>

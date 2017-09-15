<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>公告</title>
    <meta charset="utf-8">
    <%@ include file="../commons/meta.jsp" %>
</head>


<body>

<div style="margin-top: 20px">
    <table id="notifysDataGrid"></table>
</div>
<div id="notify"></div>
<div id="notifyEditTool">
    <!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="notify_obj.edit(id)">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveToy()" id="save" style="display:none">保存</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="redoToy()" id="redo" style="display:none">撤销</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="notify_obj.edit()">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="notify_obj.remove()">删除</a> -->
    <br>
    公告标题：&nbsp;&nbsp; <input type="text" class="easyui-textbox" id="notifySubject">
    公告发布时间：<input type="date" class="easyui-datebox" id="notifyBeginDate">-
    <input type="date" class="easyui-datebox" id="notifyEndDate">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="notify_obj.search()">查询</a>


</div>
<script>
    $(function () {

        notify_obj = {
            search: function () {

                $("#notifysDataGrid").datagrid('load', {
                    subject: $.trim($("#notifySubject").val()),
                    beginDate: $("#notifyBeginDate").datebox('getValue'),
                    endDate: $("#notifyEndDate").datebox('getValue')
                })
            },
        }

        $("#notifysDataGrid").datagrid({
            url: 'notifysByUser',
            title: '公告列表',
            fitColumns: true,
            striped: true,
            rownumbers: true,
            columns: [[
                {
                    field: 'pubNotify',
                    title: '序号',
                    hidden: true
                },
                {
                    field: 'no',
                    title: '序号',
                    width: 50,
                    checkbox: true
                }, {
                    field: 'notifyId',
                    title: '编号',
                    width: 80,
                    align: 'center',
                    sortable: true,
                    formatter: function (readFlag, rowDate, rowIndex) {
                        return rowDate.pubNotify.notifyId;
                    }
                }, {
                    field: 'subject',
                    title: '标题',
                    width: 80,
                    align: 'center',
                    sortable: true,
                    formatter: function (readFlag, rowDate, rowIndex) {
                        return rowDate.pubNotify.subject;
                    }
                }, {
                    field: 'createTime',
                    title: '发布日期',
                    width: 150,
                    align: 'center',
                    sortable: true,
                    formatter: function (readFlag, rowDate, rowIndex) {
                        return rowDate.pubNotify.createTime;
                    }

                }, {
                    field: 'readFlag',
                    title: '阅读状态',
                    width: 150,
                    align: 'center',
                    sortable: true,
                    formatter: function (readFlag, rowDate, rowIndex) {
                        if (rowDate["readFlag"] == 1) {
                            return "未读";
                        } else if (rowDate["readFlag"] == 2) {
                            return "已读";
                        }
                    }
                }, {
                    field: "op1",
                    title: "操作",
                    width: 100,
                    align: 'center',
                    formatter: function (value, rowData, rowIndex) {
                        var id = rowData.pubNotify.notifyId;
                        var rId = rowData.reviceId;
                        return "<a href='#' onclick=getNotifyParticulars(" + id + ","+rId+")>查看详情</a>"
                    }
                }]],
            toolbar: "#notifyEditTool",
            pagination: true,
            pageSize: 5,
            pageList: [2, 5, 10, 20, 50],
            sortName: 'createTime',
            sortOrder: 'desc'
        });
    });

    function getNotifyParticulars(id,rId) {
        // 显示窗口

        $("#notify").window({
            title: '公告详情',
            width: 700,
            height: 500,
            modal: true,
            minimizable: false,
            href: 'particulars?notifyId=' + id +'&reviceId='+rId,
            onClose: function () {
                $('#notifysDataGrid').datagrid("reload");
            }
        })
    }
</script>
</body>
</html>

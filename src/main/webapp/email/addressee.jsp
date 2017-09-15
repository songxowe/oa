<%@ page language="java" pageEncoding="UTF-8" %>


<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">

    <title>邮件管理-收件箱</title>

    <%@ include file="../commons/meta.jsp" %>

</head>

<body>
<div style="margin: 10px 30px;">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add"
       onclick="mailbox_obj_1.remove('remove')">删除</a>&nbsp;&nbsp;
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit"
       onclick="mailbox_obj_1.remove('thoroughly')">永久删除</a>&nbsp;&nbsp;


    <!-- 用户列表的工具栏设置 -->
    <div id="searchUserForm3" style="padding: 10px;">
        <div style="padding: 0 0 0 6px">
            <label>发件人:</label> <input id="fromId" name="fromId" class="easyui-textbox">
            <label>邮件主题:</label> <input id="subject" name="subject" class="easyui-textbox">
            <label>重要程度:<select name="important" id="important">
                <option value="一般">一般</option>
                <option value="重要" selected="selected">重要</option>
                <option value="非常重要">非常重要</option>
            </select></label>
            <label>阅读:<select name="readFlag" id="readFlag">
                <option value="0">未读</option>
                <option value="1">已读</option>
            </select></label>
            <br> <label>发送日期:</label> <input id="beginDate" class="easyui-datebox">
            <input id="endDate" class="easyui-datebox">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="mailbox_obj_1.search()">查询</a>
        </div>
        <div id="addressMailBox"></div>

    </div>

    <!-- 用户列表显示 -->
    <div style="margin-top: 20px;">

        <div style="margin-top: 10px;">
            <table id="addresseeData"></table>
        </div>

    </div>
    <input type="hidden" id="id" value="${sessionScope['NEWER_USER_LOGIN_INFO'].userTrueName}">
</div>
<script type="text/javascript">
    $(function () {


        mailbox_obj_1 = {
            search: function () {//查询
                $("#addresseeData").datagrid(
                    "load",
                    {
                        fromId: $.trim($("#fromId").val()),
                        important: $.trim($("#important").val()),
                        subject: $.trim($("#subject").val()),
                        beginDate: $("#beginDate").datebox('getValue'),
                        endDate: $("#endDate").datebox('getValue'),
                        readFlag: $.trim($("#readFlag").val()),
                    });
            },
            remove: function (state) {
                var rows = $("#addresseeData").datagrid("getSelections");
                if (rows.length > 0) {
                    $.messager.confirm("消息", "确认真的要删除所选的数据吗", function (flag) {
                        if (flag) {
                            var ids = [];
                            for (var i = 0; i < rows.length; i++) {
                                ids.push(rows[i].mailToId);
                            }

                            if (state == 'thoroughly') {
                                url = "modifyBy?deleteFlag=2"

                            } else if (state == "remove") {
                                url = "modifyBy?deleteFlag=1"
                            }
                            $.ajax({
                                type: "post",
                                url: url,
                                data: {
                                    ids: ids.join(","),
                                },
                                beforeSend: function () {
                                    $("#addresseeData").datagrid("loading");
                                },
                                success: function (data) {
                                    if (data) {

                                        $.messager.show({
                                            title: "提示",
                                            msg: data + "被删除成功"
                                        });
                                        $("#addresseeData").datagrid("loaded");
                                        $("#addresseeData").datagrid("load");
                                        $("#addresseeData").datagrid("unselectAll");
                                    }
                                }
                            });
                        }
                    });
                } else {
                    $.messager.alert("警告", "请选中要删除的数据", "warning");
                }
            }
        }

        $("#addresseeData").datagrid({
            url: 'listTwo?deleteFlag=0&toId=' + $("#id").val(),
            title: '邮箱管理系统',
            fitColumns: true,
            striped: true,
            rownumbers: true,
            columns: [[{
                field: 'priMail',
                title: '序号',
                hidden: true

            }, {
                field: 'io',
                title: '序号',
                width: 50,
                checkbox: true,
                sortable: true
            }, {
                field: 'mailId',
                title: '邮件编号',
                width: 200,
                align: 'center',
                sortable: true,
                hidden:true,
                formatter: function (value, rowData, rowIndex) {
                    return rowData.priMail.mailId;
                }
            }, {
                field: 'fromId',
                title: '发件人',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (value, rowData, rowIndex) {
                    return rowData.priMail.fromId;
                }
            }, {
                field: 'subject',
                title: '邮件主题',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (value, rowData, rowIndex) {
                    return rowData.priMail.subject;
                }
            }, {
                field: 'sendTime',
                title: '日期',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (value, rowData, rowIndex) {
                    return rowData.priMail.sendTime;
                }
            }, {
                field: 'attachment',
                title: '附件名称',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (value, rowData, rowIndex) {
                    return rowData.priMail.attachment;
                }
            }, {
                field: "op1",
                title: "查看详情",
                width: 80,
                align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    var mailToId = rowData.mailToId;
                    return "<a href='#' onclick=getmailbox1(" + "'" + mailToId + "'" + ")>查看</a>"
                }
            }]],
            toolbar: "#searchUserForm3",
            pagination: true,
            pageSize: 2,
            pageList: [2, 5, 10, 15, 20],
            sortName: "toId",
            sortOrder: "asc",
        })


    });

    function getmailbox1(mailToId) {
        $("#addressMailBox").window({
            title: "查看邮件详情",
            width: 350,
            height: 430,
            modal: true,
            minimizable: false,
            href: "findById_email?transmit=1&mailToId=" + mailToId,
            onClose: function () {
                $("#addresseeData").datagrid('unselectAll')
            }
        })


    }
</script>
</body>
</html>

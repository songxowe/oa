<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="commons/taglib.jsp" %>

<!DOCTYPE html>

<html>
<head>
    <TITLE>OA自动办公</TITLE>
    <%@ include file="commons/meta.jsp" %>
    <script src="${pageContext.request.contextPath }/resources/js/Clock.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/index.js"></script>
    <style>
        #logorighttop {
            PADDING-RIGHT: 50px;
            BACKGROUND-POSITION: right 50%;
            DISPLAY: block;
            PADDING-LEFT: 0px;
            BACKGROUND-IMAGE: url(${pageContext.request.contextPath }/resources/images/bg_banner_menu.gif);
            PADDING-BOTTOM: 0px;
            PADDING-TOP: 3px;
            BACKGROUND-REPEAT: no-repeat;
            HEIGHT: 30px;
            TEXT-ALIGN: right;
            position: absolute;
            top: 0px;
            right: 0px;
            width: 345px;
        }

        #logorighttop A {
            color: white;
            text-decoration: none;
        }

        #logomenu {
            BACKGROUND-IMAGE: url(${pageContext.request.contextPath }/resources/images/bg_nav.gif);
            BACKGROUND-REPEAT: repeat-x;
            HEIGHT: 30px;
            color: white;
        }

        #logomenu A {
            color: white;
        }
    </style>
    <script src="${pageContext.request.contextPath }/resources/js/sucaijiayuan.js"></script>
    <style>

        a {text-decoration: none}
        a:hover {color:red;}

        body{background:#fff;}
        .content {
            border: 3px solid #ddd;
            width: 405px;
            margin: 100px auto;
        }

        .datetable {
            border-top: 1px solid #ccc;
            border-left: 1px solid #ccc;
            background: #fff;
        }

        .datetable td {
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #ccc;
            text-align: center;
        }

        .datetable thead {
            background: #006600;
        }

        .datetable thead td {
            padding: 10px 5px;
            font: normal 12px/normal 'microsoft yahei';
            color: #fff;
            text-align: center;
        }

        .datetable thead td span {
            padding: 0 5px;
        }

        .datetable tbody td {
            padding: 5px 3px;
            font-size: 12px;
        }
    </style>
    <script type="text/javascript">
        //询问是否退出系统
        function exitSystem() {
            var flag = confirm("真的要退出系统吗？");
            if (flag)
                window.location = "userController_logout.html";
        }

        function showDivChangePassword() {
            $("#passDiv").window({
                title: "修改密码",
                width: 550,
                height: 250,
                modal: true,
                minimizable: false,
                href: "changePassUI.jsp"
            });
        }
    </script>
</head>

<c:if test="${empty sessionScope['NEWER_USER_LOGIN_INFO']}">
    <c:redirect url="login.jsp"/>
</c:if>


<body class="easyui-layout" onload="before(),initial()">
<div data-options="region:'north',split:true"
     style="height: 133px; BACKGROUND-IMAGE: url(${pageContext.request.contextPath }/resources/images/bg.gif);">
    <img src="${pageContext.request.contextPath }/resources/images/logo.png" border="0"/>
    <DIV id="logorighttop">
        <IMG src="${pageContext.request.contextPath }/resources/images/menu_seprator.gif" align=absMiddle>
        <A id=HyperLink2 href="javascript:showDivChangePassword()">修改密码</A>
        <IMG src="${pageContext.request.contextPath }/resources/images/menu_seprator.gif" align=absMiddle>
        <A id=HyperLink3 href="javascript:exitSystem()">退出系统</A>
    </DIV>
    <!-- 显示修改密码div -->
    <div id="passDiv"></div>
    <DIV id="logomenu">
        <TABLE cellSpacing="0" cellPadding="0" width="100%">
            <TBODY>
            <TR>
                <TD>
                    <DIV>
                        <IMG src="${pageContext.request.contextPath }/resources/images/nav_pre.gif" align=absMiddle>
                        欢迎
                        <SPAN id=lblBra>OA办公系统</SPAN>
                        [${sessionScope["NEWER_USER_LOGIN_INFO"].userTrueName} ] 光临
                    </DIV>
                </TD>
                <TD align=right width="70%">
								<SPAN style="PADDING-RIGHT: 50px">
										<IMG src="${pageContext.request.contextPath }/resources/images/menu_seprator.gif"
                                             align=absMiddle><SPAN
                                            id="clock"></SPAN>
								</SPAN>

                </TD>
            </TR>
            </TBODY>
        </TABLE>
    </DIV>
    <SCRIPT type=text/javascript>
        var clock = new Clock();
        clock.display(document.getElementById("clock"));
    </SCRIPT>
</div>
<!-- 左边导航菜单 -->
<div data-options="region:'west',title:'导航菜单',split:true"
     style="width: 240px;">
    <ul id="tree" class="easyui-tree">

    </ul>
</div>
<!-- 右边内容部分 -->
<div data-options="region:'center'"
     style="padding: 5px; background: #eee;">
    <div id="divtabs" class="easyui-tabs"
         data-options="fit:true,border:false">

        <div title="首页" style="padding: 15px">
            <div id="notifyPanel" style="margin:auto;padding:10px;background:#fafafa;">
                <table id="revices" style="width:530px;height:170px;text-align:center"></table>
                <a href='#' onclick="moreNotify()">查看更多</a>
                <div id="viewNotify"></div>
            </div>

            <div style="position: absolute;top:50px;left: 600px">
                <div id="emailPanel" class="easyui-panel" style="padding:10px;background:#fafafa;">
                <center>您有<span id="msgEmail" style="color:red;font-size:16px"></span>条未读邮件</center>
                </div>
            </div>
            <div style="position: absolute;top:115px;left: 600px">
                <div id="prismsPanel" class="easyui-panel" style="padding:10px;background:#fafafa;">
                    <center>您有<span id="msg" style="color:red;font-size:16px"></span>条未读信息</center>
                </div>
            </div>
            <div style="position: absolute;top:180px;left: 600px">
                <div id="taskPanel" class="easyui-panel" style="padding:10px;background:#fafafa;">
                    <center>您有<span id="msgTask" style="color:red;font-size:16px"></span>个工作审批待处理</center>
                </div>
            </div>

            <div style="position: absolute; top: 300px;">
                <div id="votePanel"
                     style="width: 600px; height: 300px; margin: auto; background: #fafafa;">
                    <table id="voteTable"
                           style="width: 540px; height: 190px; text-align: center">
                        <tr align="center">
                            <th>投票标题</th>
                            <th>创建人</th>
                            <th>终止时间</th>
                        </tr>
                    </table>
                    <a href="#" onclick="showVoteTable()">查看更多</a>
                    <div id="viewVoteModel"></div>
                </div>
            </div>

            <!-- 日历控件 -->
            <div style="position: absolute;top:140px;left:600px;width:400px;height:220px">
                <form name="CLD" class="content" style="width:400px;height:220px">
                    <table width="400px" height="220px" border="0" cellpadding="0"
                           cellspacing="0" class="datetable">
                        <thead>
                        <tr>
                            <td colSpan=7><span>公历</span> <select name="SY"
                                                                  onchange="changeCld();" style="font-SIZE: 9pt">
                                <script>
                                    for (i = 1900; i < 2050; i++) document.write('<option>' + i);
                                </script>
                            </select><span>年</span> <select name="SM" onchange="changeCld();"
                                                            style="font-SIZE: 9pt">
                                <script>
                                    for (i = 1; i < 13; i++) document.write('<option>' + i);
                                </script>
                            </select><span>月</span> </font><span id="GZ"></span></td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr style="background:#eee;">
                            <td width="54">日</td>
                            <td width="54">一</td>
                            <td width="54">二</td>
                            <td width="54">三</td>
                            <td width="54">四</td>
                            <td width="54">五</td>
                            <td width="54">六</td>
                        </tr>
                        <script>
                            var gNum;
                            for (i = 0; i < 6; i++) {
                                document.write('<tr align="center">');
                                for (j = 0; j < 7; j++) {
                                    gNum = i * 7 + j;
                                    document.write('<td id="GD' + gNum + '"><font id="SD' + gNum + '" size=2 face="Arial Black"');
                                    if (j == 0) document.write('color="red"');
                                    if (j == 6) document.write('color="#000080"');
                                    document.write('></font><br/><font id="LD' + gNum + '" size=2 style="font-size:9pt"></font></td>');
                                }
                                document.write('</tr>');
                            }
                        </script>
                        </tbody>
                    </table>
                </form>
            </div>

        </div>

    </div>


</div>

<script type="text/javascript">
    function before() {
        voteRefresh();
    }
    //投票-*--*--*--*--*--*---*---
    function voteRefresh() {
        $.ajax({
            url: 'votes',
            type: 'post',
            success: function (data) {

                $("#voteTable tr:not(:first)").each(function () {
                    this.remove();
                });
                $.each(
                    data.votes,
                    function () {
                        var tr = "<tr id='" + this.voteId + "'><td><a href='#' onclick=\"viewVotes("
                            + this.voteId
                            + ")\">"
                            + this.subject
                            + "</a></td><td>"
                            + this.user
                            + "</td><td>"
                            + this.endTime
                            + "</td></tr>";

                        $("#voteTable").append(tr)

                    })
            }
        });

        /*************公告刷新*****************/
            //公告刷新
            var i =1;
            $.ajax({
                url : 'notifyList',
                type : 'post',
                dateType : 'json',
                success : function(data){
                    $("#revices").empty();
                    var th = $("<tr><th>编号</th><th>标题</th><th>发布时间</th></tr>")
                    $("#revices").append(th)
                    $.each(data,function(){

                        var tr = $("<tr id='"+this.pubNotify.notifyId+"'></tr>")
                        var td1 = $("<td>"+i+"</td>")
                        var td2 = $("<td><a href='#' onclick='viewParticulars("+this.pubNotify.notifyId+")'>"+this.pubNotify.subject+"</a></td>")
                        var td3 = $("<td>"+this.pubNotify.createTime+"</td>")

                        $("#revices").append(tr)
                        $("#"+this.pubNotify.notifyId).append(td1)
                        $("#"+this.pubNotify.notifyId).append(td2)
                        $("#"+this.pubNotify.notifyId).append(td3)
                        i++;
                        if(i>5){
                            i=1;
                        }
                    })
                }
            });
            //短信面板刷新
            $.ajax({
                url : 'smsFindReadFlag',
                type : 'post',
                success : function(data){
                    if(data) {
                        $("#msg").text(data)
                    }else {
                        $("#msg").text(0)
                    }

                }
            });

            //邮箱面板刷新
            $.ajax({
                url : 'findreadCount',
                type : 'post',
                success : function(data){
                    if(data) {
                        $("#msgEmail").text(data)
                    }else{
                        $("#msgEmail").text(0)
                    }

                }
            })

            $.ajax({
                url : 'getTaskCount',
                type : 'post',
                success : function(data){
                    if(data) {
                        $("#msgTask").text(data)
                    }else{
                        $("#taskPanel").panel('close');
                    }
                }
            })

        //右下角弹窗刷新
        $.ajax({
            url : 'priSmsPop',
            type : 'post',
            success : function(data){
                if(data) {
                    $.messager.show({
                        title : '提示信息',
                        msg : data.priSms.content,
                        timeout : 5000
                    })
                }
            }
        });
    }

    //投票栏ajax结束****************************

    //IE浏览器有问题
    setInterval(voteRefresh, 500);

    function showVoteTable() {
        var tab = $("#divtabs");
        var title = "已发布的投票";
        var href = "goVote";
        if (tab.tabs('exists', title)) {
            //如果点击的选项卡已经存在，则选中该选项卡
            tab.tabs('select', title);
        } else {
            var content;
            if (href) {
                content = "<iframe src='" + href
                    + "' style='width:100%;height:100%'></iframe>"
            } else {
                content = "此功能还在开发中";
            }

            tab.tabs('add', {
                title: title,//标题
                content: content,//选修页内容
                closable: true
            });
        }
    }

    $(function () {

        $('#votePanel').panel({
            width: 550,
            height: 240,
            title: '最新投票信息',
            iconCls: 'icon-tip',
            closable: false,
            collapsible: false,
            minimizable: false,
            maximizable: true
        });
    });

    function viewVotes(id) {
        $("#viewVoteModel").window({
            title: '投票详情',
            width: 800,
            height: 500,
            modal: true,
            minimizable: false,
            href: "${pageContext.request.contextPath }/vote/indexList.jsp?voteId=" + id

        })
    }
</script>
<script type="text/javascript">
    /*
     *	点击超链接添加选项页的函数
     */
    function moreNotify() {
        var tab = $("#divtabs");
        var title = "公告通知";
        var href = "goNotifyUser";
        if (tab.tabs('exists', title)) {
            //如果点击的选项卡已经存在，则选中该选项卡
            tab.tabs('select', title);
        } else {
            var content;
            if (href) {
                content = "<iframe src='" + href
                    + "' style='width:100%;height:100%'></iframe>"
            } else {
                content = "此功能还在开发中";
            }

            tab.tabs('add', {
                title: title,//标题
                content: content,//选修页内容
                closable: true
            });
        }
    }
</script>

<script type="text/javascript">

    $(function() {
        $('#notifyPanel').panel({
            width : 550,
            height : 250,
            title : '公告栏',
            iconCls : 'icon-tip',
            closable : false,
            collapsible : false,
            minimizable : false,
            maximizable : true
        });
        $('#emailPanel').panel({
            width : 400,
            height : 65,
            title : '我的邮箱',
            iconCls : 'icon-tip',
            closable : false,
            collapsible : false,
            minimizable : false,
            maximizable : true
        });
        $('#prismsPanel').panel({
            width : 400,
            height : 65,
            title : '我的短信',
            iconCls : 'icon-tip',
            closable : false,
            collapsible : false,
            minimizable : false,
            maximizable : true
        });
        $('#taskPanel').panel({
            width : 400,
            height : 65,
            title : '我的审批',
            iconCls : 'icon-tip',
            closable : false,
            collapsible : false,
            minimizable : false,
            maximizable : true
        });

    });

    function viewParticulars(id){
        $("#viewNotify").window({
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
<script type="text/javascript">
    /*
     *	点击超链接添加选项页的函数
    */
    function moreNotify(){
        var tab=$("#divtabs");
        var title = "公告通知";
        var href = "goNotifyUser";
        if(tab.tabs('exists',title)){
            //如果点击的选项卡已经存在，则选中该选项卡
            tab.tabs('select',title);
        }else{
            var content;
            if(href){
                content="<iframe src='"+href+"' style='width:100%;height:100%'></iframe>"
            }else{
                content="此功能还在开发中";
            }

            tab.tabs('add',{
                title:title,//标题
                content:content,//选修页内容
                closable:true
            });
        }
    }
</script>


</body>
</html>

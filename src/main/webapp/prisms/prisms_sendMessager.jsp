<%@ page language="java" pageEncoding="UTF-8"%>
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
	<center>
		<div style="margin-top:100px">
			<table border="1" cellspacing="0" >
					<tr id="tr">
						<td>收件人</td>
						<td id="td1"><input name="toId" id="sendToId" type="text" size="35px"></td>
					</tr>
					<tr>
					<td><a href="#" onclick="findAllUser(allUser)">通讯录</a></td>
					<td><div id="allUser" style="display: none;">
							<ul id='beauty1' style="list-style: none"></ul>
						</div></td>
					</tr>
					<tr>
						<td>短信内容：</td>
						<td><input type="text" id="sendContent" name="content" class="easyui-textbox" data-options="multiline:true" style="height:200px;width:250px"></td>
					</tr>
					
				</table>
				<button type="button" onclick="sendMessager()">发送</button>

		</div>
	</center>
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url : 'findByAllTrueName',
				type : 'post',
				dataType : 'json',
				success : function(data){
					$.each(data,function(){
						var li=$("<li><a href='#' id='"+this.id+"' onclick='getId("+this.id+")'>"+this.userTrueName+"</a></li>")
						$("#beauty1").append(li)
					})
				}
			});
		});
		//显示通讯录
		function findAllUser(allUser){
			if (allUser.style.display == "block") {
				allUser.style.display = 'none';
			} else {

				allUser.style.display = 'block';
			}
		}
		//将通讯录添加到收件人
		function getId(id){
			var val = $("#"+id).text();
		
			if(val!=null){
					var s = $("#sendToId").val();
					arrS = s.split(",");
					flag = false; //标记是否已经添加
					for (i = 0; i < arrS.length; i++) {
						if (arrS[i] == val) { //判断该Email地址是否已经添加
							flag = true;
							break;
						}
					}
					if (!flag) {
						$("#sendToId").val(s  + val+ ",");
					}
					
			}else{
				$("#sendToId").val(val) ;
			}
			
		}
		//发送信息
		function sendMessager(){
			var content = $("#sendContent").val();
			var toId = $("#sendToId").val();
			$.ajax({
				url : 'sendMessager',
				type : 'post',
				data : {'content':content,'toId':toId},
				success : function(data){
					$.messager.show({
						title : '提示信息',
						msg : data+'条信息发送成功',
						timeout : 3000
					})
				}
			});
		}
	</script>

</body>
</html>

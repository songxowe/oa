<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css">
</head>
<body>
	<div>
		<form onsubmit="return checkInput()" id="voteForm" method="post">
			<input type="hidden" name="_method" value="post">
			<table id="tab1"  border="1" cellspacing="0" cellpadding="0" style="float: left; height:321px;">
				<tr>
					<th>投票标题</th>
					<td><input class="easyui-textbox" type="text" name="subject" value="${pubVote.subject }"  data-options="required:true"></td>
				</tr>
				<tr>
					<th>投票描述</th>
					<td><input class="easyui-textbox" type="text" name="descn" value="${pubVote.descn }"  data-options="required:true"></td>
				</tr>
				<tr>
					<th>终止时间</th>
					<td><input class="easyui-datetimebox" name="endTime" value='<fmt:formatDate value="${pubVote.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' style="width: 97%" data-options="required:true"></td>
				</tr>
				<tr>
					<th>发布范围(部门)</th>
					<td><input class="easyui-textbox" type="text" name="toDept" value="${pubVote.toDept }" ></td>
				</tr>
				<tr>
					<th>发布范围(职位)</th>
					<td><input class="easyui-textbox" type="text" name="toRank" value="${pubVote.toRank }"></td>
				</tr>
				<tr>
					<th>发布范围(个人)</th>
					<td><input class="easyui-textbox" type="text" name="toId" value="${pubVote.toId }"></td>
				</tr>
				<tr align="center" >
					<th colspan="2"><input type="button" onclick="checkInput()" value="提交投票"></th>
				</tr>
			</table>
			<table id="tab2" border="1" cellspacing="0" cellpadding="0"  style="float: left;width:253px">
				<tr align="center">
					<th colspan="3">点击按钮添加投票项输入</th>
				</tr>
			</table>
			<input type="button" id="additem" value="添加投票项"> 
			<input type="hidden" name="votename" id="votename">
		</form>
	</div>
	<script type="text/javascript">
		var count=$("#tab2 input[type='text']").length+1;
		var row = 1;//记录加载多少个投票项
		var ids = [];//用于保存所有输入的投票项

		$(function() {
			$("#votename").val('');
			//alert(1123);
			$("#additem")
					.click(
							function() {
								$("#votename").val(row);
								if (row <= 10) {
									var tr = "<tr name="+row+"><th>"
											+ count
											+ "</th><td><input id='"
											+ row
											+ "' class='easyui-validatebox' type='text' style='width:97%'  data-options='required:true'/></td><td><input type='button' value='删除' onclick='removeVote(" + row + ")'/></td></tr>";
									$("#tab2").append(tr);
									row++;
									count++;
								}
								//row是变化了的
								if (row > 10) {
									$("#additem").hide();
								}
							});
		});
		

		//设置表单属性
		$("#voteForm").form({
			url:"${pageContext.request.contextPath }/pubVote",
			onSubmit:function(){
				//提交前验证的代码
				//return false;则表单不会提交
			},
			//回调成功函数
			success:function(data){
				if(data>0){
					$("#voteView").window('close',true);
					$.messager.show({
						title:'提示信息',
						msg:'操作成功',
						timeout:3000
					});
				}else{
					$("#voteView").window('close',true);
					$.messager.show({
						title:'提示信息',
						msg:'操作失败!!',
						timeout:3000
					});
				}
			}
			
		});
		
		function checkInput() {
			//alert(123);
			if (row < 3) {
				alert("至少添加2个投票项!");
				return false;
			}
			$("#tab2 input[type='text']").each(function(){
				//alert($(this).val());
				var item=$(this).val();
				if(item.trim().length==0){
					$.messager.alert('提示信息','投票项不能为空');
					//alert('投票项不能为空');
					return false;
				}
			});
			$("#tab2 input[type='text']").each(function() {
				//alert($(this).val());
				ids.push($(this).val());//把输入的投票项存入数组中
			});
			var str = ids.join(",");//join方法,将数组每个元素按定义的分隔符拼接成字符串
			$("#votename").val(str);
			
			if($("#voteForm").form('validate')){
				$("#voteForm").form('submit');
			}
			$("#votename").val('');
			return true;
		}
		
		function removeVote(id) {
			$("#additem").show();
			$("#tab2 tr[name=" + id + "]").remove();
			//alert($("#tab2 input[type='text']").length);
			row=$("#tab2 input[type='text']").length+1;
		}
	</script>
</body>
</html>
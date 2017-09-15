<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>增加员工档案</title>
		<style>
			.input {
				width: 200px;
				height: 20px;
				border: 1px solid #95B8E7;
			}

			.btn {
				width: 100px;
				height: 20px;
				border: 1px solid #95B8E7;
			}
		</style>

		<%@ include file="../commons/meta.jsp"%>


	</head>

	<body>
		<form action="" method="post" id="empForm" name="empForm">
			<table id="empTable" style="margin: 10px auto;padding-top: 50px;">
				<tr>
					<td>
						<div align="right">
							姓名：
						</div>
					</td>
					<td>
						<input class="easyui-textbox" data-options="required:true" name="empName" value="${hrEmp.empName }"/>
						<input type="hidden" name="empId" value="${hrEmp.empId }" />
						<input type="hidden" name="workId" value="${hrEmp.workId}">
						<input type="hidden" name="status" value="${hrEmp.status}">
					</td>
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<select class="easyui-combobox" name="gender" style="width: 120px;">
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</td>
				</tr>				
				<tr>
					<td>
						<div align="right">
							出生日期：
						</div>
					</td>
					<td>
						<c:if test="${empty hrEmp }">
						<input type="text" name="birthday" class="easyui-datebox"/>
						</c:if>
						<c:if test="${!empty hrEmp }">
							<input type= "text" class= "easyui-datebox" name="birthday" value="<spring:eval expression="hrEmp.birthday"/>" />
						</c:if>
					</td>
					<td>
						<div align="right">
							民族：
						</div>
					</td>
					<td>
						<input type="text" name="nation" class="easyui-textbox" value="${hrEmp.nation}">
					</td>
					<td rowspan="11" style="vertical-align: top">
						<div id="uploader-demo">
							<!--用来存放item-->
							<div id="fileList" class="uploader-list"></div>
							<div id="filePicker" style="float: left;">选择图片</div>
							<button type="button" id="btnUpload">上传</button>
						</div>
					</td>
				</tr>

				<tr>
					<td>
						<div align="right">
							身份证：
						</div>
					</td>
					<td>
						<input type= "text" class= "easyui-textbox" name="idCard" value="${hrEmp.idCard}" />
					</td>
					<td>
						<div align="right">
							照片：
						</div>
					</td>
					<td>
						<c:if test="${!empty hrEmp}">
							<img src="${pageContext.request.contextPath }/resources/imgupload/${hrEmp.photo}" width="100" height="100">
						</c:if>
						<input id="up_photo" type="hidden" name="photo" value="${hrEmp.photo}">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							婚姻状况：
						</div>
					</td>
					<td>
						<select class="easyui-combobox" name="isMarry" style="width: 120px;">
							<option value="H001-1" ${hrEmp.isMarry=='H001-1'?'selected':''}>未婚</option>
							<option value="H001-1" ${hrEmp.isMarry=='H001-2'?'selected':''}>已婚</option>
							<option value="H001-2" ${hrEmp.isMarry=='H001-3'?'selected':''}>离异</option>
						</select>
					</td>
					<td>
						<div align="right">
							政治面貌：
						</div>
					</td>
					<td>
						<select class="easyui-combobox" name="politics" style="width: 120px;">
							<option value="Z003-1" ${hrEmp.politics=='Z003-1'?'selected':''}>群众</option>
							<option value="Z003-2" ${hrEmp.politics=='Z003-2'?'selected':''}>团员</option>
							<option value="Z003-3" ${hrEmp.politics=='Z003-3'?'selected':''}>预备党员</option>
							<option value="Z003-4" ${hrEmp.politics=='Z003-4'?'selected':''}>党员</option>
						</select>
					</td>
				</tr>

				<tr>
					<td>
						<div align="right">
							学历：
						</div>
					</td>
					<td>
						<select class="easyui-combobox" name="diploma" style="width: 120px;">
							<option value="X001-1" ${hrEmp.diploma=='X001-1'?'selected':''}>小学</option>
							<option value="X001-2" ${hrEmp.diploma=='X001-2'?'selected':''}>初中</option>
							<option value="X001-3" ${hrEmp.diploma=='X001-3'?'selected':''}>高中</option>
							<option value="X001-4" ${hrEmp.diploma=='X001-4'?'selected':''}>大专</option>
							<option value="X001-5" ${hrEmp.diploma=='X001-5'?'selected':''}>本科</option>
							<option value="X001-6" ${hrEmp.diploma=='X001-6'?'selected':''}>研究生</option>
							<option value="X001-7" ${hrEmp.diploma=='X001-7'?'selected':''}>博士</option>
						</select>
					</td>
					<td>
						<div align="right">
							职称：
						</div>
					</td>
					<td>
						<select class="easyui-combobox" name="title" style="width: 120px;">
							<option value="Z004-1" ${hrEmp.title=='Z004-1'?'selected':''}>助理工程师</option>
							<option value="Z004-2" ${hrEmp.title=='Z004-2'?'selected':''}>工程师</option>
							<option value="Z004-3" ${hrEmp.title=='Z004-3'?'selected':''}>高级工程师</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							毕业院校：
						</div>
					</td>
					<td>
						<input type="text" name="university" class="easyui-textbox" value="${hrEmp.university}">
					</td>
					<td>
						<div align="right">
							专业：
						</div>
					</td>
					<td>
						<input type="text" name="speciality" class="easyui-textbox" value="${hrEmp.speciality}">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							职务：
						</div>
					</td>
					<td>
						<select class="easyui-combobox" name="rank" style="width: 120px;">
							<option value="Z005-1" ${hrEmp.rank=='Z005-1'?'selected':''}>职员</option>
							<option value="Z005-2" ${hrEmp.rank=='Z005-2'?'selected':''}>部门经理</option>
							<option value="Z005-3" ${hrEmp.rank=='Z005-3'?'selected':''}>分公司经理</option>
							<option value="Z005-4" ${hrEmp.rank=='Z005-4'?'selected':''}>总经理</option>
							<option value="Z005-5" ${hrEmp.rank=='Z005-5'?'selected':''}>总裁</option>
						</select>
					</td>
					<td>
						<div align="right">
							入职日期：
						</div>
					</td>
					<td>
						<c:if test="${empty hrEmp }">
							<input type= "text" class= "easyui-datebox" name="joinDate" />
						</c:if>
						<c:if test="${!empty hrEmp }">
							<input type= "text" class= "easyui-datebox" name="joinDate" value="<spring:eval expression="hrEmp.joinDate"/>" />
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							家庭电话：
						</div>
					</td>
					<td>
						<input type="text" name="telephone" class="easyui-textbox" value="${hrEmp.telephone}">
					</td>
					<td>
						<div align="right">
							手机：
						</div>
					</td>
					<td>
						<input type="text" name="cellphone" class="easyui-textbox" value="${hrEmp.cellphone}">
					</td>
				</tr>
								
				<tr>
					<td>
						<div align="right">
							部门：
						</div>
					</td>
					<td>
						<input id="dept" class="easyui-combobox" name="hrDept.deptId" value="${hrEmp.hrDept.deptId }"
							   data-options="editable:false,valueField:'deptId',textField:'deptName',url:'hrDeptList'"/>
					</td>
					<td>
						<div align="right">
							邮件：
						</div>
					</td>
					<td>
						<input type="email" class="easyui-textbox" name="email" value="${hrEmp.email}">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							家庭详细地址：
						</div>
					</td>
					<td colspan="3">
						<input type="text" name="address" class="easyui-textbox" value="${hrEmp.address}">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							备注：
						</div>
					</td>
					<td colspan="3">
						<input type="text" name="remark" class="easyui-textbox" value="${hrEmp.remark}">
					</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						<div align="center">
							<input type="submit" value="保存" />
							<input type="reset" value="重置" />
						</div>
					</td>
					<td height="20">
						&nbsp;
					</td>
				</tr>
			</table>
		</form>


		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/webuploader/webuploader.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/resources/webuploader/webuploader.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/upload.js"></script>

		<script type="text/javascript">
            $("#empForm").form( {
				url : "empController_save",
				success : function(data) {
					if (data) {
						$.messager.show( {
							title : "提示",
							msg : "员工档案保存成功!"
						});
						$("#editEmp").window("close",true);
					}
				}
			});
		</script>

	</body>
</html>

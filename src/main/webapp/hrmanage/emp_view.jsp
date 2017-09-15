<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>员工档案</title>
		<%@ include file="../commons/meta.jsp"%>

	</head>

	<body>
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
			<table id="empTable" style="margin: 10px auto;padding-top: 50px;width: 550px;">
				<tr>
					<td>
						<div align="right">
							姓名：
						</div>
					</td>
					<td>
						${hrEmp.empName }
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
						<c:if test="${hrEmp.gender==1}">
							男
						</c:if>
						<c:if test="${hrEmp.gender==0}">
							女
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							工号：
						</div>
					</td>
					<td colspan="3">
						${hrEmp.workId}
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							出生日期：
						</div>
					</td>
					<td>
						<spring:eval expression="hrEmp.birthday"/>

					</td>
					<td>
						<div align="right">
							民族：
						</div>
					</td>
					<td>
						${hrEmp.nation}
					</td>
				</tr>

				<tr>
					<td>
						<div align="right">
							身份证：
						</div>
					</td>
					<td>
						${hrEmp.idCard}
					</td>
					<td>
						<div align="right">
							照片：
						</div>
					</td>
					<td>
						<img src="${pageContext.request.contextPath }/resources/upload/${hrEmp.photo}" width="100" height="100">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							婚姻状况：
						</div>
					</td>
					<td>
						<c:if test="${hrEmp.isMarry=='H001-1'}">
							未婚
						</c:if>
						<c:if test="${hrEmp.isMarry=='H001-2'}">
							已婚
						</c:if>
						<c:if test="${hrEmp.isMarry=='H001-3'}">
							离异
						</c:if>
					</td>
					<td>
						<div align="right">
							政治面貌：
						</div>
					</td>
					<td>
						<c:if test="${hrEmp.politics=='Z003-1'}">
							群众
						</c:if>
						<c:if test="${hrEmp.politics=='Z003-2'}">
							团员
						</c:if>
						<c:if test="${hrEmp.politics=='Z003-3'}">
							预备党员
						</c:if>
						<c:if test="${hrEmp.politics=='Z003-4'}">
							党员
						</c:if>
					</td>
				</tr>

				<tr>
					<td>
						<div align="right">
							学历：
						</div>
					</td>
					<td>
						<c:if test="${hrEmp.diploma=='X001-1'}">
							小学
						</c:if>
						<c:if test="${hrEmp.diploma=='X001-2'}">
							初中
						</c:if>
						<c:if test="${hrEmp.diploma=='X001-3'}">
							高中
						</c:if>
						<c:if test="${hrEmp.diploma=='X001-4'}">
							大专
						</c:if>
						<c:if test="${hrEmp.diploma=='X001-5'}">
							本科
						</c:if>
						<c:if test="${hrEmp.diploma=='X001-6'}">
							研究生
						</c:if>
						<c:if test="${hrEmp.diploma=='X001-7'}">
							博士
						</c:if>
					</td>
					<td>
						<div align="right">
							职称：
						</div>
					</td>
					<td>
						<c:if test="${hrEmp.title=='Z004-1'}">
							助理工程师
						</c:if>
						<c:if test="${hrEmp.title=='Z004-2'}">
							工程师
						</c:if>
						<c:if test="${hrEmp.title=='Z004-3'}">
							高级工程师
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							毕业院校：
						</div>
					</td>
					<td>
						${hrEmp.university}
					</td>
					<td>
						<div align="right">
							专业：
						</div>
					</td>
					<td>
						${hrEmp.speciality}
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							职务：
						</div>
					</td>
					<td>
						<c:if test="${hrEmp.rank=='Z005-1'}">
							职员
						</c:if>
						<c:if test="${hrEmp.rank=='Z005-2'}">
							部门经理
						</c:if>
						<c:if test="${hrEmp.rank=='Z005-3'}">
							分公司经理
						</c:if>
						<c:if test="${hrEmp.rank=='Z005-4'}">
							总经理
						</c:if>
						<c:if test="${hrEmp.rank=='Z005-5'}">
							总裁
						</c:if>
					</td>
					<td>
						<div align="right">
							入职日期：
						</div>
					</td>
					<td>
						<spring:eval expression="hrEmp.joinDate"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							家庭电话：
						</div>
					</td>
					<td>
						${hrEmp.telephone}
					</td>
					<td>
						<div align="right">
							手机：
						</div>
					</td>
					<td>
						${hrEmp.cellphone}
					</td>
				</tr>
								
				<tr>
					<td>
						<div align="right">
							部门：
						</div>
					</td>
					<td>
						${hrEmp.hrDept.deptName }
					</td>
					<td>
						<div align="right">
							邮箱：
						</div>
					</td>
					<td>
						${hrEmp.email}
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							家庭详细地址：
						</div>
					</td>
					<td colspan="3">
						${hrEmp.address}
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							备注：
						</div>
					</td>
					<td colspan="3">
						${hrEmp.remark}
					</td>
				</tr>
			</table>
	</body>
</html>

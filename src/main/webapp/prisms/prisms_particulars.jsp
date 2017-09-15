<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE HTML>
<html>
  <head>
   <meta charset="utf-8"> 
    <title>短信详情</title>
    <script src="${pageContext.request.contextPath }/resources/js/jquery-2.2.2.js"></script>
	<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
  </head>
  
  <body>
 	<div style="width:500px;margin:auto;">
    	<p>发信人：${user.userTrueName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发送时间：<fmt:formatDate value="${priSms.sendTime }" pattern="yyyy-MM-dd"/></p>
    	<br><br>
        <p>短信内容：${priSms.content }</p>
    	<br>
    	<br>
    	<br>
    	<br>
    	<p>短信类型：${priSms.smsType }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="removePriSms(${priSms.smsId })">删除</button></p>
    	
    </div>
    
    <script type="text/javascript">
    	function removePriSms(id){
    		$.ajax({
    			url : 'removePriSms',
    			type : 'post',
    			data : {'smsId':id},
    			success : function(data){
    				$.messager.show({
						title : '提示信息',
						msg : data+'条信息删除成功',
						timeout : 3000
					})
					$("#prisms").window('close',true)
    			}
    		});
    	}
    </script>
  </body>
</html>

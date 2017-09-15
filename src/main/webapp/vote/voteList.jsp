<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>终止投票列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css">
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north',title:'高级查询'"
			style="height: 100px; padding: 20px">
			主题名:<input type="text"  class="easyui-textbox" placeholder="输入主题名" id="subject3"> <label>生产日期:</label>
			<input id="beginDate3" class="easyui-datetimebox">~ <input
				id="endDate3" class="easyui-datetimebox"><input type="hidden"
				id="qwer3"> <input type="hidden" value="${param.voteStatus}">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search"
				onclick="advPubVote3()">查询</a>
		</div>
		<div data-options="region:'center'">
			<div id="editTool3">
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add"
					onclick="editVote('add')" plain="true">添加</a> -->
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editVote('update')" plain="true">修改</a> -->
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteEmp()" plain="true">删除</a> -->
			</div>
			<table id="dgVote3"></table>
			<div id="voteView3"></div>
		</div>
	</div>
	<div id="dv1"></div>
	<script type="text/javascript">
  	var editRow=undefined;//用于记录
  	$(function(){
  		//alert(${param.voteStatus}); 
  		$("#dgVote3").datagrid({
  			url:"${pageContext.request.contextPath }/pubVotes",
  			title:'员工信息表',
  			nowrap:true,//不自动换行
  			striped:true,//设置奇偶行效果
  			rownumbers:true,
  			columns:[[
  	          {field:'voteId','title':'编号',width:80,sortable:true,hidden:true},
  	          {field:'subject','title':'主题',width:80,sortable:true},
  	          {field:'createTime','title':'创建时间',width:100,sortable:true},
  	          {field:'endTime','title':'终止日期',width:150,sortable:true},
  	          {field:'voteStatus','title':'状态',width:80,sortable:true,
  	        	formatter:function(value,rowData,rowIndex){
  	        		if(value==2){
  	        			return "生效";
  	        		}else{
  	        			return "终止";
  	        		}
  	        	}
  	          },
  	          {field:"op",title:"操作",width:220,
  		        //格式化函数，处理单元格显示的内容
  		    	formatter:function(value,rowData,rowIndex){
  		    		var voteId=rowData.voteId;
  		    		var subject3=rowData.subject3;
  		    		return "<button onclick=\"findVote3("+voteId+")\">详细信息</button><button onclick=\"showVote3("+voteId+")\">图表信息</button>";//<button onclick=\"updateStatus('"+subject3+"',"+voteId+")\">修改状态</button>
  		    		
  		    	}}
  			]],
  			pagination:true,//显示分页工具栏
  			pageSize:10,
  			pageList:[2,5,10,20],
  			sortName:"voteId",
  			sortOrder:"desc",
  			toolbar:"#editTool3",
  			queryParams: {
  				voteStatus:'${param.voteStatus}'
  			}
  		});


//  		$("#cc").combobox({
//  			url:"empservlet?action=combobox",
//  			valueField:'id',
//  			textField:'text'
//  		});
  		// -- 设置今天之后的日期不能选择 --------------------
  		/* $("#beginDate3").datetimebox("calendar").calendar({
  			//firstDay: 1  
  			validator : function(date) {
  				var now = new Date();
  				// var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
  				var d2 = new Date(now.getFullYear(),
  					now.getMonth(), now.getDate());
  				// return date >= d1 && date <= d2;
  				return date <= d2;
  			}
  		})

  		$("#endDate3").datetimebox("calendar").calendar({
  			//firstDay: 2  
  			validator : function(date) {
  				var now = new Date();
  				//alert($("#beginDate3").datebox('getValue'));
  				var str=$("#beginDate3").datebox('getValue');
  				var year=0,month=0,day=0;
  				var d2 = new Date(now.getFullYear(),
  						now.getMonth(), now.getDate());
  				if($("#beginDate3").datebox('getValue').trim().length>0){
  					str=str.replace('-', '');
  					str=str.replace('-', '');
  					year=parseInt(str.substring(0,4));
  					month=parseInt(str.substring(4,6));
  					day=parseInt(str.substring(6,8));
  					var d3=new Date(year,month-1,day);
  					$("#qwer3").val(str);
  					return date>d3 && date <=d2;
  				}
  				return date <= d2;
  			}
  		}) */
  		// -----------------------------------------	
  		
  	});


  	/*
  	*	查询按钮事件
  	*/
  	function advPubVote3(){
  		//向后台传参数ename的值，并重新加载和显示第一页数据
  		$("#dgVote3").datagrid('load',{
  				'voteStatus':'${param.voteStatus}',
				'subject':$.trim($("#subject3").val()),
				'beginDate':$("#beginDate3").datebox('getValue'),
				'endDate':$("#endDate3").datebox('getValue')
		});
  	}


  	//删除记录
  	function deleteEmp(){
  		//获取所有选中行的数组
  		var rows=$("#dgEmp").datagrid('getSelections');
  		if(rows.length==0){
  			$.messager.alert("警告","请选择要删除的行","warning");
  			return;
  		}
  		
  		var ids=[];//定义用于保存所有选中行empno值的数组
  		//遍历选中行数组
  		$.each(rows,function(){
  			ids.push(this.empno);//把每行的empno值存入数组
  		});
  		
  		//alert(ids);
  		$.messager.confirm("提示信息","确定要删除吗?",function(flag){
  			//当选择确定时，执行删除
  			if(flag){
  				$.ajax({
  					url:"emp?action=delete",
  					//join方法，将数组每个元素按定义的分隔符拼接成字符串
  					data:{'ids':ids.join(",")},
  					type:"GET",
  					beforeSend:function(){
  						//进入数据加载状态
  						$("#dgEmp").datagrid("loading");
  					},
  					success:function(data){
  						//结束加载状态
  						$("#dgEmp").datagrid("loaded");
  						//加载第一页数据
  						$("#dgEmp").datagrid("load");
  						$.messager.show({
  							title:"提示信息",
  							msg:data+"个员工信息删除成功"
  						});
  						
  					}
  				});
  			}
  		});

  	}
  	
  	//修改状态
  	function updateStatus(subject3,voteId){
  		//var subject3="1234";
  		$.messager.confirm('提示信息','你要终止'+subject3+'投票吗?',function(flag){
  			if(flag){
  				$.ajax({
  					url:"updateVote",
  					//join方法，将数组每个元素按定义的分隔符拼接成字符串
  					data:{'voteId':voteId},
  					type:"post",
  					beforeSend:function(){
  						//进入数据加载状态
  						$("#dgVote3").datagrid("loading");
  					},
  					success:function(data){
  						//结束加载状态
  						$("#dgVote3").datagrid("loaded");
  						//加载第一页数据
  						$("#dgVote3").datagrid("load");
  						$.messager.show({
  							title:"提示信息",
  							msg:data.count+"个投票状态修改成功"
  						});
  						
  					}
  				});
  			}
  		});
  	}
	
  	//查询单个信息	
  	function findVote3(id){
  		//弹出新窗体
  		$("#voteView3").window({
  			title:"查看详细信息",
  			width:530,
  			height:300,
  			modal:true,
  			minimizable:false,
  			href:"${pageContext.request.contextPath }/findById?voteId="+id,
  			onClose:function(){
				//当窗体关闭，数据表格重载当前页数据
				$("#dgVote3").datagrid("reload");
			}
  		});
  	}
  	//查询图表信息
  	function showVote3(id){
  		//弹出新窗体
  		$("#voteView3").window({
  			title:"查看详细信息",
  			width:800,
  			height:600,
  			modal:true,
  			minimizable:false,
  			href:"${pageContext.request.contextPath }/vote/list3.jsp?voteId="+id,
  			onClose:function(){
				//当窗体关闭，数据表格重载当前页数据
				$("#dgVote3").datagrid("reload");
			}
  		});
  	}
  	
  	
  	
  	//添加或修改用户信息
  	function editVote(state){
  		var url="";
  		var title="";
  		
  		if(state=="add"){
  			url="${pageContext.request.contextPath }/vote/newVote.jsp";
  			title="添加投票";
  		}else{
  			//获取所有选中行的数组
  			var rows=$("#dgVote3").datagrid('getSelections');
  			
  			if(rows.length!=1){
  				$.messager.alert("警告","请选择一行进行修改","warning");
  				return;
  			}
  			
  			title="修改用户信息";
  			url="${pageContext.request.contextPath }/findById?voteId="+rows[0].voteId;		
  		}
  		
  		//弹出新窗体
  			$("#voteView3").window({
  				title:title,
  				width:607,
  				height:400,
  				modal:true,
  				minimizable:false,
  				href:url,
  				onClose:function(){
  					//当窗体关闭，数据表格重载当前页数据
  					$("#dgVote3").datagrid("reload");
  				}
  			});
  		
  	}
  	</script>
</body>
</html>
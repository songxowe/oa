$(function() {
	
	var myChart = echarts.init(document.getElementById('main6'),'macarons');
	var worldMapContainer = document.getElementById('main6');
	
	// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
//	var resizeWorldMapContainer = function() {
//		worldMapContainer.style.width = window.innerWidth * 0.6 + 'px';
//		//worldMapContainer.style.height = window.innerHeight * 0.6 + 'px';
//		worldMapContainer.style.height =$("#div6").css("height");
//	};
	// 设置容器高宽
	//resizeWorldMapContainer();

	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			//text : '异步数据加载示例'
			text : '投票选举'
		},
		tooltip : {},
		toolbox: {  
            //show: true,  
            itemSize: 20,  
            itemGap: 30,  
            right: 50,  
            feature: {  
                dataView: {show:true},  
                saveAsImage: {  
                    //excludeComponents :['toolbox'],  
                    pixelRatio: 2  
                }  
            }  
		} ,
		legend : {
			data : [ '票数' ]
		},
		xAxis : {
			data : []
		},
		yAxis : {},
		series : [ {
			name : '票数',
			type : 'bar',
			data : []
		} ]
	}, true);
	var xrr = new Array();
	var yrr = new Array();
	// ajax
	$.ajax({
				type : "get",
				//async : false, // 同步执行
				url : "../list",
				data : {
					'voteId' : $("#voteId6").val()
				},
				dataType : "json", // 返回数据形式为json
				cache:false,//设置为 false 将不缓存此页面。
				success : function(data) {
					var sum="";
					var subject=data.pv.subject;
					if (data.json) {
						$.each(
								data.json,
								function(i) {
									
									yrr.push(this.voteCount);
									xrr.push(this.title);
									var str = '';
									if(data.mark==1){
										if(i==0){
											str = "<input type='radio' name='subId' value='"
												+ this.subId
												+ "' checked/>"
												+ this.title
												+ "<br/>";
										}else{
											str = "<input type='radio' name='subId' value='"
													+ this.subId
													+ "' />"
													+ this.title
													+ "<br>";
										}
											sum += str;
									}
								});
						if(data.mark==1){
							var st = "<input type='button' id='goVote6' value='选择一个投票' />";
							sum += st;
							$("#myform6").append(sum);
						}
						
						$("#goVote6").click(function(){
							$("#myform6").form('submit');
						});
						
						
						// 填入数据
						myChart.setOption({
							title : {
								text : subject
							},
							xAxis : {
								data : xrr
							},
							series : [ {
								// 根据名字对应到相应的系列
								name : '票数',
								data : yrr
							} ]
						});
					}
				},
				error : function(errorMsg) {
					alert("不好意思,图表请求数据失败啦!");
					myChart.hideLoading();
				}
			});
	
	
	
	
	//设置表单属性
	$("#myform6").form({
		url:"http://localhost:8080/OA2/update",
		onSubmit:function(){
			//提交前验证的代码
			//return false;则表单不会提交
		},
		//回调成功函数
		success:function(data){
			if(data>0){
				$("#voteView4").window('close',true);
				$.messager.show({
					title:'提示信息',
					msg:'操作成功',
					timeout:3000
				});
			}else{
				$("#voteView4").window('close',true);
				$.messager.show({
					title:'提示信息',
					msg:'操作失败!!',
					timeout:3000
				});
			}
		}
		
	});
	
	
	
	// 用于使chart自适应高度和宽度
	/*window.onresize = function() {
		// 重置容器高宽
		resizeWorldMapContainer();
		myChart.resize();
	};*/
});
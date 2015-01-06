    <script type="text/javascript">
	
	


//图形大小
	var r='80';
	var centerx='50%';
    var centery='50%';
        // 路径配置
    require.config({
            paths:  {
                echarts: 'dist'
			}
	});   
	 require(
				[
					'echarts',
					'echarts/chart/pie'// 使用柱状图就加载bar模块，按需加载
				],
     function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('echarttestresult'));           
                var option = {				
    //鼠标浮窗提示
	tooltip : {
        show: false,
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    //图例
    legend: {
        orient : 'horizontal',
        x : 'left',
		 y:'bottom' , 
        data:['执行成功','执行失败','跳过执行']
    },
    //工具栏
    toolbox: {
        show : true,
        feature : {
            dataView : {show: true, readOnly: false},
		magicType: {
        show : true,       
        type : [ ]
    },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : false,
	//统计
    series : [
        {
            name:'执行情况汇总',
            type:'pie',
			//中心位置
            center : [centerx,centery],
			selectedMode: 'single',
			//大小
            radius : r*0.6,
            itemStyle : {
                normal : {
                    label : {
                        position : 'inner',
                        formatter : function (a,b,c,d) {return (d - 0).toFixed(0) + '%'},
						textStyle: {
										 color: '#0000FF'
								   }
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        formatter : "{b}\n{d}%",
						textStyle: {
										 color: '#0000FF'
								   }
                    }
                }           
            },
            data:[
				  #if ($totalPassed > 0)
                {value:$totalPassed, name:'执行成功',itemStyle : { normal : { color: '#33FF99' },emphasis : {color: '#99FF00'}} },
				#end
				 #if ($totalFailed > 0)
                {value:$totalFailed, name:'执行失败',itemStyle : { normal : { color: '#FF6633' },emphasis : {color: '#FF3366'}} },
				#end
				  #if ($totalSkipped > 0)
                {value:$totalSkipped, name:'跳过执行',itemStyle : { normal : { color: '#FFFF99' },emphasis : {color: '#FFFF33'}} }
				#end
            ],
				markPoint : {
					symbol:'diamond'  ,
					tooltip : {
							show: true,
							formatter: "{a} <br/>{b} : {c}"
						},
					data : [
						{name : '用例总数', value : $totalTests, x:'10%', y:40, symbolSize:20,
						
						effect :{
									show: true, 
									loop: true, 
									period: 15, 
									scaleSize : 1, 
									color : null, 
									shadowColor : null, 
									shadowBlur : 0 
								
								},
					itemStyle : { normal : { color: '#330099'},emphasis : { label : {}}}
						}
					]
				 }
  
				
        },
		//tests统计
        {
            name:'用例分集统计',
            type:'pie',
            center : [centerx, centery],
            radius : [r*0.65, r],
			itemStyle : {
                normal : {
                    label : {
						 show : false,
                        position : 'inner',
                        formatter : function (a,b,c,d) {return (d - 0).toFixed(0) + '%'}
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        formatter : "{b}\n{d}%",
						textStyle: {
							 color: '#0000FF'
								   }
                    }
                }
                
            },
            data:[
            
             #foreach ($result in $suite.results)
             	 #if ($result.testContext.passedTests.size() > 0)
  					 {value:$result.testContext.passedTests.size(), name:'${result.testContext.name}'},
   				 #end
             #end
             
             #foreach ($result in $suite.results)
             	 #if ($result.testContext.failedTests.size() > 0)
  					 {value:$result.testContext.failedTests.size(), name:'${result.testContext.name}'},
   				 #end
             #end
             
             #foreach ($result in $suite.results)
             	 #if ($result.testContext.skippedTests.size() > 0)
  					 {value:$result.testContext.skippedTests.size(), name:'${result.testContext.name}'},
   				 #end
             #end               
            ]
  
        }
		 ]// series : end
};//option end
                    
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
				window.onresize = myChart.resize 
            }//function (ec) end
        );// require end


    </script>
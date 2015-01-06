    <script type="text/javascript">
	
	
  #set ($suiteId = $velocityCount)
  #set ($totalTests = 0)
  #set ($totalPassed = 0)
  #set ($totalSkipped = 0)
  #set ($totalFailed = 0)
  
  #set ($notPassedTests = $result.testContext.skippedTests.size() + $result.testContext.failedTests.size())
  #set ($total = $result.testContext.passedTests.size() + $notPassedTests)
  #set ($totalTests = $totalTests + $total)
  #set ($totalPassed = $totalPassed + $result.testContext.passedTests.size())
  #set ($totalSkipped = $totalSkipped + $result.testContext.skippedTests.size())
  #set ($totalFailed = $totalFailed + $result.testContext.failedTests.size())

//ͼ�δ�С
	var r='80';
	var centerx='50%';
    var centery='50%';
        // ·������
    require.config({
            paths:  {
                echarts: 'dist'
			}
	});   
	 require(
				[
					'echarts',
					'echarts/chart/pie'// ʹ����״ͼ�ͼ���barģ�飬�������
				],
     function (ec) {
                // ����׼���õ�dom����ʼ��echartsͼ��
                var myChart = ec.init(document.getElementById('echarttestresult'));           
                var option = {				
    //��긡����ʾ
	tooltip : {
        show: false,
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    //ͼ��
    legend: {
        orient : 'horizontal',
        x : 'left',
		 y:'bottom' , 
        data:['ִ�гɹ�','ִ��ʧ��','����ִ��']
    },
    //������
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
	//ͳ��
    series : [
        {
            name:'ִ���������',
            type:'pie',
			//����λ��
            center : [centerx,centery],
			selectedMode: 'single',
			//��С
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
                {value:$totalPassed, name:'ִ�гɹ�',itemStyle : { normal : { color: '#33FF99' },emphasis : {color: '#99FF00'}} },
                {value:$totalFailed, name:'ִ��ʧ��',itemStyle : { normal : { color: '#FF6633' },emphasis : {color: '#FF3366'}} },
                {value:$totalSkipped, name:'����ִ��',itemStyle : { normal : { color: '#FFFF99' },emphasis : {color: '#FFFF33'}} }
            ],
				markPoint : {
					symbol:'diamond'  ,
					tooltip : {
							show: true,
							formatter: "{a} <br/>{b} : {c}"
						},
					data : [
						{name : '��������', value : $totalTests, x:'10%', y:40, symbolSize:20,
						
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
		//testsͳ��
        {
            name:'�����ּ�ͳ��',
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
  
        },
		//class-groupͳ��
		       {
            name:'��������/��ͳ��',
            type:'pie',
            center : [centerx, centery],
            radius : [r*1.005, r*1.3],
	
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
            
            #foreach ($testResult in $classResults)
            #end
                {value:test1p, name:'test1�ɹ���'},
                {value:test2p, name:'test2�ɹ���'},
                {value:test3p, name:'test3�ɹ���'},
				{value:test1f, name:'test1ʧ����'},
                {value:test2f, name:'test2ʧ����'},
                {value:test3f, name:'test3ʧ����'},
				{value:test1s, name:'test1������'},
                {value:test2s, name:'test2������'},
                {value:test3s, name:'test3������'},
                
            ]
			}
		 ]// series : end
};//option end
                    
        
                // Ϊecharts����������� 
                myChart.setOption(option); 
				window.onresize = myChart.resize 
            }//function (ec) end
        );// require end


    </script>
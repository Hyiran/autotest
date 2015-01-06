    <script type="text/javascript">
	
	


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
				  #if ($totalPassed > 0)
                {value:$totalPassed, name:'ִ�гɹ�',itemStyle : { normal : { color: '#33FF99' },emphasis : {color: '#99FF00'}} },
				#end
				 #if ($totalFailed > 0)
                {value:$totalFailed, name:'ִ��ʧ��',itemStyle : { normal : { color: '#FF6633' },emphasis : {color: '#FF3366'}} },
				#end
				  #if ($totalSkipped > 0)
                {value:$totalSkipped, name:'����ִ��',itemStyle : { normal : { color: '#FFFF99' },emphasis : {color: '#FFFF33'}} }
				#end
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
  
        }
		 ]// series : end
};//option end
                    
        
                // Ϊecharts����������� 
                myChart.setOption(option); 
				window.onresize = myChart.resize 
            }//function (ec) end
        );// require end


    </script>
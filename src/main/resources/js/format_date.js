	layui.use([ 'form', 'layedit', 'laydate' ], function() {
		var form = laydate = layui.laydate;
		 //年选择器
		  laydate.render({
		    elem: '#year'
		    ,type: 'year'
		  });
		  
		  //年月选择器
		  laydate.render({
		    elem: '#yearMonth'
		    ,type: 'month'
		  });

		  
		//选择年月日
		laydate.render({
			elem : '#yearMonthDay'
		});
		
		//选择年月日 时分秒-年月日 时分秒
		laydate.render({
			elem : '#yearMonthDayAndHourMinuteSecond'
			,type : 'datetime'
		});
		
		//选择年月日 时分秒-年月日 时分秒
		laydate.render({
			elem : '#yearMonthDayAndHourMinuteSecond2'
				,type : 'datetime'
		});
		
		//选择时分秒
		laydate.render({ 
			  elem: '#hourMinuteSecond'
			  ,type: 'time'
			
				
			});
		//, format: 'HH:mm'
	});
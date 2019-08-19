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
			,max : getNowFormatDate()
		});
		
		//选择年月日 时分秒-年月日 时分秒
		laydate.render({
			elem : '#yearMonthDayAndHourMinuteSecond2'
			,type : 'datetime'
			,max : getNowFormatDate()
		});
		
		//选择时分秒
		laydate.render({ 
			  elem: '#hourMinuteSecond'
			  ,type: 'time'
			});
		//, format: 'HH:mm'
	});
	
	
	
	function getNowFormatDate(){
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		var hours = date.getHours();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		strDate += 1;
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		if(hours >= 0 && hours <= 10){
			hours = "0" + hours;
		}
		var currentdate = date.getFullYear() + seperator1 + month
				+ seperator1 + strDate + " " + hours; 
		
		return currentdate;
	}
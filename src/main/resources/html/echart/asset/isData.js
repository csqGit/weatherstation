
// 创建图形对象并添加到main中
function setChartOption(xOldData, yOldData, xName, yName, title,
		formDate) {// x轴坐标数据 y轴坐标数据 x轴名称 y轴名称
	var intervalValue;// 数据点区间
	var xDataCount = 0; // 记录添加数据的次数
	var dayCount;// 判断查询的日期区间总共有多少天
		// （（前一个月的总天数-已经过的天数）+最后一个月的天数）计算总共生成多少个点
	var xData = [];// 存储X轴的数据
	var yData = [];// 存储Y轴的数据
	var len = 0;// 循环的次数
	var count = 60;// dayCount天产生的条数
	// 计算开始时间
	var oldFirstYear;// 起始时间的年
	var oldFirstMonth;// 起始时间的月
	var oldFirstDay;// 起始时间的日
	var oldFirstHour;
	var oldFirstMinute;
	var oldLastYear;// 终止时间的年
	var oldLastMonth;// 终止时间的月
	var oldLastDay;// 终止时间的月
	var oldLastHour;
	var oldLastMinute;
	var dateYear;// 创建日期对象的第一个参数
	var dateMonth;// 创建日期对象的第二个参数
	var dateDay;// 创建日期对象的第三个参数
	var dateHour, dateMinute = 0;
	var xoldDataFirst;// X轴的开始坐标
	var xoldDataLast;// X轴的终止坐标
	
	if (xOldData.length > 0) {
		// 取出查询的实际数据的第一个数据和最后一个数据,作为真是数据的集合点，并在此区间生成图形，其余各区间为空
		var startTime = xOldData[0];
		var endTime = xOldData[xOldData.length - 1];
		if (typeof formDate.yearMonthDayAndHourMinuteSecond != 'undefined') {// 表示根据需求查询，确定时间范围
			var timeArr = formDate.yearMonthDayAndHourMinuteSecond.split("~");
			xoldDataFirst = timeArr[0];
			xoldDataLast = timeArr[1];
			// 计算开始时间
			oldFirstYear = (parseInt(xoldDataFirst.substring(0, 4)));// 起始时间的年
			oldFirstMonth = (parseInt(xoldDataFirst.substring(5, 7)));// 起始时间的月
			oldFirstDay = (parseInt(xoldDataFirst.substring(8, 10)));// 起始时间的日
			oldFirstHour = (parseInt(xoldDataFirst.substring(11, 13)));
			oldFirstMinute = (parseInt(xoldDataFirst.substring(14, 16)));
			oldLastYear = (parseInt(xoldDataLast.substring(0, 4)));// 终止时间的年
			oldLastMonth = (parseInt(xoldDataLast.substring(5, 7)));// 终止时间的月
			oldLastDay = (parseInt(xoldDataLast.substring(8, 10)));// 终止时间的月
			oldLastHour = (parseInt(xoldDataLast.substring(11, 13)));
			oldLastMinute = (parseInt(xoldDataLast.substring(14, 16)));
		} else {// 表示默认进入当前页面，时间为当天时间到现在
			oldFirstYear = (parseInt(xOldData[0].substring(0, 4)));// 起始时间的年
			oldFirstMonth = (parseInt(xOldData[0].substring(5, 7)));// 起始时间的月
			oldFirstDay = (parseInt(xOldData[0].substring(8, 10)));// 起始时间的日
			oldFirstHour = (parseInt(xOldData[0].substring(11, 13)));
			oldFirstMinute = (parseInt(xOldData[0].substring(14, 16)));
			oldLastYear = (parseInt(xOldData[xOldData.length - 1].substring(0, 4)));// 终止时间的年
			oldLastMonth = (parseInt(xOldData[xOldData.length - 1].substring(5, 7)));// 终止时间的月
			oldLastDay = (parseInt(xOldData[xOldData.length - 1].substring(8, 10)));// 终止时间的月
			oldLastHour = (parseInt(xOldData[xOldData.length - 1].substring(11, 13)));
			oldLastMinute = (parseInt(xOldData[xOldData.length - 1].substring(14, 16)));
		}
		dateYear = oldFirstYear;// 创建日期对象的第一个参数
		dateMonth = oldFirstMonth - 1;// 创建日期对象的第二个参数
		dateDay = oldFirstDay;// 创建日期对象的第三个参数
		//计算X轴的数据点总数
		count = ((oldLastYear - oldFirstYear) * 365 + (oldLastMonth - oldFirstMonth) * 31 
				+ (oldLastDay - oldFirstDay)) * 24 * 60 + (oldLastHour - oldFirstHour) * 60 
				+ (oldLastMinute - oldFirstMinute);

		if (count > 60) {// X轴总共显示60个点 数据点的区间为点的总数/X轴的显示总数
			intervalValue = count / 30;
		} else {
			intervalValue = 1;
			count = 60;
		}
		intervalValue = parseInt(intervalValue);
		
		while (len < count) {// 因为1天产生24各点，所以判断总共有多少天就循环多少次*24，表示生成多少各点
			var date = new Date(dateYear, dateMonth, dateDay, oldFirstHour, len)// 年/月/日/时
			var month = date.getMonth();
			var day = date.getDate();
			var hours = date.getHours();
			var minute = date.getMinutes();
			if (month < 10)
				month = "0" + (month + 1);
			if (day < 10)
				day = "0" + day;
			if (hours < 10)
				hours = "0" + hours;
			if (minute < 10)
				minute = "0" + minute;
			var dateStr = date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minute;
			xData.push(dateStr);// 给X轴添加数据
			// 判断请求时间在X轴的区间  //  给y轴添加数据
			if(dateStr >= startTime && dateStr <= endTime){
				var temp = 0;//存储i的位置,下次循环直接从该位置开始
				for(var i = xDataCount; i < xOldData.length; i ++){
					if(dateStr == xOldData[i]){
						yData.push((yOldData[i] + "").substring(0, 6));
						xDataCount++;
						temp = 1;
						break;
					}
				}
				//假数据
//				if(temp == 0)	//没有值
//					yData.push("-");
				if(temp == 0)	//没有值
					yData.push((yOldData[xDataCount] + "").substring(0, 6));
			}else
				yData.push("-");
			len++;
		}
		
		// 加载图形
		setEchartDataOption(title, xName, xData, [ legend ], intervalValue,
				yName, yData, formatterValue);
	} else {
		setEchartDataOption(title, xName, xData, [ legend ], intervalValue,
				yName, yData, formatterValue);
	}
}




//创建图形,有数据
function setEchartDataOption(title, xName, xData, legendData, intervalValue,
		yName, yData, formatterValue) {
	myChart.setOption({
		noDataLoadingOption : {
			text : '暂无数据',
			effect : 'bubble',
			effectOption : {
				effect : {
					n : 0
				}
			}
		},
		animation : true,
		title : {
			text : title,
			subtext :'' 
		},
		show : true,
		tooltip : {// 鼠标悬浮时的提示信息
			trigger : 'item'// yxis item
		},
		grid : {
			y2 : 90
		},
		toolbox : {
			show : true,// 右上角选项卡
			feature : {
				mark : {// 辅助线
					show : true
				},
				dataZoom : {// 区域缩放
					show : true
				},
				magicType : {// 折线图-柱状图切换
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {// 还原
					show : true
				},
				saveAsImage : {// 保存图片
					show : true
				}
			}
		},
		legend : {
			data : legendData
		},
		
		xAxis : {
			name : xName,
			data : xData,
			axisLine : {
				lineStyle : {
					color : 'rgb(51,171,160)'
				}
			},
			axisLabel : {
				show : true,
				interval : intervalValue,// 表示数据间的显示间隔，0表示所有数据全部显示，1表示1隔1显示，2表示1隔2显示，以此类推
				rotate : 45// 表示数字倾斜程度
			},
			splitLine : {
				show : true,
				lineStyle : {
					color : [ 'rgba(51,171,160,0.3)' ], // 网格线
					width : 1,
					type : 'dashed'
				},
			},
			boundaryGap : false,
		},
		yAxis : {// y轴的单位和数据
			name : yName,
			type : 'value',
			scale : true,
			axisLine : {
				lineStyle : {
					color : 'rgb(51,171,160)'
				}
			},
			splitLine : {
				show : true,
				lineStyle : {
					color : [ 'rgba(51,171,160,0.3)' ], // 网格线
					width : 1,
					type : 'dashed'
				},
			},
			axisLabel : {
				formatter : formatterValue
			},
		},
		series : []
	}, true);

	if(xData.length > 0){
		myChart.setSeries([ {// 这是纵轴
			name : yName,
			type : 'line',
			itemStyle : {//设置线条的颜色
				normal : {
					color: 'rgb(51,171,160)'
				}
			},
			data : yData,//设置数据
			markPoint : {//设置线段的最值
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		} ]);
	}else {
		myChart.setSeries([ {// 这是纵轴
			name : yName,
			type : 'line',
			data : []
		} ]);
	}
}

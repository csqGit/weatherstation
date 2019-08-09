var myChart;
var requestData;
var deviceId;
var titleType;
var type;

window.onload = function() {
	// href="http://localhost:8080/line_chart.html?1/湿度"
	var loc = window.location.href;
	var locArr = loc.split("?");
	var typeArr = locArr[1].split("/");
	deviceId = typeArr[0];
	type = parseInt(typeArr[1]);
	titleName(type);
	// http://localhost:8080/pageLocationController/page?param=devicePage&type=
	$("#foot a").attr(
			"href",
			"http://localhost:8080/pageLocationController/page?param=devicePage&type="
					+ type);

	document.getElementById("deviceId").value = deviceId;
	// 获取存放图标容器
	var domMain = document.getElementById("main");
	// 加载初始化图表文件echarts
	myChart = echarts.init(domMain);// macarons 实线 infographic点线 不传为实线

	formData("init");
}

// 异步加载数据
function formData(requestData) {
	myChart.showLoading({
		text : '正在加载',
		color : '#ffffff',
		textColor : '#8a8e91',
		maskColor : 'rgba(255, 255, 255, 0.8)',
		effect : 'ring'// 'dynamicLine'
	});
	var formDate = formatFormDate(requestData);

	$
			.ajax({
				url : 'http://localhost:8080/weathersController/selectHistoryDataJSONList',
				type : 'POST',
				data : formDate,
				success : function(res) {
					requestSuccess(res, formDate);
				},
				error : function(err) {
					// alert("请求错误")
				}
			});
}

// 处理请求参数
function formatFormDate(requestData) {
	var formDate;
	if ("init" == requestData) {
		formDate = {
			'deviceId' : deviceId,
			'type' : type
		};
	} else if ("form" == requestData) {
		var year = $("#year").val();
		var yearMonth = $("#yearMonth").val();
		var yearMonthDay = $("#yearMonthDay").val();

		var hourMinuteSecond = $("#hourMinuteSecond").val();
		var yearMonthDayAndHourMinuteSecond = $(
				"#yearMonthDayAndHourMinuteSecond").val();
		var yearMonthDayAndHourMinuteSecond2 = $(
				"#yearMonthDayAndHourMinuteSecond2").val();
		if (yearMonthDayAndHourMinuteSecond != '') {
			if (yearMonthDayAndHourMinuteSecond2 != '') {
				formDate = {
					'deviceId' : deviceId,
					'yearMonthDayAndHourMinuteSecond' : yearMonthDayAndHourMinuteSecond
							+ "~" + yearMonthDayAndHourMinuteSecond2,
					'type' : type
				};
			} else {
				alert("请选择终止时间");
				return false;
			}

		} else if (yearMonthDay != '') {
			if (hourMinuteSecond != '') {
				formDate = {
					'deviceId' : deviceId,
					'yearMonthDay' : yearMonthDay,
					'hourMinuteSecond' : hourMinuteSecond,
					'type' : type
				};
			} else
				formDate = {
					'deviceId' : deviceId,
					'yearMonthDay' : yearMonthDay,
					'type' : type
				};
		} else if (yearMonth != '') {
			formDate = {
				'deviceId' : deviceId,
				'yearMonth' : yearMonth,
				'type' : type
			};
		} else if (year != '') {
			formDate = {
				'deviceId' : deviceId,
				'year' : year,
				'type' : type
			};
		}

	}
	return formDate;
}

// 请求成功后回调
function requestSuccess(res, formDate) {
	var xName = "时间";
	var yName = titleType;
	var title = "";
	var legend = titleType;
	var yData = [];
	var y2Data = [];
	var y3Data = [];
	var xData = [];
	if (res.length > 0) {// 如果有数据

		myChart.hideLoading();
		var len = res.length;
		title = res[0].time + "~" + res[len - 1].time;
		// 给x轴添加数据
		for (var i = 0; i < res.length; i++) {
			var xValue = res[i].id + "";
			var time = res[i].time;
			xData.push(time.substring(0, time.length - 3));
		}
		// 给y轴添加数据
		if (type == 0) {// 风向
			for (var i = 0; i < res.length; i++) {
				var yValue = res[i].dm + "";
				yData.push(yValue);
			}
		} else if (type == 1) {// 风速
			for (var i = 0; i < res.length; i++) {
				var yValue = res[i].sm + "";
				yData.push(yValue);
			}
		} else if (type == 2) {
			for (var i = 0; i < res.length; i++) {
				var yValue = res[i].ta + "";
				yData.push(yValue);
			}
		} else if (type == 3) {
			for (var i = 0; i < res.length; i++) {
				var yValue = res[i].ua + "";
				yData.push(yValue);
			}
		} else if (type == 4) {
			for (var i = 0; i < res.length; i++) {
				var yValue = res[i].pa + "";
				yData.push(yValue);
			}
		}
		// start
		myChart.hideLoading();
		setChartOption(xData, yData, y2Data, y3Data, xName, yName, title,
				legend, formDate);
	} else {// 表示当前没有数据
		myChart.hideLoading();
		setEchartNotDataOption(title, legend, yName);
	}
}

var formatterValue;
// 处理请求类型显示
function titleName(type) {
	if (type == 0) {
		titleType = "风向";
		formatterValue = '{value} D';
	} else if (type == 1) {
		titleType = "风速";
		formatterValue = '{value} M';
	} else if (type == 2) {
		titleType = "温度";
		formatterValue = '{value} °C';
	} else if (type == 3) {
		titleType = "湿度";
		formatterValue = '{value} P';
	} else if (type == 4) {
		titleType = "压强";
		formatterValue = '{value} H';
	}
}

// 创建图形对象并添加到main中
function setChartOption(xOldData, yOldData, y2OldData, y3OldData, xName, yName,
		title, legend, formDate) {// x轴坐标数据 y轴坐标数据 x轴名称 y轴名称
	var xoldDataFirst;// X轴的开始坐标
	var xoldDataLast;// X轴的终止坐标
	// 取出查询的实际数据的第一个数据和最后一个数据,作为真是数据的集合点，并在此区间生成图形，其余各区间为空
	var startTime = xOldData[0];
	var endTime = xOldData[xOldData.length - 1];
	var dayCount;// 判断查询的日期区间总共有多少天 （（前一个月的总天数-已经过的天数）+最后一个月的天数）计算总共生成多少个点

	var xData = [];// 存储X轴的数据
	var yData = [];// 存储Y轴的数据
	var len = 0;// 循环的次数
	var count = 60;// dayCount天产生的条数

	// 计算开始时间
	var oldFirstYear;// 起始时间的年
	var oldFirstMonth;// 起始时间的月
	var oldFirstDay;// 起始时间的日
	var oldFirstHour;
	var oldLastYear;// 终止时间的年
	var oldLastMonth;// 终止时间的月
	var oldLastDay;// 终止时间的月
	var oldLastHour;
	var dateYear;// 创建日期对象的第一个参数
	var dateMonth;// 创建日期对象的第二个参数
	var dateDay;// 创建日期对象的第三个参数
	var dateHour, dateMinute = 0;
	if (typeof formDate.yearMonthDayAndHourMinuteSecond != 'undefined') {// 表示根据需求查询，确定时间范围
		var timeArr = formDate.yearMonthDayAndHourMinuteSecond.split("~");
		xoldDataFirst = timeArr[0];
		xoldDataLast = timeArr[1];
		// 计算开始时间
		oldFirstYear = xoldDataFirst.substring(0, 4);// 起始时间的年
		oldFirstMonth = xoldDataFirst.substring(5, 7);// 起始时间的月
		oldFirstDay = xoldDataFirst.substring(8, 10);// 起始时间的日
		oldFirstHour = xoldDataFirst.substring(11, 13);
		oldLastYear = xoldDataLast.substring(0, 4);// 终止时间的年
		oldLastMonth = xoldDataLast.substring(5, 7);// 终止时间的月
		oldLastDay = xoldDataLast.substring(8, 10);// 终止时间的月
		oldLastHour = xoldDataLast.substring(11, 13);
	} else {// 表示默认进入当前页面，时间为当天时间到现在
		oldFirstYear = xOldData[0].substring(0, 4);// 起始时间的年
		oldFirstMonth = xOldData[0].substring(5, 7);// 起始时间的月
		oldFirstDay = xOldData[0].substring(8, 10);// 起始时间的日
		oldFirstHour = xOldData[0].substring(11, 13);
		oldLastYear = xOldData[xOldData.length - 1].substring(0, 4);// 终止时间的年
		oldLastMonth = xOldData[xOldData.length - 1].substring(5, 7);// 终止时间的月
		oldLastDay = xOldData[xOldData.length - 1].substring(8, 10);// 终止时间的月
		oldLastHour = xOldData[xOldData.length - 1].substring(11, 13);
	}
	dateYear = oldFirstYear;// 创建日期对象的第一个参数
	dateMonth = oldFirstMonth - 1;// 创建日期对象的第二个参数
	dateDay = oldFirstDay;// 创建日期对象的第三个参数
	dateHour = (oldLastHour - oldFirstHour);

	// 判断是否为同一个月
	if (oldLastMonth - oldFirstMonth == 0) {// 表示在时间段同一个月
		dayCount = (oldLastDay - oldFirstDay);
	} else {
		dayCount = (parseInt((31 - oldFirstDay)) + parseInt(oldLastDay) + parseInt((oldLastMonth
				- oldFirstMonth - 1) * 31));
	}

	// 判断是否为同一天，0 为同一天
	if (dayCount == 0) {
		if (oldLastHour - oldFirstHour == 0) {// 判断是否为同一小时
			count = 60;
			dateHour = oldFirstHour;
		} else {// 不是同一小时
			count *= dateHour;
			dateHour = oldFirstHour;
		}
	} else {// 不是同一天
		count *= dayCount * 24;
		dateHour = 0;
	}
	var intervalValue;// 数据点区间
	if (count > 60) {// X轴总共显示60个点 数据点的区间为点的总数/X轴的显示总数
		intervalValue = count / 60;
	} else {
		intervalValue = dayCount * 1;
	}
	intervalValue = parseInt(intervalValue);
	var xDataCount = 0; // 记录添加数据的次数
	while (len < count) {// 因为1天产生24各点，所以判断总共有多少天就循环多少次*24，表示生成多少各点
		var date = new Date(dateYear, dateMonth, dateDay, dateHour, len)// 年/月/日/时
		// len*n表示n/60小时生成一个点
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
		var dateStr = date.getFullYear() + "-" + month + "-" + day + " "
				+ hours + ":" + minute;

		xData.push(dateStr);// 给X轴添加数据
		// 给y轴添加数据
		if (dateStr >= startTime && dateStr <= endTime) {// 判断请求时间在X轴的区间
			if (xDataCount < yOldData.length) {
				yData.push(yOldData[xDataCount]);
				xDataCount++;
			}
		} else {// 判断请求时间不在X轴的某个区间
			yData.push("-")
		}
		len++;
	}
	var legendData;// 图表标题的数据

	legendData = [ legend ];
	setEchartDataOption(title, xName, xData, legendData, intervalValue, yName, yData,
			formatterValue);

}

function setEchartNotDataOption(title, legendData, yName){
	myChart.setOption({
		animation : true,
		title : {
			text : yName,
			subtext : title
		},
		show : true,
		tooltip : {// 鼠标悬浮时的提示信息
			trigger : 'yxis'// yxis item
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
		calculable : true,
		yAxis : {// y轴的单位和数据
			name : yName,
			type : 'value',
			scale : true,
			splitLine : {
				show : true,
				lineStyle : {
					color : [ 'rgba(51,171,160,0.3)' ], // 网格线
					width : 1,
					type : 'dashed'
				},
			},
			axisLabel : {
				formatter : 0
			},

		},
		series : [{
            name:'时间',
            type:'line',
            data:[],
            
        },]
	}, true);
	
}

//创建图形,有数据
function setEchartDataOption(title, xName, xData, legendData, intervalValue, yName,
		yData, formatterValue) {
	myChart.setOption({
		animation : true,
		title : {
			text : yName,
			subtext : title
		},
		show : true,
		tooltip : {// 鼠标悬浮时的提示信息
			trigger : 'yxis'// yxis item
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
		calculable : true,
		xAxis : {
			name : xName,
			data : xData,
			// splitNumber:200,
			axisLine : {
				lineStyle : {
					color : '#2898e5'
				}
			},
			axisLabel : {
				show : true,
				interval : intervalValue,// 表示数据间的显示间隔，0表示所有数据全部显示，1表示1隔1显示，2表示1隔2显示，以此类推
				rotate : 45
			// 表示数字倾斜程度
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


	myChart.setSeries([ {// 这是纵轴
		name : yName,
		type : 'line',
		data : yData,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	} ]);
}

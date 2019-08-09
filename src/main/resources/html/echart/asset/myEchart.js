var myChart;
var requestData;
var deviceId;
var titleType;
var type;
var legend;

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
			"./pageLocationController/page?param=devicePage&type="
					+ type);

	document.getElementById("deviceId").value = deviceId;
	getDeviceName(deviceId);
	// 获取存放图标容器
	var domMain = document.getElementById("main");
	// 加载初始化图表文件echarts
	myChart = echarts.init(domMain);// macarons 实线 infographic点线 不传为实线

	formData("init");
}

function getDeviceName(deviceId){
	$.ajax({
		url: '../deviceController/selectDeviceNameByDeviceId',
		type: 'POST',
		data : {'deviceId' : deviceId},
		success: function(res){
			$("#deviceName").html(res.deviceName);
		},
		error : function(err) {
			// alert("请求错误")
		}
	});
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
				url : './weathersController/selectHistoryDataJSONList',
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
	legend = titleType;
	var yData = [];
	var xData = [];

	myChart.hideLoading();// 隐藏动态加载图片
	var len = res.length;
	if (len > 0)
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
	setChartOption(xData, yData, xName, yName, title,  formDate);
}


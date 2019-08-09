
function selectWeatherList(param){
	var currentPage = document.getElementById("currentPage").innerText;
	var pages = document.getElementById("pages").innerText;
	var limit = document.getElementById("limit").innerText;
	var loc = document.getElementById("location").innerText;
	var deviceId = $("#device option:selected").val();
	var requestParam = $("input[type=hidden]").val();
	var time = $("#date").val();
	var year = $("#year").val();
	var yearMonth = $("#yearMonth").val();
	var yearMonthDay = $("#yearMonthDay").val();
	var hourMinuteSecond = $("#hourMinuteSecond").val();
	var yearMonthDayAndHourMinuteSecond = $("#yearMonthDayAndHourMinuteSecond").val();
	var yearMonthDayAndHourMinuteSecond2 = $("#yearMonthDayAndHourMinuteSecond2").val();

	currentPage = parseInt(currentPage);
	pages = parseInt(pages);
	//var current = 1;
	if('index' == param){
		if(currentPage == 1)
			return false;
		else
			currentPage = 1;//请求页数
	}else if('pre' == param) {//上一页
		
		if(currentPage > 1){
			currentPage --;
		}else 
			return false;
	}else if('next' == param){//下一页
		if(currentPage < pages){
			currentPage ++;
		}else 
			return false;
	}else if('last' == param){//尾页
			if(currentPage == pages){
				return false;
			}else 
				currentPage = pages;
	}else {
		var jumpPage = document.getElementById("jump").value;
		jumpPage = parseInt(jumpPage);
		if(jumpPage < 0 || jumpPage > pages){
			alert("输入页码不符合规范，总页数为：" + pages + "页")
			return false;
		}else if(jumpPage != currentPage){
			currentPage = jumpPage;
		}
	}
	//window.location .href = "selectSchoolBusList?currentPage=" + currentPage + "&limit=" + 2;
	if(requestParam == 'history'){//实时数据
		if(yearMonthDayAndHourMinuteSecond != "" && yearMonthDayAndHourMinuteSecond2 != ""){
			loc = loc + "&currentPage=" + currentPage + "&limit=" 
			+ limit + "&yearMonthDayAndHourMinuteSecond=" + yearMonthDayAndHourMinuteSecond
			+ "&yearMonthDayAndHourMinuteSecond2=" + yearMonthDayAndHourMinuteSecond2
//			+ "&param=" + requestParam;
		}else {
			loc = loc + "&currentPage=" + currentPage + "&limit=" 
			+ limit;
		}
			
		window.location .href = loc + "&deviceId="+ deviceId ;
	}else {//历史数据
		window.location .href = loc + "&currentPage=" + currentPage + "&limit=" 
		+ limit ;
		
	}

}

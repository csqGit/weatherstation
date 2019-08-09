$(function() {
		$.ajax({
			url : "http://localhost:8080/deviceController/selectDeviceList", //请求的url地址
			dataType : "json", //返回格式为json
			async : true,//请求是否异步，默认为异步，这也是ajax重要特性
			type : "GET", //请求方式
			beforeSend : function() {
				//请求前的处理
				//		    	alert("开始请求");
			},
			success : function(data) {
				//alert(JSON.stringify(data))#selectCom
				for ( var i in data) {
					var option = "<option value='"+ data[i].id+"'>"
							+ data[i].deviceName + "</option>";
					$("#company").append(option);
				}
			}
		})
	})
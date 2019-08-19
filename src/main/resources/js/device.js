//window.onload = function(){
//			
//			
//		}




	function flush(){
		window.location.reload();
	}
		$(function(){
			//动态设置main的高度，让它自适应屏幕高度
			var clientHeight = document.documentElement.clientHeight;
			var mainHeight = document.getElementById("main");
			mainHeight.style.height = clientHeight - 140 + "px";
			
			
			$(".deviceEdit").click(function(){
				$("#myForm").attr('action', '/deviceController/updateDevice');
				var deviceName = $(this).attr('deviceName');
				var deviceId = $(this).attr('deviceId');
				var id = $(this).attr('value');
				$("#deviceName").val(deviceName);
				$("#deviceId").val(deviceId);
				$("input[type='hidden']").val(id);
				$(this).attr('id', 'active');
				$(".modal-title").html("编辑气象站");
			});
			
			$(".add").click(function(){
				$("#myForm").attr('action', '/deviceController/insertDevice');
				$("#deviceName").val('');
				$("#deviceId").val('');
				$("input[type='hidden']").val('');
				$(".modal-title").html("新增气象站");
			});
			
			$("#add").click(function(){
				//var companyId = $("#companyId").val();
				var deviceName = $("#deviceName").val();
				var deviceId = $("#deviceId").val();
				if(deviceName.trim().length == 0){
					alert("变电站名称不能为空！")
					return false;
				}
				if(deviceId.trim().length == 0){
					alert("气象站编号不能为空！");
					return false;
				}
				$("#myForm").submit();
				
			});
			
			
			
			//删除气象台
			$(".layui-btn-danger").click(function(){
				
				if(confirm('确定删除吗？')){
				 var id = $(this).val();
					$.ajax({
						url: '/deviceController/deleteDeviceById',
						type: 'post',
						data: {'id' : id},
						//dataType: 'json',
						success: function(res){
							if(res == 'success'){
								//alert(JSON.stringify(res))
								//alert("删除成功");
								window.location.reload();
							}
						},
						error : function(err){
							alert(JSON.stringify(err))
							alert("删除失败");
						}
					}); 
				}
			});
		})
function flush() {
	window.location.reload();
}

$(function() {
	$("#updateUser").click(function() {
		var id = $("#userId").val();
		var companyId = $("#companyId").val();
		var username = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			url : 'http://localhost:8080/userController/updateUser',
			type : 'post',
			data : {
				'id' : id,
				'username' : username,
				'password' : password,
				'companyId.id' : companyId
			},
			success : function(res) {
				if (res == 'success') {
					$("#myForm").hide();
					var message = $("#message");
					message.show();
					message.html("<span style='color:green'>更新成功</span>");
					$(".btn-default").html('关闭');
				}
			},
			error : function(err) {
				$("#myForm").hide();
				var message = $("#message");
				message.show();
				message.html("<span style='color:green'>更新失败</span>");
				$(".btn-default").html('关闭');
			}

		});
	});
})
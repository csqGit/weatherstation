$(function(){
			$("#updateUser").click(function(){
				var username = $("#username").val();
				var pass = $("#password").val();
				if(username.trim().length == 0){//用户名为空
					alert("用户名不能为空！")
				return false;
				}
				if(pass.trim().length == 0){
					alert("密码不能为空！")
					return false;
				}
				$("#myForm").submit();
			});
		})
		
		
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$('.submit_data').click(function(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		var password2 = $("#password2").val();
		var phone = $("#phone").val();
		var userSex = 1;
		var type = 0;
		var email = $("#email").val();
		//性别选中
		if($("#sex-1").is(':checked')) {
		    // do something
			userSex = 0;
		}
		//类型选中
		if($("#type-2").is(':checked')) {
		    // do something
			type = 1;
		}else if($("#type-3").is(':checked')){
			type = 2;
		}else if($("#type-4").is(':checked')){
			type = 3;
		}else if($("#type-5").is(':checked')){
			type = 4;
		}
		var bankName = $("#bankName").val();
		//空处理
		if(isEmpty(userName,'用户名不能为空！')){return false};
		if(isEmpty(password,'密码不能为空！')){return false};
		if(isEmpty(password2,'确认密码不能为空！')){return false};
		if(isEmpty(phone,'手机号码不能为空！')){return false};
		//密码格式
/*		if(isPasswod(password)){return false};*/
		//密码比较
		if(password2 != password ){
			layer.msg('两次输入密码不一致',{icon:5,time:1500});
			return false;
		}
		//手机格式
		if(isPhone(phone)){return false};
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/user/addOrUpdate',
		        data: {userName:userName,password:password,userSex:userSex,
		        	phone:phone,email:email,type:"add",userType:type},
		        dataType: "json",
		        beforeSend: function () {
		            //防止重复提交
		        	 $(".layui-layer-shade").css({'display':'none'});
		        	 $(".layui-layer-dialog").css({'display':'none'});
		        	 loading.loadingShow();
		        },
		        success: function(data){
		        	loading.loadingHide();
		        	 if(data.flag){
		        		var index = parent.layer.getFrameIndex(window.name);
		 				parent.flush();//点击确定调用父页面方法  
		 				parent.layer.close(index);		
		        	}else{
		        		layer.msg(data.msg,{icon:5,time:1000});
		        	}	 
		        },  
		        error:function(xhr,status,statusText){
		        	loading.loadingHide();
		        	if(xhr.status == 500)
		        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
		        }  
		    });
		 });
	});
});
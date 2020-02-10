var id = $(".data_id").val();
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	getDate();
	$('.submit_data').click(function(){
		var userName = $("#userName").val();
		var oldUserName = $("#oldUserName").val();
		var phone = $("#phone").val();
		var oldPhone = $("#oldPhone").val();
		var userSex = 0;
		var userType = 0;
		var email = $("#email").val();
		var userMallId = $("#userMallId").val();
		//性别选中
		if($("#sex-2").is(':checked')) {
		    // do something
			userSex = 1;
		}
		//类型选中
		if($("#type-2").is(':checked')) {
		    // do something
			userType = 1;
		}else if($("#type-3").is(':checked')){
			userType = 2;
		}
		else if($("#type-4").is(':checked')){
			userType = 3;
		}
		var bankName = $("#bankName").val();
		//空处理
		if(isEmpty(userName,'用户名不能为空！')){return false};
		if(isEmpty(phone,'手机号码不能为空！')){return false};
		//手机格式
		if(isPhone(phone)){return false};
		//原账号和手机号码比较
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/user/addOrUpdate',
		        data: {id:id,userName:userName,userSex:userSex,
		        	phone:phone,email:email,type:"update",userType:userType,oldUserName:oldUserName,oldPhone:oldPhone,userMallId:userMallId},
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
//数据回显
function getDate(){
	$.ajax({
        type: "GET",
        url: '/tz/admin/user/selectById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
	        	$("#userName").val(data.userName);
        	    $("#oldUserName").val(data.userName);
    		    $("#phone").val(data.phone);
    		    $("#oldPhone").val(data.phone);
    		    $("#email").val(data.email);
    		    $("#userMallId").val(data.userMallId);
    		    var userSex = data.userSex;
    		    $("#userSex").val(userSex);
    		    $("input[name='sex'][value="+userSex+"]").parents(".iradio-blue").addClass("checked");
    		    $("input[name='sex'][value="+userSex+"]").prop("checked",true);
	    		var userType = data.type;
	    		$("#userType").val(userType);
	    		$("input[name='type'][value="+userType+"]").parents(".iradio-blue").addClass("checked");
	    		$("input[name='type'][value="+userType+"]").prop("checked",true);
             }	
	    	else{
	    		layer.msg('数据加载失败，请稍后重试！',{icon:5,time:1000});
		    }
        },  
        error:function(xhr,status,statusText){
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }  
    });
    
}
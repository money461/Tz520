$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$('.submit_data').click(function(){
		var name = $("#name").val();
		var version = $("#version").val();
		var down = $("#down").val();
		var isForce = 1;
		//性别选中
		if($("#sex-2").is(':checked')) {
		    // do something
			isForce = 0;
		}
		//空处理
		if(isEmpty(name,'app名称不能为空！')){return false};
		if(isEmpty(version,'版本号不能为空！')){return false};
		if(isEmpty(down,'请先上传安装包！')){return false};
		//密码格式
/*		if(isPasswod(password)){return false};*/
		//密码比较
		//手机格式
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/appDown/add',
		        data: {name:name,version:version,down:down+"?filename="+name+"_"+version+".apk",isForce:isForce},
		        dataType: "json",
		        beforeSend: function () {
		            //防止重复提交
		        	 $("#message_before_send_show").css({'display':'block'});
		    	   	 $(".message_span").html("提交中，请勿操作....");
		        },
		        success: function(data){
		        	$("#message_before_send_show").css({'display':'none'});
		        	 if(data.flag){
		        		var index = parent.layer.getFrameIndex(window.name);
		 				parent.flush();//点击确定调用父页面方法  
		 				parent.layer.close(index);		
		        	}else{
		        		layer.msg(data.msg,{icon:5,time:1000});
		        	}	 
		        },  
		        error:function(xhr,status,statusText){
		        	$("#message_before_send_show").css({'display':'none'});
		        	if(xhr.status == 500)
		        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
		        }  
		    });
		 });
	});
	$(".package").on('change',function() {
		 $("#message_before_send_show").css({'display':'block'});
	   	 $(".message_span").html("上传中....");
		hfu_ajax({
			success : function(data) {
				console.info(data.data);
				$("#down").val(data.data);
				 $("#message_before_send_show").css({'display':'none'});
			   	
			},
			progress : function(a) {
				if (a = 100) {
				}
			},
			url : "/tz/admin/file/uploadFile",
			fromId : "file_up"
		})	
	});
});
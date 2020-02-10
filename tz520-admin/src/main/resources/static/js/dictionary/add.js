$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$('.submit_data').click(function(){
		var type = $(".select option:selected").val();
		var name = $("#name").val();
		var code = $("#code").val();
		var module = $("#module").val();
	
		
		//空处理
		if(isEmpty(type,'数据类型不能为空！')){return false};
		if(isEmpty(name,'字典名称不能为空！')){return false};
		if(isEmpty(code,'关键字不能为空！')){return false};
		if(isEmpty(module,'所属模块不能为空！')){return false};
	
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/dictionary/addOrUpdate',
		        data: {type:type,name:name,code:code,module:module,way:"add"},
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
		        		layer.msg(data.msg,{icon:1,time:1000});
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
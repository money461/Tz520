var id = $("#id").val();
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	getDate();
	
	//提交更新
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
		//原账号和手机号码比较
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/dictionary/addOrUpdate',
		        data: {id:id,type:type,name:name,code:code,module:module,way:"update"},
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
        type: "post",
        url: '/tz/admin/dictionary/selectDictionaryById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
        		$(".select option[value='"+data.type+"']").prop('selected',true);
	        	$("#name").val(data.name);
        	    $("#code").val(data.code);
    		    $("#module").val(data.module);
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
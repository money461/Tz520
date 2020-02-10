var id = $(".data_id").val();
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	getDate();
	$('.submit_data').click(function(){
		var cityName = $("#cityName").val();
		var oldCityName = $("#oldCityName").val();
		//空处理
		if(isEmpty(cityName,'城市名不能为空！')){return false};
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/city/addOrUpdate',
		        data: {id:id,cityName:cityName,oldCityName:oldCityName,type:"update"},
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
        url: '/tz/admin/city/selectById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
	        	$("#cityName").val(data.cityName);
        	    $("#oldCityName").val(data.cityName);
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
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	var userId = $(".data_id").val();
	$('.submit_data').click(function(){
		var city_id = $(".city_box").val();
		console.info(userId)
		console.info(city_id)
		if(isEmpty(city_id,'请先选择城市！')){return false};
		//省份名
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/userCity/add',
		        data: {userId:userId,cityId:city_id,type:1},
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
		
	})
	//
	cityInit();
});
function cityInit(){
	$(".city_box option").remove();
	$.ajax({  
        type:"GET",  
        url: "/tz/admin/city/getCityListByState?state=1&type=1",  
        success: function(data){  
        	data = data.data;  
            if(data != null && data.length > 0){  
                var html="<option value=''>请选择</option>";   
                for(var i=0; i<data.length; i++){  
                    html+="<option value='"+data[i].id+"'>"+data[i].cityName+"</option>";
                }  
            }  
            $(".city_box").append(html);  
        }  
    })  
}
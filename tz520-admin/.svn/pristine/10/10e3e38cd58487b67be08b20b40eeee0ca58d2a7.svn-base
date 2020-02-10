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
		var name = $("#name").val();
		var linkAddress = $("#linkAddress").val();
    	var sort = $("#sort").val();
    	var logoUrl = $("#image_url").val();
	
		//空处理
		if(isEmpty(name,'分类名称不能为空！')){return false};
		if(isEmpty(sort,'分类排序code不能为空！')){return false};
		if(logoUrl==""){
			logoUrl=null;
		}
		//原账号和手机号码比较
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/tzItemCategory/addOrUpdate',
		        data: {id:id,name:name,linkAddress:linkAddress,sort:sort,logoUrl:logoUrl,type:"update"},
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
		        		 layer.msg(data.msg,{icon:5,time:1000});
		        		var index = parent.layer.getFrameIndex(window.name);
		 				parent.flush();//点击确定调用父页面方法  
		 				parent.layer.close(index);		
		        	}else{
		        		layer.msg("更新失败。请稍后使用！",{icon:5,time:1000});
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
        url: '/tz/admin/tzItemCategory/selectItemCategoryById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
	        	$("#name").val(data.name);
	        	$("#linkAddress").val(data.linkAddress);
	        	$("#sort").val(data.sort);
	        	$(".logoUrl").append("<img src="+data.logoUrl+"/>");
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
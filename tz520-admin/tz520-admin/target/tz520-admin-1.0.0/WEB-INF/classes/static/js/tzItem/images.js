var id = $(".data_id").val();
$(function(){
	/*异步加载角色信息*/
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$('.submit_data').click(function(){
		var imageUrl = $("#image_url").val();
		var id = $("#itemId").val();
		layer.confirm('确认数据检查无误并提交?',function(index){
			if(imageUrl.length < 1){
				var index = parent.layer.getFrameIndex(window.name);
 				parent.flush();//点击确定调用父页面方法  
 				parent.layer.close(index);		
			}else{
				$.ajax({
			        type: "POST",
			        url: '/tz/admin/tzItem/updateItem',
			        data: {id:id,imageUrl:imageUrl},
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
			        		layer.msg('操作失败，请稍后重试！',{icon:5,time:1000});
			        	}	 
			        },  
			        error:function(xhr,status,statusText){
			        	loading.loadingHide();
			        	if(xhr.status == 500)
			        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
			        }  
			    });
			}
		});
	})
});


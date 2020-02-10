$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	//排序code失去焦点后校验是否存在
	$("#sort").blur(function(){
		var sort =$("#sort").val();
		//校验sort是否已存在
		if(sort!=null && sort!=""){
			$.ajax({
				type: "post",
		        url: '/tz/admin/tzItemCategory/checkItemCategorySort',
		        data: {sort:sort},
		        dataType: "json",
		        success: function(data){
		            if(!data.flag){
		            	alert("排序code已存在！请遵照排序code顺序重新输入");
		            	$("#sort").val("");
		            }
		          }
			});
		}else{
			alert("排序code不能为空！");
			return;
		}
	});
	$('.submit_data').click(function(){
		var name = $("#name").val();
		var sort =$("#sort").val();
		var linkAddress = $("#linkAddress").val();
		var logoUrl= $("#image_url").val();
		//空处理
		if(isEmpty(name,'分类名称不能为空！')){return false};
		if(isEmpty(sort,'分类排序code不能为空！')){return false};
	
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/tzItemCategory/addOrUpdate',
		        data: {name:name,sort:sort,linkAddress:linkAddress,logoUrl:logoUrl,type:"add"},
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
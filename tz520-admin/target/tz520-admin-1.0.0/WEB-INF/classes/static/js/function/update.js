//获取角色id
var id = $("#id").val();

$(function(){
	
	/*异步加载父功能节点,展示所有权限信息*/
	parentIdFunctionData();
	
	/*更新页面异步加载，回显需要修改的数据*/
	getDate();
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$('.submit_data').click(function(){
		var name=$("#functionName").val();
		var code=$("#code").val();
		var description=$("#description").val();
		var page=$("#page").val();
		var zindex = $("#zindex").val();
		var generatemenu=$("#generatemenu option:selected").val();
		var pId=$("#pId option:selected").val();
		
		if(isEmpty(name,"权限名称不能为空！")){return false};
		if(isEmpty(code,"权限关键词不能为空！")){return false};
		if(isEmpty(description,"权限描述不能为空！")){return false};
		if(isEmpty(generatemenu,"权限是否生成菜单不能为空！")){return false};
		if(isEmpty(pId,"权限父节点不能为空！")){return false};
		
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/function/updateAuthFunction',
		        data: {id:id,name:name,code:code,description:description,pId:pId,page:page,generatemenu:generatemenu},
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
		        		 layer.alert(data.msg,{icon:1,time:2000});
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
		 });
		
	});
	
});	


//update编辑页面异步加载角色数据回显
function getDate(){
	$.ajax({
        type: "POST",
        url: '/tz/admin/function/findAuthFunctionById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
            	$("#id").val(data.id);
            	$("#functionName").val(data.name);
	    		$("#code").val(data.code);
	    		$("#page").val(data.page);
	    		$("#zindex").val(data.zindex);
	    		$("#generatemenu option[value='"+data.generatemenu+"']").prop('selected',true);
	    		$("#description").val(data.description);
             }	
	    	else{
	    		layer.msg('数据加载失败，请稍后重试！',{icon:5,time:1000});
		    }
        },  
        error:function(xhr,status,statusText){
        	loading.loadingHide();
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }  
    });
    
}

/*异步加载父节点*/
function parentIdFunctionData(){
	$.ajax({
		type:'post',
		dataType:'json',
		url :'/tz/admin/function/findAllFunction',
		success:function(data){
			if(data.flag){
				//清空
				$("#pId option").remove();
				//获取数据
				data = data.data;
				var html="";
				var html="<option value='0'>顶级父节点</option>";   
	            if(data != null && data.length > 0){  
	                for(var i=0; i<data.length; i++){  
	                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	                }  
	            }  
	            $("#pId").append(html);  
				
			}
		},
		error: function(){
			layer.msg('error!',{icon:1,time:1000});
		}
	});
}
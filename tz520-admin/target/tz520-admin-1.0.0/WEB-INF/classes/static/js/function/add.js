
$(function(){
	
	/*异步加载父功能节点,展示所有权限信息*/
	parentIdFunctionData();
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-function").validate({
		rules:{
			name:{
				required:true,
			},
			code:{
				required:true,
			},
			description:{
				required:true,
			},
			page:{
				required:true,
			},
			generatemenu:{
				required:true,
			},
			zindex:{
				required:true,
			},
			pid:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post',
				url: "addAuthFunction" ,
				success: function(data){
					layer.msg('添加权限成功!',{icon:1,time:1000});
				},
                error: function(XmlHttpRequest, textStatus, errorThrown){
					layer.msg('error!',{icon:1,time:1000});
				}
			});
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});

/*异步加载父节点功能*/
function parentIdFunctionData(){
	$.ajax({
		type:'post',
		dataType:'json',
		url :'/tz/admin/function/findAllFunction',
		success:function(data){
			if(data.flag){
				//清空
				$("#sel option").remove();
				//获取数据
				data = data.data;
				var html="";
				var html="<option value='0'>顶级父节点</option>";   
				console.info(data);
	            if(data != null && data.length > 0){  
	                for(var i=0; i<data.length; i++){  
	                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	                }  
	            }  
	            $("#sel").append(html);  
				
			}
		},
		error: function(){
			layer.msg('error!',{icon:1,time:1000});
		}
	});
}
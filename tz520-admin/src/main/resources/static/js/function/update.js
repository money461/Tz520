//获取角色id
var id = $("#id").val();

$(function(){
	
	/*回显权限数据*/
	getData();
	
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
		
		//获取被选中的权限上级目录
		 //获取Ztree被选中的选中id
		var functiontreeObj = $.fn.zTree.getZTreeObj("functionDemo");
		var functionnodes = functiontreeObj.getCheckedNodes(true);
		var pId = new Array();
		for(var i=0;i<functionnodes.length;i++){
			pId.push(functionnodes[i].id);
		}
		pId = pId[0];
		if(isEmpty(name,"权限名称不能为空！")){return false};
		if(isEmpty(code,"权限关键词不能为空！")){return false};
		if(isEmpty(description,"权限描述不能为空！")){return false};
		if(isEmpty(generatemenu,"权限是否生成菜单不能为空！")){return false};
		
		if(id==pId){
			layer.msg('不能选择自身对象作为父目录',{icon:5,time:1000});
			return;
		}
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/function/updateAuthFunction',
		        data: {id:id,name:name,code:code,description:description,zindex:zindex,pId:pId,page:page,generatemenu:generatemenu},
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


//update编辑页面异步加载权限数据回显
function getData(){
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
	    		/*加载权限*/
	    		parentIdFunctionData();
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

/*异步加载权限上级目录*/
function parentIdFunctionData(){
	// 授权树初始化
	var setting = {
			check: {
				enable: true,
				chkStyle: "radio",  //单选框
	            radioType: "all" , //对所有节点设置单选
				 chkboxType:  { "Y": "", "N": "" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	$.ajax({
		//发送ajax请求加载权限数据
		type : 'post',
		dataType : 'json',
		url:'/tz/admin/function/findAllFunction',
		success : function(data) {//data就是服务端返回的权限数据
			//var zNodes = eval("(" + data + ")");
//			console.info(data)
			if(data.flag){
				data = data.data;
				//使用权限数据初始化ztree
				$.fn.zTree.init($("#functionDemo"), setting, data);
			}
		},
		error : function() {
			alert('树加载异常!');
		}
		
		});
}

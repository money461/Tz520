var id = $("#id").val();
$(function(){
	
	/*异步加载角色信息*/
	roledata();
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	/*密码前后一致*/
	$("#password2").blur(function(){
		var password =$("#password").val();
		if(isPasswod(password)){return false};
		var password2=$("#password2").val();
		if(password!=password2){
			layer.msg('密码与确认密码前后不一致！',{icon:5,time:1000});
			return;
		}
		
	});
	
	/*校验手机格式*/
	$("#phone").blur(function(){
		var phone = $("#phone").val();
		if(isEmpty(phone,'手机不能为空！')){return false};
		//手机格式
		if(isPhone(phone)){return false};
	});
	
	$('.submit_data').click(function(){
		var managerName = $("#managerName").val();
		var password =$("#password").val();
		var password2=$("#password2").val();
		var phone = $("#phone").val();
		var email=$("#email").val();
		
		//获得ztree对象
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		//获得当前ztree对象选中的节点数组
		var nodes = treeObj.getCheckedNodes(true);//在提交表单之前将选中的checkbox收集
		//循环数组，获得节点的ID，拼接成字符串使用逗号分隔
		var array = new Array();
		for(var i=0;i<nodes.length;i++){
			array.push(nodes[i].id);
		}
		var roleIds = array.join(",");
		
		//空处理
		if(isEmpty(managerName,'姓名不能为空！')){return false};
		if(isEmpty(email,'邮件不能为空！')){return false};
		
		
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/manager/updateManager',
		        data: {id:id,managerName:managerName,phone:phone,password:password,email:email,roleIds:roleIds},
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
		 });
	});
});



//异步加载角色

function roledata(){
	// 授权树初始化
	var setting = {
			check: {
				enable: true,
				chkboxType: {"Y" : "s", "N" : ""}
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	$.ajax({
		type: 'get',
		dataType: 'json',
		url: '/tz/admin/role/findAllRole',
		success:function(data){
			//console.info(data);
			if(data.flag){
				 data = data.data;
				//使用权限数据初始化ztree
				$.fn.zTree.init($("#treeDemo"), setting, data); 
			}
			
			/*异步回显账户信息*/
			getDate();
		},
		error: function(data){
			$.Message().alert("warning",data.msg);
		}
		
	});
}

//update编辑页面异步加载数据回显

function getDate(){
	$.ajax({
      type: "POST",
      url: '/tz/admin/manager/findManagerById',
      data: {id:id},
      dataType: "json",
      success: function(data){
      	console.info(data)
          if(data.flag){
          	data = data.data;
          	$("#id").val(data.id);
          	$("#managerName").val(data.managerName);
    		$("#phone").val(data.phone);
    		$("#email").val(data.email);
	    		
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

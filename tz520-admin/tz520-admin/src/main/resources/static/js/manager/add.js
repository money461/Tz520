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
		var password2=$("#password2").val();
		if(isEmpty(password,'密码不能为空！')){return false};
		if(isEmpty(password2,'确认密码不能为空！')){return false};
		if(isPasswod(password)){return false};
		if(isPasswod(password2)){return false};
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
		var code = $("#code").val();
		var company=$("#company").val();
		var email=$("#email").val();
		
		
		//获取Ztree被选中的选中id
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		
		//遍历被选中的结点对象，获取id值
		var array = new Array();
		for(var i=0;i<nodes.length;i++){
//			alert(nodes[i].id);
			array.push(nodes[i].id);
		}
		//给id设置分隔符
		var roleIds = array.join(",");
		
		//空处理
		if(isEmpty(managerName,'姓名不能为空！')){return false};
		if(isEmpty(email,'邮件不能为空！')){return false};
		if(isEmpty(roleIds,'角色不能为空！')){return false};
		
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/manager/addManager',
		        data: {managerName:managerName,phone:phone,password:password,email:email,roleIds:roleIds},
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

function roledata(){
	// 授权树初始化
	var setting = {
			check: {
				enable: true,
//				chkStyle: "radio",  //单选框
//	            radioType: "all"  , //对所有节点设置单选
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
		},
		error: function(data){
			$.Message().alert("warning",data.msg);
		}
		
	});
}

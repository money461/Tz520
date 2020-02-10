$(function(){
	
	/*异步加载角色信息*/
	roledata();
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$('.submit_data').click(function(){
		var managerName = $("#managerName").val();
		var password =$("#password").val();
		var password2=$("#password2").val();
		var phone = $("#phone").val();
		var code = $("#code").val();
		var company=$("#company").val();
		var email=$("#email").val();
		var roleId=$("#roleId").val();
		
		//空处理
		if(isEmpty(managerName,'姓名不能为空！')){return false};
		if(isEmpty(phone,'手机不能为空！')){return false};
		if(isEmpty(password,'密码不能为空！')){return false};
		if(isEmpty(code,'关键词不能为空！')){return false};
		if(isEmpty(email,'邮件不能为空！')){return false};
		if(isEmpty(roleId,'角色不能为空！')){return false};
	
		//手机格式
		if(isPhone(phone)){return false};
		
		//单数
		if(isNumber(orderNumber)){return false};
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/lanbo/admin/manager/add',
		        data: {realName:realName,phone:phone,idCard:idCard,
		        	orderNumber:orderNumber,bankName:bankName,
		        	bankIdCard:bankIdCard,postalNumber:postalNumber,
		        	deliveryAddress:deliveryAddress,type:"add",userType:userType,provinceName:provinceName,provinceId:provinceId},
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

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	/*添加管理员信息 提交表单并校验表单字段*/
	$("#form-admin-add").validate({
		rules:{
			managerName:{
				required:true,
				minlength:4,
				maxlength:16
			},
			password:{
				required:true,
				minlength: 5
			},
			password2:{
				required:true,
				equalTo: "#password"
			},
			phone:{
				required:true,
				isPhone:true
			},
			email:{
				required:true,
				email:true
			},
			roleId:{
				required:true
			},
		messages: {
			managerName: {
			        minlength: "用户名必需由两个字母组成"
			      },
			      password: {
			        minlength: "密码长度不能小于 5 个字母"
			      },
			      password2: {
			        minlength: "密码长度不能小于 5 个字母",
			        equalTo: "两次密码输入不一致"
			      }
			    }
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post',
				url: "addManager" ,
				success: function(data){
					console.info(data)
					if(data.flag){
						layer.msg(data.msg,{icon:2,time:1000});
					}
				},
                error: function(XmlHttpRequest, textStatus, errorThrown){
					layer.msg('添加失败',{icon:1,time:1000});
				}
			});
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
	
});

function roledata(){
	$.ajax({
		type: 'get',
		dataType: 'json',
		url: '/tz/admin/role/findAllRole',
		success:function(data){
			//console.info(data);
			if(data.flag){
				//清空下拉表数据
				$("#roleId option").remove();
				 data = data.data;
				var html="<option value=''>请选择角色</option>";   
	            if(data != null && data.length > 0){  
	                for(var i=0; i<data.length; i++){  
	                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	                }  
	            }  
	            $("#roleId").append(html);  
			}
		},
		error: function(data){
			$.Message().alert("warning",data.msg);
		}
		
	});
}

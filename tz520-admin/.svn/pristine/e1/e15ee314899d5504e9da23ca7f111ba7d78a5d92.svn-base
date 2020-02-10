var id = $(".data_id").val();
$(function(){
	/*异步加载角色信息*/
	roledata();
	/*数据回显*/
	getDate();
	
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$('.submit_data').click(function(){
		var realName = $("#realName").val();
		var phone = $("#phone").val();
		var oldphone = $("#oldphone").val();
		var idCard = $("#idCard").val();
		
		var userType = $("#userType").val();
		var provinceName = $("#provinceName").val();
		var userType_val = $(".userType_val").val();
		var bankName = $("#bankName").val();
		var bankIdCard = $("#bankIdCard").val();
		var postalNumber = $("#postalNumber").val();
		var deliveryAddress = $("#deliveryAddress").val();
		var provinceId = $(".province_box").val();
		var isTeam_box = $(".isTeam_box").val();
		
		//空处理
		if(isEmpty(realName,'用户姓名不能为空！')){return false};
		if(isEmpty(phone,'手机不能为空！')){return false};
		if(isEmpty(userType,'用户类型不能为空！')){return false};
		if(isEmpty(provinceName,'省份不能为空！')){return false};
		if(isEmpty(provinceId,'请先选择代理省份！')){return false};
		if(isEmpty(bankName,'开户行名称不能为空！')){return false};
		if(isEmpty(bankIdCard,'银行卡号不能为空！')){return false};
		//手机格式
		if(isPhone(phone)){return false};
		//身份证
/*		if(isIdCard(idCard)){return false};	*/
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/lanbo/admin/user/add',
		        data: {realName:realName,phone:phone,idCard:idCard,
		        	bankName:bankName,bankIdCard:bankIdCard,postalNumber:postalNumber,
		        	deliveryAddress:deliveryAddress,type:"update"
		        		,userType:userType,provinceName:provinceName,oldphone:oldphone,userType_val:userType_val,provinceId:provinceId,isTeam:isTeam_box},
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
	})
});

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	/*提交表单并校验表单字段*/
	$("#form-admin-update").validate({
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
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post',
				url: "updateManager" ,
				success: function(data){
					layer.msg(data.msg,{icon:1,time:1000});
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



//update编辑页面异步加载数据回显
var id = $("#id").val();
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
            	$("#password").val(data.password);
            	$("#password2").val(data.password);
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

//异步加载角色
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

//获取订单id编辑物流信息
var orderId =$("#orderId").val();

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	//异步加载快递公司
	getExpressCom();

});


/*回显快递公司*/
function getExpressCom(){
	$.ajax({
		type: 'get',
		dataType: 'json',
		url: '/tz/admin/expressCom/queryExpressCom',
		success:function(data){
			//console.info(data);
			if(data.flag){
				//清空下拉表数据
				$("#com option").remove();
				 data = data.data;
				var html="<option value=''>请选择快递公司</option>";   
	            if(data != null && data.length > 0){  
	                for(var i=0; i<data.length; i++){  
	                    html+="<option value='"+data[i].com+"'>"+data[i].companyName+"</option>";
	                }  
	            }  
	            $("#com").append(html);  
	        	/*数据物流数据回显*/
	        	getOrderShippingDate();
			}
		},
		error: function(data){
			$.Message().alert("warning",data.msg);
		}
		
	});
}


//编辑修改物流信息异步加载订单物流数据回显

function getOrderShippingDate(){
	
	$.ajax({
        type: "POST",
        url: '/tz/admin/orderShipping/findOrderShippingById',
        data: {orderId:orderId},
        dataType: "json",
        success: function(data){
        	console.info(data);
            if(data.flag){
            	data = data.data;
            	$("#orderId").val(data.orderId);
            	$("#shippingCode").val(data.shippingCode);
            	$("#com option[value='"+data.com+"']").prop('selected',true);
            	$("#receiverName").val(data.receiverName);
            	$("#receiverPhone").val(data.receiverPhone);
            	$("#receiverMobile").val(data.receiverMobile);
            	$("#receiverState").val(data.receiverState);
            	$("#receiverCity").val(data.receiverCity);
            	$("#receiverDistrict").val(data.receiverDistrict);
            	$("#receiverAddress").val(data.receiverAddress);
            	$("#receiverZip").val(data.receiverZip);
            }	
	    	else{
	    		layer.msg(data.msg,{icon:5,time:1000});
		    }
        },  
        error:function(xhr,status,statusText){
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }  
    });
    
}

/*点击保存物流信息*/
//提交表单并校验表单字段
$('.submit_data').click(function(){
	
	var orderId = $("#orderId").val();
	var shippingCode = $("#shippingCode").val();
	var com =  $("#com").val();
	var receiverName=  $("#receiverName").val();
	var receiverPhone = $("#receiverPhone").val();
	var receiverMobile = $("#receiverMobile").val();
	var receiverState= $("#receiverState").val();
	var receiverCity = $("#receiverCity").val();
	var receiverDistrict = $("#receiverDistrict").val();
	var receiverAddress = $("#receiverAddress").val();
	var receiverZip = $("#receiverZip").val();
	
	if(isEmpty(shippingCode,'运单编号不能为空！')){return false};
	if(isEmpty(com,'货运公司不能为空！')){return false};
	if(isEmpty(receiverName,'收件人不能为空！')){return false};
	if(isEmpty(receiverMobile,'电话号码不能为空！')){return false};
	if(isEmpty(receiverState,'省份不能为空！')){return false};
	if(isEmpty(receiverCity,'城市不能为空！')){return false};
	if(isEmpty(receiverDistrict,'区县不能为空！')){return false};
	if(isEmpty(receiverAddress,'详细地址不能为空！')){return false};
	
	layer.confirm('确认数据检查无误并提交?',function(index){
		$.ajax({
			type: "POST",
			url: "/tz/admin/orderShipping/updateOrderShipping",
			data: {orderId:orderId,shippingCode:shippingCode,com:com,
				receiverName:receiverName,receiverPhone:receiverPhone,receiverMobile:receiverMobile,receiverState:receiverState,
				receiverCity:receiverCity,receiverDistrict:receiverDistrict,receiverAddress:receiverAddress,receiverZip:receiverZip},
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
						layer.msg(data.msg,{icon:1,time:6000});
						var index = parent.layer.getFrameIndex(window.name);
						//更新父页面订物流号
						$(window.parent.document).find('#shipping_'+orderId).text(shippingCode);
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

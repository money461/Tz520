var id = $(".data_id").val();
$(function(){
	getDate ();
})
//数据回显
function getDate (){
	$.ajax({
        type: "POST",
        url: '/lanbo/admin/user/selectById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
            	$(".createTime").text(data.user.createTime);
	    		$(".updateTime").text(data.user.updateTime);
	    		$(".sortNumber").text("订单数    :  "+data.user.orderNumber);
            	$(".realName").text(data.user.realName);
	    		$(".phone").text(data.user.phone);
	    		$(".idCard").text(data.user.idCard);
	    		
	    		$(".provinceName").text(data.user.provinceName);
	    		$(".dprovinceName").text(data.dprovinceName);
	    		var userType = data.user.userType;
	    		$(".team").text(data.user.isTeam);
	    		if(userType==1){
	    			$(".userType").text("股东");
	    		}else{
	    			$(".userType").text("团队");
	    		}
	    		$(".bankName").text(data.user.bankName);
	    		$(".bankIdCard").text(data.user.bankIdCard);
	    		$(".userName").text(data.user.userName);
	    		var postalNumber = "";
	    		if(data.user.postalNumber!=null){postalNumber = data.user.postalNumber}
	    		$(".postalNumber").text(
	    				postalNumber
	    	    		);
	    		var deliveryAddress = "";
	    		if(data.user.deliveryAddress!=null){deliveryAddress = data.user.deliveryAddress}
	    		
	    		$(".deliveryAddress").text(deliveryAddress);
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
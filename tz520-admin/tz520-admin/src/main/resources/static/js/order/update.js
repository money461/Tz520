var orderId = $("#orderId").val();
$(function(){
	
	/*数据回显*/
	getOrderDate();
	
});

//update编辑修改订单页面异步加载数据回显
function getOrderDate(){
	$.ajax({
        type: "POST",
        url: '/tz/admin/orderItem/findOrderItemById',
        data: {orderId:orderId},
        dataType: "json",
        success: function(data){
        	console.info(data)
        	var html="";//展示订单商品信息
        	
            if(data.flag){
            	var list = data.data;
            	var orderItemList=list[0];
            	
            	for(var i=0;i<orderItemList.length;i++){
            	 html +='<tr>'+
            	 '<td style="display:none;"><input typle="text" name="id" value='+orderItemList[i].id+'></td>'+
            	 '<td style="display:none;"><input typle="text" name="orderId" value='+orderItemList[i].orderId+'></td>'+
            	 '<td style="display:none;"><input typle="text" name="itemId" value='+orderItemList[i].itemId+'></td>'+
            	 '<td><input typle="text" disabled="disabled" name="itemTitle" value='+orderItemList[i].itemTitle+'></td>'+
       			 '<td><input typle="text"  name="price" value='+orderItemList[i].price+'>元</td>'+
       			 '<td><input typle="text" name="memberPrice" value='+orderItemList[i].memberPrice+'>元</td>'+
       			 '<td><input typle="text" name="num" value='+orderItemList[i].num+'>件/个</td>'+
       			 '<td><input typle="text" name="totalFee" value='+orderItemList[i].totalFee+'>元</td>'+
       			 '</tr>';
            	}
            	$(".data_box").append(html);
            	$("#postFee").val(list[1]);
            	$("#payment").val(list[2]);
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
	
/*更新表单*/
function orderItem_save_submit(){
	//通过将表格信息进行格式转换后存储在属性为hidden的输入框中，在后台进行存储的。
	//从一个tbody中获取数据并置入隐藏域  
	var tbody=$(".data_box");
	changeTbodyToDetail(tbody); 
	
	var postFee = $("input[name='postFee']").val();
	var payment = $("input[name='payment']").val();
	var sampleInfo = $("input[name='sampleInfo']").val();
	alert(sampleInfo);
	
	$.ajax({
		type:'post',
		dataType:'json',
		data:{sampleInfo:sampleInfo,postFee:postFee,payment:payment},
	    url:'/tz/admin/orderItem/updateOrderItem',
	    success: function(data){
	    	if(data.flag){
	    		var index = parent.layer.getFrameIndex(window.name);
	    		parent.$('.btn-refresh').click();
	    		parent.layer.close(index);
	    		layer.msg(data.msg,{icon:1,time:2000});
	    		
	    	}else{
	    		layer.msg('数据更新失败，请稍后重试！',{icon:5,time:1000});
	    	}
	    },
	    error:function(xhr,status,statusText){
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }  
	
	});
	
	
}

function changeTbodyToDetail(tbody) {  
    var result = "[";  
    tbody.find("tr").each(function (i,n) { 
    	var a="{";
    	$(n).find("td").each(function (index,element) { 
    		var name = $(element).find(":input").prop("name");
    		var val = $(element).find(":input").val();
    	     a+= name + ":\"" + val + "\",";  
    	 }); 
    	a= a.substring(0,a.length-1);
    	a+="},";
    	result+=a;

    });
    result = result.substring(0,result.length - 1);
    result+="]";
    $("input[name='sampleInfo']").val(result);
}  






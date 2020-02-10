var id = $("#orderId").val();
$(function(){
	getOrderDetailDate ();
})
//订单详情数据回显
function getOrderDetailDate(){
	$.ajax({
		type: "POST",
		url: '/tz/admin/order/findOrderDetail',
		data: {id:id},
		dataType: "json",
		success: function(data){
			console.info(data);
			if(data.flag){
				   order = data.data;
					$("#id").text(order.id);
					$("#mallId").text(order.mallId);
					$("#shippingCode").text(isNull(order.shippingCode));
					$("#userId").text(order.userId);
					$("#buyerNick").text(isNull(order.buyerNick));
					$("#account").text(isNull(order.account));
					$("#operater").text(isNull(order.operater));
					$("#companyName").text(isNull(order.companyName));
					if(order.paymentType==1){
						$("#paymentType").text('支付宝');
					}else if(order.paymentType==2){
						$("#paymentType").text('微信支付');
						
					}else if(order.paymentType==3){
						$("#paymentType").text('网银支付');
					}else if(paymentType==4){
						$("#paymentType").text('爱心值支付');
					}
					
					if(order.type==1){
						$("#type").text('购物订单');
					}else if(order.type==0){
						$("#type").text('会员升级');
					}
					if(order.status==0){
						$("#status").text('订单已取消');
					}else if(order.status==1){
						$("#status").text('未付款');
					}else if(order.status==2){
						$("#status").text('已付款请发货');
					}else if(order.status==3){
						$("#status").text('已发货');
					}else if(order.status==4){
						$("#status").text('交易完成');
					}
					else if(order.status==5){
						$("#status").text('交易关闭');
					}
					else if(order.status==6){
						$("#status").text('已退款');
					}
					$("#createdTime").text(order.createdTime);
					$("#updatedTime").text(order.updatedTime);
					$("#paymentTime").text(isNull(order.paymentTime));
					$("#consignTime").text(isNull(order.consignTime));
					$("#endTime").text(isNull(order.endTime));
					$("#closeTime").text(isNull(order.closeTime));
					$("#buyerMessage").text(isNull(order.buyerMessage));
					$("#postFee").text(order.postFee);
					$("#loveValue").text(order.loveValue);
					$("#orderNum").text(order.orderNum);
					$("#payment").text(order.payment);
					if(order.buyerRate==1){
						$("#buyerRate").text('已评价');
					}else if(order.buyerRate==0){
						$("#buyerRate").text('未评价');
						
					}
					var html="";//展示订单商品信息
					var orderItemList = order.orderItems;
					
					for(var i=0; i<orderItemList.length;i++){
						html +='<tr>'+
						'<td>'+orderItemList[i].itemId+'</td>'+
						'<td><div><strong>'+orderItemList[i].itemTitle+'</div></strong></td>'+
						'<td>'+orderItemList[i].price+'</td>'+
						'<td>'+orderItemList[i].memberPrice+'</td>'+
						'<td>'+orderItemList[i].realPrice+'</td>'+
						'<td>'+orderItemList[i].num+'</td>'+
						'<td>'+orderItemList[i].itemPost+'</td>'+
						'<td>'+orderItemList[i].totalFee+'</td>'+
						'</tr>';
					}
					$(".data_box").append(html);
					
					var orderShipping = order.orderShipping;
					$("#receiverName").text(isNull(orderShipping.receiverName));
					$("#receiverPhone").text(isNull(orderShipping.receiverPhone));
					$("#receiverMobile").text(isNull(orderShipping.receiverMobile));
					$("#receiverState").text(isNull(orderShipping.receiverState));
					$("#receiverCity").text(isNull(orderShipping.receiverCity));
					$("#receiverDistrict").text(isNull(orderShipping.receiverDistrict));
					$("#receiverAddress").text(isNull(orderShipping.receiverAddress));
					$("#receiverZip").text(isNull(orderShipping.receiverZip));
					
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
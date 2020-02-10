//每页显示10条
var rows = 10;//每页展示的条数
var curPage = 1;
$(function(){
	//运行
  	init.getList(curPage,rows);  
})
var init = {
 getList:function(curPage,rows) {
	 
	 var startTime = $("#startTime").val(); //开始时间
	 var endTime = $("#endTime").val(); //结束时间
	 var id = $("#orderId").val();  //订单编号
	 var status = $('select  option:selected').val(); //商品状态
	 
	 //当查询未发货订单时，可选择批量发货
	 if(status==2){
		 $("#batchSend").html('<input class="btn btn-primary radius" type="button" value="批量发货" onclick="batch_send()">');
	 }
	 
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/admin/order/list",//后端处理数据，返回json格式
        data: {"rows": rows, "curPage": curPage,id:id,status:status,startTime:startTime,endTime:endTime},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = ""; //展示信息
 	      	$(".data_box").empty();
	        var list = data.data.list;
//	        console.info(list);
	        $(".data_count").text(data.data.total);
	       
            if (list != null && list.length > 0) {
                for (var x = 0; x < list.length; x++) {
                	var status=""; //订单状态   0、取消订单，1、未付款，2、已付款待发货、3、已发货，4、交易成功，5、交易关闭,6、已退款
                	var paymentType=""; //支付方式 1.支付宝支付 2. 微信支付 3.网银支付 4.爱心值支付
                	var span = ""; //描述订单状态
                	var span2 =""; //描述付款方式
                	var span3 =""; //描述操作标识
                	var shippingCode="";//运单编号
                	var span4 ="";//标识是运单状况
                	var span5 ="";//标识订单属性 0 会员升级 1 购物
                	
                	//其他字段若为空，则不显示null
                	var closeTime =  list[x].closeTime == null ? "":list[x].closeTime;
                	var payment = list[x].payment ==null ? "":list[x].payment;
                	var orderNum = list[x].orderNum == null ? "":list[x].orderNum;
                	var  operater = list[x].operater==null ?"":list[x].operater;
                	var buyerNick = list[x].buyerNick == null ?"":list[x].buyerNick;
                	paymentType = list[x].paymentType;
                	status = list[x].status;
                	shippingCode=list[x].shippingCode;
                	type=list[x].type;
                	
                	if(paymentType==1){
                	    span2='<span class="label label-success radius">支付宝支付</span>';
                	}else if(paymentType==2){
                		span2='<span class="label label-success radius">微信支付</span>';
                	}else if(paymentType==3){
                		span2='<span class="label label-success radius">网银支付</span>';
                	}else if(paymentType==4){
                		span2='<span class="label label-success radius">爱心值支付</span>';
                	}
                	
                	if(type==1){
                		span5='<span class="label label-success radius">购物订单</span>';
                	}else if(type==0){
                		span5='<span class="label label-success radius">会员升级</span>';
                	}
                	
         			if(status == 0){
         				span='<span class="label label-danger radius">订单已取消</span>';
         			}else if(status==1){
         				span='<span class="label label-primary radius">未付款</span>';
         				span3='<a title="修改订单" href="javascript:;" onclick="order_edit(\'修改订单\',\''+list[x].id+'\')" style="text-decoration:none"><span class="label label-primary radius">修改订单</span></a>'
   				             +'<a title="取消订单" href="javascript:;" onclick="order_cancel(this,\''+list[x].id+'\')"  style="text-decoration:none"><span class="label label-primary radius">取消订单</span></a>';
         			}else if(status==2){
         				span='<a title="发货" href="javascript:;" onclick="send(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><span class="label label-warning radius">已付款请发货</span></a>';
         				span3='<a title="编辑物流" href="javascript:;" onclick="delivery_edit(\'编辑物流\',\''+list[x].id+'\')" style="text-decoration:none"><span class="label label-primary radius">编辑物流</span></a>'
         				       +'<a title="退款" href="javascript:;" onclick="drawback(this,\''+list[x].id+'\')"  style="text-decoration:none"><span class="label label-primary radius">退款</span></a>';
         				if(shippingCode==''|| shippingCode==null){
             				span4='<a title="编辑物流" href="javascript:;" onclick="delivery_edit(\'编辑物流\',\''+list[x].id+'\')" style="text-decoration:none"><span class="label label-danger radius">未填写物流单号</span></a>';
             			}else{
             				span4=shippingCode;
             			}
         			}else if(status==3){
                	    span='<span class="label label-secondary radius">已发货</span>';
                	    span3='<a title="修改物流" href="javascript:;" onclick="delivery_edit(\'修改物流\',\''+list[x].id+'\')" style="text-decoration:none"><span class="label label-primary radius">修改物流</span></a>';
                	    if(shippingCode==''|| shippingCode==null){
                	    	span4='<a title="编辑物流" href="javascript:;" onclick="delivery_edit(\'编辑物流\',\''+list[x].id+'\')" style="text-decoration:none"><span class="label label-danger radius">未填写物流单号</span></a>';
             			}else{
             				span4=shippingCode;
             			}
                   }else if(status==4){
                	    span='<span class="label label-secondary radius">交易完成</span>';
                	    if(shippingCode==''|| shippingCode==null){
                	    	span4="";
                	    }else{
                	    	span4=shippingCode;
                	    }
                   }else if(status==5){
                	    span='<span class="label label-success radius">交易关闭</span>';
                	    if(shippingCode==''|| shippingCode==null){
                	    	span4="";
                	    }
                   }else if(status==6){
	                	span='<span class="label label-danger radius">已退款</span>';
	                	 if(shippingCode==''|| shippingCode==null){
	                	    	span4="";
	                	    }else{
	                	    	span4=shippingCode;
	                	    }
	                }
         			html += '<tr><td id="order_'+list[x].id+'\"><input value=\"'+list[x].id+'\" name="orderId" type="checkbox"/></td>'+
         			'<td>'+list[x].id+'</td>'+
    				'<td class="td-userId">'+list[x].userId+'</td>'+
    				'<td>'+span4+'</td>'+
    				'<td class="status_ql">'+span+'</td>'+
    				'<td>'+payment+'</td>'+
    				'<td>'+list[x].loveValue+'</td>'+
    				'<td>'+orderNum+'</td>'+
    				'<td>'+span2+'</td>'+
    				'<td>'+span5+'</td>'+
    				'<td>'+list[x].createdTime+'</td>'+
    				'<td>'+list[x].updatedTime+'</td>'+
    				'<td><input class="btn btn-primary radius" type="button" value="订单详情" onclick="show_orderDetail(\'订单详情\',\''+list[x].id+'\')"></td>'+
    				'<td class=td-manage>'+span3+'</td>'+'</tr>';
    			}
        		$(".data_box").append(html);
    			init.jsonpage(data.data.pages,data.data.pageNum);
    			$("#project_page").css("display","block");	
    			$(".ml_content_false").css("display","none");	
    			
    	  }else {
        	  $("#project_page").css("display","none");
        	  $(".ml_content_false").css("display","block");	
               //无数据;
            }
        },  
        error:function(xhr,status,statusText){
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }
    });
},
jsonpage:function (pages, curPage) {
    var laypageindex = laypage({
         cont: 'project_page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>  
         pages: pages, //通过后台拿到的总页数  
         curr: curPage, //初始化当前页  
         skin: '#5a98de',//皮肤颜色  
         groups: 5, //连续显示分页数  
         skip: true, //是否开启跳页  
         first: '首页', //若不显示，设置false即可  
         last: '尾页', //若不显示，设置false即可  
         //prev: '<', //若不显示，设置false即可  
         //next: '>', //若不显示，设置false即可  
        jump: function (obj, first) { 
            //触发分页后的回调
            if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
            	curPage = obj.curr; 
            	init.getList(obj.curr,rows);
            }
        }
    });
}
}

function flush(){
	layer.msg('操作成功！',{icon:6,time:1000});
	setTimeout("window.location.reload()",1000)
}  
$(".search_btn").click(function(){
	init.getList(1,rows);  
})

/*产品-查看
function product_show(title,id){
	var  url="商品展示页面路径？id="id;
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}*/



/*订单-编辑*/
function order_edit(title,id){
//	alert(id);
	url = "/tz/admin/order/OrderAlterPage?type=edit&id="+id;
	layer_show(title,url);
}

/*物流-编辑、修改*/
function delivery_edit(title,id){
//	alert(id);
	url = "/tz/admin/order/OrderAlterPage?type=delivery&id="+id;
	layer_show(title,url);
}

/*订单详情展示*/
function show_orderDetail(title,id){
	url = "/tz/admin/order/OrderAlterPage?type=detail&id="+id;
	layer_show(title,url);
}


/*发货 */
function send(obj,id){
	//获取该订单的userId
	var userId=$.trim($(obj).parents("tr").find(".td-userId").text());
	layer.confirm('确定要发货了吗？id='+id,function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/admin/order/alterOrder',
			data: {type:"send",id:id,userId:userId},
			dataType: 'json',
			success: function(data){
				if(data.flag){
					$(obj).parents("tr").find(".td-manage").html('<a title="修改物流" href="javascript:;" onclick="delivery_edit(\'修改物流\',\''+id+'\')" style="text-decoration:none"><span class="label label-primary radius">修改物流</span></a>');
					$(obj).parents("tr").find(".status_ql").html('<span class="label label-secondary radius">已发货</span>');
					layer.msg(data.msg,{icon:1,time:1000});
				}	
				else{
					layer.msg('发货失败，请稍后重试！',{icon:5,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			}
		});		
	}); 
}

/*订单批量发货*/
function batch_send(){
	var idstr = new Array();
	var userIdStr = new Array();
	var id="";
	var userId="";  //用户id
	$("input[name='orderId']").each(function(i,n){
		if($(n).prop("checked")){
		//将被选中的商品pid拼接字符串
			idstr.push($(n).val());
		 //获取userId]
			userIdStr.push($.trim($(n).parents("tr").find(".td-userId").text()));
		}	
	});
	id=idstr.join(",");
	userId=unique(userIdStr).join(",");
	
	if(id.length==0){
		//提示需要勾选至少一个商品
		layer.msg("请至少勾选一个需要发货的商品",{icon:2,time:1500});
		return;
	}else{
		layer.confirm('确认要批量发货选中的商品吗？id='+id,function(index){
			$.ajax({
				type: 'POST',
				url: '/tz/admin/order/alterOrder',
				data: {type:"send",id:id,userId:userId},
				dataType: 'json',
				success: function(data){
					if(data.flag){
						layer.msg(data.msg,{icon:1,time:1000});
						//刷新页面
						for(var i=0;i<idstr.length;i++){
							$("#order_"+idstr[i]).parents("tr").remove();
							layer.msg(data.msg,{icon:1,time:1000});
						}
					}	
					else{
						layer.msg('删除失败，请稍后重试！',{icon:5,time:1000});
					}
				},
				error:function(data) {
					console.log(data.msg);
				}
			});		
		}); 
	}
	$("input[name='orderId']").prop("checked",false);
}

/*订单取消*/
function order_cancel(obj,id){
	layer.confirm('确定取消该订单吗？id='+id,function(index){
		$.ajax({
			type:'post',
			url:'/tz/admin/order/alterOrder',
			data:{type:"orderCancel",id:id},
			dataType:'json',
			success:function(data){
	            if(data.flag){
	            	$(obj).parents("tr").find(".status_ql").html('<span class="label label-danger radius">订单已经取消</span>');
	            	$(obj).parents("tr").find(".td-manage").html("");
					layer.msg(data.msg,{icon:1,time:1000});
	             }	
		    	else{
		    		layer.msg('下架失败，请稍后重试！',{icon:5,time:1000});
			    }
			},
			error:function(data) {
				console.log(data.msg);
			}
		});
	});
}

/*退款*/
function drawback(obj,id){
	layer.confirm('确定对该订单退款吗？id='+id,function(index){
		$.ajax({
			type:'post',
			url:'/tz/admin/order/alterOrder',
			data:{type:"drawback",id:id},
			dataType:'json',
			success:function(data){
	            if(data.flag){
	            	$(obj).parents("tr").find(".status_ql").html('<span class="label label-danger radius">已退款</span>');
	            	$(obj).parents("tr").find(".td-manage").html("");
					layer.msg(data.msg,{icon:1,time:1000});
	             }	
		    	else{
		    		layer.msg('退款失败，请稍后重试！',{icon:5,time:1000});
			    }
			},
			error:function(data) {
				console.log(data.msg);
			}
		});
	});

}


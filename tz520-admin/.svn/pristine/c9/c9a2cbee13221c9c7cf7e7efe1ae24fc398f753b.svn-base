//每页显示10条
var rows = 10;//每页展示的条数
var curPage = 1;
$(function(){
	//运行
  	init.getList(curPage,rows);  
})
var init = {
 getList:function(curPage,rows) {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var userNick = $("#userNick").val();
	var selectType = $("#selectType").val();
	var selectType2 = $("#selectType2").val();
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/admin/recommend/orderConsumerList",//后端处理数据，返回json格式
        data: {"rows":rows, "curPage":curPage,startTime:startTime,endTime:endTime,
        	userNick:userNick},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = "";
 	      	$(".data_box").empty();
	        var list = data.data.list;
	        console.info(list);
	        $(".data_count").text(data.data.total);
	        $(".total").text(data.data.navigatePages);
            if (list != null && list.length > 0) {
            	 for (var x = 0; x < list.length; x++) {
         			html += '<tr>'+
         			'<td><a class="maincolor" href="javascript:;" onClick="detail(\''+isNull(list[x].id)+'\')">'+isNull(list[x].id)+'</a></td>'+		
    				'<td>'+isNull(list[x].userNick)+'</td>'+		
    				'<td>'+isNull(list[x].userPhone)+'</td>'+
    				'<td>'+isNull(list[x].comboNum*100)+'</td>'+		
    				'<td>'+isNull(list[x].paySingleItem)+'</td>'+
    				'<td>'+list[x].createdTime+'</td>'+
    				'<td>'+list[x].updatedTime+'</td>'+
    				'<td>'+list[x].paymentTime+'</td>'+
    				'</tr>';
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
function detail(id){
	var url =  "/tz/admin/recommend/orderConsumerDetialPage?orderId="+id;
	var index = layer.open({
		type: 2,
		title: "订单详情",
		content: url
	});
	layer.full(index);
}
/*用户-推荐结算*/
function user_settlement(obj,id){
 	layer.confirm('确认要结算吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/admin/recommend/settlement',
			data: {id:id},
			dataType: 'json',
			beforeSend: function () {
		            //防止重复提交
		        	 $(".layui-layer-shade").css({'display':'none'});
		        	 $(".layui-layer-dialog").css({'display':'none'});
		        	 loading.loadingShow();
		        },
			success: function(data){
				loading.loadingHide();
	            if(data.flag){
	            	$(obj).parents("tr").find(".status_ml").html('<span class="label label-success radius">已处理</span>');
            		$(obj).parents("tr").find(".caozuo_ml").html('<a title="删除" href="javascript:;" onclick="user_del(this,\''+id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>');
					layer.msg('已结算!',{icon:1,time:1000});
	             }	
		    	else{
		    		layer.msg(data.mag,{icon:5,time:1000});
			    }
			},
			error:function(data) {
				loading.loadingHide();
				if(xhr.status == 500)
	        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
			},
		});		
	}); 
}
/*用户-推荐删除*/
function user_del(obj,id){
 	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/admin/recommend/deleteById',
			data: {id:id},
			dataType: 'json',
			beforeSend: function () {
		            //防止重复提交
		        	 $(".layui-layer-shade").css({'display':'none'});
		        	 $(".layui-layer-dialog").css({'display':'none'});
		        	 loading.loadingShow();
		        },
			success: function(data){
				loading.loadingHide();
	            if(data.flag){
	            	$(obj).parents("tr").remove();
	            	init.getList(curPage,rows); 
					layer.msg('已删除!',{icon:1,time:1000});
	             }	
		    	else{
		    		layer.msg(data.mag,{icon:5,time:1000});
			    }
			},
			error:function(data) {
				loading.loadingHide();
				if(xhr.status == 500)
	        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
			},
		});		
	}); 
}

$("#selectType").change(function(){  
    //添加所需要执行的操作代码  
/*	var selectType = $("#selectType").val();*/
	init.getList(curPage,rows); 
    })
$("#selectType2").change(function(){  
    //添加所需要执行的操作代码  
/*	var selectType = $("#selectType").val();*/
	init.getList(curPage,rows); 
    })
    
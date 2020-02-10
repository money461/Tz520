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
	var name = $("#name").val();
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/admin/dictionary/list",//后端处理数据，返回json格式
        data: {"rows": rows, "curPage": curPage,"name":name},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = "";//数据内容
        	var span="";//操作
        	var spanType = "";//字典类型
 	      	$(".data_box").empty();
	        var list = data.data.list;
	        console.info(list);
	        $(".data_count").text(data.data.total);
            if (list != null && list.length > 0) {
                for (var x = 0; x < list.length; x++) {
                	var type=list[x].type;
                	if(type==0){
                		spanType ="<span>权限类型</span>"; 
                	}else if(type==1){
                		spanType="<span>角色类型</span>";
                	}else if(type==2){
                		spanType="<span>城市类型</span>";
                	}
                	
                	span='<a title="编辑" href="javascript:;" onclick="dictionary_edit(\'编辑数据\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
				       +'<a title="删除" href="javascript:;" onclick="dictionary_del(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><i id="del" class="Hui-iconfont">&#xe6e2;</i></a>';
         			html += '<tr>'+
    				'<td>'+list[x].id+'</td>'+		
    				'<td>'+spanType+'</td>'+
    				'<td>'+list[x].name+'</td>'+
    				'<td>'+list[x].code+'</td>'+
    				'<td>'+list[x].module+'</td>'+
    				'<td>'+list[x].operater+'</td>'+
    				'<td>'+list[x].createdTime+'</td>'+
    				'<td>'+list[x].updatedTime+'</td>'+
    				'<td>'+span+'</td>'+
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
/*数据-增加*/
function dictionary_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*数据-编辑*/
function dictionary_edit(title,id,w,h){
	url =  "/tz/admin/dictionary/addOrUpdatePage?type=update&id="+id;
	layer_show(title,url,w,h);
}
/*用户-查看
function user_detail(title,id,w,h){
	url =  "/tz/admin/user/addOrUpdatePage?type=detail&id="+id;
	layer_show(title,url,w,h);
}*/

/*数据-删除*/
function dictionary_del(obj,id){
 	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/admin/dictionary/deleteDictionaryById',
			data: {id:id},
			dataType: 'json',
			success: function(data){
	            if(data.flag){
	            	$(obj).parents("tr").remove();
	            	init.getList(curPage,rows); 
					layer.msg(data.msg,{icon:1,time:1000});
	             }	
		    	else{
		    		layer.msg('删除失败，请稍后重试！',{icon:5,time:1000});
			    }
			},
			error:function(data) {
				if(xhr.status == 500)
	        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
			},
		});		
	}); 
}
/*用户-用户停用或者启用*/
function user_stop(obj,id,status,message){
 	layer.confirm('确认'+message+'要此账号吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/admin/user/updateStatusById',
			data: {id:id,status:status},
			dataType: 'json',
			success: function(data){
	            if(data.flag){ 
	            	var statusHtml = ""; 
	            	if(status == 1){
	            		statusHtml='<a style="text-decoration:none" onClick="user_stop(this,\''+id+'\',\''+status_0+'\',\''+status_msg0+'\')" href="javascript:;" title="冻结"><i class="Hui-iconfont">&#xe631;</i></a>'+
	            			'<a title="我的推荐" href="javascript:;" onclick="user_recommend_list(\''+recommend+'\',\''+id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe611;</i></a>'+
	            			'<a title="团队" href="javascript:;" onclick="user_city_list(\''+userCity2+'\',\''+id+'\',\''+team+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe62b;</i></a>'+
	            			'<a title="城市合伙人" href="javascript:;" onclick="user_city_list(\''+userCity+'\',\''+id+'\',\''+partnership+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe653;</i></a>'+
	            			
							'<a title="编辑" href="javascript:;" onclick="user_edit(\''+edit+'\',\''+id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'+
							'<a title="删除" href="javascript:;" onclick="user_del(this,\''+id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
	            		$(obj).parents("tr").find(".status_ml").html('<span class="label label-success radius">已启用</span>');
	            		$(obj).parents("tr").find(".caozuo_ml").html(""+statusHtml+"");
	            	}else{
	            		statusHtml='<a style="text-decoration:none" onClick="user_stop(this,\''+id+'\',\''+status_1+'\',\''+status_msg1+'\')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe601;</i></a>'+
	            			'<a title="我的推荐" href="javascript:;" onclick="user_recommend_list(\''+recommend+'\',\''+id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe611;</i></a>'+
	            			'<a title="团队" href="javascript:;" onclick="user_city_list(\''+userCity2+'\',\''+id+'\',\''+team+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe62b;</i></a>'+
	            			'<a title="城市合伙人" href="javascript:;" onclick="user_city_list(\''+userCity+'\',\''+id+'\',\''+partnership+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe653;</i></a>'+
	            			
							'<a title="编辑" href="javascript:;" onclick="user_edit(\''+edit+'\',\''+id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'+
							'<a title="删除" href="javascript:;" onclick="user_del(this,\''+id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
         				$(obj).parents("tr").find(".status_ml").html('<span class="label label-warning radius">已冻结</span>');
         				$(obj).parents("tr").find(".caozuo_ml").html(""+statusHtml+"");
         			}
					layer.msg(data.msg,{icon:1,time:1500});
	             }	
		    	else{
		    		layer.msg(data.msg,{icon:5,time:1500});
			    }
			},
			error:function(data) {
				if(xhr.status == 500)
	        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
			},
		});		
	}); 
}
/*推荐人-列表*/
function user_recommend_list(title,id){
	var url = "/tz/admin/recommend/listPage?id="+id;
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*团队 城市合伙人代理-列表*/
function user_city_list(title,id,type){
	var url = "/tz/admin/userCity/listPage?type="+type+"&userId="+id;
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*团队管理-列表*/
function team_list(title,phone){
	var url = "/lanbo/admin/st/listPage?phone="+phone;
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
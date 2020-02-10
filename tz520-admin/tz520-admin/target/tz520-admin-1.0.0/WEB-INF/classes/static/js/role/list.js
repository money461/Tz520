//每页显示10条
var rows = 10;//每页展示的条数
var curPage = 1;
$(function(){
	//运行
  	init.getList(curPage,rows);  
})
var init = {
 getList:function(curPage,rows) {
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/admin/role/list",//后端处理数据，返回json格式
        data: {"rows": rows, "curPage": curPage},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = "";
 	      	$(".data_box").empty();
	        var list = data.data.list;
	        console.info(list);
	        $(".data_count").text(data.data.total);
            if (list != null && list.length > 0) {
                for (var x = 0; x < list.length; x++) {
//         			var id = list[x].id;
         			var title='角色编辑';
         			html += '<tr><td>'+list[x].id+'</td>'+
    				'<td>'+list[x].name+'</td>'+
    				'<td>'+list[x].code+'</td>'+
    				'<td>'+list[x].description+'</td>'+
    				'<td>'+list[x].operater+'</td>'+
    				'<td>'+list[x].createdTime+'</td>'+
    				'<td>'+list[x].updatedTime+'</td>'+
    				'<td>'+
    				'<a title="编辑" href="javascript:;" onclick="admin_role_edit(\''+title+'\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'+
    				'<a title="删除" href="javascript:;" onclick="admin_role_del(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>'+
    				'</td>'
    				+'</tr>';
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
/*角色-增加*/
function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*角色-编辑根据角色id回显数据*/
function admin_role_edit(title,id){
	url = "/tz/admin/role/addOrUpdatePage?type=update&id="+id;
	layer_show(title,url);
}
/*角色-查看
function user_detail(title,id,w,h){
	url =  "/lanbo/admin/user/addOrUpdatePage?type=detail&id="+id;
	layer_show(title,url,w,h);
}*/
/*角色-删除*/
function admin_role_del(obj,id){
 	layer.confirm('确认要删除吗？',function(index){
 		alert(id);
		$.ajax({
			type: 'POST',
			url: '/tz/admin/role/deleteById',
			data: {id:id},
			dataType: 'json',
			success: function(data){
	            if(data.flag){
	            	$(obj).parents("tr").remove();
	            	init.getList(curPage,rows); 
					layer.msg(data.msg,{icon:1,time:1000});
	             }	
		    	else{
		    		$(obj).parents("tr").remove();
		    		layer.msg('删除失败，请稍后重试！',{icon:5,time:1000});
			    }
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	}); 
}
/*用户-设为团队领导*/
function user_teamLeader(obj,phone){
 	layer.confirm('确认要设为团队领导吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/lanbo/admin/user/setTeam',
			data: {phone:phone},
			dataType: 'json',
			success: function(data){
	            if(data.flag){
	            	init.getList(curPage,rows); 
					layer.msg(data.msg,{icon:1,time:1500});
	             }	
		    	else{
		    		layer.msg(data.msg,{icon:5,time:1500});
			    }
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	}); 
}
/*推荐人-列表*/
function tuijian_list(title,phone){
	
	var url = "/lanbo/admin/userRecommend/listPage?phone="+phone;
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*省份代理-列表*/
function shengfen_list(title,phone,userType){
	
	var url = "/lanbo/admin/province/user/listPage?phone="+phone+"&userType="+userType;
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
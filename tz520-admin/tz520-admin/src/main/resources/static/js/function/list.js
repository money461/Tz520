//每页显示10条
var rows = 10;//每页展示的条数
var curPage = 1;
$(function(){
	//运行
  	init.getList(curPage,rows);  
})
var init = {
 getList:function(curPage,rows) {
	 
	 var name=$("#name").val();
	 var code=$("#code").val();
	 var startTime = $("#startTime").val();
	 var endTime =$("#endTime").val();
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/admin/function/list",//后端处理数据，返回json格式
        data: {"rows": rows, "curPage": curPage,name:name,code:code,startTime:startTime,endTime:endTime},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = "";
        	var span="";
 	      	$(".data_box").empty();
	        var list = data.data.list;
	        console.info(list);
	        $(".data_count").text(data.data.total);
            if (list != null && list.length > 0) {
                for (var x = 0; x < list.length; x++) {
                	var title='权限编辑';
                	if(list[x].generatemenu==1){
                		span="<span class=\"c-red\" >是</span>";
                	}else{
                		span="<span>否</span>"
                	}
                	
         			html += '<tr><td>'+list[x].id+'</td>'+
    				'<td>'+isNull(list[x].name)+'</td>'+
    				'<td>'+isNull(list[x].code)+'</td>'+
    				'<td>'+isNull(list[x].description)+'</td>'+
    				'<td>'+isNull(list[x].page)+'</td>'+
    				'<td>'+span+'</td>'+
    				'<td>'+isNull(list[x].zindex)+'</td>'+
    				'<td>'+isNull(list[x].createdTime)+'</td>'+
    				'<td>'+isNull(list[x].updatedTime)+'</td><td>'+
    				'<a title="编辑" href="javascript:;" onclick="admin_function_edit(\''+title+'\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'+
    				'<a title="删除" href="javascript:;" onclick="admin_function_del(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>'
    				+'</td></tr>';
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
/*权限-增加*/
function admin_function_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*权限-编辑*/
function admin_function_edit(title,id){
	url =  "/tz/admin/function/addOrUpdatePage?type=update&id="+id;
	layer_show(title,url);
}

/*权限-删除*/
function admin_function_del(obj,id){
 	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'Get',
			url: '/tz/admin/function/deleteFunctionById',
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


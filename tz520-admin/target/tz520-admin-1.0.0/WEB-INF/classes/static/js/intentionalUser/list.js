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
	var phone = $("#phone").val();
	var userName = $("#userName").val();
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/tzIntentionalUser/list",//后端处理数据，返回json格式
        data: {"rows": rows, "curPage": curPage,startTime:startTime,endTime:endTime,phone:phone,userName:userName},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = "";
        	var sexhtml="";
 	      	$(".data_box").empty();
	        var list = data.data.list;
	        console.info(list);
	        $(".data_count").text(data.data.total);
            if (list != null && list.length > 0) {
                for (var x = 0; x < list.length; x++) {
         			sex = list[x].sex;
         			if(sex==0){
         				sexhtml = "男";
         			}else{
         				sexhtml = "女";
         			}
         			
         			html += '<tr>'+
         			'<td>'+isNull(list[x].userName)+'</td>'+
    				'<td>'+list[x].phone+'</td>'+		
    				'<td>'+isNull(sexhtml)+'</td>'+
    				'<td>'+isNull(list[x].age)+'</td>'+		
    				'<td>'+isNull(list[x].agentCity)+'</td>'+		
    				'<td>'+isNull(list[x].address)+'</td>'+		
    				'<td>'+isNull(list[x].email)+'</td>'+		
    				'<td>'+isNull(list[x].createdTime)+'</td>'+
    				'<td>'+isNull(list[x].updatedTime)+'</td>'+
    				'<td class="td-manage caozuo_ml">'+
    				'<a title="删除" href="javascript:;" onclick="user_del(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>'+
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
/*用户-增加*/
function user_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-编辑*/
function user_edit(title,id,w,h){
	url =  "/tz/admin/user/addOrUpdatePage?type=update&id="+id;
	layer_show(title,url,w,h);
}
/*用户-查看*/
function user_detail(title,id,w,h){
	url =  "/tz/admin/user/addOrUpdatePage?type=detail&id="+id;
	layer_show(title,url,w,h);
}
/*用户-删除*/
function user_del(obj,id){
 	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/tzIntentionalUser/delIntentionalUserById',
			data: {id:id},
			dataType: 'json',
			success: function(data){
	            if(data.flag){
	            	$(obj).parents("tr").remove();
	            	init.getList(curPage,rows); 
					layer.msg('已删除!',{icon:1,time:1000});
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

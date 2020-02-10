//每页显示10条
var rows = 10;//每页展示的条数
var curPage = 1;
$(function(){
	//运行
  	init.getList(curPage,rows);  
  	
  	//获取分类目录数据
  	getCategory();
})

var init = {
 getList:function(curPage,rows) {
	 var startTime = $("#startTime").val(); //开始时间
	 var endTime = $("#endTime").val(); //结束时间
	 var contentTitle = $("#contentTitle").val();  //大标题
	 var subTitle = $("#subTitle").val(); //小标题
	 var contentCategoryId = $('select[name="category"] option:selected').val(); //内容分类
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/admin/tzContent/list",//后端处理数据，返回json格式
        data: {"rows": rows, "curPage": curPage,startTime:startTime,endTime:endTime,contentTitle:contentTitle,subTitle:subTitle,contentCategoryId:contentCategoryId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = "";//具体内容
        	var span =""; //修改编辑删除操作
 	      	$(".data_box").empty();
	        var list = data.data.list;
	        console.info(list);
	        $(".data_count").text(data.data.total);
	       
            if (list != null && list.length > 0) {
                for (var x = 0; x < list.length; x++) {
         			
         			span= '<a title="编辑更新" href="javascript:;" onclick="content_edit(\'内容编辑\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
         				    +'<a title="上传广告图片2" href="javascript:;" onclick="content_image(\'上传广告图片2\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>'
   				             +'<a title="删除" href="javascript:;" onclick="content_del(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><i id="del" class="Hui-iconfont">&#xe6e2;</i></a>';
   				         
         			
         			html += '<tr><td id="content_'+list[x].id+'\"><input value=\"'+list[x].id+'\" name="contentId" type="checkbox"/></td>'+
    				'<td>'+isNull(list[x].categoryName)+'</td>'+
    				'<td><a href="javascript:;" onclick="content_detail(\'内容详情\',\''+list[x].id+'\')"><span class="label label-success radius">'+isNull(list[x].contentTitle)+'</span></a></td>'+
    				'<td>'+isNull(list[x].subTitle)+'</td>'+
    				'<td>'+isNull(list[x].titleDesc)+'</td>'+
    				'<td>'+isNull(list[x].url)+'</td>'+
    				'<td>'+isNull(list[x].contentDesc)+'</td>'+
    				'<td>'+isNull(list[x].operater)+'</td>'+
    				'<td>'+isNull(list[x].createdTime)+'</td>'+
    				'<td>'+isNull(list[x].updatedTime)+'</td>'+
    				'<td class=td-manage>'+span+'</td>'+'</tr>';
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


/*内容-增加*/
function content_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*内容-编辑*/
function content_edit(title,id){
//	alert(id);
	url = "/tz/admin/tzContent/contentAlterPage?type=edit&id="+id;
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*上传图片2添加修改*/
function content_image(title,id){
//	alert(id);
	url = "/tz/admin/tzContent/contentAlterPage?id="+id+"&type=secondPrics";
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}


/*点击商品名称-展示商品详情*/
function content_detail(title,id){
	url = "/tz/admin/tzContent/contentAlterPage?type=detail&id="+id;
	layer_show(title,url);
}


/*单个商品-异步删除 并刷新页面 */
function content_del(obj,id){
 	layer.confirm('确认要删除吗？id='+id,function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/admin/tzContent/batchDeleteContent',
			data: {ids:id},
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
				console.log(data.msg);
			}
		});		
	}); 
}
/*商品批量删除*/
function datadel(){
	var idstr = new Array();
	var ids="";
	$("input[name='contentId']").each(function(i,n){
		if($(n).prop("checked")){
		//将被选中的商品pid拼接字符串
			idstr.push($(n).val());
		}	
	});
	ids=idstr.join(",");
	if(ids.length==0){
		//提示需要勾选至少一个商品
		layer.msg("请至少勾选一个需要删除的商品",{icon:2,time:1500});
		return;
	}else{
		layer.confirm('确认要批量删除选中的商品吗？ids='+ids,function(index){
			$.ajax({
				type: 'POST',
				url: '/tz/admin/tzContent/batchDeleteContent',
				data: {ids:ids},
				dataType: 'json',
				success: function(data){
					if(data.flag){
						for(var i=0;i<idstr.length;i++){
							$("#content_"+idstr[i]).parents("tr").remove();
						}
						init.getList(curPage,rows); 
						layer.msg(data.msg,{icon:1,time:1000});
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
	$("input[name='contentId']").prop("checked",false);
}

//获取分类目录数据
function getCategory(){
	$.ajax({
		type:'GET',
		url:'/tz/admin/tzContentCategory/queryContentCatagory',
		dataType:"json",
		success:function(data){
			var html="<option value=''>查询所有分类</option>";
			var category = data.data;
			//清空下拉表数据
			$('select[name="category"] option').remove();
			 if(category != null && category.length > 0){  
	                for(var i=0; i<category.length; i++){  
	                    html+="<option value='"+category[i].id+"'>"+category[i].name+"</option>";
	                }  
	            }  
	            $('select[name="category"]').append(html);  
			
		}
	});
}
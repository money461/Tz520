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
	 var itemTitle = $("#itemTitle").val();  //商品标题
	 var description = $("#description").val(); //商品描述
	 var categoryId = $('select[name="category"] option:selected').val(); //商品类别
	 var status = $('select[name="status"]  option:selected').val(); //商品状态
    $.ajax({
        type: "get",
        async: true,//异步锁定，默认为true
        url: "/tz/admin/tzItem/list",//后端处理数据，返回json格式
        data: {"rows": rows, "curPage": curPage,startTime:startTime,endTime:endTime,itemTitle:itemTitle,description:description,status:status,categoryId:categoryId},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        	var html = "";//商品内容
        	var status = ""; //商品状态1 上架 2 下架 3删除
        	var span = ""; // 上下架描述
        	var span2 ="";  //操作描述
        	var span3=""  //商品详情连接
 	      	$(".data_box").empty();
	        var list = data.data.list;
	        console.info(list);
	        $(".data_count").text(data.data.total);
	       
            if (list != null && list.length > 0) {
                for (var x = 0; x < list.length; x++) {
                	status = list[x].status;
         			if(status == 1){
         				span='<span class="label label-success radius">已上架</span>';
         				span2='<a  href="javascript:;" title="下架" style="text-decoration:none" onClick="instockItem(this,\''+list[x].id+'\')"><i class=\"Hui-iconfont\">&#xe6de;</i></a>'
         				       +'<a title="编辑" href="javascript:;" onclick="item_edit(\'商品编辑\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
         				      +'<a title="重新上传商品轮播图片" href="javascript:;" onclick="item_image(\'重新上传商品轮播图片\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>'
         				       +'<a title="删除" href="javascript:;" onclick="item_del(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><i id="del" class="Hui-iconfont">&#xe6e2;</i></a>'
         				       +'<a title="重新上传商品详情广告图片" href="javascript:;" onclick="item_pricture(\'重新上传商品详情广告图片\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>';
         			}else if(status==2){
         				span='<span class="label label-warning radius">已下架</span>';
         				span2='<a  href="javascript:;" title="上架" style="text-decoration:none" onClick="reshelfItem(this,\''+list[x].id+'\')"><i class=\"Hui-iconfont\">&#xe6dc;</i></a>'
         				     +'<a title="编辑" href="javascript:;" onclick="item_edit(\'商品编辑\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
         				    +'<a title="重新上传商品轮播图片" href="javascript:;" onclick="item_image(\'重新上传商品轮播图片\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>'
   				             +'<a title="删除" href="javascript:;" onclick="item_del(this,\''+list[x].id+'\')" class="ml-5" style="text-decoration:none"><i id="del" class="Hui-iconfont">&#xe6e2;</i></a>'
   				          +'<a title="重新上传商品详情广告图片" href="javascript:;" onclick="item_pricture(\'重新上传商品详情广告图片\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>';
         			}else if(status==3){
         				span='<span class="label label-warning radius">已删除</span>';
         				span2='<a  href="javascript:;" title="重新发布" style="text-decoration:none" onClick="reshelfItem(this,\''+list[x].id+'\')"><i class=\"Hui-iconfont\">&#xe603;</i></a>'
         				+'<a title="重新上传商品轮播图片" href="javascript:;" onclick="item_image(\'重新上传商品轮播图片\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>'
    				     +'<a title="编辑" href="javascript:;" onclick="item_edit(\'商品编辑\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
    				     +'<a title="重新上传商品详情广告图片" href="javascript:;" onclick="item_pricture(\'重新上传商品详情广告图片\',\''+list[x].id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>';
         			}
         			
         			span3='<a title="商品详情" href="javascript:;" onclick="item_show_detail(\'商品详情\',\''+list[x].id+'\')" style="text-decoration:none"><span style="width:130px" class="label label-success radius">'+list[x].itemTitle+'</span></a>';
         			
         			html += '<tr><td id="Item_'+list[x].id+'\"><input value=\"'+list[x].id+'\" name="ItemId" type="checkbox"/></td>'+
         			'<td>'+list[x].id+'</td>'+
    				'<td>'+span3+'</td>'+
    				'<td>'+list[x].categoryName+'</td>'+
    				'<td><a href="javascript:;" onclick="item_homePageUrl(\'重新上传缩略图\',\''+list[x].id+'\')"><img width="110" height="150" class="picture-thumb" src="'+list[x].homepageUrl+'"></a></td>'+
    				'<td>'+list[x].price+'</td>'+
    				'<td>'+list[x].memberPrice+'</td>'+
    				'<td>'+list[x].lovePrice+'</td>'+
    				'<td>'+list[x].itemPost+'</td>'+
    				'<td>'+list[x].num+'</td>'+
    				'<td>'+list[x].description+'</td>'+
    				'<td class="status_ql">'+span+'</td>'+
    				'<td>'+list[x].salesNum+'</td>'+
    				'<td>'+list[x].operater+'</td>'+
    				'<td>'+list[x].createdTime+'</td>'+
    				'<td>'+list[x].updatedTime+'</td>'+
    				'<td class=td-manage>'+span2+'</td>'+'</tr>';
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


/*商品-增加*/
function manager_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*商品-编辑*/
function item_edit(title,id){
//	alert(id);
	url = "/tz/admin/tzItem/addOrUpdatePage?type=update&id="+id;
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*商品-商品轮播图修改*/
function item_image(title,id){
//	alert(id);
	url = "/tz/admin/tzItem/picUpdatePage?id="+id+"&type=images";
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}

/**
 * 重新上传商品详细广告大图片
 */
function item_pricture(title,id){
	url = "/tz/admin/tzItem/picUpdatePage?id="+id+"&type=prictures";
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}

function item_homePageUrl(title,id){
	url = "/tz/admin/tzItem/picUpdatePage?id="+id+"&type=homePageUrl";
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}

/*点击商品名称-展示商品详情*/
function item_show_detail(title,id){
	url = "/tz/admin/tzItem/addOrUpdatePage?type=detail&id="+id;
	layer_show(title,url);
}
/*单个商品下架*/
function instockItem(obj,id){
	layer.confirm('确定下架该商品吗？id='+id,function(index){
		$.ajax({
			type:'post',
			url:'/tz/admin/tzItem/operation',
			data:{type:"instockItem",ids:id+','},
			dataType:'json',
			success:function(data){
	            if(data.flag){
	            	$(obj).parents("tr").find(".status_ql").html('<span class="label label-warning radius">已下架</span>');
	            	$(obj).parents("tr").find(".td-manage").html('<td class="td-manage"><a style="text-decoration:none" onClick="reshelfItem(this,\''+id+'\')" href="javascript:;" title="上架"><i class="Hui-iconfont">&#xe6dc;</i></a>'
	            			                                      +'<a title="编辑" href="javascript:;" onclick="item_edit(\'编辑\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
	            			                                      +'<a title="重新上传商品轮播图片" href="javascript:;" onclick="item_image(\'重新上传商品轮播图片\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>'
      				                                               +'<a title="删除" href="javascript:;" onclick="item_del(this,\''+id+'\')" class="ml-5" style="text-decoration:none"><i id="del" class="Hui-iconfont">&#xe6e2;</i></a>'
      				                                             +'<a title="重新上传商品详情广告图片" href="javascript:;" onclick="item_pricture(\'重新上传商品详情广告图片\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a></td>');
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



/*商品上架*/
function reshelfItem(obj,id){
	layer.confirm('确定上架该商品吗？id='+id,function(index){
		$.ajax({
			type:'post',
			url:'/tz/admin/tzItem/operation',
			data:{type:"reshelfItem",ids:id+','},
			dataType:'json',
			success:function(data){
				if(data.flag){
					$(obj).parents("tr").find(".status_ql").html('<span class="label label-success radius">已上架</span>');
					$(obj).parents("tr").find(".td-manage").html('<td class="td-manage"><a style="text-decoration:none" onClick="instockItem(this,\''+id+'\')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>'
							+'<a title="编辑" href="javascript:;" onclick="item_edit(\'编辑\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
							 +'<a title="重新上传商品轮播图片" href="javascript:;" onclick="item_image(\'重新上传商品轮播图片\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>'
                                                                 +'<a title="删除" href="javascript:;" onclick="item_del(this,\''+id+'\')" class="ml-5" style="text-decoration:none"><i id="del" class="Hui-iconfont">&#xe6e2;</i></a>'
                                                                 +'<a title="重新上传商品详情广告图片" href="javascript:;" onclick="item_pricture(\'重新上传商品详情广告图片\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a></td>');
                                                                  
					layer.msg(data.msg,{icon:1,time:1000});
				}	
				else{
					layer.msg('上架失败，请稍后重试！',{icon:5,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}



/*单个商品-异步删除 并刷新页面 */
function item_del(obj,id){
 	layer.confirm('确认要删除吗？id='+id,function(index){
		$.ajax({
			type: 'POST',
			url: '/tz/admin/tzItem/operation',
			data: {type:"deleteItem",ids:id+','},
			dataType: 'json',
			success: function(data){
	            if(data.flag){
	            	$(obj).parents("tr").find(".status_ql").html('<span class="label label-warning radius">已删出</span>');
	            	$(obj).parents("tr").find(".td-manage").html('<td class="td-manage"><a  href="javascript:;" title="重新发布" style="text-decoration:none" onClick="reshelfItem(this,\''+id+'\')"><i class=\"Hui-iconfont\">&#xe603;</i></a>'
	            			                                     +'<a title="重新上传商品轮播图片" href="javascript:;" onclick="item_image(\'重新上传商品轮播图片\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a>'
	            			                                      +'<a title="编辑" href="javascript:;" onclick="item_edit(\'编辑\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>'
	            			                                      +'<a title="重新上传商品详情广告图片" href="javascript:;" onclick="item_pricture(\'重新上传商品详情广告图片\',\''+id+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe613;</i></a></td>');
	            			                                       
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
	$("input[name='ItemId']").each(function(i,n){
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
				url: '/tz/admin/tzItem/operation',
				data: {type:"deleteItem",ids:ids},
				dataType: 'json',
				success: function(data){
					if(data.flag){
						layer.msg(data.msg,{icon:1,time:1000});
						//刷新页面
						for(var i=0;i<idstr.length;i++){
							$("#Item_"+idstr[i]).parents("tr").find(".status_ql").html('<span class="label label-warning radius">已删出</span>');
							$("#Item_"+idstr[i]).parents("tr").find(".td-manage").html('<td class="td-manage"><a  href="javascript:;" title="重新发布" style="text-decoration:none" onClick="reshelfItem(this,\''+idstr[i]+'\')"><i class=\"Hui-iconfont\">&#xe603;</i></a>'
                                    +'<a title="编辑" href="javascript:;" onclick="item_edit(\'编辑\',\''+idstr[i]+'\')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a></td>');
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
	$("input[name='ItemId']").prop("checked",false);
}

//获取分类目录数据
function getCategory(){
	$.ajax({
		type:'GET',
		url:'/tz/admin/tzItemCategory/findAllItemCatagory',
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
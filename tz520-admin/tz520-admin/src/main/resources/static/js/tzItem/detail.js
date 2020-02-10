var id = $("#itemId").val();
$(function(){
	getItemDate();
})
//数据回显
function getItemDate(){
	$.ajax({
        type: "POST",
        url: '/tz/admin/tzItem/findItemById',
        data: {id:id},
        dataType: "json",
        success: function(data){
            if(data.flag){
            	data = data.data;
            	$("#id").text(data.id);
	    		$("#itemTitle").text(data.itemTitle);
	    		$("#category_name").text(data.categoryName);
            	$("#mallId").text(data.mallId);
	    		$("#operater").text(data.operater);
	    		$("#barcode").text(data.barcode);
	    		$("#price").text(data.price);
	    		$("#memberPrice").text(data.memberPrice);
	    		$("#lovePrice").text(data.lovePrice);
	    		$("#num").text(data.num);
	    		$("#description").text(data.description);
	    		$("#sales_num").text(data.salesNum);
	    		$("#createdTime").text(data.createdTime);
	    		$("#updatedTime").text(data.updatedTime);
	    		
	    		if(data.status==1){
	    			$("#status").text("已上架");
	    		}else if(data.status==2){
	    			$("#status").text("已下架");
	    		}else if(data.status==3){
	    			$("#status").text("已删除");
	    			
	    		}
	    		//缩略图
	    		$(".homePageUrl").html('<img class="picture-thumb" src="'+data.homepageUrl+'" width=\"60%\" height=\"60%\"/>');
	    		
	    		/*回显商品轮播图片*/
    	    		if(data.images!=null){
    	    			var html="";
    	    			var imgs = data.images;
    	    			for(var i=0;i<imgs.length;i++){
    	    				html+='<img class="picture-thumb" src=\"'+imgs[i]+'\" width=\"60%\" height=\"60%\"/></br>';
    	    			}
    	    			$(".images").append(html);
    	    	}
    	    		if(data.prictures!=null){
    	    			var html="";
    	    			var imgs = data.prictures;
    	    			for(var i=0;i<imgs.length;i++){
    	    				html+='<img class="picture-thumb" src=\"'+imgs[i]+'\" width=\"60%\" height=\"60%\"/></br>';
    	    			}
    	    			$(".prictures").append(html);
    	    		}
    		
	    		
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
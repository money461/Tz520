$(function(){
	
	/*回显数据*/
	getDate();
});	
	
//详情页面异步加载数据回显
var id = $("#contentId").val();
function getDate(){
	$.ajax({
        type: "POST",
        url: '/tz/admin/tzContent/queryContentDetail',
        data: {id:id},
        dataType: "json",
        success: function(data){
        	console.info(data)
            if(data.flag){
            	data = data.data;
            	$("#id").text(data.id);
            	$("#contentTitle").text(data.contentTitle);
            	$("#subTitle").text(data.subTitle);
            	$("#titleDesc").text(data.titleDesc);
	    		$("#url").text(data.url);
	    		$("#contentDesc").text(data.contentDesc);
	    		//广告分类
	    		$("#categoryName").text(data.categoryName);
	    		if(data.firstPrics!=null){
	    			var html="";
	    			var imgs = data.firstPrics;
	    			for(var i=0;i<imgs.length;i++){
	    				html+='<img class="picture-thumb" src=\"'+imgs[i]+'\" width=\"60%\" height=\"60%\"/></br>';
	    			}
	    			
	    			$(".picFirstUrl").append(html);
	    		}
	    		if(data.secondPrics!=null){
	    			var html="";
	    			var imgs = data.secondPrics;
	    			for(var i=0;i<imgs.length;i++){
	    				html+='<img class="picture-thumb" src=\"'+imgs[i]+'\" width=\"60%\" height=\"60%\"/></br>';
	    			}
	    			
	    			$(".picSecondUrl").append(html);
	    		}
	    		
             }	
	    	else{
	    		layer.msg('数据加载失败，请稍后重试！',{icon:5,time:1000});
		    }
        },  
        error:function(xhr,status,status){
        	if(xhr.status == 500)
        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
        }  
    });
    
}

$(function(){
	
	/*异步加载分类信息*/
	categoryData();
	
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$('.submit_data').click(function(){
		var id = $("#itemId").val();
		var itemTitle = $("#itemTitle").val();
		var categoryId = $("#categoryId").val();
		var price = $("#price").val();
		var memberPrice = $("#memberPrice").val();
		var lovePrice =$("#lovePrice").val();
		var itemPost = $("#itemPost").val();
		var num = $("#num").val();
		var barcode = $("#barcode").val();
		var description = $("#description").val();
		var status = $("input[type='radio']:checked").val();
		var imageUrl = $("#image_url").val();
	    
		//空处理
		if(isEmpty(itemTitle,'商品名称不能为空！')){return false};
		if(isEmpty(categoryId,'商品分类id不能为空！')){return false};
		if(isEmpty(price,'商品价钱不能为空！')){return false};
		if(isEmpty(memberPrice,'商品会员价不能为空！')){return false};
		if(isEmpty(lovePrice,'商品爱心价不能为空！')){return false};
		if(isEmpty(itemPost,'商品邮费不能为空！')){return false};
		if(isEmpty(num,'商品库存不能空！')){return false};
		if(isEmpty(description,'商品描述不能为空！')){return false};
		if(isEmpty(status,'商品状态不能为空！')){return false};
		
		//是否是数字且大于0
		if(isCheckNum(price,"商品单价必须大于等于0")){return false};
		if(isCheckNum(memberPrice,"会员单价必须大于等于0")){return false};
		if(isCheckNum(lovePrice,"爱心价必须大于等于0")){return false};
		if(isCheckNum(itemPost,"商品邮费必须大于等于0")){return false};
		
		//是否是正整数
		if(isIntNum(num,"数量必须是大于等于0的正整数")){return false};
		
		
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/tzItem/updateItem',
		        data: {id:id,itemTitle:itemTitle,categoryId:categoryId,price:price,memberPrice:memberPrice,lovePrice:lovePrice,itemPost:itemPost,num:num,description:description,status:status,imageUrl:imageUrl},
		        dataType: "json",
		        beforeSend: function () {
		            //防止重复提交
		        	 $(".layui-layer-shade").css({'display':'none'});
		        	 $(".layui-layer-dialog").css({'display':'none'});
		        	 loading.loadingShow();
		        },
		        success: function(data){
		        	loading.loadingHide();
		        	 if(data.flag){
		        		 layer.alert(data.msg,{icon:1,time:2000});
		        		var index = parent.layer.getFrameIndex(window.name);
		 				parent.flush();//点击确定调用父页面方法  
		 				parent.layer.close(index);		
		        	}else{
		        		layer.msg('操作失败，请稍后重试！',{icon:5,time:1000});
		        	}	 
		        },  
		        error:function(xhr,status,statusText){
		        	loading.loadingHide();
		        	if(xhr.status == 500)
		        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
		        }  
		    });
		 });
		
	});
});

//update编辑页面异步加载数据回显
var id = $("#itemId").val();
function getDate(){
	$.ajax({
        type: "POST",
        url: '/tz/admin/tzItem/findItemById',
        data: {id:id},
        dataType: "json",
        success: function(data){
        	console.info(data)
            if(data.flag){
            	data = data.data;
            	$("#id").val(data.id);
            	$("#itemTitle").val(data.itemTitle);
            	$("#price").val(data.price);
            	$("#memberPrice").val(data.memberPrice);
            	$("#lovePrice").val(data.lovePrice);
            	$("#itemPost").val(data.itemPost);
	    		$("#num").val(data.num);
	    		/*$("#status").val(data.status);*/
	    		$("#barcode").val(data.barcode);
	    		$("#description").val(data.description);
	    		/*$(".userType_val").val(userType);*/
	    		var status = data.status;
	    		//分类
	    		$("#categoryId option[value='"+data.categoryId+"']").prop('selected',true);
	    		//上架  下架 删除
    		    $("input[name='status'][value="+status+"]").parents(".iradio-blue").addClass("checked");
    		    $("input[name='status'][value="+status+"]").prop("checked",true);
	    		//上架
	    		if(status == 1){   		
            		$('.userType .two').prop('selected',false);
            	//下架
	    		}else if(status == 2){
	    			$('.userType .one').prop('selected',false);
            		$('.userType .two').prop('selected',true);
            	//删除
	    		}else{
	    			$('.userType .one').prop('selected',false);
            		$('.userType .two').prop('selected',true);
	    		}
	    		
	    		if(data.images!=null){
	    			var html="";
	    			var imgs = data.images;
	    			for(var i=0;i<imgs.length;i++){
	    				html+='<img class="picture-thumb" src=\"'+imgs[i]+'\" width=\"60%\" height=\"60%\"/></br>';
	    			}
	    			
	    			$(".imagesUrl").append(html);
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

//异步商品分类信息
function categoryData(){
	$.ajax({
		type: 'get',
		dataType: 'json',
		url: '/tz/admin/tzItemCategory/findAllItemCatagory',
		success:function(data){
			//console.info(data);
			if(data.flag){
				//清空下拉表数据
				$("#categoryId option").remove();
				 data = data.data;
				var html="<option value=''>请选择商品类别</option>";   
	            if(data != null && data.length > 0){  
	                for(var i=0; i<data.length; i++){  
	                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	                }  
	            }  
	            $("#categoryId").append(html); 
	            /*数据回显*/
	        	getDate();
			}
		},
		error: function(data){
			$.Message().alert("warning",data.msg);
		}
		
	});
}

$(function(){
	
	/*异步加载分类信息*/
	categoryData();
	
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$('.submit_data').click(function(){
		var itemTitle = $("#itemTitle").val();
		var categoryId =$("#categoryId").val();
		var price=$("#price").val();
		var memberPrice = $("#memberPrice").val();
		var lovePrice = $("#lovePrice").val();
		var itemPost = $("#itemPost").val();
		var num = $("#num").val();
		var barcode=$("#barcode").val();
		var status = 2;
		if($("#sex-1").is(':checked')) {
		    // do something
			status = 1;
		}
		var description=$("#description").val();
		var imageUrl = $("#image_url").val();
		var homepageUrl =$("#homepageUrl").val();
		if(homepageUrl.length < 1){
			homepageUrl = "http://120.55.43.176/group1/M00/00/01/rBBH51nZ6kqALjkaAAFV-7fgPdU259.jpg";
		}
		//空处理
		if(isEmpty(itemTitle,'商品标题不能为空！')){return false};
		if(isEmpty(categoryId,'商品分类不能为空！')){return false};
		if(isEmpty(price,'商品单价不能为空！')){return false};
		if(isEmpty(memberPrice,'会员价格不能为空！')){return false};
		if(isEmpty(lovePrice,'爱心价格不能为空！')){return false};
		if(isEmpty(itemPost,'商品邮费不能为空！')){return false};
		if(isEmpty(num,'商品数量不能为空！')){return false};
		if(isEmpty(description,'商品描述不能为空！')){return false};
		
		//判断单价必须大于等于0
		//是否是数字且大于0
		if(isCheckNum(price,"商品单价必须大于等于0")){return false};
		if(isCheckNum(memberPrice,"会员单价必须大于等于0")){return false};
		if(isCheckNum(lovePrice,"爱心单价必须大于等于0")){return false};
		if(isCheckNum(itemPost,"商品邮费必须大于等于0")){return false};
		
		//是否是正整数
		if(isIntNum(num,"数量必须是大于等于0的正整数")){return false};
		
		layer.confirm('确认数据检查无误并提交?',function(index){
			$.ajax({
		        type: "POST",
		        url: '/tz/admin/tzItem/addItem',
		        data: {itemTitle:itemTitle,categoryId:categoryId,price:price,
		        	memberPrice:memberPrice,lovePrice:lovePrice,itemPost:itemPost,num:num,
		        	barcode:barcode,status:status,
		        	description:description,imageUrl:imageUrl,homepageUrl:homepageUrl},
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


//异步查询商品分类信息
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
			}
		},
		error: function(data){
			$.Message().alert("warning",data.msg);
		}
		
	});
}

$(".homepageUrl_click").on("click",function() {
	$(".fileClass").click();
})
$(".fileClass").on('change',function() {
	selFile(this.files, "image", "file_up_image", "homepageUrl_click","homepageUrl");
});
function selFile(files, fileType, fromId, image, inputClass) {
	var file = files[0];
	var fr = new FileReader();
	fr.readAsDataURL(file);
	var flagType = true;
	fr.onload = function() {
		// 判断展示的文件类型
		if (file.type.indexOf("image") > -1) {
			if (fileType == "image") {
				var imgSrc = fr.result;
				$("." + image).attr('src', imgSrc);
			} else {
				layer.msg('请上传其他类型的文件！',{icon:5,time:1000});
				return;
			}
		} else {
			if (fileType != "file") {
				layer.msg('请上传图片类型的文件!',{icon:5,time:1000});
				return;
			}

		}
		$(".layui-layer-shade").css({'display':'none'});
    	$(".layui-layer-dialog").css({'display':'none'});
    	loading.loadingShow();
		var fileName = $("." + inputClass).val();
		if (fileName != "" && fileName.length > 0) {
			$.ajax({
				type : "POST",
				url : "/tz/admin/file/deleteFile",
				data : {
					fileUrl : fileName
				},
				success : function(data) {

				}
			})
		}
		console.info(file)
		console.info(fromId)
		hfu_ajax({
			success : function(data) {
				 console.info(data.data);
				 layer.alert('上传成功！', {
					    skin: 'layui-layer-molv'
					    ,closeBtn: 0
					    ,anim: 4 //动画类型
					 });
				$("." + inputClass).val(data.data);
				loading.loadingHide();
			},
			progress : function(a) {
				if (a = 100) {
				}
			},	
			url : "/tz/admin/file/uploadImageAndCrtThumbImage",
			fromId : fromId
		})
	}
}

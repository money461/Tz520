
/*导出数据*/
function ExportExcel(){
	 
	 var startTime = $(window.parent.document).find("#startTime").val();
	 var endTime = $(window.parent.document).find("#endTime").val();
	 var phone = $(window.parent.document).find("#userPhone").val();
	 var userId = $(window.parent.document).find(".data_id").val();
	 var userName = $(window.parent.document).find("#userName").val();
	 var payeeRealName = $(window.parent.document).find("#payeeRealName").val();
	 var selectType = $(window.parent.document).find("#selectType").val();
	 var fileName = $("#fileName").val(); //文件名称
	 if(isEmpty(fileName,"文件名称不能为空！")){return false};
	 
	layer.confirm('确定需要导出订单数据吗？',function(index){
		 $("#startTime").val(startTime);
		 $("#endTime").val(endTime);
		 $("#phone").val(phone);
		 $("#userId").val(userId);
		 $("#userName").val(userName);
		 $("#payeeRealName").val(payeeRealName);
		 $("#selectType").val(selectType);
		
		 //提交表单
		 document.getElementById("formData").submit();
		 layer.msg("导出下载成功！！",{icon:1,time:10000});
		/*
		$.ajax({
			type:'post',
			url:'/tz/admin/loveShow/importExel',
			data:{phone:phone,userId:userId,userName:userName,payeeRealName:payeeRealName,startTime:startTime,endTime:endTime,fileName:fileName,selectType:selectType},
			dataType:'json',
		    beforeSend: function () {
		            //防止重复提交
		        	 $(".layui-layer-shade").css({'display':'none'});
		        	 $(".layui-layer-dialog").css({'display':'none'});
		        	 loading.loadingShow();
		        },
			success:function(data){
				loading.loadingHide();
	            if(data.flag){
					layer.msg(data.msg,{icon:1,time:10000});
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);		
	             }else{
		    		layer.msg(data.msg,{icon:5,time:1000});
			    }
			},
			error:function(xhr,status,statusText){
	        	loading.loadingHide();
	        	if(xhr.status == 500)
	        		layer.msg("服务器连接失败，请联系管理员！",{icon: 2,time:1500});
	        }  
		});*/
	});
}

function browseFolder(path) {
    try {
        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
        //var Folder = Shell.BrowseForFolder(0, Message, 0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items(); // 返回 FolderItems 对象
            Folder = Folder.item(); // 返回 Folderitem 对象
            Folder = Folder.Path; // 返回路径
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            document.getElementById(path).value = Folder;
            return Folder;
        }
    }
    catch (e) {
        alert(e.message);
    }
}
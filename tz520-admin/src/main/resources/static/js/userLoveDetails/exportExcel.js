
/*导出数据*/
function ExportExcel(){
	 
	 var startTime = $(window.parent.document).find("#startTime").val();
	 var endTime = $(window.parent.document).find("#endTime").val();
	 var phone = $(window.parent.document).find("#userPhone").val();
	 var userName = $(window.parent.document).find("#userName").val();
	 var type = $(window.parent.document).find("#selectType option:selected").val();
	 
	 var fileName = $("#fileName").val(); //文件名称
	 if(isEmpty(fileName,"文件名称不能为空！")){return false};
	 
	layer.confirm('确定需要导出订单数据吗？',function(index){
		 $("#startTime").val(startTime);
		 $("#endTime").val(endTime);
		 $("#phone").val(phone);
		 $("#userName").val(userName);
		 $("#selectType").val(type);
		
		 //提交表单
		 document.getElementById("formData").submit();
		 layer.msg("导出下载成功！！",{icon:1,time:10000});
		
	});
}


<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<title>辣子两耳屎</title>

</head>
<body>
<h1 ></h1>
<div >文件上传测试 单文件</div>
     <form id="file_up" enctype="multipart/form-data">
     	<input type="file" name="file" id="file_upload"/>
     </form>
<!-- 图片上传 -->
<div id='up' class='up'>点我上传</div>

<!-- 图片预览 -->
<img alt="" src="">

</body>
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/file/uploadfile.js"></script> 
<script type='text/javascript'>
	$(".up").click(function(){
			hfu_ajax({
			success : function(data) {
				console.info(data.data);
				$("." + inputClass).val(data.data);
				$('#message_before_send_show').css({
					'display' : 'none'
				});
			},
			progress : function(a) {
				if (a = 100) {

				}
			},
			url : "/tz/admin/tzItem/add",
			fromId : 'file_up'
		})
})

</script>
</html>​​
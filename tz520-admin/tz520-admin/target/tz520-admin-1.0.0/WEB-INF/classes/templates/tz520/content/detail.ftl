<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>

<![endif]-->
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/hui/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/hui/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />

<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/hui/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/hui/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<link href="${request.contextPath}/static/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
	<div action="" method="post" class="form form-horizontal" id="form-item-add">
	 <input type="hidden" name="contentId" id="contentId" value="${id}"/>
	   <div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"></span>内容分类：</label>
			<div class="" id="categoryName"></div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></span>内容大标题：</label>
			<div class="" id="contentTitle"></div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></span>内容小标题：</label>
			<div class="" id="subTitle"></div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></span>标题描述：</label>
			<div class="" id="titleDesc"></div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></span>超链接url：</label>
			<div class="" id="url"></div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">内容描述：</label>
			<div class="" id="contentDesc"></div>
		</div>
		
		<div class="row cl">
		   <label class="form-label col-xs-4 col-sm-2">广告图片1详情：</label>
		   <div class="col-xs-8 col-sm-10 picFirstUrl"></div>
		</div>
		
		<div class="row cl">
		   <label class="form-label col-xs-4 col-sm-2">广告图片2详情：</label>
		   <div class="col-xs-8 col-sm-10 picSecondUrl"></div>
		</div>
	
	</div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/static/js/loading.js"></script>

<script type="text/javascript" src="${request.contextPath}/static/js/content/detail.js"></script> 

<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
});
</script>
</body>
</html>
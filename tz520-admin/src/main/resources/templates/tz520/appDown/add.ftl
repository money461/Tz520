<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
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
<script type="text/javascript" src="${request.contextPath}/static/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<link href="${request.contextPath}/static/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>添加管理员 - 管理员管理 - H-ui.admin v2.4</title>
<meta name="keywords" content="H-ui.admin v3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
<div class="form form-horizontal" id="form-admin-add">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>app名称：</label>
		<div class="formControls col-xs-5 col-sm-6">
			<input type="text" class="input-text" value="" placeholder="" id="name" name="name">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>版本号：</label>
		<div class="formControls col-xs-5 col-sm-6">
			<input type="text" class="input-text" value="" placeholder="" id="version" name="version">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序号：</label>
		<div class="formControls col-xs-5 col-sm-6">
			<input type="text" class="input-text" value="" placeholder="" id="sort" name="sort">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>安装包上传：</label>
		<div class="formControls col-xs-3 col-sm-2">
			<form id="file_up" enctype="multipart/form-data">
				<input  type="file" id="file" name="file" class="package">
			</form>
			<input type="hidden" class="input-text" value="" placeholder="" id="down" name="down">
		</div>
		
	</div>
	<div class="row cl">
	
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否需要强制更新：</label>
		<div class="formControls col-xs-8 col-sm-9 skin-minimal">
			<div class="radio-box">
				<input name="sex" type="radio" id="sex-1" value="1" checked>
				<label for="sex-1">是</label>
			</div>
			<div class="radio-box">
				<input type="radio" id="sex-2" name="sex" value="0">
				<label for="sex-2">否</label>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 z	col-sm-offset-3">
			<input class="submit_data  btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</div>
</article>
<!-- 数据添加的模态 --> 
<div id="message_before_send_show" style="display:none;position: fixed;z-index:5000;top:0px;left:0px;width: 100%;height: 100%;background-color: rgba(0,0,0,0.1);text-align: center;line-height: 100vh;"><i class="fa fa-spinner fa-spin"></i><span class="message_span" ></span></div>

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
<script type="text/javascript" src="${request.contextPath}/static/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/file/uploadfile.js"></script> 

<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/static/js/loading.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/appDown/add.js"></script>
<!--请在下方写此页面业务相关的脚本-->
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
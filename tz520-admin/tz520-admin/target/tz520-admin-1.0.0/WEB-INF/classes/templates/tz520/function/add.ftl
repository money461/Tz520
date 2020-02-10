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
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加权限 - 管理员管理 - H-ui.admin v2.4</title>
<meta name="keywords" content="H-ui.admin v3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
<form class="form form-horizontal" id="form-admin-function">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="" id="functionName" name="name">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>关键字</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"  value="" placeholder="关键字" id="code" name="code">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>访问路径</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="" id="page" name="page">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>优先级</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"  name="zindex" id="zindex">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">是否生成菜单：</label>
		<div class="formControls col-xs-8 col-sm-9"> 
		<span class="select-box" style="width:200px;">
			<select class="select" name="generatemenu" size="1">
				<option value="0">不生成</option>
				<option value="1">生成</option>
			</select>
		</span> 
	</div>
</div>
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">父功能节点：</label>
		<div class="formControls col-xs-8 col-sm-9"> 
		<span class="select-box" style="width:200px;">
			<select class="select valid" id="sel" name="pId" size="1">
			</select>
		</span> 
	  </div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">权限描述：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<textarea name="description" rows="4" cols="33"></textarea>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>
<!-- 数据添加的模态 --> 
<div id="message_before_send_show" style="display:none;position: fixed;z-index:5000;top:0px;left:0px;width: 100%;height: 100%;background-color: rgba(0,0,0,0.1);text-align: center;line-height: 100vh;"><i class="fa fa-spinner fa-spin"></i><span class="message_span" ></span></div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 


<script type="text/javascript" src="${request.contextPath}/static/js/function/add.js"></script>  

<!--请在下方写此页面业务相关的脚本-->
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
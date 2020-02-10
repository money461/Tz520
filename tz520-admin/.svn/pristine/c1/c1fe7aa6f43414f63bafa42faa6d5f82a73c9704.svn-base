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
<link rel="stylesheet" href="${request.contextPath}/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!--[if IE 6]>
<script type="text/javascript" src="${request.contextPath}/static/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<link href="${request.contextPath}/static/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<title>添加权限 - 管理员管理 - H-ui.admin v2.4</title>
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
			<input type="text" class="input-text" id="zindex">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否生成菜单：</label>
		<div class="formControls col-xs-8 col-sm-9"> 
		<span class="select-box" style="width:200px;">
			<select id="generatemenu" class="select" name="generatemenu" size="1">
				<option value="0">不生成</option>
				<option value="1">生成</option>
			</select>
		</span> 
	</div>
</div>
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限上级目录：</label>
		<div class="formControls col-xs-8 col-sm-9"> 
		  <ul id="functionDemo" class="ztree"></ul>
	  </div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限描述：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<textarea id="description" rows="4" cols="33"></textarea>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<button  class="btn btn-primary radius submit_data" type="button"><i class="Hui-iconfont">&#xe632;</i> 提交保存</button>
			<button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</div>
	</div>
	</form>
</article>
<!-- 数据添加的模态 --> 
<div id="message_before_send_show" style="display:none;position: fixed;z-index:5000;top:0px;left:0px;width: 100%;height: 100%;background-color: rgba(0,0,0,0.1);text-align: center;line-height: 100vh;"><i class="fa fa-spinner fa-spin"></i><span class="message_span" ></span></div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/js/loading.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script>

<script type="text/javascript" src="${request.contextPath}/static/js/function/add.js"></script>  

<!--请在下方写此页面业务相关的脚本-->
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
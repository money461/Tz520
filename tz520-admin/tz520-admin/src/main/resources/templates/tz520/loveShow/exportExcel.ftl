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
<script type="text/javascript" src="${request.contextPath}/static/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->
<title>导出爱心值提现数据</title>
</head>
<body>
<article class="page-container">
   <form id="formData" action="/tz/admin/loveShow/importExel" method="post">
     <input type="hidden" name="startTime" id="startTime" value="">
     <input type="hidden" name="endTime" id="endTime" value="">
     <input type="hidden" name="phone" id="phone" value="">
     <input type="hidden" name="userId" id="userId" value="">
     <input type="hidden" name="userName" id="userName" value="">
     <input type="hidden" name="payeeRealName" id="payeeRealName" value="">
     <input type="hidden" name="selectType" id="selectType" value="">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>填写导出文件名称：</label>
		<div class="formControls col-xs-8 col-sm-7">
			<input type="text" class="input-text" value="" placeholder="生成文件名称" id="fileName" name="fileName">
		</div>
	</div>
   </form>	
	
	</br>
	<div class="rows cl" >
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 ">
	  <button onClick="ExportExcel();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i>确认导出订单</button>
	  <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
	   </div>
	</div>
	
</article>
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
<script type="text/javascript" src="${request.contextPath}/static/js/loading.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/loveShow/exportExcel.js"></script> 

</body>
</html>
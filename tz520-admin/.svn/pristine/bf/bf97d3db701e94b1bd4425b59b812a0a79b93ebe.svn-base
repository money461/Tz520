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

<link href="${request.contextPath}/static/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<title>编辑物流</title>
<body>
<article class="page-container">
	<div  class="form form-horizontal" id="form-orderShipping-update">
		<input id="orderId" type="hidden" name="orderId" value=${id}>
		 <div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>快递公司：</label>
			<div class="formControls col-xs-8 col-sm-9">
			 <span class="select-box">
				<select class="select valid"  size="1" id="com" name="com">
					
				</select>
				</span>
			 </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>运单编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="请输入物流运单号" id="shippingCode" name="shippingCode">
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>收件人姓名：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件人真实姓名" id="receiverName" name="receiverName">
		   </div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2">固定电话：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件人固定电话" id="receiverPhone" name="receiverPhone">
		   </div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机号码：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件人手机号码" id="receiverMobile" name="receiverMobile">
		   </div>
		</div>
		<div  class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>地址-省份：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件地址省份信息" id="receiverState" name="receiverState">
		   </div>
		</div>
		<div  class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>地址-市：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件城市信息" id="receiverCity" name="receiverCity">
		   </div>
		</div>
		<div  class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>地址-区/县：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件市区信息" id="receiverDistrict" name="receiverDistrict">
		   </div>
		</div>
		<div  class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>详细地址：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件详细地址" id="receiverAddress" name="receiverAddress">
		   </div>
		</div>
		<div  class="row cl">
		<label class="form-label col-xs-4 col-sm-2">地址邮编：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<input type="text" class="input-text" value="" placeholder="请输入收件地址邮编" id="receiverZip" name="receiverZip">
		   </div>
		</div>
		
		<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 z	col-sm-offset-3">
			<input class="submit_data  btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			<button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
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
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/loading.js"></script>

<script type="text/javascript" src="${request.contextPath}/static/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script> 
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/static/js/order/delivery.js"></script>
<!--请在下方写此页面业务相关的脚本-->
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
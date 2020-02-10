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
<body>
<div class="page-container">
	<div action="" method="post" class="form form-horizontal" id="form-item-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="itemTitle" name="itemTitle">
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品分类：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select id="categoryId" name="categoryId" class="select">
					
				</select>
				</span>
				 </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品	价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="price" id="price" placeholder="商品单价" value="" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>会员价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="memberPrice" id="memberPrice" placeholder="会员价格" value="" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>爱心价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="lovePrice" id="lovePrice" placeholder="爱心价格" value="" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品邮费：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="itemPost" id="itemPost" placeholder="商品邮费" value="" class="input-text" style="width:90%">
				元</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品数量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="num" id="num" placeholder="商品数量" value="" class="input-text" style="width:90%">
				件</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品条形码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="商品条形码" id="barcode" name="barcode">
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品上下架：</label>
		<div class="formControls col-xs-8 col-sm-9 skin-minimal">
			<div class="radio-box">
				<input name="status" value="1" type="radio" id="sex-1" checked>
				<label for="sex-1">上架</label>
			</div>
			<div class="radio-box">
				<input  type="radio" value="2" id="sex-2" name="status">
				<label for="sex-2">下架</label>
			</div>
		</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品卖点描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea id="description" name="description" cols="" rows="" class="textarea"  placeholder="对该商品写个推销语吧" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="$.Huitextarealength(this,200)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品轮播图片：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="uploader-list-container">
					<div class="queueList">
						<div id="dndArea" class="placeholder">
							<div id="filePicker-2"></div>
							<p>或将照片拖到这里，单次最多可选20张,支持格式：GIF、JPG、JPEG、BMP、PNG </p>
						</div>
					</div>
					<div class="statusBar" style="display:none;">
						<div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
						<div class="info"></div>
						<div class="btns">
							<div id="filePicker2"></div>
							<div class="uploadBtn">开始上传</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="imageUrl" id="image_url"  value="" class="input-text">
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品缩略小图：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<img src="http://120.55.43.176/group1/M00/00/01/rBBH51nZ6kqALjkaAAFV-7fgPdU259.jpg"  class="homepageUrl_click" style="width:110px;height:110px">
				<form id="file_up_image" enctype="multipart/form-data">
					<input  style="display:none"  type="file" value="" name="file" class="fileClass" >
				</form>
				<input  type="hidden" value="" name="homepageUrl" id="homepageUrl" class="homepageUrl" >
			</div>
		</div>
		
	
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius submit_data" type="button"><i class="Hui-iconfont">&#xe632;</i> 提交保存</button>
				<button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
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
<script type="text/javascript" src="${request.contextPath}/static/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/file/webuploadfile.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/file/uploadfile.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/static/js/loading.js"></script>

<script type="text/javascript" src="${request.contextPath}/static/js/tzItem/add.js"></script> 

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
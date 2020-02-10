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
<title>商品详情</title>
</head>
<body>
<div class="pd-30">
	<table class="table" border="1">
		<tbody>
		   <input id="itemId" type="hidden" name="id" value=${id}>
		  <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></span>商品编号：</label>
				<div class="" id="id"></div>
		  </div>
		  </br>
		  <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"></span>商品标题：</label>
				<div class="" id="itemTitle"></div>
		  </div>
		  </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品分类：</label>
				<div class="" id="category_name"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>所属平台ID：</label>
				<div class="" id="mallId"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>操作员ID：</label>
				<div class="" id="operater"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品条形码：</label>
				<div class="" id="barcode"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品价格：</label>
				<div><span class="" id="price"></span><span>元</span></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品会员价格：</label>
			    <div><span class="" id="memberPrice"></span><span>元</span></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品爱心价格：</label>
			     <div><span class="" id="lovePrice"></span><span>元</span></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品库存量：</label>
				<div class="" id="num"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品买点描述：</label>
				<div class="" id="description"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品上下架：</label>
				<div class="" id="status"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品已售出件数：</label>
			    <div><span class="" id="sales_num"></span><span>件</span></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品创建时间：</label>
				<div class="" id="createdTime"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品更新时间：</label>
				<div class="" id="updatedTime"></div>
		    </div>
		    </br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品缩略图片：</label>
				<div class="col-xs-8 col-sm-10 homePageUrl"></div>
		    </div>
		    </br></br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品轮播图片：</label>
				<div class="col-xs-8 col-sm-10 images"></div>
		    </div>
		    </br></br>
		   <div class="row cl">
			    <label class="form-label col-xs-4 col-sm-2"></span>商品详情广告图片：</label>
				<div class="col-xs-8 col-sm-10 prictures"></div>
		    </div>
			
		</tbody>
	</table>
</div>
		
  
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui.admin/js/H-ui.admin.js"></script> 

<!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/js/tzItem/detail.js"></script> 
 
</body>
</html>

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
<title>订单列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单列表 <a class="btn btn-success radius r btn-refresh" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
		 日期范围：
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})" id="startTime" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})" id="endTime" class="input-text Wdate" style="width:120px;">
		<input type="text" name="" id="orderId" placeholder="订单编号" style="width:250px" class="input-text">
		<span style="width:150px;">
			 <select class="btn btn-primary radius"  name="" size="1">
				<option value="">所有订单</option>
				<option value="2">未发货订单</option>
				<option value="0">已取消订单</option>
				<option value="1">未付款订单</option>
				<option value="3">已发货订单</option>
				<option value="4">交易成功</option>
				<option value="5">交易关闭</option>
				<option value="6">已退款订单</option>
			</select>
			</span>
		<button name="" id="" class="btn btn-success search_btn" type="submit"><i class="Hui-iconfont">&#xe665;</i> 检索订单</button>
	</div>
	 <div class="cl pd-5 bg-1 bk-gray mt-20"> 
	 <span class="r">共有订单数 : <strong style="color:red" class="data_count"></strong>条&nbsp;&nbsp;</span>
	 </div>
	 <span id="batchSend" class="l"></span>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="40">
						<input id="orderId" name="" type="checkbox"/>
					</th>
					<th width="70">订单编号</th>
					<th width="70">用户ID</th>
					<th width="70">运单编号</th>
					<th width="70">订单状态</th>
					<th width="70">实付金额</th>
					<th width="70">使用爱心值</th>
					<th width="70">购买件数</th>
					<th width="70">付款方式</th>
					<th width="70">订单属性</th>
					<th width="80">创建时间</th>
					<th width="80">更新时间</th>
					<th width="80">订单详情</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody class="data_box">
				
			</tbody>
			
		</table>
		<div class="ml_content_false" style="display:none;margin-top: 20px;">暂无数据！</div>
		<div id="project_page"></div> 
		 <@shiro.hasPermission name="all"><div class="delete_v" style="display:none">shanchu</div> </@shiro.hasPermission> 
<!--        <@shiro.hasPermission name="all"></@shiro.hasPermission>  -->
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/order/list.js"></script>
	<script type="text/javascript" >
    /*产品-添加*/
	function item_add(title,url){
		var index = layer.open({
			type: 2,
			title: title,
			content: url
		});
		layer.full(index);
	}
	/*用户-编辑*/
	function user_edit(title,id,w,h){
		url =  "/lanbo/admin/user/addOrUpdatePage?type=update&id="+id;
		layer_show(title,url,w,h);
	}
</script>
</body>
</html>
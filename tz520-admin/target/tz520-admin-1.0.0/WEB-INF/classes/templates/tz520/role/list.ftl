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
<title>角色列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 角色列表 <a class="btn btn-success radius r btn-refresh" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
		 日期范围：
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})" id="logmax" class="input-text Wdate" style="width:120px;">
		<input type="text" name="realName" id="realName" placeholder="角色" style="width:250px" class="input-text">
		<input type="text" name="phone" id="phone" placeholder=" 关键词" style="width:250px" class="input-text">
		<button name="" id="" class="btn btn-success search_btn" type="submit"><i class="Hui-iconfont">&#xe665;</i> 检索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a class="btn btn-primary radius" onclick="admin_role_add('添加角色','/tz/admin/role/addOrUpdatePage?type=add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a></span> <span class="r">共有数据：<strong class="data_count"></strong> 条</span><@shiro.hasPermission name="all"> <span class="r">推荐订单总数 : <strong style="color:red" class="tjtotal">0 </strong>单&nbsp;&nbsp;</span><span class="r">用户总数 : <strong style="color:red" class="userTotal">0 </strong>人&nbsp;&nbsp;</span></@shiro.hasPermission>  </div>
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="70">序号</th>
					<th width="50">角色名称</th>
					<th width="70">角色关键词</th>
					<th width="70">角色描述</th>
					<th width="70">数据操作员</th>
					<th width="70">创建时间</th>
					<th width="70">更新时间</th>
					<th width="120">操作</th>
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

<script type="text/javascript" src="${request.contextPath}/static/js/role/list.js"></script>  
</body>
</html>
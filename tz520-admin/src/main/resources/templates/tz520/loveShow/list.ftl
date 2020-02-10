﻿<!DOCTYPE HTML>
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
<title>用户列表</title>
</head>
<body>
<nav class="breadcrumb"> 爱心值提现管理<span class="c-gray en">&gt;</span> 列表 <a class="btn btn-success radius r btn-refresh" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		 日期范围：
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})" id="startTime" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})" id="endTime" class="input-text Wdate" style="width:120px;">
		<input type="text" name="userPhone" id="userPhone" placeholder="会员电话" style="width:200px" class="input-text">
		<input type="text" name="userName" id="userName" placeholder="会员名称" style="width:170px" class="input-text">
		<input type="text" name="payeeRealName" id="payeeRealName" placeholder="收款人真实姓名" style="width:170px" class="input-text">
		<button name="" id="" class="btn btn-success search_btn" type="submit"><i class="Hui-iconfont">&#xe665;</i> 检索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	   <div>爱心值提取处理状态：<select id="selectType" style="width:100px"name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="select">
			<option value="">全部</option>
			<option value="1">已处理</option>
			<option value="0">未处理</option>
			</select>
	   </div>
			<span class="r">共有数据：<strong class="data_count"></strong> 条</span> 
			<@shiro.hasPermission name="exportOrderData">
			<div><a class="btn btn-primary radius" onclick="ExportExcel('导出爱心值提取数据','/tz/admin/loveShow/userLoveShowAlterPage?type=exportExcel')" href="javascript:;"><i class="Hui-iconfont">&#xe640;</i> 导出当前条件下爱心值提取数据</a></div>
			</@shiro.hasPermission>
		</div>
		<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="70">会员名</th>
					<th width="70">会员昵称</th>
					<th width="70">会员电话</th>
					<th width="70">提现类型</th>
					<th width="70">提现账号</th>
					<th width="70">提现爱心值</th>
					<th width="90">收款人真实姓名</th>
					<th width="80">创建时间</th>
					<th width="80">修改时间</th>
					<th width="80">到账时间</th>
					<th width="80">状态</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody class="data_box">
				
			</tbody>
			
		</table>
		<div class="ml_content_false" style="display:none;margin-top: 20px;">暂无数据！</div>
		<div id="project_page"></div> 
		<input type="hidden" class="data_id" value="<#if refereeId??>${refereeId }</#if>">
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

<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本  <script type="text/javascript" src="${request.contextPath}/static/js/recommend/list.js"></script>-->
<script type="text/javascript" src="${request.contextPath}/static/js/loading.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/loveShow/list.js"></script>


</body>
</html>
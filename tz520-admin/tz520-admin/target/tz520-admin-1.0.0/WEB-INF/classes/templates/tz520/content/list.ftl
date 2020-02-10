﻿
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
<title>内容列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 内容管理 <span class="c-gray en">&gt;</span> 内容列表 <a class="btn btn-success radius r btn-refresh" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
		 日期范围：
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})"id="startTime" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'2017-01-01',maxDate:''})" id="endTime"  class="input-text Wdate" style="width:120px;">
		<input type="text" name="contentTitle" id="contentTitle" placeholder="内容大标题" style="width:150px" class="input-text">
		<input type="text" name="subTitle" id="subTitle" placeholder="内容小标题" style="width:150px" class="input-text">
		<span class="select-box" style="width:150px;">
				<select name="category" class="select">
				</select>
		</span>
		<button name=""  class="btn btn-success search_btn" type="submit"><i class="Hui-iconfont">&#xe665;</i> 检索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	
	<span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
	    <a class="btn btn-primary radius" onclick="content_add('添加内容','/tz/admin/tzContent/contentAlterPage?type=add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加内容</a></span>
	 <span class="r">共有数据：<strong class="data_count"></strong>条</span>
	 <span class="r">内容数据总数 : <strong style="color:red" class="userTotal">0 </strong>条&nbsp;&nbsp;</span>
	
	 </div>
	 
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="20">
						<input id="contentId" name="" type="checkbox"/>
					</th>
					<th width="70">所属分类名称</th>
					<th width="70">内容大标题</th>
					<th width="70">内容小标题</th>
					<th width="70">标题描述</th>
					<th width="70">跳转地址</th>
					<th width="70">内容描述</th>
					<th width="70">操作者</th>
					<th width="70">创建时间</th>
					<th width="70">更新时间</th>
					<th width="70">操作</th>
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
<script type="text/javascript" src="${request.contextPath}/static/js/content/list.js"></script>
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
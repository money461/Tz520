﻿<!DOCTYPE HTML>
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
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/hui//h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/hui//h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/hui//h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台管理</title>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/lanbo/admin/userAdmin/index">后台管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="../aboutHui.shtml">H-ui</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs time_now"></span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
			<!-- <ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
							<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
							<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a>
							</li>
					</ul>
				</li>
			</ul> -->
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li class="dropDown dropDown_hover">
				 <!-- <a href="#" class="dropDown_A">admin<i class="Hui-iconfont">&#xe6d5;</i></a> -->
				   <shiro:authenticated> 欢迎管理员：[${managerName}]</shiro:authenticated>
				    	<ul class="dropDown-menu menu radius box-shadow">
						<!-- <li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li> -->
						<li><a href="/tz/admin/manager/logout" >切换账户</a></li>
						<li><a href="/tz/admin/manager/logout">退出</a></li>
				</ul>
			</li>
				<!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li class="green"><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
	<@shiro.hasPermission name="memberManager">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul class="user_ul_box">
					<@shiro.hasPermission name="queryUser"><li class="user_li"><a data-href="/tz/admin/user/listPage"  data-title="会员列表" href="javascript:void(0)">会员列表</a></li></@shiro.hasPermission>
					<@shiro.hasPermission name="commendManager"><li class="user_li"><a data-href="/tz/admin/recommend/listAllPage"  data-title="推荐管理" href="javascript:void(0)">推荐管理</a></li></@shiro.hasPermission>
					<@shiro.hasPermission name="queryLoveValue"><li class="user_li"><a data-href="/tz/admin/userLove/listPage"  data-title="爱心值管理" href="javascript:void(0)">爱心值管理</a></li></@shiro.hasPermission>
					<@shiro.hasPermission name="queryLoveValueConsume"><li class="user_li"><a data-href="/tz/admin/userLoveDetails/listPage"  data-title="爱心值消费明细" href="javascript:void(0)">爱心值消费明细</a></li></@shiro.hasPermission>
					<@shiro.hasPermission name="queryLoveValueWithdraws"><li class="user_li"><a data-href="/tz/admin/loveShow/listPage"  data-title="爱心值提现管理" href="javascript:void(0)">爱心值提现管理</a></li></@shiro.hasPermission>
				</ul>
		</dd>
	</dl>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="itemManager">
	<dl id="menu-article">
			
			<dt><i class="Hui-iconfont">&#xe620; </i>商品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul class="user_ul_box">
					<@shiro.hasPermission name="queryItem"><li><a data-href="/tz/admin/tzItem/listPage" data-title="商品列表" href="javascript:void(0)">商品列表</a></li></@shiro.hasPermission>
					<@shiro.hasPermission name="queryItemCategory"><li><a data-href="/tz/admin/tzItemCategory/listPage" data-title="商品分类" href="javascript:void(0)">商品分类</a></li></@shiro.hasPermission>
			   </ul>
		</dd>
	</dl>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="contentManager">
	<dl id="menu-article">
			
			<dt><i class="Hui-iconfont">&#xe620; </i>内容管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<@shiro.hasPermission name="queryContent"><li><a data-href="/tz/admin/tzContent/listPage" data-title="内容列表" href="javascript:void(0)">内容列表</a></li></@shiro.hasPermission >
					<@shiro.hasPermission name="queryContentCategory"><li><a data-href="/tz/admin/tzContentCategory/listPage" data-title="内容分类" href="javascript:void(0)">内容分类</a></li></@shiro.hasPermission>
			</ul>
		</dd>
	</dl>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="orderManager"> 
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe670;</i> 订单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<@shiro.hasPermission name="queryOrder"><li><a data-href="/tz/admin/order/listPage" data-title="订单列表" href="javascript:void(0)">订单列表</a></li></@shiro.hasPermission>
			</ul>
		</dd>
	</dl> 
	</@shiro.hasPermission>
	<@shiro.hasPermission name="expressManager">
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe670;</i> 物流管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<@shiro.hasPermission name="queryExpress"><li><a data-href="/tz/admin/expressCom/listPage" data-title="快递管理" href="javascript:void(0)">快递管理</a></li></@shiro.hasPermission>
			</ul>
		</dd>
	</dl> 
	</@shiro.hasPermission>
	<@shiro.hasPermission name="intentionalUserManger">
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe670;</i> 意向用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<@shiro.hasPermission name="queryIntentionalUser"><li><a data-href="/tz/tzIntentionalUser/listPage" data-title="意向用户列表" href="javascript:void(0)">意向用户列表</a></li></@shiro.hasPermission>
			</ul>
		</dd>
	</dl> 
	</@shiro.hasPermission>
	<@shiro.hasRole name="admin"> 
    <dl id="menu-admin">
		
			<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
	 				<@shiro.hasPermission name="queryFucntion"><li><a data-href="/tz/admin/function/listPage" data-title="权限管理" href="javascript:void(0)">权限管理</a></li></@shiro.hasPermission>
					<@shiro.hasPermission name="queryRole"><li><a data-href="/tz/admin/role/listPage" data-title="角色管理" href="javascript:void(0)">角色管理</a></li></@shiro.hasPermission>
					<@shiro.hasPermission name="queryManager"><li><a data-href="/tz/admin/manager/listPage" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li></@shiro.hasPermission>
			</ul>
		</dd>
	</dl>
   </@shiro.hasRole>
	<@shiro.hasPermission name="systemManager">
	<dl id="menu-system">
				<dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/tz/admin/dictionary/listPage" data-title="数据字典" href="javascript:void(0)">数据字典</a></li>
						<li><a data-href="/tz/admin/city/listPage" data-title="城市设置" href="javascript:void(0)">城市设置</a></li>
						<li><a data-href="/tz/admin/appDown/listPage" data-title="版本设置" href="javascript:void(0)">版本设置</a></li>
					</ul>
			</dd>
		</dl>
   </@shiro.hasPermission>
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
 		 		<li class="active">
					<span data-href="/tz/admin/manager/welcome">欢迎使用</span>
					<em></em>
					<i></i>
				</li> 
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
 		 		<iframe frameborder="0" src="/tz/admin/manager/welcome"></iframe> 
	 	
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui//h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui//h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript"> 
$(function(){
	now = new Date(),hour = now.getHours() 
	if(hour < 6){$(".time_now").text("凌晨好！")} 
	else if (hour < 9){$(".time_now").text("早上好！")} 
	else if (hour < 12){$(".time_now").text("上午好！")} 
	else if (hour < 14){$(".time_now").text("中午好！")} 
	else if (hour < 17){$(".time_now").text("下午好！")} 
	else if (hour < 19){$(".time_now").text("傍晚好！")} 
	else if (hour < 22){$(".time_now").text("晚上好！")} 
	else {$(".time_now").text("夜里好！")} 
});
$(".user_ul_box").on("click","user_li",function(){
	alert(1111)
});
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 
</body>
</html>
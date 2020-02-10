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
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/hui/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/hui/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/hui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/hui/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/hui/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户查看</title>
</head>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
	<img class="avatar size-XL l" src="${request.contextPath}/static/admin/hui/h-ui/images/ucnter/avatar-default.jpg">
	<dl style="margin-left:80px; color:#fff">
		<dt>
			<span class="f-18 realName"></span>
			<span class="pl-10 f-12 userName"></span>
		</dt>
		<dd class="pt-10 f-12 sortNumber" style="margin-left:0"></dd>
	</dl>
</div>
<div class="pd-20">
	<table class="table">
		<tbody>
			<tr>
				<th class="text-r ">手机：</th>
				<td class="phone"></td>
			</tr>
			<tr>
				<th class="text-r ">身份证：</th>
				<td class="idCard"></td>
			</tr>
			<tr>
				<th class="text-r ">用户类型：</th>
				<td class="userType"></td>
			</tr>
			<tr>
				<th class="text-r ">省份名：</th>
				<td class="provinceName"></td>
			</tr>
			<tr>
				<th class="text-r ">代理省份名：</th>
				<td class="dprovinceName"></td>
			</tr>
			<tr>
				<th class="text-r ">代理级别：</th>
				<td class="team"></td>
			</tr>
			<tr>
				<th class="text-r">开户行名称：</th>
				<td class="bankName"></td>
			</tr>
			<tr>
				<th class="text-r ">银行卡号：</th>
				<td class="bankIdCard"></td>
			</tr>
			<tr>
				<th class="text-r ">邮政编号：</th>
				<td class="postalNumber"></td>
			</tr>
			<tr>
				<th class="text-r ">收货地址：</th>
				<td class="deliveryAddress"></td>
			</tr>
			<tr>
				<th class="text-r ">修改时间：</th>
				<td class="updateTime"></td>
			</tr>
			<tr>
				<th class="text-r ">创建时间：</th>
				<td class="createTime"></td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" class="data_id" value="<#if id??>${id }</#if>">
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/admin/hui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/admin/hui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/admin/hui/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/admin/hui/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/admin/js/user/detail.js"></script> 
 
</body>
</html>
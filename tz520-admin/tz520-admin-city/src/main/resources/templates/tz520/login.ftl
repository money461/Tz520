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
<link href="${request.contextPath}/static/hui/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath}/static/hui/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath}/static/hui/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath}/static/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台登录</title>
<meta name="keywords" content="H-ui.admin v3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<style type="text/css">
	.validate-code {
		position: absolute;
		right: 0;
		top: 15px;
		width: 250px;
		height: 35px;
	}
       #code  
       {  
           font-family:Arial;  
           font-style:italic;  
           font-weight:bold;  
           border:0;  
           letter-spacing:4px;  
           color:blue;  
           height: 40px;
    	   width: 100px;
       }  
</style>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <div class="form form-horizontal" action="index.html" method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="phone" name="phone" type="text" placeholder="账户名（请输入手机号码）" class="input-text size-L managerName">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L passWord">
        </div>
      </div>
     <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" id="input" type="text" placeholder="验证码"  style="width:150px;">
          <img src=""> <a id="kanbuq" href="#"><input type = "button" id="code" onclick="createCode()"/> </a> </div>
      </div>

      
<!--       <@shiro.hasPermission name="all">
      </@shiro.hasPermission>  -->
      
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </div>
  </div>
</div>
<div class="footer">天智520后台管理系统</div>
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/login/login.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" >
	if(window != top) 
		top.location.href = location.href;5
</script>



<!doctype html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>404 - 对不起，您查找的页面不存在！- JS代码站</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/404/main.css">
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>
<div id="wrapper"><a class="logo" href="/"></a>
  <div id="main">
    <header id="header">
      <h1><span class="icon">!</span>404<span class="sub">page not found</span></h1>
    </header>
    <div id="content">
      <h2>访问出错啦！</h2>
      <p>错误信息：${message}</p>
      <div class="utilities">
        <form  name="formsearch" action="/plus/search.php" id="formkeyword">
          <div class="input-container">
           
          </div>
        </form>
        <a class="button right" href="#" onClick="history.go(-1);return true;">返回...</a>
        <div class="clear"></div>
      </div>
    </div>
  </div>
</div>
</html>
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
<script type="text/javascript" src="${request.contextPath}/static/js/common.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="${request.contextPath}/static/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->
<title>订单详情</title>
</head>
<body>
<div class="pd-20">
	<table border="1" class="table">
		<tbody>
		   <input id="orderId" type="hidden" name="id" value=${id}>
			<tr>
				<th  width="80" class="text-l ">订单编号：</th>
				<td class="" id="id" name="id"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">商家平台ID：</th>
				<td class="" id="mallId" name="mallId"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">买家编号ID：</th>
				<td class="" id="userId" name="userId"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">买家账户：</th>
				<td class="" id="userPhone" name="userPhone"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">买家昵称：</th>
				<td class="" id="buyerNick" name="buyerNick"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">支付账户：</th>
				<td class="" id="account" name="account"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">支付方式：</th>
				<td class="" id="paymentType" name="paymentType"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">订单属性：</th>
				<td class="" id="type" name="type"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">订单状态:</th>
				<td class="" id="status" name="status"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">订单创建时间:</th>
				<td class="" id="createdTime" name="createdTime"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">订单更新时间:</th>
				<td class="" id="updatedTime" name="updatedTime"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">订单付款时间:</th>
				<td class="" id="paymentTime" name="paymentTime"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">发货时间:</th>
				<td class="" id="consignTime" name="consignTime"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">交易完成时间:</th>
				<td class="" id="endTime" name="endTime"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">交易关闭时间:</th>
				<td class="" id="closeTime" name="closeTime"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">买家留言:</th>
				<td class="" id="buyerMessage" name="buyerMessage"></td>
			</tr>
			<tr>
				<th  width="80" class="text-l ">买家是否评价:</th>
				<td class="" id="buyerRate" name="buyerRate"></td>	
			</tr>
			<tr>
				<th  width="80" class="text-l ">订单操作人：</th>
				<td class="" id="operater" name="operater"></td>
			</tr>
		</tbody>
	</table>
</div>
		<div class="table-responsive m-t">
            <table border="1" class="table invoice-table">
              <thead>
                <tr>
                    <th width="80">商品ID</th>
                    <th>商品名称</th>
                    <th>商品单价</th>
                    <th>会员单价</th>
                    <th>实付单价</th>
                    <th>购买数量</th>
                    <th>商品邮费</th>
                    <th>商品小计(含商品邮费)</th>
                </tr>
                </thead>
                <tbody class="data_box">
                	
	     	   </tbody>
	    </table>
	  </div>
	  
	  <div>
        <table border="1" class="table invoice-total">
            <tbody>
            <tr>
                <th  width="80">总邮费：</th>
                <td class="" id="postFee"></td>
            </tr>
            <tr>
                <th  width="80" >总件数：</th>
                <td class="" id="orderNum"></td>
            </tr>
            <tr>
                <th width="80">使用爱心值：</th>
                <td class="" id="loveValue"></td>
            </tr>
            <tr>
                <th  width="80">实付总金额：</th>
                <td class="" id="payment"></td>
            </tr>
            </tbody>
        </table>
       </div>
       
  <div class="pd-20">
  <table border="1" class="table">
		<tbody>  
		<tr>
			<th  width="80"class="text-l ">运单编号：</th>
				<td class="" id="shippingCode" name="shippingCode"></td>
			</tr>
		<tr>
				<th  width="80"class="text-l ">快递公司：</th>
				<td class="" id="companyName" name="companyName"></td>
			</tr>
        <tr>
			<th  width="80" class="text-l ">收件人姓名:</th>
			<td class="" id="receiverName" name="receiverName"></td>	
		</tr>
        <tr>
			<th  width="80" class="text-l ">手机号码:</th>
			<td class="" id="receiverMobile" name="receiverMobile"></td>	
		</tr>
        <tr>
			<th  width="80" class="text-l ">地址-省份:</th>
			<td class="" id="receiverState" name="receiverState"></td>	
		</tr>
        <tr>
			<th  width="80" class="text-l ">地址-城市:</th>
			<td class="" id="receiverCity" name="receiverCity"></td>	
		</tr>
        <tr>
			<th  width="80" class="text-l ">地址-区/县:</th>
			<td class="" id="receiverDistrict" name="receiverDistrict"></td>	
		</tr>
        <tr>
			<th  width="80" class="text-l ">详细地址:</th>
			<td class="" id="receiverAddress" name="receiverAddress"></td>	
		</tr>
		  <tr>
			<th  width="80" class="text-l ">座机号码:</th>
			<td class="" id="receiverPhone" name="receiverPhone"></td>	
		</tr>
        <tr>
			<th  width="80" class="text-l ">邮政编码:</th>
			<td class="" id="receiverZip" name="receiverZip"></td>	
		</tr>
      </tbody> 
  </table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/hui/h-ui.admin/js/H-ui.admin.js"></script> 

<!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${request.contextPath}/static/js/order/detail.js"></script> 
 
</body>
</html>
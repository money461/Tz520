package com.tz.interfaces;

import com.tz.res.AppMsgResult;

public interface OrderService {
	//订单校验确认页面
	AppMsgResult validateOrder(String items, String userId, String userToken);

	//创建订单
	AppMsgResult createOrder(String userId, String orderInfo,Integer type,String orderToken, String userToken);

	//根据订单id查询订单详情
	AppMsgResult queryOrderById(String orderId, String userId, String userToken);

	//根据用户id分页查询订单列表信息
	AppMsgResult queryOrderByUserId(String userId, Integer status,String userToken,Integer curPage, Integer rows);

	//根据订单id修改订单状态
	AppMsgResult alertOrderStatus(String userId, String orderId, Integer status, String userToken);


	//支付订单前或者查询我的订单校验payOrderCache_key 是否存在
	public AppMsgResult checkPayOrder(String userId, String orderId);

}

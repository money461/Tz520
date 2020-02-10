package com.tz.service;

import com.tz.pojo.TzOrder;
import com.tz.pojo.vo.TzOrderVo;
import com.tz.res.MsgResult;

public interface OrderService  {
	
	//订单列表查询
	public MsgResult getOrderList(Integer curPage, Integer rows, TzOrderVo tzOrder);


	//查看订单详情
	public MsgResult findOrderDetail(String id);

	//修改订单状态取消订单,发货，退款等信息 修改status
	public MsgResult alterOrder(String type, String id);
		 
}

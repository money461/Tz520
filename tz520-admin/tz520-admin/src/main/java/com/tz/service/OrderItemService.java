package com.tz.service;

import com.tz.pojo.TzOrderItem;
import com.tz.res.MsgResult;

public interface OrderItemService {

	//根据订单id查询订单--商品信息
	MsgResult findOrderItemById(String orderId);

	//编辑未支付订单信息中商品价格，数量，小计金额等信息后进行更新保存
	MsgResult updateOrderItem(String sampleInfo, String postFee,String payment);

}

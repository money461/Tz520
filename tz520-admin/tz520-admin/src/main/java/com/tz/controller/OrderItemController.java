package com.tz.controller;

import java.util.List;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderItem;
import com.tz.res.MsgResult;
import com.tz.service.OrderItemService;

/**
 * 订单--商品管理处理类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/orderItem")
public class OrderItemController {


	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderItemService orderItemService;
	
	/**
	 * 根据订单id查询订单--商品信息
	 * @param orderId
	 * @return
	 */
	@PostMapping("/findOrderItemById")
	public MsgResult findOrderItemById(String orderId){
		
		MsgResult result = orderItemService.findOrderItemById(orderId);
		return result;
		
	}

	/**
	 * 编辑未支付订单信息中商品价格，数量，总金额等信息后进行更新保存
	 * @param orderItem
	 * @return
	 */
	@PostMapping("/updateOrderItem")
	public MsgResult updateOrderItem(String sampleInfo,String postFee,String payment){
	 MsgResult result = orderItemService.updateOrderItem(sampleInfo,postFee,payment);
		return result;
		
	}
	
	
}

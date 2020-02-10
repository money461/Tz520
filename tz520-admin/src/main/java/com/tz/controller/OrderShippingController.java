package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.TzOrderShipping;
import com.tz.res.MsgResult;
import com.tz.service.OrderShippingService;

/**
 * 订单物流信息处理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/orderShipping")
public class OrderShippingController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderShippingService orderShippingService;
	
	/**
	 * 根据订单id查询订单物流信息并回显至编辑修改物流信息
	 * @param id
	 * @return
	 */
	@PostMapping("/findOrderShippingById")
	public MsgResult findOrderShippingById(String orderId){
		MsgResult result = orderShippingService.findOrderShippingById(orderId);
		return result;
		
	}
	
	/**
	 * 录入修改快递物流信息
	 * @param orderShipping
	 * @return
	 */
	@PostMapping("/updateOrderShipping")
	public MsgResult updateOrderShipping(TzOrderShipping orderShipping){
		System.out.println("------修改录入运单信息--------------");
		MsgResult result = orderShippingService.updateOrderShipping(orderShipping);
		return result;
		
	}
}

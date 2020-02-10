package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.List;

import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderShipping;

public class TzOrderVo extends TzOrder implements Serializable{
 
	/**
	 *  封装查看订单详情页面信息
	 */
	private static final long serialVersionUID = 4723313559321153890L;

	//订单中包含多个商品信息
	private List<TzOrderItemVo> orderItems;
	
	//订单中包含单个收货人地址信息
	private TzOrderShipping orderShipping;


	public List<TzOrderItemVo> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TzOrderItemVo> orderItems) {
		this.orderItems = orderItems;
	}

	public TzOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TzOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	

	
	
}
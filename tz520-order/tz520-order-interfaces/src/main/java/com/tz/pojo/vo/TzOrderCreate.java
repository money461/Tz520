package com.tz.pojo.vo;

import java.util.List;

import com.tz.pojo.TzItem;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderShipping;

/**
 * 生成订单
 * @author Administrator
 *
 */
public class TzOrderCreate extends TzOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -835913971975768303L;

	private List<TzItem> orderItems;
	
	private TzOrderShipping orderShipping;

	public List<TzItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TzItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TzOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TzOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	
	
}

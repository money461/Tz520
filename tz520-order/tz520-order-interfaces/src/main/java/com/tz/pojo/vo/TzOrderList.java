package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户订单列表信息pojo封装
 * @author Administrator
 *
 */
public class TzOrderList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1446232552087944832L;

	private String orderId;
	
	private int status;
	 
	private BigDecimal payment;
	
	private BigDecimal postFee;
	
	private Integer orderNum;
	
	private String shippingCode;
	
	private String com;
	 
	//订单中包含多个商品信息
	private List<TzOrderItemVo> orderItems;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public BigDecimal getPostFee() {
		return postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	
	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public List<TzOrderItemVo> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TzOrderItemVo> orderItems) {
		this.orderItems = orderItems;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}
	
	
}

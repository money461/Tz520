package com.tz.pojo.vo;

import java.util.Date;
import java.util.List;

import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderShipping;

public class TzOrderVo extends TzOrder{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1003425264898192369L;
	// 查询开始时间
	private Date startTime;
	// 更新结束时间
	private Date endTime;

	private String userPhone; //买家账户
	
	private String consignName; //收货人名称
	
	// 订单中包含多个商品信息
	private List<TzOrderItemVo> orderItems;

	// 订单中包含单个收货人地址信息
	private TzOrderShipping orderShipping;

	private String companyName;  //快递公司名称
	
	public TzOrderVo() {
		super();
	}

	

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getUserPhone() {
		return userPhone;
	}



	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}



	public String getConsignName() {
		return consignName;
	}



	public void setConsignName(String consignName) {
		this.consignName = consignName;
	}

	
}	

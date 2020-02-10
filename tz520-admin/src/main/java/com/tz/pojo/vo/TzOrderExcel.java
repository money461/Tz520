package com.tz.pojo.vo;

import java.io.Serializable;
/**
 * 导出订单数据至excel(一对一数据)
 * @author Administrator
 *
 */
import java.math.BigDecimal;
import java.util.List;

import com.tz.pojo.TzOrderItem;
public class TzOrderExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3532668733222942979L;
/**
 * String[] title = new String[]{"订单编号","买家编号","买家账户","支付账户","实付总金额","实付总邮费","购买总件数","抵扣爱心值","支付方式","订单状态","订单属性",
    							"运单编号","货运公司名称","收货人姓名","收件人电话","省份","城市","区县","详细地址","订单创建时间","订单更新时间","订单付款时间","订单发货时间","订单完成时间","订单关闭时间"};
    	String[] filed = new String[]{"id","userId","userPhone","account","payment","postFee","orderNum","loveValue","paymentType","status","type",
    			                "shippingCode","companyName","receiverName","receiverMobile","receiverState","receiverCity","receiverDistrict","receiverAddress",
    			                 "createdTime","updatedTime","paymentTime","consignTime","endTime","closeTime"};
 */
	private String id;  //订单id
	
	private String userId; //用户id
	
	private String userPhone;
	
	private String account;
	
	private BigDecimal payment;
	
	private BigDecimal postFee;
	
	private Integer orderNum;
	
	private Integer loveValue;
	
	private BigDecimal discount;
	
	private Integer paymentType;
	
	private Integer status;
	
	private Integer type;
	
	private String shippingCode;
	
	private String companyName;
	
	private String receiverName;
	
	private String receiverMobile;
	
	private String receiverState;
	
	private String receiverCity;
	
	private String receiverDistrict;
	
	private String receiverAddress;
	
	//订单对应多个商品
	private List<TzOrderItem> orderItemList;
	
	private String createdTime;
	
	private String updatedTime;
	
	private String paymentTime;
	
	private String consignTime;
	
	private String endTime;
	
	private String closeTime;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public Integer getLoveValue() {
		return loveValue;
	}

	public void setLoveValue(Integer loveValue) {
		this.loveValue = loveValue;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getConsignTime() {
		return consignTime;
	}

	public void setConsignTime(String consignTime) {
		this.consignTime = consignTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public List<TzOrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<TzOrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
	
}

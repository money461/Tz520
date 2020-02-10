package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.tz.pojo.TzReceiverinfo;

/**
 * 订单确认校验页面pojo封装页面参数
 * @author Administrator
 *
 */
public class TzOrderValidate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6872303499664313908L;
	//生成订单令牌
	private String orderToken;
	//封装收货地址信息
	private TzReceiverinfo receiverinfo;
	//封装商品信息 标题 描述  商品价，会员价，特殊价 运费 件数 
	private List<TzOrderItemVo>  orderItemList;
	//商品需付款
	private BigDecimal cash; //不含总邮费
	//最终实际付款
	private BigDecimal payment; //cash+postFee-cash*discount-loveValue*0.01
	//总的邮费
    private BigDecimal postFee;
    //该订单商品总件数
    private int totalNum;
    
	//优惠折扣价
     private BigDecimal discount;
     //折扣描述
	 private String discountDesc;
	//该用户剩余爱心值展示
	 private int loveValue;
	 //爱心值状态
	 private int status;
	 
	public String getOrderToken() {
		return orderToken;
	}
	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}
	public TzReceiverinfo getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(TzReceiverinfo receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public List<TzOrderItemVo> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<TzOrderItemVo> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
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
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public String getDiscountDesc() {
		return discountDesc;
	}
	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}
	public int getLoveValue() {
		return loveValue;
	}
	public void setLoveValue(int loveValue) {
		this.loveValue = loveValue;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 
	 
}

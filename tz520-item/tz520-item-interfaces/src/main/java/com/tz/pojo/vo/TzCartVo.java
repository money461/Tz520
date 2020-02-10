package com.tz.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.tz.pojo.TzCart;
import com.tz.res.Constant;

public class TzCartVo extends TzCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7132754386772094814L;
	
	 private String itemTitle;
	 private String description;
	 private BigDecimal price;
	 private BigDecimal memberPrice;
	 private BigDecimal lovePrice;
	 private BigDecimal realPrice; //购物车中该用户等级下对应的商品的单价
	 private String homepageUrl;
	 private Byte status;
	 
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMemberPrice() {
		return memberPrice;
	}
	
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}
	
	public BigDecimal getLovePrice() {
		return lovePrice;
	}
	public void setLovePrice(BigDecimal lovePrice) {
		this.lovePrice = lovePrice;
	}
	public String getHomepageUrl() {
		return homepageUrl;
	}
	public void setHomepageUrl(String homepageUrl) {
		if(homepageUrl!=null && !"".equals( homepageUrl)){
			homepageUrl =Constant.FILESERVER_URL+homepageUrl;
		}else{
			homepageUrl ="";
		}
		this.homepageUrl = homepageUrl;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	 
	 

	
}

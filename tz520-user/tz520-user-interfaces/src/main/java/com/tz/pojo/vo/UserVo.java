package com.tz.pojo.vo;


import java.io.Serializable;

import com.tz.pojo.TzUser;

public class UserVo extends TzUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4815388494107640544L;
	
	//用户类型 0 普通 1 会员 2 爱心
	private Integer type;
	
	//用户的登录token
	private String userToken;
	//商城id
	private String userMallId;
	//剩余的爱心值
	private Integer loveSurplus;
	//推荐总数
	private Integer recommendTotal;
	
	
	public Integer getRecommendTotal() {
		return recommendTotal;
	}
	public void setRecommendTotal(Integer recommendTotal) {
		this.recommendTotal = recommendTotal;
	}
	public String getUserMallId() {
		return userMallId;
	}
	public void setUserMallId(String userMallId) {
		this.userMallId = userMallId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public Integer getLoveSurplus() {
		return loveSurplus;
	}
	public void setLoveSurplus(Integer loveSurplus) {
		this.loveSurplus = loveSurplus;
	}	
	
	
}	

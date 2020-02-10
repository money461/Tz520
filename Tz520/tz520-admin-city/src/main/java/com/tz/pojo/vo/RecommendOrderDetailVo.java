package com.tz.pojo.vo;

import java.util.Date;

import com.tz.pojo.TzOrder;
import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserMall;

public class RecommendOrderDetailVo extends TzOrder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8392621563439935118L;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	//被推荐用户名
	private String userNick;
	private String userPhone;
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

	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
	
	
	
	
	
}	

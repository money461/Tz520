package com.tz.pojo.vo;

import java.util.Date;

import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserMall;

public class UserVo extends TzUser {
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	//用户类型 0 普通 1 会员 2 爱心
	private Integer type;
	//
	private String userMallId;
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUserMallId() {
		return userMallId;
	}
	public void setUserMallId(String userMallId) {
		this.userMallId = userMallId;
	}
	
}	

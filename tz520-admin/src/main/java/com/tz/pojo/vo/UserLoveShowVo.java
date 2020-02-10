package com.tz.pojo.vo;

import java.util.Date;

import com.tz.pojo.TzUserLoveShow;

public class UserLoveShowVo extends TzUserLoveShow {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5163941184621671919L;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	//用户名
	private String userName;
	//用户昵称
	private String userNick;
	//手机号码
	private String phone;
	
	private String selectType;
	
	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	

	
}	

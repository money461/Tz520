package com.tz.pojo.vo;

import java.util.Date;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.TzUserLoveShow;

public class UserLoveShowVo extends TzUserLoveShow {
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	//用户名
	private String userName;
	//手机号码
	private String phone;
	
	private String selectType;
	
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

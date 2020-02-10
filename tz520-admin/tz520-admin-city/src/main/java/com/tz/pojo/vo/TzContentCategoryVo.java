package com.tz.pojo.vo;

import java.util.Date;

import com.tz.pojo.TzContentCategory;

public class TzContentCategoryVo extends TzContentCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3953314065010435935L;
	
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	public TzContentCategoryVo() {
		super();
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

	
}

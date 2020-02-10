package com.tz.pojo.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tz.pojo.TzDictionary;

public class TzDictionaryVo extends TzDictionary {

	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	public TzDictionaryVo() {
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

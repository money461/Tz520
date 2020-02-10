package com.tz.pojo.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tz.pojo.TzExpressCom;

public class TzExpressComVo extends TzExpressCom {

	// 查询开始时间
	private Date startTime;
	// 更新结束时间
	private Date endTime;
	
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
	public List<TzExpressComVo> getExpressComList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

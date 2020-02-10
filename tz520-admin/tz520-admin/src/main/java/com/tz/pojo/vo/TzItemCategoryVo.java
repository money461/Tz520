package com.tz.pojo.vo;

import java.util.Date;
import java.util.List;

import com.tz.pojo.TzItem;
import com.tz.pojo.TzItemCategory;
import com.tz.res.Constant;

public class TzItemCategoryVo extends TzItemCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3074442217260270756L;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	

	//修改分类图标地址
	@Override
	public String getLogoUrl() {
		String logoUrl = super.getLogoUrl();
		if(logoUrl!=null && !"".equals( logoUrl)){
			logoUrl =Constant.FILESERVER_URL+logoUrl;
		}else{
			logoUrl ="";
		}
		return logoUrl;
	}
	public TzItemCategoryVo() {
		super();
		// TODO Auto-generated constructor stub
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

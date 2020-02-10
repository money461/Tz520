package com.tz.pojo.vo;

import com.tz.pojo.TzOrderItem;
import com.tz.res.Constant;

public class TzOrderItemVo extends TzOrderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763955375586551585L;
	
	
	@Override
	public String getHomepageUrl() {
		String homepageUrl = super.getHomepageUrl();
		if(homepageUrl!=null && !"".equals( homepageUrl)){
			homepageUrl =Constant.FILESERVER_URL+homepageUrl;
		}else{
			homepageUrl ="";
		}
		return homepageUrl;
	}

	
	
	
}

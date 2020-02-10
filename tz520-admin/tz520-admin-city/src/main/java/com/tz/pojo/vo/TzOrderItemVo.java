package com.tz.pojo.vo;

import java.util.Date;

import com.tz.pojo.TzOrderItem;

public class TzOrderItemVo extends TzOrderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -590012829432289790L;
	//为缩略图赋值网路地址
	@Override
	public void setHomepageUrl(String homepageUrl) {
		if(homepageUrl!=null && !"".equals( homepageUrl)){
			homepageUrl ="http://120.55.43.176/"+homepageUrl;
		}else{
			homepageUrl ="";
		}
		super.setHomepageUrl(homepageUrl);
	}
	
	

}

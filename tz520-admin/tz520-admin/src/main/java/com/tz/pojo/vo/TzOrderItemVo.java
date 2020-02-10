package com.tz.pojo.vo;

import com.tz.pojo.TzOrderItem;
import com.tz.res.Constant;

public class TzOrderItemVo extends TzOrderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -590012829432289790L;

	//为缩略图赋值网路地址
	@Override
	public void setHomepageUrl(String homepageUrl) {
		if(homepageUrl!=null && !"".equals( homepageUrl)){
			homepageUrl =Constant.FILESERVER_URL+homepageUrl;
		}else{
			homepageUrl ="";
		}
		super.setHomepageUrl(homepageUrl);
	}
	
	

}

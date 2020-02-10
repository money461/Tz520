package com.tz.pojo.vo;

import com.tz.pojo.TzContentCategory;
import com.tz.res.Constant;

public class TzContentCategoryVo extends TzContentCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3953314065010435935L;

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
	
	

}

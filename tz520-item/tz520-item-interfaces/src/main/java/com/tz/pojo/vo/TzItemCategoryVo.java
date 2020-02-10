package com.tz.pojo.vo;

import com.tz.pojo.TzItemCategory;
import com.tz.res.Constant;

public class TzItemCategoryVo extends TzItemCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5653642938559817336L;

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

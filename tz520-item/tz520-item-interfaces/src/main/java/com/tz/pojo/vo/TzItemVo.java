package com.tz.pojo.vo;

import java.io.Serializable;

import com.tz.pojo.TzItem;
import com.tz.res.Constant;

public class TzItemVo extends TzItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7191498398263695564L;
	
	//商品分类名称
	private String categoryName;
	
	//缩略图地址添加
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




	public String[] getImages(){
		//获取从数据库查询出来的图片名称字符串
		String string = this.getImageUrl();
		if(string !=null && !"".equals(string)){
			String[] images = string.split(",");
			for(int i=0;i<images.length;i++){
				images[i] = Constant.FILESERVER_URL+images[i];
			}
			return images;
		}
		
		return new String[]{};
	}

	
	
	public String[] getPrictures(){
		String prictureUrl = this.getPrictureUrl();
		if(prictureUrl !=null && !"".equals(prictureUrl)){
			String[] prictures = prictureUrl.split(",");
			for(int i=0;i<prictures.length;i++){
				prictures[i] = Constant.FILESERVER_URL+prictures[i];
			}
			return prictures;
		}
		return new String[]{};
	}
	
	public TzItemVo() {
		super();
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


}

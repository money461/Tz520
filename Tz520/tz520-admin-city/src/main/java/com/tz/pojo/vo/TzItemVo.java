package com.tz.pojo.vo;

import java.util.Date;

import com.tz.pojo.TzItem;

public class TzItemVo extends TzItem{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1921428489759270856L;
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	//分类名称
	private String categoryName;
	
	//轮播图
	public String[] getImages(){
		//获取从数据库查询出来的图片名称字符串
		String string = this.getImageUrl();
		if(string !=null && !"".equals(string)){
			String[] images = string.split(",");
			for(int i=0;i<images.length;i++){
				images[i] = "http://120.55.43.176/"+images[i];
			}
			return images;
		}
		return new String[]{};
		
	}
	
	//商品详情广告大图
	public String[] getPrictures(){
		String prictureUrl = this.getPrictureUrl();
		if(prictureUrl !=null && !"".equals(prictureUrl)){
			String[] prictures = prictureUrl.split(",");
			for(int i=0;i<prictures.length;i++){
				prictures[i] = "http://120.55.43.176/"+prictures[i];
			}
			return prictures;
		}
		return new String[]{};
	}
	
	//缩略小图
	@Override
	public void setHomepageUrl(String homepageUrl) {
		if(homepageUrl!=null && !"".equals( homepageUrl)){
			homepageUrl ="http://120.55.43.176/"+homepageUrl;
		}else{
			homepageUrl ="";
		}
		super.setHomepageUrl(homepageUrl);
	}
	
	  
	  
	public TzItemVo() {
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	
	
	
    
	
}	

package com.tz.pojo.vo;

import java.util.Date;

import com.tz.pojo.TzContent;

public class TzContentVo extends TzContent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2881059021313355765L;
	
	//查询开始时间
	private Date startTime;
	//更新结束时间
	private Date endTime;
	
	//分类名称
	private String categoryName;
	

	public String[] getFirstPrics(){
		//获取从数据库查询出来的图片名称字符串
		String string = this.getPicFirstUrl();
		if(string !=null && !"".equals(string)){
			String[] firstPrics = string.split(",");
			for(int i=0;i<firstPrics.length;i++){
				firstPrics[i] = "http://120.55.43.176/"+firstPrics[i];
			}
			return firstPrics;
		}
		return new String[]{};
	}
	
	
	public String[] getSecondPrics(){
		//获取从数据库查询出来的图片名称字符串
				String string = this.getPicSecondUrl();
				if(string !=null && !"".equals(string)){
					String[] secondPrics = string.split(",");
					for(int i=0;i<secondPrics.length;i++){
						secondPrics[i] = "http://120.55.43.176/"+secondPrics[i];
					}
					return secondPrics;
				}
				return new String[]{};
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

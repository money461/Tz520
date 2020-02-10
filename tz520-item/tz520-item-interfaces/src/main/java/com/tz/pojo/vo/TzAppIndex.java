package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 封装App首页全部内容
 * @author Administrator
 *
 */
public class TzAppIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6814617877662759202L;

	//封装App首页广告以及轮播图
	List<TzHomePageContent> homePageContent;
	
	//封装商品分类
	List<TzItemCategoryVo> itemCategoryList;
	
	//查询广告分类
	List<TzContentCategoryVo> contentCategoryList;

	
	

	public List<TzHomePageContent> getHomePageContent() {
		return homePageContent;
	}

	public void setHomePageContent(List<TzHomePageContent> homePageContent) {
		this.homePageContent = homePageContent;
	}

	public List<TzItemCategoryVo> getItemCategoryList() {
		return itemCategoryList;
	}

	public void setItemCategoryList(List<TzItemCategoryVo> itemCategoryList) {
		this.itemCategoryList = itemCategoryList;
	}
	
	
	public List<TzContentCategoryVo> getContentCategoryList() {
		return contentCategoryList;
	}

	public void setContentCategoryList(List<TzContentCategoryVo> contentCategoryList) {
		this.contentCategoryList = contentCategoryList;
	}

	
	
	
	
}

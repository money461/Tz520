package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.List;


/**
 * 商城首页所有的信息
 * @author Administrator
 *
 */
public class TzStoreIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 910311814048681811L;

	//商城首页轮播图信息
	List<TzContentVo> tzContentList;
	
	//商品分类信息
	List<TzItemCategoryVo> itemCategoryList;
	
	//商品信息
	 List<TzMallItem> IndexItemList;

	 
	public List<TzContentVo> getTzContentList() {
		return tzContentList;
	}

	public void setTzContentList(List<TzContentVo> tzContentList) {
		this.tzContentList = tzContentList;
	}

	public List<TzItemCategoryVo> getItemCategoryList() {
		return itemCategoryList;
	}

	public void setItemCategoryList(List<TzItemCategoryVo> itemCategoryList) {
		this.itemCategoryList = itemCategoryList;
	}

	public List<TzMallItem> getIndexItemList() {
		return IndexItemList;
	}

	public void setIndexItemList(List<TzMallItem> indexItemList) {
		IndexItemList = indexItemList;
	}
	 
	 
	 
	
	
	
}

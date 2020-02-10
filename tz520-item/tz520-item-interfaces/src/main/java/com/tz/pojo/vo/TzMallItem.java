package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 封装商城首页商品信息
 * @author Administrator
 *
 */
public class TzMallItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840620895445869224L;

	private String id;  //商品分类id
	
	private String name; //商品分类名称
	
	private Integer sort; //商品分类code
	
	//封装商城首页商品信息及分类信息
	List<TzItemVo> IndexItemList;
	
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TzItemVo> getIndexItemList() {
		return IndexItemList;
	}

	public void setIndexItemList(List<TzItemVo> indexItemList) {
		IndexItemList = indexItemList;
	} 
	
	
	
}

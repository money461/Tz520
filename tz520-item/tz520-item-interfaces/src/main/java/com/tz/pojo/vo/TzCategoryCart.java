package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.List;

public class TzCategoryCart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3303299705843481123L;

	private Integer id;  //分类id
	
    private String name;  //分类名称
	 
	
	//用户购物车集合 一对多
	private List<TzCartVo> cartList; 
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TzCartVo> getCartList() {
		return cartList;
	}

	public void setCartList(List<TzCartVo> cartList) {
		this.cartList = cartList;
	}
	
	
}

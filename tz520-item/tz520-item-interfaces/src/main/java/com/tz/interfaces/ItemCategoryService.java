package com.tz.interfaces;

import java.util.List;

import com.tz.pojo.vo.TzItemCategoryVo;

public interface ItemCategoryService {

	
	//查询商城首页或者App首页4个商品分类信息
	List<TzItemCategoryVo> queryItemCategoryList();
	
}

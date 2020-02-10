package com.tz.mapper.vo;

import java.util.List;

import com.tz.pojo.vo.TzItemCategoryVo;

public interface TzItemCategoryMapperVo {

	//按照顺序查询所有商品分类信息4个分类信息
	List<TzItemCategoryVo> queryItemCategoryList();
	



}
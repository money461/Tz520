package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.TzItemCategoryVo;

public interface TzItemCategoryMapperVo {

	//条件查询商品分类列表信息
	List<TzItemCategoryVo> selectItemCategoryList(Map<String, Object> map);


}
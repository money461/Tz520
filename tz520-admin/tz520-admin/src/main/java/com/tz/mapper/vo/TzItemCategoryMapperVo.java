package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.TzItemCategory;
import com.tz.pojo.vo.TzItemCategoryVo;

public interface TzItemCategoryMapperVo {

	//条件查询商品分类列表信息
	List<TzItemCategoryVo> selectItemCategoryList(Map<String, Object> map);

	//异步加载说有的商品分类信息
	List<TzItemCategory> queryAllItemCategory();

	//回显该商品分类信息
	TzItemCategory selectItemCategoryById(@Param("id") Integer id);

	//校验sort是否存在
	int checkItemCategorySort(Integer sort);


}
package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.TzContentCategory;
import com.tz.pojo.vo.TzContentCategoryVo;

public interface TzContentCategoryMapperVo {
	//异步加载所有的内容分类信息
	List<TzContentCategory> queryContentCatagory();

	//内容分类
	List<TzContentCategoryVo> getContentCategoryList(Map<String, Object> map);

	//回显内容分类信息
	TzContentCategoryVo selectContentCategoryById(@Param("id") Integer id);

	//校验sort是否存在
	int checkContentCategorySort(Integer sort);

	

}
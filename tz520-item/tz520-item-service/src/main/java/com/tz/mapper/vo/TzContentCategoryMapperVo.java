package com.tz.mapper.vo;

import java.util.List;

import com.tz.pojo.vo.TzContentCategoryVo;

public interface TzContentCategoryMapperVo {

	//查询App首页的广告分类标识信息
	List<TzContentCategoryVo> queryMallContentCategory();

   

}
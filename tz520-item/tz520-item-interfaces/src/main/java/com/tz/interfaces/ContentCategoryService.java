package com.tz.interfaces;

import java.util.List;

import com.tz.pojo.vo.TzContentCategoryVo;

public interface ContentCategoryService {

	//查询App首页分类的4个分类信息
	public List<TzContentCategoryVo> queryMallContentCategory();
	
}

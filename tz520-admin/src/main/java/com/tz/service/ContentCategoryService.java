package com.tz.service;

import com.tz.pojo.TzContentCategory;
import com.tz.pojo.vo.TzContentCategoryVo;
import com.tz.res.MsgResult;

public interface ContentCategoryService {

	//异步加载所有的内容分类信息
	MsgResult queryContentCategory();

	MsgResult getContentCategoryList(TzContentCategoryVo tzcontentCategoryVo, Integer curPage, Integer rows);

	MsgResult addOrUpdateContentCategory(TzContentCategory tzContentCategory, String type);

	MsgResult selectContentCategoryById(Integer id);

	MsgResult deleteContentCategoryById(Integer id);

	MsgResult checkContentCategorySort(Integer sort);

}

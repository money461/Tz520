package com.tz.service;

import com.tz.pojo.TzItemCategory;
import com.tz.pojo.vo.TzItemCategoryVo;
import com.tz.res.MsgResult;

public interface ItemCategoryService {

	//异步加载查询所有的商品分类信息
	MsgResult findAllItemCatagory();

	//条件查询商品分类信息
	MsgResult getItemCategoryList(TzItemCategoryVo tzItemCategoryVo, Integer curPage, Integer rows);

	//商品的分类添加或者更新操作
	MsgResult addOrUpdate(TzItemCategory tzItemCategory, String type);
	
   //根据商品分类id回显商品分类信息
	MsgResult selectItemCategoryById(Integer id);

	//根据商品分类id删除信息
	MsgResult deleteItemCategoryById(Integer id);

	//校验商品分类顺序是否存在
	MsgResult checkItemCategorySort(Integer sort);

}

package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.vo.TzItemVo;

public interface TzItemMapperVo {

	//批量删除商品	
	void deleteItem(@Param("ids") String[] ids);

	//批量下架
	void instockItem( @Param("ids")String[] ids);

	//批量上架
	void reshelfItem(@Param("ids") String[] ids);

	//条件查询商品信息
	List<TzItemVo> selectTzItemList(Map<String, Object> map);

	//查询商品详情信息
	TzItemVo selectTzItemById(String id);


   
}
package com.tz.mapper.vo;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.vo.TzMallItem;
import com.tz.pojo.vo.TzItemVo;

public interface TzItemMapperVo {

	//根据商品名称模糊搜索商品信息
	List<TzItemVo> queryItemByItemTitle(@Param("itemTitle") String itemTitle);

	//随机获取商品信息
	List<TzItemVo> recommendItem(@Param("i") Integer i);

	//获取未分页且已上架的商品数据信息
	List<TzMallItem> selectIndexItemList();

	//根据商品分类id查询商品
	List<TzItemVo> selectItemByCategory(@Param("id") String id);

	//根据商品id查询商品详情
	TzItemVo queryItemDetailById(@Param("id") String id);

	//根据分类id随机推荐2个商品信息
	List<TzItemVo> recommendItemByCategory(@Param("categoryIds") String[] categoryIds);


}

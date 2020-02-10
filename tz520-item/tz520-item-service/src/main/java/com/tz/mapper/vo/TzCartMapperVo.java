package com.tz.mapper.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.TzCart;
import com.tz.pojo.vo.TzCartVo;
import com.tz.pojo.vo.TzCategoryCart;

public interface TzCartMapperVo {

	//根据用户id查询该用户的购物车信息
	List<TzCategoryCart> selectCartListByUserId(@Param("userId") String userId);

	//根据用户id及商品id查询该用户购物车中的某一商品信息
	TzCart selectCartByItemId(@Param("userId") String userId, @Param("itemId") String itemId);

	//根据用户id及商品id删除购物车信息
	void deleteCartByItemId(@Param("userId") String userId, @Param("ids") String[] ids);

	//获取用户等级
	Integer queryUserType(@Param("userId") String userId);

}

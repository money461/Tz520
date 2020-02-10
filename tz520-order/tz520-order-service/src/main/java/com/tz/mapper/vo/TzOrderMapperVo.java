package com.tz.mapper.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.TzItem;
import com.tz.pojo.TzOrderItem;
import com.tz.pojo.TzReceiverinfo;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.vo.TzOrderList;
import com.tz.pojo.vo.TzOrderVo;

public interface TzOrderMapperVo {

	//根据id查询订单信息详情
	TzOrderVo queryOrderById(@Param("orderId") String orderId);

	//根据用户id和订单状态查询订单列表信息
	List<TzOrderList> queryOrderByUserId(Map<String, Object> map);

	//查询默认的收货人地址信息
	TzReceiverinfo selectDefaultReceiverInfo(@Param("userId") String userId);
	
	//获取商品信息
	TzItem selectItemById(@Param("itemId")  String itemId);

	//批量插入商品数据至订单商品表
	void batchInsertItemData(@Param("orderItems") List<TzOrderItem> orderItems);

	//批量删除购物车该订单商品数据
	void batchDeleteCartData(@Param("userId") String userId, @Param("itemIds") ArrayList<String> itemIds);

	//批量修改商品的销售额数量
	void batchAlterSalesNum(@Param("itemList") List<TzItem> itemList);

	//添加该用户剩余爱心值
	void addLoveSurplus(@Param("userLove") TzUserLove userLove);

	//求出套餐商品的分类id
	int queryItemCategoryId();

	//查询用户等级
	 Integer queryUserType(@Param("userId") String userId);

	 //将消费记录写入爱心值消费记录表
	void insertLoveValueConsumption(@Param("tzUserLoveConsumptionDetails") TzUserLoveConsumptionDetails tzUserLoveConsumptionDetails);
   

	
}

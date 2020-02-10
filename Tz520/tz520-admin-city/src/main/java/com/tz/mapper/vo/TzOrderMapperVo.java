package com.tz.mapper.vo;

import com.tz.pojo.TzOrder;
import com.tz.pojo.vo.TzOrderVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TzOrderMapperVo {

	//根据条件查询订单信息
	List<TzOrderVo> selectOrderList(Map<String, Object> map);

	//根据订单商品id查询详情
	TzOrderVo findOrderDetail(String id);

	//批量发货处理
	void batchShipping(@Param("orderList") List<TzOrder> orderList);
   
}
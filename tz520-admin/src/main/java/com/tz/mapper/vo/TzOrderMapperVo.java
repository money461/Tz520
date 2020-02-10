package com.tz.mapper.vo;

import com.tz.pojo.TzOrder;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.vo.TzOrderVo;

import java.util.Date;
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

	//取消订单或者退款退还该用户的剩余爱心值
	void addLoveSurplus(TzUserLove userLove);

	//定时改变8天前已发货订单状态
	void orderQuartzWork(@Param("format") String format);

	//导出订单数据至excel
	List<TzOrderVo> exportExcelOrderList(Map<String, Object> map);
   
}
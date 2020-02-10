package com.tz.service;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.RecommendOrderVo;
import com.tz.pojo.vo.RecommendVo;
import com.tz.res.MsgResult;

public interface RecommendService {
	
	//查询所有的城市信息
	MsgResult  getRecommendList(RecommendVo  recommendVo,Integer rows,Integer curPage) throws Exception;
	//查询推荐用户订单消费记录
	MsgResult  orderConsumerList(RecommendOrderVo  recommendOrderVo,Integer rows,Integer curPage) throws Exception;
	//查询推荐用户的订单消费记录的商品列表信息
	MsgResult orderConsumerDetailList(String orderId,Integer rows,Integer curPage);
	 
}

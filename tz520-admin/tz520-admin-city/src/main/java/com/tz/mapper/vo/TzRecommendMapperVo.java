package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.RecommendOrderVo;
import com.tz.pojo.vo.RecommendVo;
import com.tz.pojo.vo.UserVo;


public interface TzRecommendMapperVo {

	//查看用户的推荐用户 一级和二级推荐
    List<RecommendVo> selectRecommendList(Map map);
    //查询会员推荐的前两位的个数
    int countTopTwo(Map map);
	//查询推荐用户的订单消费记录
    List<RecommendOrderVo> selectRecommendOrderConsumerList(Map map);
    //查询推荐用户的订单消费记录单品总价
    RecommendOrderVo countRecommendOrderConsumerList(Map map);
    
    
}
package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.CityVo;
import com.tz.pojo.vo.RecommendVo;
import com.tz.pojo.vo.UserVo;


public interface TzRecommendMapperVo {

	//查看用户的推荐用户 一级和二级推荐
    List<RecommendVo> selectRecommendList(Map map);
    //查询会员推荐的前两位的个数
    int countTopTwo(Map map);
    
}
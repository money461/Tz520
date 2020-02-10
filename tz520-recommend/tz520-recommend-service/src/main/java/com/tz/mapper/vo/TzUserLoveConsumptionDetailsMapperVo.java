package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;

public interface TzUserLoveConsumptionDetailsMapperVo {

	//查询用户爱心值消费记录
	List<UserLoveConsumptionDetailsVo> consumptionList(Map map);
	
}
package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveVo;

public interface TzUserLoveConsumptionDetailsMapperVo {
	
	//查看用户的成长值
    List<UserLoveConsumptionDetailsVo> selectLoveDetailAndUserList(Map map);
    
}
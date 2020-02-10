package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveShowVo;
import com.tz.pojo.vo.UserLoveVo;

public interface TzUserLoveShowMapperVo {
	
	//查看所有用户成长值提现
    List<UserLoveShowVo> selectLoveShowAndUserList(Map map);
    
}
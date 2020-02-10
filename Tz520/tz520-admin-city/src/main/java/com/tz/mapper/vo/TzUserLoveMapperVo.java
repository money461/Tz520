package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.UserLoveVo;

public interface TzUserLoveMapperVo {
	
	//查看用户的成长值
    List<UserLoveVo> selectLoveAndUserList(Map map);
    
}
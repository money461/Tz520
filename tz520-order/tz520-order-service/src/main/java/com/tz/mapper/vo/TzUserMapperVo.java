package com.tz.mapper.vo;

import java.util.Map;

import com.tz.pojo.vo.UserVo2;

public interface TzUserMapperVo {

	//查询用户信息和用户的类型
	UserVo2 selectUserAndMailInner(Map map);
	
	
}
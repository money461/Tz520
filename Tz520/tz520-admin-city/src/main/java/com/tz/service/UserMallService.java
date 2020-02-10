package com.tz.service;

import com.tz.pojo.TzUserMall;
import com.tz.res.MsgResult;

public interface UserMallService {
	
	//查询所有的用户信息
	MsgResult  addOrUpdate(TzUserMall user,String type) throws Exception;
	
		 
}

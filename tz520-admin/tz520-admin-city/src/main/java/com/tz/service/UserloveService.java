package com.tz.service;

import com.tz.pojo.TzUserLove;
import com.tz.pojo.vo.UserLoveVo;
import com.tz.res.MsgResult;

public interface UserloveService {
	
	//查询所有用户的成长值
	MsgResult  getUserLoveList(UserLoveVo userLoveVo,Integer rows,Integer curPage) throws Exception;
	//查询id删除
	MsgResult  deleteById(String id) throws Exception;
	//查询id修改用户成长值状态
	MsgResult  updateStausById(TzUserLove userLove) throws Exception;
		 
}
   
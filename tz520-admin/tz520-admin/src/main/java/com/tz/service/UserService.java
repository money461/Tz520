package com.tz.service;

import com.tz.pojo.TzUser;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;

public interface UserService {
	
	//查询所有的用户信息
	MsgResult  getUserList(UserVo userVo,Integer rows,Integer curPage) throws Exception;
	//添加或者修改用户信息
	MsgResult  addOrUpdate(TzUser user,String type,Integer userType,String oldUserName,String oldPhone,String userMallId) throws Exception;
	//根据用户的id删除用户信息（删除只是做假删除--防止误操作无法找回）
	MsgResult  deleteById(String id) throws Exception;
	//根据用户的id和类型操作用户状态
	MsgResult  updateStatusById(String id,Integer status) throws Exception;
	//根据用户的id查询用户信息
	MsgResult  selectById(String id) throws Exception;
	
		 
}

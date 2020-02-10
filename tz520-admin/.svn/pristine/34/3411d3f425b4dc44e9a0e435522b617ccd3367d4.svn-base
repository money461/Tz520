package com.tz.service;

import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzManagerVo;
import com.tz.res.MsgResult;

public interface ManagerService {

	//分页查询管理员列表信息
	MsgResult getManagerList(TzManagerVo tzManagerVo, Integer curPage, Integer rows);
    
	//添加管理员
	MsgResult addmanager(TzManager tzManager, String roleIds);

	//根据id查询管理员用户信息
	MsgResult findManagerById(String id);

	//编辑后更新管理员信息
	MsgResult updateManager(TzManager tzManager, String roleIds);

	//根据id删除管理员信息
	MsgResult delManagerById(String id);

	//管理员账户登录查询该账户对应的密码信息
	MsgResult findByManagerName(String managerName, String password);

	
	
	
}

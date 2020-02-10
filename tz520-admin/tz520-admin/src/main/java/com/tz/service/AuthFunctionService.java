package com.tz.service;

import java.util.List;

import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzAuthFunctionVo;
import com.tz.res.MsgResult;

public interface AuthFunctionService {
	
	//展示所有的权限信息
	MsgResult getFunctionList(TzAuthFunctionVo authFunctionVo, Integer rows, Integer curPage);

	//添加权限信息
	MsgResult addAuthFunction(TzAuthFunction tzAuthFunction);

	//异步加载查询所有的权限信息
	MsgResult findAllFunction();

	//根据权限id删除权限信息
	MsgResult deleteFunctionById(String id);

	//根据权限id查询对应权限信息,回显至更新页面
	MsgResult findAuthFunctionById(String id);

	//更新权限信息
	MsgResult updateAuthFunction(TzAuthFunction tzAuthFunction);

	//shiro框架权限授权查询该账户对应的code
	List<String> queryAuthFunctionByManager(TzManager manager);


		 
}

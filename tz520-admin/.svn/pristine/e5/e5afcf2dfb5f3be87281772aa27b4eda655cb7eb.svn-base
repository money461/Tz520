package com.tz.mapper.vo;

import com.tz.pojo.TzAuthFunction;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TzAuthFunctionMapperVo {
    
	//查询权限列表信息
	List<TzAuthFunction> selectAuthFunctionList(Map<String, Object> map);

	//查询权限
	List<TzAuthFunction> queryAuthFunctionBypId(@Param("id") String id);

	//删除权限下子权限
	void deleteAuthFunctionBypId(String id);

	//批量删除角色权限中间表数据
	void deleteRoleFunctionById(@Param("list") List<String> list);

	//查询所有的权限信息
	List<TzAuthFunction> queryAllFunction();

	//根据角色id查询所有的权限信息code
	List<String> queryAuthFunctionCode(@Param("id") String id);

}
package com.tz.mapper.vo;

import com.tz.pojo.TzRole;
import com.tz.pojo.vo.TzRoleVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TzRoleMapperVo {
	//异步加载所有的权限，生成角色树形
	List<TzRole> queryAllRole();

	//查询角色列表信息
	List<TzRoleVo> queryRoleList (Map<String, Object> map);

	//shiro授予角色 查询该角色code
	List<String> queryRoleCodeByManager(@Param("id") String id);

}
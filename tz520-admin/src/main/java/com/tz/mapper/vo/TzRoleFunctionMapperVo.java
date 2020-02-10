package com.tz.mapper.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.TzRoleFunction;

public interface TzRoleFunctionMapperVo {

	//批量插入角色权限数据信息
	void batchInsertData(@Param("roleFunctionList") List<TzRoleFunction> roleFunctionList);

}

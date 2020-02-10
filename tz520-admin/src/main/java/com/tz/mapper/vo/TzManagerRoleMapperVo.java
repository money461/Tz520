package com.tz.mapper.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.TzManagerRole;

public interface TzManagerRoleMapperVo {

    //批量插入用户角色中间表数据
	void batchInsertData(@Param("managerRoleList") List<TzManagerRole> managerRoleList);

}

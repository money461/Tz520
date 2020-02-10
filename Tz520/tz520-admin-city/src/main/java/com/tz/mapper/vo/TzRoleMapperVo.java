package com.tz.mapper.vo;

import com.tz.pojo.TzRole;
import com.tz.pojo.TzRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzRoleMapperVo {
    //根据账户id查询对应的角色信息
	List<TzRole> findRoleByManagerId(String id);
}
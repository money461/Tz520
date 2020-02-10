package com.tz.mapper;

import com.tz.pojo.TzRoleFunction;
import com.tz.pojo.TzRoleFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzRoleFunctionMapper {
    int countByExample(TzRoleFunctionExample example);

    int deleteByExample(TzRoleFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzRoleFunction record);

    int insertSelective(TzRoleFunction record);

    List<TzRoleFunction> selectByExample(TzRoleFunctionExample example);

    TzRoleFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzRoleFunction record, @Param("example") TzRoleFunctionExample example);

    int updateByExample(@Param("record") TzRoleFunction record, @Param("example") TzRoleFunctionExample example);

    int updateByPrimaryKeySelective(TzRoleFunction record);

    int updateByPrimaryKey(TzRoleFunction record);
}
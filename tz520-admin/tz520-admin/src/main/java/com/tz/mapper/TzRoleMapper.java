package com.tz.mapper;

import com.tz.pojo.TzRole;
import com.tz.pojo.TzRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzRoleMapper {
    int countByExample(TzRoleExample example);

    int deleteByExample(TzRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzRole record);

    int insertSelective(TzRole record);

    List<TzRole> selectByExample(TzRoleExample example);

    TzRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzRole record, @Param("example") TzRoleExample example);

    int updateByExample(@Param("record") TzRole record, @Param("example") TzRoleExample example);

    int updateByPrimaryKeySelective(TzRole record);

    int updateByPrimaryKey(TzRole record);
}
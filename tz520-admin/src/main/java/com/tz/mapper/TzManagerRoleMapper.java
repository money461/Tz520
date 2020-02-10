package com.tz.mapper;

import com.tz.pojo.TzManagerRole;
import com.tz.pojo.TzManagerRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzManagerRoleMapper {
    int countByExample(TzManagerRoleExample example);

    int deleteByExample(TzManagerRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzManagerRole record);

    int insertSelective(TzManagerRole record);

    List<TzManagerRole> selectByExample(TzManagerRoleExample example);

    TzManagerRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzManagerRole record, @Param("example") TzManagerRoleExample example);

    int updateByExample(@Param("record") TzManagerRole record, @Param("example") TzManagerRoleExample example);

    int updateByPrimaryKeySelective(TzManagerRole record);

    int updateByPrimaryKey(TzManagerRole record);
}
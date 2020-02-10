package com.tz.mapper;

import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzUserMapper {
    int countByExample(TzUserExample example);

    int deleteByExample(TzUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzUser record);

    int insertSelective(TzUser record);

    List<TzUser> selectByExample(TzUserExample example);

    TzUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzUser record, @Param("example") TzUserExample example);

    int updateByExample(@Param("record") TzUser record, @Param("example") TzUserExample example);

    int updateByPrimaryKeySelective(TzUser record);

    int updateByPrimaryKey(TzUser record);
}
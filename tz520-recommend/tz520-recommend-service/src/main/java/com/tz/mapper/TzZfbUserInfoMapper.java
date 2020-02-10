package com.tz.mapper;

import com.tz.pojo.TzZfbUserInfo;
import com.tz.pojo.TzZfbUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzZfbUserInfoMapper {
    int countByExample(TzZfbUserInfoExample example);

    int deleteByExample(TzZfbUserInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzZfbUserInfo record);

    int insertSelective(TzZfbUserInfo record);

    List<TzZfbUserInfo> selectByExample(TzZfbUserInfoExample example);

    TzZfbUserInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzZfbUserInfo record, @Param("example") TzZfbUserInfoExample example);

    int updateByExample(@Param("record") TzZfbUserInfo record, @Param("example") TzZfbUserInfoExample example);

    int updateByPrimaryKeySelective(TzZfbUserInfo record);

    int updateByPrimaryKey(TzZfbUserInfo record);
}
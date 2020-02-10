package com.tz.mapper;

import com.tz.pojo.TzUserPayLog;
import com.tz.pojo.TzUserPayLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzUserPayLogMapper {
    int countByExample(TzUserPayLogExample example);

    int deleteByExample(TzUserPayLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzUserPayLog record);

    int insertSelective(TzUserPayLog record);

    List<TzUserPayLog> selectByExample(TzUserPayLogExample example);

    TzUserPayLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzUserPayLog record, @Param("example") TzUserPayLogExample example);

    int updateByExample(@Param("record") TzUserPayLog record, @Param("example") TzUserPayLogExample example);

    int updateByPrimaryKeySelective(TzUserPayLog record);

    int updateByPrimaryKey(TzUserPayLog record);
}
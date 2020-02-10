package com.tz.mapper;

import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzUserMallMapper {
    int countByExample(TzUserMallExample example);

    int deleteByExample(TzUserMallExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzUserMall record);

    int insertSelective(TzUserMall record);

    List<TzUserMall> selectByExample(TzUserMallExample example);

    TzUserMall selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzUserMall record, @Param("example") TzUserMallExample example);

    int updateByExample(@Param("record") TzUserMall record, @Param("example") TzUserMallExample example);

    int updateByPrimaryKeySelective(TzUserMall record);

    int updateByPrimaryKey(TzUserMall record);
}
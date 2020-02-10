package com.tz.mapper;

import com.tz.pojo.TzUserCity;
import com.tz.pojo.TzUserCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzUserCityMapper {
    int countByExample(TzUserCityExample example);

    int deleteByExample(TzUserCityExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzUserCity record);

    int insertSelective(TzUserCity record);

    List<TzUserCity> selectByExample(TzUserCityExample example);

    TzUserCity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzUserCity record, @Param("example") TzUserCityExample example);

    int updateByExample(@Param("record") TzUserCity record, @Param("example") TzUserCityExample example);

    int updateByPrimaryKeySelective(TzUserCity record);

    int updateByPrimaryKey(TzUserCity record);
}
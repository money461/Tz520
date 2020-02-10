package com.tz.mapper;

import com.tz.pojo.TzCity;
import com.tz.pojo.TzCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzCityMapper {
    int countByExample(TzCityExample example);

    int deleteByExample(TzCityExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzCity record);

    int insertSelective(TzCity record);

    List<TzCity> selectByExample(TzCityExample example);

    TzCity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzCity record, @Param("example") TzCityExample example);

    int updateByExample(@Param("record") TzCity record, @Param("example") TzCityExample example);

    int updateByPrimaryKeySelective(TzCity record);

    int updateByPrimaryKey(TzCity record);
}
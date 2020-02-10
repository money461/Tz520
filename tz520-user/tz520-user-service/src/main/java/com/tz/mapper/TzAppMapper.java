package com.tz.mapper;

import com.tz.pojo.TzApp;
import com.tz.pojo.TzAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzAppMapper {
    int countByExample(TzAppExample example);

    int deleteByExample(TzAppExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TzApp record);

    int insertSelective(TzApp record);

    List<TzApp> selectByExample(TzAppExample example);

    TzApp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TzApp record, @Param("example") TzAppExample example);

    int updateByExample(@Param("record") TzApp record, @Param("example") TzAppExample example);

    int updateByPrimaryKeySelective(TzApp record);

    int updateByPrimaryKey(TzApp record);
}
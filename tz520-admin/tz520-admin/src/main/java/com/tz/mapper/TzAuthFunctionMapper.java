package com.tz.mapper;

import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.TzAuthFunctionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TzAuthFunctionMapper {
    int countByExample(TzAuthFunctionExample example);

    int deleteByExample(TzAuthFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzAuthFunction record);

    int insertSelective(TzAuthFunction record);

    List<TzAuthFunction> selectByExample(TzAuthFunctionExample example);

    TzAuthFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzAuthFunction record, @Param("example") TzAuthFunctionExample example);

    int updateByExample(@Param("record") TzAuthFunction record, @Param("example") TzAuthFunctionExample example);

    int updateByPrimaryKeySelective(TzAuthFunction record);

    int updateByPrimaryKey(TzAuthFunction record);

}
package com.tz.mapper;

import com.tz.pojo.TzManager;
import com.tz.pojo.TzManagerExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TzManagerMapper {
    int countByExample(TzManagerExample example);

    int deleteByExample(TzManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzManager record);

    int insertSelective(TzManager record);

    List<TzManager> selectByExample(TzManagerExample example);

    TzManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzManager record, @Param("example") TzManagerExample example);

    int updateByExample(@Param("record") TzManager record, @Param("example") TzManagerExample example);

    int updateByPrimaryKeySelective(TzManager record);

    int updateByPrimaryKey(TzManager record);

}
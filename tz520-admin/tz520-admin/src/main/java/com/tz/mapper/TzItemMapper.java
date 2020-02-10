package com.tz.mapper;

import com.tz.pojo.TzItem;
import com.tz.pojo.TzItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzItemMapper {
    int countByExample(TzItemExample example);

    int deleteByExample(TzItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzItem record);

    int insertSelective(TzItem record);

    List<TzItem> selectByExample(TzItemExample example);

    TzItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzItem record, @Param("example") TzItemExample example);

    int updateByExample(@Param("record") TzItem record, @Param("example") TzItemExample example);

    int updateByPrimaryKeySelective(TzItem record);

    int updateByPrimaryKey(TzItem record);
}
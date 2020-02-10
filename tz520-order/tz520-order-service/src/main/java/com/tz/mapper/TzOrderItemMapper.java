package com.tz.mapper;

import com.tz.pojo.TzOrderItem;
import com.tz.pojo.TzOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzOrderItemMapper {
    int countByExample(TzOrderItemExample example);

    int deleteByExample(TzOrderItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzOrderItem record);

    int insertSelective(TzOrderItem record);

    List<TzOrderItem> selectByExample(TzOrderItemExample example);

    TzOrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzOrderItem record, @Param("example") TzOrderItemExample example);

    int updateByExample(@Param("record") TzOrderItem record, @Param("example") TzOrderItemExample example);

    int updateByPrimaryKeySelective(TzOrderItem record);

    int updateByPrimaryKey(TzOrderItem record);
}
package com.tz.mapper;

import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzOrderMapper {
    int countByExample(TzOrderExample example);

    int deleteByExample(TzOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzOrder record);

    int insertSelective(TzOrder record);

    List<TzOrder> selectByExample(TzOrderExample example);

    TzOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzOrder record, @Param("example") TzOrderExample example);

    int updateByExample(@Param("record") TzOrder record, @Param("example") TzOrderExample example);

    int updateByPrimaryKeySelective(TzOrder record);

    int updateByPrimaryKey(TzOrder record);
}
package com.tz.mapper;

import com.tz.pojo.TzOrderShipping;
import com.tz.pojo.TzOrderShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzOrderShippingMapper {
    int countByExample(TzOrderShippingExample example);

    int deleteByExample(TzOrderShippingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(TzOrderShipping record);

    int insertSelective(TzOrderShipping record);

    List<TzOrderShipping> selectByExample(TzOrderShippingExample example);

    TzOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TzOrderShipping record, @Param("example") TzOrderShippingExample example);

    int updateByExample(@Param("record") TzOrderShipping record, @Param("example") TzOrderShippingExample example);

    int updateByPrimaryKeySelective(TzOrderShipping record);

    int updateByPrimaryKey(TzOrderShipping record);
}
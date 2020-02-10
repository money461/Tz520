package com.tz.mapper;

import com.tz.pojo.TzCityUserConsumer;
import com.tz.pojo.TzCityUserConsumerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzCityUserConsumerMapper {
    int countByExample(TzCityUserConsumerExample example);

    int deleteByExample(TzCityUserConsumerExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzCityUserConsumer record);

    int insertSelective(TzCityUserConsumer record);

    List<TzCityUserConsumer> selectByExample(TzCityUserConsumerExample example);

    TzCityUserConsumer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzCityUserConsumer record, @Param("example") TzCityUserConsumerExample example);

    int updateByExample(@Param("record") TzCityUserConsumer record, @Param("example") TzCityUserConsumerExample example);

    int updateByPrimaryKeySelective(TzCityUserConsumer record);

    int updateByPrimaryKey(TzCityUserConsumer record);
}
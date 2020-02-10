package com.tz.mapper;

import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzUserLoveConsumptionDetailsMapper {
    int countByExample(TzUserLoveConsumptionDetailsExample example);

    int deleteByExample(TzUserLoveConsumptionDetailsExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzUserLoveConsumptionDetails record);

    int insertSelective(TzUserLoveConsumptionDetails record);

    List<TzUserLoveConsumptionDetails> selectByExample(TzUserLoveConsumptionDetailsExample example);

    TzUserLoveConsumptionDetails selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzUserLoveConsumptionDetails record, @Param("example") TzUserLoveConsumptionDetailsExample example);

    int updateByExample(@Param("record") TzUserLoveConsumptionDetails record, @Param("example") TzUserLoveConsumptionDetailsExample example);

    int updateByPrimaryKeySelective(TzUserLoveConsumptionDetails record);

    int updateByPrimaryKey(TzUserLoveConsumptionDetails record);
}
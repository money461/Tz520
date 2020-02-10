package com.tz.mapper;

import com.tz.pojo.TzReceiverinfo;
import com.tz.pojo.TzReceiverinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzReceiverinfoMapper {
    int countByExample(TzReceiverinfoExample example);

    int deleteByExample(TzReceiverinfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzReceiverinfo record);

    int insertSelective(TzReceiverinfo record);

    List<TzReceiverinfo> selectByExample(TzReceiverinfoExample example);

    TzReceiverinfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzReceiverinfo record, @Param("example") TzReceiverinfoExample example);

    int updateByExample(@Param("record") TzReceiverinfo record, @Param("example") TzReceiverinfoExample example);

    int updateByPrimaryKeySelective(TzReceiverinfo record);

    int updateByPrimaryKey(TzReceiverinfo record);
}
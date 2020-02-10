package com.tz.mapper;

import com.tz.pojo.TzMall;
import com.tz.pojo.TzMallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzMallMapper {
    int countByExample(TzMallExample example);

    int deleteByExample(TzMallExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzMall record);

    int insertSelective(TzMall record);

    List<TzMall> selectByExample(TzMallExample example);

    TzMall selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzMall record, @Param("example") TzMallExample example);

    int updateByExample(@Param("record") TzMall record, @Param("example") TzMallExample example);

    int updateByPrimaryKeySelective(TzMall record);

    int updateByPrimaryKey(TzMall record);
}
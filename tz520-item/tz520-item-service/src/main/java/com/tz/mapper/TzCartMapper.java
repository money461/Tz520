package com.tz.mapper;

import com.tz.pojo.TzCart;
import com.tz.pojo.TzCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzCartMapper {
    int countByExample(TzCartExample example);

    int deleteByExample(TzCartExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzCart record);

    int insertSelective(TzCart record);

    List<TzCart> selectByExample(TzCartExample example);

    TzCart selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzCart record, @Param("example") TzCartExample example);

    int updateByExample(@Param("record") TzCart record, @Param("example") TzCartExample example);

    int updateByPrimaryKeySelective(TzCart record);

    int updateByPrimaryKey(TzCart record);
}
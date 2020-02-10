package com.tz.mapper;

import com.tz.pojo.TzUserLoveShow;
import com.tz.pojo.TzUserLoveShowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzUserLoveShowMapper {
    int countByExample(TzUserLoveShowExample example);

    int deleteByExample(TzUserLoveShowExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzUserLoveShow record);

    int insertSelective(TzUserLoveShow record);

    List<TzUserLoveShow> selectByExample(TzUserLoveShowExample example);

    TzUserLoveShow selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzUserLoveShow record, @Param("example") TzUserLoveShowExample example);

    int updateByExample(@Param("record") TzUserLoveShow record, @Param("example") TzUserLoveShowExample example);

    int updateByPrimaryKeySelective(TzUserLoveShow record);

    int updateByPrimaryKey(TzUserLoveShow record);
}
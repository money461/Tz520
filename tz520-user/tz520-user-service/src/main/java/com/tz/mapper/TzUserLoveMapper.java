package com.tz.mapper;

import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzUserLoveMapper {
    int countByExample(TzUserLoveExample example);

    int deleteByExample(TzUserLoveExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzUserLove record);

    int insertSelective(TzUserLove record);

    List<TzUserLove> selectByExample(TzUserLoveExample example);

    TzUserLove selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzUserLove record, @Param("example") TzUserLoveExample example);

    int updateByExample(@Param("record") TzUserLove record, @Param("example") TzUserLoveExample example);

    int updateByPrimaryKeySelective(TzUserLove record);

    int updateByPrimaryKey(TzUserLove record);
}
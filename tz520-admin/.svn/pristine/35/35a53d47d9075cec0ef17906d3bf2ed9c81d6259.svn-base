package com.tz.mapper;

import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzRecommendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzRecommendMapper {
    int countByExample(TzRecommendExample example);

    int deleteByExample(TzRecommendExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzRecommend record);

    int insertSelective(TzRecommend record);

    List<TzRecommend> selectByExample(TzRecommendExample example);

    TzRecommend selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzRecommend record, @Param("example") TzRecommendExample example);

    int updateByExample(@Param("record") TzRecommend record, @Param("example") TzRecommendExample example);

    int updateByPrimaryKeySelective(TzRecommend record);

    int updateByPrimaryKey(TzRecommend record);
}
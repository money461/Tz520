package com.tz.mapper;

import com.tz.pojo.TzContentCategory;
import com.tz.pojo.TzContentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzContentCategoryMapper {
    int countByExample(TzContentCategoryExample example);

    int deleteByExample(TzContentCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TzContentCategory record);

    int insertSelective(TzContentCategory record);

    List<TzContentCategory> selectByExample(TzContentCategoryExample example);

    TzContentCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TzContentCategory record, @Param("example") TzContentCategoryExample example);

    int updateByExample(@Param("record") TzContentCategory record, @Param("example") TzContentCategoryExample example);

    int updateByPrimaryKeySelective(TzContentCategory record);

    int updateByPrimaryKey(TzContentCategory record);
}
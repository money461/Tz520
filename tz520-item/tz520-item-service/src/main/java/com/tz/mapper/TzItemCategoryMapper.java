package com.tz.mapper;

import com.tz.pojo.TzItemCategory;
import com.tz.pojo.TzItemCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzItemCategoryMapper {
    int countByExample(TzItemCategoryExample example);

    int deleteByExample(TzItemCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzItemCategory record);

    int insertSelective(TzItemCategory record);

    List<TzItemCategory> selectByExample(TzItemCategoryExample example);

    TzItemCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzItemCategory record, @Param("example") TzItemCategoryExample example);

    int updateByExample(@Param("record") TzItemCategory record, @Param("example") TzItemCategoryExample example);

    int updateByPrimaryKeySelective(TzItemCategory record);

    int updateByPrimaryKey(TzItemCategory record);
}
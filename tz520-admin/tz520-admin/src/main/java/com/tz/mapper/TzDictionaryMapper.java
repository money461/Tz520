package com.tz.mapper;

import com.tz.pojo.TzDictionary;
import com.tz.pojo.TzDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzDictionaryMapper {
    int countByExample(TzDictionaryExample example);

    int deleteByExample(TzDictionaryExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzDictionary record);

    int insertSelective(TzDictionary record);

    List<TzDictionary> selectByExample(TzDictionaryExample example);

    TzDictionary selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzDictionary record, @Param("example") TzDictionaryExample example);

    int updateByExample(@Param("record") TzDictionary record, @Param("example") TzDictionaryExample example);

    int updateByPrimaryKeySelective(TzDictionary record);

    int updateByPrimaryKey(TzDictionary record);
}
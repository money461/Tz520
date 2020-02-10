package com.tz.mapper;

import com.tz.pojo.TzContent;
import com.tz.pojo.TzContentExample;
import com.tz.pojo.vo.TzHomePageContent;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzContentMapper {
    int countByExample(TzContentExample example);

    int deleteByExample(TzContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzContent record);

    int insertSelective(TzContent record);

    List<TzContent> selectByExampleWithBLOBs(TzContentExample example);

    List<TzContent> selectByExample(TzContentExample example);

    TzContent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzContent record, @Param("example") TzContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TzContent record, @Param("example") TzContentExample example);

    int updateByExample(@Param("record") TzContent record, @Param("example") TzContentExample example);

    int updateByPrimaryKeySelective(TzContent record);

    int updateByPrimaryKeyWithBLOBs(TzContent record);

    int updateByPrimaryKey(TzContent record);


}
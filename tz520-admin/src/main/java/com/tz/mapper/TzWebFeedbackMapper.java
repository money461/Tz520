package com.tz.mapper;

import com.tz.pojo.TzWebFeedback;
import com.tz.pojo.TzWebFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzWebFeedbackMapper {
    int countByExample(TzWebFeedbackExample example);

    int deleteByExample(TzWebFeedbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzWebFeedback record);

    int insertSelective(TzWebFeedback record);

    List<TzWebFeedback> selectByExample(TzWebFeedbackExample example);

    TzWebFeedback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzWebFeedback record, @Param("example") TzWebFeedbackExample example);

    int updateByExample(@Param("record") TzWebFeedback record, @Param("example") TzWebFeedbackExample example);

    int updateByPrimaryKeySelective(TzWebFeedback record);

    int updateByPrimaryKey(TzWebFeedback record);
}
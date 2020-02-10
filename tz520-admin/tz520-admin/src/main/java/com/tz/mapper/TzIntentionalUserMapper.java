package com.tz.mapper;

import com.tz.pojo.TzIntentionalUser;
import com.tz.pojo.TzIntentionalUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TzIntentionalUserMapper {
    int countByExample(TzIntentionalUserExample example);

    int deleteByExample(TzIntentionalUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TzIntentionalUser record);

    int insertSelective(TzIntentionalUser record);

    List<TzIntentionalUser> selectByExample(TzIntentionalUserExample example);

    TzIntentionalUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TzIntentionalUser record, @Param("example") TzIntentionalUserExample example);

    int updateByExample(@Param("record") TzIntentionalUser record, @Param("example") TzIntentionalUserExample example);

    int updateByPrimaryKeySelective(TzIntentionalUser record);

    int updateByPrimaryKey(TzIntentionalUser record);
}
package com.tz.mapper;

import com.tz.pojo.Tz520web;
import com.tz.pojo.Tz520webExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tz520webMapper {
    int countByExample(Tz520webExample example);

    int deleteByExample(Tz520webExample example);

    int deleteByPrimaryKey(String id);

    int insert(Tz520web record);

    int insertSelective(Tz520web record);

    List<Tz520web> selectByExample(Tz520webExample example);

    Tz520web selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Tz520web record, @Param("example") Tz520webExample example);

    int updateByExample(@Param("record") Tz520web record, @Param("example") Tz520webExample example);

    int updateByPrimaryKeySelective(Tz520web record);

    int updateByPrimaryKey(Tz520web record);
}
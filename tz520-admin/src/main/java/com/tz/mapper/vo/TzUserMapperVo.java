package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.UserVo;


public interface TzUserMapperVo {

    List<UserVo> selectUserAndMailList(Map map);

}
package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.CityVo;
import com.tz.pojo.vo.UserVo;


public interface TzCityMapperVo {

	//查看城市信息左联用户信息
    List<CityVo> selectCityAndUserList(Map map);
    //查看用户信息内联城市信息
    List<CityVo> selectCityAndUserNList(Map map);
    //查看城市未被使用的信息
    List<CityVo> selectCityNotUserList(Map map);
    
}
package com.tz.service;

import com.tz.pojo.TzUserCity;
import com.tz.pojo.vo.CityVo;
import com.tz.res.MsgResult;

public interface UserCityService {
	
	//查询所有的城市和用户信息
	MsgResult  getUserCityList(CityVo cityVo,Integer rows,Integer curPage) throws Exception;
	//查询所有的城市和用户信息
	MsgResult  add(TzUserCity userCity) throws Exception;
	//查询id删除
	MsgResult  deleteById(String id) throws Exception;
		 
}

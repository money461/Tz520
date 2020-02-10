package com.tz.service;

import com.tz.pojo.TzCity;
import com.tz.pojo.vo.CityVo;
import com.tz.res.MsgResult;

public interface CityService {
	
	//查询所有的城市信息
	MsgResult  getCityList(CityVo cityVo,Integer rows,Integer curPage) throws Exception;
	//添加或者修改城市信息
	MsgResult  addOrUpdate(TzCity city,String type,String oldCityName) throws Exception;
	//根据城市的id删除城市信息（删除只是做假删除--防止误操作无法找回）
	MsgResult  deleteById(String id) throws Exception;
	//根据城市的id和类型操作城市状态
	MsgResult  updateStatusById(String id,Integer status) throws Exception;
	//根据城市的id查询城市信息
	MsgResult  selectById(String id) throws Exception;
	//根据类型查询所有的城市信息
	MsgResult  getCityListByState(TzCity city,String type) throws Exception;
		 
}

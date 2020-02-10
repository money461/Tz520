package com.tz.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tz.pojo.TzApp;
import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzRole;
import com.tz.res.MsgResult;

public interface AppService {
	
    //查询所有版本信息数据
	MsgResult findAll(Integer rows,Integer curPage) throws Exception;
	//添加新的版本信息
	MsgResult add(TzApp app) throws Exception;
	//根据用户id删除版本
	MsgResult deleteById(int id) throws Exception;	 
}

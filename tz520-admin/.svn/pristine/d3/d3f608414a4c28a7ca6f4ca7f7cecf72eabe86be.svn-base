package com.tz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.res.MsgResult;

public interface UserLoveConsumptionDetailsService {
	
	//查询所有用户的成长值
	MsgResult  getUserLoveDetailList(UserLoveConsumptionDetailsVo consumptionDetailsVo,Integer rows,Integer curPage) throws Exception;
	//查询id删除
	MsgResult  deleteById(String id) throws Exception;
	
	//导出excel
	MsgResult importExcel(UserLoveConsumptionDetailsVo consumptionDetailsVo, String fileName, HttpServletRequest request, HttpServletResponse response);
		 
}
   
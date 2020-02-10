package com.tz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.pojo.TzUserLoveShow;
import com.tz.pojo.vo.UserLoveShowVo;
import com.tz.res.MsgResult;

public interface UserLoveShowService {
	
	//查询所有用户的成长值提现
	MsgResult  getUserLoveShowList(UserLoveShowVo loveShowVo,Integer rows,Integer curPage) throws Exception;
	//查询id删除
	MsgResult  deleteById(String id) throws Exception;
	//提现结算
	MsgResult  settlement(TzUserLoveShow userLoveShow) throws Exception;
	
	//导出用户爱心值提取信息
	MsgResult importLoveShowExcel(UserLoveShowVo loveShowVo, String fileName, HttpServletRequest request, HttpServletResponse response);
		 
}
   
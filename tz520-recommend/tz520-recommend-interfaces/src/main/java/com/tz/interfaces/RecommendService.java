package com.tz.interfaces;

import com.tz.pojo.TzUserLoveShow;
import com.tz.res.AppMsgResult;

public interface RecommendService {

	//根据用户id 和登录token获取推荐信息
	AppMsgResult getRecommendList(String userId,String userToken);
	//用户爱心值提现
	AppMsgResult withdrawals(TzUserLoveShow userLoveShow,String userToken,String passWord);
	//爱心值消费记录
	AppMsgResult consumptionList(String userId,String userToken);
	
}

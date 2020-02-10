package com.tz.service;

import com.tz.pojo.TzIntentionalUser;
import com.tz.pojo.vo.TzIntentionalUserVo;
import com.tz.res.MsgResult;

public interface IntentionalUserService {

	MsgResult getIntentionalUserList(TzIntentionalUserVo tzIntentionalUserVo, Integer rows, Integer curPage);

	//添加意向用户
	MsgResult addIntentionalUser(TzIntentionalUser tzIntentionalUser);

}

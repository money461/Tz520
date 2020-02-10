package com.tz.interfaces;

import java.util.List;

import com.tz.pojo.vo.TzHomePageContent;
import com.tz.pojo.vo.TzContentVo;
import com.tz.res.AppMsgResult;

public interface ContentService {

	//展示app首页新闻广告
	 List<TzHomePageContent> homePageContent();

	//展示商城首页轮播图
	List<TzContentVo> showMallContent();

	//展示商城首页所有信息
	AppMsgResult showAppIndex();

}

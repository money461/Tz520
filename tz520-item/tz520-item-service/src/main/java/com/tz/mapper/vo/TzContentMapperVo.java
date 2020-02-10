package com.tz.mapper.vo;

import java.util.List;

import com.tz.pojo.vo.TzHomePageContent;
import com.tz.pojo.vo.TzContentVo;

public interface TzContentMapperVo {

	//展示app首页广告
	List<TzHomePageContent> homePageContent();

	 //展示商城首页轮播图
	List<TzContentVo> showMallContent();
    

}
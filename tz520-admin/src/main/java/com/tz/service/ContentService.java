package com.tz.service;

import com.tz.pojo.TzContent;
import com.tz.pojo.vo.TzContentVo;
import com.tz.res.MsgResult;

public interface ContentService {

	//查看广告列表信息
	MsgResult getContentList(Integer curPage, Integer rows, TzContentVo tzContentVo);

	//查看广告详情
	MsgResult queryContentDetail(String id);

	//添加广告
	MsgResult addContent(TzContent tzContent);

	//更新广告
	MsgResult updateContent(TzContent tzContent);

	//批量删除
	MsgResult batchDeleteContent(String ids);

}

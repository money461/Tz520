package com.tz.service;

import com.tz.pojo.TzExpressCom;
import com.tz.pojo.vo.TzExpressComVo;
import com.tz.res.MsgResult;

public interface ExpressComService {

	MsgResult deleteExpressComById(Integer id);

	MsgResult selectExpressComById(Integer id);

	MsgResult addOrUpdateExpressCom(TzExpressCom tzExpressCom, String type);

	MsgResult queryExpressCom();

	MsgResult getExpressComList(TzExpressComVo tzExpressComVo, Integer curPage, Integer rows);

}

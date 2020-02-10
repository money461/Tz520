package com.tz.service;

import com.tz.pojo.TzDictionary;
import com.tz.pojo.vo.TzDictionaryVo;
import com.tz.res.MsgResult;

public interface DictionaryService {

	//根据条件查询字典信息
	MsgResult getDictionaryList(Integer curPage, Integer rows, TzDictionaryVo tzDictionaryVo);

	//编辑或者添加数据字典信息
	MsgResult addOrUpdate(TzDictionary tzDictionary, String way);

	//根据id查询数据字典信息
	MsgResult selectDictionaryById(String id);

	//根据字典id删除数据
	MsgResult deleteDictionaryById(String id);

}

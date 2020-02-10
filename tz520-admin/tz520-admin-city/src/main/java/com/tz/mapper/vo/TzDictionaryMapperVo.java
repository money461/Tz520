package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.TzDictionaryVo;

public interface TzDictionaryMapperVo {

	//根据条件查询数据字典内容
	List<TzDictionaryVo> selectDictionaryList(Map<String, Object> map);

}

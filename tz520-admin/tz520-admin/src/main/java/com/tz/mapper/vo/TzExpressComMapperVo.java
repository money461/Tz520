package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import com.tz.pojo.vo.TzExpressComVo;

public interface TzExpressComMapperVo {

	List<TzExpressComVo> getExpressComList(Map<String, Object> map);

}

package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.vo.TzContentVo;

public interface TzContentMapperVo {

	//分页查内容详情
	List<TzContentVo> selectTzContentList(Map<String, Object> map);

	//批量删除
	void batchDeleteContent(@Param("strId") String[] strId);

	//查询广告信息详情
	TzContentVo queryContentDetail(@Param("id") String id);

	
	
	
}
package com.tz.mapper.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.vo.TzManagerVo;

public interface TzManagerMapperVo {

	//更新最近登录时间
	void updateLastUpload(@Param("id") String id);

	//查询所有的管理员信息
	List<TzManagerVo> selectManagerList(Map<String, Object> map);

}

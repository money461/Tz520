package com.tz.mapper.vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tz.pojo.TzReceiverinfo;

public interface TzReceiverinfoMapperVo {

	//清空该用户所有的默认地址标识
	void deleteISDefault(@Param("userId") String userId);

	//根据用户id查询收货人地址信息（按照更新时间查询）
	List<TzReceiverinfo> selectReceiverInfoByUserId(@Param("userId") String userId);

}

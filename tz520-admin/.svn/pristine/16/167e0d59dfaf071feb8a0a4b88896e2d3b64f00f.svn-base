package com.tz.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.vo.TzUserLoveConsumptionDetailsMapperVo;
import com.tz.mapper.vo.TzUserLoveMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveVo;
import com.tz.res.MsgResult;
import com.tz.service.UserLoveConsumptionDetailsService;
import com.tz.service.UserloveService;

@Service
public class UserLoveConsumptionDetailsServiceImpl implements UserLoveConsumptionDetailsService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private TzUserLoveConsumptionDetailsMapper userLoveConsumptionDetailsMapper;	
	private TzUserLoveConsumptionDetailsExample example;
	private TzUserLoveConsumptionDetailsExample.Criteria criteria;
		
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
	@Autowired 
	TzUserLoveConsumptionDetailsMapperVo loveConsumptionDetailsMapperVo;	
    
	@Override
	public MsgResult getUserLoveDetailList(UserLoveConsumptionDetailsVo consumptionDetailsVo,Integer rows,Integer curPage) throws Exception {
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String phone = consumptionDetailsVo.getPhone();
    	//手机号码
    	if(StringUtil.isNotEmpty(phone)){
    		map.put("phone", "%"+phone+"%");
    	}else{
    		map.put("phone", null);
    	}
    	//时间段
    	if(null != consumptionDetailsVo.getStartTime() && null != consumptionDetailsVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(consumptionDetailsVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(consumptionDetailsVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
    	//登录用户的id
    	Subject subject = SecurityUtils.getSubject();
    	TzUser user = (TzUser)subject.getSession().getAttribute("user");
		String userId = user.getId();
		map.put("userId", userId);
		
        List<UserLoveConsumptionDetailsVo> lists = loveConsumptionDetailsMapperVo.selectLoveDetailAndUserList(map);
        //取记录总条数
        PageInfo<UserLoveConsumptionDetailsVo> pageInfo = new PageInfo<>(lists);
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}
	@Override
	public MsgResult deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			int res = userLoveConsumptionDetailsMapper.deleteByPrimaryKey(id);
			if(res == 1){
				msgResult = MsgResult.nodata(true, "操作成功！");
			}else{
				msgResult = MsgResult.nodata(false, "操作失败，请稍后重试！");
			}
		}else{
			msgResult = MsgResult.result(false, "操作失败，请稍后重试！", "参数id不能为空！");
		}
		return msgResult;
	}
	
}

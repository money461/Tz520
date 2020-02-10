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
import com.tz.id.IDUtils;
import com.tz.mapper.TzCityMapper;
import com.tz.mapper.TzUserCityMapper;
import com.tz.mapper.vo.TzCityMapperVo;
import com.tz.pojo.TzCity;
import com.tz.pojo.TzCityExample;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUserCity;
import com.tz.pojo.TzUserCityExample;
import com.tz.pojo.vo.CityVo;
import com.tz.pojo.vo.CommonVo;
import com.tz.res.MsgResult;
import com.tz.service.UserCityService;

@Service
public class UserCityServiceImpl implements UserCityService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	TzUserCityMapper userCityMapper;	
	private TzUserCityExample example;
	private TzUserCityExample.Criteria criteria;
		
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
    
	@Autowired 
	TzCityMapper cityMapper;	
	private TzCityExample tzCityExample;
	private TzCityExample.Criteria criteria2;
	
	@Autowired 
	TzCityMapperVo cityMapperVo;	
    
	@Override
	public MsgResult getUserCityList(CityVo cityVo,Integer rows,Integer curPage) throws Exception {
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String cityName = cityVo.getCityName();
    	//手机号码
    	if(StringUtil.isNotEmpty(cityName)){
    		map.put("cityName", "%"+cityName+"%");
    	}else{
    		map.put("cityName", null);
    	}
    	//时间段
    	if(null != cityVo.getStartTime() && null != cityVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(cityVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(cityVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
    	map.put("userId", cityVo.getUserId());
    	map.put("type", cityVo.getUserType());
        List<CityVo> lists = cityMapperVo.selectCityAndUserNList(map);
        //取记录总条数
        PageInfo<CityVo> pageInfo = new PageInfo<>(lists);
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}

	@Override
	public MsgResult add(TzUserCity userCity) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(null != userCity){
			userCity.setUpdatedTime(new Date());
			Subject subject = SecurityUtils.getSubject();
			TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
			String id = manager.getId();
			//操作人
			userCity.setOperater(id);
			//新增
			userCity.setCreatedTime(new Date());
			userCity.setId(IDUtils.genId());
			int res = userCityMapper.insertSelective(userCity);
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
	@Override
	public MsgResult deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			int res = userCityMapper.deleteByPrimaryKey(id);
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

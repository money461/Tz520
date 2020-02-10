package com.tz.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
import com.tz.mapper.TzOrderItemMapper;
import com.tz.mapper.TzRecommendMapper;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.vo.TzRecommendMapperVo;
import com.tz.pojo.TzCity;
import com.tz.pojo.TzCityExample;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzOrderItem;
import com.tz.pojo.TzOrderItemExample;
import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzRecommendExample;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserCity;
import com.tz.pojo.TzUserCityExample;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.RecommendOrderVo;
import com.tz.pojo.vo.RecommendVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.Constant;
import com.tz.res.MsgResult;
import com.tz.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService  {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	TzRecommendMapper recommendMapper;	
	private TzRecommendExample example;
	private TzRecommendExample.Criteria criteria;
	
	//消费明细
	@Autowired 
	TzUserLoveConsumptionDetailsMapper consumptionDetailsMapper;	
	private TzUserLoveConsumptionDetailsExample consumptionDetailsExample;
	private TzUserLoveConsumptionDetailsExample.Criteria criteria3;
	
	//爱心成长值
	@Autowired 
	TzUserLoveMapper userLoveMapper;	
	private TzUserLoveExample userLoveExample;
	private TzUserLoveExample.Criteria criteria2;
	//订单商品记录
	@Autowired 
	TzOrderItemMapper orderItemMapper;	
	private TzOrderItemExample orderItemExample;
	private TzOrderItemExample.Criteria criteria4;
	
	@Autowired 
	TzRecommendMapperVo recommendMapperVo;	
	
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
    
	@Override
	public MsgResult getRecommendList(RecommendVo recommendVo,Integer rows,Integer curPage) throws Exception {
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
 
    	//时间段
    	if(null != recommendVo.getStartTime() && null != recommendVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(recommendVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(recommendVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
    	//被推荐会员电话
    	String userPhone = recommendVo.getUserPhone();
    	if(StringUtil.isNotEmpty(userPhone)){
    		map.put("phone", "%"+userPhone+"%");
    	}else{
    		map.put("phone",null);
    	}
    	//被推荐会员昵称
    	String refereeName = recommendVo.getRefereeName();
    	if(StringUtil.isNotEmpty(refereeName)){
    		map.put("refereeName", "%"+refereeName+"%");
    	}else{
    		map.put("refereeName",null);
    	}
    	//登录用户的id
    	Subject subject = SecurityUtils.getSubject();
    	TzUser user = (TzUser)subject.getSession().getAttribute("user");
		String id = user.getId();
		map.put("refereeId",id);
    	//用户的电话
    	String refereePhone = recommendVo.getRefereePhone();
    	if(StringUtil.isNotEmpty(refereePhone)){
    		map.put("refereePhone", "%"+refereePhone+"%");
    	}else{
    		map.put("refereePhone",null);
    	}
    	//被荐用户的电话
    	String realPhone = recommendVo.getRealPhone();
    	if(StringUtil.isNotEmpty(realPhone)){
    		map.put("realPhone", realPhone);
    	}else{
    		map.put("realPhone",null);
    	}
    	//筛选条件1
    	String selectType = recommendVo.getSelectType();
    	if(StringUtil.isNotEmpty(selectType)){
    		//秒反
    		if("0".equals(selectType)){
    			map.put("topTwo",selectType);
    		}else{
    			map.put("grade",selectType);
    		}
    	}else{
    		map.put("topTwo",null);
    		map.put("grade",null);	
    	}
    	//筛选条件2
    	String selectType2 = recommendVo.getSelectType2();
    	if(StringUtil.isNotEmpty(selectType2)){
    		map.put("feedbackStatus",selectType2);
    	}else{
    		map.put("feedbackStatus",null);
    	}
    	//商城id
    	String mallId = common.getMallId();
    	map.put("mallId", mallId);
        List<RecommendVo> lists = recommendMapperVo.selectRecommendList(map);
        //取记录总条数
        PageInfo<RecommendVo> pageInfo = new PageInfo<>(lists);
        //爱心值
        userLoveExample = new TzUserLoveExample();
        criteria2 = userLoveExample.createCriteria();
        criteria2.andUserIdEqualTo(id);
        List<TzUserLove> love = userLoveMapper.selectByExample(userLoveExample);
        if(love.size()>0){
        	pageInfo.setNavigateFirstPage(love.get(0).getLoveSurplus());
        	pageInfo.setNavigateLastPage(love.get(0).getLoveTotal());
        }else{
        	pageInfo.setNavigateFirstPage(0);
        	pageInfo.setNavigateLastPage(0);
        } 
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}
	@Override
	public MsgResult orderConsumerList(RecommendOrderVo recommendOrderVo, Integer rows, Integer curPage)
			throws Exception {
			MsgResult msgResult = null;
			rows = rows == null?10:rows;
			curPage = curPage == null?1:curPage;
			//分页处理
	        PageHelper.startPage(curPage, rows);
	        //封装map集合
	        Map<String,Object> map = new HashMap<String, Object>();
	    	//时间段
	    	if(null != recommendOrderVo.getStartTime() && null != recommendOrderVo.getEndTime()){
	    		map.put("startTime",DateUtile.pushDays(recommendOrderVo.getStartTime(),0) );
	    		map.put("endTime", DateUtile.pushDays(recommendOrderVo.getEndTime(),0));
	    	}else{
	    		map.put("startTime",null);
	    		map.put("endTime",null);
	    	}
	    	//被推荐会员昵称
	    	String userNick = recommendOrderVo.getUserNick();
	    	if(StringUtil.isNotEmpty(userNick)){
	    		map.put("userNick", "%"+userNick+"%");
	    	}else{
	    		map.put("userNick",null);
	    	}
	    	//登录用户的id
	    	Subject subject = SecurityUtils.getSubject();
	    	TzUser user = (TzUser)subject.getSession().getAttribute("user");
			String id = user.getId();
			map.put("refereeId",id);
	    	//商城id
	    	String mallId = common.getMallId();
	    	map.put("mallId", mallId);
	        List<RecommendOrderVo> lists = recommendMapperVo.selectRecommendOrderConsumerList(map);
	        //取记录总条数
	        PageInfo<RecommendOrderVo> pageInfo = new PageInfo<>(lists);
	        RecommendOrderVo recommendOrderVo2 = recommendMapperVo.countRecommendOrderConsumerList(map);
	        if(null != recommendOrderVo2){
	        	pageInfo.setNavigatePages(recommendOrderVo2.getPaySingleItemTotal());
	        }
			msgResult = MsgResult.result(true, "", pageInfo);
			return msgResult;
	}

	@Override
	public MsgResult orderConsumerDetailList(String orderId,Integer rows,Integer curPage) {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		orderItemExample = new TzOrderItemExample();
		//分页处理
        PageHelper.startPage(curPage, rows);
        criteria4 = orderItemExample.createCriteria();
        criteria4.andOrderIdEqualTo(orderId);
        orderItemExample.setOrderByClause(" created_time DESC ");	
        List<TzOrderItem> lists = orderItemMapper.selectByExample(orderItemExample);
        LinkedList<TzOrderItem> orderItems = new LinkedList<>();
        for(TzOrderItem orderItem : lists  ){
        	orderItem.setHomepageUrl(Constant.FILESERVER_URL+orderItem.getHomepageUrl());
        	orderItems.add(orderItem);
        }	
        PageInfo<TzOrderItem> pageInfo = new PageInfo<>(orderItems);
        
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}

}

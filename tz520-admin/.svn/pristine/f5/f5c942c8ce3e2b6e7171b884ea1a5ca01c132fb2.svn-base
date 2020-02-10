package com.tz.serviceImpl;

import java.util.ArrayList;
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
import com.tz.mapper.TzRecommendMapper;
import com.tz.mapper.TzUserCityMapper;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.vo.TzCityMapperVo;
import com.tz.mapper.vo.TzRecommendMapperVo;
import com.tz.pojo.TzCity;
import com.tz.pojo.TzCityExample;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzRecommendExample;
import com.tz.pojo.TzUserCity;
import com.tz.pojo.TzUserCityExample;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.vo.CityVo;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.RecommendVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;
import com.tz.service.CityService;
import com.tz.service.RecommendService;
import com.tz.service.UserCityService;

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
    	//用户的电话
    	String refereeId = recommendVo.getRefereeId();
    	if(StringUtil.isNotEmpty(refereeId)){
    		map.put("refereeId", refereeId);
    	}else{
    		map.put("refereeId",null);
    	}
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
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}

	@Override
	public MsgResult deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(StringUtil.isNotEmpty(id)){	
			int res = recommendMapper.deleteByPrimaryKey(id);
			if(res == 1){
				msgResult = MsgResult.nodata(true, "操作成功！");
			}else{
				msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
			}
		}else{
			msgResult = MsgResult.result(false, "删除失败，请稍后重试！", "参数id不能为空！");
		}
		return msgResult;
	}
	
	@Transactional
	@Override
	public MsgResult settlementById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(StringUtil.isNotEmpty(id)){	
			try {
				TzRecommend recommend = recommendMapper.selectByPrimaryKey(id);
				if(null != recommend){
					Subject subject = SecurityUtils.getSubject();
					TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
					String refereeId = recommend.getRefereeId();
					String operaterId = manager.getId();
					/* //封装map集合
			        Map<String,Object> map = new HashMap<String, Object>();
			        String refereeId = recommend.getRefereeId();
			        map.put("refereeId", refereeId);
			        int count = recommendMapperVo.countTopTwo(map);
			        //两位
			        if(count >= 2){
			        	recommend.setTopTwo(0);
			        //一位	
			        }else if(count >=1){
			        	recommend.setTopTwo(2);
			        //没有	
			        }else{
			        	recommend.setTopTwo(1);
			        }*/
			        recommend.setUpdatedTime(new Date());
			        recommend.setFeedbackTime(new Date());
			        recommend.setFeedbackStatus(1);
			        recommend.setOperater(operaterId);
			        //RefereeId
					int res = recommendMapper.updateByPrimaryKeySelective(recommend);	
					//爱心值
					int feedbackFee = recommend.getFeedbackFee();
					//消费明细
					//后台结算
					TzUserLoveConsumptionDetails details = new TzUserLoveConsumptionDetails();
					details.setId(IDUtils.genId());
					details.setConsumeVal(Integer.toString(feedbackFee));
					details.setCreatedTime(new Date());
					details.setUpdatedTime(new Date());
					details.setType(3);
					details.setOperater(operaterId);
					details.setUserId(refereeId);
					//添加消费记录
					consumptionDetailsMapper.insertSelective(details);
			        //爱心成长值
					userLoveExample = new TzUserLoveExample();
			        criteria2 = userLoveExample.createCriteria();
			        criteria2.andUserIdEqualTo(refereeId);
			        List<TzUserLove> userLoves = userLoveMapper.selectByExample(userLoveExample);
			        if(userLoves.size() > 0){
			        	TzUserLove userLove = userLoves.get(0);
			        	userLove.setLastUpdatedTime(new Date());
			        	userLove.setUpdatedTime(new Date());
			        	userLove.setOperater(operaterId);
			        	userLove.setLoveSurplus(userLove.getLoveSurplus()-feedbackFee);
			        	//修改成长值
			        	userLoveMapper.updateByPrimaryKeySelective(userLove);    	
			        }else{
			        	TzUserLove userLove= new TzUserLove();
						userLove.setId(IDUtils.genId());
						//剩余爱心值
						userLove.setLoveSurplus(0);
						//总爱心值
						userLove.setLoveTotal(feedbackFee);
						userLove.setCreatedTime(new Date());
						userLove.setUpdatedTime(new Date());
						userLove.setLastUpdatedTime(new Date());
						userLove.setUserId(refereeId);
						userLove.setOperater(operaterId);
						userLoveMapper.insert(userLove);
			        }
			        if(res == 1){
						msgResult = MsgResult.nodata(true, "操作成功！");
					}else{
						msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
					}
				}else{
					msgResult = MsgResult.result(false, "删除失败，请稍后重试！", "参数id不能为空！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
			}
		}else{
			msgResult = MsgResult.result(false, "删除失败，请稍后重试！", "参数id不能为空！");
		}
		return msgResult;
	}

}

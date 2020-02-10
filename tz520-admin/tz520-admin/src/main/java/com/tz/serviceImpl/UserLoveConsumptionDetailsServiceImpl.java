package com.tz.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.excel.ExcelUtil;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.vo.TzUserLoveConsumptionDetailsMapperVo;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveShowVo;
import com.tz.res.MsgResult;
import com.tz.service.UserLoveConsumptionDetailsService;

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
    	//用户名
    	String userName = consumptionDetailsVo.getUserName();
    	if(StringUtil.isNotEmpty(userName)){
    		map.put("userName", "%"+userName+"%");
    	}else{
    		map.put("userName", null);
    	}
    	//消费类型
    	Integer type = consumptionDetailsVo.getType();
    	if(type!=null){
    		map.put("type", type);
    	}
    	
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
	
	/**
	 * 导出excel
	 */
	@Override
	public MsgResult importExcel(UserLoveConsumptionDetailsVo consumptionDetailsVo, String fileName,
			HttpServletRequest request, HttpServletResponse response) {
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
    	//用户名
    	String userName = consumptionDetailsVo.getUserName();
    	if(StringUtil.isNotEmpty(userName)){
    		map.put("userName", "%"+userName+"%");
    	}else{
    		map.put("userName", null);
    	}
    	//消费类型
    	Integer type = consumptionDetailsVo.getType();
    	if(type!=null){
    		map.put("type", type);
    	}
    	
        List<UserLoveConsumptionDetailsVo> lists = loveConsumptionDetailsMapperVo.selectLoveDetailAndUserList(map);
      
    	if(lists.isEmpty()){
    		return MsgResult.nodata(false, "在该条件下没有找到爱心值消费明细数据，不能生成excel!");
    	}else{
    	String[] title = new String[]{"会员ID","会员名称","会员昵称","会员电话","消费描述","提现类型","消费类型","收入/支出","提现账号","消费爱心值","手续费","订单号ID","消费时间","更新时间"};
    	String[] LoveConsumptionDetail = new String[]{"userId","userName","userNick","phone","name","showType","type","status","showAccount","consumeVal","fee","orderId","createdTime","updatedTime"};
    	String num = ExcelUtil.exportExcel3(lists, title,LoveConsumptionDetail,fileName, request,response);
		return MsgResult.nodata(true, "成功导出数据："+num+"条");
    	}
	}
}

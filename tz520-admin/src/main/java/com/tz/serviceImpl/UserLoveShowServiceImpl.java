package com.tz.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tz.excel.ExcelUtil;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.TzUserLoveShowMapper;
import com.tz.mapper.vo.TzUserLoveConsumptionDetailsMapperVo;
import com.tz.mapper.vo.TzUserLoveMapperVo;
import com.tz.mapper.vo.TzUserLoveShowMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.TzUserLoveShow;
import com.tz.pojo.TzUserLoveShowExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveShowVo;
import com.tz.pojo.vo.UserLoveVo;
import com.tz.res.MsgResult;
import com.tz.service.UserLoveConsumptionDetailsService;
import com.tz.service.UserLoveShowService;
import com.tz.service.UserloveService;

@Service
public class UserLoveShowServiceImpl implements UserLoveShowService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private TzUserLoveShowMapper userLoveShowMapper;	
	private TzUserLoveShowExample example;
	private TzUserLoveShowExample.Criteria criteria;
		
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
	@Autowired 
	TzUserLoveShowMapperVo userLoveShowMapperVo;	
    
	@Override
	public MsgResult getUserLoveShowList(UserLoveShowVo loveShowVo,Integer rows,Integer curPage) throws Exception {
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String phone = loveShowVo.getPhone();
    	//手机号码
    	if(StringUtil.isNotEmpty(phone)){
    		map.put("phone", phone+"%");
    	}else{
    		map.put("phone", null);
    	}
    	
    	String userName = loveShowVo.getUserName();
    	//用户名称
    	if(StringUtil.isNotEmpty(userName)){
    		map.put("userName", "%"+userName+"%");
    	}else{
    		map.put("userName", null);
    	}
    	
    	//收款人真实姓名
    	String payeeRealName =loveShowVo.getPayeeRealName();
    	if(StringUtil.isNotEmpty(payeeRealName)){
    		map.put("payeeRealName", "%"+payeeRealName+"%");
    	}else{
    		map.put("payeeRealName", null);
    	}
    	
    	//时间段
    	if(null != loveShowVo.getStartTime() && null != loveShowVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(loveShowVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(loveShowVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
    	//用户id
    	String userId = loveShowVo.getUserId();
    	if(StringUtil.isNotEmpty(userId)){
    		map.put("userId", userId);
    	}else{
    		map.put("userId", null);
    	}
    	//状态
    	String selectType = loveShowVo.getSelectType();
    	if(StringUtil.isNotEmpty(selectType)){
    		map.put("status", selectType);
    	}else{
    		map.put("status", null);
    	}
        List<UserLoveShowVo> lists = userLoveShowMapperVo.selectLoveShowAndUserList(map);
        //取记录总条数
        PageInfo<UserLoveShowVo> pageInfo = new PageInfo<>(lists);
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}
	@Override
	public MsgResult deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			int res = userLoveShowMapper.deleteByPrimaryKey(id);
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
	public MsgResult settlement(TzUserLoveShow userLoveShow) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(null != userLoveShow){
			userLoveShow.setAccountTime(new Date());
			userLoveShow.setStatus(1);
			Subject subject = SecurityUtils.getSubject();
			TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
			String OperaterId = manager.getId();
			//操作人
			userLoveShow.setOperater(OperaterId);
			userLoveShow.setUpdatedTime(new Date());
			int res = userLoveShowMapper.updateByPrimaryKeySelective(userLoveShow);
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
	 * 导出用户爱心值提取信息excel
	 */
	@Override
	public MsgResult importLoveShowExcel(UserLoveShowVo loveShowVo, String fileName, HttpServletRequest request, HttpServletResponse response) {
		//封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String phone = loveShowVo.getPhone();
    	//手机号码
    	if(StringUtil.isNotEmpty(phone)){
    		map.put("phone", phone+"%");
    	}else{
    		map.put("phone", null);
    	}
    	
    	String userName = loveShowVo.getUserName();
    	//用户名称
    	if(StringUtil.isNotEmpty(userName)){
    		map.put("userName", "%"+userName+"%");
    	}else{
    		map.put("userName", null);
    	}
    	//收款人真实姓名
    	String payeeRealName =loveShowVo.getPayeeRealName();
    	if(StringUtil.isNotEmpty(payeeRealName)){
    		map.put("payeeRealName", "%"+payeeRealName+"%");
    	}else{
    		map.put("payeeRealName", null);
    	}
    	//时间段
    	if(null != loveShowVo.getStartTime() && null != loveShowVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(loveShowVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(loveShowVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
    	//用户id
    	String userId = loveShowVo.getUserId();
    	if(StringUtil.isNotEmpty(userId)){
    		map.put("userId", userId);
    	}else{
    		map.put("userId", null);
    	}
    	//状态
    	String selectType = loveShowVo.getSelectType();
    	if(StringUtil.isNotEmpty(selectType)){
    		map.put("status", selectType);
    	}else{
    		map.put("status", null);
    	}
    	List<UserLoveShowVo> list = userLoveShowMapperVo.selectLoveShowAndUserList(map);
    	if(list.isEmpty()){
    		return MsgResult.nodata(false, "在该条件下没有找到爱心值提取数据，不能生成excel!");
    	}else{
    	String[] title = new String[]{"会员ID","会员名称","会员昵称","会员电话","提现类型","提现状态","提现账号","提现金额","收款用户真实姓名","创建时间","修改时间","到账时间"};
    	//方法二
    	String[] loveShow = new String[]{"userId","userName","userNick","phone","type","status","account","consumeVal","payeeRealName","createdTime","updatedTime","accountTime"};
    	String num = ExcelUtil.exportExcel2(list, title,loveShow,fileName, request,response);
		return MsgResult.nodata(true, "成功导出数据:"+num+"条！");
    	}
	}
	
}

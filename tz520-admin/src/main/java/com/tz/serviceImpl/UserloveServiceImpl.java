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
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.vo.TzUserLoveMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserLoveVo;
import com.tz.res.MsgResult;
import com.tz.service.UserloveService;

@Service
public class UserloveServiceImpl implements UserloveService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private TzUserLoveMapper userLoveMapper;	
	private TzUserLoveExample example;
	private TzUserLoveExample.Criteria criteria;
		
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
	@Autowired 
	TzUserLoveMapperVo loveMapperVo;	
    
	@Override
	public MsgResult getUserLoveList(UserLoveVo userLoveVo,Integer rows,Integer curPage) throws Exception {
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String phone = userLoveVo.getPhone();
    	//手机号码
    	if(StringUtil.isNotEmpty(phone)){
    		map.put("phone", "%"+phone+"%");
    	}else{
    		map.put("phone", null);
    	}
    	//时间段
    	if(null != userLoveVo.getStartTime() && null != userLoveVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(userLoveVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(userLoveVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
    	//用户id
    	String userName = userLoveVo.getUserName();
    	if(StringUtil.isNotEmpty(userName)){
    		map.put("userName", "%"+userName+"%");
    	}else{
    		map.put("userName", null);
    	}
    	//状态
    	 Integer status = userLoveVo.getStatus();
    	if(status!=null){
    		map.put("status", status);
    	}
        List<UserLoveVo> lists = loveMapperVo.selectLoveAndUserList(map);
        //取记录总条数
        PageInfo<UserLoveVo> pageInfo = new PageInfo<>(lists);
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}
	@Override
	public MsgResult deleteById(String id) throws Exception {
		MsgResult msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			int res = userLoveMapper.deleteByPrimaryKey(id);
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
	public MsgResult updateStausById(TzUserLove userLove) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(null != userLove){
			userLove.setUpdatedTime(new Date());
			Subject subject = SecurityUtils.getSubject();
			TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
			String OperaterId = manager.getId();
			//操作人
			userLove.setOperater(OperaterId);
			//状态
			userLove.setLastUpdatedTime(new Date());
			int res = userLoveMapper.updateByPrimaryKeySelective(userLove);
			if(res == 1){
				msgResult = MsgResult.nodata(true, "操作成功！");
			}else{
				msgResult = MsgResult.nodata(false, "操作失败，请稍后重试！");
			}	
		}else{
			msgResult = MsgResult.nodata(false, "操作失败，请稍后重试！");
		}	
		return msgResult;
	}
	
	/**
	 * 导出用户爱心值信息
	 */
	@Override
	public MsgResult importExcel(UserLoveVo userLoveVo, String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		//封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String phone = userLoveVo.getPhone();
    	//手机号码
    	if(StringUtil.isNotEmpty(phone)){
    		map.put("phone", "%"+phone+"%");
    	}else{
    		map.put("phone", null);
    	}
    	//时间段
    	if(null != userLoveVo.getStartTime() && null != userLoveVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(userLoveVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(userLoveVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
    	//用户id
    	String userName = userLoveVo.getUserName();
    	if(StringUtil.isNotEmpty(userName)){
    		map.put("userName", "%"+userName+"%");
    	}else{
    		map.put("userName", null);
    	}
    	//状态
    	 Integer status = userLoveVo.getStatus();
    	if(status!=null){
    		map.put("status", status);
    	}
        List<UserLoveVo> list = loveMapperVo.selectLoveAndUserList(map);
        if(list.isEmpty()){
    		return MsgResult.nodata(false, "在该条件下没有找到用户爱心值数据，不能生成excel!");
    	}else{
    	String[] title = new String[]{"会员ID","会员名称","会员昵称","会员电话","总爱心值","剩余爱心值","冻结/启动","创建时间","修改时间","最后一次修改时间"};
    	//方法二
    	String[] userLove = new String[]{"userId","userName","userNick","phone","loveTotal","loveSurplus","status","createdTime","updatedTime","lastUpdatedTime"};
    	String num = ExcelUtil.exportExcel4(list, title,userLove,fileName, request,response);
		return MsgResult.nodata(true, "成功导出数据:"+num+"条！");
		
	}
	}
}

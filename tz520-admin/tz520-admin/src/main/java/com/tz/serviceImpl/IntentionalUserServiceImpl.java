package com.tz.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzIntentionalUserMapper;
import com.tz.mapper.vo.TzIntentionalUserMapperVo;
import com.tz.pojo.TzIntentionalUser;
import com.tz.pojo.vo.TzIntentionalUserVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;
import com.tz.service.IntentionalUserService;
@Service
public class IntentionalUserServiceImpl implements IntentionalUserService {

	//引入扩展mapper类
	@Autowired
	private TzIntentionalUserMapperVo tzIntentionalUserMapperVo;
	
	@Autowired
	private TzIntentionalUserMapper tzIntentionalUserMapper;
	
	@Override
	public MsgResult getIntentionalUserList(TzIntentionalUserVo tzIntentionalUserVo, Integer rows, Integer curPage) {
		  MsgResult msgResult=null;
				rows = rows == null?10:rows;
				curPage = curPage == null?1:curPage;
				//分页处理
		        PageHelper.startPage(curPage, rows);
		        //封装map集合
		        Map<String,Object> map = new HashMap<String, Object>();
		        String phone = tzIntentionalUserVo.getPhone();
		    	//手机号码
		    	if(StringUtil.isNotEmpty(phone)){
		    		map.put("phone", "%"+phone+"%");
		    	}else{
		    		map.put("phone", null);
		    	}
		    	String userName = tzIntentionalUserVo.getUserName();
		    	//用户名
		    	if(StringUtil.isNotEmpty(userName)){
		    		map.put("userName", "%"+userName+"%");
		    	}else{
		    		map.put("userName", null);
		    	}
		    
		    	//时间段
		    	if(null != tzIntentionalUserVo.getStartTime() && null != tzIntentionalUserVo.getEndTime()){
		    		map.put("startTime",DateUtile.pushDays(tzIntentionalUserVo.getStartTime(),0) );
		    		map.put("endTime", DateUtile.pushDays(tzIntentionalUserVo.getEndTime(),0));
		    	}else{
		    		map.put("startTime",null);
		    		map.put("endTime",null);
		    	}
		        List<TzIntentionalUserVo> lists = tzIntentionalUserMapperVo.selectIntentionalUserList(map);
		        //取记录总条数
		        PageInfo<TzIntentionalUserVo> pageInfo = new PageInfo<>(lists);
				msgResult = MsgResult.result(true, "", pageInfo);
				return msgResult;
			}

	/**
	 * 添加意向用户
	 */
	@Override
	public MsgResult addIntentionalUser(TzIntentionalUser tzIntentionalUser) {
		MsgResult msgResult=null;
		tzIntentionalUser.setId(IDUtils.genId());
		tzIntentionalUser.setUpdatedTime(new Date());
		tzIntentionalUser.setCreatedTime(new Date());
		int i = tzIntentionalUserMapperVo.insertSelective(tzIntentionalUser);
		if(i==1){
			msgResult = MsgResult.nodata(true, "success");
		}else{
			msgResult = MsgResult.nodata(false, "添加失败！");
		}
		return msgResult;
	}
	}


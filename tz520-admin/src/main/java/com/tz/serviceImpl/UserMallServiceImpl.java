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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzUserMallMapper;
import com.tz.mapper.TzUserMapper;
import com.tz.mapper.vo.TzUserMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;
import com.tz.service.UserMallService;
import com.tz.service.UserService;

@Service
public class UserMallServiceImpl implements UserMallService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired TzUserMallMapper userMallMapper;	
	private TzUserMallExample example;
	private TzUserMallExample.Criteria criteria;
	
	//公共配置类
    @Autowired
    private CommonVo common;
	
	@Override
	public MsgResult addOrUpdate(TzUserMall user, String type) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(null != user){
			user.setUpdatedTime(new Date());
			Subject subject = SecurityUtils.getSubject();
			TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
			String id = manager.getId();
			//操作人
			user.setOperater(id);
			//新增
			if("add".equals(type)){		
				user.setCreatedTime(new Date());
				user.setId(IDUtils.genId());
				int res = userMallMapper.insertSelective(user);
				if(res == 1){
					msgResult = MsgResult.nodata(true, "操作成功！");
				}else{
					msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
				}
			}//修改
			else if("update".equals(type)){
				int res = userMallMapper.updateByPrimaryKeySelective(user);
				if(res == 1){
					msgResult = MsgResult.nodata(true, "操作成功！");
				}else{
					msgResult = MsgResult.nodata(false, "修改失败，请稍后重试！");
				}
			}else{
				msgResult = MsgResult.nodata(false, "系统无法识别，请传递类型！");
			}	
		}else{
			msgResult = MsgResult.nodata(false, "传递的参数不能为空！");
		}
		return msgResult;
	}

}

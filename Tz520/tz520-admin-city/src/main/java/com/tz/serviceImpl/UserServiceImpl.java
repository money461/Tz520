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
import com.tz.pojo.TzManagerExample;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;
import com.tz.service.UserService;

@Service
public class UserServiceImpl implements UserService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	TzUserMapper userMapper;	
	private TzUserExample example;
	private TzUserExample.Criteria criteria;
	
	//用户扩展类
	@Autowired 
	private TzUserMapperVo userMapperVo;	
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
    
    @Autowired 
	TzUserMallMapper userMallMapper;	
	private TzUserMallExample userMallExample;
	private TzUserMallExample.Criteria criteria2;
    
	
	
	@Override
	public MsgResult login(String phone, String password) throws Exception {
		// TODO Auto-generated method stub
		MsgResult result = null;
		if(StringUtil.isNotEmpty(phone)){
			if(StringUtil.isNotEmpty(password)){
				example = new TzUserExample();
				criteria = example.createCriteria();
				criteria.andPhoneEqualTo(phone);
				List<TzUser> list = userMapper.selectByExample(example);
				if(list!=null && !list.isEmpty()){
					TzUser user = list.get(0);
					String machPasword = IDUtils.md5(password+user.getCode());
					if(machPasword.equals(user.getPassword())){
						//商城id
				    	String mallId = common.getMallId();
						userMallExample = new TzUserMallExample();
						criteria2 = userMallExample.createCriteria();
						criteria2.andUserIdEqualTo(user.getId());
						criteria2.andMallIdEqualTo(mallId);
						List<TzUserMall>  malls = userMallMapper.selectByExample(userMallExample);
						if(malls!=null && !malls.isEmpty()){
							//判断用户的类型是否为城市爱心合伙人
							if(malls.get(0).getType() == 4){
								result = MsgResult.result(true, "success", user);
							}else{
								result = MsgResult.nodata(false, "账户名或者密码错误！！");
							}
						}
					}else{
						result = MsgResult.nodata(false, "账户名或者密码错误！！");
					}
				}else{
					result = MsgResult.nodata(false, "账户名或者密码错误！！");
				}
			}else{
				result = MsgResult.nodata(false, "请输入登陆密码！");
			}
		}else{
			result = MsgResult.nodata(false, "请输入账户名！");
		}
		return result;
	}
	

}

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
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.TzUserMallMapper;
import com.tz.mapper.TzUserMapper;
import com.tz.mapper.vo.TzUserMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.Constant;
import com.tz.res.MsgResult;
import com.tz.service.UserMallService;
import com.tz.service.UserService;

@Service
public class UserServiceImpl implements UserService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired TzUserMapper userMapper;	
	private TzUserExample example;
	private TzUserExample.Criteria criteria;
	
	//用户商城类
	@Autowired TzUserMallMapper userMallMapper;	
	private TzUserMallExample userMallExample;
	private TzUserMallExample.Criteria criteria2;
	//用户扩展类
	@Autowired 
	private TzUserMapperVo userMapperVo;	
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
    
    @Autowired 
	private TzUserLoveMapper userLoveMapper;	
	private TzUserLoveExample userLoveExample;
	private TzUserLoveExample.Criteria userLoveCriteria;
    //商城用户类
    @Autowired
    private UserMallService userMallService;
    
	
	@Override
	public MsgResult getUserList(UserVo userVo, Integer rows, Integer curPage) throws Exception {
		MsgResult msgResult = null;
		// TODO Auto-generated method stub
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String phone = userVo.getPhone();
    	//手机号码
    	if(StringUtil.isNotEmpty(phone)){
    		map.put("phone", "%"+phone+"%");
    	}else{
    		map.put("phone", null);
    	}
    	String userName = userVo.getUserName();
    	//用户名
    	if(StringUtil.isNotEmpty(userName)){
    		map.put("userName", "%"+userName+"%");
    	}else{
    		map.put("userName", null);
    	}
    	//商城id
    	String mallId = common.getMallId();
    	map.put("mallId", mallId);
    	//时间段
    	if(null != userVo.getStartTime() && null != userVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(userVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(userVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        List<UserVo> lists = userMapperVo.selectUserAndMailList(map);
        //取记录总条数
        PageInfo<UserVo> pageInfo = new PageInfo<>(lists);
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}
	@Transactional
	@Override
	public MsgResult addOrUpdate(TzUser user, String type,Integer userType,String oldUserName,String oldPhone,String userMallId) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(null != user){
			List<TzUser> list = new ArrayList<TzUser>();
			String userName = user.getUserName();
			if("update".equals(type)){
				if(oldUserName.equals(userName)){	
				}else{
					example = new TzUserExample();
			        criteria = example.createCriteria();
			        criteria.andUserNameEqualTo(userName);
			        list = userMapper.selectByExample(example);
				}
			}else{
				example = new TzUserExample();
		        criteria = example.createCriteria();
		        criteria.andUserNameEqualTo(userName);
		        list = userMapper.selectByExample(example);
			}
			
			if(list.size()>0){
				msgResult = MsgResult.nodata(false, "用户名已被注册！");
			}
			else{
				String phone = user.getPhone();
				List<TzUser> list2 = new ArrayList<TzUser>();;
				if("update".equals(type)){
					if(oldPhone.equals(phone)){	
					}else{
						example = new TzUserExample();
				        criteria = example.createCriteria();
				        criteria.andPhoneEqualTo(phone);
				        list2 = userMapper.selectByExample(example);
					}
				}else{
					example = new TzUserExample();
			        criteria = example.createCriteria();
			        criteria.andPhoneEqualTo(phone);
			        list2 = userMapper.selectByExample(example);
				}	
				if(list2.size()>0){
					msgResult = MsgResult.nodata(false, "手机号码已被注册！");
				}else{
					user.setUpdatedTime(new Date());
					Subject subject = SecurityUtils.getSubject();
					TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
					String id = manager.getId();
					//操作人
					user.setOperater(id);
					//新增
					if("add".equals(type)){	
						String userId = IDUtils.genId();
						user.setCreatedTime(new Date());
						user.setLastUpload(new Date());
						user.setId(userId);
						String code = IDUtils.getCode();
						user.setCode(code);
						user.setUserNick("520_"+IDUtils.phoneCode());	
						user.setHeadUrl(Constant.DFHEAD2);
						String newRecommendedCode = IDUtils.phoneCode();
						user.setRecommendedCode(newRecommendedCode);
						user.setPassword(IDUtils.md5(user.getPassword()+code));
						int res = userMapper.insertSelective(user);
						
						//商城id
				    	String mallId = common.getMallId();
						//添加商城中间表
						TzUserMall userMall = new TzUserMall();
						userMall.setMallId(mallId);
						userMall.setPayAccount("tz520");
						userMall.setPayWay(4);
						userMall.setPrice("520");
						//普通会员
						userMall.setType(userType);
						userMall.setUserId(user.getId());
						MsgResult msgResult2 = userMallService.addOrUpdate(userMall, "add");
						//添加用户爱心表
						TzUserLove love = new TzUserLove();
						love.setId(IDUtils.genId());
						love.setCreatedTime(new Date());
						love.setLastUpdatedTime(new Date());
						love.setLoveSurplus(0);
						love.setLoveTotal(0);
						love.setUpdatedTime(new Date());
						love.setUserId(userId);
						love.setLoveSurplus(0);
						love.setLoveTotal(0);
						int res3 = userLoveMapper.insertSelective(love);
						if(res == 1 && msgResult2.getFlag() && res3 == 1){
							msgResult = MsgResult.nodata(true, "操作成功！");
						}else{
							msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
						}
					}//修改
					else if("update".equals(type)){
						int res = userMapper.updateByPrimaryKeySelective(user);
						//商城id
				    	String mallId = common.getMallId();
						//添加商城中间表
						TzUserMall userMall = new TzUserMall();
						userMall.setMallId(mallId);
						userMall.setPayAccount("tz520");
						userMall.setPayWay(4);
						userMall.setPrice("520");
						//普通会员
						userMall.setType(userType);
						userMall.setId(userMallId);
						MsgResult msgResult2 = userMallService.addOrUpdate(userMall, "update");
						if(res == 1 && msgResult2.getFlag()){
							msgResult = MsgResult.nodata(true, "操作成功！");
						}else{
							msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
						}
					}else{
						msgResult = MsgResult.nodata(false, "系统无法识别，请传递类型！");
					}
				}	
			}
		}else{
			msgResult = MsgResult.nodata(false, "传递的参数不能为空！");
		}
		return msgResult;
	}
	
	
	@Override
	public MsgResult deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			TzUser user = new TzUser();
			user.setUpdatedTime(new Date());
			Subject subject = SecurityUtils.getSubject();
			TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
			String OperaterId = manager.getId();
			//操作人
			user.setOperater(OperaterId);
			//删除
			user.setStatus(2);
			user.setId(id);
			int res = userMapper.updateByPrimaryKeySelective(user);
			if(res == 1){
				msgResult = MsgResult.nodata(true, "操作成功！");
			}else{
				msgResult = MsgResult.nodata(false, "删除失败，请稍后重试！");
			}	
		}else{
			msgResult = MsgResult.result(false, "删除失败，请稍后重试！", "参数id不能为空！");
		}	
		return msgResult;
	}
	@Override
	public MsgResult updateStatusById(String id, Integer status) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			TzUser user = new TzUser();
			user.setUpdatedTime(new Date());
			Subject subject = SecurityUtils.getSubject();
			TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
			String OperaterId = manager.getId();
			//操作人
			user.setOperater(OperaterId);
			//状态
			user.setStatus(status);
			user.setId(id);
			int res = userMapper.updateByPrimaryKeySelective(user);
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
	public MsgResult selectById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(StringUtil.isNotEmpty(id)){

	        //封装map集合
	        Map<String,Object> map = new HashMap<String, Object>();
	    	//商城id
	    	String mallId = common.getMallId();
	    	map.put("mallId", mallId);
	    	map.put("id", id);
	        List<UserVo> lists = userMapperVo.selectUserAndMailList(map);
			if(lists.size() > 0){
				msgResult = MsgResult.result(true, "查询成功！", lists.get(0));
			}else{
				msgResult = MsgResult.nodata(false, "查询失败，请稍后重试！");
			}	
		}else{
			msgResult = MsgResult.result(false, "操作失败，请稍后重试！", "参数id不能为空！");
		}	
		return msgResult;
	}


}

package com.tz.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.interfaces.ReceiverInfoService;
import com.tz.mapper.TzReceiverinfoMapper;
import com.tz.mapper.vo.TzReceiverinfoMapperVo;
import com.tz.pojo.TzReceiverinfo;
import com.tz.res.AppMsgResult;
@Service
public class ReceiverInfoServiceImpl implements ReceiverInfoService{

	@Autowired
	private RedisCache cache;
	
	//引入收货人信息管理映射接口
	@Autowired
	private TzReceiverinfoMapperVo receiverInfoMapperVo;
	
	@Autowired
	private TzReceiverinfoMapper receiverInfoMapper;
	
	//校验useruserToken是否过期存在
		public AppMsgResult checkUserToken(String userId,String userToken){
			AppMsgResult msgResult = null; 
				if(StringUtil.isNotEmpty(userToken)){
					//获取用户的登录 token
					String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
					String userToken_cache = cache.getCache(cache_key, String.class);
					//是否存在
					if(null != userToken_cache && userToken.equals(userToken_cache)){
						//重置userToken有效时间
						cache.putCacheWithExpireTime(cache_key, userToken_cache, RedisCache.USERCAHCETIME);
						msgResult= AppMsgResult.nodata(true,"success");
					}else{
						msgResult= AppMsgResult.nodata(false, "failure");
					}
				}else{
				msgResult= AppMsgResult.nodata(false, "failure");
			}
			return msgResult;
			
		}
	
	/**
	 * //在结算确认订单页面，根据用户id回显该用户的收货地址信息
	 */
	@Override
	public AppMsgResult selectShippingByUserId(String userId,String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isNotEmpty(userId)){
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				//收货人信息
				List<TzReceiverinfo> receiverinfoList = receiverInfoMapperVo.selectReceiverInfoByUserId(userId);
				result = AppMsgResult.result(200, "success", receiverinfoList);
				
			}else{
				result = AppMsgResult.result(538,"用户未登录！",null);
			}
			
		}else{
			result = AppMsgResult.result(542,"用户id为null",null);
		}
			
		return result;
	}

	/**
	 * 在订单确认页面或者管理收货地址页面创建添加收货地址信息进行保存 type=add 更新修改保存收人信息type=update
	 */
	@Override
	public AppMsgResult createReceiverInfo(String userId,String receiverInfo,String type,String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isNotEmpty(userId)){
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				if(StringUtils.isBlank(receiverInfo)){
					return AppMsgResult.result(521, "receiverInfo参数有误！！", null);
				}
				TzReceiverinfo tzReceiverinfo = JSONObject.parseObject(receiverInfo, TzReceiverinfo.class);
				Date date = new Date();
				if(StringUtils.isBlank(type)){
					return AppMsgResult.result(543, "type参数有误！！", "");
				}
				//判断收货人信息参数是否为空
				if(StringUtils.isBlank(tzReceiverinfo.getReceiverName())){
					return AppMsgResult.result(543, "收货人姓名参数null！！", null);
				}else if(StringUtils.isBlank(tzReceiverinfo.getReceiverMobile())){
					return AppMsgResult.result(543, "收货人电话参数null！！", null);
				}else if(StringUtils.isBlank(tzReceiverinfo.getReceiverState())){
					return AppMsgResult.result(543, "收货人省份参数null！！", null);
				}else if(StringUtils.isBlank(tzReceiverinfo.getReceiverDistrict())){
					return AppMsgResult.result(543, "收货人区域参数null！！", null);
				}else if(StringUtils.isBlank(tzReceiverinfo.getReceiverCity())){
					return AppMsgResult.result(543, "收货人城市参数null！！", null);
				}else if(StringUtils.isBlank(tzReceiverinfo.getReceiverAddress())){
					return AppMsgResult.result(543, "收货详细地址参数null！！", null);
				}
				if("add".equals(type)){
					//设置默认地址,先清空原来所有的isDefault数据 至0
					receiverInfoMapperVo.deleteISDefault(userId);
					tzReceiverinfo.setId(IDUtils.genId());
					tzReceiverinfo.setUserId(userId);
					tzReceiverinfo.setIsDefault(1); //设置为默认地址
					tzReceiverinfo.setCreatedTime(date);
					tzReceiverinfo.setUpdatedTime(date);
				    int i = receiverInfoMapper.insertSelective(tzReceiverinfo);
					if(i==1){
						result =  AppMsgResult.result(200, "保存收货人信息成功！", null);
					}else{
						result =  AppMsgResult.result(558, "保存收货人信息失败,请稍后再试！", null);
					}
				}else if("update".equals(type)){
					//设置默认地址,先清空原来所有的isDefault数据 至0
					receiverInfoMapperVo.deleteISDefault(userId);
					//更新保存收货人信息
					tzReceiverinfo.setUpdatedTime(date);
					tzReceiverinfo.setIsDefault(1);  //设置为默认地址
					int i = receiverInfoMapper.updateByPrimaryKeySelective(tzReceiverinfo);
					if(i==1){
						result =  AppMsgResult.result(200, "更新收货人信息成功！", null);
					}else{
						result =  AppMsgResult.result(558, "更新收货人信息失败，请稍后再试！", null);
					}
				}
			}else{
				result = AppMsgResult.result(538, "用户未登录", null);
			}
		}else{
			result = AppMsgResult.result(542,"用户id为null",null);
		}
		
		return result;
	}

	//根据收货人信息id删除信息 
	@Override
	public AppMsgResult optReciverInfo(String userId,String id,String type,String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isNotEmpty(userId)){
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				
				if(StringUtils.isNotEmpty(id)){
					if(StringUtils.isBlank(type)){
						return AppMsgResult.result(543, "type参数有误！！", null);
					}
					if("delete".equals(type)){
						//删除执行
						int i = receiverInfoMapper.deleteByPrimaryKey(id);
						if(i==1){
							result=AppMsgResult.result(200,"收货人信息删除成功！",null);
						}else{
							result=AppMsgResult.result(558,"操作失败，请稍后再试！",null);
						}
					}else if("default".equals(type)){
						//设置为默认地址
						//设置默认地址,先清空原来所有的isDefault数据 至0
						receiverInfoMapperVo.deleteISDefault(userId);
						//根据主键id设置参数isDefault=1默认
						TzReceiverinfo receiverinfo = new TzReceiverinfo();
						receiverinfo.setId(id);
						receiverinfo.setIsDefault(1);
						receiverinfo.setUpdatedTime(new Date());
						int i = receiverInfoMapper.updateByPrimaryKeySelective(receiverinfo);
						if(i==1){
							result=AppMsgResult.result(200,"设置默认地址成功！",null);
						}else{
							result=AppMsgResult.result(558,"操作失败！",null);
						}
					}  
					
				}else{
					result = AppMsgResult.result(543,"id为null",null);
				}
			}else{
				
				result = AppMsgResult.result(538, "用户未登录", null);
			}
		}else{
			result = AppMsgResult.result(542,"用户id为null",null);
		}
		return result;
	}


}

package com.tz.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.interfaces.CartService;
import com.tz.mapper.TzCartMapper;
import com.tz.mapper.vo.TzCartMapperVo;
import com.tz.pojo.TzCart;
import com.tz.pojo.vo.TzCartVo;
import com.tz.pojo.vo.TzCategoryCart;
import com.tz.res.AppMsgResult;

@Service
public class CartServiceImpl implements CartService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private TzCartMapper tzCartMapper;
	
	//引入购物车映射接口
	@Autowired
	private  TzCartMapperVo tzCartMapperVo;
	
	
	/**
	 * 查询该用户的等级，根据不同等级，展示不同的价格
	 * 普通用户 --》普通价 、 会员价、实际价（普通价）
	 * 会员 用户--》普通价、 会员价、实际价（会员价）
	 * 爱心会员用户---》普通价、会员价（爱心会员价）、实际价（爱心会员价）
	 */
	public TzCartVo alterPrice(TzCartVo tzCartVo,Integer type){
		  //获取用户等级
		  if(type==0){
			  //实际支付价 realPrice=普通会员价  其他价格不变
			  tzCartVo.setRealPrice(tzCartVo.getPrice());
		  }else if(type==1){
			  //实际支付价realPrice=等于会员价 其他价格不变
			  tzCartVo.setRealPrice(tzCartVo.getMemberPrice());
		  }else if(type==2 ||type==3 ||type==4){
			//实际支付价realPrice=等于爱心会员价 memberPrice=爱心会员价其他价格不变
			  tzCartVo.setRealPrice(tzCartVo.getLovePrice());
			  tzCartVo.setMemberPrice(tzCartVo.getLovePrice());
		  }
		  
		return tzCartVo;
	}
	
	
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
		
    //删除缓存
	public void  deleteRedis(String userId){
		//删除该用户缓存中的购物车信息
		String cartCache_key = RedisCache.CAHCENAME+"|selectCartList|"+userId;
		List<TzCategoryCart> cartList_cache = cache.getListCache(cartCache_key, TzCategoryCart.class);
		if(cartList_cache!=null){
				cache.deleteCache(cartCache_key);
		}
	}

	//根据用户id查询用户购物车信息，从redis中获取，缓存中没有，查询数据库cart表
	@Override
	public AppMsgResult selectCartList(String userId, String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isBlank(userId)){
			result = AppMsgResult.result(542, "userId为null", null);
		}else{
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				//验证通过，获取购物车数据信息
				 //获取用户等级
				 Integer type = tzCartMapperVo.queryUserType(userId);
				 if(type==null){
					 //没有找到默认为普通用户
					 type=0;
				 }
				//根据用户id向缓存中获取购物车信息
				String cartCache_key = RedisCache.CAHCENAME+"|selectCartList|"+userId;
				List<TzCategoryCart>  categoryCartList = cache.getListCache(cartCache_key, TzCategoryCart.class);
				if(categoryCartList!=null){
					LOG.info("get cache with key:"+cartCache_key);
				}else{
					//从数据库中查询该用户购物车信息
					categoryCartList = tzCartMapperVo.selectCartListByUserId(userId);
					if(categoryCartList.size() > 0){
						//遍历购物车商品信息
						for (TzCategoryCart tzCategoryCart : categoryCartList) {
							List<TzCartVo> cartList=new ArrayList<TzCartVo>();
							List<TzCartVo> cartList_old = tzCategoryCart.getCartList();
							for (TzCartVo tzCartVo : cartList_old) {
								//获取每个购物车中的商品信息
								TzCartVo cartVo = this.alterPrice(tzCartVo, type);
								//添加至集合中
								cartList.add(cartVo);
							}
							//将修改价格后的购物车数据封装
							tzCategoryCart.setCartList(cartList);
						}
						
						//将购车信息存入缓存中
						cache.putListCacheWithExpireTime(cartCache_key, categoryCartList, RedisCache.CAHCETIME);
						LOG.info("put cache with key:"+cartCache_key);
					}
				}
				
				//返回结果
				result = AppMsgResult.result(200,"success",categoryCartList);
				
			}else {
				//该userToken不存在，需要跳转至登录页面
				result = AppMsgResult.result(538, "用户未登录!", null);
			}
			
		}
		return result;
	}


	/**
	 * 添加购物车商品
	 */
	@Override
	public AppMsgResult addCart(String list, String userId, String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isBlank(userId)){
			result=  AppMsgResult.result(542, "用户id不能为空", null);
		}else{
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				if(StringUtils.isBlank(list)){
					return AppMsgResult.result(543, "list参数有误！！", null);
				}
				//将对象反序列化为数组集合对象
				List<TzCart> cartList = JSONObject.parseArray(list, TzCart.class);
				Date date = new Date();
				  for (TzCart cart : cartList) {
					  String itemId = cart.getItemId();
					  if(StringUtils.isBlank(itemId)){
						  return AppMsgResult.result(543, "list参数有误！！", null);
					  }
					  Integer num = cart.getNum();
					  if(num==null || num<=0){
						  return AppMsgResult.result(543, "num参数有误！", null);
					  }
					  //查询该用户购物车中是否存在商品
					  TzCart tzCart = tzCartMapperVo.selectCartByItemId(userId, itemId);
					  if(tzCart!=null){
						  //更新商品的数量
						  tzCart.setUpdatedTime(date);;
						  //加上原来商品的数量上添加
						  tzCart.setNum(num+tzCart.getNum());
						  tzCartMapper.updateByPrimaryKeySelective(tzCart);
					  }else{
						  //添加商品至购物车
						    	cart.setId(IDUtils.genId());
						    	cart.setUserId(userId);
						    	cart.setCreatedTime(date);
						    	cart.setUpdatedTime(date);
						    	//向购物车插入数据
						    	tzCartMapper.insertSelective(cart);
						    }
					  }
					  
				    //删除缓存
				    this.deleteRedis(userId);
					 result=AppMsgResult.result(200, "购物车添加操作成功！", null);
			   }else{
				 //该userToken不存在，需要跳转至登录页面
				result = AppMsgResult.result(538, "用户未登录！", null);
			   }
		}		  
		return result;
	}

	//删除单个或者多个购物车信息
	@Override
	public AppMsgResult deleteCart(String userId, String itemIds, String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isBlank(userId)){
			result = AppMsgResult.result(542, "userId为null", null);
		}else{
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				if(StringUtils.isBlank(itemIds)){
					return AppMsgResult.result(543, "itemIds参数有误！！", null);
				}
				//批量删除
				String[] ids = itemIds.split(",");
				tzCartMapperVo.deleteCartByItemId(userId,ids);
				  //删除缓存
			    this.deleteRedis(userId);
				 result=AppMsgResult.result(200, "购物车删除操作成功！", null);
			  }else{
				//该userToken不存在，需要跳转至登录页面
					result = AppMsgResult.result(538, "用户未登录！", null);
			  }
			}
		return result;
	}

	/**
	 * 修改购物车商品数量信息
	 */
	@Override
	public AppMsgResult updateCart(String cart, String userId, String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isBlank(userId)){
			result = AppMsgResult.result(543, "failure", "userId="+userId);
		}else{
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				if(StringUtils.isBlank(cart)){
					return AppMsgResult.result(543, "cart参数有误！！", null);
				}
				//json字符串装换为bean对象
				TzCart tzCart = JSONObject.parseObject(cart, TzCart.class);
				String itemId = tzCart.getItemId();
				if(itemId==null){
					return  AppMsgResult.result(543, "itemId参数null！！", null);
				}
				Integer num = tzCart.getNum();
				if(num==null ||num<=0){
					return  AppMsgResult.result(543, "num参数有误！！", null);
				}
				//查询商品信息
				tzCart = tzCartMapperVo.selectCartByItemId(userId, itemId);
				if(tzCart!=null){
					//更新商品数量
					tzCart.setNum(num);
					tzCart.setUpdatedTime(new Date());
					int i = tzCartMapper.updateByPrimaryKeySelective(tzCart);
					if(i==1){
						//删除缓存
						this.deleteRedis(userId);
						result=AppMsgResult.result(200, "购物车更新操作成功！", null);
						
					}else{
						 result=AppMsgResult.result(558, "购物车更新操作失败！", null);
					}
				}else{
					result=AppMsgResult.result(560, "购物车不存在该商品数据！", null);
				}
			}else{
				//该userToken不存在，需要跳转至登录页面
				result = AppMsgResult.result(538, "用户未登录！", null);
			}
		}
		return result;
	}

}
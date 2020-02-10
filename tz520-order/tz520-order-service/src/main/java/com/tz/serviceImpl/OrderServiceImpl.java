package com.tz.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.interfaces.OrderService;
import com.tz.mapper.TzCityUserConsumerMapper;
import com.tz.mapper.TzOrderMapper;
import com.tz.mapper.TzOrderShippingMapper;
import com.tz.mapper.TzRecommendMapper;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.TzUserMallMapper;
import com.tz.mapper.vo.TzOrderMapperVo;
import com.tz.mapper.vo.TzUserMapperVo;
import com.tz.pojo.TzCityUserConsumer;
import com.tz.pojo.TzCityUserConsumerExample;
import com.tz.pojo.TzItem;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderItem;
import com.tz.pojo.TzOrderShipping;
import com.tz.pojo.TzReceiverinfo;
import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzRecommendExample;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.TzOrderCreate;
import com.tz.pojo.vo.TzOrderItemVo;
import com.tz.pojo.vo.TzOrderList;
import com.tz.pojo.vo.TzOrderValidate;
import com.tz.pojo.vo.TzOrderVo;
import com.tz.pojo.vo.UserVo2;
import com.tz.res.AppMsgResult;
@Service
public class OrderServiceImpl implements OrderService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	//引入扩展类订单mapper
	@Autowired
	private TzOrderMapperVo orderMapperVo;
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private TzOrderMapper orderMapper;
	
	@Autowired
	private TzOrderShippingMapper orderShippingMapper;
	
	@Autowired 
	private TzUserMallMapper userMallMapper ;	
	private TzUserMallExample userMallExample;
	private TzUserMallExample.Criteria criteria2;
	//用户总的爱心值类
	@Autowired 
	private TzUserLoveMapper userLoveMapper;	
	private TzUserLoveExample userLoveExample;
	private TzUserLoveExample.Criteria userLoveCriteria;
	//用户爱心值消费类
	@Autowired 
	TzUserLoveConsumptionDetailsMapper consumptionDetailsMapper;
	//用户推荐类
	@Autowired 
	TzRecommendMapper recommendMapper;	
	private TzRecommendExample recommendExample;
	private TzRecommendExample.Criteria recommendCriteria;
	
	//城市爱心合伙人单品消费记录
	@Autowired 
	TzCityUserConsumerMapper cityUserConsumerMapper;	
	private TzCityUserConsumerExample cityUserConsumerExample;
	private TzCityUserConsumerExample.Criteria criteria7;
	
	//配置文件引入商品所属商家平台
	@Autowired
	private CommonVo common;
	
    //用户扩展类
    @Autowired
	private TzUserMapperVo userMapperVo;
	
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
	 * 清空用户我的订单缓存信息
	 * @param userId
	 */
	public  void deleteOrderCache(String userId){
		String orderCache_key = RedisCache.CAHCENAME+"|selectOrderList|"+userId;
		List<TzOrderList>  orderList_cache = cache.getListCache(orderCache_key, TzOrderList.class);
		if(orderList_cache!=null){
			LOG.info("clear cache with key:"+orderCache_key);
			cache.deleteCache(orderCache_key);
		}
	}
	
	//获取订单中该商品该用户等级下实际支付的价格
	private BigDecimal getRealPrice(TzItem item, Integer type) {
        BigDecimal realPrice=null;
        if(type==0){
			  //实际支付价 realPrice=普通会员价  其他价格不变
			  realPrice = item.getPrice(); 
		  }else if(type==1){
			  //实际支付价realPrice=等于会员价 其他价格不变
			  realPrice=item.getMemberPrice();
		  }else if(type==2 ||type==3 ||type==4){
			//实际支付价realPrice=等于爱心会员价 memberPrice=爱心会员价其他价格不变
			 realPrice=item.getLovePrice();
		  }
		return realPrice;
	}
		  
	/**
	 * 订单系统确认校验展示
	 */
	@Override
	public AppMsgResult validateOrder(String items, String userId,String userToken) {
		AppMsgResult result=null;
		
		if(StringUtils.isNotEmpty(userId)){
				//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
				if((boolean)msgResult.getFlag()){
					//查询商品信息及物流信息和爱心值
					 if(StringUtils.isNotBlank(items)){
						 List<TzItem> list = JSONObject.parseArray(items, TzItem.class);
						 if(list.size()!=0){
							 
							 //获取用户等级
							 Integer type = orderMapperVo.queryUserType(userId);
							 if(type==null){
								 //没有找到默认为普通用户
								 type=0;
							 }
							 
							 //商品需付款
								BigDecimal cash=new BigDecimal(0); //不含总邮费
								BigDecimal payment=new BigDecimal(0); //实际最终付款
								BigDecimal postFee=new BigDecimal(0);//总邮费
								int  totalNum=0; //总件数
								List<TzOrderItemVo> orderItemList= new ArrayList<TzOrderItemVo>();
								 //逐个查询商品信息并计算各个商品实际支付价钱
								System.out.println("传输过来商品获得长度--------"+list.size());
								
								 for (TzItem tzItem : list) {
									 String itemId = tzItem.getId();
										if(StringUtils.isBlank(itemId)){
											return AppMsgResult.result(543, "itemId参数有误！！", null);
										}
									 //去数据库查询该商品信息
									TzItem item = orderMapperVo.selectItemById(itemId);
									Integer num = tzItem.getNum();
									if(null==num || num<=0){
										return AppMsgResult.result(543, "商品购买数量异常", null);
									}
									System.out.println("商品id:--------"+itemId+"购买数量:--------"+num);
									TzOrderItemVo orderItemVo = new TzOrderItemVo();
									orderItemVo.setItemId(itemId);
									orderItemVo.setItemTitle(item.getItemTitle());
									orderItemVo.setDescription(item.getDescription());
									orderItemVo.setHomepageUrl(item.getHomepageUrl());
									BigDecimal itemPost = item.getItemPost();
									orderItemVo.setItemPost(itemPost);
									orderItemVo.setNum(num);
									orderItemVo.setPrice(item.getPrice());
									orderItemVo.setMemberPrice(item.getMemberPrice());
									
									//获取该用户该商品的实际价格
									//获取该用户的实际商品单价
									BigDecimal realPrice = this.getRealPrice(item,type);
									orderItemVo.setRealPrice(realPrice);
									//不含邮费该商品支付实际价钱 = 会员等级超级会员价*num
									BigDecimal itemcash = realPrice.multiply(new BigDecimal(num));
									//含该商品邮费价钱
									orderItemVo.setTotalFee(itemcash.add(itemPost).setScale(2, BigDecimal.ROUND_HALF_UP));
									//总数量
									totalNum=totalNum+num;
									//求总邮费
									postFee=postFee.add(itemPost.setScale(2, BigDecimal.ROUND_HALF_UP));
									//不含邮费支付总价钱
									cash=cash.add(itemcash);
									orderItemList.add(orderItemVo);
								}
								 
								 //查询默认的收货人信息
								 TzReceiverinfo receiverinfo = orderMapperVo.selectDefaultReceiverInfo(userId);
								 /**
								  * 查询折扣信息 (暂无)
								  */
								 //最终实际支付：cash+postFee-cash*discount
								 //商品总价超过300元包邮
								 if(cash.compareTo(new BigDecimal(300))==1){
									 postFee=new BigDecimal(0);
								 }
								//计算应该支付总价=商品超级会员价*num+总邮费-（折扣）  （不包含爱心值）
								 payment=cash.add(postFee).subtract(cash.multiply(new BigDecimal(0)));
								 payment = payment.setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入，2.35变成2.4
								 //封装数据
								 TzOrderValidate orderValidate = new TzOrderValidate();
								 orderValidate.setReceiverinfo(receiverinfo);
								 orderValidate.setOrderItemList(orderItemList);
								 orderValidate.setCash(cash.setScale(2, BigDecimal.ROUND_HALF_UP));
								 orderValidate.setDiscount(new BigDecimal(0));
								 orderValidate.setDiscountDesc("暂无优惠券可以使用！");
								 orderValidate.setPayment(payment);
								 
								 orderValidate.setPostFee(postFee);
								 orderValidate.setTotalNum(totalNum);
								 
								 //获取该用户剩余爱心值
								  userLoveExample = new TzUserLoveExample();
								  userLoveCriteria = userLoveExample.createCriteria();
								  userLoveCriteria.andUserIdEqualTo(userId);
								  List<TzUserLove>  userLoveList = userLoveMapper.selectByExample(userLoveExample);
								  TzUserLove userLove=userLoveList.get(0);
								 if(null!=userLove){
									 Integer status = userLove.getStatus();
									 if(status==1){
										 orderValidate.setLoveValue(userLove.getLoveSurplus());
									 }else{
										 //爱心值被冻结 爱心值为0
										 orderValidate.setLoveValue(0);
									 }
									 orderValidate.setStatus(status);
								 }else{
									//该用户在用户爱心值表中不存在,默认爱心值0
									 orderValidate.setLoveValue(0);
									 //默认状态可以使用
									 orderValidate.setStatus(1);
								 }
								 
								 System.out.println("购买总数量：---------"+totalNum);
								 
								 //设置生成订单令牌Ordertoken //1分钟有效
								 String cache_key=RedisCache.CAHCENAME+"|getOrderToken|"+userId;
								 String token = IDUtils.md5(userId+new Date());
								 cache.putCacheWithExpireTime(cache_key,token , RedisCache.ORDERTIME);
								 orderValidate.setOrderToken(token);
								 result = AppMsgResult.result(200,"success",orderValidate);
						 }else{
							 result = AppMsgResult.result(543,"传输参数有误！",null);
						 }
						
					 }else{
						 result = AppMsgResult.result(543,"商品参数null！",null);
					 }
					 
				}else{
					result = AppMsgResult.result(538, "用户未登录！",null);
				}
			}
			return result;
	}
	
	/**
	 * 生成订单系统
	 */
	@Override
	public AppMsgResult createOrder(String userId, String orderInfo,Integer type,String orderToken, String userToken){
	     	AppMsgResult result=null;
			if(StringUtils.isNotEmpty(userId)){
				//判断用户是否登录或者登录是否过期
				AppMsgResult msgResult = checkUserToken(userId, userToken);
				if((boolean)msgResult.getFlag()){
					//校验订单生成orderToken是否过期
					AppMsgResult orderResult = checkOrderToken(userId,orderToken);
					if((boolean)orderResult.getFlag()){
						//检验订单类型
						if(type!=1 && type!=0 || type==null){
							return AppMsgResult.result(543,"type传入参数有误！",null);
						}
						
						//会员升级。判断用户是否已经是会员了
						if(type == 0){
							userMallExample = new TzUserMallExample(); 
							criteria2 = userMallExample.createCriteria();
							criteria2.andUserIdEqualTo(userId);
							List<TzUserMall> userMalls = userMallMapper.selectByExample(userMallExample);
							if(userMalls.size()>0){
								if(userMalls.get(0).getType() >= 1){
									return AppMsgResult.result(560,"创建订单失败，您已是会员！",null);
								}
							}
						}
						if(StringUtils.isBlank(orderInfo)){
							return AppMsgResult.result(543,"传入参数有误！",null);
						}
						TzOrderCreate order = JSONObject.parseObject(orderInfo, TzOrderCreate.class);
						//获取用户等级
						Integer userType = orderMapperVo.queryUserType(userId);
						if(userType==null){
							//没有找到默认为普通用户
							userType=0;
						}
						//获取抵扣掉的爱心值(消费掉的爱心值)
						Integer consumeVal = order.getLoveValue();
						if(null==consumeVal || consumeVal<0){
							return  AppMsgResult.result(543,"消费爱心值传入参数有误！",null);
						}
						System.out.println("扣掉爱心值：--------"+consumeVal);
						

						//获取商品信息最终支付计算总价是否和页面总支付价钱一致
						List<TzItem> itemList = order.getOrderItems();
						if(itemList==null||itemList.isEmpty()||itemList.size()==0){
							return AppMsgResult.result(543,"商品信息传入参数有误！",null);
						}

						//获取页面传输过来的最终支付总价（前端已处理扣除爱心值最终支付价）
						 BigDecimal payment_old = order.getPayment();
							if(null==payment_old){
								return AppMsgResult.result(543,"payment传入参数有误！",null);
							}
						
						 //获取订单物流信息
						TzOrderShipping tzOrderShipping = order.getOrderShipping();
						if(null==tzOrderShipping){
							return AppMsgResult.result(543,"传入参数有误！",null);
						}else if(StringUtils.isBlank(tzOrderShipping.getReceiverName())){
						//判断收货人信息参数是否为空
							return AppMsgResult.result(543, "收货人姓名参数null！！", null);
						}else if(StringUtils.isBlank(tzOrderShipping.getReceiverMobile())){
							return AppMsgResult.result(543, "收货人电话参数null！！", null);
						}else if(StringUtils.isBlank(tzOrderShipping.getReceiverState())){
							return AppMsgResult.result(543, "收货人省份参数null！！", null);
						}else if(StringUtils.isBlank(tzOrderShipping.getReceiverDistrict())){
							return AppMsgResult.result(543, "收货人区域参数null！！", null);
						}else if(StringUtils.isBlank(tzOrderShipping.getReceiverCity())){
							return AppMsgResult.result(543, "收货人城市参数null！！", null);
						}else if(StringUtils.isBlank(tzOrderShipping.getReceiverAddress())){
							return AppMsgResult.result(543, "收货详细地址参数null！！", null);
						}
						   //1.生成订单id
							String orderId = IDUtils.genId();
						    Date date = new Date();
							//查询校验商品需付款金额
							BigDecimal cash=new BigDecimal(0); //不含总邮费
							BigDecimal payment=new BigDecimal(0); //实际最终付款
							BigDecimal postFee=new BigDecimal(0);//总邮费
							BigDecimal paySingleItem=new BigDecimal(0);//生成单品的总费用
							int  totalNum=0; //总件数
							int  comboNum=0; //订单中套餐总数
							
							
							List<TzOrderItem> orderItemList= new ArrayList<TzOrderItem>();
							//获取商品id
							 ArrayList<String> itemIds = new ArrayList<String>();
							 
							//求出套餐商品的分类id
							 int cid = orderMapperVo.queryItemCategoryId();
							
						//对订单中的商品逐一查询计算
						for (TzItem tzItem : itemList) {
							//购买数量
							Integer num = tzItem.getNum();
							if(null==num || num<=0){
								return AppMsgResult.result(543,"商品数量传入参数有误！",null);
							}
							String itemId = tzItem.getId();
							if(StringUtils.isBlank(itemId)){
								return AppMsgResult.result(543,"商品id传入参数有误！",null);
							}
							itemIds.add(itemId);
							 //去数据库查询该商品信息
							TzItem item = orderMapperVo.selectItemById(itemId);
							//设置订单商品id
							TzOrderItem tzOrderItem = new TzOrderItem();
							tzOrderItem.setId(IDUtils.genId());
							tzOrderItem.setOrderId(orderId);
							tzOrderItem.setItemId(itemId);
							tzOrderItem.setNum(num);
							tzOrderItem.setItemTitle(item.getItemTitle());
							tzOrderItem.setDescription(item.getDescription());
							tzOrderItem.setHomepageUrl(item.getHomepageUrl());
							tzOrderItem.setPrice(item.getPrice());
							tzOrderItem.setMemberPrice(item.getMemberPrice());
							tzOrderItem.setCreatedTime(date);
							tzOrderItem.setUpdatedTime(date);
							
							 //获取该用户商品的实际单价
						    BigDecimal realPrice = this.getRealPrice(item,userType);
							tzOrderItem.setRealPrice(realPrice);
							BigDecimal itemPost = item.getItemPost();
							tzOrderItem.setItemPost(itemPost);
							//不含邮费该商品支付实际价钱 = 会员等级超级会员价*num
							BigDecimal itemcash = realPrice.multiply(new BigDecimal(num));
							//计算该商品需付款价钱 （含该商品邮费）
							tzOrderItem.setTotalFee(itemcash.add(itemPost).setScale(2, BigDecimal.ROUND_HALF_UP));
							//总数量
							totalNum=totalNum+num;
							//求订单中套餐的数量
							if(item.getCategoryId().equals(cid+"")){
								comboNum = comboNum+num;
							}
							//求订单中单品的总费用(不包含其他费用)
							if(!item.getCategoryId().equals(cid+"")){
								paySingleItem = paySingleItem.add(itemcash);
								System.out.println("单品商品的支付价钱：=========="+paySingleItem);
							}
							//求总邮费
							postFee=postFee.add(itemPost);
							//不含邮费该商品支付总价钱
							cash=cash.add(itemcash);
							orderItemList.add(tzOrderItem);
						}
						
						 //商品总价超过300元包邮
						 if(cash.compareTo(new BigDecimal(300))==1){
							 postFee=new BigDecimal(0);
						 }
						 /**
						  * 获取折扣信息
						  */
						 order.setDiscount(new BigDecimal(0));
						 order.setDiscountDesc("暂无优惠券可以使用！");
						 //打折计算 默认0折
						 BigDecimal money = cash.add(postFee).subtract(cash.multiply(new BigDecimal(0)));
						 
						 //判断爱心值是否超出剩余爱心值
						 TzUserLove userLove = null;
						//判断是否消费了用户剩余爱心值
						if(consumeVal==0){
						  //未消费剩余爱心值(不需要 查询剩余爱心值 不修改剩余爱心值，不记录剩余爱心值消费记录)
							payment=money;
							System.out.println("该用户最终支付金额-------------------："+payment);
							
						}else if(consumeVal>0){
							//消费了剩余爱心值
							//查询用户剩余爱心值
							userLoveExample = new TzUserLoveExample();
							userLoveCriteria = userLoveExample.createCriteria();
							userLoveCriteria.andUserIdEqualTo(userId);
							List<TzUserLove>  userLoveList = userLoveMapper.selectByExample(userLoveExample);
							  userLove=userLoveList.get(0);
							if(null==userLove){
								userLove = new TzUserLove();
								userLove.setLoveSurplus(0); //未找到该用户 剩余爱心值默认0
							}
							Integer loveValue = userLove.getLoveSurplus();
							System.out.println("该用户剩余爱心值-------------："+loveValue);
							if(consumeVal.intValue()>loveValue.intValue()){
								return  AppMsgResult.result(543,"用户爱心值不够，请充值！",null);
							}
							//抵扣的爱心值不能大于商品实际支付价钱
							if(money.intValue()<consumeVal){
								return AppMsgResult.result(557,"爱心值支付不能大于商品实际支付总金额！",null);
							}
							//扣除使用抵扣的爱心值 1:1
							payment = money.subtract(new BigDecimal(consumeVal));
							System.out.println("该用户最终支付金额-------------------："+payment);
							
						}
						
						 //判断页面支付价与后台支付总价是否一致
						 if(payment.compareTo(payment_old)!=0){
							 return AppMsgResult.result(556,"支付总价数据异常,请稍后再试！",null);
						 }
						 
						//补全生成订单
						order.setId(orderId);
						order.setUserId(userId);
						//设置订单状态
						//当爱心值全部抵扣了商品支付价后可以直接发货了
						if(payment.compareTo(new BigDecimal("0"))==0 && consumeVal>0){
							order.setStatus(2);
							//支付方式--爱心值支付
							order.setPaymentType(4);
							order.setPaymentTime(date);
						 //实际支付金额大于0
						}else if(payment.compareTo(new BigDecimal("0"))==1){
							//默认订单状态未付款
							order.setStatus(1);
						}else{
							 return AppMsgResult.result(556,"支付总价数据异常,请稍后再试！",null);
						}
						order.setMallId(common.getMallId());
						order.setPayment(payment.setScale(2,BigDecimal.ROUND_HALF_UP));  //实际支付总金额
						order.setOrderNum(totalNum);  //订单总的商品件数
						order.setComboNum(comboNum);  //订单中套餐数量
						order.setPostFee(postFee.setScale(2,BigDecimal.ROUND_HALF_UP));  //订单总支付邮费
						order.setPaySingleItem(paySingleItem.setScale(2,BigDecimal.ROUND_HALF_UP)); //支付单品的总费用（不包含其他金额）
						order.setCreatedTime(date);
						order.setUpdatedTime(date);
						if(type==0){
							//会员升级订单 order表默认属性type=1
							order.setType(0);
						}
						//2、向订单表插入数据。
						int i = orderMapper.insertSelective(order);
						if(i!=1){
							return  AppMsgResult.result(559,"操作异常,请稍后重试！",null);
						}
						//3批量插入订单商品明细表
						orderMapperVo.batchInsertItemData(orderItemList);
						
						 //4、向订单物流表插入数据
						//设置订单id
						tzOrderShipping.setOrderId(orderId);
						tzOrderShipping.setCreatedTime(date);
						tzOrderShipping.setUpdatedTime(date);
						int k = orderShippingMapper.insertSelective(tzOrderShipping);
						if(k!=1){
							return  AppMsgResult.result(559,"操作异常,请稍后重试！",null);
						}
						
						//5.批量修改订单商品的销售额
						orderMapperVo.batchAlterSalesNum(itemList);
						
						//6.删除购物车该商品数据
						orderMapperVo.batchDeleteCartData(userId,itemIds);
						//7.清空该用户购物车缓存
						String cartCache_key = RedisCache.CAHCENAME+"|selectCartList|"+userId;
						//直接删除未判断空
						cache.deleteCache(cartCache_key);
						//8.清空该用户在缓存中的订单信息
						this.deleteOrderCache(userId);
						
						
						//9.减少该用户的剩余爱心值(判断该用户是否存在的剩余爱心值)
						if(consumeVal>0){
							//获取用户剩余爱心值(表中不存在该用户剩余爱心值默认为0)
							Integer loveValue = userLove.getLoveSurplus();
							//计算扣除消费的爱心值后，修改剩余爱心值
							int loveValue_surPlus = loveValue-consumeVal;
							if(loveValue>0 && loveValue_surPlus>=0){
								//执行修改爱心值
								userLove.setUserId(userId);
								userLove.setLoveSurplus(loveValue_surPlus);
								userLove.setUpdatedTime(date);
								userLove.setLastUpdatedTime(date);
								userLoveMapper.updateByPrimaryKeySelective(userLove);
							}else{
								return  AppMsgResult.result(543,"用户爱心值不够，请充值！",null);
							}
						}
						
						//10、将爱心值消费记录写入表中
						if(order.getStatus()==2){
							   //已付款，将消费爱心值>0消费记录写入爱心值消费表中
								TzUserLoveConsumptionDetails tzUserLoveConsumptionDetails = new TzUserLoveConsumptionDetails();
								tzUserLoveConsumptionDetails.setId(IDUtils.genId());
								tzUserLoveConsumptionDetails.setUserId(userId);
								tzUserLoveConsumptionDetails.setType(1);
								tzUserLoveConsumptionDetails.setConsumeVal("-"+consumeVal.toString());
								tzUserLoveConsumptionDetails.setOrderId(orderId);
								tzUserLoveConsumptionDetails.setCreatedTime(date);
								tzUserLoveConsumptionDetails.setUpdatedTime(date);
								tzUserLoveConsumptionDetails.setStatus(0); //支出状态
								tzUserLoveConsumptionDetails.setName("商城购物消费抵扣！");
								consumptionDetailsMapper.insertSelective(tzUserLoveConsumptionDetails);
								
								//购买礼包的数量
								LOG.info("comboNum----------/comboNum"+comboNum);
								if(comboNum > 0){
									//查询用户上级是否存在推荐
									recommendExample = new TzRecommendExample(); 
									recommendCriteria = recommendExample.createCriteria();
									recommendCriteria.andUserIdEqualTo(userId);
									recommendCriteria.andIsplayEqualTo(1);
									recommendCriteria.andGradeEqualTo(1);
									//一级推荐用户爱心值增加
									List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
									if(tzRecommends1.size() > 0){
										
										//推荐用户的id
										String refereeId =  tzRecommends1.get(0).getRefereeId();
										LOG.info("refereeId----------/refereeId"+refereeId);
										userLoveExample = new TzUserLoveExample();
										userLoveCriteria = userLoveExample.createCriteria();
										userLoveCriteria.andUserIdEqualTo(refereeId);
										List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
										//判断推荐用户是否存在爱心记录数据
										if(list.size() > 0){
											TzUserLove addLove= list.get(0);
											addLove.setLastUpdatedTime(new Date());
											addLove.setLoveSurplus(addLove.getLoveSurplus()+comboNum*common.getGradeOne());
											addLove.setLoveTotal(addLove.getLoveTotal()+comboNum*common.getGradeOne());
											userLoveMapper.updateByPrimaryKeySelective(addLove);	
										}
										//添加消费记录
										TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
										consumptionDetails.setId(IDUtils.genId());
										consumptionDetails.setUserId(refereeId);
										consumptionDetails.setConsumeVal("+"+comboNum*common.getGradeOne());
										consumptionDetails.setCreatedTime(new Date());
										consumptionDetails.setUpdatedTime(new Date());
										consumptionDetails.setType(4);
										consumptionDetails.setOrderId(orderId);
										consumptionDetails.setName("推荐用户礼包购买！");
										consumptionDetails.setStatus(0);
										consumptionDetailsMapper.insertSelective(tzUserLoveConsumptionDetails);
									}
									System.out.println("调用成功");
								}
								BigDecimal newPaySingleItem = order.getPaySingleItem();
								//遍历上级城市爱心合伙人 5%的单品提成
								//单品数量是否大于0
								if(newPaySingleItem.intValue()  > 0){
									//商城id
								    String mallId = common.getMallId();
								    //统计
									int addCount = 0;
									boolean firstFlag = true;
									//查询上级的用户id
									String re_userId = userId;
									TzCityUserConsumer cityUserConsumer = new TzCityUserConsumer();
									while(true){
										 try {
											 //休息1秒再来 防止cpu占用率过高
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										//当前用户
										if(firstFlag){
											Map<String,Object> map = new HashMap<String, Object>();
										    map.put("mallId", mallId);
										    map.put("userId", re_userId);
										    //查询用户类型
										    UserVo2 userVo = userMapperVo.selectUserAndMailInner(map);
										    if(null != userVo){
										       if(userVo.getType() == 4){
										    	   //为用户添加信息
										    	   cityUserConsumer.setId(IDUtils.genId());
										    	   cityUserConsumer.setOrderId(orderId);
										    	   cityUserConsumer.setOrderUserId(userId);
										    	   cityUserConsumer.setUserId(re_userId);
										    	   cityUserConsumer.setCreatedTime(new Date());
										    	   cityUserConsumer.setUpdatedTime(new Date());
										    	   cityUserConsumerMapper.insertSelective(cityUserConsumer);
										    	   addCount++;
										        }
										      }
										   firstFlag = false;
										}else{
											recommendExample = new TzRecommendExample(); 
											recommendCriteria = recommendExample.createCriteria();
											recommendCriteria.andUserIdEqualTo(re_userId);
											recommendCriteria.andIsplayEqualTo(1);
											recommendCriteria.andGradeEqualTo(1);
											//一级推荐用户爱心值增加
											List<TzRecommend> tzRecommends = recommendMapper.selectByExample(recommendExample); 	
											//上级是否存在用户
											if(tzRecommends.size()>0){
												TzRecommend recommend =  tzRecommends.get(0);
												re_userId = recommend.getRefereeId();
												Map<String,Object> map = new HashMap<String, Object>();
											    map.put("mallId", mallId);
											    map.put("userId", re_userId);
											    //查询用户类型
											    UserVo2  userVo = userMapperVo.selectUserAndMailInner(map);
											    if(null != userVo){
											       if(userVo.getType() == 4){
											    	   //为用户添加信息
											    	   cityUserConsumer.setId(IDUtils.genId());
											    	   cityUserConsumer.setOrderId(orderId);
											    	   cityUserConsumer.setOrderUserId(userId);
											    	   cityUserConsumer.setUserId(re_userId);
											    	   cityUserConsumer.setCreatedTime(new Date());
											    	   cityUserConsumer.setUpdatedTime(new Date());
											    	   cityUserConsumerMapper.insertSelective(cityUserConsumer);
											    	   addCount++;
											        }
											      }
											}else{
												break;
											}
										}
										//返两个城市爱心合伙人  终止
										if(addCount == 2){
											break;
										}
									}
								}
								
						}
						
						
						//设置未付款订单支付有效时期
						if(order.getStatus()==1){
							String payOrderCache_key = RedisCache.CAHCENAME+"|payOrderToken|"+orderId;
							String token = IDUtils.md5(userId+new Date());
							cache.putCacheWithExpireTime(payOrderCache_key, token,RedisCache.PAYTIME);
						}
						
						//订单生成后，返回该订单id
						result = AppMsgResult.result(200, "success", orderId);
						
					}else{
						result = AppMsgResult.result(557, "长时间未下单，页面数据已近失效，请重新选择下单！",null);
					}
				}else{
					result = AppMsgResult.result(538, "用户未登录", null);
				}
			}else{
				result = AppMsgResult.result(542,"用户id为null",null);
			}
			
		return result;
	}

	//校验订单生成令牌是否过期
	private AppMsgResult checkOrderToken(String userId, String orderToken) {
		AppMsgResult msgResult = null; 
		if(StringUtil.isNotEmpty(orderToken)){
			//获取用户的登录 token
			 String cache_key=RedisCache.CAHCENAME+"|getOrderToken|"+userId;
			String orderToken_cache = cache.getCache(cache_key, String.class);
			
			//是否存在
			if(null != orderToken_cache && orderToken.equals(orderToken_cache)){
				//清除token
				cache.deleteCache(cache_key);
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
	 * 根据订单id查询订单详情信息
	 */
	@Override
	public AppMsgResult queryOrderById(String orderId,String userId,String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isNotEmpty(userId)){
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				if(StringUtils.isNotBlank(orderId)){
					//从订单-订单商品-收货人中查询订单详情
					TzOrderVo orderVo = orderMapperVo.queryOrderById(orderId);
					//返回结果
					result = AppMsgResult.result(200, "success", orderVo);
				}else{
					result = AppMsgResult.result(543, "订单id为null", null);
				}
				
			}else{
				result = AppMsgResult.result(538, "用户未登录！",null);
			}
			
		}else{
			result = AppMsgResult.result(548, "userId为null",null);
		}
		return result;
	}

	
	/**
	 * 根据用户id分页查询订单列表信息
	 * @param userId
	 * @param curPage 
	 * @param rows
	 * @param status //订单状态  0、取消订单，1、未付款，2、已付款待发货、3、已发货，4、交易成功，5、交易关闭,6、已退款
	 * @param userToken
	 * @return
	 */
	@Override
	public AppMsgResult queryOrderByUserId(String userId, Integer status,String userToken,Integer curPage, Integer rows) {
			AppMsgResult result=null;
			if(StringUtils.isNotEmpty(userId)){
				//判断用户是否登录或者登录是否过期
				AppMsgResult msgResult = checkUserToken(userId, userToken);
				if((boolean)msgResult.getFlag()){
					//设置分页条件
					rows = rows == null?20:rows;
					curPage = curPage == null?1:curPage;
					//分页处理
//					PageHelper.startPage(curPage, rows);
					//设置查询条件封装条件 map集合
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("userId", userId);
					map.put("status", status);
					List<TzOrderList> orderList=null;
					//判断订单状态
					if(status==null){
						//从缓存中查询全部订单列表信息
						String orderCache_key = RedisCache.CAHCENAME+"|selectOrderList|"+userId;
						orderList= cache.getListCache(orderCache_key, TzOrderList.class);
						if(orderList!=null){
							//遍历查询所有的订单，查询未支付的订单
							for (TzOrderList order : orderList) {
								int sta = order.getStatus();
								String orderId = order.getOrderId();
								if(sta==1){
									//未支付订单需要校验是否过期
									AppMsgResult checkPayResult = this.checkPayOrder(userId, orderId);
									if(!(boolean) checkPayResult.getFlag()){
										//未支付订单无效修改状态 交易关闭
										order.setStatus(5);
									}
								}
							}
							LOG.info("get cache with key:"+orderCache_key);
							
						}else{
							//从数据库订单列表中查询该用户订单信息，并存入缓存中(未分页)
							orderList = orderMapperVo.queryOrderByUserId(map);
							//进行分页操作
							// PageInfo<TzOrderVo> pageInfo = new PageInfo<TzOrderVo>(orderList_cache);
							if(orderList.size()>0){
								//查询该用户未支付订单的信息，判断支付是否有效,无效修改该订单的状态，有效返回
								//遍历查询所有的订单，查询未支付的订单
								for (TzOrderList order : orderList) {
									int sta = order.getStatus();
									String orderId = order.getOrderId();
									if(sta==1){
										//未支付订单需要校验是否过期
										AppMsgResult checkPayResult = this.checkPayOrder(userId, orderId);
										if(!(boolean) checkPayResult.getFlag()){
											//未支付订单无效修改状态 交易关闭
											order.setStatus(5);
										}
									}
								}
								
								//将数据存入缓存中
								cache.putListCacheWithExpireTime(orderCache_key, orderList, RedisCache.CAHCETIME);
								LOG.info("put cache with key:"+orderCache_key);
							}
						}
					}else{
						//其他订单状态直接从数据库order列表中  状态-条件分页查询 不存缓存redis
						orderList = orderMapperVo.queryOrderByUserId(map);
						if(status==1 && orderList.size()>0){
							//遍历查询所有的订单，查询未支付的订单
							for (TzOrderList order : orderList) {
								String orderId = order.getOrderId();
									//未支付订单需要校验是否过期
									AppMsgResult checkPayResult = this.checkPayOrder(userId, orderId);
									if(!(boolean) checkPayResult.getFlag()){
										//未支付订单无效修改状态 交易关闭
										order.setStatus(5);
									}
								}
							}
					}
					//直接返回该用户订单列表信息
					result = AppMsgResult.result(200,"success",orderList);
					
				}else{
					result = AppMsgResult.result(538, "用户未登录！", null);
				}
				
			}else{
				result = AppMsgResult.result(548, "userId为null",null);
			}
				
			
		return result;
	}

	
	
	
	//根据订单id修改订单状态(0、取消订单, 4、确认收货)
	@Override
	public AppMsgResult alertOrderStatus(String userId,String orderId, Integer status,String userToken) {
		AppMsgResult result=null;
		if(StringUtils.isNotEmpty(userId)){
			//判断用户是否登录或者登录是否过期
			AppMsgResult msgResult = checkUserToken(userId, userToken);
			if((boolean)msgResult.getFlag()){
				if(StringUtils.isBlank(orderId)){
					return AppMsgResult.result(543, "orderId参数有误！！", null);
				}
				TzOrder tzOrder = orderMapper.selectByPrimaryKey(orderId);
				if(null==tzOrder){
					return AppMsgResult.result(551, "该订单不存在！", null);
				}
				if(status!=0 && status!=4 || null==status){
					return AppMsgResult.result(543, "status参数有误！！", null);
				}
			 if(status==0){
				    //判断订单必须为未付款状态才能取消订单
				    if(tzOrder.getStatus()!=1){
				    	result = AppMsgResult.result(548,"操作异常,请稍后重试！",null);
				    }
					//取消订单操作，需要返回该用户的爱心值 获取订单表中被扣除的爱心值,取消支付payOrderToken
				    String payOrderCache_key = RedisCache.CAHCENAME+"|payOrderToken|"+orderId;
				    cache.deleteCache(payOrderCache_key);
					Integer loveValue = tzOrder.getLoveValue();
					if(loveValue>0){
						//扣除爱心值超过0才返还爱心值
						TzUserLove userLove = new TzUserLove();
						userLove.setUserId(userId);
						userLove.setLoveSurplus(loveValue);
						userLove.setUpdatedTime(new Date());
						//增加该用户剩余爱心值
						orderMapperVo.addLoveSurplus(userLove);
					}
					
				}else if(status==4){
					//判断订单必须为已发货状态才能修改为确认收货
				    if(tzOrder.getStatus()!=3){
				    	result = AppMsgResult.result(548,"操作异常,请稍后重试！",null);
				    }
				}
				tzOrder.setStatus(status);
				tzOrder.setUpdatedTime(new Date());
				tzOrder.setEndTime(new Date());
				int i = orderMapper.updateByPrimaryKeySelective(tzOrder);
				if(i==1){
					result = AppMsgResult.result(200,"订单操作成功！",null);
				}else{
					result = AppMsgResult.result(548,"操作异常,请稍后重试！",null);
				}
				//清空该用户缓存中的订单信息
				this.deleteOrderCache(userId);
				
			}else{
				result = AppMsgResult.result(538, "用户未登录！", null);
			}
		}else{
			result = AppMsgResult.result(548, "userId为null",null);
		}
		return result;
	}

	/**
	 * 对未支付订单校验payOrderCache_key 是否存在 存在保持未支付，不存在设置为交易关闭status=5
	 */
	@Override
	public AppMsgResult checkPayOrder(String userId, String orderId) {
		AppMsgResult result=null;
		//查询缓存
		String payOrderCache_key = RedisCache.CAHCENAME+"|payOrderToken|"+orderId;
		String payOrderCache = cache.getCache(payOrderCache_key, String.class);
		if(payOrderCache!=null){
			//未支付订单有效
			result = AppMsgResult.nodata(true,"success");
		}else{
			Date date = new Date();
			//订单支付无效，修改为订单关闭状态，返回爱心值
			TzOrder tzOrder = orderMapper.selectByPrimaryKey(orderId);
			tzOrder.setId(orderId);
			tzOrder.setStatus(5);
			tzOrder.setUpdatedTime(date);
			tzOrder.setCloseTime(date);
			//更改订单状态
			orderMapper.updateByPrimaryKeySelective(tzOrder);
			//清空我的订单缓存
			this.deleteOrderCache(userId);
			//获取爱心值
			Integer loveValue = tzOrder.getLoveValue();
			if(loveValue>0){
				//扣除爱心值超过0才返还爱心值
				TzUserLove userLove = new TzUserLove();
				userLove.setUserId(userId);
				userLove.setLoveSurplus(loveValue);
				userLove.setUpdatedTime(new Date());
				//增加该用户剩余爱心值
				orderMapperVo.addLoveSurplus(userLove);
			}
			
			result= AppMsgResult.nodata(false,"failure");
			
		}
		return result;
	}

	
	
}

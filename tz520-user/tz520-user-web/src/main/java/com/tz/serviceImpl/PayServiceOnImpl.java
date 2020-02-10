package com.tz.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzCityUserConsumerMapper;
import com.tz.mapper.TzOrderMapper;
import com.tz.mapper.TzRecommendMapper;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.TzUserMallMapper;
import com.tz.mapper.TzUserPayLogMapper;
import com.tz.mapper.vo.TzUserMapperVo;
import com.tz.pojo.TzCityUserConsumer;
import com.tz.pojo.TzCityUserConsumerExample;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderExample;
import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzRecommendExample;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import com.tz.pojo.TzUserPayLog;
import com.tz.pojo.TzUserPayLogExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserVo2;
import com.tz.res.AppMsgResult;
import com.tz.sdk.weixin.Sign;
import com.tz.sdk.weixin.WXPayConstants;
import com.tz.sdk.weixin.WXPayUtil;
import com.tz.sdk.weixin.WeChatPayConfig;
import com.tz.sdk.zfb.AlipayConfig;
import com.tz.service.PayServiceOn;

import net.sf.json.JSONObject;

@Service
public class PayServiceOnImpl implements PayServiceOn {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	//用户支付记录表
	@Autowired TzUserPayLogMapper userPayLogMapper ;	
	private TzUserPayLogExample userPayLogExample;
	private TzUserPayLogExample.Criteria criteria2;
	
	//用户商城中间类
	@Autowired TzUserMallMapper userMallMapper ;	
	private TzUserMallExample userMallExample;
	private TzUserMallExample.Criteria criteria;
	
	//用户总的爱心值类
	@Autowired 
	private TzUserLoveMapper userLoveMapper;	
	private TzUserLoveExample userLoveExample;
	private TzUserLoveExample.Criteria criteria3;
	//用户订单
	@Autowired 
	private TzOrderMapper orderMapper;	
	private TzOrderExample orderExample;
	private TzOrderExample.Criteria criteria4;
	
	//用户爱心值消费类
	@Autowired 
	TzUserLoveConsumptionDetailsMapper consumptionDetailsMapper;	
	private TzUserLoveConsumptionDetailsExample consumptionDetailsExample;
	private TzUserLoveConsumptionDetailsExample.Criteria criteria5;	
	
	//用户推荐类
	@Autowired 
	TzRecommendMapper recommendMapper;	
	private TzRecommendExample recommendExample;
	private TzRecommendExample.Criteria criteria6;
	
	//城市爱心合伙人单品消费记录
	@Autowired 
	TzCityUserConsumerMapper cityUserConsumerMapper;	
	private TzCityUserConsumerExample cityUserConsumerExample;
	private TzCityUserConsumerExample.Criteria criteria7;
	
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;

    //用户扩展类
    @Autowired
	private TzUserMapperVo userMapperVo;
    
    @Transactional
	public void zfbAPPnotify(HttpServletRequest request, HttpServletResponse httpResponse){
		//获取支付宝POST过来反馈信息
		LOG.info("invoke----------/zfbAPPnotify");
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                    : valueStr + values[i] + ",";
		  }
		  //乱码解决，这段代码在出现乱码时使用。
	/*	  try {
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		  params.put(name, valueStr);
		 }
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		try {
			//线上
			/*boolean flag = AlipaySignature.rsaCheckV1(params,AlipayConfig.ALIPAY_PUBLIC_KEY,"UTF-8", "RSA2");*/
			//测试
			boolean flag = AlipaySignature.rsaCheckV1(params,AlipayConfig.CS_ALIPAY_PUBLIC_KEY,"UTF-8", "RSA2");
			
			if (flag) {// 验证成功		
				 if("TRADE_SUCCESS".equals(params.get("trade_status"))){  
					//用户支付的账号
				   String buyer_logon_id =params.get("buyer_logon_id");
				   LOG.info("buyer_logon_id----------/buyer_logon_id"+buyer_logon_id);
					//付款金额  
		           String amount = params.get("buyer_pay_amount");  
		           LOG.info("amount----------/amount"+amount);
	               //商户订单号  
	               String out_trade_no = params.get("out_trade_no");
	               LOG.info("out_trade_no----------/amount"+out_trade_no);
//	               //支付宝交易号  
	               String trade_no = params.get("trade_no");
	               LOG.info("trade_no----------/amount"+trade_no);
	               //交易支付时间  
	               String gmt_payment = params.get("gmt_payment");
	               LOG.info("gmt_payment----------/amount"+gmt_payment);
	               //根据不同类型来进行操作
	               //查询支付订单
	               TzUserPayLog userPayLog = userPayLogMapper.selectByPrimaryKey(out_trade_no);  
	               String userId = userPayLog.getUserId();
	               int buyType = userPayLog.getBuyType();
	               //未支付成功
	               if(userPayLog.getStatus() !=1){
	            	   //付款时间
	            	   Date PayTime = DateUtile.StringToDate(gmt_payment);
	            	   userPayLog.setAccount(buyer_logon_id);
	            	   userPayLog.setPaymentTime(PayTime);
	            	   userPayLog.setStatus(1);
	            	   userPayLog.setTradeNo(trade_no);
	            	   userPayLog.setTotalMount(amount);
	            	   userPayLogMapper.updateByPrimaryKeySelective(userPayLog);
	            	   //会员升级
		               if(buyType == 0){	   
		            	userMallExample = new TzUserMallExample(); 
		   				criteria = userMallExample.createCriteria();
		   				criteria.andUserIdEqualTo(userId);
		   				//商城id
				    	String mallId = common.getMallId();
		   				criteria.andMallIdEqualTo(mallId);
		   				//修改用户会员状态
		   				List<TzUserMall> userMalls =  userMallMapper.selectByExample(userMallExample);
		   				TzUserMall userMall = userMalls.get(0);
		   				if(userMall.getType()==0){
		   					userMall.setType(1);
		   				}
		   				userMall.setPayWay(1);
		   				userMall.setPrice(amount);
		   				userMall.setPayAccount(buyer_logon_id);
		   				userMall.setUpdatedTime(new Date());
		   				userMall.setStartTime(PayTime);
		   				userMall.setEndTime(DateUtile.getPutOffYear(PayTime));
		   				userMallMapper.updateByPrimaryKeySelective(userMall);
		   				String orderId = userPayLog.getOrderId();
						//订单成功
		   				TzOrder order = new TzOrder();
						order.setStatus(2);
						order.setPaymentTime(PayTime);
						order.setPaymentType(1);
						order.setId(orderId);
						orderMapper.updateByPrimaryKeySelective(order);
						cache.deleteCache(RedisCache.CAHCENAME+"|selectOrderList|"+userId);
						cache.deleteCache(RedisCache.CAHCENAME+"|payOrderToken|"+orderId);
						//给用户增加爱心值数据记录
						//会员如果是推荐的会员那么给前面的数据置为已开通
						recommendExample = new TzRecommendExample(); 
						criteria6 = recommendExample.createCriteria();
						criteria6.andUserIdEqualTo(userId);
						//上级是否存在推荐
						List<TzRecommend> TzRecommends = recommendMapper.selectByExample(recommendExample);
						if(TzRecommends.size() > 0){
							TzRecommend record = new TzRecommend();
							record.setUserId(userId);
							record.setIsplay(1);
							record.setFeedbackStatus(1);
							record.setFeedbackTime(PayTime);
							recommendMapper.updateByExampleSelective(record, recommendExample);
							//添加爱心值
							recommendExample = new TzRecommendExample(); 
							criteria6 = recommendExample.createCriteria();
							criteria6.andUserIdEqualTo(userId);
							criteria6.andIsplayEqualTo(1);
							criteria6.andGradeEqualTo(1);
							//一级推荐用户爱心值增加
							List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
							if(tzRecommends1.size() > 0){
								//推荐用户的id
								String refereeId =  tzRecommends1.get(0).getRefereeId();
								userLoveExample = new TzUserLoveExample();
								criteria3 = userLoveExample.createCriteria();
								criteria3.andUserIdEqualTo(refereeId);
								List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
								//判断推荐用户是否存在爱心记录数据
								if(list.size()> 0){
									TzUserLove addLove= list.get(0);
									addLove.setLastUpdatedTime(new Date());
									addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeOne());
									addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeOne());
									userLoveMapper.updateByPrimaryKeySelective(addLove);
								}
								//添加消费记录
								TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
								consumptionDetails.setId(IDUtils.genId());
								consumptionDetails.setUserId(refereeId);
								consumptionDetails.setConsumeVal("+"+common.getGradeOne());
								consumptionDetails.setCreatedTime(new Date());
								consumptionDetails.setUpdatedTime(new Date());
								consumptionDetails.setType(4);
								consumptionDetails.setOrderId(orderId);
								consumptionDetails.setName("520商城推荐用户升级会员！");
								consumptionDetails.setStatus(0);
								consumptionDetailsMapper.insertSelective(consumptionDetails);
							}
							recommendExample = new TzRecommendExample(); 
							criteria6 = recommendExample.createCriteria();
							criteria6.andUserIdEqualTo(userId);
							criteria6.andIsplayEqualTo(1);
							criteria6.andGradeEqualTo(2);
							//二级推荐用户爱心值增加
							List<TzRecommend> tzRecommends2 = recommendMapper.selectByExample(recommendExample);
							if(tzRecommends2.size() > 0){
								//推荐用户的id
								String refereeId =  tzRecommends2.get(0).getRefereeId();
								userLoveExample = new TzUserLoveExample();
								criteria3 = userLoveExample.createCriteria();
								criteria3.andUserIdEqualTo(refereeId);
								List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
								//判断推荐用户是否存在爱心记录数据
								if(list.size()> 0){
									TzUserLove addLove= list.get(0);
									addLove.setLastUpdatedTime(new Date());
									addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeTwo());
									addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeTwo());
									userLoveMapper.updateByPrimaryKeySelective(addLove);
								}
								//添加消费记录
								TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
								consumptionDetails.setId(IDUtils.genId());
								consumptionDetails.setUserId(refereeId);
								consumptionDetails.setConsumeVal("+"+common.getGradeTwo());
								consumptionDetails.setCreatedTime(new Date());
								consumptionDetails.setUpdatedTime(new Date());
								consumptionDetails.setType(4);
								consumptionDetails.setOrderId(orderId);
								consumptionDetails.setName("520商城推荐用户升级会员！");
								consumptionDetails.setStatus(0);
								consumptionDetailsMapper.insertSelective(consumptionDetails);
							}
						}
						//购物
		               }else if(buyType == 1){
		            	    //付款时间
		            	    Date PayTime1 = DateUtile.StringToDate(gmt_payment);
		            	    userPayLog.setAccount(buyer_logon_id);
		            	    userPayLog.setPaymentTime(PayTime1);
		            	    userPayLog.setStatus(1);
		            	    userPayLog.setTradeNo(trade_no);
		            	    userPayLog.setTotalMount(amount);
		            	    userPayLogMapper.updateByPrimaryKeySelective(userPayLog);
	            	   
		            	   	String orderId = userPayLog.getOrderId();
		            	   	TzOrder order = orderMapper.selectByPrimaryKey(orderId);
		            	   	if(null != order){
		            	   		//用户抵扣的爱心值
		            	   	  	int consumeVal = order.getLoveValue();
			            	   	if(consumeVal > 0){
									//添加消费记录
									TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
									consumptionDetails.setUserId(userId);
									consumptionDetails.setId(IDUtils.genId());
									consumptionDetails.setConsumeVal("-"+consumeVal);
									consumptionDetails.setCreatedTime(new Date());
									consumptionDetails.setUpdatedTime(new Date());
									consumptionDetails.setType(1);
									consumptionDetails.setShowType(0);
									consumptionDetails.setOrderId(orderId);
									consumptionDetails.setName("520商城购物消费抵扣！");
									consumptionDetails.setStatus(0);
									consumptionDetailsMapper.insertSelective(consumptionDetails);
			            	   	}
								//订单成功
			            	   	order.setPaymentType(1);
								order.setStatus(2);
								order.setPaymentTime(PayTime1);
								orderMapper.updateByPrimaryKeySelective(order);
								cache.deleteCache(RedisCache.CAHCENAME+"|selectOrderList|"+userId);
								cache.deleteCache(RedisCache.CAHCENAME+"|payOrderToken|"+orderId);
								int comboNum = order.getComboNum();
								BigDecimal paySingleItem = order.getPaySingleItem();
								LOG.info("paySingleItem----------/paySingleItem"+paySingleItem);
								LOG.info("comboNum----------/comboNum"+comboNum);
								if(comboNum > 0){
									//第一次购买礼包
									userMallExample = new TzUserMallExample(); 
					   				criteria = userMallExample.createCriteria();
					   				criteria.andUserIdEqualTo(userId);
					   				//商城id
							    	String mallId = common.getMallId();
					   				criteria.andMallIdEqualTo(mallId);
					   				//修改用户会员状态
					   				List<TzUserMall> userMalls =  userMallMapper.selectByExample(userMallExample);
					   				TzUserMall userMall = userMalls.get(0);
					   				//判断是否是第一次购买礼包
					   				if(userMall.getType() == 0){
					   					userMall.setType(1);
					   					userMall.setPayWay(1);
						   				userMall.setPrice(amount);
						   				userMall.setPayAccount(buyer_logon_id);
						   				userMall.setUpdatedTime(new Date());
						   				userMall.setStartTime(PayTime);
						   				userMall.setEndTime(DateUtile.getPutOffYear(PayTime));
						   				userMallMapper.updateByPrimaryKeySelective(userMall);
						   				//走推荐流程
										//会员如果是推荐的会员那么给前面的数据置为已开通
										recommendExample = new TzRecommendExample(); 
										criteria6 = recommendExample.createCriteria();
										criteria6.andUserIdEqualTo(userId);
										//上级是否存在推荐
										List<TzRecommend> TzRecommends = recommendMapper.selectByExample(recommendExample);
										if(TzRecommends.size() > 0){
											TzRecommend record = new TzRecommend();
											record.setUserId(userId);
											record.setIsplay(1);
											record.setFeedbackStatus(1);
											record.setFeedbackTime(PayTime);
											recommendMapper.updateByExampleSelective(record, recommendExample);
											//添加爱心值
											recommendExample = new TzRecommendExample(); 
											criteria6 = recommendExample.createCriteria();
											criteria6.andUserIdEqualTo(userId);
											criteria6.andIsplayEqualTo(1);
											criteria6.andGradeEqualTo(1);
											//一级推荐用户爱心值增加
											List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
											if(tzRecommends1.size() > 0){
												//推荐用户的id
												String refereeId =  tzRecommends1.get(0).getRefereeId();
												userLoveExample = new TzUserLoveExample();
												criteria3 = userLoveExample.createCriteria();
												criteria3.andUserIdEqualTo(refereeId);
												List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
												//判断推荐用户是否存在爱心记录数据
												if(list.size()> 0){
													TzUserLove addLove= list.get(0);
													addLove.setLastUpdatedTime(new Date());
													addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeOne());
													addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeOne());
													userLoveMapper.updateByPrimaryKeySelective(addLove);
												}
												//添加消费记录
												TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
												consumptionDetails.setId(IDUtils.genId());
												consumptionDetails.setUserId(refereeId);
												consumptionDetails.setConsumeVal("+"+common.getGradeOne());
												consumptionDetails.setCreatedTime(new Date());
												consumptionDetails.setUpdatedTime(new Date());
												consumptionDetails.setType(4);
												consumptionDetails.setOrderId(orderId);
												consumptionDetails.setName("520商城推荐用户升级会员！");
												consumptionDetails.setStatus(0);
												consumptionDetailsMapper.insertSelective(consumptionDetails);
											}
											recommendExample = new TzRecommendExample(); 
											criteria6 = recommendExample.createCriteria();
											criteria6.andUserIdEqualTo(userId);
											criteria6.andIsplayEqualTo(1);
											criteria6.andGradeEqualTo(2);
											//二级推荐用户爱心值增加
											List<TzRecommend> tzRecommends2 = recommendMapper.selectByExample(recommendExample);
											if(tzRecommends2.size() > 0){
												//推荐用户的id
												String refereeId =  tzRecommends2.get(0).getRefereeId();
												userLoveExample = new TzUserLoveExample();
												criteria3 = userLoveExample.createCriteria();
												criteria3.andUserIdEqualTo(refereeId);
												List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
												//判断推荐用户是否存在爱心记录数据
												if(list.size()> 0){
													TzUserLove addLove= list.get(0);
													addLove.setLastUpdatedTime(new Date());
													addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeTwo());
													addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeTwo());
													userLoveMapper.updateByPrimaryKeySelective(addLove);
												}
												//添加消费记录
												TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
												consumptionDetails.setId(IDUtils.genId());
												consumptionDetails.setUserId(refereeId);
												consumptionDetails.setConsumeVal("+"+common.getGradeTwo());
												consumptionDetails.setCreatedTime(new Date());
												consumptionDetails.setUpdatedTime(new Date());
												consumptionDetails.setType(4);
												consumptionDetails.setOrderId(orderId);
												consumptionDetails.setName("520商城推荐用户升级会员！");
												consumptionDetails.setStatus(0);
												consumptionDetailsMapper.insertSelective(consumptionDetails);
											}
										}
					   				}else{
										//查询用户上级是否存在推荐
										recommendExample = new TzRecommendExample(); 
										criteria6 = recommendExample.createCriteria();
										criteria6.andUserIdEqualTo(userId);
										criteria6.andIsplayEqualTo(1);
										criteria6.andGradeEqualTo(1);
										//一级推荐用户爱心值增加
										List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
										if(tzRecommends1.size() > 0){
											
											//推荐用户的id
											String refereeId =  tzRecommends1.get(0).getRefereeId();
											 LOG.info("refereeId----------/refereeId"+refereeId);
											userLoveExample = new TzUserLoveExample();
											criteria3 = userLoveExample.createCriteria();
											criteria3.andUserIdEqualTo(refereeId);
											List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
											//判断推荐用户是否存在爱心记录数据
											if(list.size()> 0){
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
											consumptionDetails.setName("520商城推荐用户礼包购买！");
											consumptionDetails.setStatus(0);
											consumptionDetailsMapper.insertSelective(consumptionDetails);
										}
										
										System.out.println("调用成功");
					   				}
								}
								//遍历上级城市爱心合伙人 5%的单品提成
								//单品数量是否大于0
								if(paySingleItem.intValue()  > 0){
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
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										//当前用户
										if(firstFlag){
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
										   firstFlag = false;
										}else{
											recommendExample = new TzRecommendExample(); 
											criteria6 = recommendExample.createCriteria();
											criteria6.andUserIdEqualTo(re_userId);
											criteria6.andIsplayEqualTo(1);
											criteria6.andGradeEqualTo(1);
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
		               }
		               //错误的回调请求
		               }else{  
		            	   System.out.println("已经修改了订单信息！"); 
		               }  
	               }
			}else{
				LOG.info("buyer_logon_id----------/flag--"+flag);
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//支付宝手机网页支付
	public AppMsgResult zfbWebAliPay(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String userId,String type,String userToken,String orderId){	
		AppMsgResult  appMsgResult=  null;
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
		//实例化客户端 --测试
/*		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.CS_GATEWAYURL, AlipayConfig.CS_APP_ID, AlipayConfig.CS_APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.CS_ALIPAY_PUBLIC_KEY, "RSA2");
*/		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
		alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
		alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
		if(StringUtil.isNotEmpty(userId)){
			if(StringUtil.isNotEmpty(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
				String result_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != result_cache){
					//验证
					if(result_cache.equals(userToken)){
						//查询订单
		            	TzOrder order = orderMapper.selectByPrimaryKey(orderId);
		            	if(null != order){
		        			//创建订单信息
		                	TzUserPayLog userPayLog = new TzUserPayLog();
		                	String id = IDUtils.genId();
		                	userPayLog.setId(id);
		                	userPayLog.setUserId(userId);
		                	userPayLog.setPayType(1);
		                	userPayLog.setTotalMount("520");
		                	userPayLog.setBody("爱心520商城");
		                
		                	userPayLog.setStatus(0);
		                	userPayLog.setCreatedTime(new Date());
		                	userPayLog.setUpdatedTime(new Date());
							//会员升级
				            if(order.getType() == 0){
				            	//记录支付记录
				            	userPayLog.setBuyType(0);
				            	userPayLog.setOrderId(orderId);
				            	userPayLog.setSubject("爱心520商城账户会员升级,超值礼包购买！");
				            	int res = userPayLogMapper.insertSelective(userPayLog);
				            	if(res == 1){
				            		alipayRequest.setBizContent("{" +
				            				" \"out_trade_no\":\""+id+"\"," +
				            				" \"body\":\"爱心520商城！\"," +
				            				//正式
				            				/*" \"total_amount\":\""+order.getPayment()+"\"," +*/
			                				//测试
				            				" \"total_amount\":\"0.01\"," +
				            				" \"subject\":\"爱心520商城账户会员升级,超值礼包购买！\"," +
				            				" \"product_code\":\"QUICK_WAP_PAY\"" +
				            				" }");//填充业务参数
				            		//正式
			            			/*alipayRequest.setNotifyUrl(AlipayConfig.APPnotify);*/
			            			//测试
			            			alipayRequest.setNotifyUrl(AlipayConfig.CS_APPnotify);
			            			alipayRequest.setReturnUrl("http://www.520zhiai.com/weixin/index.html#/paySuccess");
			            			String form="";
			            			try {
			            				form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
			            				appMsgResult = AppMsgResult.result(200, "success！", form);
			            			} catch (AlipayApiException e) {
			            				appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
			            				e.printStackTrace();
			            			}
				            	}else{
				            		appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
				            	}
				            //购物
				            }else if(order.getType() == 1){
				            	//订单id
				            	if(StringUtil.isNotEmpty(orderId)){
				                	//记录支付记录
				            		userPayLog.setBuyType(1);
				            		userPayLog.setOrderId(orderId);
				            		userPayLog.setSubject("爱心520商城订单购物支付！");
				                	int res = userPayLogMapper.insertSelective(userPayLog);
				                	if(res == 1){
				                		alipayRequest.setBizContent("{" +
					            				" \"out_trade_no\":\""+id+"\"," +
				                				//正式
					            				/*" \"total_amount\":\""+order.getPayment()+"\"," +*/
				                				//测试
					            				" \"total_amount\":\"0.01\"," +
					            				" \"body\":\"爱心520商城！\"," +
					            				" \"subject\":\"爱心520商城订单购物支付！\"," +
					            				" \"product_code\":\"QUICK_WAP_PAY\"" +
					            				" }");//填充业务参数
				            			/*alipayRequest.setNotifyUrl(AlipayConfig.APPnotify);*/
				            			//测试
				            			alipayRequest.setNotifyUrl(AlipayConfig.CS_APPnotify);
				            			alipayRequest.setReturnUrl("http://www.520zhiai.com/weixin/index.html#/paySuccess");
				            			String form="";
				            			try {
				            				form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
				            				appMsgResult = AppMsgResult.result(200, "success！", form);
				            			} catch (AlipayApiException e) {
				            				appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
				            				e.printStackTrace();
				            			}
				                	}else{
				                		appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
				                	}
				            	}else{
			                		appMsgResult = AppMsgResult.result(548, "订单id不能为空！", null);
			                	}
				            //错误的回调请求
				            }else{
				            	appMsgResult = AppMsgResult.result(549, "请先选择支付的类型！", null);
				            } 
		            	}else{
		            		appMsgResult = AppMsgResult.result(547, "订单信息不存在，请稍后重试！", null);
		            	}
					}else{
						appMsgResult = AppMsgResult.result(538,"用户未登录！",null );
					}
				}else{
					appMsgResult = AppMsgResult.result(538,"用户未登录！",null );
				}
			}else{
				appMsgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			appMsgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		} 
        return appMsgResult;
	  }
	
	/*
     * 发起公众号支付
     *
     * @param response
     * @throws Exception
     */
	@Override
	public AppMsgResult wechatWebPAy(HttpServletRequest request,String userId,String code,String userToken,String orderId) {
		AppMsgResult  appMsgResult=  null;
		if(StringUtils.isNoneBlank(userId)){
			if(StringUtils.isNoneBlank(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
				String result_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != result_cache){
					//验证
					if(result_cache.equals(userToken)){
						//微信公众号支付预授权码
						if(StringUtils.isNoneBlank(code)){
							//微信公众号支付通过code得到  用户对应公众号的openid  code 081zMnXL0vqOj322sjUL0ubAXL0zMnXK
							String openid = Sign.getOpenId(code);
							//openid 不为空
							if(StringUtils.isNoneBlank(openid)){
								//查询订单
				            	TzOrder order = orderMapper.selectByPrimaryKey(orderId);
				            	if(null != order){
				        			//创建订单信息
				                	TzUserPayLog userPayLog = new TzUserPayLog();
				                	String id = IDUtils.genId();
				                	userPayLog.setId(id);
				                	userPayLog.setUserId(userId);
				                	userPayLog.setPayType(0);
				                	userPayLog.setTotalMount("520");
				                	userPayLog.setBody("爱心520商城");
				                
				                	userPayLog.setStatus(0);
				                	userPayLog.setCreatedTime(new Date());
				                	userPayLog.setUpdatedTime(new Date());
				                	
				                	boolean wechatpayflag = false;
									//会员升级
						            if(order.getType() == 0){
						            	//记录支付记录
						            	userPayLog.setBuyType(0);
						            	userPayLog.setOrderId(orderId);
						            	userPayLog.setSubject("爱心520商城账户会员升级,超值礼包购买！");
						            	int res = userPayLogMapper.insertSelective(userPayLog);
						            	if(res == 1){
						            		wechatpayflag = true;
						            	}else{
						            		appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
						            	}
						            //购物
						            }else if(order.getType() == 1){
						            	//订单id
						            	if(StringUtils.isNoneBlank(orderId)){
						                	//记录支付记录
						            		userPayLog.setBuyType(0);
						            		userPayLog.setOrderId(orderId);
						            		userPayLog.setSubject("爱心520商城订单购物支付！");
						                	int res = userPayLogMapper.insertSelective(userPayLog);
						                	if(res == 1){
						                		//微信支付操作
						                		wechatpayflag = true;
						                	}else{
						                		appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
						                	}
						            	}else{
					                		appMsgResult = AppMsgResult.result(548, "订单id不能为空！", null);
					                	}
						            //错误的回调请求
						            }else{
						            	appMsgResult = AppMsgResult.result(549, "请先选择支付的类型！", null);
						            } 
						            //发起支付请求
						            if(wechatpayflag){
						            	//微信支付操作	
					            		// 生成订单
					        			Map<String, String> result = new HashMap<>();
					        	        //准备发起支付的参数
					        	        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
					        	        parameters.put("appid", WeChatPayConfig.gzh_appid); // 应用ID
					        	        parameters.put("mch_id", WeChatPayConfig.gzh_mch_id); // 商户号
					        	       /* parameters.put("device_info", WeChatPayConfig.DEVICE_INFO); // 设备号
					        */	        String nonce_str = IDUtils.genId();
					        	        parameters.put("nonce_str", nonce_str); // 随机字符串
					        	        parameters.put("body", userPayLog.getSubject()); // 描述
					        	        parameters.put("out_trade_no", id); // 商户订单号
					        	        parameters.put("total_fee", order.getPayment().multiply(new BigDecimal(100)).intValue()+""); // 订单总金额，单位为分
					        	        parameters.put("spbill_create_ip", WXPayUtil.getIpAddress(request)); // 用户端实际ip
					        	      /*  parameters.put("notify_url", WeChatPayConfig.Webnotify); */// 支付成功后，回调地址 正式
					        	        parameters.put("notify_url", WeChatPayConfig.cs_Webnotify); // 支付成功后，回调地址 测试
					        	        parameters.put("trade_type", WeChatPayConfig.TRADE_TYPE_JSAPI); // 支付类型(JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MICROPAY--刷卡支付)
					        	        parameters.put("openid", openid); // trade_type=JSAPI，此参数必传
					        	        try {
					        	        	String sign = WXPayUtil.generateSignature(parameters,WeChatPayConfig.gzh_key);
					        	 	        System.out.println("参与签名的参数：" + parameters);
					        	 	        parameters.put("sign", sign);// 根据微信签名规则，生成签名

					        	 	        // 将请求参数转换为xml格式的string
					        	 	        String requestXML = WXPayUtil.generateSignedXml(parameters, WeChatPayConfig.gzh_key);

					        	 	        System.out.println("---------------------------------------------------------");
					        	 	        System.out.println(sign);
					        	 	        System.out.println("---------------------------------------------------------");
					        	 	        System.out.println(requestXML);
					        	 	        System.out.println("---------------------------------------------------------");

					        	 	        // 发送请求
					        	 	        String xmlData = WXPayUtil.httpsRequest2(WXPayConstants.DOMAIN_API+WXPayConstants.UNIFIEDORDER_URL_SUFFIX, "POST", requestXML);
					        	 	        
					        	 	        System.out.println(xmlData);
					        	 	        System.out.println("**************************");
					        				System.out.println(new String(xmlData.getBytes("UTF-8")));
					        		        // 解析XML
					        		        Map<String, String> map = WXPayUtil.xmlToMap(xmlData); 		        		        
					        		        String prepay_id = (String) map.get("prepay_id");// 获取prepay_id
					        		        
					        		        result.put("appId", (String) map.get("appid"));  
					        		        result.put("timeStamp", System.currentTimeMillis()/1000+""); 
					        		        result.put("nonceStr", UUID.randomUUID().toString()  
					        	                    .replaceAll("-", ""));  
					        		        result.put("package", "prepay_id=" + prepay_id);   
					        		        result.put("signType", "MD5");
					        		        String paySign = WXPayUtil.generateSignature(result, WeChatPayConfig.gzh_key);
					        		       /* System.out.println("paySign-----------"+WXPayUtil.isSignatureValid(paySign, WeChatPayConfig.gzh_key));*/
					        		        
					        		        result.put("paySign", paySign);
					        		       /* LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
					        		        String prepay_id = (String) map.get("prepay_id");// 获取prepay_id
					        		        params.put("appid", WeChatPayConfig.gzh_appid);
					        		        long timeStr = System.currentTimeMillis() / 1000;
					        		        params.put("timeStamp", timeStr + "");
					        		        params.put("nonceStr", nonce_str);
					        		        params.put("package", "Sign=WXPay");
					        		        params.put("partnerid", WeChatPayConfig.gzh_mch_id);
					        		        params.put("prepayid",prepay_id);
					        		        String paySign = WXPayUtil.generateSignature(params, "UTF-8");
					        		        result.put("package", "Sign=WXPay");
					        		        result.put("appid", WeChatPayConfig.gzh_appid);
					        		        result.put("partnerid", WeChatPayConfig.gzh_mch_id);
					        		        result.put("prepayid", prepay_id);
					        		        result.put("timeStamp", timeStr + "");
					        		        result.put("nonceStr", nonce_str);
					        		        result.put("signType", "MD5");*/
					        		      /*  result.put("paySign", paySign);*/
					        		        /*result.put("attach", "");*/
					        		        appMsgResult = AppMsgResult.result("200", "success", result);	
					        			} catch (Exception e) {
					        				// TODO Auto-generated catch block
					        				appMsgResult = AppMsgResult.result("568", "支付失败", null);
					        				e.printStackTrace();
					        			}
						            }
				            	}else{
				            		appMsgResult = AppMsgResult.result(547, "订单信息不存在，请稍后重试！", null);
				            	}
							}else{
								appMsgResult = AppMsgResult.result(568, "支付失败，请稍后重试，微信授权openid错误！", null);
							}
						}else{
							appMsgResult = AppMsgResult.result(567, "支付失败，请稍后重试，微信授权code错误！", null);
						}
					}else{
						appMsgResult = AppMsgResult.result(538,"用户未登录！",null );
					}
				}else{
					appMsgResult = AppMsgResult.result(538,"用户未登录！",null );
				}
			}else{
				appMsgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			appMsgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return appMsgResult;
	}
	@Override
	public AppMsgResult wechatShare(String url) {
		// TODO Auto-generated method stub
		 AppMsgResult result = null;
		 //微信的access_token
		 String access_token = "";
		//微信的jsapiTicket
		 String jsapiTicket = "";
		 //token缓存名
		 String cache_key = RedisCache.CAHCENAME+"|wechat|access_token|";
		 //jsapiTicket缓存名
		 String cache_key2 = RedisCache.CAHCENAME+"|wechat|jsapiTicket|";
		 String result_cache = cache.getCache(cache_key, String.class);
		 if(null != result_cache){
			 access_token = result_cache;
			 LOG.info("invoke----------/cache"+result_cache);
			 cache.deleteCache(cache_key2);
		 }else{
			 access_token = Sign.getAccess_token();
			 cache.putCacheWithExpireTime(cache_key, access_token, RedisCache.ACCESSTOKENTIME);
		 } 
		 //jsapiTicket缓存名
		 String result_cache2 = cache.getCache(cache_key2, String.class);
		 if(null != result_cache2){
			 jsapiTicket = result_cache2;
			 LOG.info("invoke----------/cache"+result_cache2);
		 }else{
			 jsapiTicket = Sign.getJsapiTicket(access_token);
			 cache.putCacheWithExpireTime(cache_key2, jsapiTicket, RedisCache.ACCESSTOKENTIME);
		 } 
         Map<String, String> ret = new HashMap<>();
         try {
			ret = Sign.createShareSign(jsapiTicket,access_token, url);
         } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
         }
         ret.put("appid", WeChatPayConfig.gzh_appid);
         result = AppMsgResult.result("200", "success", ret);
         LOG.info("----------/result"+result);
		 return result;
	}

	@Override
	public AppMsgResult wechatAppPAy(HttpServletRequest request, String userId, String userToken, String orderId) {
		// TODO Auto-generated method stub
		AppMsgResult  appMsgResult=  null;
		
		if(StringUtils.isNoneBlank(userId)){
			if(StringUtils.isNoneBlank(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
				String result_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != result_cache){
					//验证
					if(result_cache.equals(userToken)){
								//查询订单
				            	TzOrder order = orderMapper.selectByPrimaryKey(orderId);
				            	if(null != order){
				        			//创建订单信息
				                	TzUserPayLog userPayLog = new TzUserPayLog();
				                	String id = IDUtils.genId();
				                	userPayLog.setId(id);
				                	userPayLog.setUserId(userId);
				                	userPayLog.setPayType(0);
				                	userPayLog.setTotalMount("520");
				                	userPayLog.setBody("爱心520商城");
				                
				                	userPayLog.setStatus(0);
				                	userPayLog.setCreatedTime(new Date());
				                	userPayLog.setUpdatedTime(new Date());
				                	
				                	boolean wechatpayflag = false;
									//会员升级
						            if(order.getType() == 0){
						            	//记录支付记录
						            	userPayLog.setBuyType(0);
						            	userPayLog.setOrderId(orderId);
						            	userPayLog.setSubject("爱心520商城账户会员升级,超值礼包购买！");
						            	int res = userPayLogMapper.insertSelective(userPayLog);
						            	if(res == 1){
						            		wechatpayflag = true;
						            	}else{
						            		appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
						            	}
						            //购物
						            }else if(order.getType() == 1){
						            	//订单id
						            	if(StringUtils.isNoneBlank(orderId)){
						                	//记录支付记录
						            		userPayLog.setBuyType(0);
						            		userPayLog.setOrderId(orderId);
						            		userPayLog.setSubject("爱心520商城订单购物支付！");
						                	int res = userPayLogMapper.insertSelective(userPayLog);
						                	if(res == 1){
						                		//微信支付操作
						                		wechatpayflag = true;
						                	}else{
						                		appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
						                	}
						            	}else{
					                		appMsgResult = AppMsgResult.result(548, "订单id不能为空！", null);
					                	}
						            //错误的回调请求
						            }else{
						            	appMsgResult = AppMsgResult.result(549, "请先选择支付的类型！", null);
						            } 
						            //发起支付请求
						            if(wechatpayflag){
						            	//微信支付操作	
					            		// 生成订单
					        			Map<String, String> result = new HashMap<>();
					        	        //准备发起支付的参数
					        	        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
					        	        parameters.put("appid", WeChatPayConfig.appid); // 应用ID
					        	        parameters.put("mch_id", WeChatPayConfig.mch_id); // 商户号
					        	       /* parameters.put("device_info", WeChatPayConfig.DEVICE_INFO); // 设备号
					        */	       /* String nonce_str = IDUtils.genId();*/
					        	        parameters.put("nonce_str", IDUtils.genId()); // 随机字符串
					        	        parameters.put("body", userPayLog.getSubject()); // 描述
					        	        parameters.put("out_trade_no", id); // 商户订单号
					        	        parameters.put("total_fee", order.getPayment().multiply(new BigDecimal(100)).intValue()+""); // 订单总金额，单位为分
					        	        parameters.put("spbill_create_ip", WXPayUtil.getIpAddress(request)); // 用户端实际ip
					        	        /*parameters.put("notify_url", WeChatPayConfig.APPnotify);*/ // 支付成功后，回调地址 正式
					        	        parameters.put("notify_url", WeChatPayConfig.cs_APPnotify); // 支付成功后，回调地址 测试
					        	        parameters.put("trade_type", WeChatPayConfig.TRADE_TYPE_APP); // 支付类型(JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MICROPAY--刷卡支付)
					        	       /* parameters.put("openid", openid);*/ // trade_type=JSAPI，此参数必传
					        	        try {
					        	        	String sign = WXPayUtil.generateSignature(parameters,WeChatPayConfig.key);
					        	 	        System.out.println("参与签名的参数：" + parameters);
					        	 	        parameters.put("sign", sign);// 根据微信签名规则，生成签名
					        	 	        System.out.println("---------------------------------------------------------");
					        	 	        System.out.println(WXPayUtil.getIpAddress(request));
					        	 	        // 将请求参数转换为xml格式的string
					        	 	        String requestXML = WXPayUtil.generateSignedXml(parameters, WeChatPayConfig.key);

					        	 	        System.out.println("---------------------------------------------------------");
					        	 	        System.out.println(sign);
					        	 	        System.out.println("---------------------------------------------------------");
					        	 	        System.out.println(requestXML);
					        	 	        System.out.println("---------------------------------------------------------");

					        	 	        // 发送请求
					        	 	        String xmlData = WXPayUtil.httpsRequest2(WXPayConstants.DOMAIN_API+WXPayConstants.UNIFIEDORDER_URL_SUFFIX, "POST", requestXML);
					        	 	        
					        	 	        System.out.println(xmlData);
					        	 	        System.out.println("**************************");
					        				System.out.println(new String(xmlData.getBytes("UTF-8")));
					        		        // 解析XML
					        		        Map<String, String> map = WXPayUtil.xmlToMap(xmlData);
					        		        String prepay_id = (String) map.get("prepay_id");// 获取prepay_id
					        		        
					        		        result.put("appid", (String) map.get("appid"));  
					        		        result.put("noncestr", UUID.randomUUID().toString()  
					        	                    .replaceAll("-", ""));  
					        		        result.put("package", "Sign=WXPay");  
					        		        result.put("partnerid", (String) map.get("mch_id"));  
					        		        result.put("prepayid", prepay_id);  
					        		        result.put("timestamp", System.currentTimeMillis()/1000+"");  
					        		        String paySign = WXPayUtil.generateSignature(result, WeChatPayConfig.key);
					        		        result.put("signType", "MD5");
					        		        result.put("paySign", paySign);
					        		      /*  params.put("appid", WeChatPayConfig.appid);
					        		        long timeStr = System.currentTimeMillis() / 1000;
					        		        params.put("nonceStr", IDUtils.genId());
					        		        params.put("package", "Sign=WXPay");
					        		        params.put("timeStamp", timeStr + "");
					        		       
					        		        params.put("partnerid", WeChatPayConfig.mch_id);
					        		        params.put("prepayid",prepay_id);
					        		        String paySign = WXPayUtil.generateSignature(params, "UTF-8");
					        		        result.put("package", "Sign=WXPay");
					        		        result.put("appid", WeChatPayConfig.appid);
					        		        result.put("partnerid", WeChatPayConfig.mch_id);
					        		        result.put("prepayid", prepay_id);
					        		        result.put("timeStamp", timeStr + "");
					        		        result.put("nonceStr", IDUtils.genId());
					        		        result.put("signType", "MD5");
					        		        result.put("paySign", paySign);*/
					        		        /*result.put("attach", "");*/
					        		        appMsgResult = AppMsgResult.result("200", "success", result);	
					        			} catch (Exception e) {
					        				// TODO Auto-generated catch block
					        				appMsgResult = AppMsgResult.result("568", "支付失败", null);
					        				e.printStackTrace();
					        			}
						            }
				            	}else{
				            		appMsgResult = AppMsgResult.result(547, "订单信息不存在，请稍后重试！", null);
				            	}
					}else{
						appMsgResult = AppMsgResult.result(538,"用户未登录！",null );
					}
				}else{
					appMsgResult = AppMsgResult.result(538,"用户未登录！",null );
				}
			}else{
				appMsgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			appMsgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return appMsgResult;
	}
	
	@Transactional
	public void wechatWebNotify(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //读取参数  
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }  
        in.close();  
        inputStream.close();  
        //解析xml成map  
        Map<String, String> m = new HashMap<String, String>();  
        
        try {
			m = WXPayUtil.xmlToMap(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        for(Object keyValue : m.keySet()){
            System.out.println(keyValue+"="+m.get(keyValue));
        }
        //过滤空 设置 TreeMap  
        SortedMap<String,String> packageParams = new TreeMap<String,String>();        
        Iterator it = m.keySet().iterator();  
        while (it.hasNext()) {  
            String parameter = (String) it.next();  
            String parameterValue = m.get(parameter);  

            String v = "";  
            if(null != parameterValue) {  
                v = parameterValue.trim();  
            }  
            packageParams.put(parameter, v);  
        }  
        //判断签名是否正确  
        String resXml = "";  
        try {
			if(WXPayUtil.isSignatureValid(packageParams, WeChatPayConfig.gzh_key)) {
			     if("SUCCESS".equals((String)packageParams.get("result_code"))){ 
			         // 这里是支付成功  
			         //////////执行自己的业务逻辑////////////////  
			         String mch_id = (String)packageParams.get("mch_id"); //商户号 
			         String openid = (String)packageParams.get("openid");  //用户标识
			         String out_trade_no = (String)packageParams.get("out_trade_no"); //商户订单号
			         String total_fee = (String)packageParams.get("total_fee");  
			         String transaction_id = (String)packageParams.get("transaction_id"); //微信支付订单号
			         //查询支付订单
			         TzUserPayLog userPayLog = userPayLogMapper.selectByPrimaryKey(out_trade_no);  
			         
			         if(!WeChatPayConfig.gzh_mch_id.equals(mch_id)||userPayLog==null){
			        	 LOG.info("支付失败,错误信息：" + "参数错误");  
			             resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
			                                 + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";  
			         }else{
			        	 //判断是否处理
			             if(userPayLog.getStatus() !=1){//支付的价格
			            	 //订单状态的修改。根据实际业务逻辑执行   
			            	 //处理逻辑
			            	 String userId = userPayLog.getUserId();
				             int buyType = userPayLog.getBuyType();
				             //付款时间
			            	   Date PayTime = new Date();
			            	   userPayLog.setAccount(openid);
			            	   userPayLog.setPaymentTime(PayTime);
			            	   userPayLog.setStatus(1);
			            	   userPayLog.setTradeNo(transaction_id);
			            	   userPayLog.setTotalMount(total_fee);
			            	   userPayLogMapper.updateByPrimaryKeySelective(userPayLog);
			            	   //会员升级
				               if(buyType == 0){	   
				            	userMallExample = new TzUserMallExample(); 
				   				criteria = userMallExample.createCriteria();
				   				criteria.andUserIdEqualTo(userId);
				   				//商城id
						    	String mallId = common.getMallId();
				   				criteria.andMallIdEqualTo(mallId);
				   				//修改用户会员状态
				   				List<TzUserMall> userMalls =  userMallMapper.selectByExample(userMallExample);
				   				TzUserMall userMall = userMalls.get(0);
				   				if(userMall.getType()==0){
				   					userMall.setType(1);
				   				}
				   				userMall.setPayWay(1);
				   				userMall.setPrice(total_fee);
				   				userMall.setPayAccount(transaction_id);
				   				userMall.setUpdatedTime(new Date());
				   				userMall.setStartTime(PayTime);
				   				userMall.setEndTime(DateUtile.getPutOffYear(PayTime));
				   				userMallMapper.updateByPrimaryKeySelective(userMall);
				   				String orderId = userPayLog.getOrderId();
								//订单成功
				   				TzOrder order = new TzOrder();
								order.setStatus(2);
								order.setPaymentTime(PayTime);
								order.setPaymentType(1);
								order.setId(orderId);
								orderMapper.updateByPrimaryKeySelective(order);
								cache.deleteCache(RedisCache.CAHCENAME+"|selectOrderList|"+userId);
								cache.deleteCache(RedisCache.CAHCENAME+"|payOrderToken|"+orderId);
								//给用户增加爱心值数据记录
								//会员如果是推荐的会员那么给前面的数据置为已开通
								recommendExample = new TzRecommendExample(); 
								criteria6 = recommendExample.createCriteria();
								criteria6.andUserIdEqualTo(userId);
								//上级是否存在推荐
								List<TzRecommend> TzRecommends = recommendMapper.selectByExample(recommendExample);
								if(TzRecommends.size() > 0){
									TzRecommend record = new TzRecommend();
									record.setUserId(userId);
									record.setIsplay(1);
									record.setFeedbackStatus(1);
									record.setFeedbackTime(PayTime);
									recommendMapper.updateByExampleSelective(record, recommendExample);
									//添加爱心值
									recommendExample = new TzRecommendExample(); 
									criteria6 = recommendExample.createCriteria();
									criteria6.andUserIdEqualTo(userId);
									criteria6.andIsplayEqualTo(1);
									criteria6.andGradeEqualTo(1);
									//一级推荐用户爱心值增加
									List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
									if(tzRecommends1.size() > 0){
										//推荐用户的id
										String refereeId =  tzRecommends1.get(0).getRefereeId();
										userLoveExample = new TzUserLoveExample();
										criteria3 = userLoveExample.createCriteria();
										criteria3.andUserIdEqualTo(refereeId);
										List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
										//判断推荐用户是否存在爱心记录数据
										if(list.size()> 0){
											TzUserLove addLove= list.get(0);
											addLove.setLastUpdatedTime(new Date());
											addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeOne());
											addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeOne());
											userLoveMapper.updateByPrimaryKeySelective(addLove);
										}
										//添加消费记录
										TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
										consumptionDetails.setId(IDUtils.genId());
										consumptionDetails.setUserId(refereeId);
										consumptionDetails.setConsumeVal("+"+common.getGradeOne());
										consumptionDetails.setCreatedTime(new Date());
										consumptionDetails.setUpdatedTime(new Date());
										consumptionDetails.setType(4);
										consumptionDetails.setOrderId(orderId);
										consumptionDetails.setName("520商城推荐用户升级会员！");
										consumptionDetails.setStatus(0);
										consumptionDetailsMapper.insertSelective(consumptionDetails);
									}
									recommendExample = new TzRecommendExample(); 
									criteria6 = recommendExample.createCriteria();
									criteria6.andUserIdEqualTo(userId);
									criteria6.andIsplayEqualTo(1);
									criteria6.andGradeEqualTo(2);
									//二级推荐用户爱心值增加
									List<TzRecommend> tzRecommends2 = recommendMapper.selectByExample(recommendExample);
									if(tzRecommends2.size() > 0){
										//推荐用户的id
										String refereeId =  tzRecommends2.get(0).getRefereeId();
										userLoveExample = new TzUserLoveExample();
										criteria3 = userLoveExample.createCriteria();
										criteria3.andUserIdEqualTo(refereeId);
										List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
										//判断推荐用户是否存在爱心记录数据
										if(list.size()> 0){
											TzUserLove addLove= list.get(0);
											addLove.setLastUpdatedTime(new Date());
											addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeTwo());
											addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeTwo());
											userLoveMapper.updateByPrimaryKeySelective(addLove);
										}
										//添加消费记录
										TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
										consumptionDetails.setId(IDUtils.genId());
										consumptionDetails.setUserId(refereeId);
										consumptionDetails.setConsumeVal("+"+common.getGradeTwo());
										consumptionDetails.setCreatedTime(new Date());
										consumptionDetails.setUpdatedTime(new Date());
										consumptionDetails.setType(4);
										consumptionDetails.setOrderId(orderId);
										consumptionDetails.setName("520商城推荐用户升级会员！");
										consumptionDetails.setStatus(0);
										consumptionDetailsMapper.insertSelective(consumptionDetails);
									}
								}
								//购物
				               }else if(buyType == 1){
				            	    //付款时间
				            	    Date PayTime1 = new Date();
				            	    userPayLog.setAccount(openid);
				            	    userPayLog.setPaymentTime(PayTime1);
				            	    userPayLog.setStatus(1);
				            	    userPayLog.setTradeNo(transaction_id);
				            	    userPayLog.setTotalMount(total_fee);
				            	    userPayLogMapper.updateByPrimaryKeySelective(userPayLog);
			            	   
				            	   	String orderId = userPayLog.getOrderId();
				            	   	TzOrder order = orderMapper.selectByPrimaryKey(orderId);
				            	   	if(null != order){
				            	   		//用户抵扣的爱心值
				            	   	  	int consumeVal = order.getLoveValue();
					            	   	if(consumeVal > 0){
											//添加消费记录
											TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
											consumptionDetails.setUserId(userId);
											consumptionDetails.setId(IDUtils.genId());
											consumptionDetails.setConsumeVal("-"+consumeVal);
											consumptionDetails.setCreatedTime(new Date());
											consumptionDetails.setUpdatedTime(new Date());
											consumptionDetails.setType(1);
											consumptionDetails.setShowType(0);
											consumptionDetails.setOrderId(orderId);
											consumptionDetails.setName("520商城购物消费抵扣！");
											consumptionDetails.setStatus(0);
											consumptionDetailsMapper.insertSelective(consumptionDetails);
					            	   	}
										//订单成功
					            	   	order.setPaymentType(1);
										order.setStatus(2);
										order.setPaymentTime(PayTime1);
										orderMapper.updateByPrimaryKeySelective(order);
										cache.deleteCache(RedisCache.CAHCENAME+"|selectOrderList|"+userId);
										cache.deleteCache(RedisCache.CAHCENAME+"|payOrderToken|"+orderId);
										int comboNum = order.getComboNum();
										 LOG.info("comboNum----------/comboNum"+comboNum);
										if(comboNum > 0){
											//第一次购买礼包
											userMallExample = new TzUserMallExample(); 
							   				criteria = userMallExample.createCriteria();
							   				criteria.andUserIdEqualTo(userId);
							   				//商城id
									    	String mallId = common.getMallId();
							   				criteria.andMallIdEqualTo(mallId);
							   				//修改用户会员状态
							   				List<TzUserMall> userMalls =  userMallMapper.selectByExample(userMallExample);
							   				TzUserMall userMall = userMalls.get(0);
							   				//判断是否是第一次购买礼包
							   				if(userMall.getType() == 0){
							   					userMall.setType(1);
							   					userMall.setPayWay(1);
								   				userMall.setPrice(total_fee);
								   				userMall.setPayAccount(openid);
								   				userMall.setUpdatedTime(new Date());
								   				userMall.setStartTime(PayTime);
								   				userMall.setEndTime(DateUtile.getPutOffYear(PayTime));
								   				userMallMapper.updateByPrimaryKeySelective(userMall);
								   				//走推荐流程
												//会员如果是推荐的会员那么给前面的数据置为已开通
												recommendExample = new TzRecommendExample(); 
												criteria6 = recommendExample.createCriteria();
												criteria6.andUserIdEqualTo(userId);
												//上级是否存在推荐
												List<TzRecommend> TzRecommends = recommendMapper.selectByExample(recommendExample);
												if(TzRecommends.size() > 0){
													TzRecommend record = new TzRecommend();
													record.setUserId(userId);
													record.setIsplay(1);
													record.setFeedbackStatus(1);
													record.setFeedbackTime(PayTime);
													recommendMapper.updateByExampleSelective(record, recommendExample);
													//添加爱心值
													recommendExample = new TzRecommendExample(); 
													criteria6 = recommendExample.createCriteria();
													criteria6.andUserIdEqualTo(userId);
													criteria6.andIsplayEqualTo(1);
													criteria6.andGradeEqualTo(1);
													//一级推荐用户爱心值增加
													List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
													if(tzRecommends1.size() > 0){
														//推荐用户的id
														String refereeId =  tzRecommends1.get(0).getRefereeId();
														userLoveExample = new TzUserLoveExample();
														criteria3 = userLoveExample.createCriteria();
														criteria3.andUserIdEqualTo(refereeId);
														List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
														//判断推荐用户是否存在爱心记录数据
														if(list.size()> 0){
															TzUserLove addLove= list.get(0);
															addLove.setLastUpdatedTime(new Date());
															addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeOne());
															addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeOne());
															userLoveMapper.updateByPrimaryKeySelective(addLove);
														}
														//添加消费记录
														TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
														consumptionDetails.setId(IDUtils.genId());
														consumptionDetails.setUserId(refereeId);
														consumptionDetails.setConsumeVal("+"+common.getGradeOne());
														consumptionDetails.setCreatedTime(new Date());
														consumptionDetails.setUpdatedTime(new Date());
														consumptionDetails.setType(4);
														consumptionDetails.setOrderId(orderId);
														consumptionDetails.setName("520商城推荐用户升级会员！");
														consumptionDetails.setStatus(0);
														consumptionDetailsMapper.insertSelective(consumptionDetails);
													}
													recommendExample = new TzRecommendExample(); 
													criteria6 = recommendExample.createCriteria();
													criteria6.andUserIdEqualTo(userId);
													criteria6.andIsplayEqualTo(1);
													criteria6.andGradeEqualTo(2);
													//二级推荐用户爱心值增加
													List<TzRecommend> tzRecommends2 = recommendMapper.selectByExample(recommendExample);
													if(tzRecommends2.size() > 0){
														//推荐用户的id
														String refereeId =  tzRecommends2.get(0).getRefereeId();
														userLoveExample = new TzUserLoveExample();
														criteria3 = userLoveExample.createCriteria();
														criteria3.andUserIdEqualTo(refereeId);
														List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
														//判断推荐用户是否存在爱心记录数据
														if(list.size()> 0){
															TzUserLove addLove= list.get(0);
															addLove.setLastUpdatedTime(new Date());
															addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeTwo());
															addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeTwo());
															userLoveMapper.updateByPrimaryKeySelective(addLove);
														}
														//添加消费记录
														TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
														consumptionDetails.setId(IDUtils.genId());
														consumptionDetails.setUserId(refereeId);
														consumptionDetails.setConsumeVal("+"+common.getGradeTwo());
														consumptionDetails.setCreatedTime(new Date());
														consumptionDetails.setUpdatedTime(new Date());
														consumptionDetails.setType(4);
														consumptionDetails.setOrderId(orderId);
														consumptionDetails.setName("520商城推荐用户升级会员！");
														consumptionDetails.setStatus(0);
														consumptionDetailsMapper.insertSelective(consumptionDetails);
													}
												}
							   				}else{
												//查询用户上级是否存在推荐
												recommendExample = new TzRecommendExample(); 
												criteria6 = recommendExample.createCriteria();
												criteria6.andUserIdEqualTo(userId);
												criteria6.andIsplayEqualTo(1);
												criteria6.andGradeEqualTo(1);
												//一级推荐用户爱心值增加
												List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
												if(tzRecommends1.size() > 0){
													
													//推荐用户的id
													String refereeId =  tzRecommends1.get(0).getRefereeId();
													 LOG.info("refereeId----------/refereeId"+refereeId);
													userLoveExample = new TzUserLoveExample();
													criteria3 = userLoveExample.createCriteria();
													criteria3.andUserIdEqualTo(refereeId);
													List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
													//判断推荐用户是否存在爱心记录数据
													if(list.size()> 0){
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
													consumptionDetails.setName("520商城推荐用户礼包购买！");
													consumptionDetails.setStatus(0);
													consumptionDetailsMapper.insertSelective(consumptionDetails);
												}
												System.out.println("调用成功");
							   				}
										}
										//遍历上级城市爱心合伙人 5%的单品提成
										BigDecimal paySingleItem = order.getPaySingleItem();
										LOG.info("paySingleItem----------/paySingleItem"+paySingleItem);
										//单品数量是否大于0
										if(paySingleItem.intValue()  > 0){
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
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												//当前用户
												if(firstFlag){
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
												   firstFlag = false;
												}else{
													recommendExample = new TzRecommendExample(); 
													criteria6 = recommendExample.createCriteria();
													criteria6.andUserIdEqualTo(re_userId);
													criteria6.andIsplayEqualTo(1);
													criteria6.andGradeEqualTo(1);
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
								}
			            	 resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
				                 + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
				         }else{
				        	 resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
				                             + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
				         LOG.info("订单已处理");  
				                     }
				     }
			     }else {  
			    	 LOG.info("支付失败,错误信息：" + packageParams.get("err_code"));  
			         resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
			                 + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
			     }  
			} else{  
			    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
			            + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> "; 
			    LOG.info("通知签名验证失败");  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        //------------------------------  
        //处理业务完毕  
        //------------------------------  
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        out.write(resXml.getBytes());  
        out.flush();  
        out.close();  
    }
	
	@Transactional
	public void wechatAppNotify(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //读取参数  
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }  
        in.close();  
        inputStream.close();  
        //解析xml成map  
        Map<String, String> m = new HashMap<String, String>();  
        
        try {
			m = WXPayUtil.xmlToMap(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        for(Object keyValue : m.keySet()){
            System.out.println(keyValue+"="+m.get(keyValue));
        }
        //过滤空 设置 TreeMap  
        SortedMap<String,String> packageParams = new TreeMap<String,String>();        
        Iterator it = m.keySet().iterator();  
        while (it.hasNext()) {  
            String parameter = (String) it.next();  
            String parameterValue = m.get(parameter);  

            String v = "";  
            if(null != parameterValue) {  
                v = parameterValue.trim();  
            }  
            packageParams.put(parameter, v);  
        }  
        //判断签名是否正确  
        String resXml = "";  
        try {
			if(WXPayUtil.isSignatureValid(packageParams, WeChatPayConfig.key)) {
			     if("SUCCESS".equals((String)packageParams.get("result_code"))){ 
			         // 这里是支付成功  
			         //////////执行自己的业务逻辑////////////////  
			         String mch_id = (String)packageParams.get("mch_id"); //商户号 
			         String openid = (String)packageParams.get("openid");  //用户标识
			         String out_trade_no = (String)packageParams.get("out_trade_no"); //商户订单号
			         String total_fee = (String)packageParams.get("total_fee");  
			         String transaction_id = (String)packageParams.get("transaction_id"); //微信支付订单号
			         //查询支付订单
			         TzUserPayLog userPayLog = userPayLogMapper.selectByPrimaryKey(out_trade_no);  
			         
			         if(!WeChatPayConfig.mch_id.equals(mch_id)||userPayLog==null){
			        	 LOG.info("支付失败,错误信息：" + "参数错误");  
			             resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
			                                 + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";  
			         }else{
			        	 //判断是否处理
			             if(userPayLog.getStatus() !=1){//支付的价格
			            	 //订单状态的修改。根据实际业务逻辑执行   
			            	 //处理逻辑
			            	 String userId = userPayLog.getUserId();
				             int buyType = userPayLog.getBuyType();
				             //付款时间
			            	   Date PayTime = new Date();
			            	   userPayLog.setAccount(openid);
			            	   userPayLog.setPaymentTime(PayTime);
			            	   userPayLog.setStatus(1);
			            	   userPayLog.setTradeNo(transaction_id);
			            	   userPayLog.setTotalMount(total_fee);
			            	   userPayLogMapper.updateByPrimaryKeySelective(userPayLog);
			            	   //会员升级
				               if(buyType == 0){	   
				            	userMallExample = new TzUserMallExample(); 
				   				criteria = userMallExample.createCriteria();
				   				criteria.andUserIdEqualTo(userId);
				   				//商城id
						    	String mallId = common.getMallId();
				   				criteria.andMallIdEqualTo(mallId);
				   				//修改用户会员状态
				   				List<TzUserMall> userMalls =  userMallMapper.selectByExample(userMallExample);
				   				TzUserMall userMall = userMalls.get(0);
				   				if(userMall.getType()==0){
				   					userMall.setType(1);
				   				}
				   				userMall.setPayWay(1);
				   				userMall.setPrice(total_fee);
				   				userMall.setPayAccount(transaction_id);
				   				userMall.setUpdatedTime(new Date());
				   				userMall.setStartTime(PayTime);
				   				userMall.setEndTime(DateUtile.getPutOffYear(PayTime));
				   				userMallMapper.updateByPrimaryKeySelective(userMall);
				   				String orderId = userPayLog.getOrderId();
								//订单成功
				   				TzOrder order = new TzOrder();
								order.setStatus(2);
								order.setPaymentTime(PayTime);
								order.setPaymentType(1);
								order.setId(orderId);
								orderMapper.updateByPrimaryKeySelective(order);
								cache.deleteCache(RedisCache.CAHCENAME+"|selectOrderList|"+userId);
								cache.deleteCache(RedisCache.CAHCENAME+"|payOrderToken|"+orderId);
								//给用户增加爱心值数据记录
								//会员如果是推荐的会员那么给前面的数据置为已开通
								recommendExample = new TzRecommendExample(); 
								criteria6 = recommendExample.createCriteria();
								criteria6.andUserIdEqualTo(userId);
								//上级是否存在推荐
								List<TzRecommend> TzRecommends = recommendMapper.selectByExample(recommendExample);
								if(TzRecommends.size() > 0){
									TzRecommend record = new TzRecommend();
									record.setUserId(userId);
									record.setIsplay(1);
									record.setFeedbackStatus(1);
									record.setFeedbackTime(PayTime);
									recommendMapper.updateByExampleSelective(record, recommendExample);
									//添加爱心值
									recommendExample = new TzRecommendExample(); 
									criteria6 = recommendExample.createCriteria();
									criteria6.andUserIdEqualTo(userId);
									criteria6.andIsplayEqualTo(1);
									criteria6.andGradeEqualTo(1);
									//一级推荐用户爱心值增加
									List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
									if(tzRecommends1.size() > 0){
										//推荐用户的id
										String refereeId =  tzRecommends1.get(0).getRefereeId();
										userLoveExample = new TzUserLoveExample();
										criteria3 = userLoveExample.createCriteria();
										criteria3.andUserIdEqualTo(refereeId);
										List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
										//判断推荐用户是否存在爱心记录数据
										if(list.size()> 0){
											TzUserLove addLove= list.get(0);
											addLove.setLastUpdatedTime(new Date());
											addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeOne());
											addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeOne());
											userLoveMapper.updateByPrimaryKeySelective(addLove);
										}
										//添加消费记录
										TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
										consumptionDetails.setId(IDUtils.genId());
										consumptionDetails.setUserId(refereeId);
										consumptionDetails.setConsumeVal("+"+common.getGradeOne());
										consumptionDetails.setCreatedTime(new Date());
										consumptionDetails.setUpdatedTime(new Date());
										consumptionDetails.setType(4);
										consumptionDetails.setOrderId(orderId);
										consumptionDetails.setName("520商城推荐用户升级会员！");
										consumptionDetails.setStatus(0);
										consumptionDetailsMapper.insertSelective(consumptionDetails);
									}
									recommendExample = new TzRecommendExample(); 
									criteria6 = recommendExample.createCriteria();
									criteria6.andUserIdEqualTo(userId);
									criteria6.andIsplayEqualTo(1);
									criteria6.andGradeEqualTo(2);
									//二级推荐用户爱心值增加
									List<TzRecommend> tzRecommends2 = recommendMapper.selectByExample(recommendExample);
									if(tzRecommends2.size() > 0){
										//推荐用户的id
										String refereeId =  tzRecommends2.get(0).getRefereeId();
										userLoveExample = new TzUserLoveExample();
										criteria3 = userLoveExample.createCriteria();
										criteria3.andUserIdEqualTo(refereeId);
										List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
										//判断推荐用户是否存在爱心记录数据
										if(list.size()> 0){
											TzUserLove addLove= list.get(0);
											addLove.setLastUpdatedTime(new Date());
											addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeTwo());
											addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeTwo());
											userLoveMapper.updateByPrimaryKeySelective(addLove);
										}
										//添加消费记录
										TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
										consumptionDetails.setId(IDUtils.genId());
										consumptionDetails.setUserId(refereeId);
										consumptionDetails.setConsumeVal("+"+common.getGradeTwo());
										consumptionDetails.setCreatedTime(new Date());
										consumptionDetails.setUpdatedTime(new Date());
										consumptionDetails.setType(4);
										consumptionDetails.setOrderId(orderId);
										consumptionDetails.setName("520商城推荐用户升级会员！");
										consumptionDetails.setStatus(0);
										consumptionDetailsMapper.insertSelective(consumptionDetails);
									}
								}
								//购物
				               }else if(buyType == 1){
				            	    //付款时间
				            	    Date PayTime1 = new Date();
				            	    userPayLog.setAccount(openid);
				            	    userPayLog.setPaymentTime(PayTime1);
				            	    userPayLog.setStatus(1);
				            	    userPayLog.setTradeNo(transaction_id);
				            	    userPayLog.setTotalMount(total_fee);
				            	    userPayLogMapper.updateByPrimaryKeySelective(userPayLog);
			            	   
				            	   	String orderId = userPayLog.getOrderId();
				            	   	TzOrder order = orderMapper.selectByPrimaryKey(orderId);
				            	   	if(null != order){
				            	   		//用户抵扣的爱心值
				            	   	  	int consumeVal = order.getLoveValue();
					            	   	if(consumeVal > 0){
											//添加消费记录
											TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
											consumptionDetails.setUserId(userId);
											consumptionDetails.setId(IDUtils.genId());
											consumptionDetails.setConsumeVal("-"+consumeVal);
											consumptionDetails.setCreatedTime(new Date());
											consumptionDetails.setUpdatedTime(new Date());
											consumptionDetails.setType(1);
											consumptionDetails.setShowType(0);
											consumptionDetails.setOrderId(orderId);
											consumptionDetails.setName("520商城购物消费抵扣！");
											consumptionDetails.setStatus(0);
											consumptionDetailsMapper.insertSelective(consumptionDetails);
					            	   	}
										//订单成功
					            	   	order.setPaymentType(1);
										order.setStatus(2);
										order.setPaymentTime(PayTime1);
										orderMapper.updateByPrimaryKeySelective(order);
										cache.deleteCache(RedisCache.CAHCENAME+"|selectOrderList|"+userId);
										cache.deleteCache(RedisCache.CAHCENAME+"|payOrderToken|"+orderId);
										int comboNum = order.getComboNum();
										 LOG.info("comboNum----------/comboNum"+comboNum);
										if(comboNum > 0){
											//第一次购买礼包
											userMallExample = new TzUserMallExample(); 
							   				criteria = userMallExample.createCriteria();
							   				criteria.andUserIdEqualTo(userId);
							   				//商城id
									    	String mallId = common.getMallId();
							   				criteria.andMallIdEqualTo(mallId);
							   				//修改用户会员状态
							   				List<TzUserMall> userMalls =  userMallMapper.selectByExample(userMallExample);
							   				TzUserMall userMall = userMalls.get(0);
							   				//判断是否是第一次购买礼包
							   				if(userMall.getType() == 0){
							   					userMall.setType(1);
							   					userMall.setPayWay(1);
								   				userMall.setPrice(total_fee);
								   				userMall.setPayAccount(openid);
								   				userMall.setUpdatedTime(new Date());
								   				userMall.setStartTime(PayTime);
								   				userMall.setEndTime(DateUtile.getPutOffYear(PayTime));
								   				userMallMapper.updateByPrimaryKeySelective(userMall);
								   				//走推荐流程
												//会员如果是推荐的会员那么给前面的数据置为已开通
												recommendExample = new TzRecommendExample(); 
												criteria6 = recommendExample.createCriteria();
												criteria6.andUserIdEqualTo(userId);
												//上级是否存在推荐
												List<TzRecommend> TzRecommends = recommendMapper.selectByExample(recommendExample);
												if(TzRecommends.size() > 0){
													TzRecommend record = new TzRecommend();
													record.setUserId(userId);
													record.setIsplay(1);
													record.setFeedbackStatus(1);
													record.setFeedbackTime(PayTime);
													recommendMapper.updateByExampleSelective(record, recommendExample);
													//添加爱心值
													recommendExample = new TzRecommendExample(); 
													criteria6 = recommendExample.createCriteria();
													criteria6.andUserIdEqualTo(userId);
													criteria6.andIsplayEqualTo(1);
													criteria6.andGradeEqualTo(1);
													//一级推荐用户爱心值增加
													List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
													if(tzRecommends1.size() > 0){
														//推荐用户的id
														String refereeId =  tzRecommends1.get(0).getRefereeId();
														userLoveExample = new TzUserLoveExample();
														criteria3 = userLoveExample.createCriteria();
														criteria3.andUserIdEqualTo(refereeId);
														List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
														//判断推荐用户是否存在爱心记录数据
														if(list.size()> 0){
															TzUserLove addLove= list.get(0);
															addLove.setLastUpdatedTime(new Date());
															addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeOne());
															addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeOne());
															userLoveMapper.updateByPrimaryKeySelective(addLove);
														}
														//添加消费记录
														TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
														consumptionDetails.setId(IDUtils.genId());
														consumptionDetails.setUserId(refereeId);
														consumptionDetails.setConsumeVal("+"+common.getGradeOne());
														consumptionDetails.setCreatedTime(new Date());
														consumptionDetails.setUpdatedTime(new Date());
														consumptionDetails.setType(4);
														consumptionDetails.setOrderId(orderId);
														consumptionDetails.setName("520商城推荐用户升级会员！");
														consumptionDetails.setStatus(0);
														consumptionDetailsMapper.insertSelective(consumptionDetails);
													}
													recommendExample = new TzRecommendExample(); 
													criteria6 = recommendExample.createCriteria();
													criteria6.andUserIdEqualTo(userId);
													criteria6.andIsplayEqualTo(1);
													criteria6.andGradeEqualTo(2);
													//二级推荐用户爱心值增加
													List<TzRecommend> tzRecommends2 = recommendMapper.selectByExample(recommendExample);
													if(tzRecommends2.size() > 0){
														//推荐用户的id
														String refereeId =  tzRecommends2.get(0).getRefereeId();
														userLoveExample = new TzUserLoveExample();
														criteria3 = userLoveExample.createCriteria();
														criteria3.andUserIdEqualTo(refereeId);
														List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
														//判断推荐用户是否存在爱心记录数据
														if(list.size()> 0){
															TzUserLove addLove= list.get(0);
															addLove.setLastUpdatedTime(new Date());
															addLove.setLoveSurplus(addLove.getLoveSurplus()+common.getGradeTwo());
															addLove.setLoveTotal(addLove.getLoveTotal()+common.getGradeTwo());
															userLoveMapper.updateByPrimaryKeySelective(addLove);
														}
														//添加消费记录
														TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
														consumptionDetails.setId(IDUtils.genId());
														consumptionDetails.setUserId(refereeId);
														consumptionDetails.setConsumeVal("+"+common.getGradeTwo());
														consumptionDetails.setCreatedTime(new Date());
														consumptionDetails.setUpdatedTime(new Date());
														consumptionDetails.setType(4);
														consumptionDetails.setOrderId(orderId);
														consumptionDetails.setName("520商城推荐用户升级会员！");
														consumptionDetails.setStatus(0);
														consumptionDetailsMapper.insertSelective(consumptionDetails);
													}
												}
							   				}else{
												//查询用户上级是否存在推荐
												recommendExample = new TzRecommendExample(); 
												criteria6 = recommendExample.createCriteria();
												criteria6.andUserIdEqualTo(userId);
												criteria6.andIsplayEqualTo(1);
												criteria6.andGradeEqualTo(1);
												//一级推荐用户爱心值增加
												List<TzRecommend> tzRecommends1 = recommendMapper.selectByExample(recommendExample);
												if(tzRecommends1.size() > 0){
													
													//推荐用户的id
													String refereeId =  tzRecommends1.get(0).getRefereeId();
													 LOG.info("refereeId----------/refereeId"+refereeId);
													userLoveExample = new TzUserLoveExample();
													criteria3 = userLoveExample.createCriteria();
													criteria3.andUserIdEqualTo(refereeId);
													List<TzUserLove>  list = userLoveMapper.selectByExample(userLoveExample);
													//判断推荐用户是否存在爱心记录数据
													if(list.size()> 0){
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
													consumptionDetails.setName("520商城推荐用户礼包购买！");
													consumptionDetails.setStatus(0);
													consumptionDetailsMapper.insertSelective(consumptionDetails);
												}
												System.out.println("调用成功");
							   				}
										}
										//遍历上级城市爱心合伙人 5%的单品提成
										BigDecimal paySingleItem = order.getPaySingleItem();
										LOG.info("paySingleItem----------/paySingleItem"+paySingleItem);
										//单品数量是否大于0
										if(paySingleItem.intValue()  > 0){
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
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												//当前用户
												if(firstFlag){
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
												   firstFlag = false;
												}else{
													recommendExample = new TzRecommendExample(); 
													criteria6 = recommendExample.createCriteria();
													criteria6.andUserIdEqualTo(re_userId);
													criteria6.andIsplayEqualTo(1);
													criteria6.andGradeEqualTo(1);
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
								}
			            	 resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
				                 + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
				         }else{
				        	 resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
				                             + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
				         LOG.info("订单已处理");  
				                     }
				     }
			     }else {  
			    	 LOG.info("支付失败,错误信息：" + packageParams.get("err_code"));  
			         resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
			                 + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
			     }  
			} else{  
			    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
			            + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> "; 
			    LOG.info("通知签名验证失败");  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        //------------------------------  
        //处理业务完毕  
        //------------------------------  
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        out.write(resXml.getBytes());  
        out.flush();  
        out.close();  
    }
	
	public static void main(String[] args) {
		int count = 1;
		while(true){
			System.out.println(System.currentTimeMillis());
			 try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(count ==100){
				 break;
			 }
		}
		
	}
	
}

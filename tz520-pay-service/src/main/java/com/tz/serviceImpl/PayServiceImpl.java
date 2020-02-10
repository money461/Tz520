package com.tz.serviceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenAppCodetesttestRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayOpenAppCodetesttestResponse;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.interfaces.PayService;
import com.tz.mapper.TzOrderMapper;
import com.tz.mapper.TzUserMallMapper;
import com.tz.mapper.TzUserPayLogMapper;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderExample;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import com.tz.pojo.TzUserPayLog;
import com.tz.pojo.TzUserPayLogExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.res.AppMsgResult;
import com.tz.sdk.zfb.AlipayConfig;


@Service
public class PayServiceImpl implements PayService  {
	
	//用户商城中间类
	@Autowired 
	private TzUserMallMapper userMallMapper ;	
	private TzUserMallExample userMallExample;
	private TzUserMallExample.Criteria criteria;
	
	//用户支付记录表
	@Autowired 
	private TzUserPayLogMapper userPayLogMapper ;	
	private TzUserPayLogExample userPayLogExample;
	private TzUserPayLogExample.Criteria criteria2;
	
	//用户订单
	@Autowired 
	private TzOrderMapper orderMapper;	
	private TzOrderExample orderExample;
	private TzOrderExample.Criteria criteria3;
	
	
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;

	//支付宝app支付
	public AppMsgResult zfbAPPaliPay(String userId,String type,String userToken,String orderId){
			AppMsgResult  appMsgResult=  null;
			//实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
			//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			
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
					            	userPayLog.setSubject("爱心520商城账户会员升级，超值礼包购买！");
					            	int res = userPayLogMapper.insertSelective(userPayLog);
					            	if(res == 1){
					            		model.setBody("爱心520商城");
					        			model.setSubject("爱心520商城账户会员升级，超值礼包购买！");
					        			model.setOutTradeNo(id);
					        			model.setTimeoutExpress("30m");
					        			model.setTotalAmount(""+order.getPayment()+"");
					        			model.setProductCode("QUICK_MSECURITY_PAY");
					        			request.setBizModel(model);
					        			request.setNotifyUrl(AlipayConfig.APPnotify);
					        			try {
					        			        //这里和普通的接口调用不同，使用的是sdkExecute
					        			        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
					        			        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
					        			        appMsgResult = AppMsgResult.result(200, "ok", response.getBody());
					        			    } catch (AlipayApiException e) {
					        			        e.printStackTrace();
					        			        appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
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
					            		userPayLog.setSubject("爱心520商城订单购物支付");
					                	int res = userPayLogMapper.insertSelective(userPayLog);
					                	if(res == 1){
					                		model.setBody("爱心520商城");
					            			model.setSubject("爱心520商城订单购物支付！");
					            			model.setOutTradeNo(id);
					            			model.setTimeoutExpress("30m");
					            			model.setTotalAmount(""+order.getPayment()+"");
					            			model.setProductCode("QUICK_MSECURITY_PAY");
					            			request.setBizModel(model);
					            			request.setNotifyUrl(AlipayConfig.APPnotify);
					            			try {
					            			        //这里和普通的接口调用不同，使用的是sdkExecute
					            			        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
					            			        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
					            			        appMsgResult = AppMsgResult.result(200, "ok", response.getBody());
					            			    } catch (AlipayApiException e) {
					            			    	appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
					            			        e.printStackTrace();
					            			}
					                	}else{
					                		appMsgResult = AppMsgResult.result(528, "服务器繁忙，请稍后重试！", null);
					                	} 
					            	}else{
				                		appMsgResult = AppMsgResult.result(547, "订单信息不存在，请稍后重试！", null);
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
				appMsgResult = AppMsgResult.result(541,"用户id不能为空！", null);
			}
			return appMsgResult;	
	    }
	
}

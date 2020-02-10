package com.tz.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.github.pagehelper.util.StringUtil;
import com.sun.tools.internal.xjc.model.SymbolSpace;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.interfaces.RecommendService;
import com.tz.mapper.TzRecommendMapper;
import com.tz.mapper.TzUserLoveConsumptionDetailsMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.TzUserLoveShowMapper;
import com.tz.mapper.TzUserMapper;
import com.tz.mapper.TzZfbUserInfoMapper;
import com.tz.mapper.vo.TzUserLoveConsumptionDetailsMapperVo;
import com.tz.pojo.TzRecommendExample;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveConsumptionDetails;
import com.tz.pojo.TzUserLoveConsumptionDetailsExample;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.TzUserLoveShow;
import com.tz.pojo.TzUserLoveShowExample;
import com.tz.pojo.TzZfbUserInfo;
import com.tz.pojo.TzZfbUserInfoExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.RecommendVo;
import com.tz.res.AppMsgResult;
import com.tz.sdk.zfb.AlipayConfig;

@Service
public class RecommendServiceImpl implements RecommendService   {
	
	//用户总的爱心值类
	@Autowired 
	private TzUserLoveMapper userLoveMapper;	
	private TzUserLoveExample userLoveExample;
	private TzUserLoveExample.Criteria criteria;
	
	//用户推荐类
	@Autowired 
	TzRecommendMapper recommendMapper;	
	private TzRecommendExample recommendExample;
	private TzRecommendExample.Criteria criteria2;
	
	//用户爱心值提现
	@Autowired 
	private TzUserLoveShowMapper userLoveShowMapper;	
	private TzUserLoveShowExample userLoveShowExample;
	private TzUserLoveShowExample.Criteria criteria3;
	
	//用户爱心值消费类
	@Autowired 
	TzUserLoveConsumptionDetailsMapper consumptionDetailsMapper;	
	private TzUserLoveConsumptionDetailsExample consumptionDetailsExample;
	private TzUserLoveConsumptionDetailsExample.Criteria criteria4;
	
	//用户支付宝授权登录类
	@Autowired 
	TzZfbUserInfoMapper zfbUserInfoMapper;	
	private TzZfbUserInfoExample zfbUserInfoExample;
	private TzZfbUserInfoExample.Criteria criteria5;
	
	//用户爱心值消费扩展类
	@Autowired 
	TzUserLoveConsumptionDetailsMapperVo detailsMapperVo;	
	//用户类
	@Autowired 
	TzUserMapper userMapper;	
	private TzUserExample example;
	private TzUserExample.Criteria criteria6;
	
	@Autowired
	private RedisCache cache;
	
    @Autowired
    private CommonVo common;
	
	@Override
	public AppMsgResult getRecommendList(String userId, String userToken) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(StringUtil.isNotEmpty(userId)){
			if(StringUtil.isNotEmpty(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
				String result_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != result_cache){
					//验证
					if(result_cache.equals(userToken)){
						RecommendVo recommendVo = new  RecommendVo();
						//操作
						//获取用户爱心值
						userLoveExample = new TzUserLoveExample();
						criteria = userLoveExample.createCriteria();
						criteria.andUserIdEqualTo(userId);
						List<TzUserLove> loves= userLoveMapper.selectByExample(userLoveExample);
						//数据封装
						if(loves.size() > 0){
							TzUserLove love= loves.get(0);
							//获取用户一级推荐爱心
							String mallId = common.getMallId();
							recommendExample = new TzRecommendExample();
							criteria2 = recommendExample.createCriteria();
							criteria2.andRefereeIdEqualTo(userId);
							//付款
							criteria2.andIsplayEqualTo(1);
							//一级推荐
							criteria2.andGradeEqualTo(1);
							//商城id
							criteria2.andMallIdEqualTo(mallId);
							int gradeOne = recommendMapper.countByExample(recommendExample);
							//获取用户二级推荐爱心
							recommendExample = new TzRecommendExample();
							criteria2 = recommendExample.createCriteria();
							criteria2.andRefereeIdEqualTo(userId);
							//付款
							criteria2.andIsplayEqualTo(1);
							//二级推荐
							criteria2.andGradeEqualTo(2);
							//商城id
							criteria2.andMallIdEqualTo(mallId);
							int gradeTwo = recommendMapper.countByExample(recommendExample);
							if(love != null){
								recommendVo.setLoveTotal(love.getLoveTotal());
								recommendVo.setLoveSurplus(love.getLoveSurplus());
								recommendVo.setStatus(love.getStatus());
							}
							recommendVo.setGradeOne(gradeOne*100);
							recommendVo.setGradeTwo(gradeTwo*52);
						}
						msgResult = AppMsgResult.result(200,"success",recommendVo);
					}else{
						msgResult = AppMsgResult.result(538,"用户未登录！",null );
					}
				}else{
					msgResult = AppMsgResult.result(538,"用户未登录！",null);
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
	@Transactional
	@Override
	public AppMsgResult withdrawals(TzUserLoveShow userLoveShow, String userToken,String password) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(null != userLoveShow){
			//用户id
			String userId = userLoveShow.getUserId();
			//提现账号
			String account = userLoveShow.getAccount();
			//提现真实姓名
			String payeeRealName = userLoveShow.getPayeeRealName();
			//提现金额
			Integer consumeVal = userLoveShow.getConsumeVal();
			//提现类型
			Integer type = userLoveShow.getType();
			
			if(StringUtil.isNotEmpty(userId)){
				if(StringUtil.isNotEmpty(payeeRealName)){
					if(StringUtil.isNotEmpty(account)){
						if(consumeVal >= 50){
							if(type != null){
								if(StringUtil.isNotEmpty(userToken)){
									if(StringUtil.isNotEmpty(password)){
										//获取用户的登录 token
										String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
										String result_cache = cache.getCache(cache_key, String.class);
										//是否存在
										if(null != result_cache){
											//验证
											if(result_cache.equals(userToken)){
												//查询用户
												TzUser user = null; 
												user = userMapper.selectByPrimaryKey(userId);
												String code = user.getCode();
												if(null !=user){
													Integer Status = user.getStatus();
													if(Status ==1){
														if(user.getPassword().equals(IDUtils.md5(user+code))){
															//查询用户可提现金额
															userLoveExample = new TzUserLoveExample();
															criteria = userLoveExample.createCriteria();
															criteria.andUserIdEqualTo(userId);
															List<TzUserLove> loves= userLoveMapper.selectByExample(userLoveExample);	
															if(loves.size()>0){
																TzUserLove userLove = loves.get(0);
																//剩余的爱心值
																int oldLoveSurplus= userLove.getLoveSurplus();
																//可提取
																if(oldLoveSurplus >= consumeVal ){
																	//操作
																	//剩余减少
																	Integer loveSurplus =  oldLoveSurplus - consumeVal;
																	if(loveSurplus >= 50){
																		//查询用户是否授权成功
																		/*zfbUserInfoExample = new TzZfbUserInfoExample(); 
																		criteria5 = zfbUserInfoExample.createCriteria();
																		criteria5.andUserIdEqualTo(userId);
																		List<TzZfbUserInfo> zfbUserInfos = zfbUserInfoMapper.selectByExample(zfbUserInfoExample);
																		if(zfbUserInfos.size() > 0){
																			TzZfbUserInfo zfbUserInfo = zfbUserInfos.get(0);
																			//获取支付宝授权的id
																			String zfbUserId  = zfbUserInfo.getZfbUserId();
																			if(StringUtil.isNotEmpty(zfbUserId)){
												
													
																			}else{
																				msgResult = AppMsgResult.result(552,"请先进行支付宝账号授权！",null);
																			}
																		}else{
																			msgResult = AppMsgResult.result(552,"请先进行支付宝账号授权！",null);
																		}*/
																		//添加提现
																		String id = IDUtils.genId();
																		userLoveShow.setId(id);
																		userLoveShow.setCreatedTime(new Date());
																		userLoveShow.setUpdatedTime(new Date());
																		//添加消费记录
																		Integer fee = consumeVal*5/1000;
																		if(fee == 0){
																			fee = 1;
																		}else if(fee > 25){
																			fee = 25;
																		}
																		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
																		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
																		request.setBizContent("{" +
																		"\"out_biz_no\":\""+id+"\"," +
																		"\"payee_type\":\"ALIPAY_LOGONID\"," +
																		"\"payee_account\":\""+account+"\"," +
																		"\"amount\":\""+(consumeVal-fee)+"\"," +
												/*						"\"payer_show_name\":\"上海交通卡退款\"," +*/
																		"\"payee_real_name\":\""+payeeRealName+"\"," +
																		"\"remark\":\"爱心520商城爱心值提取！\"" +
																		"  }");
																		try {
																			AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
																			if(response.isSuccess()){
																				System.out.println("调用成功");
																				userLoveShow.setAccountTime(new Date());
																				userLoveShow.setStatus(1);
																				//修改状态
																				userLoveShowMapper.insertSelective(userLoveShow);
																				userLove.setLoveSurplus(loveSurplus);
																				//减少用户的总剩余值
																				userLoveMapper.updateByPrimaryKeySelective(userLove);
																				
																				TzUserLoveConsumptionDetails consumptionDetails = new TzUserLoveConsumptionDetails();
																				consumptionDetails.setId(IDUtils.genId());
																				consumptionDetails.setUserId(userId);
																				consumptionDetails.setConsumeVal("-"+(consumeVal-fee));
																				consumptionDetails.setCreatedTime(new Date());
																				consumptionDetails.setUpdatedTime(new Date());
																				consumptionDetails.setType(0);
																				consumptionDetails.setShowType(type);
																				consumptionDetails.setName("支付宝爱心值提取！");
																				consumptionDetails.setStatus(0);
																				consumptionDetails.setFee(fee.toString());
																				consumptionDetailsMapper.insertSelective(consumptionDetails);
																				//手续费
																				consumptionDetails.setId(IDUtils.genId());
																				consumptionDetails.setUserId(userId);
																				consumptionDetails.setConsumeVal("-"+fee);
																				consumptionDetails.setCreatedTime(new Date());
																				consumptionDetails.setUpdatedTime(new Date());
																				consumptionDetails.setType(0);
																				consumptionDetails.setShowType(type);
																				consumptionDetails.setName("提取爱心值手续费");
																				consumptionDetails.setStatus(0);
																				consumptionDetails.setFee("0");
																				consumptionDetailsMapper.insertSelective(consumptionDetails);
																				msgResult = AppMsgResult.result(200,"success",null );
																			} else {
																				System.out.println("调用失败");
																				msgResult = AppMsgResult.result(528,"服务器繁忙或账号和姓名不匹配，请仔细检查或者稍后重试！",null );
																			}
																		} catch (AlipayApiException e) {
																			// TODO Auto-generated catch block
																			e.printStackTrace();
																		}	
																	}else{
																		msgResult = AppMsgResult.result(553,"亲，没有这么多的爱心值可提取！",null);
																	}
																}else{
																	msgResult = AppMsgResult.result(553,"亲，没有这么多的爱心值可提取！",null );
																}
															}else{
																msgResult = AppMsgResult.result(554,"爱心值出现异常，请稍后重试！",null);
															}	
														}else{
															msgResult = AppMsgResult.result(565,"登录密码错误！",null);
														}
													}else{
														msgResult = AppMsgResult.result(535,"用户账号已被冻结！",null);
													}
												}else{
													msgResult = AppMsgResult.result(564,"用户未注册！",null);
												}					
											}else{
												msgResult = AppMsgResult.result(538,"用户未登录！",null );
											}
										}else{
											msgResult = AppMsgResult.result(538,"用户未登录！",null);
										}
									}else{
										msgResult = AppMsgResult.result(532, "密码不能为空！", null);	
									}
								}else{
									msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
								}
							}else{
								msgResult = AppMsgResult.result(555,"请先选择提现类型！", null);
							}
						}else{
							msgResult = AppMsgResult.result(556,"提现爱心值至少在50(包含50)以上！", null);
						}
					}else{
						msgResult = AppMsgResult.result(557,"支付宝账号不能为空！",null);
					}
				}else{
					msgResult = AppMsgResult.result(558,"支付宝真实收款人姓名不能为空！", null);
				}
			}else{
				msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(543,"参数不能为空！",null);
		}
		return msgResult;
	}

	@Override
	public AppMsgResult consumptionList(String userId, String userToken) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(StringUtil.isNotEmpty(userId)){
			if(StringUtil.isNotEmpty(userToken)){
				//获取用户的登录 token
				String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
				String result_cache = cache.getCache(cache_key, String.class);
				//是否存在
				if(null != result_cache){
					//验证
					if(result_cache.equals(userToken)){
						//封装map集合
				        Map<String,Object> map = new HashMap<String, Object>();
				        map.put("userId", userId);
						msgResult = AppMsgResult.result(200,"success",detailsMapperVo.consumptionList(map));
					}else{
						msgResult = AppMsgResult.result(538,"用户未登录！",null );
					}
				}else{
					msgResult = AppMsgResult.result(538,"用户未登录！",null);
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
	
	public static void main(String[] args) {
		/*AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,"json","utf-8",AlipayConfig.ZHFALIPAY_PUBLIC_KEY,"RSA2");
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();	
		String out_biz_no = "transfer" + System.currentTimeMillis() + (long)(Math.random()*100000L);
		request.setBizContent("{" +
		"\"out_biz_no\":\""+out_biz_no+"\"," +
		"\"payee_type\":\"ALIPAY_LOGONID\"," +
		"\"payee_account\":\"18398369380\"," +
		"\"amount\":\"0.1\"," +
		"\"payer_show_name\":\"转交店面\"," +
		"\"remark\":\"测试转账\"" +
		"  }");
		AlipayFundTransToaccountTransferResponse response;	
		try {
		response = alipayClient.execute(request);
		if(response.isSuccess()){	
		System.out.println("转账单号："+response.getOutBizNo());
		System.out.println("支付宝转账流水号："+response.getOrderId());
		System.out.println("时间："+response.getPayDate());
		} else {
		System.out.println("调用失败");
		}
		} catch (AlipayApiException e) {	
		e.printStackTrace();
		}*/
		Integer consumeVal = 4000;
		Integer fee = consumeVal*5/1000;
		if(fee == 0){
			fee = 1;
		}else if(fee > 25){
			fee = 25;
		}
		System.out.println(fee);
		
	}
	
}

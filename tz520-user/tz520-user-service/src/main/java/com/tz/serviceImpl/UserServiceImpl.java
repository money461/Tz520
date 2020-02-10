package com.tz.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOfflineMaterialImageUploadRequest;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipayOfflineMaterialImageUploadResponse;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.id.IDUtils;
import com.tz.interfaces.UserService;
import com.tz.mapper.TzAppMapper;
import com.tz.mapper.TzRecommendMapper;
import com.tz.mapper.TzUserLoveMapper;
import com.tz.mapper.TzUserMallMapper;
import com.tz.mapper.TzUserMapper;
import com.tz.mapper.TzZfbUserInfoMapper;
import com.tz.mapper.vo.TzUserMapperVo;
import com.tz.pojo.TzApp;
import com.tz.pojo.TzAppExample;
import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzRecommendExample;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserExample;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveExample;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzUserMallExample;
import com.tz.pojo.TzZfbUserInfo;
import com.tz.pojo.TzZfbUserInfoExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserVo;
import com.tz.pojo.vo.UserVo2;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.sdk.zfb.AlipayConfig;
import com.tz.sms.ALiDaYuUtil;
import com.tz.validate.ValidateUtil;


@Service
public class UserServiceImpl implements UserService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	//用户类
	@Autowired 
	TzUserMapper userMapper;	
	private TzUserExample example;
	private TzUserExample.Criteria criteria;
	
	//用户商城中间类
	@Autowired 
	TzUserMallMapper userMallMapper ;	
	private TzUserMallExample userMallExample;
	private TzUserMallExample.Criteria criteria2;
	
	//用户推荐类
	@Autowired 
	TzRecommendMapper recommendMapper;	
	private TzRecommendExample recommendExample;
	private TzRecommendExample.Criteria criteria3;
	//安卓app版本
	@Autowired 
	private TzAppMapper appMapper ;
	private TzAppExample appExample;
	private TzAppExample.Criteria criteria5;
	
	//用户支付宝授权登录类
	@Autowired 
	TzZfbUserInfoMapper zfbUserInfoMapper;	
	private TzZfbUserInfoExample zfbUserInfoExample;
	private TzZfbUserInfoExample.Criteria criteria4;
	
	@Autowired 
	private TzUserLoveMapper userLoveMapper;	
	private TzUserLoveExample userLoveExample;
	private TzUserLoveExample.Criteria userLoveCriteria;

	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private  CommonVo common;
    //用户扩展类
    @Autowired
	private TzUserMapperVo userMapperVo;
   
	
	@Override
	public AppMsgResult getUserList(Integer rows, Integer curPage) {
		AppMsgResult msgResult = null; 
		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+curPage+"|"+rows;
		//先去缓存中取
		List<TzUser> result_cache=cache.getListCache(cache_key, TzUser.class);
		if (result_cache != null) {
			LOG.info("get cache with key:"+cache_key);
		} else {
			//缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
			rows = rows == null?10:rows;
			curPage = curPage == null?1:curPage;
			example = new TzUserExample();
			//分页处理
	        PageHelper.startPage(curPage, rows);
	        
	        criteria = example.createCriteria();
			result_cache=userMapper.selectByExample(example);
			System.out.println(result_cache);
			cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
			LOG.info("put cache with key:"+cache_key);
		}
		msgResult = AppMsgResult.result(true, "", result_cache);
		return msgResult;
	}
	@Override
	public AppMsgResult getTelCode(String phone, String type) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(StringUtil.isNotEmpty(phone)){
			//格式校验
			if(ValidateUtil.phoneValidate(phone)){
				example = new TzUserExample(); 
				criteria = example.createCriteria();
				criteria.andPhoneEqualTo(phone);
				List<TzUser> users =  userMapper.selectByExample(example);
				int count =users.size();
				//注册获取验证码
				if("regist".equals(type)){
					//账号已经注册
					if(count > 0){
						msgResult = AppMsgResult.result(521,"手机号码已经被使用！", null);
					}else{
						//缓存用户验证码手机号码
						String cache_key = RedisCache.CAHCENAME+"|getTelCode|"+type+"|"+phone;
						//操作
						String result_cache = IDUtils.phoneCode();
						//判断操作
						String res = "";
						try {
							res = ALiDaYuUtil.sendMessage("爱心520商城","SMS_105250418",phone,result_cache);
						} catch (ClientException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							res = "";
						}
						if("OK".equals(res)){
							cache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.CODECAHCETIME);
							msgResult = AppMsgResult.result(200, "success", null);
						}else{
							msgResult = AppMsgResult.result(522, "发送验证码错误，请稍后重试！", null);
						}
					}
				//忘记密码获取验证码
				}else{
					if(count < 1){
						msgResult = AppMsgResult.result(523, "手机号码未注册！", null);
					}else{
						//操作
						//判断操作	
						//缓存用户验证码手机号码
						String cache_key=RedisCache.CAHCENAME+"|getTelCode|"+type+"|"+phone;
						//操作
						String result_cache = IDUtils.phoneCode();
						//判断操作
						String res = "";
						try {
							res = ALiDaYuUtil.sendMessage("爱心520商城","SMS_105250417",phone,result_cache);
						} catch (ClientException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							res = "";
						}
						if("OK".equals(res)){
							cache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.CODECAHCETIME);
							msgResult = AppMsgResult.result(200,"success" , null);
						}else{
							msgResult = AppMsgResult.result(522,"发送验证码错误，请稍后重试！" , null);
						}
					}
				}
			}else{
				msgResult = AppMsgResult.result(524, "手机号码格式错误！", null);
			}
		}else{
			msgResult = AppMsgResult.result(525, "手机号码不能为空！", null);
		}
		return msgResult;
	}
	@Transactional
	@Override
	public AppMsgResult regist(String phone, String passWord, String code,String recommendedCode) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(StringUtil.isNotEmpty(phone)){
			if(StringUtil.isNotEmpty(passWord)){
				if(StringUtil.isNotEmpty(code)){
					//手机号码判断
					if(ValidateUtil.phoneValidate(phone)){
						//注册
						example = new TzUserExample(); 
						criteria = example.createCriteria();
						criteria.andPhoneEqualTo(phone);
						List<TzUser> users =  userMapper.selectByExample(example);
						int count =users.size();
						//判断是否注册
						if(count > 0){
							msgResult = AppMsgResult.result(526, "手机号码已经被使用！","" );
						}else{
							String cache_key = RedisCache.CAHCENAME+"|getTelCode|regist|"+phone;
							//验证码
							String result_cache = cache.getCache(cache_key, String.class);
							System.out.println(result_cache);
							//验证码判断
							if(StringUtil.isNotEmpty(result_cache)){
								if(code.equals(result_cache)){
									//密码判断
									if(ValidateUtil.passWordValidate(passWord)){
										//推荐码
										boolean flag = false;
										//当推荐码不为空    判断推荐正确性
										//添加推荐关系
									    //商城id
									    String mallId = common.getMallId();
									    //得到推荐用户的信息
										TzUser retzUser = null;	
										 UserVo2  userVo2 = null;
										if(StringUtil.isNotEmpty(recommendedCode)){
											example = new TzUserExample(); 
											criteria = example.createCriteria();
											criteria.andRecommendedCodeEqualTo(recommendedCode);
											List<TzUser> tzUsers =  userMapper.selectByExample(example);
											if(tzUsers.size() > 0){
												retzUser = tzUsers.get(0);
												//未冻结
												if(retzUser.getStatus() == 1){
													//封装map集合
											        Map<String,Object> map = new HashMap<String, Object>();
											        map.put("mallId", mallId);
											        map.put("userId", retzUser.getId());
											        
											        userVo2 = userMapperVo.selectUserAndMailInner(map);
											        //推荐码的用户是否加入会员
											        if(null != userVo2){
											        	if(userVo2.getType() ==0){
											        		flag = true;
															msgResult = AppMsgResult.result(527, "推荐码错误或推荐码已经失效！",null);
											        	}
											        }
												}else{
													flag = true;
													msgResult = AppMsgResult.result(527, "推荐码错误或推荐码已经失效！",null);
												}
											}else{
												flag = true;
												msgResult = AppMsgResult.result(527, "推荐码错误或推荐码已经失效！",null);
											}
										}
										if(!flag){
											
											TzUser tzUser = new TzUser();
											String id = IDUtils.genId();
											tzUser.setUpdatedTime(new Date());
											//验证成功 添加用户数据
											tzUser.setCreatedTime(new Date());
											tzUser.setLastUpload(new Date());
											tzUser.setPhone(phone);
											tzUser.setId(IDUtils.genId());
											tzUser.setHeadUrl(Constant.DFHEAD2);
											String ucode = IDUtils.getCode();
											tzUser.setCode(ucode);
											tzUser.setId(id);
											tzUser.setUserNick("520_"+IDUtils.phoneCode());				
											tzUser.setPassword(IDUtils.md5(passWord+ucode));
											//推荐码
											String newRecommendedCode = IDUtils.phoneCode();
											tzUser.setRecommendedCode(newRecommendedCode);
											int res = userMapper.insertSelective(tzUser);
											
											//添加商城中间表
											TzUserMall userMall = new TzUserMall();
											userMall.setId(IDUtils.genId());
											userMall.setMallId(mallId);
											userMall.setUserId(id);
											userMall.setCreatedTime(new Date());
											userMall.setUpdatedTime(new Date());
											int res2 = userMallMapper.insertSelective(userMall);
											
											//添加用户爱心表
											TzUserLove love = new TzUserLove();
											love.setId(IDUtils.genId());
											love.setCreatedTime(new Date());
											love.setLastUpdatedTime(new Date());
											love.setLoveSurplus(0);
											love.setLoveTotal(0);
											love.setUpdatedTime(new Date());
											love.setUserId(id);
											love.setLoveSurplus(0);
											love.setLoveTotal(0);
											int res3 = userLoveMapper.insertSelective(love);
											//添加关联的推荐用户信息
											if(null != retzUser){
												//查询推荐用户的推荐信息，是否有城市爱心合伙人，这是查询推荐码上级的用户
												recommendExample = new TzRecommendExample(); 
												criteria3 = recommendExample.createCriteria();
												criteria3.andUserIdEqualTo(retzUser.getId());
												criteria3.andGradeEqualTo(1);
												List<TzRecommend> tzRecommends = recommendMapper.selectByExample(recommendExample);
												//查询是否存在城市爱心合伙人
												/*String cityLoveId = null;
												if(tzRecommends.size()>0){
													cityLoveId = tzRecommends.get(0).getCityLoveId();
												}*/
												//上级用户验证完 直接添加（一级）
												/*recommendMapper*/
												TzRecommend tzRecommend = new TzRecommend();
												tzRecommend.setId(IDUtils.genId());
												
												tzRecommend.setCreatedTime(new Date());
												tzRecommend.setUpdatedTime(new Date());
												tzRecommend.setUserId(id);
												tzRecommend.setRefereeId(retzUser.getId());
												tzRecommend.setGrade(1);
												tzRecommend.setRealId(retzUser.getId());
												//默认开始未开通不做爱心值计算
												tzRecommend.setIsplay(0);
												tzRecommend.setFeedbackStatus(0);
												tzRecommend.setFeedbackFee(common.getGradeOne());
												tzRecommend.setMallId(mallId);
												recommendMapper.insertSelective(tzRecommend);
												//城市爱心合伙人判断
												/*if(StringUtil.isNotEmpty(cityLoveId)){
													//封装map集合
											        Map<String,Object> map = new HashMap<String, Object>();
											        map.put("mallId", mallId);
											        map.put("userId", retzUser.getId());
											        UserVo2 userVo = userMapperVo.selectUserAndMailInner(map);
											        //查询城市爱心合伙人是否还生效  
											        //判断推荐的用户是否是城市爱心合伙人
											        if(null != userVo){ 
											        	if(userVo.getType() == 4){
											        		tzRecommend.setCityLoveId(cityLoveId);
															tzRecommend.setId(IDUtils.genId());
															tzRecommend.setRefereeId(cityLoveId);
															tzRecommend.setGrade(3);
															tzRecommend.setFeedbackFee(0);
															recommendMapper.insertSelective(tzRecommend);
											        	}
											        }
												}*/
												//查询上级的上级是否有用户
												if(tzRecommends.size()>0){
													//没必要判断， 后期可以开通
													/*cityLoveId = tzRecommends.get(0).getCityLoveId();*/
													//查询上级的上级是否存在（二级）
													TzRecommend recommend = tzRecommends.get(0);
													if(recommend.getIsplay()==1){
														//得到推荐用户的上级推荐用户是否满足条件--- 大于10个
														//封装map集合
												        Map<String,Object> map = new HashMap<String, Object>();
												        map.put("mallId", mallId);
												        map.put("userId", recommend.getRefereeId());
												        //查询用户类型
												        UserVo2  userVo = userMapperVo.selectUserAndMailInner(map);
												        if(null !=userVo){
												        	int type = userVo.getType();
												        	//类型为爱心合伙人以上 存在二级推荐费 礼包的10% 520 == 52
												        	if(type >= 3){
												        		tzRecommend.setId(IDUtils.genId());
																tzRecommend.setRefereeId(recommend.getRefereeId());
																tzRecommend.setGrade(2);
																tzRecommend.setFeedbackFee(common.getGradeTwo());
																recommendMapper.insertSelective(tzRecommend);
												        	}
												        } 
													}
												}
												
											}
											if(res == 1 && res2 == 1 && res3 ==1){
												//注册自动登录
												String login_key = RedisCache.CAHCENAME+"|getUserToken|"+tzUser.getId();
												String token = IDUtils.md5(tzUser.getId()+new Date()); 
												cache.putCacheWithExpireTime(login_key,token , RedisCache.USERCAHCETIME);
												
												//封装map集合
										        Map<String,Object> map = new HashMap<String, Object>();
										        map.put("mallId", mallId);
										        map.put("userId", id);
										        //封装用户信息
										        UserVo  userVo = userMapperVo.selectUserAndMail(map);
										        userVo.setUserToken(token);
												/*AppUser appUser = new AppUser();
												appUser.setTzUser(tzUser);
												appUser.setUserToken(token);
												//用户类型
												appUser.setType(0);*/
										        String qrCode = userVo.getQrCode();
												if(StringUtil.isNotEmpty(qrCode)){
													userVo.setQrCode(Constant.FILESERVER_URL+qrCode);
												}
												String headUrl = userVo.getHeadUrl();
												if(StringUtil.isNotEmpty(headUrl)){
													userVo.setHeadUrl(Constant.FILESERVER_URL+headUrl);
												}
												msgResult = AppMsgResult.result(200,"success",userVo);
											}else{
												msgResult = AppMsgResult.result(528,"服务器繁忙，请稍后重试！",null);
											}		
										}else{
											msgResult = AppMsgResult.result(527,"推荐码错误或推荐码已经失效！" ,null);
										}
									}else{
										msgResult = AppMsgResult.result(529,"密码必须为6-19位数字、字母组合！",null );
									}
								}else{
									msgResult = AppMsgResult.result(530, "验证码错误！",null);
								}	
							//code判断
							}else{
								msgResult = AppMsgResult.result(530,"验证码错误！" ,null);
							}
						}
						
						
					}else{
						msgResult = AppMsgResult.result(524,"手机号码格式错误！",null);
					}
				}else{
					msgResult = AppMsgResult.result(531,"验证码不能为空！", null);
				}
			}else{
				msgResult = AppMsgResult.result(532, "密码不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(533,"手机号码不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult findPassWord(String phone, String password, String code) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(StringUtil.isNotEmpty(phone)){
			if(StringUtil.isNotEmpty(password)){
				if(StringUtil.isNotEmpty(code)){
					//手机号码判断
					if(ValidateUtil.phoneValidate(phone)){
						example = new TzUserExample(); 
						criteria = example.createCriteria();
						criteria.andPhoneEqualTo(phone);
						List<TzUser> users =  userMapper.selectByExample(example);
						int count =users.size();
						//判断是否注册
						if(count < 1){
							msgResult = AppMsgResult.result(523, "手机号码未注册！",null );
						}else{
							String cache_key = RedisCache.CAHCENAME+"|getTelCode|find|"+phone;
							//验证码
							String result_cache = cache.getCache(cache_key, String.class);
							System.out.println(result_cache);
							//验证码判断
							if(StringUtil.isNotEmpty(result_cache)){
								if(code.equals(result_cache)){
									//密码判断
									if(ValidateUtil.passWordValidate(password)){
										TzUser tzUser = users.get(0);
										tzUser.setPassword(IDUtils.md5(password+tzUser.getCode()));
										int res = userMapper.updateByPrimaryKeySelective(tzUser);
										if(res == 1){
											msgResult = AppMsgResult.result(200, "success",tzUser);
										}else{
											msgResult = AppMsgResult.result(528,"操作失败，请稍后重试！" ,null);
										}
									}else{
										msgResult = AppMsgResult.result(529,"密码必须为6-19位数字、字母组合！",null );
									}
								}else{
									msgResult = AppMsgResult.result(530, "验证码错误",null);
								}	
							//code判断
							}else{
								msgResult = AppMsgResult.result(530,"验证码错误！" ,null);
							}
						}
					}else{
						msgResult = AppMsgResult.result(524,"手机号码格式错误！",null);
					}
				}else{
					msgResult = AppMsgResult.result(531,"验证码不能为空！", null);
				}
			}else{
				msgResult = AppMsgResult.result(532, "密码不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(533,"手机号码不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult login(String phone, String password) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(StringUtil.isNotEmpty(phone)){
			if(StringUtil.isNotEmpty(password)){
				//手机号码判断
				if(ValidateUtil.phoneValidate(phone)){
					//账号是否存在
					example = new TzUserExample(); 
					criteria = example.createCriteria();
					criteria.andPhoneEqualTo(phone);
					List<TzUser> users =  userMapper.selectByExample(example);
					if(users.size() > 0){
						TzUser  tzUser = users.get(0);
						String code = tzUser.getCode();
						Integer Status = tzUser.getStatus();
						if(Status ==1){
							if(tzUser.getPassword().equals(IDUtils.md5(password+code))){
								
								//单点登录
								String userId = tzUser.getId();
								String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
								
								String token = IDUtils.md5(tzUser.getId()+new Date()); 
								cache.putCacheWithExpireTime(cache_key,token , RedisCache.USERCAHCETIME);
								String result_cache = cache.getCache(cache_key, String.class);
								System.out.println("-----------------result_cache:"+result_cache);
								tzUser.setLastUpload(new Date());
								userMapper.updateByPrimaryKeySelective(tzUser);
								
								//查询用户升级的的等级资格
								recommendExample = new TzRecommendExample(); 
								criteria3 = recommendExample.createCriteria();
								criteria3.andRefereeIdEqualTo(userId);
								criteria3.andGradeEqualTo(1);
								criteria3.andIsplayEqualTo(1);
								int count = recommendMapper.countByExample(recommendExample);
								 //商城id
							    String mallId = common.getMallId();
								//是否满足条件
								boolean flag = false;
								//封装map集合
						        Map<String,Object> map2 = new HashMap<String, Object>();
						        map2.put("mallId", mallId);
						        map2.put("userId", userId);
						        //封装用户信息
						        UserVo2  userVo2 = userMapperVo.selectUserAndMailInner(map2);
						        //用户类型
						        int type = 0;
						        if(null != userVo2){
						        	type = userVo2.getType();
						        }
								if(count > 0){
									//爱心会员
									if(count >=10){
										if(type < 3){
											type = 3;
											flag = true;
										}
									//c爱心合伙人	
									}else if(count >= 2){
										if(type < 2){
											type = 2;
											flag = true;
										}
									}
								}
								//修改用户类型信息
								if(flag){
									userMallExample = new TzUserMallExample(); 
									criteria2 = userMallExample.createCriteria();
									criteria2.andUserIdEqualTo(userId);
									List<TzUserMall> userMalls = userMallMapper.selectByExample(userMallExample);
									if(userMalls.size()>0){
										TzUserMall mall = userMalls.get(0);
										mall.setType(type);
										userMallMapper.updateByPrimaryKeySelective(mall);
									}
								}
								//封装map集合
						        Map<String,Object> map = new HashMap<String, Object>();
						        map.put("mallId", mallId);
						        map.put("userId", userId);
						        //封装用户信息
						        UserVo  userVo = userMapperVo.selectUserAndMail(map);
						        userVo.setUserToken(token);
						        String qrCode = userVo.getQrCode();
								if(StringUtil.isNotEmpty(qrCode)){
									userVo.setQrCode(Constant.FILESERVER_URL+qrCode);
								}
								String headUrl = userVo.getHeadUrl();
								if(StringUtil.isNotEmpty(headUrl)){
									userVo.setHeadUrl(Constant.FILESERVER_URL+headUrl);
								}
								msgResult = AppMsgResult.result(200,"success",userVo);					
							}else{
								msgResult = AppMsgResult.result(534,"手机号码或者密码错误！",null);
							}
						}else{
							msgResult = AppMsgResult.result(535,"用户账号已被冻结！",null);
						}
						
					}else{
						msgResult = AppMsgResult.result(523,"手机号码未注册！",null);
					}
				}else{
					msgResult = AppMsgResult.result(524,"手机号码格式错误！",null);
				}
			}else{
				msgResult = AppMsgResult.result(532, "密码不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(533,"手机号码不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult updatePassWord(String id,String oldPassword,String password, String userToken) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(StringUtil.isNotEmpty(id)){
			if(StringUtil.isNotEmpty(userToken)){
				if(StringUtil.isNotEmpty(oldPassword)){
					if(StringUtil.isNotEmpty(password)){
						//密码格式判断
						if(ValidateUtil.passWordValidate(password)){
							//获取用户的登录 token
							String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+id;
							String result_cache = cache.getCache(cache_key, String.class);
							//是否存在
							if(null != result_cache){
								//验证
								if(result_cache.equals(userToken)){
									//修改
									TzUser tzUser = userMapper.selectByPrimaryKey(id);
									if(tzUser != null){
										String sqloldPassword = tzUser.getPassword();
										String code = tzUser.getCode();
										//原密码比较
										if(sqloldPassword.equals(IDUtils.md5(oldPassword+code))){
											tzUser.setPassword(IDUtils.md5(password+code));
											tzUser.setUpdatedTime(new Date());
											int res = userMapper.updateByPrimaryKeySelective(tzUser);
											if(res ==1){
												msgResult = AppMsgResult.result(200,"success",null );
											}else{
												msgResult = AppMsgResult.result(528,"修改失败，请稍后重试！",null );
											}
										}else{
											msgResult = AppMsgResult.result(536,"修改失败，原密码错误！",null);
										}
									}else{
										msgResult = AppMsgResult.result(537,"用户账号不存在！",null);
									}	
								}else{
									msgResult = AppMsgResult.result(538,"用户未登录！",null);
								}
							}else{
								msgResult = AppMsgResult.result(538,"用户未登录！",null );
							}
						}else{
							msgResult = AppMsgResult.result(529,"密码必须为6-19位数字、字母组合！",null);
						}
					}else{
						msgResult = AppMsgResult.result(539, "新密码不能为空！", null);
					}
				}else{
					msgResult = AppMsgResult.result(540, "原密码不能为空！", null);
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult updateUser(TzUser user, String userToken) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 
		//判断空
		if(null != user){
			String id = user.getId();
			String userNick = user.getUserNick();
			if(StringUtil.isNotEmpty(id)){
				boolean flag = false;
				if(StringUtil.isNotEmpty(userToken)){
					if(StringUtil.isNotEmpty(userNick)){
						if(userNick.length() <= 10){
						}else{
							flag = true;
							msgResult = AppMsgResult.result(559,"昵称长度不能大于10！",null);
						}
					}
					if(!flag){
						//获取用户的登录 token
						String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+id;
						String result_cache = cache.getCache(cache_key, String.class);
						//是否存在
						if(null != result_cache){
							//验证
							if(result_cache.equals(userToken)){
								//修改
								user.setUpdatedTime(new Date());
								int res = userMapper.updateByPrimaryKeySelective(user);
								if(res ==1){
									msgResult = AppMsgResult.result(200,"success",null);
								}else{
									msgResult = AppMsgResult.result(528,"服务器繁忙，请稍后重试！",null);
								}
							}else{
								msgResult = AppMsgResult.result(538,"用户未登录！",null );
							}
						}else{
							msgResult = AppMsgResult.result(538,"用户未登录！",null );
						}
					}
				}else{
					msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
				}
			}else{
				msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(543,"请传入参数！", null);
		}
		return msgResult;
	}
	
	@Override
	public AppMsgResult instantUpdateUser(String userId, String userToken) {
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
						String token = IDUtils.md5(userId+new Date()); 
						cache.putCacheWithExpireTime(cache_key,token , RedisCache.USERCAHCETIME);
						result_cache = cache.getCache(cache_key, String.class);
						System.out.println("-----------------result_cache:"+result_cache);
						//商城id
					    String mallId = common.getMallId();
					    
					   //查询用户升级的的等级资格
						recommendExample = new TzRecommendExample(); 
						criteria3 = recommendExample.createCriteria();
						criteria3.andRefereeIdEqualTo(userId);
						criteria3.andGradeEqualTo(1);
						criteria3.andIsplayEqualTo(1);
						int count = recommendMapper.countByExample(recommendExample);
						//是否满足条件
						boolean flag = false;
						//封装map集合
				        Map<String,Object> map2 = new HashMap<String, Object>();
				        map2.put("mallId", mallId);
				        map2.put("userId", userId);
				        //封装用户信息
				        UserVo2  userVo2 = userMapperVo.selectUserAndMailInner(map2);
				        //用户类型
				        int type = 0;
				        if(null != userVo2){
				        	type = userVo2.getType();
				        }
						if(count > 0){
							//爱心会员
							if(count >=10){
								if(type < 3){
									type = 3;
									flag = true;
								}
							//c爱心合伙人	
							}else if(count >= 2){
								if(type < 2){
									type = 2;
									flag = true;
								}
							}
						}
						//修改用户类型信息
						if(flag){
							userMallExample = new TzUserMallExample(); 
							criteria2 = userMallExample.createCriteria();
							criteria2.andUserIdEqualTo(userId);
							List<TzUserMall> userMalls = userMallMapper.selectByExample(userMallExample);
							if(userMalls.size()>0){
								TzUserMall mall = userMalls.get(0);
								mall.setType(type);
								userMallMapper.updateByPrimaryKeySelective(mall);
							}
						}
						
						//封装map集合
				        Map<String,Object> map = new HashMap<String, Object>();
				        map.put("mallId", mallId);
				        map.put("userId", userId);
					    //用户的信息
				        UserVo  userVo = new UserVo();
				        userVo = userMapperVo.selectUserAndMail(map);
				        if(userVo != null){
				        	 userVo.setUserToken(token);
				        	int status = userVo.getStatus();
							if(status ==1 ){
								String qrCode = userVo.getQrCode();
								if(StringUtil.isNotEmpty(qrCode)){
									userVo.setQrCode(Constant.FILESERVER_URL+qrCode);
								}
								String headUrl = userVo.getHeadUrl();
								if(StringUtil.isNotEmpty(headUrl)){
									userVo.setHeadUrl(Constant.FILESERVER_URL+headUrl);
								}
								msgResult = AppMsgResult.result(200,"success",userVo);
							}else{
								msgResult = AppMsgResult.result(535,"用户账号已被冻结！",null );
							} 
				        }else{
				        	msgResult = AppMsgResult.result(537,"用户账号不存在！",null );	
				        }		        
					}else{
						msgResult = AppMsgResult.result(538,"用户未登录！",null);
					}
				}else{
					msgResult = AppMsgResult.result(538,"用户未登录！",null );
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult alipayLogin(String userId, String userToken) {
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
						AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
						
						AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
						
						request.setGrantType("authorization_code");
						request.setCode("4b203fe6c11548bcabd8da5bb087a83b");
					
						
						AlipaySystemOauthTokenResponse response = null;
						try {
							response = alipayClient.execute(request);
							System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
							msgResult = AppMsgResult.result(200, "ok", response.getBody());
						} catch (AlipayApiException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(response.isSuccess()){
							System.out.println("调用成功");
						} else {
							System.out.println("调用失败");
						}		
					}else{
						msgResult = AppMsgResult.result(538,"用户未登录！",null);
					}
				}else{
					msgResult = AppMsgResult.result(538,"用户未登录！",null );
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult alipayLoginAuth(String userId, String userToken) {
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
						String sign;
						try {
							/*sign = AlipaySignature.rsaSign(keyValues, AlipayConfig.APP_PRIVATE_KEY, "utf-8", "RSA2");*/
							//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify 
							Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap("2088821325292062", "2017102109434079", "kkkkk098999",true);
							
							String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
							sign = OrderInfoUtil2_0.getSign(authInfoMap, AlipayConfig.APP_PRIVATE_KEY, true);
							final String authInfo = info + "&sign=" + sign;
							System.out.println(authInfo);
							
							msgResult = AppMsgResult.result(200,"success",authInfo);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						msgResult = AppMsgResult.result(538,"用户未登录！",null);
					}
				}else{
					msgResult = AppMsgResult.result(538,"用户未登录！",null );
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult alipayLoginAuthNotify(String userId, String userToken,String zfbAuthCode,String zfbUserId) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null; 	
		//判断空
		if(StringUtil.isNotEmpty(userId)){
			if(StringUtil.isNotEmpty(userToken)){
				if(StringUtil.isNotEmpty(zfbAuthCode)){
					if(StringUtil.isNotEmpty(zfbUserId)){
						//获取用户的登录 token
						String cache_key=RedisCache.CAHCENAME+"|getUserToken|"+userId;
						String result_cache = cache.getCache(cache_key, String.class);
						//是否存在
						if(null != result_cache){
							//验证
							if(result_cache.equals(userToken)){
								//
								zfbUserInfoExample = new TzZfbUserInfoExample(); 
								criteria4 = zfbUserInfoExample.createCriteria();
								criteria4.andUserIdEqualTo(userId);
								List<TzZfbUserInfo> zfbUserInfos = zfbUserInfoMapper.selectByExample(zfbUserInfoExample);
								TzZfbUserInfo zfbUserInfo = null;
								int res = 0;
								if(zfbUserInfos.size() > 0){
									zfbUserInfo = zfbUserInfos.get(0);
									zfbUserInfo.setUpdatedTime(new Date());
									zfbUserInfo.setZfbAuthCode(zfbAuthCode);
									zfbUserInfo.setZfbUserId(zfbUserId);
									res = zfbUserInfoMapper.updateByPrimaryKeySelective(zfbUserInfo);
								}else{
									zfbUserInfo = new TzZfbUserInfo();
									zfbUserInfo.setId(IDUtils.genId());
									zfbUserInfo.setCreatedTime(new Date());
									zfbUserInfo.setUpdatedTime(new Date());
									zfbUserInfo.setZfbAuthCode(zfbAuthCode);
									zfbUserInfo.setZfbUserId(zfbUserId);
									zfbUserInfo.setUserId(userId);
									res = zfbUserInfoMapper.insertSelective(zfbUserInfo);
								}
								if(res == 1){
									msgResult = AppMsgResult.result(200,"success！",null);
								}else{
									msgResult = AppMsgResult.result(544,"添加支付宝登录授权信息失败，请重新授权！",null);
								}
							}else{
								msgResult = AppMsgResult.result(538,"用户未登录！",null);
							}
						}else{
							msgResult = AppMsgResult.result(538,"用户未登录！",null );
						}
					}else{
						msgResult = AppMsgResult.result(545, "支付宝授权id不能为空！", null);
					}
				}else{
					msgResult = AppMsgResult.result(546, "支付宝授权code不能为空！", null);
				}
			}else{
				msgResult = AppMsgResult.result(541, "用户token不能为空！", null);
			}
		}else{
			msgResult = AppMsgResult.result(542,"用户id不能为空！", null);
		}
		return msgResult;
	}
	@Override
	public AppMsgResult appVerison() {
		AppMsgResult msgResult = null; 	
		// TODO Auto-generated method stub
		appExample = new TzAppExample();
		criteria5 = appExample.createCriteria();
		appExample.setOrderByClause(" created_time DESC ");	
		List<TzApp> apps= appMapper.selectByExample(appExample);
		if(apps.size() > 0){
			TzApp app= apps.get(0);
			app.setDown(Constant.FILESERVER_URL+app.getDown());
			msgResult = AppMsgResult.result(200, "success", apps.get(0));
		}else{
			msgResult = AppMsgResult.result(563, "app没有更新的版本信息", null);
		}
		return msgResult;
	}
	
	
}

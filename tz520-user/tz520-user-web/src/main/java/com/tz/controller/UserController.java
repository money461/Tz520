package com.tz.controller;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.tz.interfaces.PayService;
import com.tz.interfaces.UserService;
import com.tz.pojo.TzUser;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.res.MsgResult;
import com.tz.sdk.weixin.Sign;
import com.tz.sdk.weixin.WeChatPayConfig;
import com.tz.service.FileService;
import com.tz.service.PayServiceOn;



@RestController
@RequestMapping("app/api")
public class UserController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private PayServiceOn payServiceOn;
	
	
	/**
	 * 获取会员列表信息
	 * @param curPage
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AppMsgResult getUserList(int curPage,int rows) {
		LOG.info("invoke----------/user/list");
		/*String ss = payService.hello("111111111111");*/
		/*System.out.println("----------"+ss);*/
		return userService.getUserList(rows, curPage);
	}
	/**
	 * 获取手机验证码
	 * @param phone
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getTelCode/{phone}/{type}", method = RequestMethod.GET)
	public AppMsgResult getTelCode(@PathVariable("phone") String phone,@PathVariable("type") String type) {
		LOG.info("invoke----------/user/getTelCode");
		return userService.getTelCode(phone, type);
	}
	/**
	 * 用户注册
	 * @param phone
	 * @param passWord
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public AppMsgResult regist(String phone,String password,String code,String recommendedCode) {
		LOG.info("invoke----------/user/getTelCode");
		return userService.regist(phone, password, code,recommendedCode);
	}
	/**
	 * 找回密码
	 * @param phone
	 * @param passWord
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/findPassWord", method = RequestMethod.POST)
	public AppMsgResult findPassWord(String phone,String password,String code) {
		LOG.info("invoke----------/user/findPassWord");
		return userService.findPassWord(phone, password, code);
	}
	/**
	 * 用户登陆
	 * @param phone
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public AppMsgResult login(String phone,String password) {
		LOG.info("invoke----------/user/login");
		return userService.login(phone, password);
	}
	/**
	 * 修改密码 
	 * @param id
	 * @param password
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
	public AppMsgResult updatePassWord(String userId,String oldPassword,String password,String userToken) {
		LOG.info("invoke----------/user/updatePassWord");
		return userService.updatePassWord(userId,oldPassword, password, userToken);
	}
	/**
	 * 修改用户资料
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public AppMsgResult updateUser(TzUser user,String userToken) {
		LOG.info("invoke----------/user/updateUser");
		return userService.updateUser(user, userToken);
	}
	/**
	 * 上传二维码
	 * @param file
	 * @param user
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/updateQRCode", method = RequestMethod.POST)
	public AppMsgResult updateQRCode(@RequestParam("file")MultipartFile file,TzUser user,String userToken) {
		LOG.info("invoke----------/user/updateQRCode");
		AppMsgResult msgResult = null;
		if(null != user){
			msgResult = fileService.uploadHead(user.getId(), userToken,file);
			if(200 == (int)msgResult.getFlag()){
				user.setQrCode((String)msgResult.getData());
				msgResult = userService.updateUser(user, userToken);
			}
		}else{
			msgResult = AppMsgResult.result(521,"参数错误！", "");
		} 
		return msgResult;
	}
	/**
	 * 上传头像
	 * @param file
	 * @param id
	 * @param userToken
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadHead", method = RequestMethod.POST)
	public AppMsgResult uploadHead(@RequestParam("file")MultipartFile file,String userId,String userToken) throws IOException {
		LOG.info("invoke----------/user/uploadHead");
		AppMsgResult msgResult = null;
		msgResult = fileService.uploadHead(userId, userToken,file);
		if(200 == (int)msgResult.getFlag()){
			TzUser user  = new TzUser();
			String headUrl = (String)msgResult.getData();
			user.setId(userId);
			user.setHeadUrl(headUrl);
			msgResult = userService.updateUser(user, userToken);
			if(200 == (int)msgResult.getFlag()){
				msgResult = AppMsgResult.result(200, "success", headUrl);
			}
		}
		return msgResult;
		
	}
	/**
	 * 删除用户图片
	 * @param id
	 * @param userToken
	 * @param fileUrl
	 * @return
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public AppMsgResult deleteFile(String userId,String userToken,String fileUrl) {
		LOG.info("invoke----------/user/deleteFile");
		return fileService.deleteFile(userId, userToken, fileUrl);
	}
	/**
	 * 支付宝回调接口
	 * @param request
	 * @param httpResponse
	 * @throws IOException
	 */
	@RequestMapping(value = "/zfbAPPnotify", method = RequestMethod.POST)
	public void zfbAPPnotify(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
		//获取支付宝POST过来反馈信息
		LOG.info("invoke----------/user/zfbAPPnotify");
		payServiceOn.zfbAPPnotify(request, httpResponse);
	}
	/**
	 * 支付宝app支付接口
	 * @param userId
	 * @param type
	 * @param totalAmount
	 * @return
	 */
	@RequestMapping(value = "/zfbAPPaliPay", method = RequestMethod.POST)
	public AppMsgResult zfbAPPaliPay(String userId,String type,String userToken,String orderId) {
		LOG.info("invoke----------/user/zfbAPPaliPay");
		return payService.zfbAPPaliPay(userId,type,userToken,orderId);
	}  
	/**
	 * 支付宝web支付接口
	 * @param userId
	 * @param type
	 * @param totalAmount
	 * @return
	 */
	@RequestMapping(value = "/zfbWebAliPay", method = RequestMethod.POST)
	public AppMsgResult zfbWebAliPay(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String userId,String type,String userToken,String orderId) {
		LOG.info("invoke----------/user/zfbWebAliPay");
		return payServiceOn.zfbWebAliPay(httpRequest, httpResponse, userId,type,userToken,orderId);
	}
	/**
	 * 即时更新用户信息
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/instantUpdateUser", method = RequestMethod.POST)
	public AppMsgResult instantUpdateUser(String userId,String userToken) {
		LOG.info("invoke----------/user/instantUpdateUser");
		return userService.instantUpdateUser(userId, userToken);
	}
	/**
	 * 支付宝登录
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/alipayLogin", method = RequestMethod.POST)
	public AppMsgResult alipayLogin(String userId,String userToken) {
		LOG.info("invoke----------/user/alipayLogin");
		return userService.alipayLogin(userId, userToken);
	}
	/**
	 * 支付宝登录授权
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/alipayLoginAuth", method = RequestMethod.POST)
	public AppMsgResult alipayLoginAuth(String userId,String userToken) {
		LOG.info("invoke----------/user/alipayLoginAuth");
		return userService.alipayLoginAuth(userId, userToken);
	}
	/**
	 * 支付宝登录授权信息回调添加
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/alipayLoginAuthNotify", method = RequestMethod.POST)
	public AppMsgResult alipayLoginAuthNotify(String userId,String userToken,String zfbAuthCode,String zfbUserId) {
		LOG.info("invoke----------/user/alipayLoginAuthNotify");
		return userService.alipayLoginAuthNotify(userId, userToken, zfbAuthCode, zfbUserId);
	}
	@RequestMapping(value = "/file") 
	public ModelAndView file() {
		LOG.info("invoke----------/user/file");
		return new ModelAndView("/file.html");
	}
	/**
	 * 订单物流信息
	 * @param com
	 * @param no
	 * @return
	 */
	@RequestMapping(value = "/orderLogistics/{com}/{no}", method = RequestMethod.GET)
	public AppMsgResult orderLogistics(@PathVariable("com") String com,@PathVariable("no") String no) {
		return JuheDemo.getRequest1(com,no);
	}
	/**
	 * 更新版本
	 * @param com
	 * @param no
	 * @return
	 */
	@RequestMapping(value = "/appVerison", method = RequestMethod.GET)
	public AppMsgResult appVerison() {
		LOG.info("invoke----------/user/appVerison");
		return userService.appVerison();
	}
	/**
	 * 微信分享后台配置
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/wechatShare", method = RequestMethod.POST)
	public AppMsgResult weixinShare(String url) {
		 LOG.info("invoke----------/wechatShare");
         // 注意 URL 一定要动态获取，不能 hardcode
        /* String url = "http://example.com";*/
         return payServiceOn.wechatShare(url);
	}
	/**
	 * 微信公众号支付
	 * @param request
	 * @param userId
	 * @param code
	 * @param userToken
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/wechatWebPay", method = RequestMethod.POST)
	public AppMsgResult weixinWebPAy(HttpServletRequest request,String userId,String code,String userToken,String orderId) {
		 LOG.info("invoke----------/wechatWebPAy");
         return payServiceOn.wechatWebPAy(request,userId,code,userToken,orderId);
	}
	/**
	 * 微信 app支付
	 * @param request
	 * @param userId
	 * @param userToken
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/wechatAppPay", method = RequestMethod.POST)
	public AppMsgResult weixinWebPAy(HttpServletRequest request,String userId,String userToken,String orderId) {
		 LOG.info("invoke----------/wechatAppPay");
         return payServiceOn.wechatAppPAy(request,userId,userToken,orderId);
	}
	/**
	 * 微信 app支付回调
	 * @param request
	 * @return 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/wechatWebNotify", method = RequestMethod.POST)
	public void wechatWebNotify(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 LOG.info("invoke----------/wechatWebNotify");
         payServiceOn.wechatWebNotify(request, response);
	}
	/**
	 * 微信公众号支付回调
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/wechatAppNotify", method = RequestMethod.POST)
	public void wechatAppNotify(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 LOG.info("invoke----------/wechatAppNotify");
         payServiceOn.wechatAppNotify(request, response);
	}

	
}

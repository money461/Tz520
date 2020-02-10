package com.tz.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.tz.res.AppMsgResult;
import com.tz.res.MsgResult;

public interface PayServiceOn {
	
	public void zfbAPPnotify(HttpServletRequest request, HttpServletResponse httpResponse);
	//支付宝手机网页支付
	public AppMsgResult zfbWebAliPay(HttpServletRequest httpRequest,HttpServletResponse httpResponse,String userId,String type,String userToken,String orderId);
	//微信公众号支付
	public AppMsgResult wechatWebPAy(HttpServletRequest request,String userId,String code,String userToken,String orderId);
	//微信公众号
	public AppMsgResult wechatShare(String url);
	//微信App支付
	public AppMsgResult wechatAppPAy(HttpServletRequest request,String userId,String userToken,String orderId);
	//微信公众号支付回调
	public void wechatWebNotify(HttpServletRequest request,HttpServletResponse response) throws IOException;
	//微信App支付回调
	public void wechatAppNotify(HttpServletRequest request,HttpServletResponse response) throws IOException;
	
	
}

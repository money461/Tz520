package com.tz.interfaces;

import com.tz.pojo.TzUser;
import com.tz.res.AppMsgResult;


public interface UserService {

	//用户会员列表数据
	AppMsgResult getUserList(Integer rows, Integer curPage);
	//获取用户手机验证码 type -- regist:注册 updateL:修改密码
	AppMsgResult getTelCode(String phone,String type);
	//用户注册
	AppMsgResult regist(String phone,String password,String code,String recommendedCode);
	//用户找回密码
	AppMsgResult findPassWord(String phone,String password,String code);
	//用户登录
	AppMsgResult login(String phone,String passWord);
	//根据用户id修改密码 userToken：用户登录返回token
	AppMsgResult updatePassWord(String userId,String oldPassword,String password,String userToken);
	//根据用户id修改用户资料
	AppMsgResult updateUser(TzUser user,String userToken);
	//即时更新用户信息
	AppMsgResult instantUpdateUser(String userId,String userToken);
	//支付宝登录
	AppMsgResult alipayLogin(String userId,String userToken);
	//支付宝登录授权
	AppMsgResult alipayLoginAuth(String userId,String userToken);
	//支付宝登录授权记录或者修改zfd登录id和code
	AppMsgResult alipayLoginAuthNotify(String userId,String userToken,String zfbAuthCode,String zfbUserId);
	//app更新信息
	AppMsgResult appVerison();


}

package com.tz.controller;


import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.id.IDUtils;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUser;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;

import com.tz.service.UserService;

/**
 * 用户 
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	/**
	 * 登录页面
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/loginPage")
	public ModelAndView loginPage(){
		return new ModelAndView("tz520/login");
	}
	/**
	 * 登录页面
	 * @param httpSession
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/login")
	public MsgResult toLogin(String phone,String password,HttpSession session) throws Exception{
		//根据用户名，查询管理员表信息对应的 密码，比较密码是否相同
		MsgResult msgResult = userService.login(phone, password);
		if(msgResult.getFlag()){
			//将用户信息保存在session中
			TzUser user = (TzUser) msgResult.getData();
			session.setAttribute("user",user);
			//调用shiro框架，将当前账户信息存入token
			//获取当前登录账户对象
			Subject subject = SecurityUtils.getSubject();
			//设置用户名和密码保存至token对象中
			UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
			//调用realm中实现方法doGetAuthenticationInfo 认证实现
			subject.login(token);
		}
		return msgResult;
	}
	/**
	 * 登录页面跳转至主页面
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView andView = new ModelAndView("tz520/index");
		//登录用户的id
    	Subject subject = SecurityUtils.getSubject();
    	TzUser user = (TzUser)subject.getSession().getAttribute("user");
    	andView.addObject("phone", user.getPhone());
    	LOG.info("invoke----------/----------"+user.getPhone());
		return andView;
	}
	/**
	 * 退出账户
	 * @return
	 */
	@GetMapping("/logout")
	public ModelAndView logout(){
		//获取当前登录账户信息
	  Subject subject = SecurityUtils.getSubject();
	  subject.logout();
	 /* Session session = subject.getSession();
	  session.removeAttribute("managerName");*/
	  return new ModelAndView("tz520/login");
	}


}

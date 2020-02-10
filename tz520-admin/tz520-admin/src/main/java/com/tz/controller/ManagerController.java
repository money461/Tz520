package com.tz.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzManagerVo;
import com.tz.res.MsgResult;

import com.tz.service.ManagerService;

/**
 * 管理员
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/manager")
public class ManagerController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ManagerService managerService; 
	
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
	 */
	@GetMapping("/login")
	public MsgResult toLogin(String managerName,String password,HttpSession session){
		//根据用户名，查询管理员表信息对应的 密码，比较密码是否相同
		MsgResult msgResult = managerService.findByManagerName(managerName,password);
		if(msgResult.getFlag()){
			//将用户信息保存在session中
			TzManager manager = (TzManager) msgResult.getData();
			session.setAttribute("manager",manager);
			//调用shiro框架，将当前账户信息存入token
			//获取当前登录账户对象
			Subject subject = SecurityUtils.getSubject();
			//设置用户名和密码保存至token对象中
			UsernamePasswordToken token = new UsernamePasswordToken(managerName, password);
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
		//获取当前登录账户对象
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		String managerName = manager.getManagerName();
		return new ModelAndView("tz520/index","managerName",managerName);
	}
	
	/**
	 * 进入欢迎界面
	 * @return
	 */
	@RequestMapping("/welcome")
	public ModelAndView welcome(){
		//获取当前登录账户对象
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		String managerName = manager.getManagerName();
		return new ModelAndView("tz520/welcome","managerName",managerName);
	}
	
	/**
	 * 跳转至管理员列表视图
	 * @return
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/manager/listPage");
		return new ModelAndView("tz520/manager/list");
	}
	
	/**
	 * 查看管理员列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@GetMapping("/list")
	public MsgResult list(TzManagerVo tzManagerVo, int curPage,int rows){
		LOG.info("invoke----------/manager/list");
		return managerService.getManagerList(tzManagerVo,curPage,rows);
		
	}
	
	/**
	 * 跳转至添加管理员弹出窗口或者更新窗口
	 * @param type
	 * @return
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,String id){
		LOG.info("invoke----------/manager/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/manager/add");
		}else{
			return new ModelAndView("tz520/manager/update","id",id);
		}
		
	}
	
	/**
	 * 添加管理员
	 * @param TzManager
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addManager")
	public MsgResult adduser(TzManager tzManager,String roleIds) throws Exception {
		LOG.info("invoke----------/manager/TzManager");
		MsgResult msgResult =  managerService.addmanager(tzManager,roleIds);
		return msgResult;
	}
	
	/**
	 * 根据id查询管理员用户信息,在update.ftl页面进行回显
	 * @param id
	 * @return
	 */
	@PostMapping("/findManagerById")
	public MsgResult findManagerById(String id){
		MsgResult result = managerService.findManagerById(id);
	  return result;
	}
	
	/**
	 * 编辑后更新管理员信息和管理员角色中间表信息
	 * @param tzManager
	 * @param roleId
	 * @return
	 */
	@PostMapping("/updateManager")
	public MsgResult updateManager(TzManager tzManager,String roleIds){
		MsgResult result = managerService.updateManager(tzManager,roleIds);
		return result;
	}
	
	/**
	 * 删除管理员信息，并删除管理员角色中间表信息
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteById")
	public MsgResult delManagerById(String id){
		MsgResult result = managerService.delManagerById(id);
		return result;
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

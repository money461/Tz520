package com.tz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	 * 用户列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/user/listPage");
		return new ModelAndView("tz520/user/list");
	}
	/**
	 * 用户列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(UserVo userVo,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/user/list");
		return userService.getUserList(userVo,rows, curPage);
	}
	/**
	 * 用户列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,String id) throws Exception {
		LOG.info("invoke----------/user/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/user/add");
		}else{
			ModelAndView andView = new ModelAndView();
			andView.setViewName("tz520/user/update");
			andView.addObject("id", id);
			return andView;
		}
	}
	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("addOrUpdate")
	public MsgResult addOrUpdate(TzUser user,String type,Integer userType,String oldUserName,String oldPhone,String userMallId) throws Exception {
		LOG.info("invoke----------/user/adduser");
		System.out.println("添加用户");
		return userService.addOrUpdate(user, type,userType,oldUserName,oldPhone,userMallId);
	}
	/**
	 * 用户账号停用启动
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("updateStatusById")
	public MsgResult updateStatusById(String id,Integer status) throws Exception {
		LOG.info("invoke----------/user/updateStatusById");
		return userService.updateStatusById(id, status);
	}
	/**
	 * 用户账号删除
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteById")
	public MsgResult deleteById(String id) throws Exception {
		LOG.info("invoke----------/user/deleteById");
		return userService.deleteById(id);
	}
	/**
	 * 根据id查询用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@GetMapping("selectById")
	public MsgResult selectById(String id) throws Exception {
		LOG.info("invoke----------/user/selectById");
		return userService.selectById(id);
	}

}

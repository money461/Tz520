package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzIntentionalUser;
import com.tz.pojo.vo.TzIntentionalUserVo;
import com.tz.res.MsgResult;
import com.tz.service.IntentionalUserService;
/**
 * 意向用户表管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/tzIntentionalUser")
public class IntentionalUserController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IntentionalUserService intentionalUserService;
	
	/**
	 * 意向用户列表数据信息
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/tzIntentionalUser/listPage");
		return new ModelAndView("tz520/intentionalUser/list");
	}
	/**
	 * 意向用户列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(TzIntentionalUserVo tzIntentionalUserVo,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/tzIntentionalUser/list");
		return intentionalUserService.getIntentionalUserList(tzIntentionalUserVo,rows, curPage);
	}
	
	/**
	 * 添加意向用户
	 * @return
	 */
	@PostMapping("/addIntentionalUser")
	public MsgResult addIntentionalUser(TzIntentionalUser tzIntentionalUser){
		LOG.info("invoke----------tzIntentionalUser/addIntentionalUser");
		return intentionalUserService.addIntentionalUser(tzIntentionalUser);
	}
	
}

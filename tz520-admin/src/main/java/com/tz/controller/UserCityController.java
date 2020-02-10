package com.tz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzCity;
import com.tz.pojo.TzUserCity;
import com.tz.pojo.vo.CityVo;
import com.tz.res.MsgResult;
import com.tz.service.CityService;
import com.tz.service.UserCityService;

/**
 * 城市类
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/userCity")
public class UserCityController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserCityService userCityService;
	
	/**
	 * 团队或者城市合伙人代理的城市列表页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage(String userId,String type) throws Exception {
		LOG.info("invoke----------/userCity/listPage");
		ModelAndView andView = new ModelAndView();
		if("team".equals(type)){
			andView.setViewName("tz520/user/team/list");
			andView.addObject("userId", userId);
		}else{
			andView.setViewName("tz520/user/partnership/list");
			andView.addObject("userId", userId);
		}
		return andView;
	}
	/**
	 * 城市列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(CityVo cityVo,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/userCity/list");
		return userCityService.getUserCityList(cityVo, rows, curPage);
	}  
	/**
	 * 团队或者城市合伙人代理的城市添加页面
	 * @return
	 * @throws Exception
	 * 
	 */
	@GetMapping("/addPage")
	public ModelAndView addPage(String userId,String type) throws Exception {
		LOG.info("invoke----------/userCity/addPage");
		ModelAndView andView = new ModelAndView();
		if("team".equals(type)){
			andView.setViewName("tz520/user/team/add");
			andView.addObject("userId", userId);
		}else{
			andView.setViewName("tz520/user/partnership/add");
			andView.addObject("userId", userId);
		}
		return andView;
	}
	/**
	 * 用户城市中间表添加
	 * @return
	 * @throws Exception
	 * 
	 */
	@PostMapping("/add")
	public MsgResult addPage(TzUserCity userCity) throws Exception {
		LOG.info("invoke----------/userCity/add");
		return userCityService.add(userCity);
	}
	/**
	 * 根据id取消代理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deleteById")
	public MsgResult deleteById(String id) throws Exception {
		LOG.info("invoke----------/userCity/deleteById");
		return userCityService.deleteById(id);
	}
	
}

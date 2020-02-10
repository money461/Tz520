package com.tz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzUserLove;
import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveVo;
import com.tz.res.MsgResult;
import com.tz.service.UserLoveConsumptionDetailsService;
import com.tz.service.UserloveService;

/**
 * 用户爱心值消费明细类
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/userLoveDetails")
public class UserLoveDetailsController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserLoveConsumptionDetailsService detailsService;
	
	/**
	 * 所有用户的爱心值消费列表页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/userLoveDetails/listPage");
		return new ModelAndView("tz520/userLoveDetails/list");
	}
	/**
	 * 查询所有用户的爱心值消费明细
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(UserLoveConsumptionDetailsVo consumptionDetailsVo,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/userLoveDetails/list");
		return detailsService.getUserLoveDetailList(consumptionDetailsVo, rows, curPage);
	}
}

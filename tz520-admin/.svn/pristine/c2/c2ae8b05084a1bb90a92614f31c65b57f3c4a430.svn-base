package com.tz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzApp;
import com.tz.pojo.TzUserLove;
import com.tz.pojo.TzUserLoveShow;
import com.tz.pojo.vo.UserLoveConsumptionDetailsVo;
import com.tz.pojo.vo.UserLoveShowVo;
import com.tz.pojo.vo.UserLoveVo;
import com.tz.res.MsgResult;
import com.tz.service.AppService;
import com.tz.service.UserLoveConsumptionDetailsService;
import com.tz.service.UserLoveShowService;
import com.tz.service.UserloveService;

/**
 * 安卓app管理类
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/appDown")
public class TzAppController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppService appService;
	/**
	 * 添加app上架记录页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addPage")
	public ModelAndView addPage() throws Exception {
		LOG.info("invoke----------/appDown/addPage");
		return new ModelAndView("tz520/appDown/add");
	}
	/**
	 * 添加app上架记录
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	public MsgResult list(TzApp app) throws Exception {
		LOG.info("invoke----------/appDown/add");
		return appService.add(app);
	}
	/**
	 * 所有安卓版本列表页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/appDown/listPage");
		return new ModelAndView("tz520/appDown/list");
	}
	/**
	 * 所有安卓版本列表数据
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(TzApp app,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/appDown/list");
		return appService.findAll(rows, curPage);
	}
	/**
	 * 根据id删除数据
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteById")
	public MsgResult deleteById(int id) throws Exception {
		LOG.info("invoke----------/appDown/deleteById");
		return appService.deleteById(id);
	}
	
}

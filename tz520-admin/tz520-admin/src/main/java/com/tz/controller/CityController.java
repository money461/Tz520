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
import com.tz.pojo.vo.CityVo;
import com.tz.res.MsgResult;
import com.tz.service.CityService;

/**
 * 城市类
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/city")
public class CityController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityService cityService;
	
	/**
	 * 城市列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/city/listPage");
		return new ModelAndView("tz520/city/list");
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
		LOG.info("invoke----------/city/list");
		return cityService.getCityList(cityVo, rows, curPage);
	}
	/**
	 * 根据状态来查询城市列表
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getCityListByState")
	public MsgResult getCityListByState(TzCity city,String type) throws Exception {
		LOG.info("invoke----------/city/getCityListByState");
		return cityService.getCityListByState(city,type);
	}
	
	/**
	 * 城市列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,String id) throws Exception {
		LOG.info("invoke----------/city/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/city/add");
		}else{
			ModelAndView andView = new ModelAndView();
			andView.setViewName("tz520/city/update");
			andView.addObject("id", id);
			return andView;
		}
	}
	/**
	 * 添加城市
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("addOrUpdate")
	public MsgResult addOrUpdate(TzCity city,String type,String oldCityName) throws Exception {
		LOG.info("invoke----------/city/adduser");
		return cityService.addOrUpdate(city, type, oldCityName);
	}
	/**
	 * 城市账号停用启动
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("updateStatusById")
	public MsgResult updateStatusById(String id,Integer status) throws Exception {
		LOG.info("invoke----------/city/updateStatusById");
		return cityService.updateStatusById(id, status);
	}
	/**
	 * 城市账号删除
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteById")
	public MsgResult deleteById(String id) throws Exception {
		LOG.info("invoke----------/city/deleteById");
		return cityService.deleteById(id);
	}
	/**
	 * 根据id查询城市信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@GetMapping("selectById")
	public MsgResult selectById(String id) throws Exception {
		LOG.info("invoke----------/city/selectById");
		return cityService.selectById(id);
	}

	
}

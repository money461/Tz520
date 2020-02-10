package com.tz.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzUserLoveShow;
import com.tz.pojo.vo.UserLoveShowVo;
import com.tz.res.MsgResult;
import com.tz.service.UserLoveShowService;

/**
 * 用户爱心值提现类
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/loveShow")
public class UserLoveShowController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserLoveShowService userLoveShowService;
	
	/**
	 * 所有用户的爱心值提现列表页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/loveShow/listPage");
		return new ModelAndView("tz520/loveShow/list");
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/userLoveShowAlterPage")
	public ModelAndView userLoveShowAlterPage(String type){
		LOG.info("invoke----------/userLove/userLoveShowAlterPage");
		if("exportExcel".endsWith(type)){
			return new ModelAndView("tz520/loveShow/exportExcel");
		}else{
			
			return new ModelAndView("tz520/error");
		}
	}
	
	/**
	 * 查询所有用户的爱心值消提现
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(UserLoveShowVo loveShowVo,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/loveShow/list");
		return userLoveShowService.getUserLoveShowList(loveShowVo, rows, curPage);
	}
	/**
	 * 根据id删除数据
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteById")
	public MsgResult deleteById(String id) throws Exception {
		LOG.info("invoke----------/loveShow/deleteById");
		return userLoveShowService.deleteById(id);
	}
	/* 结算
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("settlement")
	public MsgResult settlement(TzUserLoveShow userLoveShow) throws Exception {
		LOG.info("invoke----------/loveShow/settlement");
		return userLoveShowService.settlement(userLoveShow);
	}
	
	/**
	 * 生成Excel
	 * @param loveShowVo
	 * @param fileName
	 * @param response
	 * @return
	 */
	@PostMapping("/importExel")
	public MsgResult importExcel(UserLoveShowVo loveShowVo,String fileName,HttpServletRequest request,HttpServletResponse response){
		LOG.info("invoke----------/loveShow/importExel");
		
		MsgResult result = userLoveShowService.importLoveShowExcel(loveShowVo,fileName,request,response);
		try {
			   if(!result.getFlag()){
				   response.setContentType("text/html;charset=utf-8");
				   response.getWriter().print(result.getMsg());
			   }
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return null;
	}
	
}

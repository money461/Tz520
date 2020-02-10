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

import com.tz.pojo.TzUserLove;
import com.tz.pojo.vo.UserLoveVo;
import com.tz.res.MsgResult;
import com.tz.service.UserloveService;

/**
 * 用户爱心值类
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/userLove")
public class UserLoveController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserloveService userloveService;
	
	/**
	 * 所有用户的爱心值列表页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/userLove/listPage");
		return new ModelAndView("tz520/userLove/list");
	}
	/**
	 * 查询所有用户的爱心值
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(UserLoveVo userLoveVo,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/userLove/list");
		return userloveService.getUserLoveList(userLoveVo, rows, curPage);
	}
	/**
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping("/alterUserLovePage")
	public ModelAndView alterUserLovePage(String type){
		LOG.info("invoke----------/userLove/alterUserLovePage");
		if("exportExcel".endsWith(type)){
			return new ModelAndView("tz520/userLove/exportExcel");
		}else{
			return new ModelAndView("error");
		}
		
	}
	
	/**
	 * 根据id删除数据
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteById")
	public MsgResult deleteById(String id) throws Exception {
		LOG.info("invoke----------/userLove/deleteById");
		return userloveService.deleteById(id);
	}
	/**
	 * 根据id修改成长值状态
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("updateStausById")
	public MsgResult updateStausById(TzUserLove userLove) throws Exception {
		LOG.info("invoke----------/userLove/updateStausById");
		return userloveService.updateStausById(userLove);
	}
	
	/**
	 * 
	 * @return
	 */
	@PostMapping("/importExcel")
	public MsgResult importExcel(UserLoveVo userLoveVo,String fileName,HttpServletRequest request,HttpServletResponse response){
		MsgResult result= userloveService.importExcel(userLoveVo,fileName,request,response);
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

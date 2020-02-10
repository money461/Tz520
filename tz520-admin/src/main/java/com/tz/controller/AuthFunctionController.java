package com.tz.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.mapper.vo.TzAuthFunctionMapperVo;
import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.vo.TzAuthFunctionVo;
import com.tz.res.MsgResult;

import com.tz.service.AuthFunctionService;

/**
 * 权限
 * @author
 */
@RestController
@RequestMapping("/admin/function")
public class AuthFunctionController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthFunctionService functionService;
	
	
	/**
	 * 跳转至权限列表信息展示页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/function/listPage");
		return new ModelAndView("tz520/function/list");
	}
	/**
	 * 用户列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(TzAuthFunctionVo authFunctionVo, int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/function/list");
		return functionService.getFunctionList(authFunctionVo,rows, curPage);
	}
	
	
	/**
	 *跳转至添加或者更新页面
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,String id) throws Exception {
		LOG.info("invoke----------/function/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/function/add");
		}else{
			return new ModelAndView("tz520/function/update","id",id);
		}
	}

	/**
	 * 异步加载获取所有的权限信息,在角色添加或者更新页面中生成权限树
	 * @return
	 */
	@PostMapping("/findAllFunction")
	public MsgResult findAllFunction(){
		//查询所有的权限
		MsgResult result = functionService.findAllFunction();
		return result;
	}
	
	/**
	 * 添加权限信息
	 * @param tzAuthFunction
	 * @return
	 */
	@PostMapping("/addAuthFunction")
	public MsgResult addAuthFunction(TzAuthFunction tzAuthFunction){
		LOG.info("invoke----------/function/addFunction");
		MsgResult msgResult = functionService.addAuthFunction(tzAuthFunction);
		return msgResult;
		
	}
	
	/**
	 * 根据权限id删除权限信息
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteFunctionById")
	public MsgResult deleteFunctionById(String id){
		LOG.info("invoke----------/function/deleteFunctionById");
		System.out.println("删除权限id---"+id);
		MsgResult result = functionService.deleteFunctionById(id);
		return result;
	}
	
	/**
	 * 根据权限id回显权限信息至权限更新页面
	 */
	@PostMapping("/findAuthFunctionById")
	public MsgResult findAuthFunctionById(String id){
		LOG.info("invoke----------/function/deleteFunction");
		MsgResult result = functionService.findAuthFunctionById(id);
		return result;
	}

	/**
	 * 更新权限信息
	 * @return
	 */
	@PostMapping("/updateAuthFunction")
	public MsgResult updateAuthFunction(TzAuthFunction tzAuthFunction){
		System.out.println("更新权限信息-----");
		MsgResult result = functionService.updateAuthFunction(tzAuthFunction);
		return result;
		
	}
}

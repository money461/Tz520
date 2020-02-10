package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzExpressCom;
import com.tz.pojo.vo.TzExpressComVo;
import com.tz.res.MsgResult;
import com.tz.service.ExpressComService;

/**
 * 管理快递公司
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/expressCom")
public class ExpressComController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExpressComService expressComService;
	
	/**
	 * 异步加载查询所有的快递公司信息
	 * @return
	 */
	@GetMapping("/queryExpressCom")
	public MsgResult queryContentCatagory(){
		MsgResult result = expressComService.queryExpressCom();
		return result;
	};
	
	/**
	 * 跳转至快递公司列表页面
	 * @return
	 */
	@RequestMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/expressCom/listPage");
		return new ModelAndView("tz520/expressCom/list");
		
	}
	
	/**
	 * 快递公司列表信息
	 * @param curPage
	 * @param rows
	 * @return
	 */
	@GetMapping("/list")
	public MsgResult list(TzExpressComVo tzExpressComVo, int curPage,int rows){
		LOG.info("invoke----------/expressCom/list");
		return expressComService.getExpressComList(tzExpressComVo,curPage,rows);
	}
	
	/**
	 * 点击添加修改跳转响应页面
	 * @param type
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,Integer id){
		LOG.info("invoke----------/expressCom/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/expressCom/add");
		}else if("update".equals(type)){
			return new ModelAndView("tz520/expressCom/update","id",id);
		}else{
			return new ModelAndView("tz520/error");
		}
		
	}
	/**
	 * 快递公司添加或者更新操作
	 * @param tzItemCategory
	 * @return
	 */
	@PostMapping("/addOrUpdate")
	public MsgResult addOrUpdateExpressCom(TzExpressCom tzExpressCom,String type ){
		System.out.println("添加或者商品分类-----------");
		MsgResult result = expressComService.addOrUpdateExpressCom(tzExpressCom,type);
		return result;
		
	}
	
	/**
	 * 根据广告分类id回显广告分类信息
	 * @param id
	 * @return
	 */
	@PostMapping("/selectExpressComById")
	public MsgResult selectExpressComById(Integer id){
		System.out.println("快递====================="+id);
		MsgResult result = expressComService.selectExpressComById(id);
		
		return result;
	}
	
	/**
	 * 根据商品分类id删除分类信息
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteExpressComById")
	public MsgResult deleteExpressComById(Integer id){
		MsgResult result = expressComService.deleteExpressComById(id);
		return result;
	}
	
	
}

package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzItem;
import com.tz.pojo.vo.TzItemVo;
import com.tz.res.MsgResult;
import com.tz.service.ItemService;

/*
 * 商品类 
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/tzItem")
public class ItemController  {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemService itemService;
	/**
	 * 跳转商品列表页面
	 * @return
	 */
	@RequestMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/tzItem/listPage");
		return new ModelAndView("tz520/tzItem/list");
	}
	
	/**
	 * 查看商品列表信息
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@GetMapping("/list")
	public MsgResult list(TzItemVo tzItemVo, int curPage,int rows){
		LOG.info("invoke----------/tzItem/list");
		return itemService.getItemList(tzItemVo,curPage,rows);
	}
	
	/**
	 * 商品添加页面
	 * @return
	 */
	@RequestMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,String id){
		LOG.info("invoke----------/tzItem/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/tzItem/add");
		}else if("update".endsWith(type)){
			return new ModelAndView("tz520/tzItem/update","id",id);
		}else{
			return new ModelAndView("tz520/tzItem/detail","id",id);
		}
	}
	/**
	 * 添加商品信息
	 * @param tzItem
	 * @return
	 */
	@PostMapping("/addItem")
	public MsgResult addItem(TzItem tzItem){
		System.out.println("--------添加商品信息-----");
		MsgResult result =itemService.addItem(tzItem);
		return result;
		
	}
	
	/**
	 * 上传商品图片信息
	 * @param file
	 * @throws Exception
	 */
	@PostMapping("/add")
	public void add(@RequestParam("file")MultipartFile file) throws Exception {
		LOG.info("invoke----------/tzItem/add");
		itemService.add(file);
	}
	
	
	//点击编辑商品，根据商品id回显商品信息
	@PostMapping("/findItemById")
	public MsgResult findItemById(String id){
		System.out.println("---编辑商品id----"+id);
		MsgResult result = itemService.findItemById(id);
		return result;
		
	}
	/**
	 * 更新商品信息
	 * @param tzItem
	 * @return
	 */
	@PostMapping("/updateItem")
	public MsgResult updateItem(TzItem tzItem){
		System.out.println("----更新商品-----");
		MsgResult result = itemService.updateItem(tzItem);
		return result;
	}
	/**
	 * 上下架删除一个或者多个商品 修改status
	 * @param ids
	 * @return
	 */
	@PostMapping("/operation")
	public MsgResult  operation(String type,String ids){
		MsgResult result = itemService.operation(type,ids);
		return result;
	}
	
	/**
	 * 商品详情图修改,商品缩略图修改
	 * @return
	 */
	@RequestMapping("/picUpdatePage")
	public ModelAndView picUpdatePage(String id,String type){
		LOG.info("invoke----------/tzItem/picUpdatePage");
		if("images".equals(type)){
			return new ModelAndView("tz520/tzItem/images","id",id);
		}else if("homePageUrl".equals(type)){
			return new ModelAndView("tz520/tzItem/homePageUrl","id",id);
		}else if("prictures".equals(type)){
			return new ModelAndView("tz520/tzItem/prictures","id",id);
		}
		return new ModelAndView("error");
	}
	
}

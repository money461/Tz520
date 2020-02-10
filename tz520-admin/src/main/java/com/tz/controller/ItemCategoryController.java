package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzItemCategory;
import com.tz.pojo.vo.TzItemCategoryVo;
import com.tz.pojo.vo.TzItemVo;
import com.tz.res.MsgResult;
import com.tz.service.ItemCategoryService;
/**
 * 商品分类管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/tzItemCategory")
public class ItemCategoryController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	
	/**
	 * 异步加载查询所有的商品分类信息
	 * @return
	 */
	@GetMapping("/findAllItemCatagory")
	public MsgResult findAllItemCatagory(){
		MsgResult result = itemCategoryService.findAllItemCatagory();
		return result;
	};
	
	/**
	 * 跳转至商品分类列表页面
	 * @return
	 */
	@RequestMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/tzItemCategory/listPage");
		return new ModelAndView("tz520/ItemCategory/list");
		
	}
	
	/**
	 * 商品分类列表信息
	 * @param tzItemCategoryVo
	 * @param curPage
	 * @param rows
	 * @return
	 */
	@GetMapping("/list")
	public MsgResult list(TzItemCategoryVo tzItemCategoryVo, int curPage,int rows){
		LOG.info("invoke----------/tzItemCategory/list");
		return itemCategoryService.getItemCategoryList(tzItemCategoryVo,curPage,rows);
	}
	
	/**
	 * 点击商品分类，跳转至分类列表页面
	 * @param type
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,Integer id){
		LOG.info("invoke----------/tzItemCategory/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/ItemCategory/add");
		}else{
			ModelAndView andView = new ModelAndView();
			andView.setViewName("tz520/ItemCategory/update");
			andView.addObject("id", id);
			return andView;
		}
		
	}
	/**
	 * 商品的分类添加或者更新操作
	 * @param tzItemCategory
	 * @return
	 */
	@PostMapping("/addOrUpdate")
	public MsgResult addOrUpdate(TzItemCategory tzItemCategory,String type ){
		System.out.println("添加或者商品分类-----------");
		MsgResult result = itemCategoryService.addOrUpdate(tzItemCategory,type);
		return result;
		
	}
	
	/**
	 * 根据商品分类id回显商品分类信息
	 * @param id
	 * @return
	 */
	@PostMapping("/selectItemCategoryById")
	public MsgResult selectItemCategoryById(Integer id){
		MsgResult result = itemCategoryService.selectItemCategoryById(id);
		
		return result;
	}
	
	/**
	 * 根据商品分类id删除分类信息
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteItemCategoryById")
	public MsgResult deleteItemCategoryById(Integer id){
		MsgResult result = itemCategoryService.deleteItemCategoryById(id);
		return result;
	}
	
	/**
	 * 校验排序sort是否存在
	 * @return
	 */
	@PostMapping("/checkItemCategorySort")
	public MsgResult checkItemCategorySort(Integer sort){
		return itemCategoryService.checkItemCategorySort(sort);
		
	}
}

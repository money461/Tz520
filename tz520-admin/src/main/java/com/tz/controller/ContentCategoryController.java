package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzContentCategory;
import com.tz.pojo.vo.TzContentCategoryVo;
import com.tz.res.MsgResult;
import com.tz.service.ContentCategoryService;

/**
 * 广告分类管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/tzContentCategory")
public class ContentCategoryController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 异步加载查询所有的广告分类信息
	 * @return
	 */
	@GetMapping("/queryContentCatagory")
	public MsgResult queryContentCatagory(){
		MsgResult result = contentCategoryService.queryContentCategory();
		return result;
	};
	
	/**
	 * 跳转至广告分类列表页面
	 * @return
	 */
	@RequestMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/tzContentCategory/listPage");
		return new ModelAndView("tz520/contentCategory/list");
		
	}
	
	/**
	 * 广告分类列表信息
	 * @param curPage
	 * @param rows
	 * @return
	 */
	@GetMapping("/list")
	public MsgResult list(TzContentCategoryVo tzcontentCategoryVo, int curPage,int rows){
		LOG.info("invoke----------/tzContentCategory/list");
		return contentCategoryService.getContentCategoryList(tzcontentCategoryVo,curPage,rows);
	}
	
	/**
	 * 点击广告分类，跳转至广告分类添加修改页面
	 * @param type
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,Integer id){
		LOG.info("invoke----------/tzContentCategory/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/contentCategory/add");
		}else if("update".equals(type)){
			return new ModelAndView("tz520/contentCategory/update","id",id);
		}else{
			return new ModelAndView("tz520/error");
		}
		
	}
	/**
	 * 广告分类添加或者更新操作
	 * @param tzItemCategory
	 * @return
	 */
	@PostMapping("/addOrUpdate")
	public MsgResult addOrUpdateContentCategory(TzContentCategory tzContentCategory,String type ){
		System.out.println("添加或者商品分类-----------");
		MsgResult result = contentCategoryService.addOrUpdateContentCategory(tzContentCategory,type);
		return result;
		
	}
	
	/**
	 * 根据广告分类id回显广告分类信息
	 * @param id
	 * @return
	 */
	@PostMapping("/selectContentCategoryById")
	public MsgResult selectContentCategoryById(Integer id){
		MsgResult result = contentCategoryService.selectContentCategoryById(id);
		
		return result;
	}
	
	/**
	 * 根据商品分类id删除分类信息
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteContentCategoryById")
	public MsgResult deleteContentCategoryById(Integer id){
		MsgResult result = contentCategoryService.deleteContentCategoryById(id);
		return result;
	}
	
	/**
	 * 校验sort是否存在
	 * @return
	 */
	@PostMapping("/checkContentCategorySort")
	public MsgResult checkContentCategorySort(Integer sort){
		return contentCategoryService.checkContentCategorySort(sort);
		
	}
}

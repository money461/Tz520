package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzDictionary;
import com.tz.pojo.TzUser;
import com.tz.pojo.vo.TzDictionaryVo;
import com.tz.pojo.vo.TzOrderVo;
import com.tz.res.MsgResult;
import com.tz.service.DictionaryService;

/**
 * 数据字典管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/dictionary")
public class DictionaryController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 跳转数据字典页面
	 * @return
	 */
	@RequestMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/tzDictionary/listPage");
		return new ModelAndView("tz520/dictionary/list");
	}
	
	/**
	 * 查看字典列表信息
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@GetMapping("/list")
	public MsgResult list(int curPage,int rows,TzDictionaryVo tzDictionaryVo){
		LOG.info("invoke----------/tzDictionaryVo/list");
		return dictionaryService.getDictionaryList(curPage,rows,tzDictionaryVo);
	}
	
	/**
	 * 跳转至添加或者编辑数据字典信息页面
	 * @param type
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,String id) throws Exception {
		LOG.info("invoke----------/tzDictionary/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/dictionary/add");
		}else{
			ModelAndView andView = new ModelAndView();
			andView.setViewName("tz520/dictionary/update");
			andView.addObject("id", id);
			return andView;
		}
	}
	
	
	/**
	 * 添加数据字典信息或者修改编辑数据字典信息
	 */
	@PostMapping("/addOrUpdate")
	public MsgResult addOrUpdate(TzDictionary tzDictionary,String way) throws Exception {
		LOG.info("invoke----------/tzDictionary/addOrUpdate");
		System.out.println("添加或更新数据字典信息");
		return dictionaryService.addOrUpdate(tzDictionary,way);
	}
	
	//根据字典id查询数据信息并回显至编辑页面
	@PostMapping("/selectDictionaryById")
	public MsgResult selectDictionaryById(String id){
		LOG.info("invoke----------/tzDictionary/selectDictionaryById");
		MsgResult result = dictionaryService.selectDictionaryById(id);
		return result;
	}
	
	
	//根据字典id删除数据
	@PostMapping("/deleteDictionaryById")
	public MsgResult deleteDictionaryById(String id){
		LOG.info("invoke----------/tzDictionary/deleteDictionaryById");
		MsgResult result = dictionaryService.deleteDictionaryById(id);
		return result;
	}
	
	
	 
}

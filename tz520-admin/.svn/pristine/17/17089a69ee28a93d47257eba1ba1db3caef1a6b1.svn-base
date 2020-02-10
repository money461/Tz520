package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzContent;
import com.tz.pojo.vo.TzContentVo;
import com.tz.res.MsgResult;
import com.tz.service.ContentService;

/**
 * 广告管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/tzContent")
public class ContentController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 跳转广告列表页面
	 * @return
	 */
	@RequestMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/tzContent/listPage");
		return new ModelAndView("tz520/content/list");
	}
	
	/**
	 * 查看广告列表信息
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@GetMapping("/list")
	public MsgResult list(int curPage,int rows,TzContentVo tzContentVo){
		LOG.info("invoke----------/order/list");
		return contentService.getContentList(curPage,rows,tzContentVo);
	}
	
	/**
	 * 跳转至内容编辑页面或者订单详情页面
	 * @return
	 */
	@RequestMapping("/contentAlterPage")
	public ModelAndView editContentPage(String type,String id){
		LOG.info("invoke----------/content/editContentPage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/content/add");
		}else if("edit".endsWith(type)){
			//跳转至广告编辑更新页面
			return new ModelAndView("tz520/content/update","id",id);
		}else if("detail".endsWith(type)){
			//跳转至广告详情页面
			return new ModelAndView("tz520/content/detail","id",id);
		}else if("secondPrics".endsWith(type)){
			//跳转至添加更新图片2地址
			return new ModelAndView("tz520/content/picSecondUrl","id",id);
		}else{
			//跳转至404页面
			return new ModelAndView("tz520/error");
		}
	}
	
	/**
	 * 添加广告信息
	 * @param 
	 * @return
	 */
	@PostMapping("/addContent")
	public MsgResult addItem(TzContent tzContent){
		System.out.println("--------添加商品信息-----");
		MsgResult result =contentService.addContent(tzContent);
		return result;
	}
	
	/**
	 * 更新广告信息
	 * @param 
	 * @return
	 */
	@PostMapping("/updateContent")
	public MsgResult updateItem(TzContent tzContent){
		System.out.println("----更新商品-----");
		MsgResult result = contentService.updateContent(tzContent);
		return result;
	}
	
	/**
	 * 批量删除
	 * @param tzContent
	 * @return
	 */
	@PostMapping("/batchDeleteContent")
	public MsgResult batchDeleteContent(String ids){
		System.out.println("----批量删除-----");
		MsgResult result = contentService.batchDeleteContent(ids);
		return result;
	}
	
	/**
	 * 根据id查看广告详情
	 * @return
	 */
	@PostMapping("/queryContentDetail")
	public MsgResult queryContentDetail(String id){
		System.out.println("---查看广告详情---");
		MsgResult result = contentService.queryContentDetail(id);
		return result;
	}
	
}

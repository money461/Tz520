
package com.tz.ItemController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tz.interfaces.ItemService;
import com.tz.res.AppMsgResult;



@RestController
@RequestMapping("app/api")
public class ItemController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemService itemService;
	
	
	/**
	 * 商城首页所有信息
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public AppMsgResult showStoreIndex(){
		LOG.info("invoke----------/Item/storeIndex");
		return itemService.showStoreIndex();
		
	}
	
	/**
	 * 模糊查询商品名称，展示商品数据
	 * @return
	 */
	@RequestMapping(value="/queryItemByItemTitle",method=RequestMethod.GET)
	public AppMsgResult queryItemByTitle( String itemTitle,Integer curPage,Integer rows){
		LOG.info("invoke----------/Item/queryItemByItemTitle");
		return itemService.queryItemByItemTitle(itemTitle,rows, curPage);
		
	}
	/**
	 * 根据id查询商品详情信息
	 * @return
	 */
	@RequestMapping(value="/queryItemDetailById",method=RequestMethod.GET)
	public AppMsgResult queryItemDetailById(String id){
		LOG.info("invoke----------/Item/queryItemDetailById");
		return itemService.queryItemDetailById(id);
	}
	
	/**
	 * 随机推荐商品3个或者2个商品套餐
	 */
	@RequestMapping(value="/recommendItem",method=RequestMethod.GET)
	public AppMsgResult recommendItem(Integer i){
		LOG.info("invoke----------/Item/recommendItem");
		return itemService.recommendItem(i);
	}
	
	/**
	 * 
	 * 根据分类随机推荐2个商品
	 * @return
	 */
	@RequestMapping(value="/recommendItemByCategory",method=RequestMethod.GET)
	public AppMsgResult recommendItemByCategory(String ids){
		LOG.info("invoke----------/Item/recommendItemByCategory");
		return itemService.recommendItemByCategory(ids);
	}
	
	
	
	/**
	 * 根据分类查询商品信息
	 */
	@RequestMapping(value="/queryItemByCategory",method=RequestMethod.GET)
	public AppMsgResult queryItemByCategory(String id){
		LOG.info("invoke----------/Item/queryItemByCategory");
		return itemService.queryItemByCategory(id);
	}
	
}

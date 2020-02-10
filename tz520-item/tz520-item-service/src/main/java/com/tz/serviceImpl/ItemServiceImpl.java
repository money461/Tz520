package com.tz.serviceImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.interfaces.ContentService;
import com.tz.interfaces.ItemCategoryService;
import com.tz.interfaces.ItemService;
import com.tz.mapper.vo.TzItemMapperVo;
import com.tz.pojo.vo.TzContentVo;
import com.tz.pojo.vo.TzItemCategoryVo;
import com.tz.pojo.vo.TzMallItem;
import com.tz.pojo.vo.TzItemVo;
import com.tz.pojo.vo.TzStoreIndex;
import com.tz.res.AppMsgResult;
@Service
public class ItemServiceImpl implements ItemService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TzItemMapperVo itemMapperVo;
	
	@Autowired
	private RedisCache cache;
	
	//引入广告轮播图接口服务
	@Autowired
	private ContentService contentService;
	
	//引入商品分类信息接口服务
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	/**
	 * 商城首页查询商品信息
	 */
	@Override
	public  List<TzMallItem> selectIndexItemList(){
		//商城首页缓存key
		String IndexItemcache_key=RedisCache.CAHCENAME+"|selectIndexItemList|";
		//先去缓存中取已分页的商品数据
		 List<TzMallItem> IndexItemList = cache.getListCache(IndexItemcache_key, TzMallItem.class);
		if (IndexItemList != null) {
			LOG.info("get cache with key:"+IndexItemcache_key);
		} else {
	        //获取商城首页已上架的商品数据信息
			IndexItemList = itemMapperVo.selectIndexItemList();
			if(IndexItemList.size()>0){
				cache.putListCacheWithExpireTime(IndexItemcache_key, IndexItemList, RedisCache.CAHCETIME);
				LOG.info("put cache with key:"+IndexItemcache_key);
			}
		}
	
		return  IndexItemList;
	}

   /**
    * 展示商城首页所有的信息（不存入缓存）
    */
	@Override
	public AppMsgResult showStoreIndex() {
		
		//创建封装对象
		TzStoreIndex tzStoreIndex = new TzStoreIndex();
		//查询首页轮播广告信息
		List<TzContentVo> showIndexItem = contentService.showMallContent();
		if(showIndexItem.size()<=0){
			return AppMsgResult.result(565, "没有找到轮播数据", null);
		}
		tzStoreIndex.setTzContentList(showIndexItem);
		
		//查询商城首页分类信息4个分类信息
		List<TzItemCategoryVo> storeCategoryList = itemCategoryService.queryItemCategoryList();
		if(storeCategoryList.size()<=0){
			return AppMsgResult.result(564, "没有找到分类数据", null);
		}
		tzStoreIndex.setItemCategoryList(storeCategoryList);
		
		//查询商城首页商品信息
		tzStoreIndex.setIndexItemList(this.selectIndexItemList());
		return AppMsgResult.result(200, "success", tzStoreIndex);
	}

	
	
	/**
	 * 模糊搜索查询商品名称返回信息
	 */
	@Override
	public AppMsgResult queryItemByItemTitle(String itemTitle, Integer rows, Integer curPage) {
		AppMsgResult result=null;
			//判断模糊查询商品名称是否为空
		  if(StringUtils.isNotBlank(itemTitle)){
			  rows = rows == null?10:rows;
			  curPage = curPage == null?1:curPage;
			  //分页处理
			  PageHelper.startPage(curPage, rows);
			  List<TzItemVo> itemList= itemMapperVo.queryItemByItemTitle(itemTitle);
			  PageInfo<TzItemVo> pageInfo = new PageInfo<TzItemVo>(itemList);
			  if(itemList.size()==0){
				  result = AppMsgResult.result(200, "没有找到类似商品！！", null);
			  }else{
				  result = AppMsgResult.result(200, "success", pageInfo);
			  }
			  
		  }else{
			  //返回商城首页数据
			  List<TzMallItem> indexItemList = this.selectIndexItemList();
			  result = AppMsgResult.result(200, "success", indexItemList);
		  }
		
		return result;
	}
	
	
	/**
	 * 展示商品详情页面
	 * @return
	 */

	@Override
	public AppMsgResult queryItemDetailById(String id) {
		AppMsgResult msgResult = null;
		if(StringUtils.isNotEmpty(id)){
			//先去缓存中获取商品信息
			String Itemcache_key=RedisCache.CAHCENAME+"|queryItemDetailById|"+id;
			TzItemVo itemVo = cache.getCache(Itemcache_key, TzItemVo.class);
			if(itemVo!=null){
				LOG.info("get cache with key:"+Itemcache_key);
			}else{
				itemVo = itemMapperVo.queryItemDetailById(id);
				if(itemVo!=null){
					cache.putCacheWithExpireTime(Itemcache_key, itemVo, RedisCache.CAHCETIME);
					LOG.info("put cache with key:"+Itemcache_key);
				}else{
					msgResult = AppMsgResult.result(543, "商品不存在，已经下架",null);
				}
			}
			msgResult = AppMsgResult.result(200, "success",itemVo);
			
		}else{
			msgResult = AppMsgResult.result(543, "商品id为null", null);
		}
		return msgResult;
	}

	/**
	 * 随机推荐商品3个
	 */
	@Override
	public AppMsgResult recommendItem(Integer i) {
		List<TzItemVo> itemList= itemMapperVo.recommendItem(i);
		return AppMsgResult.result(200, "success", itemList);
	}

	//根据商品分类id查询商品
	@Override
	public AppMsgResult queryItemByCategory(String id) {
		AppMsgResult msgResult = null;
		if(StringUtils.isBlank(id)){
			return AppMsgResult.result(521, "参数有误！！", null);
		}
		//先去缓存中获取商品信息
		String categoryCache_key=RedisCache.CAHCENAME+"|queryItemByCategory|"+id;
		List<TzItemVo> itemList = cache.getListCache(categoryCache_key, TzItemVo.class);
		if(itemList!=null){
			LOG.info("get cache with key:"+categoryCache_key);
		}else{
			//去数据库查询商品信息
			 itemList = itemMapperVo.selectItemByCategory(id);
			 if(itemList.size()>0){
				 //存入缓存
				 cache.putListCacheWithExpireTime(categoryCache_key, itemList, RedisCache.CAHCETIME);
				 LOG.info("put cache with key:"+categoryCache_key);
			 }
		}
		msgResult = AppMsgResult.result(200,"success",itemList);
		return msgResult;
	}

	/*
	 * 根据分类id随机推荐商品2个
	 */
	@Override
	public AppMsgResult recommendItemByCategory(String ids) {
		if(StringUtils.isBlank(ids)){
			return AppMsgResult.result(543, "参数有误！！", null);
		}
		String[] categoryIds = ids.split(",");
		List<TzItemVo> itemList= itemMapperVo.recommendItemByCategory(categoryIds);
		return AppMsgResult.result(200, "success", itemList);
	}

	
}

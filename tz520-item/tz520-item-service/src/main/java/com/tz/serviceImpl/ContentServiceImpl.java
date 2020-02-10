package com.tz.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.cache.RedisCache;
import com.tz.interfaces.ContentCategoryService;
import com.tz.interfaces.ContentService;
import com.tz.interfaces.ItemCategoryService;
import com.tz.mapper.vo.TzContentMapperVo;
import com.tz.pojo.vo.TzAppIndex;
import com.tz.pojo.vo.TzContentCategoryVo;
import com.tz.pojo.vo.TzHomePageContent;
import com.tz.pojo.vo.TzItemCategoryVo;
import com.tz.pojo.vo.TzContentVo;
import com.tz.res.AppMsgResult;
@Service
public class ContentServiceImpl implements ContentService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TzContentMapperVo tzContentMapperVo;
	
	@Autowired
	private RedisCache cache;
	
	//引入商品分类接口
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	//引入内容分类接口
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * //展示app首页新闻广告以及轮播图信息
	 */
	@Override
	public List<TzHomePageContent> homePageContent() {
		String homePageContentCache_key=RedisCache.CAHCENAME+"|homePageContent|";
		//从缓存中获取数据
		List<TzHomePageContent> categoryContentList = cache.getListCache(homePageContentCache_key, TzHomePageContent.class);
		if(null!=categoryContentList){
			//返回值
			LOG.info("get cache with key:"+homePageContentCache_key);
		}else{
			//去数据库查询
			categoryContentList = tzContentMapperVo.homePageContent();
			if(categoryContentList.size()>0){
				//将APP商城广告信息存储在缓存中
				cache.putListCacheWithExpireTime(homePageContentCache_key, categoryContentList, RedisCache.CAHCETIME);
				LOG.info("put cache with key:"+homePageContentCache_key);
			}
		}
		return  categoryContentList;
	}
	
	/**
	 * //展示商城首页轮播图
	 */
	@Override
	public List<TzContentVo> showMallContent() {
		String showMallContentCache_key=RedisCache.CAHCENAME+"|showMallContent|";
		//从缓存中取商城轮播图片信息
		List<TzContentVo> tzContentList = cache.getListCache(showMallContentCache_key, TzContentVo.class);
		if(null!=tzContentList){
			LOG.info("get cache with key:"+showMallContentCache_key);
		}else{
			tzContentList = tzContentMapperVo.showMallContent();
			if(tzContentList.size()>0){
				cache.putListCacheWithExpireTime(showMallContentCache_key, tzContentList, RedisCache.CAHCETIME);
				LOG.info("put cache with key:"+showMallContentCache_key);
			}
		}
		return  tzContentList;
	}
	
	/**
	 * 查询APP首页所有的商品信息（不存缓存）
	 */
	@Override
	public AppMsgResult showAppIndex() {
		
		TzAppIndex tzAppIndex = new TzAppIndex();
		//获取商品分类
		List<TzItemCategoryVo> categoryList = itemCategoryService.queryItemCategoryList();
		if(categoryList.size()<=0){
			return AppMsgResult.result(561, "没有找到分类信息", null);
		}
		tzAppIndex.setItemCategoryList(categoryList);
		
		//获取App首页广告分类信息
		List<TzContentCategoryVo> contentCategoryList = contentCategoryService.queryMallContentCategory();
		if(contentCategoryList.size()<=0){
			return AppMsgResult.result(561, "没有找到分类信息", null);
		}
		tzAppIndex.setContentCategoryList(contentCategoryList);
		
		//获取App首页广告新闻以及轮播图信息
		List<TzHomePageContent> homePageContent = this.homePageContent();
		if(homePageContent.size()<=0){
			return AppMsgResult.result(562, "没有找到广告内容信息", null);
		}
		//获取首页内容信息
		tzAppIndex.setHomePageContent(homePageContent);
		
		return AppMsgResult.result(200, "success",tzAppIndex);
	}

}

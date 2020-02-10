package com.tz.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.cache.RedisCache;
import com.tz.interfaces.ItemCategoryService;
import com.tz.mapper.vo.TzItemCategoryMapperVo;
import com.tz.pojo.vo.TzItemCategoryVo;
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TzItemCategoryMapperVo tzItemCategoryMapperVo;
	
	@Autowired
	private RedisCache cache;
	
	/**
	 * 查询商城首页或者App首页4个商品分类信息
	 */
	@Override
	public List<TzItemCategoryVo> queryItemCategoryList() {
		String categoryCache_key=RedisCache.CAHCENAME+"|queryItemCategoryList|";
		List<TzItemCategoryVo> itemCategoryList = cache.getListCache(categoryCache_key, TzItemCategoryVo.class);
		if(itemCategoryList!=null){
			LOG.info("get cache with key:"+categoryCache_key);
		}else{
			itemCategoryList =  tzItemCategoryMapperVo.queryItemCategoryList();
			if(itemCategoryList.size()>0){
				//将分类信息存储在缓存中
				cache.putListCacheWithExpireTime(categoryCache_key, itemCategoryList,RedisCache.CAHCETIME);
				LOG.info("put cache with key:"+categoryCache_key);
			}
		}
		return itemCategoryList;
	}

}

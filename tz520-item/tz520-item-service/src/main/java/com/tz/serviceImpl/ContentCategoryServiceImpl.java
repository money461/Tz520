package com.tz.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.cache.RedisCache;
import com.tz.interfaces.ContentCategoryService;
import com.tz.mapper.vo.TzContentCategoryMapperVo;
import com.tz.pojo.vo.TzContentCategoryVo;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TzContentCategoryMapperVo tzContentCategoryVo;
	
	@Autowired
	private RedisCache cache;
	
	/**
	 * 查询App首页内容分类信息
	 */
	@Override
	public List<TzContentCategoryVo> queryMallContentCategory() {
		String categoryCache_key=RedisCache.CAHCENAME+"|queryMallContentCategory|";
		List<TzContentCategoryVo> contentCategoryList = cache.getListCache(categoryCache_key, TzContentCategoryVo.class);
		if(contentCategoryList!=null){
			LOG.info("get cache with key:"+categoryCache_key);
		}else{
			contentCategoryList =  tzContentCategoryVo.queryMallContentCategory();
			if(contentCategoryList.size()>0){
				//将分类信息存储在缓存中
				cache.putListCacheWithExpireTime(categoryCache_key, contentCategoryList,RedisCache.CAHCETIME);
				LOG.info("put cache with key:"+categoryCache_key);
			}
		}
		return contentCategoryList;
	}
		

}

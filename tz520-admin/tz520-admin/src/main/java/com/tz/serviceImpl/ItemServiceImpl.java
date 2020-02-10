package com.tz.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzItemMapper;
import com.tz.mapper.vo.TzItemMapperVo;
import com.tz.pojo.TzItem;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.TzItemVo;
import com.tz.res.MsgResult;
import com.tz.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService  {
	
	/**
	 * 
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	//公共配置类
    @Autowired
    private CommonVo common;
	
	//引入商品mapper接口
	@Autowired
	private TzItemMapper tzItemMapper;
	
	//引入缓存
	@Autowired
	private RedisCache cache;
	
	/**
	 * 引入商品item扩展mapper接口 执行批量删除上下架
	 */
	@Autowired
	private TzItemMapperVo tzItemMapperVo;
	
    @Autowired
    private FastDFSClientWrapper dfsClientWrapper;
    
    /**
     * 添加图片文件信息
     */
	@Override
	public String add(MultipartFile file) throws IOException {
		String path = dfsClientWrapper.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), file.getOriginalFilename());
		LOG.info("222--------+"+path);
		return null;
	}
	
	//测试上传文件
	public static void main(String[] args) {
		String a = "l love feng ye";
        String b = "love";
        boolean c = a.contains(b);
	}

	/**
	 * 清空所有有关商品的缓存
	 */
	public void deleteAllCache(){
		//模糊清空用户购物车（需要前端页面自行校验是否下架）
		String cartCache_key = RedisCache.CAHCENAME+"|selectCartList|*";
		cache.deleteCacheWithPattern(cartCache_key);
		//精准清空商城首页缓存key
		String IndexItemcache_key=RedisCache.CAHCENAME+"|selectIndexItemList|";
		cache.deleteCache(IndexItemcache_key);
		//模糊清空商品分类查询缓存
		String categoryCache_key=RedisCache.CAHCENAME+"|queryItemByCategory|*";
		cache.deleteCacheWithPattern(categoryCache_key);
		//商品详情缓存
		String Itemcache_key=RedisCache.CAHCENAME+"|queryItemDetailById|*";
		cache.deleteCacheWithPattern(Itemcache_key);
		
		System.out.println("------------------清空所有关于商品的缓存-------------------");
	}
	
	/**
	 * 对商品修改后模糊清空数据缓存
	 */
	public void deleteCache(){
		//清空用户购物车（需要前端页面自行校验是否下架）
		String cartCache_key = RedisCache.CAHCENAME+"|selectCartList|*";
		cache.deleteCacheWithPattern(cartCache_key);
		//商城首页缓存key
		String IndexItemcache_key=RedisCache.CAHCENAME+"|selectIndexItemList|";
		cache.deleteCache(IndexItemcache_key);
		//商品分类查询缓存
		String categoryCache_key=RedisCache.CAHCENAME+"|queryItemByCategory|*";
		cache.deleteCacheWithPattern(categoryCache_key);
		
		System.err.println("-------------模糊清空所有购物车、商城首页、商品分类查询缓存-----------------");
	}
	
	/**
	 * 对某一件商品进行修改，需要清空缓存
	 */
	public void deleteCacheById(String id){
		//商品详情缓存
		String Itemcache_key=RedisCache.CAHCENAME+"|queryItemDetailById|"+id;
		//查询该商品是否存在缓存中
		cache.deleteCache(Itemcache_key);
		System.out.println("---------------清空某一商品详情的缓存--------------------"+id);
	}
	
	/**
	 * 分页展示商品信息
	 */
	@Override
	public MsgResult getItemList(TzItemVo tzItemVo,Integer curPage, Integer rows) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //设置查询条件
        //商品名称
        String itemTitle = tzItemVo.getItemTitle();
        if(StringUtils.isNotEmpty(itemTitle)){
        	map.put("itemTitle", "%"+itemTitle+"%");
        }else{
        	map.put("itemTitle", null);
        }
        
        //商品描述
         String description = tzItemVo.getDescription();        
         if(StringUtils.isNotEmpty(description)){
        	 map.put("description", "%"+description+"%");
         }else{
        	 map.put("description", null);
         }
         
         //商品分类id
         String categoryId = tzItemVo.getCategoryId();
         if(StringUtils.isNotEmpty(categoryId)){
        	 map.put("categoryId",categoryId);
         }else{
        	 map.put("categoryId", null);
         }
         
         //商品状态
          map.put("status", tzItemVo.getStatus());
          
         
       //商城id
     	String mallId = common.getMallId();
     	map.put("mallId", mallId);
     	
     	//时间段
    	if(null != tzItemVo.getStartTime() && null != tzItemVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzItemVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzItemVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
     	
    	//调用扩展类接口tzItemMapperVO
    	List<TzItemVo> itemList =  tzItemMapperVo.selectTzItemList(map);
    	
        //获取分页数据结果
        PageInfo<TzItemVo> pageInfo = new PageInfo<TzItemVo>(itemList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	/**
	 * 添加商品信息
	 */
	@Override
	public MsgResult addItem(TzItem tzItem) {
		//获取商品id
		tzItem.setId(IDUtils.genId());
		//获取商品平台
		tzItem.setMallId(common.getMallId());
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzItem.setOperater(manager.getId());
		
		//商品创建时间
		tzItem.setCreatedTime(new Date());
		tzItem.setUpdatedTime(new Date());
		
		tzItemMapper.insertSelective(tzItem);
		
		//商城首页商品信息缓存key
		String IndexItemcache_key=RedisCache.CAHCENAME+"|selectIndexItemList|*";
		cache.deleteCacheWithPattern(IndexItemcache_key);
		//商品分类查询信息缓存
		String categoryCache_key=RedisCache.CAHCENAME+"|queryItemByCategory|*";
		cache.deleteCacheWithPattern(categoryCache_key);
		
		return MsgResult.nodata(true, "商品添加成功！");
	}

	/**
	 * 根据商品id回显商品详细信息
	 */
	@Override
	public MsgResult findItemById(String id) {
		TzItemVo tzItemVo = tzItemMapperVo.selectTzItemById(id);
		return MsgResult.result(true, "",tzItemVo);
	}

	/**
	 * 更新商品信息
	 */
	@Override
	public MsgResult updateItem(TzItem tzItem) {
		tzItem.setUpdatedTime(new Date());
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzItem.setOperater(manager.getId());
		if("".equalsIgnoreCase(tzItem.getImageUrl())){
			tzItem.setImageUrl(null);
		}
		tzItemMapper.updateByPrimaryKeySelective(tzItem);
		
		//清空该商品缓存
		String id = tzItem.getId();
		this.deleteCacheById(id);
		//清空其他缓存
		this.deleteCache();
		
		return MsgResult.nodata(true, "商品更新成功！");
	}

	//上架下架 删除一个或者多个商品
	@Override
	public MsgResult operation(String type, String ids) {
		if("deleteItem".equals(type)){
			String[] arrId = ids.split(",");
			tzItemMapperVo.deleteItem(arrId);
			//清空所有有关商品的缓存信息
			this.deleteAllCache();
			return MsgResult.nodata(true, "商品删除成功！");
			
		}else if("reshelfItem".equals(type)){
			String[] arrId = ids.split(",");
				tzItemMapperVo.reshelfItem(arrId);
				//清空所有有关商品的缓存信息
				this.deleteAllCache();
			return MsgResult.nodata(true, "商品上架成功！");
			
		}else if("instockItem".equals(type)){
			String[] arrId = ids.split(",");
				tzItemMapperVo.instockItem(arrId);
				//清空所有有关商品的缓存信息
				this.deleteAllCache();
			return MsgResult.nodata(true, "商品下架成功！");
		}else{
			return MsgResult.nodata(false,"");
		}
	}


	
	
}

package com.tz.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.mapper.TzItemCategoryMapper;
import com.tz.mapper.vo.TzItemCategoryMapperVo;
import com.tz.pojo.TzItemCategory;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzItemCategoryVo;
import com.tz.res.MsgResult;
import com.tz.service.ItemCategoryService;
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	//引入扩展类mapper接口
	@Autowired
	private TzItemCategoryMapperVo tzItemCategoryMapperVo;
	//引入商品分类mapper接口
	@Autowired
	private TzItemCategoryMapper tzItemCategoryMapper;
	
	//引入缓存
	@Autowired
	private RedisCache cache;
	/**
	 * 清空所有有关商品的缓存
	 */
	public void deleteAllCache(){
		//清空用户购物车（需要前端页面自行校验是否下架）
		String cartCache_key = RedisCache.CAHCENAME+"|selectCartList|*";
		cache.deleteCacheWithPattern(cartCache_key);
		//商城首页缓存key
		String IndexItemcache_key=RedisCache.CAHCENAME+"|selectIndexItemList|";
		cache.deleteCacheWithPattern(IndexItemcache_key);
		//商品分类查询缓存
		String categoryCache_key=RedisCache.CAHCENAME+"|queryItemByCategory|*";
		cache.deleteCacheWithPattern(categoryCache_key);
		//商品详情缓存
		String Itemcache_key=RedisCache.CAHCENAME+"|queryItemDetailById|*";
		cache.deleteCacheWithPattern(Itemcache_key);
		//清空商品分类目录缓存
		String categoryListCache_key=RedisCache.CAHCENAME+"|queryItemCategoryList|";
		cache.deleteCache(categoryListCache_key);
	}
	
	
	
	/**
	 * 异步加载所有的商品分类信息
	 */
	@Override
	public MsgResult findAllItemCatagory() {
		List<TzItemCategory> itemCategoryList = tzItemCategoryMapperVo.queryAllItemCategory();
		return  MsgResult.result(true, "",itemCategoryList);
		
	}

	//条件查询商品分类信息
	@Override
	public MsgResult getItemCategoryList(TzItemCategoryVo tzItemCategoryVo, Integer curPage, Integer rows) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //设置查询条件
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        String name = tzItemCategoryVo.getName();
        if(StringUtils.isNotEmpty(name)){
        	map.put("name", "%"+name+"%");
        }else{
        	map.put("name", null);
        }
      //时间段
    	if(null != tzItemCategoryVo.getStartTime() && null != tzItemCategoryVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzItemCategoryVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzItemCategoryVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        
    	//调用扩展类接口查询数据
    	List<TzItemCategoryVo> ItemCategoryList =tzItemCategoryMapperVo.selectItemCategoryList(map);
    	
        //获取分页数据结果
        PageInfo<TzItemCategoryVo> pageInfo = new PageInfo<TzItemCategoryVo>(ItemCategoryList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
		
	}

	//商品的分类添加或者更新操作
	@Override
	public MsgResult addOrUpdate(TzItemCategory tzItemCategory, String type) {
		Date date = new Date();
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzItemCategory.setOperater(manager.getId());
		tzItemCategory.setIsParent(true); //默认父节点
		tzItemCategory.setUpdatedTime(date);
		if("add".equals(type)){
			tzItemCategory.setCreatedTime(date);
			int i = tzItemCategoryMapper.insertSelective(tzItemCategory);
			if(i==1){
				return MsgResult.nodata(true, "添加商品分类成功！");
			}
		}else if("update".equals(type)){
			if(tzItemCategory.getLogoUrl()==""){
				tzItemCategory.setLogoUrl(null);
			}
			int i = tzItemCategoryMapper.updateByPrimaryKeySelective(tzItemCategory);
			//清空缓存
			this.deleteAllCache();
			if(i==1){
				return MsgResult.nodata(true, "更新商品分类成功！");
			}
		}
		return MsgResult.nodata(false, "操作失败！");
	}

	//根据商品分类id回显商品分类信息
	@Override
	public MsgResult selectItemCategoryById(Integer id) {
		TzItemCategory itemCategory = tzItemCategoryMapperVo.selectItemCategoryById(id);
		return MsgResult.result(true, "", itemCategory);
	}

	//根据商品分类id删除分类信息
	@Override
	public MsgResult deleteItemCategoryById(Integer id) {
		tzItemCategoryMapper.deleteByPrimaryKey(id);
		//清空缓存
		this.deleteAllCache();
		return MsgResult.nodata(true, "商品分类删除成功！");
	}

	/**
	 * 校验sort的值是否存在
	 */
	@Override
	public MsgResult checkItemCategorySort(Integer sort) {
		MsgResult result=null;
		int i = tzItemCategoryMapperVo.checkItemCategorySort(sort);
		if(i!=0){
			//存在sort
			result=MsgResult.nodata(false, "");
		}else{
			//不存在sort
			result=MsgResult.nodata(true, "");
		}
		return result;
	}

	
	
}

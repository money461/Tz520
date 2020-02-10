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
import com.tz.mapper.TzContentCategoryMapper;
import com.tz.mapper.vo.TzContentCategoryMapperVo;
import com.tz.pojo.TzContentCategory;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzContentCategoryVo;
import com.tz.res.MsgResult;
import com.tz.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TzContentCategoryMapper tzContentCategoryMapper;
	
	@Autowired
	private TzContentCategoryMapperVo tzContentCategoryMapperVo;
	
	  //引入缓存
  	@Autowired
  	private RedisCache cache;
	
  	/**
  	 * 清空说有有关广告内容及其内容分类目录的缓存
  	 */
  	public void deleteAllContentCache(){
  		//清空App首页缓存
  		String homePageContentCache_key=RedisCache.CAHCENAME+"|homePageContent|";
  		cache.deleteCache(homePageContentCache_key);
  		//清空商城首页缓存
  		String showMallContentCache_key=RedisCache.CAHCENAME+"|showMallContent|";
  		cache.deleteCache(showMallContentCache_key);
  		//清空App首页内容分类缓存
  		String categoryCache_key=RedisCache.CAHCENAME+"|queryMallContentCategory|";
  		cache.deleteCache(categoryCache_key);
  	}
  	
	
   /**
    * //异步加载所有的内容分类信息
    */
	@Override
	public MsgResult queryContentCategory() {
		List<TzContentCategory> contentcategoryList = tzContentCategoryMapperVo.queryContentCatagory();
		return MsgResult.result(true, "", contentcategoryList);
	}

	/**
	 * 查询所有的内容列表信息
	 */
	@Override
	public MsgResult getContentCategoryList(TzContentCategoryVo tzcontentCategoryVo, Integer curPage, Integer rows) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //设置查询条件
        String name = tzcontentCategoryVo.getName();
        if(StringUtils.isNotBlank(name)){
        	map.put("name", "%"+name+"%");
        }else{
        	map.put("name", null);
        }
      //时间段
    	if(null != tzcontentCategoryVo.getStartTime() && null != tzcontentCategoryVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzcontentCategoryVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzcontentCategoryVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        
    	//调用扩展类接口
    	List<TzContentCategoryVo> contentCategoryList =  tzContentCategoryMapperVo.getContentCategoryList(map);
    	  //获取分页数据结果
        PageInfo<TzContentCategoryVo> pageInfo = new PageInfo<TzContentCategoryVo>(contentCategoryList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	/**
	 * 添加或者更新内容分类信息
	 */
	@Override
	public MsgResult addOrUpdateContentCategory(TzContentCategory tzContentCategory, String type) {
		Date date = new Date();
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzContentCategory.setUpdatedTime(date);
		if("add".equals(type)){
			tzContentCategory.setOperater(manager.getId());
			tzContentCategory.setIsParent(true);  //默认是父节点
			tzContentCategory.setCreatedTime(date);
			int i = tzContentCategoryMapper.insertSelective(tzContentCategory);
			if(i==1){
				return MsgResult.nodata(true, "添加内容分类成功！");
			}
		}else if("update".equals(type)){
			if(tzContentCategory.getLogoUrl()==""){
				tzContentCategory.setLogoUrl(null);
			}
			int i = tzContentCategoryMapper.updateByPrimaryKeySelective(tzContentCategory);
			if(i==1){
				//清除所有内容分类缓存
				this.deleteAllContentCache();
			}
			return MsgResult.nodata(true, "更新内容分类成功！");
		}
		return MsgResult.nodata(false, "操作失败！");
	}

	/**
	 * 更新回显数据
	 */
	@Override
	public MsgResult selectContentCategoryById(Integer id) {
		TzContentCategoryVo contentCategory = tzContentCategoryMapperVo.selectContentCategoryById(id);
		return MsgResult.result(true, "", contentCategory);
	}

	@Override
	public MsgResult deleteContentCategoryById(Integer id) {
		int i = tzContentCategoryMapper.deleteByPrimaryKey(id);
		if(i==1){
			//清除所有内容分类缓存
			this.deleteAllContentCache();
			return MsgResult.nodata(true, "删除操作成功！");
		}
		return MsgResult.nodata(false, "操作失败！");
	}

	/**
	 * 校验sort值是否存在
	 */
	@Override
	public MsgResult checkContentCategorySort(Integer sort) {
		MsgResult result=null;
		int i = tzContentCategoryMapperVo.checkContentCategorySort(sort);
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

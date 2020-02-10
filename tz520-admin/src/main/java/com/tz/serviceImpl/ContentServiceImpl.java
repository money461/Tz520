package com.tz.serviceImpl;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzContentMapper;
import com.tz.mapper.vo.TzContentMapperVo;
import com.tz.pojo.TzContent;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzContentVo;
import com.tz.res.MsgResult;
import com.tz.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private TzContentMapper tzContentMapper;
    
    //引入扩展映射接口
    @Autowired
    private TzContentMapperVo tzContentMapperVo;
    
  //引入缓存
  	@Autowired
  	private RedisCache cache;
  	
  	/**
  	 * 清空说有有关广告内容的缓存
  	 */
  	public void deleteAllContentCache(){
  		//清空App首页缓存
  		String homePageContentCache_key=RedisCache.CAHCENAME+"|homePageContent|";
  		cache.deleteCache(homePageContentCache_key);
  		//清空商城首页缓存
  		String showMallContentCache_key=RedisCache.CAHCENAME+"|showMallContent|";
  		cache.deleteCache(showMallContentCache_key);
  	}
  	
	/**
	 * 分页查询内容信息
	 */
	@Override
	public MsgResult getContentList(Integer curPage, Integer rows, TzContentVo tzContentVo) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //设置查询条件
        //大标题
        String contentTitle = tzContentVo.getContentTitle();
        if(StringUtils.isNotEmpty(contentTitle)){
        	map.put("contentTitle", "%"+contentTitle+"%");
        }else{
        	map.put("contentTitle", null);
        }
        
        //小标题
         String subTitle = tzContentVo.getSubTitle();       
         if(StringUtils.isNotEmpty(subTitle)){
        	 map.put("subTitle", "%"+subTitle+"%");
         }else{
        	 map.put("subTitle", null);
         }
         
         //内容分类id
        Integer contentCategoryId = tzContentVo.getContentCategoryId();
         if(contentCategoryId!=null){
        	 map.put("contentCategoryId",contentCategoryId);
         }else{
        	 map.put("contentCategoryId", null);
         }
     	
     	//时间段
    	if(null != tzContentVo.getStartTime() && null != tzContentVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzContentVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzContentVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
     	
    	//调用扩展类接口tzMapperVO
    	 List<TzContentVo> contentList = tzContentMapperVo.selectTzContentList(map);
    	
        //获取分页数据结果
        PageInfo<TzContentVo> pageInfo = new PageInfo<TzContentVo>(contentList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	
   /**
    * 查询内容详细信息
    */
	@Override
	public MsgResult queryContentDetail(String id) {
		
		TzContentVo tzContentVo = tzContentMapperVo.queryContentDetail(id);
		
		return MsgResult.result(true, "", tzContentVo);
	}

    /**
     * 添加内容
     */
	@Override
	public MsgResult addContent(TzContent tzContent) {
		MsgResult result=null; 
		//获取商品id
		tzContent.setId(IDUtils.genId());
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzContent.setOperater(manager.getId());
		
		//商品创建时间
		tzContent.setCreatedTime(new Date());
		tzContent.setUpdatedTime(new Date());
		
		//插入数据
		int i = tzContentMapper.insertSelective(tzContent);
		if(i==1){
			result = MsgResult.nodata(true, "内容添加成功！");
		}else{
			result = MsgResult.nodata(false, "操作失败！！");
		}
		return result;
	}

	//更新内容
	@Override
	public MsgResult updateContent(TzContent tzContent) {
		MsgResult result=null; 
		tzContent.setUpdatedTime(new Date());
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzContent.setOperater(manager.getId());
		if(tzContent.getPicFirstUrl()==""){
			tzContent.setPicFirstUrl(null);
		}
		int i = tzContentMapper.updateByPrimaryKeySelective(tzContent);
		//清空所有内容分类缓存
		this.deleteAllContentCache();
		if(i==1){
			result = MsgResult.nodata(true, "更新内容成功！");
		}else{
			result = MsgResult.nodata(false, "操作失败！！");
		}
		return result;
	}

	
	/**
	 * 批量删除内容信息
	 */
	@Override
	public MsgResult batchDeleteContent(String ids) {
		String[] strId = ids.split(",");
		tzContentMapperVo.batchDeleteContent(strId);
		//清空所有内容分类缓存
		this.deleteAllContentCache();
		return MsgResult.nodata(true, "删除成功！");
	}

}

package com.tz.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzDictionaryMapper;
import com.tz.mapper.vo.TzDictionaryMapperVo;
import com.tz.pojo.TzDictionary;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzDictionaryVo;
import com.tz.res.MsgResult;
import com.tz.service.DictionaryService;
@Service
public class DictionaryServiceImpl implements DictionaryService {
 
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	//引入订单扩展类接口
	@Autowired
	private TzDictionaryMapperVo tzDictionaryMapperVo;
	
	@Autowired
	private TzDictionaryMapper tzDictionaryMapper;
	
	
	//根据条件查询字典信息
	@Override
	public MsgResult getDictionaryList(Integer curPage, Integer rows, TzDictionaryVo tzDictionaryVo) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //设置查询条件
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //字典名称
        String name = tzDictionaryVo.getName();
        if(StringUtils.isNotEmpty(name)){
        	map.put("name", "%"+name+"%");
        }else{
        	map.put("name", null);
        }
        
    	//时间段
    	if(null != tzDictionaryVo.getStartTime() && null != tzDictionaryVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzDictionaryVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzDictionaryVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        
    	//调用扩展类接口查询数据
    	List<TzDictionaryVo> dictionaryList = tzDictionaryMapperVo.selectDictionaryList(map);
    	
        //获取分页数据结果
        PageInfo<TzDictionaryVo> pageInfo = new PageInfo<TzDictionaryVo>(dictionaryList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	//编辑或者更新数据字典信息
	@Override
	public MsgResult addOrUpdate(TzDictionary tzDictionary, String type) {
		Date date = new Date();
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzDictionary.setOperater(manager.getId());
		if("update".equals(type)){
			//更新数据
			tzDictionary.setUpdatedTime(date);
			tzDictionaryMapper.updateByPrimaryKeySelective(tzDictionary);
			return MsgResult.nodata(true, "更新成功！");
		}else if("add".equals(type)){
			//添加数据
			tzDictionary.setId(IDUtils.genId());
			
			tzDictionary.setCreatedTime(date);
			tzDictionary.setUpdatedTime(date);
			tzDictionaryMapper.insertSelective(tzDictionary);
			return MsgResult.nodata(true, "添加成功！");
		}
		return null;
	}

	//根据id查询数据字典信息
	@Override
	public MsgResult selectDictionaryById(String id) {
		TzDictionary tzDictionary = tzDictionaryMapper.selectByPrimaryKey(id);
		return MsgResult.result(true, "", tzDictionary);
	}

	//根据字典id删除数据
	@Override
	public MsgResult deleteDictionaryById(String id) {
		tzDictionaryMapper.deleteByPrimaryKey(id);
		return MsgResult.nodata(true, "删除成功！");
	}
	
	

}

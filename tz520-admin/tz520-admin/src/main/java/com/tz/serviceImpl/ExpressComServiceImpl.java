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
import com.tz.date.DateUtile;
import com.tz.mapper.TzExpressComMapper;
import com.tz.mapper.vo.TzExpressComMapperVo;
import com.tz.pojo.TzExpressCom;
import com.tz.pojo.TzExpressComExample;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzExpressComVo;
import com.tz.res.MsgResult;
import com.tz.service.ExpressComService;
/**
 * 
 * @author Administrator
 *
 */
@Service
public class ExpressComServiceImpl implements ExpressComService {

	@Autowired
	private TzExpressComMapper tzExpressComMapper;
	private TzExpressComExample tzExpressComExample;
	private TzExpressComExample.Criteria expressComCriteria;
	
	
	@Autowired
	private TzExpressComMapperVo tzExpressComMapperVo;
	
	
	
	@Override
	public MsgResult deleteExpressComById(Integer id) {
		MsgResult result=null;
		int i = tzExpressComMapper.deleteByPrimaryKey(id);
		if(i==1){
			result = MsgResult.nodata(true, "删除成功！");
		}else{
			result = MsgResult.nodata(false, "删除失败！");
		}
		return result;
	}

	
	@Override
	public MsgResult selectExpressComById(Integer id) {
		TzExpressCom tzExpressCom = tzExpressComMapper.selectByPrimaryKey(id);
		
		return MsgResult.result(true, "", tzExpressCom);
	}

	@Override
	public MsgResult addOrUpdateExpressCom(TzExpressCom tzExpressCom, String type) {
		Date date = new Date();
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzExpressCom.setUpdatedTime(date);
		if("add".equals(type)){
			tzExpressCom.setCreatedTime(date);
			int i = tzExpressComMapper.insertSelective(tzExpressCom);
			if(i==1){
				return MsgResult.nodata(true, "添加内容分类成功！");
			}
		}else if("update".equals(type)){
			
			int i = tzExpressComMapper.updateByPrimaryKeySelective(tzExpressCom);
			if(i==1){
				return MsgResult.nodata(true, "更新内容分类成功！");
			}
		}
		return MsgResult.nodata(false, "操作失败！");
	}

	@Override
	public MsgResult getExpressComList(TzExpressComVo tzExpressComVo, Integer curPage, Integer rows) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //设置查询条件
        String name = tzExpressComVo.getCompanyName();
        if(StringUtils.isNotBlank(name)){
        	map.put("name", "%"+name+"%");
        }else{
        	map.put("name", null);
        }
      //时间段
    	if(null != tzExpressComVo.getStartTime() && null != tzExpressComVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzExpressComVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzExpressComVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        
    	//调用扩展类接口
    	List<TzExpressComVo> expressComList =  tzExpressComMapperVo.getExpressComList(map);
    	  //获取分页数据结果
        PageInfo<TzExpressComVo> pageInfo = new PageInfo<TzExpressComVo>(expressComList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	@Override
	public MsgResult queryExpressCom() {
		tzExpressComExample = new TzExpressComExample();
		expressComCriteria = tzExpressComExample.createCriteria();
		
		List<TzExpressCom> expressComList = tzExpressComMapper.selectByExample(tzExpressComExample);
		
		return MsgResult.result(true, "", expressComList);
	}


	
}

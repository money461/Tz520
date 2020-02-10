package com.tz.serviceImpl;

import java.util.ArrayList;
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
import com.tz.mapper.TzAuthFunctionMapper;
import com.tz.mapper.vo.TzAuthFunctionMapperVo;
import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.TzAuthFunctionExample;
import com.tz.pojo.TzManager;
import com.tz.pojo.vo.TzAuthFunctionVo;
import com.tz.res.MsgResult;

import com.tz.service.AuthFunctionService;


@Service
public class AuthFunctionServiceImpl implements AuthFunctionService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private TzAuthFunctionMapper tzAuthFunctionMapper;
	private TzAuthFunctionExample tzAuthFunctionExample;
	private TzAuthFunctionExample.Criteria authFunctionCriteria;
	
	//权限扩展类
	@Autowired 
	private TzAuthFunctionMapperVo tzAuthFunctionMapperVo;
	
	//引入缓存
	@Autowired
	private RedisCache cache;
	/**
	 * 查询所有权限信息
	 */
	@Override
	public MsgResult getFunctionList(TzAuthFunctionVo authFunctionVo,Integer rows, Integer curPage) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //设置查询条件
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        String code = authFunctionVo.getCode();
        if(StringUtils.isNoneBlank(code)){
        	map.put("code", "%"+code+"%");
        }else{
        	map.put("code", null);
        }
        String name = authFunctionVo.getName();
        if(StringUtils.isNoneBlank(name)){
        	map.put("name", "%"+name+"%");        
        }else{
        	map.put("name", null);
        }
        //时间段
    	if(null != authFunctionVo.getStartTime() && null != authFunctionVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(authFunctionVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(authFunctionVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        
		//调用Mapper进行查询
		 List<TzAuthFunction> functionList = tzAuthFunctionMapperVo.selectAuthFunctionList(map);
		 
		//获取分页数据结果
	     PageInfo<TzAuthFunction> pageInfo = new PageInfo<TzAuthFunction>(functionList);
	     
	     //返回结果
	     MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	/**
	 * 添加权限信息
	 */
	@Override
	public MsgResult addAuthFunction(TzAuthFunction tzAuthFunction) {
		//生成权限id
		tzAuthFunction.setId(IDUtils.genId());
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzAuthFunction.setOperater(manager.getId());
		tzAuthFunction.setCreatedTime(new Date());
		tzAuthFunction.setUpdatedTime(new Date());
		//调用mapper添加权限信息
		tzAuthFunctionMapper.insertSelective(tzAuthFunction);
		return MsgResult.nodata(true, "权限添加成功");
	}

	/**
	 * 异步加载所有的权限信息,在角色添加或者更新页面生成权限树
	 */
	@Override
	public MsgResult findAllFunction() {
		//调用mapper接口
		List<TzAuthFunction> functionList = tzAuthFunctionMapperVo.queryAllFunction();
		
		return MsgResult.result(true, "", functionList);
	}
	
	

	/**
	 * 根据权限id删除权限信息
	 */
	@Override
	public MsgResult deleteFunctionById(String id) {
		//删除主键唯一的权限信息
		tzAuthFunctionMapper.deleteByPrimaryKey(id);
		//删除pid=id的权限信息 
		List<TzAuthFunction> authFunctionList = tzAuthFunctionMapperVo.queryAuthFunctionBypId(id);
		if(authFunctionList.size()>0){
			tzAuthFunctionMapperVo.deleteAuthFunctionBypId(id);
		}
		//批量删除角色权限中间表数据
		List<String> list =new ArrayList<String>();
		list.add(id);
		for (TzAuthFunction tzAuthFunction : authFunctionList) {
			list.add(tzAuthFunction.getId());
		}
		
		tzAuthFunctionMapperVo.deleteRoleFunctionById(list);
		
		return MsgResult.nodata(true, "权限删除成功！");
	}
	
	
	
	/**
	 * 根据权限id回显权限信息至权限更新页面
	 * @param id
	 * @return
	 */
	@Override
	public MsgResult findAuthFunctionById(String id) {
		TzAuthFunction tzAuthFunction = tzAuthFunctionMapper.selectByPrimaryKey(id);
		return MsgResult.result(true, "", tzAuthFunction);
	}

	/**
	 * 更新保存权限信息
	 * @param tzAuthFunction
	 */
	@Override
	public MsgResult updateAuthFunction(TzAuthFunction tzAuthFunction) {
		MsgResult result = null;
		tzAuthFunction.setUpdatedTime(new Date());
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		tzAuthFunction.setOperater(manager.getId());
		if(StringUtils.isEmpty(tzAuthFunction.getpId())){
			tzAuthFunction.setpId(null);
		}
	   //更新权限数据
		int i = tzAuthFunctionMapper.updateByPrimaryKeySelective(tzAuthFunction);
		if(i==1){
			result = MsgResult.nodata(true, "权限更新成功！");
		}else{
			result = MsgResult.nodata(false, "权限更新失败！");
		}
		return result;
		
	}

	/**
	 * shiro框架中根据用户id查询对应的权限信息
	 */
	@Override
	public List<String> queryAuthFunctionByManager(TzManager manager) {
		List<String> athFunctionList =null;
		if("admin".equals(manager.getManagerName())){
		    	athFunctionList = tzAuthFunctionMapperVo.queryAuthFunctionCode(null);
		}else{
		    	athFunctionList = tzAuthFunctionMapperVo.queryAuthFunctionCode(manager.getId());
		
		}
		return athFunctionList;
	}
	
	
}

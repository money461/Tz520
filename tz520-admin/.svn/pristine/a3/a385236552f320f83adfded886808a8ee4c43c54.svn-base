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
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzManagerMapper;
import com.tz.mapper.TzManagerRoleMapper;
import com.tz.mapper.TzRoleFunctionMapper;
import com.tz.mapper.TzRoleMapper;
import com.tz.mapper.vo.TzRoleFunctionMapperVo;
import com.tz.mapper.vo.TzRoleMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzManagerExample;
import com.tz.pojo.TzManagerRoleExample;
import com.tz.pojo.TzRole;
import com.tz.pojo.TzRoleExample;
import com.tz.pojo.vo.TzRoleVo;
import com.tz.pojo.TzRoleFunction;
import com.tz.pojo.TzRoleFunctionExample;
import com.tz.res.MsgResult;

import com.tz.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TzRoleMapper tzRoleMapper;
	private TzRoleExample tzRoleExample;
	private TzRoleExample.Criteria roleCriteria;
	
	//角色扩展类
	@Autowired
	private TzRoleMapperVo tzRoleMapperVo;
	
	//引入角色权限扩展接口
	@Autowired
	private TzRoleFunctionMapperVo tzRoleFunctionMapperVo;
	
	
	@Autowired
	private TzRoleFunctionMapper tzRoleFunctionMapper;
	private TzRoleFunctionExample tzRoleFunctionExample;
	private TzRoleFunctionExample.Criteria roleFunctionCriteria;
	
	@Autowired
	private TzManagerMapper tzManagerMapper;
	private TzManagerExample tzManagerExample;
	private TzManagerExample.Criteria tzManagerCriteria;
	
	@Autowired 
	private TzManagerRoleMapper tzManagerRoleMapper;
	private TzManagerRoleExample  tzManagerRoleExample;
	private TzManagerRoleExample.Criteria managerRoleCriteria;
	
	
   /**
    * 获取当前账户的id
    * @return
    */
	public String getManagerId(){
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		return manager.getId();
	}
	
	/**
	 * 分页查询所有角色信息
	 */
	@Override
	public MsgResult getRoleList(TzRoleVo tzRoleVo,Integer rows, Integer curPage) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //设置查询条件
        Map<String,Object> map = new HashMap<String,Object>();
        String name = tzRoleVo.getName();
        if(StringUtils.isNotBlank(name)){
        	map.put("name", "%"+name+"%");
        }else{
        	map.put("name", null);
        }
    	
        //时间段
      	if(null != tzRoleVo.getStartTime() && null != tzRoleVo.getEndTime()){
      		map.put("startTime",DateUtile.pushDays(tzRoleVo.getStartTime(),0) );
      		map.put("endTime", DateUtile.pushDays(tzRoleVo.getEndTime(),0));
      	}else{
      		map.put("startTime",null);
      		map.put("endTime",null);
      	}
      	
      	List<TzRoleVo> roleList = tzRoleMapperVo.queryRoleList(map);
		//获取分页数据结果
	     PageInfo<TzRoleVo> pageInfo = new PageInfo<TzRoleVo>(roleList);
	     
	     //返回结果
	     MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	/**
	 * 添加角色信息,并将权限id与角色id关联至中间表
	 */
	@Override
	public MsgResult addRole(TzRole tzRole,String functionIds) {
		//生成权限id
		String roleId = IDUtils.genId();
		tzRole.setId(roleId);
		//获取当前登录账户ID
		tzRole.setOperater(this.getManagerId());
		tzRole.setCreatedTime(new Date());
		tzRole.setUpdatedTime(new Date());
		//调用mapper添加权限信息
		tzRoleMapper.insertSelective(tzRole);
		
		if(StringUtils.isNotBlank(functionIds)){
			String managerId = this.getManagerId();
			String[] StrfunctionId = functionIds.split(",");
			List<TzRoleFunction> roleFunctionList = new ArrayList<TzRoleFunction>();
			for (String functionId : StrfunctionId) {
				//给该角色绑定权限插入数据至中间表
				//生成角色权限id
				TzRoleFunction rolefunction = new TzRoleFunction();
				rolefunction.setId(IDUtils.genId());
				rolefunction.setRoleId(roleId);
				rolefunction.setFunctionId(functionId);
				//获取当前登录账户ID
				tzRole.setOperater(managerId);
				roleFunctionList.add(rolefunction);
			}
			//批量插入角色权限数据信息
			tzRoleFunctionMapperVo.batchInsertData(roleFunctionList);
		}
		return MsgResult.nodata(true, "角色添加成功！");
		
	}

	/**
	 * 添加管理员异步加载查询所有的角色信息，在添加管理员或者更新管理员页面中展示
	 */
	@Override
	public MsgResult findAllRole() {
		 //设置查询条件
		List<TzRole> roleList = tzRoleMapperVo.queryAllRole();
		return MsgResult.result(true, "", roleList);
	}

	/**
	 * 根据角色id删除角色信息
	 */
	@Override
	public MsgResult delRole(String id) {
		//删除角色表数据
		tzRoleMapper.deleteByPrimaryKey(id);
		
		//删除角色权限中间表相关信息
		//创建删除条件
		tzRoleFunctionExample = new TzRoleFunctionExample();
		roleFunctionCriteria = tzRoleFunctionExample.createCriteria();
		roleFunctionCriteria.andRoleIdEqualTo(id);
		//调用roleFunctionMapper
		tzRoleFunctionMapper.deleteByExample(tzRoleFunctionExample);
		
		//删除管理员表与角色中间表响应信息
		tzManagerRoleExample = new TzManagerRoleExample();
		managerRoleCriteria = tzManagerRoleExample.createCriteria();
		managerRoleCriteria.andRoleIdEqualTo(id);
		tzManagerRoleMapper.deleteByExample(tzManagerRoleExample);
		
		return MsgResult.nodata(true, "删除操作成功");
	}

	/**
	 * 更新保存角色数据
	 */
	@Override
	public MsgResult updateRole(TzRole tzRole, String functionIds) {
		//更新角色表数据信息
		tzRole.setUpdatedTime(new Date());
		//获取当前登录用户
		tzRole.setOperater(this.getManagerId());
		//判断是否需要修改角色父目录
		if(StringUtils.isEmpty(tzRole.getpId())){
			tzRole.setpId(null);
		}
		tzRoleMapper.updateByPrimaryKeySelective(tzRole);
	if(StringUtils.isNotBlank(functionIds)){
		//更新角色权限中间表数据
		//先删除角色权限中间表信息
		String roleId = tzRole.getId();
		tzRoleFunctionExample = new TzRoleFunctionExample();
		roleFunctionCriteria = tzRoleFunctionExample.createCriteria();
		roleFunctionCriteria.andRoleIdEqualTo(tzRole.getId());
		tzRoleFunctionMapper.deleteByExample(tzRoleFunctionExample);
		
		//更新角色权限中间表数据信息
			String managerId = this.getManagerId();
			String[] StrfunctionId = functionIds.split(",");
			List<TzRoleFunction> roleFunctionList = new ArrayList<TzRoleFunction>();
			for (String functionId : StrfunctionId) {
				//给该角色绑定权限插入数据至中间表
				//生成角色权限id
				TzRoleFunction rolefunction = new TzRoleFunction();
				rolefunction.setId(IDUtils.genId());
				rolefunction.setRoleId(roleId);
				rolefunction.setFunctionId(functionId);
				//获取当前登录账户ID
				tzRole.setOperater(managerId);
				roleFunctionList.add(rolefunction);
			}
			//批量插入角色权限数据信息
			tzRoleFunctionMapperVo.batchInsertData(roleFunctionList);
		}
		
		return MsgResult.nodata(true, "角色更新成功！");
		
	}

	/**
	 * 根据角色id查询角色表数据，在update.ftl页面回显
	 */
	@Override
	public MsgResult finRoleById(String id) {
		TzRole tzRole = tzRoleMapper.selectByPrimaryKey(id);
		return MsgResult.result(true, "", tzRole);
	}
	
	//shiro框架中realm根据用户查询对应的角色信息
	@Override
	public List<String> queryRoleCodeByManager(TzManager manager) {
		
		List<String> roleCodeList = null;
		roleCodeList = tzRoleMapperVo.queryRoleCodeByManager(manager.getId());
		return roleCodeList;
	}
}

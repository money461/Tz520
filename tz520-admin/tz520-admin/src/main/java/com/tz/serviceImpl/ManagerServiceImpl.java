package com.tz.serviceImpl;

import java.util.ArrayList;
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
import com.tz.id.IDUtils;
import com.tz.mapper.TzManagerMapper;
import com.tz.mapper.TzManagerRoleMapper;
import com.tz.mapper.vo.TzManagerMapperVo;
import com.tz.mapper.vo.TzManagerRoleMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzManagerExample;
import com.tz.pojo.TzManagerRole;
import com.tz.pojo.TzManagerRoleExample;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.TzManagerVo;
import com.tz.res.MsgResult;

import com.tz.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired 
    private TzManagerMapper tzManagerMapper;
    private TzManagerExample  tzManagerExample;
	private TzManagerExample.Criteria managerCriteria;
		
	@Autowired 
	private TzManagerRoleMapper tzManagerRoleMapper;
	private TzManagerRoleExample  tzManagerRoleExample;
    private TzManagerRoleExample.Criteria managerRoleCriteria;
	//引入管理员列表扩展接口
	@Autowired
	private TzManagerMapperVo tzManagerMapperVo;
	
	//引入管理员角色扩展接口
	@Autowired
	private  TzManagerRoleMapperVo tzManagerRoleMapperVo;
	
	@Autowired
	private CommonVo commonVo;
	
	
	private String getManagerId(){
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		return manager.getId();
	}
	
	/**
	 * 分页查询管理员列表信息
	 */
	@Override
	public MsgResult getManagerList(TzManagerVo tzManagerVo,Integer curPage, Integer rows) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        
      //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        
        String managerName = tzManagerVo.getManagerName();
        if(StringUtils.isNoneBlank(managerName)){
        	map.put("name", "%"+managerName+"%");
        }else{
        	map.put("name", null);
        }
        
        String phone = tzManagerVo.getPhone();
        if(StringUtils.isNoneBlank(phone)){
        	map.put("phone", "%"+phone+"%");
        }else{
        	map.put("phone", null);
        }
        
      //商家平台id
    	String mallId = commonVo.getMallId();
    	map.put("mallId", mallId);
    	
      //时间段
    	if(null != tzManagerVo.getStartTime() && null != tzManagerVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzManagerVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzManagerVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        
        //调用mapper分页查询管理员
        List<TzManagerVo> ManagerList = tzManagerMapperVo.selectManagerList(map);
        
        //获取分页数据结果
        PageInfo<TzManagerVo> pageInfo = new PageInfo<TzManagerVo>(ManagerList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}

	/**
	 * 添加管理员
	 */
	@Override
	public MsgResult addmanager(TzManager tzManager,String roleIds) {
		MsgResult result=null;
		String code = IDUtils.getCode();
		String password = tzManager.getPassword();
		//生成管理员id
		String mid = IDUtils.genId();
		tzManager.setId(mid);
		tzManager.setPassword(IDUtils.md5(password+code));
		tzManager.setCode(code);
		//添加商家平台id
		tzManager.setMallId(commonVo.getMallId());
		tzManager.setCreatedTime(new Date());
		tzManager.setUpdatedTime(new Date());
		//为该管理员添加角色信息至用户角色中间表
		if(StringUtils.isNoneBlank(roleIds)){
			String[] roleIdStr = roleIds.split(",");
			List<TzManagerRole> managerRoleList = new ArrayList<TzManagerRole>();
			String managerId = this.getManagerId();
			for (String roleId : roleIdStr) {
				TzManagerRole tzManagerRole = new TzManagerRole();
				tzManagerRole.setId(IDUtils.genId());
				tzManagerRole.setManagerId(mid);
				tzManagerRole.setRoleId(roleId);
				
				//获取当前登录用户
				tzManagerRole.setOperater(managerId);
				tzManagerRole.setCreatedTime(new Date());
				tzManagerRole.setUpdatedTime(new Date());
				managerRoleList.add(tzManagerRole);
			}
			
			//批量保存管理员与角色信息
			tzManagerRoleMapperVo.batchInsertData(managerRoleList);
		}
		//保存管理员信息
	    int i = tzManagerMapper.insertSelective(tzManager);
	    if(i==1){
	    	result = MsgResult.nodata(true, "添加成功");
	    }else{
	    	result = MsgResult.nodata(false, "添加失败");
	    }
	    
		return result; 
	}

	/**
	 * 根据id查询管理员用户，update页面回显
	 */
	@Override
	public MsgResult findManagerById(String id) {
		TzManager tzManager = tzManagerMapper.selectByPrimaryKey(id);
		return MsgResult.result(true, "", tzManager);
	}

	//编辑后更新管理员信息
	@Override
	public MsgResult updateManager(TzManager tzManager,String roleIds) {
		MsgResult result = null;
		String mId = tzManager.getId();
		String password = tzManager.getPassword();
		if(StringUtils.isEmpty(password)){
			tzManager.setPassword(null);
		}else{
			String code = IDUtils.getCode();
			tzManager.setPassword(IDUtils.md5(password+code));
			tzManager.setCode(code);
		}
		
		tzManager.setUpdatedTime(new Date());
		//更新管理员表数据
		int i = tzManagerMapper.updateByPrimaryKeySelective(tzManager);
		
		//更新管理员角色中间表数据
		//重新添加账户角色绑定信息
		if(StringUtils.isNoneBlank(roleIds)){
			// 先删除中间表中该用户角色信息
			tzManagerRoleExample = new TzManagerRoleExample();
			managerRoleCriteria = tzManagerRoleExample.createCriteria();
			managerRoleCriteria.andManagerIdEqualTo(tzManager.getId());

			tzManagerRoleMapper.deleteByExample(tzManagerRoleExample);

			String[] roleIdStr = roleIds.split(",");
			List<TzManagerRole> managerRoleList = new ArrayList<TzManagerRole>();
			String managerId = this.getManagerId();
			for (String roleId : roleIdStr) {
				TzManagerRole tzManagerRole = new TzManagerRole();
				tzManagerRole.setId(IDUtils.genId());
				tzManagerRole.setManagerId(mId);
				tzManagerRole.setRoleId(roleId);

				// 获取当前登录用户
				tzManagerRole.setOperater(managerId);
				tzManagerRole.setCreatedTime(new Date());
				tzManagerRole.setUpdatedTime(new Date());
				managerRoleList.add(tzManagerRole);
			}

			// 批量保存管理员与角色信息
			tzManagerRoleMapperVo.batchInsertData(managerRoleList);
		}
		if(i==1){
			result = MsgResult.nodata(true, "管理员更新成功！");
		}else{
			result = MsgResult.nodata(false, "管理员更新失败！");
		}
		
		return result;
	}

	/**
	 * 根据id删除管理员信息，删除管理角色中间表信息
	 */
	@Override
	public MsgResult delManagerById(String id) {
		MsgResult result=null;
		//删除管理员表数据信息
		tzManagerMapper.deleteByPrimaryKey(id);
		//删除管理员角色中间表信息
		tzManagerRoleExample = new TzManagerRoleExample();
		managerRoleCriteria = tzManagerRoleExample.createCriteria();
		managerRoleCriteria.andManagerIdEqualTo(id);
		int i = tzManagerRoleMapper.deleteByExample(tzManagerRoleExample);
		if(i==1){
			result = MsgResult.nodata(true, "删除操作成功！");
		}else{
			result = MsgResult.nodata(true, "删除操作失败！");
		}
		//返回结果
		return result;
	}

	/**
	 * 登录认证 登录页面根据账户名称查询账户密码,是否相同，并返回结果
	 */
	@Override
	public MsgResult findByManagerName(String managerName,String password) {
		tzManagerExample = new TzManagerExample();
		managerCriteria = tzManagerExample.createCriteria();
		managerCriteria.andManagerNameEqualTo(managerName);
		List<TzManager> list = tzManagerMapper.selectByExample(tzManagerExample);
		if(list!=null && !list.isEmpty()){
			TzManager manager = list.get(0);
			String machPasword = IDUtils.md5(password+manager.getCode());
			if(manager.getPassword().equals(machPasword)){
				//更新最近登录时间
				tzManagerMapperVo.updateLastUpload(manager.getId());
				return MsgResult.result(true, "登录成功！！",manager);
			}else{
				return MsgResult.nodata(false, "账户名或者密码错误！！");
			}
		}else{
			return MsgResult.nodata(false, "账户名或者密码错误！！");
		}
	}	

}

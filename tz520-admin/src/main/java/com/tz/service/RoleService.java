package com.tz.service;

import java.util.List;

import com.tz.pojo.TzManager;
import com.tz.pojo.TzRole;
import com.tz.pojo.vo.TzRoleVo;
import com.tz.res.MsgResult;

public interface RoleService {

	//分页查询管理员列表信息
	MsgResult getRoleList(TzRoleVo tzRoleVo, Integer curPage, Integer rows);

	//添加角色
	MsgResult addRole(TzRole  tzRole, String functionIds);

	//查询所有的角色信息
	MsgResult findAllRole();

	//根据id删除角色信息
	MsgResult delRole(String id);

	//更新保存角色数据
	MsgResult updateRole(TzRole tzRole, String functionIds);

	//根据角色id查询角色表对应数据
	MsgResult finRoleById(String id);

	//根据用户查询该用户对应的角色code
	List<String> queryRoleCodeByManager(TzManager manager);

    
}

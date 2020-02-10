package com.tz.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzRole;
import com.tz.pojo.vo.TzRoleVo;
import com.tz.res.MsgResult;

import com.tz.service.AuthFunctionService;
import com.tz.service.RoleService;
import com.tz.service.UserService;

/**
 * 角色
 * @author
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleService roleService;
	
	/**
	 * 跳转至角色列表页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage() throws Exception {
		LOG.info("invoke----------/role/listPage");
		return new ModelAndView("tz520/role/list");
	}
	
	/**
	 * 分页展示所有角色信息
	 * @param rows
	 * @param curPage
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(TzRoleVo tzRoleVo,int rows,int curPage) throws Exception {
		LOG.info("invoke----------/role/list");
		return roleService.getRoleList(tzRoleVo,rows, curPage);
	}
	
	/**
	 * 添加角色信息
	 * @param tzAuthFunction
	 * @return
	 */
	@PostMapping("/addRole")
	public MsgResult addRole(TzRole tzRole,String functionIds){
		LOG.info("invoke----------/role/addRole");
		MsgResult msgResult = roleService.addRole(tzRole,functionIds);
		return msgResult;
	}

	/**
	 *跳转至添加或者编辑更新页面
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/addOrUpdatePage")
	public ModelAndView addOrUpdatePage(String type,String id) throws Exception {
		LOG.info("invoke----------/role/addOrUpdatePage");
		if("add".endsWith(type)){
			return new ModelAndView("tz520/role/add");
		}else{
			return new ModelAndView("tz520/role/update","id",id);
		}
	}
	
	
	/**
	 * 更新角色信息
	 * @param tzRole
	 * @param functionIds
	 * @return
	 */
	@PostMapping("/updateRole")
	public MsgResult updateRole(TzRole tzRole,String functionIds){
		LOG.info("invoke----------/role/updateRole");
		System.out.println("角色id---"+tzRole.getId());
		System.out.println("选中的权限id---"+functionIds);
		MsgResult result = roleService.updateRole(tzRole,functionIds);
		return result;
	}
	
	/**
	 * 根据id异步加载角色信息并回显至编辑页面
	 * @param id
	 * @return
	 */
	@PostMapping("/findRoleById")
	public MsgResult findRoleById(String id){
		LOG.info("invoke----------/role/findRoleById");
		MsgResult result = roleService.finRoleById(id);
		return result;
	}
	
	/**
	 * 根据角色id删除该角色信息以及中间表数据
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteById")
	public MsgResult delRole(String id){
		System.out.println("删除角色id--"+id);
		MsgResult msgResult = roleService.delRole(id);
		
		return  msgResult;
	}
	
	/**
	 * 添加管理员异步加载所有角色信息
	 * @return
	 */
	@GetMapping("/findAllRole")
	public MsgResult findAllRole(){
		MsgResult msgResult = roleService.findAllRole();
		System.out.println("查询所有角色");
		return msgResult;
		
	}

}

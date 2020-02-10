package com.tz.conf;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzRole;

import com.tz.service.AuthFunctionService;
import com.tz.service.RoleService;


/**
 * 自定义realm 实现安全数据连接
 * shiro权限框架执行流程： 应用程序--Subject对象--SecurityManager--Realm 安全数据访问
 * @author Administrator
 *
 */

public class AuthRealm extends AuthorizingRealm{
	/*	
	@Autowired
	private ManagerService managerService;*/
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthFunctionService authFunctionService;
	
/*	@Autowired
	private TzAuthFunctionMapper authFunctionMapper;
	
	private TzAuthFunctionExample authFunctionExample;
	private TzAuthFunctionExample.Criteria authFunctionCriteria;*/
	
	
	/**
	 * 认证功能
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("shiro认证管理。。。。");
		//获取登录页面用户输入账户和密码组成的token
		UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        String managerName = utoken.getUsername();
        String password = new String(utoken.getPassword());
        TzManager tzManager = new TzManager();
        tzManager.setPassword(password);
        tzManager.setManagerName(managerName);
        
        //获取当前登录账户
        Object principal = utoken.getPrincipal();
        Object credentials = utoken.getCredentials();
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,managerName);
        SubjectContext subjectContext = new DefaultSubjectContext();
        subjectContext.setAuthenticated(true);
        subjectContext.setAuthenticationToken(utoken); 
        subjectContext.setAuthenticationInfo(info);
        /*    //根据用户名查询数据库，获取该用户名的密码
        MsgResult msgResult = managerService.findByManagerName(managerName);
        List<TzManager> list = (List<TzManager>) msgResult.getData();*/
    /*    if(list!=null&&!list.isEmpty()){
        	TzManager tzManager = list.get(0);
        	//找到对用账户密码日志打印
        	Logger.getLogger(getClass()).info("管理员账户认证成功，登录账户："+managerName);
        	//放入rshiro.调用，将获取的密码进行对比
        	//**
        	 * 参数一：期望登录后保存在subject中的信息
        	 * 参数二：返回密码，securityManager安全管理器 自动比较返回密码和用户输入密码是否一致
        	 *       若是密码一致 则认证成功 若密码不一致 报密码错误异常。
        	 *       返回为null 说明该账户不存在 报账户异常。
        	 * 参数三： realm名称
        	 */
		/*
        	return new SimpleAuthenticationInfo(tzManager, tzManager.getPassword(),this.getClass().getName());
        	
        	
        }else{
        	Logger.getLogger(getClass()).info("管理员账户认证失败，登录账户："+managerName);
        }*/
        return new SimpleAuthenticationInfo(tzManager,utoken.getPassword(),this.getClass().getName());
        
	}
	
	/**
	 * 授权功能 doGetAuthorizationInfo()进行角色的添加和权限的添加
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection  principal) {
		System.out.println("shiro授权管理。。。");
		//获取当前登录账户
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		System.out.println("当前登录账户id:-------------"+manager.getId()+"------------");
		System.out.println("当前登录账户：------------"+manager.getManagerName()+"-----------------");
		SimpleAuthorizationInfo authorizationInfo =new SimpleAuthorizationInfo();
		
		//查询对应的角色code
		List<String> roleCodeList = roleService.queryRoleCodeByManager(manager);
		if(roleCodeList.size()>0){
			authorizationInfo.addRoles(roleCodeList);
		}
		
		
		//调用AuthFunctionService层，根据角色id查询对应的权限信息code
		List<String> authFunctionCodeList =  authFunctionService.queryAuthFunctionByManager(manager);
		if(authFunctionCodeList.size()>0){
			authorizationInfo.addStringPermissions(authFunctionCodeList);
		}
	
		return authorizationInfo;
		
	}
}	
	/*	
		RUserVo userVos=(RUserVo)principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
		
		System.out.println(userVos+"----------------------");
		List<String> permissions=new ArrayList<>();
        Set<RRoleVo> roles = userVos.getRoles();
		String power =  userAdmin.getName();
        if("admin".equals(power)){
        	example = new AuthFunctionExample();
        	List<AuthFunction> authFunctions = mapper.selectByExample(example);
        	for(AuthFunction authFunction:authFunctions)
//        		 permissions.add(authFunction.getCode());
       	 permissions.add("all");
        }else if("finance".equals(power)){
        	if(roles.size()>0) {
                for(RRoleVo role : roles) {
                	  Set<AuthFunction> functions = role.getAuthFunctions();
                      if(functions.size()>0) {
                          for(AuthFunction function : functions) {
                              permissions.add(function.getCode());
                          }
                      }
                }
            }
        	permissions.add("one");
        }else{
        	permissions.add("son");
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
	}*/


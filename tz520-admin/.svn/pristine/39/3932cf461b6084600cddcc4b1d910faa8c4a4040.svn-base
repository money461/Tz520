package com.tz.conf;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器配置
 * @author ningmeng
 *
 */
public class MyInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
//		response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.err.println("----拦截查看session---------");
		Object user = request.getSession().getAttribute("manager");
		// 未登录.session处理
		if(user==null){
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			String basePath = request.getScheme() + "://" + request.getServerName() 
					+ ":" + request.getServerPort()+request.getContextPath();
			String loginUrl=basePath+"/admin/manager/loginPage";
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("alert(\"请登录后操作\");");
			builder.append("window.location.href='"+loginUrl+"';");
			builder.append("</script>");
			out.print(builder.toString());
			out.close();
			return false;
		}
		return true;
	}

}

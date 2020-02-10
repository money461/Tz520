package com.tz.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理
 * @author ningmeng
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject, Exception ex) {
		CustomException customException = null;
		//如果抛出的是系统自定义异常则直接转换
		if(ex instanceof CustomException){
			customException = (CustomException)ex;
		}else{
			//不是系统自定义的异常，则重新构造新的未知异常
			customException = new CustomException("发生未知异常，请与系统管理员联系！");
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message",customException.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}
}

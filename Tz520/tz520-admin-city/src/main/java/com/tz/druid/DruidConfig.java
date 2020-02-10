package com.tz.druid;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig { 
	
  @Bean 
  public ServletRegistrationBean druidServlet() { 
    ServletRegistrationBean reg = new ServletRegistrationBean(); 
    reg.setServlet(new StatViewServlet()); 
    reg.addUrlMappings("/druid/*"); 
    reg.addInitParameter("allow", "127.0.0.1"); //白名单 
    //reg.addInitParameter("deny","127.0.0.1"); //黑名单 
    reg.addInitParameter("loginUsername", "admin"); 
    reg.addInitParameter("loginPassword", "admin"); 
    return reg; 
  } 
  @Bean public FilterRegistrationBean filterRegistrationBean() { 
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(); 
    filterRegistrationBean.setFilter(new WebStatFilter());

    //拦截的路径
    filterRegistrationBean.addUrlPatterns("/*"); 
    //放行的路径
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); 
    return filterRegistrationBean; 
   }

}

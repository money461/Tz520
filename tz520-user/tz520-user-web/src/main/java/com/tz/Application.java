
package com.tz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.github.tobato.fastdfs.FdfsClientConfig;
 
/**
 * 主程序入口
 * @author menglin
 *
 */
@SpringBootApplication
@Import(FdfsClientConfig.class)
@ImportResource({"classpath:dubbox/*.xml"})
public class Application extends SpringBootServletInitializer {

 	@Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
		
	}
	private CorsConfiguration buildConfig() {  
        CorsConfiguration corsConfiguration = new CorsConfiguration();  
        corsConfiguration.addAllowedOrigin("*");  
        corsConfiguration.addAllowedHeader("*");  
        corsConfiguration.addAllowedMethod("*");  
        return corsConfiguration;  
    }  
      
    /** 
     * 跨域过滤器 
     * @return 
     */  
    @Bean  
    public CorsFilter corsFilter() {  
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
        source.registerCorsConfiguration("/**", buildConfig()); // 4  
        return new CorsFilter(source);  
    } 
	
}

package com.tz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
 
/**
 * 主程序入口
 * @author menglin
 *
 */
@SpringBootApplication
@ImportResource({"classpath:dubbox/*.xml"})
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
		
	}

	
}

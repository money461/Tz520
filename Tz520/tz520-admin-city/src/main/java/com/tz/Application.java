
package com.tz;

import javax.servlet.http.HttpSession;    

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.tobato.fastdfs.FdfsClientConfig;

/**
 * 主程序入口
 * @author menglin
 *
 */
@Controller
@Import(FdfsClientConfig.class)
@SpringBootApplication
/*@ImportResource({"classpath:dubbox/*.xml"})*/
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@RequestMapping("/")
    String home(HttpSession httpSession) {
	    httpSession.removeAttribute("manager");
        return "redirect:/admin/user/loginPage";
    }
	
}

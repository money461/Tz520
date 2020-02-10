package com.tz.userController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tz.interfaces.UserService;
import com.tz.pojo.User;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@GetMapping("/findById")
	public User selectByPrimaryKey(String  id) {
		LOG.info("invoke----------/user/list");
		return userService.selectByPrimaryKey(id);
	}

}

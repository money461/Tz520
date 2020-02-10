package com.tz.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.interfaces.UserService;
import com.tz.mapper.UserMapper;
import com.tz.pojo.User;

@Service
public class UserServiceImpl implements UserService  {
	
	@Autowired UserMapper userMapper;
	
	@Override
	public User selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}
}

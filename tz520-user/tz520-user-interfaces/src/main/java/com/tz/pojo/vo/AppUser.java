package com.tz.pojo.vo;

import java.io.Serializable;

import com.tz.pojo.TzUser;

public class AppUser  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3638201115061006321L;
	//用户token
	private String userToken;
	//
	private TzUser tzUser;
	//用户类型
	private Integer type;

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public TzUser getTzUser() {
		return tzUser;
	}

	public void setTzUser(TzUser tzUser) {
		this.tzUser = tzUser;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	
	
	
}

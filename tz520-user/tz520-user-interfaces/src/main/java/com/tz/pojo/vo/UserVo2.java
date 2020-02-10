package com.tz.pojo.vo;


import java.io.Serializable;


public class UserVo2  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4815388494107640544L;
	
	//用户id
	private String id;
	//用户类型 0 普通 1 会员 2 爱心
	private Integer type;
	//用户状态
	private Integer status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}	

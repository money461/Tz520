package com.tz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TzZfbUserInfo implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7271882980768777130L;

	private String id;

    private String zfbAuthCode;

    private String zfbUserId;

    private String userId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getZfbAuthCode() {
        return zfbAuthCode;
    }

    public void setZfbAuthCode(String zfbAuthCode) {
        this.zfbAuthCode = zfbAuthCode == null ? null : zfbAuthCode.trim();
    }

    public String getZfbUserId() {
        return zfbUserId;
    }

    public void setZfbUserId(String zfbUserId) {
        this.zfbUserId = zfbUserId == null ? null : zfbUserId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
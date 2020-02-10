package com.tz.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Tz520web {
    private String id;

    private String webIntroduce;
    
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

    public String getWebIntroduce() {
        return webIntroduce;
    }

    public void setWebIntroduce(String webIntroduce) {
        this.webIntroduce = webIntroduce == null ? null : webIntroduce.trim();
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
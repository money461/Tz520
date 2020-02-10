package com.tz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TzMall  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6527142826784690159L;

	private String id;

    private String mallName;

    private String mallUrl;

    private Integer mallStatus;

    private String mallIntroduce;

    private String mallManager;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private String operater;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName == null ? null : mallName.trim();
    }

    public String getMallUrl() {
        return mallUrl;
    }

    public void setMallUrl(String mallUrl) {
        this.mallUrl = mallUrl == null ? null : mallUrl.trim();
    }

    public Integer getMallStatus() {
        return mallStatus;
    }

    public void setMallStatus(Integer mallStatus) {
        this.mallStatus = mallStatus;
    }

    public String getMallIntroduce() {
        return mallIntroduce;
    }

    public void setMallIntroduce(String mallIntroduce) {
        this.mallIntroduce = mallIntroduce == null ? null : mallIntroduce.trim();
    }

    public String getMallManager() {
        return mallManager;
    }

    public void setMallManager(String mallManager) {
        this.mallManager = mallManager == null ? null : mallManager.trim();
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

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater == null ? null : operater.trim();
    }
}
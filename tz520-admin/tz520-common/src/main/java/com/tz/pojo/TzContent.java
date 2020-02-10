package com.tz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TzContent implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4764315787956440969L;

	private String id;

    private Integer contentCategoryId;

    private String contentTitle;

    private String subTitle;

    private String titleDesc;

    private String url;
    
    private String contentDesc;

    private String picFirstUrl;

    private String picSecondUrl;

    private String operater;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createdTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updatedTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getContentCategoryId() {
        return contentCategoryId;
    }

    public void setContentCategoryId(Integer contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle == null ? null : contentTitle.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc == null ? null : titleDesc.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPicFirstUrl() {
        return picFirstUrl;
    }

    public void setPicFirstUrl(String picFirstUrl) {
        this.picFirstUrl = picFirstUrl == null ? null : picFirstUrl.trim();
    }

    public String getPicSecondUrl() {
        return picSecondUrl;
    }

    public void setPicSecondUrl(String picSecondUrl) {
        this.picSecondUrl = picSecondUrl == null ? null : picSecondUrl.trim();
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater == null ? null : operater.trim();
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

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc == null ? null : contentDesc.trim();
    }
}
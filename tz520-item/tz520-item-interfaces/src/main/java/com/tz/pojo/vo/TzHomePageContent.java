package com.tz.pojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 封装广告内容
 * @author Administrator
 *
 */
public class TzHomePageContent  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7110495051734923645L;

	private Integer contentCategoryId;  //广告分类id
	private String contentCategoryName;  //广告分类名称
	
	private String linkAddress ; //广告分类跳转地址
	
	private List<TzContentVo> contentList;

	public Integer getContentCategoryId() {
		return contentCategoryId;
	}

	public void setContentCategoryId(Integer contentCategoryId) {
		this.contentCategoryId = contentCategoryId;
	}

	public String getContentCategoryName() {
		return contentCategoryName;
	}

	public void setContentCategoryName(String contentCategoryName) {
		this.contentCategoryName = contentCategoryName;
	}

	
	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public List<TzContentVo> getContentList() {
		return contentList;
	}

	public void setContentList(List<TzContentVo> contentList) {
		this.contentList = contentList;
	}

	
}

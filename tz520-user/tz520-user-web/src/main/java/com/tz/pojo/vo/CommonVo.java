package com.tz.pojo.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "common")
@PropertySource("classpath:common.properties")
@Component
public class CommonVo {

	//520 商城id
    private String mallId;
    //一级推荐
    private Integer gradeOne;
    //二级推荐
    private Integer gradeTwo;

	public String getMallId() {
		return mallId;
	}

	public void setMallId(String mallId) {
		this.mallId = mallId;
	}

	public Integer getGradeOne() {
		return gradeOne;
	}

	public void setGradeOne(Integer gradeOne) {
		this.gradeOne = gradeOne;
	}

	public Integer getGradeTwo() {
		return gradeTwo;
	}

	public void setGradeTwo(Integer gradeTwo) {
		this.gradeTwo = gradeTwo;
	}
    
    
    
}
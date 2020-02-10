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

	public String getMallId() {
		return mallId;
	}

	public void setMallId(String mallId) {
		this.mallId = mallId;
	}
    
    
    
}
package com.tz.ItemController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tz.interfaces.ContentService;
import com.tz.res.AppMsgResult;

/**
 * 广告新闻信息处理层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("app/api")
public class ContentController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 查询App首页所有信息
	 * @return
	 */
	@RequestMapping(value="/appIndex",method=RequestMethod.GET)
	public AppMsgResult showAppIndex(){
		LOG.info("invoke----------/content/showAppIndex");
		return contentService.showAppIndex();
	}
	
}

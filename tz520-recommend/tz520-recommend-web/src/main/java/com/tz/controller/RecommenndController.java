package com.tz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tz.interfaces.RecommendService;
import com.tz.pojo.TzUserLoveShow;
import com.tz.res.AppMsgResult;
import com.tz.res.MsgResult;


@RestController
@RequestMapping("app/api")
public class RecommenndController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RecommendService recommendService;
	
	/**
	 * 我的推荐数据
	 * @param id
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/getRecommend/{userId}/{userToken}", method = RequestMethod.GET)
	public AppMsgResult getRecommend(@PathVariable("userId") String userId,@PathVariable("userToken") String userToken) {
		LOG.info("invoke----------/user/getRecommend");
		return recommendService.getRecommendList(userId, userToken);
	}
	/**
	 * 爱心值提取
	 * @param userLoveShow
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/withdrawals", method = RequestMethod.POST)
	public AppMsgResult withdrawals(TzUserLoveShow userLoveShow,String userToken,String password) {
		LOG.info("invoke----------/user/withdrawals");
		return recommendService.withdrawals(userLoveShow, userToken,password);
	}
	/**
	 * 爱心消费记录
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/consumptionList/{userId}/{userToken}", method = RequestMethod.GET)
	public AppMsgResult consumptionList(@PathVariable("userId") String userId,@PathVariable("userToken") String userToken) {
		LOG.info("invoke----------/user/consumptionList");
		return recommendService.consumptionList(userId, userToken);
	}
	

}

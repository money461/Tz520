package com.tz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.id.IDUtils;
import com.tz.pojo.TzRecommend;
import com.tz.pojo.TzUser;
import com.tz.pojo.vo.RecommendVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;
import com.tz.service.RecommendService;
import com.tz.service.UserService;

/**
 * 会员推荐用户
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/recommend")
public class RecommendController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RecommendService recommendService;
	
	/**
	 * 用户--推荐列表数据信息
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listPage")
	public ModelAndView listPage(String id) throws Exception {
		LOG.info("invoke----------/recommend/listPage");
		ModelAndView andView = new ModelAndView("tz520/user/recommend/list");
		andView.addObject("refereeId", id);
		return andView;
	}
	/**
	 * 用户--推荐列表数据信息
	 * @param rows 个数
	 * @param curPage 当前页
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public MsgResult list(RecommendVo recommendVo,int  rows,int curPage) throws Exception {
		LOG.info("invoke----------/recommend/list");
		return recommendService.getRecommendList(recommendVo, rows, curPage);
	}
	/**
	 * 结算
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/settlement")
	public MsgResult settlementById(String id) throws Exception {
		LOG.info("invoke----------/recommend/settlement");
		return recommendService.settlementById(id);
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deleteById")
	public MsgResult deleteById(String id) throws Exception {
		LOG.info("invoke----------/recommend/deleteById");
		return recommendService.deleteById(id);
	}
	/**
	 * 所有的 用户--推荐列表数据信息 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listAllPage")
	public ModelAndView listAllPage() throws Exception {
		LOG.info("invoke----------/recommend/listAllPage");
		ModelAndView andView = new ModelAndView("tz520/recommend/list");
		return andView;
	}

}

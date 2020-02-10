package com.tz.controller;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tz.pojo.TzItem;
import com.tz.pojo.TzOrder;
import com.tz.pojo.vo.TzOrderVo;
import com.tz.res.MsgResult;
import com.tz.service.ItemService;
import com.tz.service.OrderService;


/**
 * 订单后台处理
 * 用户 
 * @author menglin
 * 2017年9月25日15:49:11
 */
@RestController
@RequestMapping("/admin/order")
public class OrderController  {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;
	/**
	 * 跳转订单列表页面
	 * @return
	 */
	@RequestMapping("/listPage")
	public ModelAndView listPage(){
		LOG.info("invoke----------/tzItem/listPage");
		return new ModelAndView("tz520/order/list");
	}
	
	/**
	 * 查看订单列表信息
	 * @param page
	 * @param rows
	 * @return
	 */
	
	@GetMapping("/list")
	public MsgResult list(int curPage,int rows,TzOrderVo tzOrderVo){
		LOG.info("invoke----------/order/list");
		return orderService.getOrderList(curPage,rows,tzOrderVo);
	}
	
	/**
	 * 计算总金额以及总的商品数量
	 * @return
	 */
	@PostMapping("/computeOrder")
	public MsgResult computeOrder(TzOrderVo tzOrderVo){
		LOG.info("invoke----------/order/computeOrder");
		return orderService.computeOrder(tzOrderVo);
	}
	
	/**
	 * 跳转至订单编辑页面或者订单详情页面
	 * @return
	 */
	@RequestMapping("/OrderAlterPage")
	public ModelAndView editOrderPage(String type,String id){
		LOG.info("invoke----------/order/OrderDetailPageOrEditOrderPage");
		if("edit".endsWith(type)){
			//跳转至编辑更新页面
			return new ModelAndView("tz520/order/update","id",id);
		}else if("detail".endsWith(type)){
			//跳转至订单详情页面
			return new ModelAndView("tz520/order/detail","id",id);
		}else if("delivery".endsWith(type)){
			//跳转至物流编辑修改页面
			return new ModelAndView("tz520/order/delivery","id",id);
		}else if("exportExcel".endsWith(type)){
			//跳转至数据导出提示页面
			return new ModelAndView("tz520/order/exportExcel");
		}else{
			return new ModelAndView("/error");
			
		}
	}
	
	
	/**
	 * 根据订单id查看订单详情
	 * @return
	 */
	@PostMapping("/findOrderDetail")
	public MsgResult findOrderDetail(String id){
		System.out.println("---查看订单详情---");
		MsgResult result = orderService.findOrderDetail(id);
		return result;
	}
	
	
	/**
	 * 修改订单状态取消订单,发货，退款等信息 修改status
	 * @param id
	 * @return
	 */
	@PostMapping("/alterOrder")
	public MsgResult  alterOrder(String type,String id,String userId){
		MsgResult result = orderService.alterOrder(type,id,userId);
		return result;
	}
	
	/**
	 * 
	 * @param tzOrderVo
	 * @param curPage
	 * @param rows
	 * @return
	 */
	@PostMapping("/importExel")
	public MsgResult ImportOrderExcel(TzOrderVo tzOrderVo,String fileName,HttpServletResponse response){
		MsgResult result = orderService.ImportOrderExcel(tzOrderVo,fileName,response);
		try {
			if(!result.getFlag()){
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(result.getMsg());
			}
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return null;
		
	}
	
}

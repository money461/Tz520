package com.tz.orderController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tz.interfaces.OrderService;
import com.tz.interfaces.PayService;
import com.tz.res.AppMsgResult;

@RestController
@RequestMapping("app/api")
public class OrderController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderService orderSerivce;
	
	/*@Autowired
	private PayService payService;
	*/
	/**
	 * 确认订单回显收货人及商品信息
	 * @param itemIds
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/validateOrder",method=RequestMethod.POST )
	public AppMsgResult validateOrder(String items,String userId,String userToken){
		LOG.info("invoke----/validateOrder");
		AppMsgResult result= orderSerivce.validateOrder(items,userId,userToken);
		return result;
		
	} 
	
	/**
	 * 创建确认生成订单
	 * @param orderVo
	 * @return
	 */
	@RequestMapping(value="/createOrder",method=RequestMethod.POST)
	public AppMsgResult createOrder(String userId,String orderInfo,Integer type,String orderToken, String userToken){
		LOG.info("invoke----/createOrder");
		AppMsgResult result=  orderSerivce.createOrder(userId,orderInfo,type,orderToken,userToken);
		return result;
	}
	
	/**
	 * 根据订单id查询订单详情信息
	 * @param orderId
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/queryOrderById",method=RequestMethod.POST)
	public AppMsgResult queryOrderById(String userId,String orderId,String userToken){
		AppMsgResult result=  orderSerivce.queryOrderById(orderId,userId,userToken);
		return result;
	}

	/**
	 * 根据用户id/订单状态分页查询订单列表信息
	 * @param userId
	 * @param curPage 
	 * @param rows
	 * @param status //订单状态  0、取消订单，1、未付款，2、已付款待发货、3、已发货，4、交易成功，5、交易关闭,6、已退款
	 * @param userToken
	 * @return
	 */
	 
	@RequestMapping(value="/queryOrderByUserId",method=RequestMethod.POST)
	public AppMsgResult queryOrderByUserId(String userId, Integer status, String userToken,int curPage, int rows){
		AppMsgResult result=  orderSerivce.queryOrderByUserId(userId,status,userToken,curPage,rows);
		return result;
		
	}
	
	/**
	 * 根据订单id修改订单状态(付款，取消订单，申请退款/退货)
	 * @param orderId
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/alertOrderStatus",method=RequestMethod.POST)
	public AppMsgResult alertOrderStatus(String userId,String orderId, Integer status,String userToken){
		AppMsgResult result = orderSerivce.alertOrderStatus(userId,orderId,status,userToken);
		return result;
	}		
	

}

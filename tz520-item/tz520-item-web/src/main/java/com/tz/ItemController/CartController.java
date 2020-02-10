package com.tz.ItemController;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tz.interfaces.CartService;
import com.tz.pojo.TzCart;
import com.tz.res.AppMsgResult;

@RestController
@RequestMapping("app/api")
public class CartController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 根据用户id查询购物车列表信息
	 */
	@RequestMapping(value="/listCart",method=RequestMethod.POST)
	public AppMsgResult selectCartList(String userId,String userToken){
		LOG.info("invoke----------/Cart/listCart");
		return cartService.selectCartList(userId,userToken);
	}
	
	/**
	 * 添加单个或者多个商品至购物车
	 * @param cartList
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/addCart",method=RequestMethod.POST)
	public AppMsgResult addCart(String cartList, String userId,String userToken){
		LOG.info("invoke----------/Cart/addCart");
		return cartService.addCart(cartList,userId,userToken);
	}
	
	
	/**
	 *  更新购物车商品信息
	 * @param cart
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/updateCart",method=RequestMethod.POST)
	public AppMsgResult updateCart(String cart,String userId, String userToken){
		LOG.info("invoke----------/Cart/updateCart");
		return cartService.updateCart(cart,userId,userToken);
	}
	
	
	
	/**
	 * 删除购物车信息
	 */
	@RequestMapping(value="/deleteCart",method=RequestMethod.POST)
	public AppMsgResult deleteCart(String userId,String itemIds,String userToken){
		LOG.info("invoke----------/Cart/deleteCart");
		return cartService.deleteCart(userId,itemIds,userToken);
		
	}
	
}

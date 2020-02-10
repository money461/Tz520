package com.tz.interfaces;



import com.tz.res.AppMsgResult;

public interface CartService {

	//查询购物车信息
	AppMsgResult selectCartList(String userId, String userToken);
	
	//删除购物车商品信息
	AppMsgResult deleteCart(String userId, String itemIds, String userToken);

	//添加购物车商品信息
	AppMsgResult addCart(String cartList, String userId, String userToken);

	//添加更新购物车商品信息
	AppMsgResult updateCart(String cart, String userId, String userToken);

}

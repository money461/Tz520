package com.tz.orderController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tz.interfaces.ReceiverInfoService;
import com.tz.res.AppMsgResult;
/**
 * 收货人信息管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("app/api")
public class ReceiverInfoController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReceiverInfoService receiverInfoService;
	/**
	 * 在结算确认订单页面，或者管理收货地址页面，根据用户id回显该用户的收货地址信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/listReceiverinfo",method=RequestMethod.POST)
	public AppMsgResult selectShippingByUserId(String userId,String userToken){
		AppMsgResult result= receiverInfoService.selectShippingByUserId(userId,userToken);
		return result;
	}
	
	/**
	 * 在订单确认页面或者管理收货地址页面创建添加收货地址信息进行保存 type=add 更新修改保存收人信息type=update
	 * @param receiverInfo
	 * @return
	 */
	@RequestMapping(value="/createReceiverInfo",method=RequestMethod.POST)
	public AppMsgResult createReceiverInfo(String userId, String receiverInfo,String type,String userToken){
		AppMsgResult result= receiverInfoService.createReceiverInfo(userId,receiverInfo,type,userToken);
		return result;
		
	}
	/**
	 * 根据收货人信息id设置默认地址 type=default
	 * 根据收货人信息id删除该数据 type=delete
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/optReciverInfo",method=RequestMethod.POST)
	public AppMsgResult editReceiverInfo(String userId, String id,String type, String userToken){
		AppMsgResult result= receiverInfoService.optReciverInfo(userId,id,type,userToken);
		return result;
	}
	
}

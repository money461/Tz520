package com.tz.interfaces;

import com.tz.res.AppMsgResult;

public interface ReceiverInfoService {

	//在结算确认订单页面，根据用户id回显该用户的收货地址信息
	AppMsgResult selectShippingByUserId(String userId, String userToken);

	//在订单确认页面或者管理收货地址页面创建收货地址信息进行保存
	AppMsgResult createReceiverInfo(String userId, String receiverInfo,String type, String userToken);

	//根据id回显收货人信息至修改页面或者根据收货人信息id删除信息
	AppMsgResult optReciverInfo(String userId, String id, String type, String userToken);


}

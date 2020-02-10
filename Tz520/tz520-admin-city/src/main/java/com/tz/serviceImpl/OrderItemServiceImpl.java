package com.tz.serviceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tz.mapper.TzOrderItemMapper;
import com.tz.mapper.TzOrderMapper;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderExample;
import com.tz.pojo.TzOrderItem;
import com.tz.pojo.TzOrderItemExample;
import com.tz.res.MsgResult;
import com.tz.service.OrderItemService;
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private TzOrderItemMapper tzOrderItemMapper;
	private TzOrderItemExample tzOrderItemExample;
	private TzOrderItemExample.Criteria orderItemCriteria;
	
	@Autowired
	private TzOrderMapper tzOrderMapper;
	
	/**
	 * 根据订单id查询订单--商品信息
	 */
	@Override
	public MsgResult findOrderItemById(String orderId) {
		tzOrderItemExample= new TzOrderItemExample();
		orderItemCriteria = tzOrderItemExample.createCriteria();
		orderItemCriteria.andOrderIdEqualTo(orderId);
		List<TzOrderItem> orderItemList = tzOrderItemMapper.selectByExample(tzOrderItemExample);
		
		//查询该订单的邮费
		TzOrder tzOrder = tzOrderMapper.selectByPrimaryKey(orderId);
		BigDecimal postFee = tzOrder.getPostFee();
		BigDecimal payment = tzOrder.getPayment();
		
		ArrayList<Object> arrayList = new ArrayList<Object>();
		arrayList.add(0, orderItemList);
		arrayList.add(1, postFee);
		arrayList.add(2,payment);
		return MsgResult.result(true, "", arrayList);
	}

	//编辑未支付订单信息中商品价格，数量，总金额等信息后进行更新保存
	@Override
	public MsgResult updateOrderItem(String sampleInfo, String postFee,String payment) {
	 List<TzOrderItem> orderItemlist = JSONObject.parseArray(sampleInfo, TzOrderItem.class);
	 String orderId =null; 
	 //获取当前登录账户ID
	 Subject subject = SecurityUtils.getSubject();
	 TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
	 String id = manager.getId();
	 //遍历订单商品列表，修改数据
	 for (TzOrderItem tzOrderItem : orderItemlist) {
		 orderId = tzOrderItem.getOrderId();
		  tzOrderItem.setOperater(id);
		  tzOrderItem.setUpdatedTime(new Date());
		 tzOrderItemMapper.updateByPrimaryKeySelective(tzOrderItem);
		 
	}
		
	 //修改订单邮费
	 TzOrder order = tzOrderMapper.selectByPrimaryKey(orderId);
	 order.setOperater(id);
	 order.setPostFee(new BigDecimal(postFee));
	 order.setPayment(new BigDecimal(payment));
	 tzOrderMapper.updateByPrimaryKeySelective(order);
	 
	 return MsgResult.nodata(true, "订单修改成功！");
	}

}

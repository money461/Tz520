package com.tz.serviceImpl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.mapper.TzOrderMapper;
import com.tz.mapper.TzOrderShippingMapper;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzOrderExample;
import com.tz.pojo.TzOrderShipping;
import com.tz.pojo.TzOrderShippingExample;
import com.tz.res.MsgResult;
import com.tz.service.OrderShippingService;
@Service
public class OrderShippingServiceImpl implements OrderShippingService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
 	@Autowired
	private TzOrderShippingMapper tzOrderShippingMapper;
	
	@Autowired
	private TzOrderMapper tzOrderMapper;
	/**
	 * 根据订单id回显订单物流信息
	 */
	@Override
	public MsgResult findOrderShippingById(String orderId) {
		TzOrderShipping orderShipping = tzOrderShippingMapper.selectByPrimaryKey(orderId);
		return MsgResult.result(true, "", orderShipping);
	}

	/**
	 * 编辑修改后对物流信息进行保存
	 */
	@Override
	public MsgResult updateOrderShipping(TzOrderShipping orderShipping) {
		Date date = new Date();
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
	     orderShipping.setOperater(manager.getId());
		 orderShipping.setUpdatedTime(date);
		 tzOrderShippingMapper.updateByPrimaryKeySelective(orderShipping);
		 
		 //更新订单表
		 String orderId = orderShipping.getOrderId();
		 TzOrder tzOrder = tzOrderMapper.selectByPrimaryKey(orderId);
		 tzOrder.setShippingCode(orderShipping.getShippingCode());
		 tzOrder.setUpdatedTime(date);
		 tzOrderMapper.updateByPrimaryKeySelective(tzOrder);
		return MsgResult.nodata(true, "物流编辑操作成功！");
	}

}

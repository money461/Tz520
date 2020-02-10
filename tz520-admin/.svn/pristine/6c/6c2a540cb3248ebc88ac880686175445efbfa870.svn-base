package com.tz.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.date.DateUtile;
import com.tz.mapper.TzOrderMapper;
import com.tz.mapper.vo.TzOrderMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzOrder;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.TzOrderVo;
import com.tz.res.MsgResult;
import com.tz.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService  {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	//配置文件引入商品所属商家平台
	@Autowired
	private CommonVo common;

	//引入订单扩展类接口
	@Autowired
	private TzOrderMapperVo tzOrderMapperVo;
	
	@Autowired
	private TzOrderMapper tzOrderMapper;

	/**
	 * 分页查看订单信息
	 */
	@Override
	public MsgResult getOrderList(Integer curPage, Integer rows,TzOrderVo tzOrderVo) {
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //设置查询条件
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //订单id
        String id = tzOrderVo.getId();
        if(StringUtils.isNotEmpty(id)){
        	map.put("id", "%"+id+"%");
        }else{
        	map.put("id", null);
        }
        //status状态
        map.put("status", tzOrderVo.getStatus());
        
        //商家平台id
    	String mallId = common.getMallId();
    	map.put("mallId", mallId);
    	//时间段
    	if(null != tzOrderVo.getStartTime() && null != tzOrderVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(tzOrderVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(tzOrderVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        
    	//调用扩展类接口查询数据
    	List<TzOrderVo> orderList = tzOrderMapperVo.selectOrderList(map);
    	
        //获取分页数据结果
        PageInfo<TzOrderVo> pageInfo = new PageInfo<TzOrderVo>(orderList);
        
        //返回结果
        MsgResult result = MsgResult.result(true, "", pageInfo);
		return result;
	}


	/**
	 * 根据订单id查看订单详细信息（基本信息、订单商品信息、订单物流信息）
	 */

	@Override
	public MsgResult findOrderDetail(String id) {
		TzOrderVo orderVo = tzOrderMapperVo.findOrderDetail(id);
	  return MsgResult.result(true, "",orderVo);
	}
	
	/**
	 * 修改订单状态取消订单,发货，退款等信息 修改status
	 */
	@Override
	public MsgResult alterOrder(String type, String id) {
		Date date = new Date();
		TzOrder order=null;
		//获取当前登录账户ID
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		//发货
		if("send".equals(type)){
			List<TzOrder> orderList =new  ArrayList<TzOrder>();
			String[] ids = id.split(",");
			for (String oid : ids) {
				TzOrder tzOrder = new TzOrder();
				tzOrder.setId(oid);
				//修改订单表订单状态
				tzOrder.setStatus(3);
				//设置操作员
				tzOrder.setOperater(manager.getId());
				//设置发货时间
				tzOrder.setConsignTime(date);
				tzOrder.setUpdatedTime(date);
				orderList.add(tzOrder);
			}
			//批量处理发货
			tzOrderMapperVo.batchShipping(orderList);
			
			 /**
			  * 调用物流端口，派送商品，MQ通知快递员上门取件。
			  */
			 
			 System.out.println("------------通知快递员上门取件------------------");
			 
			
			return MsgResult.nodata(true, "发货成功！");
		}else if("orderCancel".equals(type)){
			//取消订单
			 order = tzOrderMapper.selectByPrimaryKey(id);
			 order.setStatus(0);
			 order.setUpdatedTime(date);
			
			 order.setOperater(manager.getId());
			 tzOrderMapper.updateByPrimaryKeySelective(order);
			 return MsgResult.nodata(true, "订单取消成功！");
		}else if("drawback".equals(type)){
			//退款操作
			 order = tzOrderMapper.selectByPrimaryKey(id);
			/*
			 * 获取订单的支付账户信息,调用三方支付平台
			 * 
			 */
			 System.out.println("-------该订单的支付账户信息--------"+order.getAccount());
			 System.out.println("-------------调用第三方支付平台--------------------");
			 order.setStatus(6);
			 order.setUpdatedTime(date);
			 tzOrderMapper.updateByPrimaryKeySelective(order);
			 return MsgResult.nodata(true, "退款成功！");
		}
		return null;
	
	}




}

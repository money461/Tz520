package com.tz.serviceImpl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.excel.ExcelUtil;
import com.tz.mapper.TzOrderMapper;
import com.tz.mapper.vo.TzOrderMapperVo;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzOrder;
import com.tz.pojo.TzUserLove;
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
	
	//引入缓存
	@Autowired
	private RedisCache cache;

	/**
	 * 订单后台取消、发货后、退款后清空我的订单列表缓存信息
	 */
	public void deleteCache(String userId){
		String orderCache_key = RedisCache.CAHCENAME+"|selectOrderList|"+userId;
		cache.deleteCache(orderCache_key);
		LOG.info("-------------清除该用户我的订单列表缓存信息---------");
	}
	
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
        
        String userPhone = tzOrderVo.getUserPhone();
        if(StringUtils.isNotEmpty(userPhone)){
        	map.put("userPhone", userPhone+"%");
        }else{
        	map.put("userPhone", null);
        }
        
        String consignName = tzOrderVo.getConsignName();
        if(StringUtils.isNotEmpty(consignName)){
        	map.put("consignName", "%"+consignName+"%");
        }else{
        	map.put("consignName",null);
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
	public MsgResult alterOrder(String type, String id,String userIds) {
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
			//删除我的订单缓存信息
			String[] userIdstr = userIds.split(",");
			for (String userId : userIdstr) {
				this.deleteCache(userId);
			}
			
			 /**
			  * 调用物流端口，派送商品，MQ通知快递员上门取件。
			  */
			 
			 System.out.println("------------通知快递员上门取件------------------");
			 
			
			return MsgResult.nodata(true, "发货成功！");
		}else if("orderCancel".equals(type)){
			//取消订单
			order = tzOrderMapper.selectByPrimaryKey(id);
			//取消订单操作，需要返回该用户的爱心值 获取订单表中被扣除的爱心值,取消支付payOrderToken
		    String payOrderCache_key = RedisCache.CAHCENAME+"|payOrderToken|"+id;
		    cache.deleteCache(payOrderCache_key);
			Integer loveValue = order.getLoveValue();
			String userId = order.getUserId();
			if(loveValue>0){
				//扣除爱心值超过0才返还爱心值
				TzUserLove userLove = new TzUserLove();
				userLove.setUserId(userId);
				userLove.setLoveSurplus(loveValue);
				userLove.setUpdatedTime(new Date());
				//增加该用户剩余爱心值
				tzOrderMapperVo.addLoveSurplus(userLove);
			}
			 order.setStatus(0);
			 order.setUpdatedTime(date);
			
			 order.setOperater(manager.getId());
			 tzOrderMapper.updateByPrimaryKeySelective(order);
			 //删除我的订单缓存信息
			 this.deleteCache(userId);
			 return MsgResult.nodata(true, "订单取消成功！");
		}else if("drawback".equals(type)){
			//退款操作
			 order = tzOrderMapper.selectByPrimaryKey(id);
			 String userId = order.getUserId();
			 
			 //退换该用户的抵扣的爱心值
			 Integer loveValue = order.getLoveValue();
				if(loveValue>0){
					//扣除爱心值超过0才返还爱心值
					TzUserLove userLove = new TzUserLove();
					userLove.setUserId(userId);
					userLove.setLoveSurplus(loveValue);
					userLove.setUpdatedTime(new Date());
					//增加该用户剩余爱心值
					tzOrderMapperVo.addLoveSurplus(userLove);
				}
			 
			/*
			 * 获取订单的支付账户信息,调用三方支付平台
			 * 
			 */
			 System.out.println("-------该订单的支付账户信息--------"+order.getAccount());
			 System.out.println("-------------调用第三方支付平台--------------------");
			 order.setStatus(6);
			 order.setUpdatedTime(date);
			 order.setOperater(manager.getId());
			 tzOrderMapper.updateByPrimaryKeySelective(order);
			 
			//删除我的订单缓存信息
			 this.deleteCache(userId);
			 return MsgResult.nodata(true, "退款成功！");
		}
		return null;
	
	}
   /**
    * 将订单数据导出excel
    */
	@Override
	public MsgResult ImportOrderExcel(TzOrderVo tzOrderVo,String fileName,HttpServletResponse response) {
		 //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //订单id
        String id = tzOrderVo.getId();
        if(StringUtils.isNotEmpty(id)){
        	map.put("id", "%"+id+"%");
        }else{
        	map.put("id", null);
        }
        
        String userPhone = tzOrderVo.getUserPhone();
        if(StringUtils.isNotEmpty(userPhone)){
        	map.put("userPhone", userPhone+"%");
        }else{
        	map.put("userPhone", null);
        }
        
        String consignName = tzOrderVo.getConsignName();
        if(StringUtils.isNotEmpty(consignName)){
        	map.put("consignName", "%"+consignName+"%");
        }else{
        	map.put("consignName",null);
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
    	List<TzOrderVo> orderList = tzOrderMapperVo.exportExcelOrderList(map);
    	if(orderList.isEmpty()){
    		return MsgResult.nodata(false, "在该条件下没有找到订单数据，不能生成excel!");
    	}else{
    	String[] title = new String[]{"订单编号","买家编号","买家账户","支付账户","实付总金额","实付总邮费","购买总件数","抵扣爱心值","折扣","支付方式","订单状态","订单属性",
    							"运单编号","货运公司名称","收货人姓名","收件人电话","省份","城市","区县","详细地址","商品ID","商品标题","商品价格","普通会员价","实付价格","选购数量","商品邮费","商品实付金额(含邮费)","订单创建时间","订单更新时间","订单付款时间","订单发货时间","订单完成时间","订单关闭时间"};
    	//方法二
    	String[] order = new String[]{"id","userId","userPhone","account","payment","postFee","orderNum","loveValue","discount","paymentType","status","type",
    			"shippingCode","companyName","receiverName","receiverMobile","receiverState","receiverCity","receiverDistrict","receiverAddress",
    			"itemId","itemTitle","price","memberPrice","realPrice","num","itemPost","totalFee","createdTime","updatedTime","paymentTime","consignTime","endTime","closeTime"};
    	String orderItemList = "orderItemList";
    	String list = ExcelUtil.exportExcel(orderList, order, title,orderItemList, fileName, response);
		return MsgResult.nodata(true, "成功导出数据:"+list+"条！");
    	}
	}

/**
 * 计算总金额以及总的商品数量
 */
@Override
public MsgResult computeOrder(TzOrderVo tzOrderVo) {
	//封装map集合
    Map<String,Object> map = new HashMap<String, Object>();
    //订单id
    String id = tzOrderVo.getId();
    if(StringUtils.isNotEmpty(id)){
    	map.put("id", "%"+id+"%");
    }else{
    	map.put("id", null);
    }
    
    String userPhone = tzOrderVo.getUserPhone();
    if(StringUtils.isNotEmpty(userPhone)){
    	map.put("userPhone", userPhone+"%");
    }else{
    	map.put("userPhone", null);
    }
    
    String consignName = tzOrderVo.getConsignName();
    if(StringUtils.isNotEmpty(consignName)){
    	map.put("consignName", "%"+consignName+"%");
    }else{
    	map.put("consignName",null);
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
	BigDecimal totalPayment = new BigDecimal("0");//总金额
	int totalItemNum = 0; //总的商品数量
	
	Map<String,Object> List = new HashMap<String,Object>();
	for (TzOrderVo tzOrderVo2 : orderList) {
		totalPayment = totalPayment.add(tzOrderVo2.getPayment());
		totalItemNum +=tzOrderVo2.getOrderNum();
		List.put("totalPayment", totalPayment.setScale(2,BigDecimal.ROUND_HALF_UP));
		List.put("totalItemNum", totalItemNum);
	}
	
	return  MsgResult.result(true,"",List);
}




}

package com.tz.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tz.cache.RedisCache;
import com.tz.mapper.vo.TzOrderMapperVo;

/**
 * 订单定时确认收货
 * @author Administrator
 *
 */
@Component
@Configurable
@EnableScheduling
public class OrderQuartz {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TzOrderMapperVo orderMapperVo;
	
	@Autowired
	private RedisCache cache;
	
	//定时确认收货
	//每天凌晨2点执行一次对8天前创建的已发货的订单执行状态修改为交易成功！
	@Scheduled(cron = "0 0 2 * * ?")
	public void OrderAlterStatus(){
		//当前时间
		LOG.info("The time is now " + dateFormat ().format (new Date()));
		//清空所有用户的我的订单缓存列表信息
		String orderCache_key = RedisCache.CAHCENAME+"|selectOrderList|*";
		cache.deleteCacheWithPattern(orderCache_key);
		LOG.info("清空所有用户订单列表缓存"+orderCache_key);
		Date date = new DateTime().minusDays(8).toDate();
		String format = dateFormat ().format (date);
		//当前时间向前推8天  
		orderMapperVo.orderQuartzWork(format);
		LOG.info("正在执行8前已发货订单状态修改"+new DateTime().minusDays(8).toDate());
	}

	private SimpleDateFormat  dateFormat() {
		 return new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	}
	
}
